package com.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.MemberDto;

// JpaRepository<entity객체,primary Key타입>
// find,save,delete - CRUD작업에 필요한 기본 메소드가 만들어져 있음.
public interface MemberRepository extends JpaRepository<MemberDto, String> {

	//로그인확인 : 1개 select일때 타입 -> Optional
	Optional<MemberDto> findByIdAndPw(String id, String pw);

}
