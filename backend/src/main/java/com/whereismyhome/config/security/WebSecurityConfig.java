package com.whereismyhome.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.cors().disable()
				.authorizeRequests()
					.antMatchers("/", "/api/address", "/api/user/**","/api/trade/**", "/api/interest/**", "/api/news**").permitAll()
					.antMatchers(HttpMethod.GET, "/api/notice/**").permitAll()
					.antMatchers(HttpMethod.POST, "/api/notice/**").hasRole(Role.ADMIN.getValue())
					.antMatchers(HttpMethod.PUT, "/api/notice/**").hasRole(Role.ADMIN.getValue())
					.antMatchers(HttpMethod.DELETE, "/api/notice/**").hasRole(Role.ADMIN.getValue())
					.antMatchers(HttpMethod.GET, "/api/qna/**").permitAll()
					.antMatchers(HttpMethod.POST, "/api/qna/**").hasAnyRole(Role.ADMIN.getValue(), Role.USER.getValue())
					.antMatchers(HttpMethod.DELETE, "/api/qna/**").hasAnyRole(Role.ADMIN.getValue(), Role.USER.getValue())
					.antMatchers(HttpMethod.OPTIONS).permitAll()
					.anyRequest().authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}