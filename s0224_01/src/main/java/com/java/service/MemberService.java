package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {

	//로그인확인
	MemberDto findByIdAndPw(MemberDto mdto);

	//전체회원리스트
	List<MemberDto> findAll();

}
