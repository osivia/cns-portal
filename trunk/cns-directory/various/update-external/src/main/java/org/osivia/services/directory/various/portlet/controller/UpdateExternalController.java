package org.osivia.services.directory.various.portlet.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.services.directory.various.portlet.model.UpdatedPersons;
import org.osivia.services.directory.various.portlet.service.UpdateExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Update external portlet controller.
 * 
 * @author Cédric Krommenhoek
 */
@Controller
@RequestMapping("VIEW")
public class UpdateExternalController {

    /** Portlet context. */
    @Autowired
    private PortletContext portletContext;

    /** Portlet service. */
    @Autowired
    private UpdateExternalService service;


    /**
     * Constructor.
     */
    public UpdateExternalController() {
        super();
    }


    /**
     * View render mapping.
     * 
     * @param request render request
     * @param response render response
     * @return view path
     */
    @RenderMapping
    public String view(RenderRequest request, RenderResponse response) {
        return "view";
    }


    /**
     * Start update.
     * 
     * @param request action request
     * @param response action response
     * @param updatedPersons updated persons
     * @throws PortletException
     */
    @ActionMapping("start")
    public void start(ActionRequest request, ActionResponse response, @ModelAttribute("updatedPersons") UpdatedPersons updatedPersons) throws PortletException {
        // Portal controller context
        PortalControllerContext portalControllerContext = new PortalControllerContext(this.portletContext, request, response);

        this.service.update(portalControllerContext, updatedPersons);
    }


    /**
     * Get updated persons model attribute.
     * 
     * @param request portlet request
     * @param response portlet response
     * @return updated persons
     * @throws PortletException
     */
    @ModelAttribute("updatedPersons")
    public UpdatedPersons getUpdatedPersons(PortletRequest request, PortletResponse response) throws PortletException {
        // Portal controller context
        PortalControllerContext portalControllerContext = new PortalControllerContext(this.portletContext, request, response);
        
        return this.service.getUpdatedPersons(portalControllerContext);
    }

}
