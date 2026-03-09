package com.java.service;

import java.util.List;

import com.java.dto.SaleDto;

public interface ApiService {

	//01.이메일 발송
	String emailSend(String email);
	//03-02. 그래프 데이터 가져오기
	List<SaleDto> findByIdContaining(String syearMonth);

}
