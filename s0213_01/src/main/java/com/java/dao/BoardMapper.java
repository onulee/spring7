package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//게시글 전체가져오기 - boardMapper.xml
	List<BoardDto> selectAll();

}
