package fr.gouv.education.cns.feeder.controller;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletRequest;

import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.customization.ICustomizationModulesRepository;
import org.osivia.portal.api.feeder.IFeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.gouv.education.cns.feeder.service.FeederService;

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


    /** Customization modules repository. */
    private ICustomizationModulesRepository repository;


    /** Portlet config. */
    @Autowired
    private PortletConfig portletConfig;

    /** Portlet service. */
    @Autowired
    private FeederService service;


    /** Customization module metadatas. */
    private final CustomizationModuleMetadatas metadatas;



    /**
     * Constructor.
     */
    public FeederController() {
        super();
        this.metadatas = this.generateMetadatas();
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
     * Post-contruct.
     * 
     * @throws PortletException
     */
    @PostConstruct
    public void postConstruct() throws PortletException {
        super.init(this.portletConfig);
        this.repository = (ICustomizationModulesRepository) this.getPortletContext().getAttribute(ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY);
        this.repository.register(this.metadatas);
    }


    /**
     * Pre-destroy.
     * 
     * @throws PortletException
     */
    @PreDestroy
    public void preDestroy() throws PortletException {
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

        this.service.invoke(request);
    }

}
