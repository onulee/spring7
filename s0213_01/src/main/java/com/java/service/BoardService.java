package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BoardService {

	//게시글 전체가져오기
	List<BoardDto> selectAll();

}
