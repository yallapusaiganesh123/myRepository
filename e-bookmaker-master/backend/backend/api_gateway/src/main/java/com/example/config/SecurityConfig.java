package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

@Configuration
public class SecurityConfig {
	
	@SuppressWarnings("null")
	@Bean
    @Primary
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

	@SuppressWarnings("removal")
	@Bean
	public SecurityWebFilterChain filterchain(ServerHttpSecurity http) throws Exception {
		
	http.cors().disable().csrf().disable().
		authorizeExchange(auth->auth.pathMatchers("/api/user/login",
				"/api/user/register",
				"/api/admin/login",
				"/api/author/register",
				"/api/author/login"
				).permitAll().anyExchange().authenticated()).oauth2ResourceServer().jwt();
		
	return http.build();
	}
	
}
