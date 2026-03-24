package com.java.service;

import java.util.Map;

import com.java.dto.BoardApiDto;
import com.java.dto.BoardDto;

public interface CustomerService {

	//01.게시판리스트
	Map<String, Object> findAll(int page, String category, String search);
    //02.게시글상세보기
	BoardDto findById(Integer bno);
	//03.게시글쓰기 저장
	BoardDto save(BoardApiDto boardApiDto);

}
