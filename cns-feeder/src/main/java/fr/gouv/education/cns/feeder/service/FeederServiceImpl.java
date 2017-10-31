package fr.gouv.education.cns.feeder.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osivia.directory.v2.model.ext.WorkspaceRole;
import org.osivia.directory.v2.service.PersonUpdateService;
import org.osivia.directory.v2.service.WorkspaceService;
import org.osivia.portal.api.directory.v2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fr.gouv.education.cns.directory.v2.model.CnsPerson;
import fr.gouv.education.cns.feeder.dao.CnsSourcePersonDao;
import fr.gouv.education.cns.feeder.model.CnsSourcePerson;

/**
 * Feeder portlet service implementations.
 * 
 * @author Cédric Krommenhoek
 * @see FeederService
 */
@Service
public class FeederServiceImpl implements FeederService {

    /** Environment. */
    @Autowired
    private Environment environment;

    /** CNS person DAO. */
    @Autowired
    private CnsSourcePersonDao cnsPersonDao;

    /** Person service. */
    @Autowired
    private PersonUpdateService personService;

    /** Workspace service. */
    @Autowired
    private WorkspaceService workspaceService;


    /** Log. */
    private final Log log;


    /**
     * Constructor.
     */
    public FeederServiceImpl() {
        super();
        this.log = LogFactory.getLog(this.getClass());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void invoke(HttpServletRequest request) {
        // Authentication DOM element
        Element authentication = this.getAuthentication(request);

        // Principal identifier
        String principalId = this.getPrincipalId(authentication);
        // Principal attributes
        Map<String, String> principalAttributes = this.getPrincipalAttributes(authentication);


        // User DN
        Name userDn = this.personService.getEmptyPerson().buildDn(principalId);

        // Person
        Person person = this.personService.getPersonNoCache(userDn);

        if (person == null) {
            // CNS source person
            CnsSourcePerson sourcePerson = this.cnsPersonDao.getPerson(principalId);

            if (sourcePerson != null) {
                // Creation
                log.info("Création de la personne : " + sourcePerson.getCn());
                person = this.toCnsPerson(sourcePerson);

                if (person != null) {
                    this.personService.create(person);

                    // Shared workspace
                    String sharedWorkspaceId = this.environment.getProperty("COMMUN");
                    log.info("Ajout à l'espace commun : " + sharedWorkspaceId);
                    this.workspaceService.addOrModifyMember(sharedWorkspaceId, person.getDn(), WorkspaceRole.WRITER);

                    // Entity workspace
                    String entity = sourcePerson.getEntity();
                    if (StringUtils.isNotBlank(entity)) {
                        String workspaceId = this.environment.getProperty(StringUtils.upperCase(entity));
                        if (StringUtils.isNotEmpty(workspaceId)) {
                            log.info("Ajout à l'espace : " + sharedWorkspaceId);
                            this.workspaceService.addOrModifyMember(workspaceId, person.getDn(), WorkspaceRole.WRITER);
                        }
                    }
                } else {
                    this.log.error("Unable to create user " + userDn);
                }
            }
        }


        if (person != null) {
            // Refresh
            person = this.personService.getPersonNoCache(person.getDn());

            if (person.getExternal() == null) {
                // Update external indicator
                boolean external = BooleanUtils.toBoolean(principalAttributes.get("external"));
                person.setExternal(external);
            }

            // Update last connection date
            person.setLastConnection(new Date());

            this.personService.update(person);
        }
    }


    /**
     * Get authentication DOM element.
     * 
     * @param request HTTP servlet request
     * @return DOM element
     */
    private Element getAuthentication(HttpServletRequest request) {
        // CAS response
        String casResponse = (String) request.getAttribute("casresponse");

        // Authentication DOM element
        Element authentication;
        try {
            // Document builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Document builder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Document
            StringReader reader = new StringReader(casResponse);
            InputSource inputSource = new InputSource(reader);
            Document document = builder.parse(inputSource);

            authentication = (Element) document.getElementsByTagName("cas:serviceResponse").item(0);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        return authentication;
    }


    /**
     * Get principal identifier.
     * 
     * @param authentication authentication DOM element
     * @return principal identifier
     */
    private String getPrincipalId(Element authentication) {
        return authentication.getElementsByTagName("cas:user").item(0).getTextContent();
    }


    /**
     * Get principal attributes.
     * 
     * @param authentication authentication DOM element
     * @return principal attributes
     */
    private Map<String, String> getPrincipalAttributes(Element authentication) {
        // CAS attributes
        Node casAttributes;
        NodeList casAttributesList = authentication.getElementsByTagName("cas:attributes");
        if (casAttributesList == null) {
            casAttributes = null;
        } else {
            casAttributes = casAttributesList.item(0);
        }

        // Principal attributes
        Map<String, String> principalAttributes;
        if (casAttributes == null) {
            principalAttributes = new HashMap<>(0);
        } else {
            int length = casAttributes.getChildNodes().getLength();
            principalAttributes = new HashMap<>(length);
            for (int i = 0; i < length; i++) {
                // CAS attribute
                Node casAttribute = casAttributes.getChildNodes().item(i);
                if (casAttribute.getNodeType() == Node.ELEMENT_NODE) {
                    String attributeName = casAttribute.getNodeName();
                    if (StringUtils.startsWith(attributeName, CAS_ATTRIBUTE_PREFIX)) {
                        String key = StringUtils.removeStart(attributeName, CAS_ATTRIBUTE_PREFIX);
                        String value = casAttribute.getTextContent();

                        principalAttributes.put(key, value);
                    }
                }
            }
        }

        return principalAttributes;
    }


    /**
     * Convert CNS source person to CNS person.
     * 
     * @param sourcePerson CNS source person
     * @return CNS person
     */
    private CnsPerson toCnsPerson(CnsSourcePerson sourcePerson) {
        // display name
        String displayName = sourcePerson.getGivenName() + " " + sourcePerson.getSn();

        // Person
        Person person = this.personService.getEmptyPerson();

        // CNS person
        CnsPerson cnsPerson;
        if ((person != null) && (person instanceof CnsPerson)) {
            cnsPerson = (CnsPerson) person;

            cnsPerson.setUid(sourcePerson.getUid());
            cnsPerson.setCn(displayName);
            cnsPerson.setDisplayName(displayName);
            cnsPerson.setGivenName(sourcePerson.getGivenName());
            cnsPerson.setMail(sourcePerson.getMail());
            cnsPerson.setSn(sourcePerson.getSn());

            // Entity
            String entity = sourcePerson.getEntity();
            if (StringUtils.isNotBlank(entity)) {
                cnsPerson.setEntity(StringUtils.upperCase(entity));
            }
        } else {
            cnsPerson = null;
        }

        return cnsPerson;
    }

}
