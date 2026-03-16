package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.dto.CustomUserDetails;
import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto memberDto = memberRepository.findByUsername(username);
		if(memberDto != null) {
			return new CustomUserDetails(memberDto);
		}
		return null;
	}

}
