package com.cfs.inventory.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.cfs.inventory.model")
public class InfrastructureConfig {

}
