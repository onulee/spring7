package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	
	//회원가입페이지
	@GetMapping("/auth/join") 
	public String join() {
		return "join";    
	}
	
	//회원가입저장
	@PostMapping("/auth/joinProc") 
	public String joinProc(MemberDto mdto,Model model) {
		System.out.println("controller username : "+mdto.getUsername());
		memberService.save(mdto);
		return "redirect:/";    
	}
	
	//로그아웃확인
	@GetMapping("/logout") 
	public String logout() {
		session.invalidate();
		return "redirect:/auth/login";   
	}
	
	//로그인페이지
	@GetMapping("/auth/login")
	public String login() {
		return "login";   //WEB-INF/views/login.jsp
	}
	
	//로그인확인
	@PostMapping("/auth/login")
	public String login(MemberDto mdto) {
		
		MemberDto memberDto = memberService.findByIdAndPw(mdto);
		if(memberDto!=null) {
			System.out.println("로그인 되었습니다.");
			return "redirect:/";   
		}else {
			System.out.println("아이디 또는 패스워드가 일치하지 않습니다.");
			return "login";   
		}
	}
	
	
	

	
	

}
