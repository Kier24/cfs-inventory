package com.cfs.inventory;

import java.math.BigDecimal;
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
import com.cfs.inventory.model.ContainerType;
import com.cfs.inventory.model.ContainerTypeRepository;
import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.ProductRepository;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserRepository userRepository, PasswordEncoder passwordEncoder,
			ContainerTypeRepository containerTypeRepository, ProductRepository productRepository) {
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

				ContainerType litter = new ContainerType();
				litter.setName("Litter");
				ContainerType halfLitter = new ContainerType();
				halfLitter.setName("Half Litter");
				ContainerType gallon = new ContainerType();
				gallon.setName("Gallon");
				ContainerType halfGallon = new ContainerType();
				halfGallon.setName("Half Gallon");
				ContainerType ml320 = new ContainerType();
				ml320.setName("320 mL");
				ContainerType ml200 = new ContainerType();
				ml200.setName("200 mL");

				containerTypeRepository.save(litter);
				containerTypeRepository.save(halfLitter);
				containerTypeRepository.save(gallon);
				containerTypeRepository.save(halfGallon);
				containerTypeRepository.save(ml320);
				containerTypeRepository.save(ml200);

				Product bananaKetchup = new Product(null,"Banana Ketchup", BigDecimal.valueOf(30.50));
				Product soySauce = new Product(null,"Soy Sauce", BigDecimal.valueOf(25.99));
				Product vinegar = new Product(null,"Vinegar", BigDecimal.valueOf(12.99));
				
				productRepository.save(bananaKetchup);
				productRepository.save(soySauce);
				productRepository.save(vinegar);
			}

		};
	}
}
