package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.MemberDto;

public interface MemberRepository extends JpaRepository<MemberDto, String> {

	//02.로그인 확인
	MemberDto findByIdAndPw(String id, String pw);

}
