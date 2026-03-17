package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.dto.RoleType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

//	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//회원가입
	@Override
	public void save(MemberDto mdto) {
		//mdto.setRole("USER");
		mdto.setRole(RoleType.USER);
		// 1111 -> c125403c-9e8a-4ee7-bb94-fb4b752ba76f 리턴해서 돌려줌.
		mdto.setPassword(bCryptPasswordEncoder.encode(mdto.getPassword()));
		System.out.println("비번 : "+mdto.getPassword());
		System.out.println("객체 : "+mdto);
	}

}
