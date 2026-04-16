package com.java.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.java.dto.KakaoDto;
import com.java.dto.OAuthTokenDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
public class FController {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	@Value("${kakao.client-id}")
	private String clientId;
	@Value("${kakao.client-secret}")
	private String clientSecret;
	@Value("${kakao.redirect-uri}")
	private String redirectUri;
	@Value("${kakao.logout-redirect-uri}")
	private String logoutRedirectUri;
	
	private WebClient webClient = WebClient.create();
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/logout-complete")
	public String logout() {
		session.invalidate();
		System.out.println("로그아웃!");
		return "redirect:/";
	}
	@GetMapping("/loginKakao") //javascript
	public String loginkakao() {

	    String url = "https://kauth.kakao.com/oauth/authorize"
	            + "?response_type=code"
	            + "&client_id=" + clientId
	            + "&redirect_uri=" + redirectUri
	            + "&scope=profile_nickname,profile_image";
	    System.out.println("로그인 code : "+url);
	    return "redirect:" + url;
	}
	@GetMapping("/kakaoLogout")
	public String kakaoLogout() {
		//String client_id = clientId;
		//String logout_redirect_uri = logoutRedirectUri;

	    String url = "https://kauth.kakao.com/oauth/logout"
	            + "?client_id=" + clientId
	            + "&logout_redirect_uri=" + logoutRedirectUri;
	    System.out.println("로그아웃 : "+url);
	    return "redirect:" + url;
	}
	
	@GetMapping("/kakao/oauth")
	public String oauth(String code) {
		// [ 카카오 로그인 ]
		//#### 1차 : code ####
		System.out.println("## 1차 oauth code : "+code);
		String grant_type = "authorization_code";
		
		//카카오로그인 form방식
		//WebClient webClient = WebClient.create();
		OAuthTokenDto oAuthTokenDto =
		         webClient.post()
		               .uri("https://kauth.kakao.com/oauth/token")
		               .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
		               .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		               .body(BodyInserters.fromFormData("grant_type", grant_type)
		                       .with("client_id", clientId)
		                       .with("client_secret", clientSecret)
		                       .with("redirect_uri", redirectUri)
		                       .with("code", code))
		               .retrieve()
		               .bodyToMono(OAuthTokenDto.class)
		               .block();

		System.out.println("oAuthTokenDto : "+oAuthTokenDto);
		System.out.println("oAuthTokenDto Access_token : "+ oAuthTokenDto.getAccess_token());
			
		// #### 3차 사용자 정보 요청 ####
		// post방식으로 전송 - daum 토큰키를 요청함.
		KakaoDto kakaoDto =
		         webClient.post()
		               .uri("https://kapi.kakao.com/v2/user/me")
		               .header("Authorization", "Bearer "+oAuthTokenDto.getAccess_token())
		               .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
		               .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		               .retrieve()
		               .bodyToMono(KakaoDto.class)
		               .block();

		System.out.println("kakaoDto : "+kakaoDto);
					
		System.out.println("kakaoDto 개인정보 닉네임 : "+ kakaoDto.getProperties().getNickname());
		System.out.println("kakaoDto id : "+ kakaoDto.getId());
		System.out.println("kakaoDto connected_at : "+ kakaoDto.getConnected_at());
		
		//로그인 섹션 생성
		session.setAttribute("kakaoSessionId", kakaoDto.getId());
		session.setAttribute("kakaoSessionNicName", kakaoDto.getProperties().getNickname());
		System.out.println("카카오로그인 성공!!");
		return "redirect:/";
				
	}
	
}
