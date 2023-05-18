package com.test.project.member.dto;

import com.test.project.member.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JoinLocationDto {
    private String latitude;
    private String longitude;
    private String address;
    private User user;

}
