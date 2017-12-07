package org.osivia.services.directory.various.portlet.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;
import javax.portlet.PortletException;

import org.apache.commons.collections.CollectionUtils;
import org.osivia.directory.v2.model.CollabProfile;
import org.osivia.directory.v2.model.ext.WorkspaceGroupType;
import org.osivia.directory.v2.service.PersonUpdateService;
import org.osivia.directory.v2.service.WorkspaceService;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.directory.v2.model.Person;
import org.osivia.services.directory.various.portlet.model.UpdatedPersons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Update external portlet service implementation.
 * 
 * @author Cédric Krommenhoek
 * @see UpdateExternalService
 */
@Service
public class UpdateExternalServiceImpl implements UpdateExternalService {

    /** Transversal workspace identifier. */
    private static final String TRANSVERSAL_WORKSPACE_ID = "Transverse";


    /** Application context. */
    @Autowired
    private ApplicationContext applicationContext;

    /** Person service. */
    @Autowired
    private PersonUpdateService personService;

    /** Workspace service. */
    @Autowired
    private WorkspaceService workspaceService;


    /**
     * Constructor.
     */
    public UpdateExternalServiceImpl() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public UpdatedPersons getUpdatedPersons(PortalControllerContext portalControllerContext) throws PortletException {
        return this.applicationContext.getBean(UpdatedPersons.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void update(PortalControllerContext portalControllerContext, UpdatedPersons updatedPersons) throws PortletException {
        

        // Transversal profile
        CollabProfile profileCriteria = this.workspaceService.getEmptyProfile();
        profileCriteria.setWorkspaceId(TRANSVERSAL_WORKSPACE_ID);
        profileCriteria.setType(WorkspaceGroupType.space_group);
        List<CollabProfile> profiles = this.workspaceService.findByCriteria(profileCriteria);
        if (CollectionUtils.isEmpty(profiles) || (profiles.size() > 1)) {
            throw new PortletException("Unable to find space group for transversal workspace (" + TRANSVERSAL_WORKSPACE_ID + ")");
        }
        CollabProfile profile = profiles.get(0);

        // Transversal profile name
        Name name = profile.getDn();
        
        // Person search criteria
        Person criteria = this.personService.getEmptyPerson();

        // Persons
        List<Person> persons = this.personService.findByCriteria(criteria);
        
        // Updated persons list
        List<Person> list;

        if (CollectionUtils.isEmpty(persons)) {
            list = null;
        } else {
            list = new ArrayList<>(persons.size());

            for (Person person : persons) {
                if (person.getExternal() == null) {
                    boolean external = CollectionUtils.isNotEmpty(person.getProfiles()) && (person.getProfiles().contains(name));
                    person.setExternal(external);
                    this.personService.update(person);
                    list.add(person);
                }
            }
        }

        // Update model
        updatedPersons.setList(list);
        updatedPersons.setStarted(true);
    }

}
