package org.osivia.portal.demo.customizer;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.BooleanUtils;
import org.jboss.portal.core.model.portal.Page;
import org.jboss.portal.core.model.portal.Portal;
import org.osivia.portal.api.PortalException;
import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.customization.ICustomizationModulesRepository;
import org.osivia.portal.api.customization.IProjectCustomizationConfiguration;
import org.osivia.portal.api.locator.Locator;
import org.osivia.portal.api.urls.IPortalUrlFactory;

/**
 * Project customizer portlet.
 *
 * @author Cédric Krommenhoek
 * @see GenericPortlet
 * @see ICustomizationModule
 */
public class ProjectCustomizerPortlet extends GenericPortlet implements ICustomizationModule {

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "osivia.demo.customizer.project.name";
    /** Customization modules repository attribute name. */
    private static final String ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY = "CustomizationModulesRepository";


    /** Customization modules repository. */
    private ICustomizationModulesRepository repository;

    /** Internationalization customization module metadatas. */
    private final CustomizationModuleMetadatas metadatas;
    /** Portal URL factory. */
    private final IPortalUrlFactory portalURLFactory;


    /**
     * Constructor.
     */
    public ProjectCustomizerPortlet() {
        super();
        this.metadatas = this.generateMetadatas();

        // Portal URL factory
        this.portalURLFactory = Locator.findMBean(IPortalUrlFactory.class, "osivia:service=UrlFactory");
    }


    /**
     * Utility method used to generate attributes bundles customization module metadatas.
     *
     * @return metadatas
     */
    private CustomizationModuleMetadatas generateMetadatas() {
        CustomizationModuleMetadatas metadatas = new CustomizationModuleMetadatas();
        metadatas.setName(CUSTOMIZER_NAME);
        metadatas.setModule(this);
        metadatas.setCustomizationIDs(Arrays.asList(IProjectCustomizationConfiguration.CUSTOMIZER_ID));
        return metadatas;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws PortletException {
        super.init();
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
    public void customize(String customizationID, CustomizationContext context) {
        Map<String, Object> attributes = context.getAttributes();

        // Project customization configuration
        IProjectCustomizationConfiguration configuration = (IProjectCustomizationConfiguration) attributes
                .get(IProjectCustomizationConfiguration.CUSTOMIZER_ATTRIBUTE_CONFIGURATION);

        if (configuration.isBeforeInvocation() && !configuration.isAdministrator()) {
            HttpServletRequest request = configuration.getHttpServletRequest();
            boolean redirect = BooleanUtils.toBoolean(request.getParameter("redirect"));
            Principal principal = request.getUserPrincipal();

            if (!redirect && (principal != null)) {
                Page page = configuration.getPage();
                Portal portal = page.getPortal();
                if (page.equals(portal.getDefaultPage())) {
                    try {
                        String redirectionURL = this.portalURLFactory
                                .adaptPortalUrlToNavigation(context.getPortalControllerContext(), "/portal/auth/MonEspace");
                        configuration.setRedirectionURL(redirectionURL);
                    } catch (PortalException e) {
                        // Do nothing
                    }
                }
            }
        }
    }
}
