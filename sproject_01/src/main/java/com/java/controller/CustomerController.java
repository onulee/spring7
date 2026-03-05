package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.dto.BoardDto;
import com.java.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired CustomerService customerService;
	
	//01.게시판리스트
	@GetMapping("/customer/customer")
	public String customer(Model model) {
		List<BoardDto> list = customerService.findAll(); 
		model.addAttribute("list",list);
		return "customer/customer";
	} 
}
