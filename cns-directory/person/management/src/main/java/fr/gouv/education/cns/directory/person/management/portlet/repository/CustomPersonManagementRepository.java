package fr.gouv.education.cns.directory.person.management.portlet.repository;

import java.util.Set;

import javax.portlet.PortletException;

import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.services.person.management.portlet.repository.PersonManagementRepository;

import fr.gouv.education.cns.directory.person.util.portlet.model.PersonEntity;

/**
 * Customized person management portlet repository interface.
 * 
 * @author Cédric Krommenhoek
 * @see PersonManagementRepository
 */
public interface CustomPersonManagementRepository extends PersonManagementRepository {

    /**
     * Get entities.
     * 
     * @param portalControllerContext portal controller context
     * @return entities
     * @throws PortletException
     */
    Set<PersonEntity> getEntities(PortalControllerContext portalControllerContext) throws PortletException;

}
