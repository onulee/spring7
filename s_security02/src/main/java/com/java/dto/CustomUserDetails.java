package com.java.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	// 로그인필요한 정보 : id,pw,role  name(X),phone(X),email(X)...
	
	private MemberDto memberDto;
	public CustomUserDetails(MemberDto memberDto) {
		this.memberDto = memberDto;
	}
	
	@Override //권한
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 다형성 : 부모의 참조변수 자손의 객체
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(	()-> {return memberDto.getRole();}	);
		return collection;
	}

	@Override //패스워드
	public @Nullable String getPassword() {
		return memberDto.getPw();
	}

	@Override //아이디
	public String getUsername() {
		return memberDto.getId();
	}

}
