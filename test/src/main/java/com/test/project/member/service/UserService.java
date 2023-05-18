package com.test.project.member.service;

import com.test.project.member.dto.JoinDto;
import com.test.project.member.entity.User;
import com.test.project.member.entity.UserLocation;
import com.test.project.member.repository.UserLocationRepository;
import com.test.project.member.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLocationRepository userLocationRepository;


    public User saveMember(User user){
        validateDuplicateMember(user);
        return userRepository.save(user);
    }
    public User saveUserAndGetUser(JoinDto joinDto, PasswordEncoder passwordEncoder) {
        User user = User.saveUser(joinDto, passwordEncoder);
        return saveMember(user);
    }

    private void validateDuplicateMember(User user){
        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser !=null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public void saveUserLocation(Long userId, String latitude, String longitude, String address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        UserLocation userLocation = new UserLocation();
        userLocation.setLatitude(latitude);
        userLocation.setLongitude(longitude);
        userLocation.setAddress(address);
        userLocation.setUser(user);

        userLocationRepository.save(userLocation);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user ==null){
            throw new UsernameNotFoundException(email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // 패스워드도 설정해야 합니다
                .roles(user.getRole().toString())
                .build();
    }
}
