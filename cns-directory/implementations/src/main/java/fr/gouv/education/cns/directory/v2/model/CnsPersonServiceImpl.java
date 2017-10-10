package fr.gouv.education.cns.directory.v2.model;

import org.osivia.directory.v2.service.PersonServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.gouv.education.cns.directory.v2.service.CnsPersonService;

/**
 * CNS person service implementation.
 * 
 * @author Cédric Krommenhoek
 * @see PersonServiceImpl
 * @see CnsPersonService
 */
@Service
@Primary
public class CnsPersonServiceImpl extends PersonServiceImpl implements CnsPersonService {

    /**
     * Constructor.
     */
    public CnsPersonServiceImpl() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CnsPerson getPerson(String uid) {
        return (CnsPerson) super.getPerson(uid);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CnsPerson getEmptyPerson() {
        return (CnsPerson) super.getEmptyPerson();
    }

}
