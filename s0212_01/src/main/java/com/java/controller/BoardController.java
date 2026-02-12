package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired BoardService boardService;
	
	@GetMapping("/board/blist")
	public String blist(Model model) {
		//게시글 전체가져오기 - 여러개
		List<BoardDto> list = boardService.selectAll();
		model.addAttribute("list",list);
		System.out.println("list개수 : "+list.size());
		return "blist";
	}

}
