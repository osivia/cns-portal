package fr.gouv.education.cns.feeder.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Feeder portlet service interface.
 * 
 * @author Cédric Krommenhoek
 */
public interface FeederService {

    /** CAS attribute prefix. */
    String CAS_ATTRIBUTE_PREFIX = "cas:";


    /**
     * Invoke feeder.
     * 
     * @param request HTTP servlet request
     */
    void invoke(HttpServletRequest request);

}
