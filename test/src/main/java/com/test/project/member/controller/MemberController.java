package com.test.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {



    @GetMapping("/join")
    public String joinView(){
        return "member/join";
    }
}