package com.nay.springboot.Thymeleafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nay.springboot.Thymeleafdemo.service.UserService;

@Configuration
public class SecurityConfig {
	
    private UserService userService;
	
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	public SecurityConfig(UserService userService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
		this.userService = userService;
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(configurer ->
						configurer
								.requestMatchers("/").authenticated()
								.requestMatchers("/").hasRole("EMPLOYEE")
								.requestMatchers("/leaders/**").hasAnyRole("MANAGER")
								.requestMatchers("/systems").hasRole("ADMIN")
								.requestMatchers("/**").permitAll()
				)
				.formLogin(form ->
						form
								.loginPage("/showMyLoginPage")
								.loginProcessingUrl("/authenticateTheUser")
								.successHandler(customAuthenticationSuccessHandler)
								.permitAll()
				)
				.logout(logout -> logout.permitAll()
				);

		return http.build();
	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}

}
