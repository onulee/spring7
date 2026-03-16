package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberRepository memberRepository;
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	//회원가입저장
	@Transactional
	@Override
	public void save(MemberDto mdto) {
		mdto.setRole("ROLE_ADMIN");
		mdto.setPassword(bCryptPasswordEncoder.encode(mdto.getPassword()));
		memberRepository.save(mdto);
	}//

	
	@Override //로그인확인
	public MemberDto findByIdAndPw(MemberDto mdto) {
		MemberDto memberDto = null;
		return memberDto;
	}//로그인확인

	
	

	
	

}
