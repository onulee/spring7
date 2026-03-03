package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.CommentDto;

import jakarta.servlet.http.HttpSession;

//@RestController
@Controller
public class CommentController {
	
	@Autowired HttpSession session;
	
	@ResponseBody
	@PostMapping("/comment/save")
	public String commentSave(CommentDto cdto,
			@RequestParam(name="bno",required = false) int bno
			) {
		//cno:자동부여,ccontent,bno,id,cdate:자동부여
		System.out.println("내용 : "+cdto.getCcontent());
		System.out.println("게시글번호 : "+bno); //boardDto
		System.out.println("id : "+session.getAttribute("session_id")); //memberDto
		
		return "성공";
	}

}
