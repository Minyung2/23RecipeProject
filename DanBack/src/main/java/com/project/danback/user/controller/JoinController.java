package com.project.danback.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.danback.user.service.UserService;

@RestController
public class JoinController {
	@Autowired
	UserService dao;

	@PostMapping("idCheck")
	public Map<String, Object> idCheck(@RequestBody String user_id) {
		Pattern pattern = Pattern.compile("^[a-z0-9]{5,15}$");
		Matcher matcher = pattern.matcher(user_id);
		int idDuplicated = dao.idCheck(user_id);
		Map<String, Object> response = new HashMap<>();
		if (idDuplicated >= 1) {
			response.put("idValid", "이미 사용중인 아이디입니다.");
		} else if (!matcher.matches()) {
			response.put("idValid", "유효한 아이디를 입력해주세요.");
		} else {
			response.put("idValid", "사용 가능한 아이디 입니다.");
		}
		return response;
	}

	@PostMapping("pwCheck")
	public Map<String, Object> pwCheck(@RequestBody String user_pw) {
		Map<String, Object> response = new HashMap<>();
		Pattern pattern = Pattern
				.compile("^(?=.*[A-Za-z0-9])(?=.*[!@#$%^&*()_+,.?/:;~])[A-Za-z0-9!@#$%^&*()_+,.?/:;~]{8,16}$");
		Matcher matcher = pattern.matcher(user_pw);
		if (!matcher.matches()) {
			response.put("pwValid", "유효한 패스워드를 입력하세요");
		} else {
			response.put("pwValid", "사용 가능한 패스워드 입니다.");
		}
		return response;
	}

	@PostMapping("nameCheck")
	public Map<String, Object> nameCheck(@RequestBody String user_name) {
		Map<String, Object> response = new HashMap<>();
		Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z가-힣])[a-zA-Z가-힣]{2,20}$");
		Matcher matcher = pattern.matcher(user_name);
		if (!matcher.matches()) {
			response.put("nameValid", "유효한 이름을 입력하세요");
		} else {
			response.put("nameValid", "사용 가능한 이름입니다.");
		}
		return response;
	}
	
	@PostMapping("nicknameCheck")
	public Map<String, Object> nicknameCheck(@RequestBody String user_nickname) {
		Map<String, Object> response = new HashMap<>();
		Pattern pattern = Pattern.compile("^[a-zA-Z가-힣0-9\\s]{2,20}$");
		Matcher matcher = pattern.matcher(user_nickname);
		int nickDulicated = dao.nicknameCheck(user_nickname);
		if(nickDulicated>=1) {
			response.put("nicknameValid", "이미 사용중인 닉네임입니다.");
		} else if (!matcher.matches()) {
			response.put("nicknameValid", "유효한 닉네임을 입력해주세요.");
		} else {
			response.put("nicknameValid", "사용 가능한 닉네임입니다.");
		}
		return response;
	}
	
	
	@PostMapping("phoneCheck")
	public Map<String, Object> phoneCheck(@RequestBody String user_phone) {
		Map<String, Object> response = new HashMap<>();
		user_phone = user_phone.replaceAll("-", "");
		Pattern pattern = Pattern.compile("^01([016789])(\\d{3,4})(\\d{4})$");
		Matcher matcher = pattern.matcher(user_phone);
		int phoneDulicated = dao.phoneCheck(user_phone);
		if(phoneDulicated>=1) {
			response.put("phoneValid", "이미 사용중인 번호입니다.");
		} else if (!matcher.matches()) {
			response.put("phoneValid", "유효한 번호을 입력해주세요.");
		} else {
			response.put("phoneValid", "사용 가능한 번호입니다.");
		}
		return response;
	}
	

}
