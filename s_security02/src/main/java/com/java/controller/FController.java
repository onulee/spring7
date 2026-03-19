package com.java.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FController {

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	@GetMapping("/errorException")
	public String errorException() {
		return "errorException";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		//세션정보 가져오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		//세션 ROLE정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
		// 컬렉션 객체는 Iterator객체를 사용해서 출력할수 있음.
		Iterator<? extends GrantedAuthority> iterator = collection.iterator();
		GrantedAuthority auth = iterator.next();
		String role = auth.getAuthority();
		
		//전달
		model.addAttribute("session_id",id);
		model.addAttribute("session_role",role);
		return "index";
	}
}
