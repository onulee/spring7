package com.java.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MemberController {
	
	//@RequestMapping("/login")
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		// jsp -> request.setAttribute()
		// spring -> model.addAttribute()
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		return "doLogin";
	}//login
	
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}//join
	
	@PostMapping("/join")
	public String join(MemberDto mdto,Model model) {
		System.out.println(String.format("%s,%s,%s,%s,%s,%s,%s",
				mdto.getId(),mdto.getPw(),mdto.getName(),
				mdto.getPhone(),
				mdto.getEmail(),mdto.getGender(),mdto.getHobby()));
		model.addAttribute("member",mdto);
		return "doJoin";
	}//join

}
