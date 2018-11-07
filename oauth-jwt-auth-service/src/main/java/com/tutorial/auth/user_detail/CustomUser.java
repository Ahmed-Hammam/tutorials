package com.tutorial.auth.user_detail;

import org.springframework.security.core.userdetails.User;

import com.tutorial.auth.entity.UserEntity;

public class CustomUser extends User {
	private static final long serialVersionUID = 1L;

	public CustomUser(UserEntity user) {
		super(user.getUsername(), user.getPassword(), user.getGrantedAuthoritiesList());
	}

}
