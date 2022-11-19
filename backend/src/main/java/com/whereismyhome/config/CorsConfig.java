package com.whereismyhome.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://127.0.0.1:5502","http://127.0.0.1:9000","http://127.0.0.1:9001")
		.allowedHeaders("X-AUTH_TOKEN", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
		.exposedHeaders("X-AUTH_TOKEN", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
		.allowedMethods("*")
		.allowCredentials(true);
	}
}