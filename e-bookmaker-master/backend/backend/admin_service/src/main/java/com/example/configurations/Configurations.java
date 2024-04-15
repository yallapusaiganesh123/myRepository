package com.example.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.repository.UserAdminRepository;

@Configuration
public class Configurations {
	
	UserAdminRepository repo;
	
	@Bean
	WebClient.Builder webclient(){
		return WebClient.builder();
	}

	@Bean
	ModelMapper modelmapper() {
		return new ModelMapper();
	}
	

	
	@Bean
	PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
