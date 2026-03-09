package com.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfig {
	
	//시큐리티 로그인에서 데이터 확인 
	@Autowired private PrincipalDetailService principalDetailService;


	@Bean  //리턴타입 SecurityFilterChain
	public SecurityFilterChain filterChan(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(auth -> auth
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/","/login","/css/**","/js/**","/images/**").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin(form -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/loginProc")
                    .defaultSuccessUrl("/") // 로그인 성공 후 이동할 페이지
                    .failureUrl("/login") //로그인 실패시 이동할 페이지
                    .permitAll()
            );
		
		return http.build();
	}
}
