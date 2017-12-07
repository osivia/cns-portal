package fr.gouv.education.cns.directory.person.card.portlet.model;

import org.osivia.services.person.card.portlet.model.PersonNuxeoProfile;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Customized person Nuxeo profile.
 * 
 * @author Cédric Krommenhoek
 * @see PersonNuxeoProfile
 */
@Component
@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomPersonNuxeoProfile extends PersonNuxeoProfile {

    /** Administrative entity. */
    private String administrativeEntity;
    /** Generic mail. */
    private String genericMail;
    /** Referer. */
    private String referer;


    /**
     * Constructor.
     */
    public CustomPersonNuxeoProfile() {
        super();
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

}
