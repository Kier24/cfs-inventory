package com.cfs.inventory.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "com.cfs.inventory" })
@EnableTransactionManagement
public class ApplicationConfig {

}