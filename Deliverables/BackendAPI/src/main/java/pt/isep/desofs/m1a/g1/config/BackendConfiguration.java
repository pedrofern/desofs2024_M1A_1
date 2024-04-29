package pt.isep.desofs.m1a.g1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pt.isep.desofs.m1a.g1.repository")
public class BackendConfiguration {

}
