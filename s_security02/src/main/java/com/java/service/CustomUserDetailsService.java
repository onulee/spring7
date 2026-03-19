package com.java.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.dto.CustomUserDetails;
import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

//로그인부분 service
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{

	//jpa활용해서 id,pw 있는지 확인
	private final MemberRepository memberRepository;
	
	//id,pw 로그인 확인 -> id만 입력받고, pw는 알아서 db를 검색해 줌 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//로그인시
		System.out.println("로그인에서 전송하면 넘어온 username id : "+username);
		
		// repository전달
		MemberDto memberDto = memberRepository.findById(username).orElse(null);
		if(memberDto != null) return new CustomUserDetails(memberDto);
		else return null;
	}

}
