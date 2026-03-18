package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {
	
	private String tid; //결제 고유번호
	private String next_redirect_pc_url; //QR화면 - 카카오톡으로 결제 요청 메시지(TMS)를 보내기 위한 사용자 정보 입력 화면

}
