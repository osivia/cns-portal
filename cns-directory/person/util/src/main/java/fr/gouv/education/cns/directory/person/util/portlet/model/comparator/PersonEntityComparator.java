package fr.gouv.education.cns.directory.person.util.portlet.model.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import fr.gouv.education.cns.directory.person.util.portlet.model.PersonEntity;

/**
 * Person entity comparator.
 * 
 * @author Cédric Krommenhoek
 * @see Comparator
 * @see PersonEntity
 */
@Component
public class PersonEntityComparator implements Comparator<PersonEntity> {

    /**
     * Constructor.
     */
    public PersonEntityComparator() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(PersonEntity entity1, PersonEntity entity2) {
        int result;

        if (entity1 == null) {
            result = -1;
        } else if (entity2 == null) {
            result = 1;
        } else {
            String label1 = entity1.getLabel();
            String label2 = entity2.getLabel();

            if (label1 == null) {
                result = -1;
            } else if (label2 == null) {
                result = 1;
            } else {
                result = label1.compareToIgnoreCase(label2);
            }
        }

        return result;
    }

}
