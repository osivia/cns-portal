package fr.gouv.education.cns.customizer.attributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;

import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.customization.ICustomizationModulesRepository;
import org.osivia.portal.api.theming.IAttributesBundle;

/**
 * Technical portlet for attributes bundles customization.
 *
 * @author Cédric Krommenhoek
 * @see GenericPortlet
 * @see ICustomizationModule
 */
public class AttributesBundlesCustomizerPortlet extends GenericPortlet implements ICustomizationModule {

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "cns.customizer.attributesBundles.name";
    /** Customization modules repository attribute name. */
    private static final String ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY = "CustomizationModulesRepository";

    /** Customization modules repository. */
    private ICustomizationModulesRepository repository;
    /** Internationalization customization module metadatas. */
    private final CustomizationModuleMetadatas metadatas;
    /** Customized bundles. */
    private final List<IAttributesBundle> bundles;


    /**
     * Constructor.
     */
    public AttributesBundlesCustomizerPortlet() {
        super();
        this.metadatas = this.generateMetadatas();

        this.bundles = new ArrayList<IAttributesBundle>();
        this.bundles.add(ApplicationsAttributesBundle.getInstance()); // FIXME
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
        metadatas.setCustomizationIDs(Arrays.asList(IAttributesBundle.CUSTOMIZER_ID));
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
    public void customize(CustomizationContext context) {
        Map<String, Object> attributes = context.getAttributes();
        String name = (String) attributes.get(IAttributesBundle.CUSTOMIZER_ATTRIBUTE_NAME);

        for (IAttributesBundle bundle : this.bundles) {
            if (bundle.getAttributeNames().contains(name)) {
                attributes.put(IAttributesBundle.CUSTOMIZER_ATTRIBUTE_RESULT, bundle);
                break;
            }
        }
    }

}
