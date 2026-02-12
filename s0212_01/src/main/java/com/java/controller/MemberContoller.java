package com.java.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberContoller {
	
	@Autowired HttpSession session;
	@Autowired MemberService memberService;
	
	// 회원가입01
	@GetMapping("/member/join01")
	public String join01() {
		return "join01";
	}
	// 회원가입02
	@GetMapping("/member/join02")
	public String join02() {
		return "join02";
	}
	// 회원가입03 확인 - phone1,2,3 ,email1,2
 	@PostMapping("/member/join03")
	public String join03(MemberDto mdto,
			String phone1,String phone2,String phone3,
			String email1,String email2	) {
 		//넘어온 데이터 확인
 		String phone = String.format("%s-%s-%s", phone1,phone2,phone3);
 		String email = String.format("%s@%s", email1,email2);
 		mdto.setPhone(phone);
 		mdto.setEmail(email);
 		
 		//db저장
 		memberService.insertMember(mdto);
 		
		return "join03";
	}
	
	// 로그아웃 확인
	@GetMapping("/member/logout")
	public String logout(String flag,Model model) {
		model.addAttribute("flag",flag);
		session.invalidate();
		return "redirect:/?flag=0";
	}
	
	// 로그인페이지 호출
	@GetMapping("/member/login")
	public String login(String flag,Model model) {
		model.addAttribute("flag",flag);
		return "login";
	}
	
	// 로그인확인 - Session,Cookie
	@PostMapping("/member/login")
	public String login(MemberDto mdto,String saveId,
			HttpServletResponse response) {
		System.out.println("id,pw : "+mdto.getId()+","+mdto.getPw());
		System.out.println("saveId : "+saveId);
		//아이디저장 - cookie
		Cookie cookie = new Cookie("cook_id", mdto.getId());
		if(saveId !=null) {
			cookie.setMaxAge(60*60*24*30); //60초X60분X24시간X30일
		}else {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		
		//로그인 1개 객체가져오기
		MemberDto memberDto = memberService.selectLogin(mdto);
		if(memberDto != null) {
			System.out.println("id,pw 존재함");
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			
			return "redirect:/?flag=1";
		}else {
			System.out.println("id,pw 일치하지 않음.");
			return "redirect:/member/login?flag=2";
		}
	}
	
	

}
