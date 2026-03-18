package com.java.service;

import com.java.dto.ApproveResponseDto;
import com.java.dto.OrderDto;
import com.java.dto.ResponseDto;

public interface BoardService {

	//결제화면 QR요청
	ResponseDto orderPay(OrderDto orderDto);

	//결제승인 최종요청
	ApproveResponseDto approvePay(String tid, String pg_token);

}
