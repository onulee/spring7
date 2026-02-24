package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

@Controller      //jsp페이지 리턴
public class MemberController {

	@Autowired MemberService memberService;
	
	@GetMapping("/member/mlist") //전체회원리스트
	public String mlist(Model model) {
		List<MemberDto> list = memberService.findAll();
		System.out.println("controller mlist : "+list.size());
		model.addAttribute("list",list);
		return "mlist";   
	}
	
	@GetMapping("/member/login") //로그인페이지 연결
	public String login() {
		return "login";   
	}
	
	@PostMapping("/member/login") //로그인확인
	public String dologin(MemberDto mdto){
		System.out.println("controller mdto : "+mdto.getId()+","+mdto.getPw());
		
		//service에서 1명의 데이터 가져오기
		MemberDto memberDto = memberService.findByIdAndPw(mdto);
//		System.out.println(memberDto.getId()); //null일경우 get을 할수 없음.
		if(memberDto!=null) {
			System.out.println("로그인이 되었습니다.");
			// 섹션을 저장해서 메인으로 리턴
		}else {
			System.out.println("아이디 또는 패스워드가 일치 하지 않습니다.");
			// 로그인페이지로 리턴
		}
		return "login";   
	}
	
	
	
}
