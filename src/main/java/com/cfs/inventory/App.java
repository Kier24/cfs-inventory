package com.cfs.inventory;

import java.util.Arrays;
import java.util.HashSet;

import com.cfs.inventory.model.Product;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cfs.inventory.authentication.model.Role;
import com.cfs.inventory.authentication.model.User;
import com.cfs.inventory.authentication.model.UserRepository;
import com.cfs.inventory.model.Container;
import com.cfs.inventory.model.ContainerRepository;
import com.cfs.inventory.model.ProductRepository;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserRepository userRepository, PasswordEncoder passwordEncoder,
											   ContainerRepository containerRepository, ProductRepository productRepository) {
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

				Container litter = new Container();
				litter.setName("Litter");
				Container halfLitter = new Container();
				halfLitter.setName("Half Litter");
				Container gallon = new Container();
				gallon.setName("Gallon");
				Container halfGallon = new Container();
				halfGallon.setName("Half Gallon");
				Container ml320 = new Container();
				ml320.setName("320 mL");
				Container ml200 = new Container();
				ml200.setName("200 mL");

				containerRepository.save(litter);
				containerRepository.save(halfLitter);
				containerRepository.save(gallon);
				containerRepository.save(halfGallon);
				containerRepository.save(ml320);
				containerRepository.save(ml200);

				Product bananaKetchup = new Product(null,"Banana Ketchup","Banana Ketchup",litter,
						20,5);/*
				Product soySauce = new Product(null,"Soy Sauce");
				Product vinegar = new Product(null,"Vinegar");
				*/
				productRepository.save(bananaKetchup);
				/*productRepository.save(soySauce);
				productRepository.save(vinegar);*/
			}

		};
	}
}
