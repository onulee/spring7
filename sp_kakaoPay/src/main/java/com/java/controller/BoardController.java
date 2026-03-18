package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.ApproveResponseDto;
import com.java.dto.OrderDto;
import com.java.dto.ResponseDto;
import com.java.service.BoardService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	@Autowired HttpSession session;
	
//	@Autowired private BoardService boardService;
	private final BoardService boardService;
	
	@GetMapping("/board/board")
	public String board() {
		return "board";
	}
	
	//1.구매준비 페이지
	@ResponseBody
	@PostMapping("/board/orderPay")
	public ResponseDto orderPay(OrderDto orderDto) {
		System.out.println("controller orderPay : "+orderDto.getName());
		// 카카오페이로 정보전달
		ResponseDto responseDto = boardService.orderPay(orderDto);
		session.setAttribute("tid", responseDto.getTid()); //session저장
		session.setAttribute("user_id", orderDto.getId()); //session저장
		System.out.println("카카오QR결제화면 url : "+responseDto.getNext_redirect_pc_url());
		return responseDto;
	}
	
	//카카오결제성공후 이동
	@GetMapping("/board/completed")
	public String completed(@RequestParam("pg_token") String pg_token, Model model) {
		String tid = (String) session.getAttribute("tid");
		ApproveResponseDto approveResponseDto = boardService.approvePay(tid,pg_token);
		System.out.println("completed 결제최종승인시간 : "+approveResponseDto.getApproved_at());
		System.out.println("completed approveResponseDto 모든결제내용 : "+approveResponseDto);
		model.addAttribute("approveResponseDto",approveResponseDto);
		return "/success";
	}
	//카카오결제취소후 이동
	@GetMapping("/board/cancel")
	public String cancel() {
		return "cancel";
	}
	//카카오결제실패후 이동
	@GetMapping("/board/fail")
	public String fail() {
		return "fail";
	}
	
	

}
