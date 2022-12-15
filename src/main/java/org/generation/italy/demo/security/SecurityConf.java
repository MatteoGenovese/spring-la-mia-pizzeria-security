package org.generation.italy.demo.security;

import org.generation.italy.demo.service.UserServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConf {

	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/user", "/user/**").hasAuthority("USER")
				.requestMatchers(HttpMethod.POST, "/user", "/user/**").hasAuthority("ADMIN")
				.requestMatchers("/admin", "/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/useradmin", "/useradmin/**").hasAnyAuthority("USER", "ADMIN")	
				.requestMatchers("/**").permitAll()
			.and().formLogin()
			.and().logout()
		;

		return http.build();
	}
	
	@Bean
	public UserDetailsService getuseDetailsService() {
		
		return new UserServ();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(getuseDetailsService());
		provider.setPasswordEncoder(getPasswordEncoder());
		
		return provider;
	}
}	