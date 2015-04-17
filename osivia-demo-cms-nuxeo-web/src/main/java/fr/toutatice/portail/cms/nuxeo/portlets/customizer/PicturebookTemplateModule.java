package fr.toutatice.portail.cms.nuxeo.portlets.customizer;

import java.util.ArrayList;
import java.util.List;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.osivia.portal.api.Constants;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.internationalization.Bundle;
import org.osivia.portal.api.internationalization.IBundleFactory;
import org.osivia.portal.api.internationalization.IInternationalizationService;
import org.osivia.portal.api.locator.Locator;
import org.osivia.portal.api.notifications.INotificationsService;
import org.osivia.portal.api.notifications.Notifications;
import org.osivia.portal.api.notifications.NotificationsType;
import org.osivia.portal.api.windows.PortalWindow;
import org.osivia.portal.core.cms.CMSException;
import org.osivia.portal.core.cms.CMSPublicationInfos;
import org.osivia.portal.core.cms.CMSServiceCtx;
import org.osivia.portal.core.cms.ICMSService;

import fr.toutatice.portail.cms.nuxeo.api.INuxeoCommand;
import fr.toutatice.portail.cms.nuxeo.api.NuxeoController;
import fr.toutatice.portail.cms.nuxeo.api.domain.ITemplateModule;
import fr.toutatice.portail.cms.nuxeo.portlets.files.UploadFilesCommand;

/**
 * Picturebook template module.
 *
 * @author Cédric Krommenhoek
 * @see ITemplateModule
 */
public class PicturebookTemplateModule implements ITemplateModule {

    /** File upload notifications duration. */
    private static final int FILE_UPLOAD_NOTIFICATIONS_DURATION = 1000;

    /** Bundle factory. */
    private final IBundleFactory bundleFactory;
    /** Notifications service. */
    private final INotificationsService notificationsService;


    /**
     * Constructor.
     */
    public PicturebookTemplateModule() {
        super();

        // Bundle factory
        IInternationalizationService internationalizationService = Locator.findMBean(IInternationalizationService.class,
                IInternationalizationService.MBEAN_NAME);
        this.bundleFactory = internationalizationService.getBundleFactory(this.getClass().getClassLoader());

        // Notification service
        this.notificationsService = Locator.findMBean(INotificationsService.class, INotificationsService.MBEAN_NAME);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void doView(PortalControllerContext portalControllerContext, PortalWindow window, RenderRequest request, RenderResponse response)
            throws PortletException {
        // Nuxeo controller
        NuxeoController nuxeoController = new NuxeoController(request, response, portalControllerContext.getPortletCtx());
        // Path
        String path = window.getProperty(Constants.WINDOW_PROP_URI);

        if (StringUtils.isNotEmpty(path)) {
            try {
                // CMS service
                ICMSService cmsService = NuxeoController.getCMSService();
                // CMS context
                CMSServiceCtx cmsContext = nuxeoController.getCMSCtx();

                // Computed path
                path = nuxeoController.getComputedPath(path);

                // Publication informations
                CMSPublicationInfos publicationInfos = cmsService.getPublicationInfos(cmsContext, path);
                boolean editable = publicationInfos.isEditableByUser();
                request.setAttribute("editable", editable);
                request.setAttribute("parentId", publicationInfos.getLiveId());
            } catch (CMSException e) {
                throw new PortletException(e);
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void processAction(PortalControllerContext portalControllerContext, PortalWindow window, ActionRequest request, ActionResponse response)
            throws PortletException {
        // Action name
        String action = request.getParameter(ActionRequest.ACTION_NAME);

        // Nuxeo controller
        NuxeoController nuxeoController = new NuxeoController(request, response, portalControllerContext.getPortletCtx());
        // Bundle
        Bundle bundle = this.bundleFactory.getBundle(request.getLocale());

        if (PortletMode.VIEW.equals(request.getPortletMode())) {
            // View

            if ("fileUpload".equals(action)) {
                // File upload

                String parentId = request.getParameter("parentId");

                // Notification
                Notifications notifications;

                try {
                    DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
                    PortletFileUpload fileUpload = new PortletFileUpload(fileItemFactory);
                    List<FileItem> fileItems = fileUpload.parseRequest(request);

                    // Accepted files
                    List<FileItem> acceptedFileItems = new ArrayList<FileItem>(fileItems.size());
                    for (FileItem fileItem : fileItems) {
                        boolean accepted;
                        try {
                            MimeType mimeType = new MimeType(fileItem.getContentType());
                            String primaryType = mimeType.getPrimaryType();
                            accepted = "image".equals(primaryType);
                        } catch (MimeTypeParseException e) {
                            accepted = false;
                        }

                        if (accepted) {
                            acceptedFileItems.add(fileItem);
                        } else {
                            Notifications rejectedFileNotifications = new Notifications(NotificationsType.WARNING, FILE_UPLOAD_NOTIFICATIONS_DURATION);
                            rejectedFileNotifications.addMessage(bundle.getString("MESSAGE_FILE_UPLOAD_REJECTED_FILE", fileItem.getName()));
                            this.notificationsService.addNotifications(portalControllerContext, rejectedFileNotifications);
                        }
                    }

                    if (!acceptedFileItems.isEmpty()) {
                        // Nuxeo command
                        INuxeoCommand command = new UploadFilesCommand(parentId, acceptedFileItems);
                        nuxeoController.executeNuxeoCommand(command);

                        // Refresh navigation
                        request.setAttribute(Constants.PORTLET_ATTR_UPDATE_CONTENTS, Constants.PORTLET_VALUE_ACTIVATE);

                        // Notification
                        notifications = new Notifications(NotificationsType.SUCCESS, FILE_UPLOAD_NOTIFICATIONS_DURATION);
                        notifications.addMessage(bundle.getString("MESSAGE_FILE_UPLOAD_SUCCESS"));
                    } else {
                        notifications = null;
                    }
                } catch (FileUploadException e) {
                    // Notification
                    notifications = new Notifications(NotificationsType.ERROR, FILE_UPLOAD_NOTIFICATIONS_DURATION);
                    notifications.addMessage(bundle.getString("MESSAGE_FILE_UPLOAD_ERROR"));
                }

                this.notificationsService.addNotifications(portalControllerContext, notifications);
            }
        }
    }

}
