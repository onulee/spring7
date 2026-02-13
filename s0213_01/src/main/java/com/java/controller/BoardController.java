package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired BoardService boardService;
	
	//게시글 쓰기페이지
	@GetMapping("/board/bwrite") 
	public String bwrite() {
		return "board/bwrite";
	}
	
	//게시글 쓰기저장
	@PostMapping("/board/bwrite") 
	public String bwrite(BoardDto bdto, Model model) {
		System.out.println("bwrite btitle : "+bdto.getBtitle());
		//db저장
		
		
		return "redirect:/board/blist?flag=1";
	}
	
	//게시글 전체가져오기
	@GetMapping("/board/blist") 
	public String blist(Model model) {
		List<BoardDto> list = boardService.selectAll();
		model.addAttribute("list",list);
		System.out.println("list 개수 : "+list.size());
		return "board/blist";
	}

}
