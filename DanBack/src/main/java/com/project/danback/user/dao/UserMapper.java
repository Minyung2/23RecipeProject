package com.project.danback.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

import com.project.danback.user.dto.UsersDto;

@Mapper
public interface UserMapper {
	void joinUser(UsersDto dto);
	UsersDto findUserByUsername(String user_id);
	String findAuthorityByUsername(String user_id);
	int idCheck(String user_id);
	int nicknameCheck(String user_nickname);
	int phoneCheck(String user_phone);
}
