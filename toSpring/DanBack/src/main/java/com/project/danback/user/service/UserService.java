package com.project.danback.user.service;

import com.project.danback.user.dto.UsersDto;

public interface UserService {
	void joinUser(UsersDto dto);
	int idCheck(String user_id);
	int nicknameCheck(String user_nickname);
	int phoneCheck(String user_phone);
}	
