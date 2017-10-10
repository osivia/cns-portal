package fr.gouv.education.cns.directory.v2.service;

import org.osivia.directory.v2.service.PersonUpdateService;

import fr.gouv.education.cns.directory.v2.model.CnsPerson;

/**
 * CNS person service interface.
 * 
 * @author Cédric Krommenhoek
 * @see PersonUpdateService
 */
public interface CnsPersonService extends PersonUpdateService {

    /**
     * {@inheritDoc}
     */
    @Override
    CnsPerson getPerson(String uid);


    /**
     * {@inheritDoc}
     */
    @Override
    CnsPerson getEmptyPerson();

}
