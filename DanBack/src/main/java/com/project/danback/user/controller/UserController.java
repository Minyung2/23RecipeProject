package com.project.danback.user.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.danback.user.dto.UsersDto;
import com.project.danback.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService dao;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/join")
	public String join() {
		return "User/join";
	}

	@RequestMapping("/login")
	public String login() {
		return "User/login";
	}
	

	@PostMapping("/joinProcess")
	public String joinProcess(UsersDto user) {
		String inputPassword = user.getUser_pw();
		System.out.println(user.getUser_pw());
		String encryptedPassword = bCryptPasswordEncoder.encode(inputPassword);
		user.setUser_pw(encryptedPassword);
		user.setAuthority("ROLE_USER");
		dao.joinUser(user);
		return "redirect:/";
	}
	
	
	
}
