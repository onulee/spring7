package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApproveResponseDto {
	private String aid;  //요청 고유번호
	private String tid;  //결제 고유번호
	private String cid;  //가맹점 코드
	private String partner_order_id;  //가맹점 주문번호
	private String partner_user_id;   //가맹점 회원 id
	private String payment_method_type; //결제수단
	private String item_name;  //상품명
	private String item_code;  //상품코드
	private String quantity;   //상품수량
	private String created_at; //결제 준비요청 시간
	private String approved_at; //결제승인시간 
	private String payload;     //결제승인요청에 대한 저장값
}
