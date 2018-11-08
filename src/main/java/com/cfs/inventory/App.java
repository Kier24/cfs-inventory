package com.cfs.inventory;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cfs.inventory.authentication.model.Role;
import com.cfs.inventory.authentication.model.User;
import com.cfs.inventory.authentication.model.UserRepository;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserRepository userRepository,PasswordEncoder passwordEncoder) {
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) throws Exception {
				User user = new User();
				user.setUsername("admin");
				String encodedPassword = passwordEncoder.encode("admin");
				user.setPassword(encodedPassword);
				user.setName("Kier");
				user.setLastName("Tenorio");
				user.setActive(1);
				Role role = new Role();
				role.setRole("master");
				user.setRoles(new HashSet<Role>(Arrays.asList(role)));
				userRepository.save(user);
			}
			
		};
	}
}

