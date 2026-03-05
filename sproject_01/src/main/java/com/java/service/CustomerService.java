package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface CustomerService {

	//01.게시판리스트
	List<BoardDto> findAll();

}
