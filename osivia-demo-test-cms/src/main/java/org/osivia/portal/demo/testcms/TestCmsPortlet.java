package org.osivia.portal.demo.testcms;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.windows.PortalWindow;
import org.osivia.portal.api.windows.WindowFactory;

import fr.toutatice.portail.cms.nuxeo.api.CMSPortlet;
import fr.toutatice.portail.cms.nuxeo.api.NuxeoController;
import fr.toutatice.portail.cms.nuxeo.api.NuxeoException;
import fr.toutatice.portail.cms.nuxeo.api.PortletErrorHandler;

/**
 * Test CMS portlet.
 * 
 * @author Jean-Sébastien Steux
 * @see CMSPortlet
 */
public class TestCmsPortlet extends CMSPortlet {

    /** Logger. */
    private static Log logger = LogFactory.getLog(TestCmsPortlet.class);

    /** View JSP path. */
    private static final String VIEW_JSP = "/WEB-INF/jsp/view.jsp";
    /** Admin JSP path. */
    private static final String ADMIN_JSP = "/WEB-INF/jsp/admin.jsp";


    /**
     * Default constructor.
     */
    public TestCmsPortlet() {
        super();
    }


    /**
     * Admin view display.
     * 
     * @param req request
     * @param res response
     * @throws PortletException
     * @throws IOException
     */
    @RenderMode(name = "admin")
    public void doAdmin(RenderRequest req, RenderResponse res) throws IOException, PortletException {
        NuxeoController nuxeoCtrl = new NuxeoController(req, res, this.getPortletContext());

        res.setContentType("text/html");
        PortletRequestDispatcher rd = null;

        PortalWindow window = WindowFactory.getWindow(req);

        String nuxeoPath = window.getProperty("osivia.cms.uri");
        if (nuxeoPath == null) {
            nuxeoPath = "";
        }
        req.setAttribute("nuxeoPath", nuxeoPath);

        String displayLiveVersion = window.getProperty("osivia.cms.displayLiveVersion");
        req.setAttribute("displayLiveVersion", displayLiveVersion);

        String scope = window.getProperty("osivia.cms.scope");
        req.setAttribute("scope", scope);

        req.setAttribute("nuxeoCtrl", nuxeoCtrl);

        rd = this.getPortletContext().getRequestDispatcher(ADMIN_JSP);
        rd.include(req, res);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void processAction(ActionRequest req, ActionResponse res) throws IOException, PortletException {
        logger.debug("processAction ");

        if ("admin".equals(req.getPortletMode().toString()) && req.getParameter("modifierPrefs") != null) {
            PortalWindow window = WindowFactory.getWindow(req);

            window.setProperty("osivia.cms.uri", req.getParameter("nuxeoPath"));

            if (req.getParameter("scope") != null && req.getParameter("scope").length() > 0) {
                window.setProperty("osivia.cms.scope", req.getParameter("scope"));
            } else if (window.getProperty("osivia.cms.scope") != null) {
                window.setProperty("osivia.cms.scope", null);
            }


            if (req.getParameter("displayLiveVersion") != null && req.getParameter("displayLiveVersion").length() > 0) {
                window.setProperty("osivia.cms.displayLiveVersion", req.getParameter("displayLiveVersion"));
            } else if (window.getProperty("osivia.cms.displayLiveVersion") != null) {
                window.setProperty("osivia.cms.displayLiveVersion", null);
            }

            res.setPortletMode(PortletMode.VIEW);
            res.setWindowState(WindowState.NORMAL);
        }

        if ("admin".equals(req.getPortletMode().toString()) && req.getParameter("annuler") != null) {

            res.setPortletMode(PortletMode.VIEW);
            res.setWindowState(WindowState.NORMAL);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        try {
            response.setContentType("text/html");

            NuxeoController nuxeoCtrl = new NuxeoController(request, response, this.getPortletContext());


            PortalWindow window = WindowFactory.getWindow(request);


            String nuxeoPath = window.getProperty("osivia.cms.uri");


            if (nuxeoPath != null) {

                Document doc = nuxeoCtrl.fetchDocument(nuxeoPath);


                nuxeoCtrl.setCurrentDoc(doc);

                String note = doc.getProperties().getString("note:note");

                if (note != null) {
                    note = nuxeoCtrl.transformHTMLContent(note);
                }

                request.setAttribute("note", note);

                PortletRequestDispatcher dispatcher = this.getPortletContext().getRequestDispatcher(VIEW_JSP);
                dispatcher.include(request, response);

                // Standard menu bar items
                nuxeoCtrl.insertContentMenuBarItems();


            } else {
                response.setContentType("text/html");
                response.getWriter().print("<h2>Document non défini</h2>");
                response.getWriter().close();
                return;
            }

        } catch (NuxeoException e) {
            PortletErrorHandler.handleGenericErrors(response, e);
        } catch (Exception e) {
            if (!(e instanceof PortletException)) {
                throw new PortletException(e);
            }
        }
    }


}
