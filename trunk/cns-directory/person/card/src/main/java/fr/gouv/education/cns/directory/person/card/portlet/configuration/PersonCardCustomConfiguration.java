package fr.gouv.education.cns.directory.person.card.portlet.configuration;

import org.osivia.services.person.card.portlet.configuration.PersonCardConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Person card customized portlet configuration.
 * 
 * @author Cédric Krommenhoek
 * @see PersonCardConfiguration
 */
@Configuration
@ComponentScan(basePackages = {"fr.gouv.education.cns.directory.person.card.portlet", "fr.gouv.education.cns.directory.person.util.portlet"})
public class PersonCardCustomConfiguration extends PersonCardConfiguration {

    /**
     * Constructor.
     */
    public PersonCardCustomConfiguration() {
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
