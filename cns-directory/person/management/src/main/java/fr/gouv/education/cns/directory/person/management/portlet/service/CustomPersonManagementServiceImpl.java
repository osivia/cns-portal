package fr.gouv.education.cns.directory.person.management.portlet.service;

import java.util.Map;
import java.util.Set;

import javax.portlet.PortletException;

import org.apache.commons.collections.MapUtils;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.services.person.management.portlet.model.PersonManagementForm;
import org.osivia.services.person.management.portlet.service.PersonManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.gouv.education.cns.directory.person.management.portlet.model.CustomPersonManagementForm;
import fr.gouv.education.cns.directory.person.management.portlet.repository.CustomPersonManagementRepository;
import fr.gouv.education.cns.directory.person.util.portlet.model.PersonEntity;

/**
 * Customized person management portlet service implementation.
 * 
 * @author Cédric Krommenhoek
 * @see PersonManagementServiceImpl
 */
@Service
@Primary
public class CustomPersonManagementServiceImpl extends PersonManagementServiceImpl {

    /** Application context. */
    @Autowired
    private ApplicationContext applicationContext;

    /** Portlet repository. */
    @Autowired
    private CustomPersonManagementRepository repository;


    /**
     * Constructor.
     */
    public CustomPersonManagementServiceImpl() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public PersonManagementForm getForm(PortalControllerContext portalControllerContext) throws PortletException {
        // Form
        CustomPersonManagementForm form = this.applicationContext.getBean(CustomPersonManagementForm.class);

        // Entities
        Set<PersonEntity> entities = this.repository.getEntities(portalControllerContext);
        form.setEntities(entities);

        return form;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void search(PortalControllerContext portalControllerContext, PersonManagementForm form, String filters) throws PortletException {
        // Filters map
        Map<String, String> map = this.getFiltersMap(portalControllerContext, filters);

        // Update form
        String filter;
        String entity;
        if (MapUtils.isEmpty(map)) {
            filter = null;
            entity = null;
        } else {
            filter = map.get("filter");
            entity = map.get("entity");
        }
        form.setFilter(filter);
        if (form instanceof CustomPersonManagementForm) {
            CustomPersonManagementForm customForm = (CustomPersonManagementForm) form;
            customForm.setEntity(entity);
        }

        this.search(portalControllerContext, form);
    }

}
