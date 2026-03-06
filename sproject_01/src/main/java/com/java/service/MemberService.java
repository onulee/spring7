package com.java.service;

import com.java.dto.MemberDto;

public interface MemberService {

	//02.로그인확인
	MemberDto findByIdAndPw(MemberDto mdto);
	//07.회원가입 step03저장
	void save(MemberDto mdto);

}
