package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MemberMapper;
import com.java.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberMapper memberMapper;
	
	@Override //로그인
	public MemberDto selectLogin(MemberDto mdto) {
		MemberDto memberDto = memberMapper.selectLogin(mdto);
		return memberDto;
	}

	@Override //회원가입
	public void insertMember(MemberDto mdto) {
		memberMapper.insertMember(mdto);
	}

	@Override //회원검색
	public MemberDto selectOne(String id) {
		MemberDto memberDto = memberMapper.selectOne(id);
		return memberDto;
	}

}
