package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {

	//회원가입 저장
	void save(MemberDto mdto);
	//로그인확인
	MemberDto findByIdAndPw(MemberDto mdto);

}
