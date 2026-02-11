package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {

	// 회원 전체리스트
	List<MemberDto> selectAll();

}
