package fr.gouv.education.cns.feeder.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osivia.directory.v2.model.ext.WorkspaceRole;
import org.osivia.directory.v2.service.WorkspaceService;
import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.customization.ICustomizationModulesRepository;
import org.osivia.portal.api.feeder.IFeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fr.gouv.education.cns.directory.v2.model.CnsPerson;
import fr.gouv.education.cns.directory.v2.service.CnsPersonService;
import fr.gouv.education.cns.feeder.dao.CnsSourcePersonDao;
import fr.gouv.education.cns.feeder.model.CnsSourcePerson;

/**
 * Feeder controller.
 * 
 * @author Cédric Krommenhoek
 * @see GenericPortlet
 * @see ICustomizationModule
 */
@Controller
public class FeederController extends GenericPortlet implements ICustomizationModule {

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "cns.customizer.feeder";
    /** Customization modules repository attribute name. */
    private static final String ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY = "CustomizationModulesRepository";

    /** CAS attribute prefix. */
    private static final String CAS_ATTRIBUTE_PREFIX = "cas:";


    /** Customization modules repository. */
    private ICustomizationModulesRepository repository;

    
    /** Environment. */
    @Autowired
    private Environment environment;

    /** CNS person DAO. */
    @Autowired
    private CnsSourcePersonDao cnsPersonDao;

    /** Person service. */
    @Autowired
    private CnsPersonService personService;
    
    /** Workspace service. */
    @Autowired
    private WorkspaceService workspaceService;


    /** Customization module metadatas. */
    private final CustomizationModuleMetadatas metadatas;
    /** Log. */
    private final Log log;


    /**
     * Constructor.
     */
    public FeederController() {
        super();
        this.metadatas = this.generateMetadatas();
        this.log = LogFactory.getLog(this.getClass());
    }


    /**
     * Generate customization module metadatas.
     * 
     * @return metadatas
     */
    private CustomizationModuleMetadatas generateMetadatas() {
        final CustomizationModuleMetadatas metadatas = new CustomizationModuleMetadatas();
        metadatas.setName(CUSTOMIZER_NAME);
        metadatas.setModule(this);
        metadatas.setCustomizationIDs(Arrays.asList(IFeederService.CUSTOMIZER_ID));
        return metadatas;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void init(PortletConfig portletConfig) throws PortletException {
        super.init(portletConfig);
        this.repository = (ICustomizationModulesRepository) this.getPortletContext().getAttribute(ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY);
        this.repository.register(this.metadatas);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        super.destroy();
        this.repository.unregister(this.metadatas);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void customize(CustomizationContext customizationContext) {
        // Customization attributes
        Map<String, Object> customizationAttributes = customizationContext.getAttributes();
        // HTTP servlet request
        HttpServletRequest request = (HttpServletRequest) customizationAttributes.get(IFeederService.CUSTOMIZER_ATTRIBUTE_REQUEST);

        // Authentication DOM element
        Element authentication = this.getAuthentication(request);

        // Principal identifier
        String principalId = this.getPrincipalId(authentication);
        // Principal attributes
        Map<String, String> principalAttributes = this.getPrincipalAttributes(authentication);


        // CNS person
        CnsPerson person = this.personService.getPerson(principalId);

        if (person == null) {
            // CNS source person
            CnsSourcePerson sourcePerson = this.cnsPersonDao.getPerson(principalId);

            if (sourcePerson != null) {
                // Creation
                log.info("Création de la personne : " + sourcePerson.getCn());
                person = this.toCnsPerson(sourcePerson);
                person.setExternal(true);
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
            }
        }


        if (person != null) {
            // Refresh
            person = this.personService.getPersonNoCache(person.getDn());

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
        
        // CNS person
        CnsPerson person = this.personService.getEmptyPerson();
        person.setUid(sourcePerson.getUid());
        person.setCn(displayName);
        person.setDisplayName(displayName);
        person.setGivenName(sourcePerson.getGivenName());
        person.setMail(sourcePerson.getMail());
        person.setSn(sourcePerson.getSn());

        // Entity
        String entity = sourcePerson.getEntity();
        if (StringUtils.isNotBlank(entity)) {
            person.setEntity(StringUtils.upperCase(entity));
        }
        
        return person;
    }

}
