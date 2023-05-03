package com.project.danback.authConfiguration;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class AuthnFailureHandler implements AuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String id = request.getParameter("user_id");
		String errorMsg="";
		if(exception instanceof BadCredentialsException) {
			errorMsg="아이디나 비밀번호가 틀립니다. 다시 확인해 주세요.";
		}else  if(exception instanceof InternalAuthenticationServiceException) {
			errorMsg="내부적인 시스템 문제로 인해 인증 요청을 처리할 수 없다.";
		}else if(exception instanceof DisabledException) {
			errorMsg="계정이 비활성화되었습니다.";
		}else if(exception instanceof CredentialsExpiredException) {
			errorMsg="비밀번호 유효기간이 만료되었습니다.";
		}
		
		request.setAttribute("user_id", id);
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("login").forward(request, response);
		
	}
	
	
}
