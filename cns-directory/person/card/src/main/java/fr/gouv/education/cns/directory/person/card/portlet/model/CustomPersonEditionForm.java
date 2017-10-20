package fr.gouv.education.cns.directory.person.card.portlet.model;

import java.util.Set;

import org.osivia.services.person.card.portlet.model.PersonEditionForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Customized person card edition form java-bean.
 * 
 * @author Cédric Krommenhoek
 * @see PersonEditionForm
 */
@Component
@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomPersonEditionForm extends PersonEditionForm {

    /** Entity. */
    private String entity;
    /** Administrative entity. */
    private String administrativeEntity;
    /** Generic mail. */
    private String genericMail;
    /** Referer. */
    private String referer;

    /** Entities. */
    private Set<PersonEntity> entities;


    /**
     * Constructor.
     */
    public CustomPersonEditionForm() {
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
     * Getter for administrativeEntity.
     * 
     * @return the administrativeEntity
     */
    public String getAdministrativeEntity() {
        return administrativeEntity;
    }

    /**
     * Setter for administrativeEntity.
     * 
     * @param administrativeEntity the administrativeEntity to set
     */
    public void setAdministrativeEntity(String administrativeEntity) {
        this.administrativeEntity = administrativeEntity;
    }

    /**
     * Getter for genericMail.
     * 
     * @return the genericMail
     */
    public String getGenericMail() {
        return genericMail;
    }

    /**
     * Setter for genericMail.
     * 
     * @param genericMail the genericMail to set
     */
    public void setGenericMail(String genericMail) {
        this.genericMail = genericMail;
    }

    /**
     * Getter for referer.
     * 
     * @return the referer
     */
    public String getReferer() {
        return referer;
    }

    /**
     * Setter for referer.
     * 
     * @param referer the referer to set
     */
    public void setReferer(String referer) {
        this.referer = referer;
    }

    /**
     * Getter for entities.
     * @return the entities
     */
    public Set<PersonEntity> getEntities() {
        return entities;
    }

    /**
     * Setter for entities.
     * @param entities the entities to set
     */
    public void setEntities(Set<PersonEntity> entities) {
        this.entities = entities;
    }

}
