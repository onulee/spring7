package com.java.service;

import com.java.dto.MemberDto;

public interface MemberService {

	//로그인확인
	MemberDto selectLogin(MemberDto mdto);

	//회원가입
	void insertMember(MemberDto mdto);

	//회원검색
	MemberDto selectOne(String id);

	//회원정보수정 확인
	void updateMember(MemberDto mdto);

}
