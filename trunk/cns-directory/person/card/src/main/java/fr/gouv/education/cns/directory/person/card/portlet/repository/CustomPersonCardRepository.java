package fr.gouv.education.cns.directory.person.card.portlet.repository;

import java.util.Set;

import javax.portlet.PortletException;

import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.services.person.card.portlet.repository.PersonCardRepository;

import fr.gouv.education.cns.directory.person.util.portlet.model.PersonEntity;

/**
 * Customized person card portlet repository.
 * 
 * @author Cédric Krommenhoek
 * @see PersonCardRepository
 */
public interface CustomPersonCardRepository extends PersonCardRepository {

    /**
     * Get entities.
     * 
     * @param portalControllerContext portal controller context
     * @return entities
     * @throws PortletException
     */
    Set<PersonEntity> getEntities(PortalControllerContext portalControllerContext) throws PortletException;

}
