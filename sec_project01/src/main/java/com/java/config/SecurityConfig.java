package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	// 특정 HTTP 요청에 대한 웹 기반 보안 구성
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests((auth) -> auth
            	.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
            	.requestMatchers("/","/auth/**","/css/**","/images/**","/js/**")
            	.permitAll()
            	.requestMatchers("/admin").hasRole("ADMIN")
            	.requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                .anyRequest()
                .authenticated()
            )
            .formLogin((auth) -> auth
            		.loginPage("/auth/login")
            		.loginProcessingUrl("/auth/loginProc")
            		.permitAll()
            )
            .logout((auth) -> auth
            		.logoutUrl("/logout")
            		.logoutSuccessUrl("/")
            		.invalidateHttpSession(true)
            		.deleteCookies("COOK_ID")
            )
            .sessionManagement((auth) -> auth
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true)
            )
            .sessionManagement((auth) -> auth
            		 .sessionFixation().changeSessionId()
            )
            //.csrf((auth) -> auth.disable())
            ;
        return http.build();
	}
}
