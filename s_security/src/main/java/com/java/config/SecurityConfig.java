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
		//예외페이지 적용
		http
			.authorizeHttpRequests((auth) -> auth
					//controller에서 page연결시 허가
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/","/images/**","/css/**","/js/**","/auth/**").permitAll()
					.requestMatchers("/admin").hasRole("ADMIN") //ADMIN권한소유자만
					.requestMatchers("/my/**").hasAnyRole("USER","ADMIN") //권한 2개이상
					.anyRequest().authenticated()  //로그인후 페이지 오픈
			)
			.formLogin((auth) -> auth
					.loginPage("/auth/login") //기본 로그인페이지연결
					.loginProcessingUrl("/auth/loginProc") //로그인버튼 클릭시 진행
					.permitAll()
					
			)
			.logout((auth) -> auth
					.logoutUrl("/logout")  //로그아웃페이지 호출
					.logoutSuccessUrl("/") //로그아웃 성공시 메인페이지 이동
					.invalidateHttpSession(true) //세션모두종료
			)		
			//.csrf((auth) -> auth.disable()) //개발단계에서는 예외처리
			;
		return http.build(); //리턴하는 객체를 ioc컨테이너에 등록시켜줌.
	}
	
	//암호화메소드구현 - 시큐리티에서 지원하는 암호화객체
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
