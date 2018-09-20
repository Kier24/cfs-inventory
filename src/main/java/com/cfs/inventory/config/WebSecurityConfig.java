package com.cfs.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("admin")
				.password("$2y$12$yaK1icrynyR01z7ZnkKG2uPzKlxutXcWBhOE6xXmuhviO0DtPOhDm").roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user);
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

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