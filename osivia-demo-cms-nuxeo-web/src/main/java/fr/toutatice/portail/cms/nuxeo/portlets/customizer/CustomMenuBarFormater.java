package fr.toutatice.portail.cms.nuxeo.portlets.customizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletContext;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.ecm.EcmViews;
import org.osivia.portal.api.internationalization.Bundle;
import org.osivia.portal.api.menubar.MenubarDropdown;
import org.osivia.portal.api.menubar.MenubarItem;
import org.osivia.portal.core.cms.CMSException;
import org.osivia.portal.core.cms.CMSExtendedDocumentInfos;
import org.osivia.portal.core.cms.CMSPublicationInfos;
import org.osivia.portal.core.cms.CMSServiceCtx;

import fr.toutatice.portail.cms.nuxeo.portlets.customizer.helpers.ContextualizationHelper;
import fr.toutatice.portail.cms.nuxeo.portlets.customizer.helpers.MenuBarFormater;
import fr.toutatice.portail.cms.nuxeo.portlets.document.helpers.DocumentConstants;
import fr.toutatice.portail.cms.nuxeo.portlets.document.helpers.DocumentHelper;
import fr.toutatice.portail.cms.nuxeo.portlets.service.CMSService;


/**
 * @author David Chevrier.
 *
 */
public class CustomMenuBarFormater extends MenuBarFormater {

    /**
     * Constructor.
     *
     * @param portletContext portlet context
     * @param customizer CMS customizer
     * @param cmsService CMS service
     */
    public CustomMenuBarFormater(PortletContext portletContext, DefaultCMSCustomizer customizer, CMSService cmsService) {
        super(portletContext, customizer, cmsService);
    }
    
    /**
     * Get link to remote publishing tasks.
     * 
     * @param portalControllerContext
     * @param cmsContext
     * @param menubar
     * @param bundle
     * @param extendedInfos
     * @throws CMSException
     */
    @Override
    protected void getRemotePublishingLink(PortalControllerContext portalControllerContext, CMSServiceCtx cmsContext, List<MenubarItem> menubar, Bundle bundle,
            CMSExtendedDocumentInfos extendedInfos) throws CMSException {

        Document document = (Document) cmsContext.getDoc();

        if (!DocumentHelper.isFolder(document)) {

            String path = document.getPath();
            CMSPublicationInfos pubInfos = super.getCmsService().getPublicationInfos(cmsContext, path);

            // DCH: FIXME: state is "ExtendedInfo"...
            if (pubInfos.isRemotePublishable() && pubInfos.isLiveSpace() && ContextualizationHelper.isCurrentDocContextualized(cmsContext)) {
                String url = StringUtils.EMPTY;

                MenubarDropdown parent = this.getCMSEditionDropdown(portalControllerContext, bundle);
                MenubarItem remotePubItem = new MenubarItem("REMOTE_PUBLISHING_URL", bundle.getString("REMOTE_PUBLISHING"), null, parent, 14, url, null, null, "fancyframe_refresh");

                if (DocumentConstants.APPROVED_DOC_STATE.equals(document.getState())) {

                    Boolean isValidationWfRunning = extendedInfos.getIsValidationWorkflowRunning();

                    if (BooleanUtils.isFalse(isValidationWfRunning)) {
                        // We can publish remotly
                        Map<String, String> requestParameters = new HashMap<String, String>();
                        String remotePublishingURL = super.getCmsService().getEcmUrl(cmsContext, EcmViews.remotePublishing, pubInfos.getDocumentPath(),
                                requestParameters);

                        remotePubItem.setUrl(remotePublishingURL);
                    }

                } else {
                    remotePubItem.setDisabled(true);
                }

                menubar.add(remotePubItem);

            }

        }

    }

    
}
