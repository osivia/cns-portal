package org.osivia.services.directory.various.portlet.model;

import java.util.ArrayList;
import java.util.List;

import org.osivia.portal.api.directory.v2.model.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Updated persons java-bean.
 * 
 * @author Cédric Krommenhoek
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UpdatedPersons {

    /** List. */
    private List<Person> list;
    /** Started indicator. */
    private boolean started;


    /**
     * Constructor.
     */
    public UpdatedPersons() {
        super();
        this.list = new ArrayList<>();
    }


    /**
     * Getter for list.
     * 
     * @return the list
     */
    public List<Person> getList() {
        return list;
    }

    /**
     * Setter for list.
     * 
     * @param list the list to set
     */
    public void setList(List<Person> list) {
        this.list = list;
    }

    /**
     * Getter for started.
     * 
     * @return the started
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Setter for started.
     * 
     * @param started the started to set
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

}
