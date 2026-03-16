package com.java.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FController {

	@GetMapping({"/","/index"})
	public String index(Model model) {
		//sec 섹션정보
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("섹션 id : "+id);
		
		//sec role정보
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		
		model.addAttribute("session_id",id);
		model.addAttribute("session_role",role);
		
		return "index";
	}
}
