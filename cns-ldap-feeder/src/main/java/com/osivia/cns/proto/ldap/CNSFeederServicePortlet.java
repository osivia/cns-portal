package com.osivia.cns.proto.ldap;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;

import org.osivia.portal.api.login.IUserDatasModuleRepository;
import org.osivia.portal.api.login.UserDatasModuleMetadatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.DispatcherPortlet;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

import fr.toutatice.portail.cms.nuxeo.api.CMSPortlet;


public class CNSFeederServicePortlet extends DispatcherPortlet {
	

    protected IUserDatasModuleRepository repository;
    private UserDatasModuleMetadatas userDatasModule;
    
    @Autowired
    private ApplicationContext context;
    

    @Override
    public void init(PortletConfig config) throws PortletException {

        super.init(config);

        PortletContext pc = this.getPortletContext();

        // export the directory service for other portlets
        //ApplicationContext context = PortletApplicationContextUtils.getWebApplicationContext(pc);
        Feeder feeder = (Feeder) getPortletApplicationContext().getBean("feeder");

        feeder.setPortletContext(pc);


        // register the feeder of current user informations
        try {
            repository = (IUserDatasModuleRepository) feeder.getPortletContext().getAttribute("UserDatasModulesRepository");

            userDatasModule = new UserDatasModuleMetadatas();

            userDatasModule.setName("LDAPFEEDER");
            userDatasModule.setOrder(-1); // appelé avant le chargement du user
            userDatasModule.setModule(feeder);

            repository.register(userDatasModule);
        } catch (Exception e) {
            throw new PortletException(e);
        }


    }


    // @Override
    public void destroy() {

        super.destroy();

        repository.unregister(userDatasModule);
    }


}
