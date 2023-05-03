package com.project.danback.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.danback.user.dao.UserMapper;
import com.project.danback.user.dto.UsersDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper dao;
	
	@Override
	public void joinUser(UsersDto dto) {
		dao.joinUser(dto);		
	}

	@Override
	public int idCheck(String user_id) {
		return dao.idCheck(user_id);
	}

	@Override
	public int nicknameCheck(String user_nickname) {
		return dao.nicknameCheck(user_nickname);
	}

	@Override
	public int phoneCheck(String user_phone) {
		return dao.phoneCheck(user_phone);
	}


}
