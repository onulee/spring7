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
	
	@GetMapping("/")
	public String index(Model model) {
		//세션정보 id
		String session_id = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("세션 id : "+session_id);
		//세션정보 ROLE
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = collection.iterator();
		GrantedAuthority auth = iterator.next();
		String session_role = auth.getAuthority();
		System.out.println("세션 role : "+session_role);
		
		//세션정보
		model.addAttribute("session_id",session_id);
		model.addAttribute("session_role",session_role);
		
		return "index";
	}

}
