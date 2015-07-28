package org.osivia.portal.demo.customizer;

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
public class CMSDemoCustomizerPortlet extends CMSCustomizerPortlet implements ICustomizationModule {

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "osivia.demo.customizer.cms.demo.name";


    /** Forum list template. */
    public static final String STYLE_FORUM = "forum";

    /** Picturebook list template. */
    public static final String STYLE_PICTUREBOOK = "picturebook";

    /** Default schemas. */
    public static final String DEFAULT_SCHEMAS = "dublincore, common, toutatice, file";

    /** Picturebook schemas. */
    public static final String SCHEMAS_PICTUREBOOK = "dublincore, common, toutatice, note, files, acaren, webcontainer, file, picture";

    /** Bundle factory. */
    protected IBundleFactory bundleFactory;


    /**
     * Constructor.
     */
    public CMSDemoCustomizerPortlet() {
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
        metadatas.setOrder(1010);
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

        docTypes.put("Forum", new CMSItemType("Forum", true, true, false, false, true, true, Arrays.asList("Thread"), null,
                "glyphicons glyphicons-conversation"));
        // Forum thread
        docTypes.put("Thread", new CMSItemType("Thread", false, false, false, false, true, true, new ArrayList<String>(0), null, "glyphicons glyphicons-chat"));


        Map<String, ListTemplate> templates = getListTemplates(context);


        templates.put(STYLE_FORUM, new ListTemplate(STYLE_FORUM, bundle.getString("LIST_TEMPLATE_FORUM"), DEFAULT_SCHEMAS));
 

        List<IPlayerModule> modules = getPlayers(context);
        // ! insertion au début
        modules.add(0, new PlayerModule(getPortletContext()));


        // ragments
        List<FragmentType> fragmentTypes = getFragmentTypes(context);
        fragmentTypes.add(new FragmentType(TitleFragmentModule.ID, bundle.getString("FRAGMENT_TYPE_TITLE"), new TitleFragmentModule(getPortletContext())));


    }






}
