package fr.gouv.education.cns.feeder;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
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
import org.osivia.directory.v2.service.PersonUpdateService;
import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.customization.ICustomizationModulesRepository;
import org.osivia.portal.api.directory.v2.DirServiceFactory;
import org.osivia.portal.api.directory.v2.model.Person;
import org.osivia.portal.api.feeder.IFeederService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Feeder customizer.
 * 
 * @author Cédric Krommenhoek
 * @see GenericPortlet
 * @see ICustomizationModule
 */
public class FeederCustomizer extends GenericPortlet implements ICustomizationModule {

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "cns.customizer.feeder";
    /** Customization modules repository attribute name. */
    private static final String ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY = "CustomizationModulesRepository";

    /** CAS attribute prefix. */
    private static final String CAS_ATTRIBUTE_PREFIX = "cas:";


    /** Customization modules repository. */
    private ICustomizationModulesRepository repository;


    /** Customization module metadatas. */
    private final CustomizationModuleMetadatas metadatas;
    /** Log. */
    private final Log log;

    /** Person service. */
    private final PersonUpdateService personService;


    /**
     * Constructor.
     */
    public FeederCustomizer() {
        super();
        this.metadatas = this.generateMetadatas();
        this.log = LogFactory.getLog(this.getClass());

        // Person service
        this.personService = DirServiceFactory.getService(PersonUpdateService.class);
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
        // CAS response
        String casResponse = (String) request.getAttribute("casresponse");

        // Authentication element
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

        // Principal identifier
        String principalId = authentication.getElementsByTagName("cas:user").item(0).getTextContent();

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


        // Person
        Person person = this.personService.getPerson(principalId);

        if (person == null) {

        }



        // TODO Auto-generated method stub

    }

}
