package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.ApiService;

@Controller
public class ApiController {
	
	@Autowired ApiService apiService;

	//01. 이메일발송
	@ResponseBody
	@PostMapping("/api/email")
	public String emailSend(
			@RequestParam(name="email",required = false) String email
			) {
		System.out.println("이메일 확인 : "+email);
		String pwCode = apiService.emailSend(email);
		return pwCode;
	}
	
	
	
	
	
	
}
