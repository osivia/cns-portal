package org.osivia.portal.demo.picture.customizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;

import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.internationalization.Bundle;
import org.osivia.portal.api.internationalization.IBundleFactory;
import org.osivia.portal.api.internationalization.IInternationalizationService;
import org.osivia.portal.api.locator.Locator;
import org.osivia.portal.core.cms.CMSItemType;

import fr.toutatice.portail.cms.nuxeo.api.domain.CMSCustomizerPortlet;
import fr.toutatice.portail.cms.nuxeo.api.domain.FragmentType;
import fr.toutatice.portail.cms.nuxeo.api.domain.IPlayerModule;
import fr.toutatice.portail.cms.nuxeo.api.domain.ITemplateModule;
import fr.toutatice.portail.cms.nuxeo.api.domain.ListTemplate;


/**
 * Technical portlet for attributes bundles customization.
 * 
 * @author Jean-Sébastien steux
 * @see GenericPortlet
 * @see ICustomizationModule
 */
public class CMSPictureCustomizerPortlet extends CMSCustomizerPortlet implements ICustomizationModule {

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "osivia.demo.customizer.cms.picture.name";


    /** Picturebook list template. */
    public static final String STYLE_PICTUREBOOK = "picturebook";

    /** Picturebook schemas. */
    public static final String SCHEMAS_PICTUREBOOK = "dublincore, common, toutatice, note, files, acaren, webcontainer, file, picture";

    /** Bundle factory. */
    protected IBundleFactory bundleFactory;


    /**
     * Constructor.
     */
    public CMSPictureCustomizerPortlet() {
        super();

    }


    @Override
    public void init() throws PortletException {
        super.init();

        // Bundle factory
        IInternationalizationService internationalizationService = Locator.findMBean(IInternationalizationService.class,
                IInternationalizationService.MBEAN_NAME);
        this.bundleFactory = internationalizationService.getBundleFactory(this.getClass().getClassLoader());
    }

    /**
     * Utility method used to generate attributes bundles customization module metadatas.
     * 
     * @return metadatas
     */
    protected CustomizationModuleMetadatas generateMetadatas() {
        CustomizationModuleMetadatas metadatas = new CustomizationModuleMetadatas();
        metadatas.setName(CUSTOMIZER_NAME);
        metadatas.setModule(this);
        metadatas.setCustomizationIDs(Arrays.asList("osivia.customizer.cms.id"));
        metadatas.setOrder(100);
        return metadatas;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeCMSProperties(String customizationID, CustomizationContext context) {

        // save current class loader


        // Bundle
        Bundle bundle = this.bundleFactory.getBundle(context.getLocale());


        Map<String, CMSItemType> docTypes = getDocTypes(context);
        // Picture book
        docTypes.put("PictureBook", new CMSItemType("PictureBook", true, false, true, true, false, true, Arrays.asList("Picture", "PictureBook"), null,
                "glyphicons glyphicons-picture"));
        // Picture
        docTypes.put("Picture",new CMSItemType("Picture", false, false, false, false, false, true, new ArrayList<String>(0), null, "glyphicons glyphicons-picture"));


        Map<String, ListTemplate> templates = getListTemplates(context);

        ListTemplate picturebookTemplate = new ListTemplate(STYLE_PICTUREBOOK, bundle.getString("LIST_TEMPLATE_PICTUREBOOK"), SCHEMAS_PICTUREBOOK);
        ITemplateModule picturebookModule = new PicturebookTemplateModule( getPortletContext());
        picturebookTemplate.setModule(picturebookModule);
        templates.put(STYLE_PICTUREBOOK, picturebookTemplate);


        List<IPlayerModule> modules = getPlayers(context);
        // ! insertion au début
        modules.add(0, new PlayerModule(getPortletContext()));


 

    }






}
