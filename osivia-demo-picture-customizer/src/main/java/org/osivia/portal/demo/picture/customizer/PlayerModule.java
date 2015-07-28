package org.osivia.portal.demo.picture.customizer;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletContext;

import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.core.cms.CMSException;
import org.osivia.portal.core.cms.CMSHandlerProperties;
import org.osivia.portal.core.cms.CMSPublicationInfos;
import org.osivia.portal.core.cms.CMSServiceCtx;
import org.osivia.portal.core.cms.ICMSService;
import org.osivia.portal.core.constants.FileBrowserView;
import org.osivia.portal.core.constants.InternalConstants;

import fr.toutatice.portail.cms.nuxeo.api.domain.CMSCustomizerModule;
import fr.toutatice.portail.cms.nuxeo.api.domain.IPlayerModule;
import fr.toutatice.portail.cms.nuxeo.api.services.INuxeoCustomizer;



/**
 * The Class PlayerModule.
 *
 * @author Jean-Sébastien Steux
 */
public class PlayerModule extends CMSCustomizerModule implements IPlayerModule {
    
    
    /**
     * Instantiates a new player module.
     *
     * @param portletContext the portlet context
     */
    public PlayerModule(PortletContext portletContext) {
        super(portletContext);

    }
    
    /**
     * Get PictureBook player.
     *
     * @param ctx
     * @return PictureBook player
     * @throws Exception
     */
    /**
     * Get picturebook player properties.
     *
     * @param cmsContext CMS context
     * @return player properties
     * @throws CMSException 
     */
    public CMSHandlerProperties getCMSPictureBookPlayer(CMSServiceCtx cmsContext) throws CMSException  {
        Document document = (Document) cmsContext.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();

        windowProperties.put("osivia.title", document.getTitle());
        windowProperties.put("osivia.cms.scope", cmsContext.getScope());
        windowProperties.put("osivia.cms.uri", document.getPath());
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("osivia.ajaxLink", "1");
        windowProperties.put("osivia.cms.displayLiveVersion", cmsContext.getDisplayLiveVersion());
        windowProperties.put("osivia.cms.style", CMSPictureCustomizerPortlet.STYLE_PICTUREBOOK);
        windowProperties.put("osivia.nuxeoRequest", getNuxeoCustomizer().createFolderRequest(cmsContext, false));
        windowProperties.put("osivia.cms.pageSize", "24");
        windowProperties.put("osivia.cms.pageSizeMax", "96");
        windowProperties.put("osivia.cms.maxItems", "96");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-portail-cms-nuxeo-viewListPortletInstance");

        return linkProps;
    }

    
    /* (non-Javadoc)
     * @see fr.toutatice.portail.cms.nuxeo.api.domain.IPlayerModule#getCMSPlayer(org.osivia.portal.core.cms.CMSServiceCtx, org.osivia.portal.core.cms.ICMSService)
     */
    @Override
    public CMSHandlerProperties getCMSPlayer(CMSServiceCtx cmsContext, ICMSService cmsService)  throws CMSException {

        // Document
        Document document = (Document) cmsContext.getDoc();
        // Publication infos
        CMSPublicationInfos pubInfos = cmsService.getPublicationInfos(cmsContext, document.getPath());

        // Workspace indicator
        boolean workspace = (cmsContext.getContextualizationBasePath() != null) && (pubInfos.isLiveSpace());


        if ("PictureBook".equals(document.getType())) {
            if (workspace) {
                // File browser
                cmsContext.setDisplayLiveVersion("1");
                CMSHandlerProperties properties = getNuxeoCustomizer().getCMSFileBrowser(cmsContext);
                Map<String, String> windowProperties = properties.getWindowProperties();
                windowProperties.put(InternalConstants.PROP_WINDOW_TITLE, document.getTitle());
                windowProperties.put(InternalConstants.DEFAULT_VIEW_WINDOW_PROPERTY, FileBrowserView.THUMBNAILS.getName());
                return properties;
            } else {
                return getCMSPictureBookPlayer(cmsContext);
            }
        }
        return null;
    }

}
