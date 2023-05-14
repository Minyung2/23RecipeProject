package com.test.project.member.dto;

import com.test.project.member.constant.OAuthProvider;
import com.test.project.member.constant.Role;
import lombok.Data;
@Data
public class MemberDto {
    private Long idx;
    private String email;
    private String password;
    private String name;
    private Role role;
    private OAuthProvider OAuthProvider;
}
