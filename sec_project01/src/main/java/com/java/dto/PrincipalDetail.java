package com.java.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalDetail implements UserDetails {

	private MemberDto memberDto;
	
	 public PrincipalDetail(MemberDto member) {
	        this.memberDto = memberDto;
	    }
	
	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {

	        Collection<GrantedAuthority> collect = new ArrayList<>();

	        collect.add(() -> {
	            return memberDto.getRole();
	        });

	        return collect;
	    }

	 @Override
	    public String getPassword() {
	        return memberDto.getPassword();
	    }

	@Override
    public String getUsername() {
        return memberDto.getUsername();
    }
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
