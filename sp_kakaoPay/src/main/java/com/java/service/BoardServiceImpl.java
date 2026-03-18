package com.java.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.java.dto.ApproveResponseDto;
import com.java.dto.OrderDto;
import com.java.dto.ResponseDto;

import jakarta.servlet.http.HttpSession;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired HttpSession session;
	
	//결제화면 QR요청
	@Override
	public ResponseDto orderPay(OrderDto orderDto) {
		// 카카오페이쪽으로 QR코드결제창을 요청
		String id = orderDto.getId();
		String name = orderDto.getName();
		int totalPrice = orderDto.getTotalPrice();
		Map<String, String> map = new HashMap<>();
		map.put("cid","TC0ONETIME"); //가맹점 코드
		map.put("partner_order_id","1234567890"); //주문번호
		map.put("partner_user_id",id); //회원아이디
		map.put("item_name",name); //상품명
		map.put("quantity","1"); //상품수량
		map.put("total_amount",""+totalPrice); //상품총액
		map.put("tax_free_amount","0"); //상품비과세 금액
		map.put("approval_url","http://localhost:8181/board/completed"); //결제성공url
		map.put("cancel_url","http://localhost:8181/board/cancel"); //결제취소url
		map.put("fail_url","http://localhost:8181/board/fail"); //결제실패url
		
		System.out.println("serviceImpl map : "+map);
		
		// 카카오페이로 전송 - url전송
		WebClient webClient = WebClient.create();
		ResponseDto responseDto = webClient.post()
				.uri("https://open-api.kakaopay.com/online/v1/payment/ready")
				.header("Authorization", "SECRET_KEY DEV5AF16E011E90E89F908638FCB0AE957934741")
				.header("Content-Type", "application/json")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(map)
				.retrieve()
				.bodyToMono(ResponseDto.class)
				.block();
		
		return responseDto;
	}

	//결제승인 최종요청
	@Override
	public ApproveResponseDto approvePay(String tid, String pg_token) {
		String user_id = (String) session.getAttribute("user_id");
		Map<String, String> map = new HashMap<>();
		map.put("cid","TC0ONETIME"); //가맹점 코드
		map.put("tid",tid); //가맹점 코드
		map.put("partner_order_id","1234567890"); //주문번호
		map.put("partner_user_id",user_id); //회원아이디
		map.put("pg_token",pg_token); //상품명
		
		// 카카오페이로 결제최종승인 전송 - url전송
		WebClient webClient = WebClient.create();
		ApproveResponseDto approveResponseDto = webClient.post()
				.uri("https://open-api.kakaopay.com/online/v1/payment/approve")
				.header("Authorization", "SECRET_KEY DEV5AF16E011E90E89F908638FCB0AE957934741")
				.header("Content-Type", "application/json")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(map)
				.retrieve()
				.bodyToMono(ApproveResponseDto.class)
				.block();
		return approveResponseDto;
	}

}
