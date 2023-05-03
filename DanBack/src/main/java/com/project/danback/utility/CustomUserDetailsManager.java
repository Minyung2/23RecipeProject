package com.project.danback.utility;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.danback.user.dao.UserMapper;
import com.project.danback.user.dto.UsersDto;


public class CustomUserDetailsManager implements UserDetailsService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomUserDetailsManager.class);
	  	@Autowired
	    private UserMapper userMapper;
	  	
	    @Override
	    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
	    	logger.info("user_id: {}", user_id);
	        UsersDto user = userMapper.findUserByUsername(user_id);
	        logger.info("User found: {}", user);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with username: " + user_id);
	        }
	        String authority = userMapper.findAuthorityByUsername(user_id);
	        Collection<GrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority(authority));
	        user.setAuthorities(authorities);
	        
	        return user;
	    }
	    
	  

}
