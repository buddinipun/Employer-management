package com.buddi.userManagement.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/authenticate").permitAll() // Allow access to Swagger UI and API docs
                .requestMatchers("/api/v1/employees/**").authenticated() // Restrict account endpoints to 'USER' role
                .anyRequest().authenticated() // Require authentication for other endpoints
            )
        .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWT filter before username password authentication
        
        return http.build();
    }
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	    	
	        UserDetails user = User.withDefaultPasswordEncoder()
	                .username("testuser")
	                .password("password")
	                .roles("USER")
	                .build();

	        return new InMemoryUserDetailsManager(user);
	    }
}
