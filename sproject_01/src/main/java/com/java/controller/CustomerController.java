package com.java.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.BoardApiDto;
import com.java.dto.BoardDto;
import com.java.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CustomerController {

//	@Autowired CustomerService customerService;
	private final CustomerService customerService;
	
	//02.게시글쓰기
	@GetMapping("/customer/customerWrite")
	public String customerWrite() {
		return "customer/customerWrite";
	}
	
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
	@GetMapping("/customer/blist")
	public Map<String, Object> customerApi(
			@RequestParam(name="page",defaultValue = "1") int page,
			@RequestParam(name="category",required = false) String category,
			@RequestParam(name="search",required = false) String search
			) {
		System.out.println("controller page : "+page);
		System.out.println("controller search : "+search);
		
		Map<String, Object> map = customerService.findAll(page,category,search); 
		List<BoardDto> list = (List<BoardDto>) map.get("list");
		return map;
	} 
	
	//02-01.게시판상세보기 : react bview
//	@CrossOrigin(origins = "http://localhost:3000") //WebConfig.java설정되어 있음.
	@ResponseBody
	@GetMapping("/customer/bview/{bno}")
	public BoardDto bviewApi(
			@PathVariable(name="bno") Integer bno
			) {
		System.out.println("controller bno : "+bno);
		BoardDto boardDto = customerService.findById(bno); 
		return boardDto;
	} 
	
	//03-01.게시글쓰기 저장 : react bwrite
	@ResponseBody
	@PostMapping("/customer/bwrite")
	public BoardDto bwriteApi(@RequestBody BoardApiDto boardApiDto) {
		System.out.println("controller btitle : "+boardApiDto.getBtitle());
		System.out.println("controller bcontent : "+boardApiDto.getBcontent());
		System.out.println("controller id : "+boardApiDto.getId());
		BoardDto boardDto = customerService.save(boardApiDto);
		return boardDto;
	}
	
	
	
}
