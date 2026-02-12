package com.java.dao;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.MemberDto;

@Mapper    //@Component,@Controller,@Service,@Reqpository,@Configration,@Bean
public interface MemberMapper {

	// 로그인 확인
	MemberDto selectLogin(MemberDto mdto);
	
}
