package com.java.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.dto.CustomUserDetails;
import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{

	//MemberDB에서 아이디와 패스워드 확인
	private final MemberRepository memberRepository;
	
	@Override //로그인확인메소드
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 로그인은 아이디만 입력하면, 패스워드를 알아서 매칭함.
		MemberDto memberDto = memberRepository.findByUsername(username);
		if(memberDto != null) 
			return new CustomUserDetails(memberDto);	 //username,password,ROLE_USER	
		else 
			return null;
	}

}
