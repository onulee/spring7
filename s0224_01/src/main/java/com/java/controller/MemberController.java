package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller      //jsp페이지 리턴
public class MemberController {

	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	
	@GetMapping("/member/delete") //회원삭제
	public String delete(MemberDto mdto) {
		System.out.println("delete mdto : "+mdto.getId());
		//db삭제 -> service전달
		memberService.deleteById(mdto);
		return "redirect:/";   
	}
	
	@GetMapping("/member/memberShip") //회원가입페이지 호출
	public String memberShip() {
		return "memberShip";   
	}
	
	@PostMapping("/member/memberShip") //회원가입페이지 호출
	public String memberShip(MemberDto mdto,
		@RequestParam(name="phone1",required = false) String phone1,
		@RequestParam(name="phone2",required = false) String phone2,
		@RequestParam(name="phone3",required = false) String phone3
			) {
		System.out.println("controller mdto : "+mdto.getId());
		String phone = String.format("%s-%s-%s", phone1,phone2,phone3);
		System.out.println("phone : "+phone);
		mdto.setPhone(phone);
		//db저장 - service전달
		memberService.save(mdto);
		
		return "memberShip";   
	}
	
	@GetMapping("/member/mlist") //전체회원리스트
	public String mlist(Model model) {
		List<MemberDto> list = memberService.findAll();
		System.out.println("controller mlist : "+list.size());
		model.addAttribute("list",list);
		return "mlist";   
	}
	
	@GetMapping("/member/login") //로그인페이지 호출
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
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			return "redirect:/";   
		}else {
			System.out.println("아이디 또는 패스워드가 일치 하지 않습니다.");
			return "login";   
		}
	}
	
	@GetMapping("/member/logout") //로그인확인
	public String logout(){
		session.invalidate(); //섹션모두삭제
		return "redirect:/";
	}
	
	
	
}
