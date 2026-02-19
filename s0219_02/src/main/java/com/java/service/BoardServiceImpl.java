package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardMapper;
import com.java.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardMapper boardMapper;
	
	@Override //게시글 전체가져오기
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardMapper.selectAll();
		return list;
	}//
	
	@Override //게시글 1개 가져오기
	public BoardDto selectOne(BoardDto boardDto) {
		BoardDto bdto = boardMapper.selectOne(boardDto);
		return bdto;
	}
	@Override //게시글수정 및 1개가져오기
	public BoardDto updateBoard(BoardDto boardDto) {
		//게시글수정
		boardMapper.updateBoard(boardDto);
		//게시글 1개가져오기
		BoardDto bdto = boardMapper.selectOne(boardDto);
		return bdto;
	}

	@Override //게시글쓰기
	public void insertBoard(BoardDto boardDto) {
		boardMapper.insertBoard(boardDto);
	}
	@Override //답변달기 저장
	public void InsertReply(BoardDto boardDto) {
		// 같은 bgroup에 있는 현재 게시글보다 더 높은 bstep을 1씩증가
		boardMapper.updateReply(boardDto);
		// 답변달기 저장
		boardMapper.insertReply(boardDto);
		
	}
	
	@Override //게시글삭제
	public void deleteBoard(BoardDto boardDto) {
		boardMapper.deleteBoard(boardDto);
	}

	

	

	

}
