package fr.gouv.education.cns.directory.person.card.portlet.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Person entity java-bean.
 * 
 * @author Cédric Krommenhoek
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PersonEntity {

    /** Code. */
    private String code;
    /** Label. */
    private String label;


    /**
     * Constructor.
     */
    public PersonEntity() {
        super();
    }


    /**
     * Getter for code.
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for code.
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for label.
     * 
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Setter for label.
     * 
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
