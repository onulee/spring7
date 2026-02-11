package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.service.BoardService;
import com.java.service.MemberService;

@Controller
public class MemberController {
	
	//IOC컨테이너에 있으니 가져와서 자동으로 넣어줘.
	@Autowired MemberService memberService;
	
	@GetMapping("/member/mlist")
	public String mlist(Model model) {
		
		List<MemberDto> list = memberService.selectAll();
		model.addAttribute("list",list);
		System.out.println("list개수 : "+list.size());
		
		return "mlist";
	}

}
