package com.test.project.member.dto;

import com.test.project.member.constant.OauthProvider;
import com.test.project.member.constant.Role;
import lombok.Data;
import lombok.Getter;

@Data
public class MemberDto {
    private Long idx;
    private String email;
    private String password;
    private String name;
    private Role role;
    private OauthProvider oauthProvider;
}
