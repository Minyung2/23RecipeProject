package com.project.danback.utility;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetAuthorityUtility {
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 현재 인증된 사용자의 권한 정보를 가져오는 메서드

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getAuthorities() != null) {
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
			}
		}

		return authorities;
	}
}
