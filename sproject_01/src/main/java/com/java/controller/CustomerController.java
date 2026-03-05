package com.java.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.BoardDto;
import com.java.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired CustomerService customerService;
	
	//01.게시판리스트
	@GetMapping("/customer/customer")
	public String customer(
			@RequestParam(name="page",defaultValue = "1") int page,
			Model model) {
		Map<String, Object> map = customerService.findAll(page); 
		model.addAttribute("map",map);
		return "customer/customer";
	} 
}
