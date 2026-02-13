package com.java.controller;

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
public class MemberController {

	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	
	
	@GetMapping("/member/mupdate")
	public String mupdate(Model model) {
		String id = (String)session.getAttribute("session_id");
		MemberDto memberDto = memberService.selectOne(id);
		model.addAttribute("member",memberDto);
		System.out.println("mupdate id : "+id);
		return "member/mupdate";
	}
	
	
	//회원가입03
	@PostMapping("/member/join03")
	public String join03(MemberDto mdto,
			String phone1,String phone2,String phone3,
			String email1,String email2,Model model) {
		String phone = phone1+"-"+phone2+"-"+phone3;
		mdto.setPhone(phone);
		String email = email1+"@"+email2;
		mdto.setEmail(email);
		System.out.println("join03 Controller : "+mdto.getId()+","+mdto.getPhone());
		
		//db저장 : controller - service - serviceImpl - dao - xml
		memberService.insertMember(mdto);
		
		model.addAttribute("name",mdto.getName());
		return "member/join03";
	}//
	//회원가입02
	@GetMapping("/member/join02")
	public String join02() {
		return "member/join02";
	}//
	
	//회원가입01
	@GetMapping("/member/join01")
	public String join01() {
		return "member/join01";
	}//
	
	//로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}//
	
	//로그인페이지
	@GetMapping("/member/login")
	public String login(String flag,Model model) {
		model.addAttribute("flag",flag);
		return "member/login";
	}//
	
	//로그인확인
	@PostMapping("/member/login")
	public String login(MemberDto mdto,String saveId,
			HttpServletResponse response, Model model ) {
		System.out.println("controller id,pw : "+mdto.getId());
		//saveId 쿠키저장 - id:aaa
		Cookie cookie = new Cookie("cookie_id",mdto.getId());
		if(saveId != null) cookie.setMaxAge(60*60*24*30); //60초*60분*24시간*30일
		else cookie.setMaxAge(0);
		response.addCookie(cookie);
		//로그인확인
		MemberDto memberDto = memberService.selectLogin(mdto);
		if(memberDto != null) {
			System.out.println("memberDto name : "+memberDto.getName());
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			return "redirect:/?flag=1";
			
		}else {
			System.out.println("id,pw존재하지 않음.");
			return "redirect:/member/login?flag=2";
		}
		
	}//login
	
	
}//class
