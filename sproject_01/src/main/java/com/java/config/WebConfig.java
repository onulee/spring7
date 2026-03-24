package com.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //API허용
		        .allowedOrigins("http://localhost:3000") //REACT만 허용
		        .allowCredentials(true); //쿠키,세션사용가능
	}
}
