package org.osivia.directory.v2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Primary
@EnableTransactionManagement
@ComponentScan(basePackages = {"org.osivia.directory.v2", "fr.gouv.education.cns.directory.v2"})
public class CustomizedConfiguration extends AppConfig {

}
