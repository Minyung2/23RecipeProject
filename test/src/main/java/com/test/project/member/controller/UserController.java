package com.test.project.member.controller;

import com.test.project.member.dto.JoinDto;
import com.test.project.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("joinDto",new JoinDto());
        return "member/join";
    }

    @GetMapping("/login")
    public String login() { return "member/login";}


    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/login";
    }





}
