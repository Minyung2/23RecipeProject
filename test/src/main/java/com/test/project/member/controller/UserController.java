package com.test.project.member.controller;

import com.test.project.member.dto.JoinDto;
import com.test.project.member.entity.User;
import com.test.project.member.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String joinForm(Model model, HttpSession session) {
        if (session != null) {
            JoinDto joinDto = new JoinDto();
            joinDto.setName((String) session.getAttribute("name"));
            joinDto.setEmail((String) session.getAttribute("email"));
            joinDto.setAgeRange((String) session.getAttribute("ageRange"));
            joinDto.setMobile((String) session.getAttribute("mobile"));
            joinDto.setGender((String) session.getAttribute("gender"));
            System.out.println(joinDto);
            model.addAttribute("joinDto", joinDto);
        } else {
            model.addAttribute("joinDto", new JoinDto());
        }

        return "member/join";
    }

    @PostMapping("/join")
    public  String joinSubmit(@Valid JoinDto joinDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/join";
        }
        try {
            User member = User.saveUser(joinDto, passwordEncoder);
            userService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/join";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() { return "member/login";}


    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/login";
    }

}
