package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

// Component,Controller,Service,Repository,Configuration,Bean
@Mapper //myBatis지원하는 어노테이션
public interface BoardDao {

	// 게시글 전체가져오기
	List<BoardDto> selectAll();

}
