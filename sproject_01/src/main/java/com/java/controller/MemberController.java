package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	private final HttpSession session;

	//01.로그인페이지 연결
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	} 
	//02.로그인확인
	@PostMapping("/member/login")
	public String login(MemberDto mdto, 
			@RequestParam(name="idsave",required = false) String idsave,
			HttpServletResponse response, Model model) {
		
		// 쿠키저장 - 입력된 아이디를 저장
		Cookie cookie = new Cookie("cook_id", mdto.getId());
		cookie.setPath("/");
		if(idsave != null) cookie.setMaxAge(60*60*24*30); //30일동안 저장
		else cookie.setMaxAge(0); //30일동안 저장
		response.addCookie(cookie);
		
		// 로그인확인
		MemberDto memberDto = memberService.findByIdAndPw(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			model.addAttribute("flag",1);
		}else {
			model.addAttribute("flag",-1);
		}
		return "member/login";
	} //
	
	//02-02. 세션 확인 - api
	@ResponseBody
	@GetMapping("/member/session")
	public String session(){
	    return (String) session.getAttribute("session_id");
	}
	
	//02-01.로그인확인 - api
	@ResponseBody
	@PostMapping("/member/loginApi")
	public String loginApi(@RequestBody MemberDto mdto, 
			@RequestParam(name="idsave",required = false) String idsave,
			HttpServletResponse response, Model model) {
		
		// 쿠키저장 - 입력된 아이디를 저장
		Cookie cookie = new Cookie("cook_id", mdto.getId());
		cookie.setPath("/");
		if(idsave != null) cookie.setMaxAge(60*60*24*30); //30일동안 저장
		else cookie.setMaxAge(0); //30일동안 저장
		response.addCookie(cookie);
		
		// 로그인확인
		String session_id = null;
		MemberDto memberDto = memberService.findByIdAndPw(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			session_id = memberDto.getId();
		}
		return session_id;
	} //
	
	//03-01.로그아웃 : react api
	@ResponseBody
	@GetMapping("/member/logoutApi")
	public String logoutApi() {
		session.invalidate();
		return "success";
	} 
	
	//03.로그아웃
	@GetMapping("/member/logout")
	public String logout(Model model) {
		session.invalidate();
		model.addAttribute("flag",0);
		return "member/login";
	} 
	
	//04.회원가입페이지 step01연결
	@GetMapping("/member/step01")
	public String step01() {
		return "/member/step01";
	}
	
	//05.회원가입페이지 step02연결
	@GetMapping("/member/step02")
	public String step02() {
		return "/member/step02";
	}
	
	//06.회원가입페이지 step03연결
	@GetMapping("/member/step03")
	public String step03() {
		return "/member/step03";
	}
	
	//07.회원가입 step03저장
	@PostMapping("/member/step03")
	public String step03(MemberDto mdto,
			@RequestParam(name="phone1",required = false) String phone1,
			@RequestParam(name="phone2",required = false) String phone2,
			@RequestParam(name="phone3",required = false) String phone3,
			@RequestParam(name="email1",required = false) String email1,
			@RequestParam(name="email2",required = false) String email2,
			Model model
			) {
		String phone = phone1+"-"+phone2+"-"+phone3;
		String email = email1 +"@"+email2;
		mdto.setPhone(phone);
		mdto.setEmail(email);
		//service 전달
		memberService.save(mdto);
		return "redirect:/member/step04?id="+mdto.getId();
	}
	
	//07-01.아이디 중복확인
	@ResponseBody
	@GetMapping("/member/idCheck")
	public String idCheck(MemberDto mdto) {
		String id = mdto.getId();
		System.out.println("id : "+id);
		//
		String temp = "able";
		MemberDto memberDto = memberService.findById(id);
		if(memberDto!=null) temp = "unable"; 
		return temp;
	}
	
	//08.회원가입페이지 step04
	@GetMapping("/member/step04")
	public String step04( Model model,
			@RequestParam(name="id",required = false) String id	) {
		model.addAttribute("id",id);
		return "member/step04";
	}
	
	
	
	
	
}
