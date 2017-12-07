package fr.gouv.education.cns.directory.person.card.portlet.service;

import java.util.Set;

import javax.portlet.PortletException;

import org.apache.commons.lang.StringUtils;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.directory.v2.model.Person;
import org.osivia.services.person.card.portlet.model.PersonEditionForm;
import org.osivia.services.person.card.portlet.model.PersonNuxeoProfile;
import org.osivia.services.person.card.portlet.service.PersonCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.gouv.education.cns.directory.person.card.portlet.model.CustomPersonEditionForm;
import fr.gouv.education.cns.directory.person.card.portlet.model.CustomPersonNuxeoProfile;
import fr.gouv.education.cns.directory.person.card.portlet.repository.CustomPersonCardRepository;
import fr.gouv.education.cns.directory.person.util.portlet.model.PersonEntity;
import fr.gouv.education.cns.directory.v2.model.CnsPerson;

/**
 * Customized person card portlet service implementation.
 * 
 * @author Cédric Krommenhoek
 * @see PersonCardServiceImpl
 */
@Service
@Primary
public class CustomPersonCardServiceImpl extends PersonCardServiceImpl {

    /** Portlet repository. */
    @Autowired
    private CustomPersonCardRepository repository;


    /**
     * Constructor.
     */
    public CustomPersonCardServiceImpl() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillLdapProperties(PortalControllerContext portalControllerContext, PersonEditionForm form, Person person) throws PortletException {
        super.fillLdapProperties(portalControllerContext, form, person);

        if ((form != null) && (form instanceof CustomPersonEditionForm) && (person != null) && (person instanceof CnsPerson)) {
            // Customized edition form
            CustomPersonEditionForm customForm = (CustomPersonEditionForm) form;
            // CNS person
            CnsPerson cnsPerson = (CnsPerson) person;

            customForm.setEntity(cnsPerson.getEntity());

            // Entities
            Set<PersonEntity> entities = this.repository.getEntities(portalControllerContext);
            customForm.setEntities(entities);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillNuxeoProperties(PortalControllerContext portalControllerContext, PersonEditionForm form, PersonNuxeoProfile nuxeoProfile)
            throws PortletException {
        super.fillNuxeoProperties(portalControllerContext, form, nuxeoProfile);

        if ((form != null) && (form instanceof CustomPersonEditionForm) && (nuxeoProfile != null) && (nuxeoProfile instanceof CustomPersonNuxeoProfile)) {
            // Customized edition form
            CustomPersonEditionForm customForm = (CustomPersonEditionForm) form;
            // Customized Nuxeo profile
            CustomPersonNuxeoProfile customNuxeoProfile = (CustomPersonNuxeoProfile) nuxeoProfile;

            customForm.setAdministrativeEntity(customNuxeoProfile.getAdministrativeEntity());
            customForm.setGenericMail(customNuxeoProfile.getGenericMail());
            customForm.setReferer(customNuxeoProfile.getReferer());
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void setLdapProperties(PortalControllerContext portalControllerContext, PersonEditionForm form, Person person) throws PortletException {
        super.setLdapProperties(portalControllerContext, form, person);

        if ((form != null) && (form instanceof CustomPersonEditionForm) && (person != null) && (person instanceof CnsPerson)) {
            // Customized edition form
            CustomPersonEditionForm customForm = (CustomPersonEditionForm) form;
            // CNS person
            CnsPerson cnsPerson = (CnsPerson) person;

            cnsPerson.setEntity(StringUtils.trimToNull(customForm.getEntity()));
        }
    }

}
