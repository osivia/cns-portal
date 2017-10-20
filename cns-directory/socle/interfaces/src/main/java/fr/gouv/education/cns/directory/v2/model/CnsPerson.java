package fr.gouv.education.cns.directory.v2.model;

import org.osivia.portal.api.directory.v2.model.Person;

/**
 * CNS person interface.
 * 
 * @author Cédric Krommenhoek
 * @see Person
 */
public interface CnsPerson extends Person {

    /**
     * Get CNS person entity.
     * 
     * @return entity
     */
    String getEntity();


    /**
     * Set CNS person entity.
     * 
     * @param entity entity
     */
    void setEntity(String entity);

}
