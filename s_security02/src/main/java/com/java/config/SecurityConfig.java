package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// csrf토큰, 페이지분기, 로그인폼, 로그아웃
		http
			//.csrf((auth)->auth.disable())
			.authorizeHttpRequests((auth)->auth
					//jsp페이지는 index.jsp 열기
					//permitAll:로그인없이 오픈, hasRole:권한에 따라 오픈, authenticated:로그인후 오픈
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/","/auth/**","/images/**","/css/**","/js/**").permitAll()
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/member/**").hasAnyRole("ADMIN","MANAGER","USER") //2개이상
					.anyRequest().authenticated())
			.exceptionHandling((auth)->auth
					.accessDeniedPage("/errorException"))
			
			.formLogin((auth)->auth
					.loginPage("/auth/login")
					.loginProcessingUrl("/auth/loginProc")
					.permitAll())
			.logout((auth)->auth           //원칙 : logout -> post전송이 되어야 함.
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					);
		return http.build();
	}
	
	@Bean //리턴된 값이 객체일때 객체를 바로 IOC컨테이터에 등록시킬때 사용
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
