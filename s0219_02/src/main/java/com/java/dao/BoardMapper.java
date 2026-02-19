package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//게시글 전체가져오기
	List<BoardDto> selectAll();
	//게시글 상세보기
	BoardDto selectOne(BoardDto boardDto);
	//게시글수정
	void updateBoard(BoardDto boardDto);

	//게시글쓰기
	void insertBoard(BoardDto boardDto);
	//답변달기 같은bgroup 모두bstep1증가
	void updateReply(BoardDto boardDto);
	//답변달기 저장
	void insertReply(BoardDto boardDto);
	
	//게시글삭제
	void deleteBoard(BoardDto boardDto);


}
