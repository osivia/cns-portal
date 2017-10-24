package fr.gouv.education.cns.directory.person.management.portlet.model;

import java.util.Set;

import org.osivia.services.person.management.portlet.model.PersonManagementForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.gouv.education.cns.directory.person.util.portlet.model.PersonEntity;

/**
 * Customized search form java-bean.
 * 
 * @author Cédric Krommenhoek
 */
@Component
@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomPersonManagementForm extends PersonManagementForm {

    /** Entity. */
    private String entity;

    /** Entities. */
    private Set<PersonEntity> entities;


    /**
     * Constructor.
     */
    public CustomPersonManagementForm() {
        super();
    }


    /**
     * Getter for entity.
     * 
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Setter for entity.
     * 
     * @param entity the entity to set
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * Getter for entities.
     * 
     * @return the entities
     */
    public Set<PersonEntity> getEntities() {
        return entities;
    }

    /**
     * Setter for entities.
     * 
     * @param entities the entities to set
     */
    public void setEntities(Set<PersonEntity> entities) {
        this.entities = entities;
    }

}
