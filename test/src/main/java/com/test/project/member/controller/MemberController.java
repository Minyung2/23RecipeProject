package com.test.project.member.controller;

import com.test.project.member.dto.JoinDto;
import com.test.project.member.entity.Member;
import com.test.project.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("joinDto",new JoinDto());
        return "member/join";
    }
    @PostMapping(value = "/join")
    public String newMember(@Valid JoinDto memberFormDto
            , BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/join";
        }
        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login() { return "member/login";}

  /*  @GetMapping("login/oauth/kakao")
    public String kakaoLogin(String token){

        return "redirect:/";
    }*/
    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/login";
    }
}
