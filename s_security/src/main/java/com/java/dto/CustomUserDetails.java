package com.java.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// security는 타입 : UserDetails 타입
public class CustomUserDetails implements UserDetails {

	//MemberDto객체를 가져와서 CustomUserDetails에 넣어줌.
	private MemberDto memberDto;
	
	//생성자
	public CustomUserDetails(MemberDto memberDto) {
		this.memberDto = memberDto;
	}
	
	@Override //권한타입 - Collection
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		//security는 권한접두어로 ROLE_
		collection.add(()-> {return "ROLE_"+memberDto.getRole();} );
		return collection;
	}

	@Override //로그인에 필요한 패스워드
	public @Nullable String getPassword() {
		return memberDto.getPassword();
	}

	@Override //로그인에 필요한 아이디
	public String getUsername() {
		return memberDto.getUsername();
	}

}
