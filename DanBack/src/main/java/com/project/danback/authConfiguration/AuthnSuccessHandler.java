package com.project.danback.authConfiguration;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.project.danback.utility.GetAuthorityUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class AuthnSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	GetAuthorityUtility authorities;

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String target = "/";

		Collection<? extends GrantedAuthority> authList = authorities.getAuthorities();
		if (authList.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
			target = "/";
		} else if (authList.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
			target = "/";
		}
		response.sendRedirect(target);
	}
}
