package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberContoller {
	
	@Autowired HttpSession session;
	@Autowired MemberService memberService;
	
	// 회원가입01
	@GetMapping("/member/join01")
	public String join01() {
		return "join01";
	}
	// 회원가입02
	@GetMapping("/member/join02")
	public String join02() {
		return "join02";
	}
	
	// 로그아웃 확인
	@GetMapping("/member/logout")
	public String logout(String flag,Model model) {
		model.addAttribute("flag",flag);
		session.invalidate();
		return "redirect:/?flag=0";
	}
	
	// 로그인페이지 호출
	@GetMapping("/member/login")
	public String login(String flag,Model model) {
		model.addAttribute("flag",flag);
		return "login";
	}
	
	// 로그인확인
	@PostMapping("/member/login")
	public String login(MemberDto mdto) {
		System.out.println("id,pw : "+mdto.getId()+","+mdto.getPw());
		//로그인 1개 객체가져오기
		MemberDto memberDto = memberService.selectLogin(mdto);
		if(memberDto != null) {
			System.out.println("id,pw 존재함");
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			return "redirect:/?flag=1";
		}else {
			System.out.println("id,pw 일치하지 않음.");
			return "redirect:/member/login?flag=2";
		}
	}
	
	

}
