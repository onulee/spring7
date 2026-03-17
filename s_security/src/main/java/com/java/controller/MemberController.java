package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
//	@Autowired private MemberService memberService;
	private final MemberService memberService;
	
	@GetMapping("/auth/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/auth/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/auth/joinProc")
	public String joinProc(MemberDto mdto) {
		System.out.println("controller username : "+mdto.getUsername());
		System.out.println("controller password : "+mdto.getPassword());
		System.out.println("controller mdto : "+mdto);
		memberService.save(mdto);
		return "join";
//		return "redirect:/";
	}
	
	
	

}
