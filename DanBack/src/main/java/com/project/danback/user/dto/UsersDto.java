package com.project.danback.user.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class UsersDto implements UserDetails {

	public UsersDto() {
		super();
	}

	private String user_idx;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_nickname;
	private String user_email;
	private String user_address;
	private String user_phone;
	private int enabled;
	private Collection<? extends GrantedAuthority> authorities;
	


	public String getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public void setEnabled(int enabled) {
	    this.enabled = enabled;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
	     return this.authorities;
	}
	
	public String getAuthority() {
	    if (authorities != null && !authorities.isEmpty()) {
	        return authorities.iterator().next().getAuthority();
	    }
	    return null;
	}
	@Override
	public String getPassword() {
		return user_pw;
	}
	
	@Override
	public String getUsername() {
		return user_id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled == 1;
	}

}
