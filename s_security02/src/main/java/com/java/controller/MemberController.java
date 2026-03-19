package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@RequiredArgsConstructor
@Controller
public class MemberController {

	//@Autowired private MemberService memberService;
	private final MemberService memberService;
	
	@GetMapping("/auth/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/auth/joinProc")
	public String joinProc(MemberDto mdto) {
		System.out.println("controller id : "+mdto.getId());
		//회원가입 저장
		memberService.save(mdto);
		return "redirect:/";
	}
	
	@GetMapping("/auth/login")
	public String login() {
		return "login";
	}
	
	//get방식으로 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/";
	}
	
	
	
	@GetMapping("/member/member")
	public String member() {
		return "member";
	}
}
