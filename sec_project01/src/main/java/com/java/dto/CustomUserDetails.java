package com.java.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
//  isAccountNonExpired()
//	isAccountNonLocked()
//	isCredentialsNonExpired()
//	isEnabled() 4개 : 6.0버전부터 모두 true
	
	//사용하려는 객체
	private MemberDto memberDto;
	
	//생성자구현
	public CustomUserDetails(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	@Override  //사용자권한
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Collection 객체
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			@Override
			public @Nullable String getAuthority() {
				return memberDto.getRole();
			}
		});
		return collection;
	}

	@Override
	public @Nullable String getPassword() {
		return memberDto.getPassword();
	}

	@Override
	public String getUsername() {
		return memberDto.getUsername();
	}

}
