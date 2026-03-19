package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	//@Autowired private MemberRepository memberRepository;
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//회원가입 저장
	@Override
	public void save(MemberDto mdto) {
		//권한추가
		mdto.setRole("ROLE_ADMIN");
		//시큐리티 비밀번호 암호화
		mdto.setPw(bCryptPasswordEncoder.encode(mdto.getPw()));
		System.out.println("비밀번호 암호화 : "+ mdto.getPw());
		
		memberRepository.save(mdto);
		
	}

}
