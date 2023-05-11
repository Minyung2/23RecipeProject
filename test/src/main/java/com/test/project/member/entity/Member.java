package com.test.project.member.entity;


import com.test.project.member.constant.Role;
import com.test.project.member.dto.JoinDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member  {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique=true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(JoinDto joinDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(joinDto.getName());
        member.setEmail(joinDto.getEmail());
        String password=passwordEncoder.encode(joinDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }
}