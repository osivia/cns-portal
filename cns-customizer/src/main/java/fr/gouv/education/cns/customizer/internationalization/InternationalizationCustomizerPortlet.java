package fr.gouv.education.cns.customizer.internationalization;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;

import org.apache.commons.lang.StringUtils;
import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.customization.ICustomizationModulesRepository;
import org.osivia.portal.api.internationalization.IInternationalizationService;


/**
 * Technical portlet for internationalization customization.
 *
 * @author Cédric Krommenhoek
 * @see GenericPortlet
 * @see ICustomizationModule
 */
public class InternationalizationCustomizerPortlet extends GenericPortlet implements ICustomizationModule {

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "cns.customizer.internationalization.name";
    /** Customization modules repository attribute name. */
    private static final String ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY = "CustomizationModulesRepository";
    /** Custom resource bundle name. */
    private static final String RESOURCE_BUNDLE_NAME = "Resource";

    /** Customization modules repository. */
    private ICustomizationModulesRepository repository;
    /** Internationalization customization module metadatas. */
    private final CustomizationModuleMetadatas metadatas;


    /**
     * Constructor.
     */
    public InternationalizationCustomizerPortlet() {
        super();
        this.metadatas = this.generateMetadatas();
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
    public void customize(CustomizationContext context) {
        Map<String, Object> attributes = context.getAttributes();
        String key = (String) attributes.get(IInternationalizationService.CUSTOMIZER_ATTRIBUTE_KEY);
        Locale locale = (Locale) attributes.get(IInternationalizationService.CUSTOMIZER_ATTRIBUTE_LOCALE);

        if (StringUtils.isNotBlank(key) && (locale != null)) {
            try {
                ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
                String result = resourceBundle.getString(key);
                attributes.put(IInternationalizationService.CUSTOMIZER_ATTRIBUTE_RESULT, result);
            } catch (MissingResourceException e) {
                // Do nothing
            }
        }
    }


    /**
     * Utility method used to generate internationalization customization module metadatas.
     *
     * @return metadatas
     */
    private final CustomizationModuleMetadatas generateMetadatas() {
        CustomizationModuleMetadatas metadatas = new CustomizationModuleMetadatas();
        metadatas.setName(CUSTOMIZER_NAME);
        metadatas.setModule(this);
        metadatas.setCustomizationIDs(Arrays.asList(IInternationalizationService.CUSTOMIZER_ID));
        return metadatas;
    }

}
