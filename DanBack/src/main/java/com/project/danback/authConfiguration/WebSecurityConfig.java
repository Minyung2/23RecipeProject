package com.project.danback.authConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.danback.user.dao.UserMapper;
import com.project.danback.utility.CustomUserDetailsManager;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	public AuthnSuccessHandler authnSuccessHandler;
	@Autowired
	public AuthnFailureHandler authnFailureHandler;
	@Autowired
	UserMapper dao;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();
		http.authorizeHttpRequests(request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/css/**", "/js/**", "/img/**,/topbottom/**").permitAll().requestMatchers("/**").permitAll()
//				.requestMatchers("/guest/**").permitAll()
//				.requestMatchers("/RecipeBoard/**").hasAnyRole("USER", "ADMIN").anyRequest().authenticated()
		);
		
		http.formLogin().loginPage("/login")
		.loginProcessingUrl("/loginProcess")
				.successHandler(authnSuccessHandler).failureHandler(authnFailureHandler)
				.usernameParameter("user_id").passwordParameter("user_pw").permitAll();
				
		http.logout()
			.logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsManager();
		
	}

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
