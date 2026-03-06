package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;

	//01.로그인페이지 연결
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	} 
	//02.로그인확인
	@PostMapping("/member/login")
	public String login(MemberDto mdto, 
			@RequestParam(name="idsave",required = false) String idsave,
			HttpServletResponse response, Model model) {
		
		// 쿠키저장 - 입력된 아이디를 저장
		Cookie cookie = new Cookie("cook_id", mdto.getId());
		cookie.setPath("/");
		if(idsave != null) cookie.setMaxAge(60*60*24*30); //30일동안 저장
		else cookie.setMaxAge(0); //30일동안 저장
		response.addCookie(cookie);
		
		// 로그인확인
		MemberDto memberDto = memberService.findByIdAndPw(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			model.addAttribute("flag",1);
		}else {
			model.addAttribute("flag",-1);
		}
		return "member/login";
	} //
	
	//03.로그아웃
	@GetMapping("/member/logout")
	public String logout(Model model) {
		session.invalidate();
		model.addAttribute("flag",0);
		return "member/login";
	} 
	
	//04.회원가입페이지 step01연결
	@GetMapping("/member/step01")
	public String step01() {
		return "/member/step01";
	}
	
	//05.회원가입페이지 step02연결
	@GetMapping("/member/step02")
	public String step02() {
		return "/member/step02";
	}
	
	//06.회원가입페이지 step03연결
	@GetMapping("/member/step03")
	public String step03() {
		return "/member/step03";
	}
	
	
	
}
