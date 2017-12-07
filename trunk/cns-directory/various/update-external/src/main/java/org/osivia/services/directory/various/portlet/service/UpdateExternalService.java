package org.osivia.services.directory.various.portlet.service;

import javax.portlet.PortletException;

import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.services.directory.various.portlet.model.UpdatedPersons;

/**
 * Update external portlet service interface.
 * 
 * @author Cédric Krommenhoek
 */
public interface UpdateExternalService {

    /**
     * Get updated persons.
     * 
     * @param portalControllerContext portal controller context
     * @return updated persons
     * @throws PortletException
     */
    UpdatedPersons getUpdatedPersons(PortalControllerContext portalControllerContext) throws PortletException;


    /**
     * Update persons.
     * 
     * @param portalControllerContext portal controller context
     * @param updatedPersons updated persons
     * @throws PortletException
     */
    void update(PortalControllerContext portalControllerContext, UpdatedPersons updatedPersons) throws PortletException;

}
