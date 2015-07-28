package org.osivia.portal.demo.customizer;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletContext;

import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.Constants;
import org.osivia.portal.core.cms.CMSException;
import org.osivia.portal.core.cms.CMSHandlerProperties;
import org.osivia.portal.core.cms.CMSPublicationInfos;
import org.osivia.portal.core.cms.CMSServiceCtx;
import org.osivia.portal.core.cms.ICMSService;



import fr.toutatice.portail.cms.nuxeo.api.domain.CMSCustomizerModule;
import fr.toutatice.portail.cms.nuxeo.api.domain.IPlayerModule;



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
     * Utility method used to create forum player request.
     *
     * @param cmsContext CMS context
     * @param cmsService the cms service
     * @return request
     * @throws CMSException the CMS exception
     */
    private String createForumPlayerRequest(CMSServiceCtx cmsContext, ICMSService cmsService) throws CMSException {
        // Document
        Document document = (Document) cmsContext.getDoc();
        // Publication infos
        CMSPublicationInfos pubInfos = cmsService.getPublicationInfos(cmsContext, document.getPath());

        StringBuilder request = new StringBuilder();
        request.append("ecm:parentId = '").append(pubInfos.getLiveId()).append("' ");
        request.append("AND ecm:primaryType = 'Thread' ");
        request.append("ORDER BY dc:modified DESC ");
        return request.toString();
    }
    
    
    /**
     * Get forum player.
     *
     * @param ctx CMS context
     * @param cmsService the cms service
     * @return CMS forum player
     * @throws CMSException the CMS exception
     */
    private CMSHandlerProperties getForumPlayer(CMSServiceCtx ctx, ICMSService cmsService) throws CMSException {
        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put("osivia.nuxeoRequest", this.createForumPlayerRequest(ctx, cmsService));
        windowProperties.put("osivia.cms.style", CMSDemoCustomizerPortlet.STYLE_FORUM);
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("theme.dyna.partial_refresh_enabled", "false");
        windowProperties.put(Constants.WINDOW_PROP_SCOPE, ctx.getScope());
        windowProperties.put("osivia.ajaxLink", "1");
        windowProperties.put(Constants.WINDOW_PROP_VERSION, ctx.getDisplayLiveVersion());


        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-portail-cms-nuxeo-viewListPortletInstance");

        return linkProps;
    }

    

    /**
     * Get forum thread player.
     *
     * @param cmsContext CMS context
     * @return forum thread player
     */
    private CMSHandlerProperties getForumThreadPlayer(CMSServiceCtx cmsContext) {
        Document document = (Document) cmsContext.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put(Constants.WINDOW_PROP_URI, document.getPath());
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("osivia.ajaxLink", "1");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("osivia-services-forum-portletInstance");

        return linkProps;
    }
    
    /* (non-Javadoc)
     * @see fr.toutatice.portail.cms.nuxeo.api.domain.IPlayerModule#getCMSPlayer(org.osivia.portal.core.cms.CMSServiceCtx, org.osivia.portal.core.cms.ICMSService)
     */
    @Override
    public CMSHandlerProperties getCMSPlayer(CMSServiceCtx ctx, ICMSService cmsService)  throws CMSException {
        Document doc = (Document) ctx.getDoc();

        if ("Forum".equals(doc.getType())) {
            return this.getForumPlayer(ctx, cmsService);
        }
 
        if ("Thread".equals(doc.getType())) {
            return this.getForumThreadPlayer(ctx);
        }        
        
        return null;
    }

}
