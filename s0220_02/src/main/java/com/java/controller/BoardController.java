package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired BoardService boardService;
	
	
	@GetMapping("/board/blist")
	public String blist(Model model) {
		int page = 1;
		Map<String, Object> map = boardService.selectAll(page);
		model.addAttribute("map",map); //list,count,page,maxPage,startPage,endPage
		return "blist";
	}
	
	@GetMapping("/board/bwrite") //글쓰기 페이지열기
	public String bwrite() {
		return "bwrite";
	}
	
	@PostMapping("/board/bwrite") //글쓰기 저장
	public String bwrite(BoardDto bdto, 
			@RequestPart("file") MultipartFile files,
			Model model) {
		if(!files.isEmpty()) {
			//원본이름 500.jpg -> 500.jpg
			String originFileName = files.getOriginalFilename();
			//long time = System.currentTimeMillis();
			UUID uuid = UUID.randomUUID(); //랜덤문자를 리턴
			System.out.println(uuid);
			String uploadFileName = String.format("%s_%s", uuid,originFileName);
			bdto.setBfile(uploadFileName);
			System.out.println("실제파일이름 : "+uploadFileName);
			System.out.println("btitle : "+bdto.getBtitle());
			System.out.println("bcontent : "+bdto.getBcontent());
			//파일위치
			String fileUrl = "c:/upload/";
			File f = new File(fileUrl+uploadFileName);
			try {
				files.transferTo(f);
			} catch (Exception e) {e.printStackTrace();}
			// dto bfile에 실제 저장되는 파일이름 추가
		}
		
		// service호출
		boardService.insertBoard(bdto);
		return "redirect:/board/blist";
	}
	
	@GetMapping("/board/bview") //게시글 1개 상세보기
	public String bview(BoardDto bdto,Model model) {
		BoardDto boardDto = boardService.selectOne(bdto);
		model.addAttribute("board",boardDto);
		return "bview";
	}

}
