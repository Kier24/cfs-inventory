package com.cfs.inventory.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/console/**,/h2-console").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").usernameParameter("username")
                .passwordParameter("password").permitAll().defaultSuccessUrl("/")
               .and().logout().permitAll();
		
	}

	

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**", "/vendor/**","/external/**", "/fonts/**", "/css/**", "/js/**", "/images/**");
	}

}