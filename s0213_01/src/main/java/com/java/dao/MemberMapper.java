package com.java.dao;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.MemberDto;

@Mapper  //Mybatis설치
public interface MemberMapper {

	//로그인확인
	MemberDto selectLogin(MemberDto mdto);

	//회원가입 - 성공1, 실패0
	void insertMember(MemberDto mdto);

	//회원검색
	MemberDto selectOne(String id);

}
