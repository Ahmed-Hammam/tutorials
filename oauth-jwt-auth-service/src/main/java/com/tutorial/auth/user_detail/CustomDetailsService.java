package com.tutorial.auth.user_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tutorial.auth.entity.UserEntity;
import com.tutorial.auth.repository.OAuthDao;

@Service
public class CustomDetailsService implements UserDetailsService {
	
	@Autowired
	private OAuthDao oauthDao;

	@Override
	public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserEntity userEntity = null;
		try {
			userEntity = oauthDao.getUserDetails(username);
			CustomUser customUser = new CustomUser(userEntity);
			return customUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}
}