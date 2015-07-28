package org.osivia.portal.demo.project.customizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;

import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.customization.ICustomizationModulesRepository;
import org.osivia.portal.api.internationalization.IBundleFactory;
import org.osivia.portal.api.internationalization.IInternationalizationService;
import org.osivia.portal.api.locator.Locator;


/**
 * Technical portlet for attributes bundles customization.
 *
 * @author Jean-Sébastien steux
 * @see GenericPortlet
 * @see ICustomizationModule
 */
public class ProjectCMSCustomizerPortlet extends GenericPortlet implements ICustomizationModule {

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "osivia.demo.customizer.cms.project.name";
    /** Customization modules repository attribute name. */
    private static final String ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY = "CustomizationModulesRepository";

    /** Customization modules repository. */
    private ICustomizationModulesRepository repository;
    /** Internationalization customization module metadatas. */
    private final CustomizationModuleMetadatas metadatas;

    
    
    /** Bundle factory. */
    private IBundleFactory bundleFactory;
    
    private long deployTs=0L;

     
      

    /**
     * Constructor.
     */
    public ProjectCMSCustomizerPortlet() {
        super();
        this.metadatas = this.generateMetadatas();
        this.deployTs = System.currentTimeMillis();
    }


    /**
     * Utility method used to generate attributes bundles customization module metadatas.
     *
     * @return metadatas
     */
    private final CustomizationModuleMetadatas generateMetadatas() {
        CustomizationModuleMetadatas metadatas = new CustomizationModuleMetadatas();
        metadatas.setName(CUSTOMIZER_NAME);
        metadatas.setModule(this);
        metadatas.setCustomizationIDs(Arrays.asList("osivia.customizer.cms.id"));
        metadatas.setOrder(1200);
        return metadatas;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws PortletException {
        super.init();
        
        // Bundle factory
        IInternationalizationService internationalizationService = Locator.findMBean(IInternationalizationService.class,
                IInternationalizationService.MBEAN_NAME);
        this.bundleFactory = internationalizationService.getBundleFactory(this.getClass().getClassLoader());

        
        
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
    public void customize(String customizationID, CustomizationContext context) {
        
        Map<String, Object> attributes = context.getAttributes();
        
        
        Map<String,String> jsp = (Map<String,String>) attributes.get("osivia.customizer.cms.jsp"); 
        if( jsp == null)    {
            jsp = new HashMap<String, String>();
            attributes.put("osivia.customizer.cms.jsp", jsp); 
        }
        jsp.put("doc-view-note", CUSTOMIZER_NAME);
        attributes.put("doc-view-note."+CUSTOMIZER_NAME, "/WEB-INF/jsp/document/view-note.jsp");

        
        attributes.put("osivia.customizer.cms."+CUSTOMIZER_NAME+".path", this.getPortletContext().getRealPath("/"));        
        attributes.put("osivia.customizer.cms."+CUSTOMIZER_NAME+".ts", deployTs);


    }

}
