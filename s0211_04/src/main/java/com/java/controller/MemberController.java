package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.Member;
import com.java.service.MemberService;
import com.java.service.MemberServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired HttpSession session;
	@Autowired MemberService memberService = new MemberServiceImpl();
	
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate(); //섹션모두삭제
		return "redirect:/?flag=0";
	}
	
	@GetMapping("/member/login")
	public String login(String flag,Model model) {
		model.addAttribute("flag",flag);
		return "login";
	}
	
	//1.HttpServletRequest 2.@RequestParam 3.객체
	@PostMapping("/member/login") //form
	public String login(Member member,Model model) {
		
		//db연결 - id,pw가 맞으면, 1개 가져오기
		System.out.println("id,pw : "+member.getId()+","+member.getPw());
		Member m = memberService.selectIdAndPw(member);
		if(m != null) {
			System.out.println("m : "+m.getId());
			session.setAttribute("session_id", m.getId());
			session.setAttribute("session_name", m.getName());
			return "redirect:/?flag=1";
		}else {
			System.out.println("아이디,패스워드 일치하지 않음");
		}
		return "redirect:/member/login?flag=2";
	}

}
