package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	//@Component @Controller @Service @Repository @Configuration @Bean
	//@Mapper - Mybatis 설치하면 생김.
	@Autowired MemberMapper memberMapper;
	
	@Override
	public Member selectIdAndPw(Member member) {
		String id = member.getId();
		String pw = member.getPw();
		Member m = memberMapper.selectIdAndPw(id,pw);
		return m;
	}

	

	

}
