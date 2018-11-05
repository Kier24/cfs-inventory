package com.cfs.inventory.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

@Configuration
@ComponentScan({ "com.cfs.inventory" })
@EnableTransactionManagement
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public Mapper mapper() {
		return DozerBeanMapperBuilder.buildDefault();
	}
}