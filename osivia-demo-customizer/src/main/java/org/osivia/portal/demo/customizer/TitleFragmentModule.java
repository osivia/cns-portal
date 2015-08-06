/**
 *
 */
package org.osivia.portal.demo.customizer;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.windows.PortalWindow;
import org.osivia.portal.api.windows.WindowFactory;

import fr.toutatice.portail.cms.nuxeo.api.NuxeoController;
import fr.toutatice.portail.cms.nuxeo.api.domain.PluginModule;
import fr.toutatice.portail.cms.nuxeo.api.domain.IFragmentModule;

/**
 * Title fragment module.
 * 
 * @see IFragmentModule
 */
public class TitleFragmentModule extends PluginModule implements IFragmentModule {

    /** Link fragment identifier. */
    public static final String ID = "title";

    /** Link target path window property name. */
    public static final String PATH_WINDOW_PROPERTY = "osivia.docPath";


    /** JSP name. */
    private static final String JSP_NAME = "title";



    /**
     * Private constructor.
     */
    public TitleFragmentModule(PortletContext portletContext) {
        super(portletContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doView(PortalControllerContext portalControllerContext) throws PortletException {

        
        // Request
        PortletRequest request = portalControllerContext.getRequest();
        // Response
        PortletResponse response = portalControllerContext.getResponse();
        // Nuxeo controller
        NuxeoController nuxeoController = new NuxeoController(request, response, portalControllerContext.getPortletCtx());

        // Current window
        PortalWindow window = WindowFactory.getWindow(request);

        // Link target path
        String targetPath = window.getProperty(PATH_WINDOW_PROPERTY);


        if (StringUtils.isNotEmpty(targetPath)) {
            // Computed path
            targetPath = nuxeoController.getComputedPath(targetPath);

            // Fetch Nuxeo document
            Document document = nuxeoController.fetchDocument(targetPath);

            // Update title
            String title = document.getTitle();

            request.setAttribute("title", title);

        } else {
            request.setAttribute("messageKey", "MESSAGE_PATH_UNDEFINED");
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void doAdmin(PortalControllerContext portalControllerContext) throws PortletException {
        // Request
        PortletRequest request = portalControllerContext.getRequest();

        // Current window
        PortalWindow window = WindowFactory.getWindow(request);

        // Link name
        String path = window.getProperty(PATH_WINDOW_PROPERTY);
        request.setAttribute("path", path);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processAdminAction(PortalControllerContext portalControllerContext) throws PortletException {
        // Request
        PortletRequest request = portalControllerContext.getRequest();

        // Current window
        PortalWindow window = WindowFactory.getWindow(request);

        // Link name
        window.setProperty(PATH_WINDOW_PROPERTY, StringUtils.trimToNull(request.getParameter("path")));

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayedInAdmin() {
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getViewJSPName() {
        return JSP_NAME;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdminJSPName() {
        return JSP_NAME;
    }

}
