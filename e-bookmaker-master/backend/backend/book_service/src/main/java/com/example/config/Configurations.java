package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Configurations {
	
	@Bean
	WebClient.Builder webclient(){
		return WebClient.builder();
	}

	@Bean
	ModelMapper modelmapper() {
		return new ModelMapper();
	}
	
}
