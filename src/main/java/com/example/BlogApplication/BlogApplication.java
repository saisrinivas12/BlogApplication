package com.example.BlogApplication;

import com.example.BlogApplication.Repository.RoleRepository;
import com.example.BlogApplication.config.AppConstants;
import com.example.BlogApplication.entities.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
         System.out.println("encoded pass ");
		String result = passwordEncoder.encode("ravikanth");
		System.out.println("encode "+result);

		//System.out.println(this.passwordEncoder.matches("charan","$2a$2a$10$OlugdHMIXZyllhpEBm9ncuWQ.V4PmVbgT3xb5pDuVyc6GhIkuWC4O"));

		Role r1 = new com.example.BlogApplication.entities.Role();
		r1.setId(AppConstants.NORMAL_USER);
		r1.setName("ROLE_NORMAL");
		Role r2 = new Role();
		r2.setId(AppConstants.ADMIN_USER);
		r2.setName("ROLE_ADMIN");
		List<Role> roles = List.of(r1,r2);
		List<Role>savedRoles = roleRepository.saveAll(roles);
		System.out.println("saved Roles "+savedRoles);


	}
}
