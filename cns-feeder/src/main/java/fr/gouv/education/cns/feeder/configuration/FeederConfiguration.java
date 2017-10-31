package fr.gouv.education.cns.feeder.configuration;

import java.util.HashMap;
import java.util.Map;

import org.osivia.directory.v2.service.PersonUpdateService;
import org.osivia.directory.v2.service.WorkspaceService;
import org.osivia.portal.api.directory.v2.DirServiceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Feeder configuration.
 * 
 * @author Cédric Krommenhoek
 */
@Configuration
@ComponentScan(basePackages = "fr.gouv.education.cns.feeder")
@PropertySource("classpath:workspaces.properties")
public class FeederConfiguration {

    /**
     * Constructor.
     */
    public FeederConfiguration() {
        super();
    }


    /**
     * Get CNS context source.
     * 
     * @return context source
     */
    @Bean(name = "cnsContextSource")
    public ContextSource getCnsContextSource() {
        // Base environment properties
        Map<String, Object> baseEnvironmentProperties = new HashMap<>();
        baseEnvironmentProperties.put("com.sun.jndi.ldap.connect.timeout", System.getProperty("ldapSource.timeout"));

        // Context source
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(System.getProperty("ldapSource.url"));
        contextSource.setBase(System.getProperty("ldapSource.base"));
        contextSource.setUserDn(System.getProperty("ldapSource.manager.dn"));
        contextSource.setPassword(System.getProperty("ldapSource.manager.pswd"));
        contextSource.setPooled(true);
        contextSource.setBaseEnvironmentProperties(baseEnvironmentProperties);

        return contextSource;
    }


    /**
     * Get CNS LDAP template.
     * 
     * @param contextSource context source
     * @return LDAP template
     */
    @Bean(name = "cnsLdapTemplate")
    public LdapTemplate getCnsLdapTemplate(@Qualifier("cnsContextSource") ContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }


    /**
     * Get person service.
     * 
     * @return person service
     */
    @Bean
    public PersonUpdateService getPersonService() {
        return DirServiceFactory.getService(PersonUpdateService.class);
    }


    /**
     * Get workspace service.
     * 
     * @return workspace service
     */
    @Bean
    public WorkspaceService getWorkspaceService() {
        return DirServiceFactory.getService(WorkspaceService.class);
    }

}
