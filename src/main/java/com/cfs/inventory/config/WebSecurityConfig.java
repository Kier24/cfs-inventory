package com.cfs.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("kier").password("123456").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	//	http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().formLogin()
	//			.loginPage("/login").permitAll().and().logout().permitAll();
		http.csrf().disable();
	}
	

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()		
//				.antMatchers("/orders/**").hasRole("USER")			
//				.and()
//			.formLogin()
//				.loginPage("/login").permitAll().failureUrl("/403");	
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.csrf().disable();
//
//		// The pages does not require login
//		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
//
//		// /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
//		// If no login, it will redirect to /login page.
//		http.authorizeRequests().antMatchers("/order").access("hasAnyRole('USER', 'ROLE_ADMIN')");
//
//		// For ADMIN only.
//		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
//
//		// When the user has logged in as XX.
//		// But access a page that requires role YY,
//		// AccessDeniedException will be thrown.
//		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//
//		// Config for Login Form
//		http.authorizeRequests().and().formLogin()//
//				// Submit URL of login page.
//				.loginProcessingUrl("/j_spring_security_check") // Submit URL
//				.loginPage("/login")//
//				.defaultSuccessUrl("/order")//
//				.failureUrl("/login?error=true")//
//				.usernameParameter("username")//
//				.passwordParameter("password")
//				// Config for Logout Page
//				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
//
//	}
}