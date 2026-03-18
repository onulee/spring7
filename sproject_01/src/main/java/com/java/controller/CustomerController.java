package com.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.BoardDto;
import com.java.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired CustomerService customerService;
	
	//01.게시판리스트
	@GetMapping("/customer/customer")
	public String customer(
			@RequestParam(name="page",defaultValue = "1") int page,
			@RequestParam(name="category",required = false) String category,
			@RequestParam(name="search",required = false) String search,
			Model model) {
		Map<String, Object> map = customerService.findAll(page,category,search); 
		model.addAttribute("map",map);
		return "customer/customer";
	} 
	
	//01-01.게시판리스트 : react 게시판 연결
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	@GetMapping("/customer/customerJson")
	public Map<String, Object> customerJson(
			@RequestParam(name="page",defaultValue = "1") int page,
			@RequestParam(name="category",required = false) String category,
			@RequestParam(name="search",required = false) String search
			) {
		Map<String, Object> map = customerService.findAll(page,category,search); 
		List<BoardDto> list = (List<BoardDto>) map.get("list");
		return map;
	} 
	
	//02.게시글쓰기
	@GetMapping("/customer/customerWrite")
	public String customerWrite() {
		return "customer/customerWrite";
	}
	
}
