package com.test.project.member.service;

import com.test.project.member.entity.User;
import com.test.project.member.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User saveMember(User user){
        validateDuplicateMember(user);
        return userRepository.save(user);
    }
    private void validateDuplicateMember(User user){
        User findUser = userRepository.findByEmail(user.getEmail()).orElseThrow(EntityNotFoundException::new);
        if(findUser !=null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        if(user ==null){
            throw new UsernameNotFoundException(email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .roles(user.getRole().toString())
                .build();
    }

}
