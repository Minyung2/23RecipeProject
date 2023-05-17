package com.test.project.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
public class JoinDto {


    @NotEmpty(message = "이메일은 필수 입력입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;
    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;
    @NotEmpty(message = "비밀번호는 필수 입력입니다.")
    @Length(min=8,max=16,message="비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "")
    private String nickName;

    @NotEmpty(message = "")
    private String ageRange;

    @NotEmpty(message = "")
    private String mobile;

    @NotEmpty(message = "")
    private String gender;

}
