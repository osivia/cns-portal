package fr.gouv.education.cns.directory.person.management.portlet.configuration;

import org.osivia.services.person.management.portlet.configuration.PersonManagementConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Customized person management portlet configuration.
 * 
 * @author Cédric Krommenhoek
 * @see PersonManagementConfiguration
 */
@Configuration
@ComponentScan(basePackages = {"fr.gouv.education.cns.directory.person.management.portlet", "fr.gouv.education.cns.directory.person.util.portlet"})
public class CustomPersonManagementConfiguration extends PersonManagementConfiguration {

    /**
     * Constructor.
     */
    public CustomPersonManagementConfiguration() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Bean(name = "messageSource")
    @Override
    public ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("Resource-custom", "Resource");
        return messageSource;
    }

}
