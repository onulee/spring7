package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MemberMapper;
import com.java.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberMapper memberMapper;
	
	@Override //로그인 확인
	public MemberDto selectLogin(MemberDto mdto) {
		MemberDto memberDto = memberMapper.selectLogin(mdto);
		return memberDto;
	}

	@Override //회원가입 확인
	public void insertMember(MemberDto mdto) {
		int result = memberMapper.insertMember(mdto);
		System.out.println("result : "+result);
	}

}
