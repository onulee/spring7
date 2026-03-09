package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.java.dto.MemberDto;
import com.java.dto.PrincipalDetail;
import com.java.repository.MemberRepository;

public class PrincipalDetailService implements UserDetailsService {

	@Autowired private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 MemberDto memberDto = memberRepository
	                .findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));
		
		
		 return new PrincipalDetail(memberDto);
		 

		    
	}

}
