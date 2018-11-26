package com.tutorial.auth.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.tutorial.auth.entity.UserEntity;

@Repository
public class OAuthDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserEntity getUserDetails(String username) {
		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";
		List<UserEntity> list = jdbcTemplate.query(userSQLQuery, new String[] { username },
				(ResultSet rs, int rowNum) -> {

					UserEntity user = new UserEntity();
					user.setUsername(username);
					user.setPassword(rs.getString("PASSWORD"));
					return user;
				});
		if (list.size() > 0) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
			grantedAuthoritiesList.add(grantedAuthority);
			list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
			return list.get(0);
		}
		return null;
	}

	@PostConstruct
	public void init() {
		/*String userSQLQuery = "CREATE TABLE USERS (ID INT PRIMARY KEY, USERNAME VARCHAR(45), PASSWORD VARCHAR(60))";
		jdbcTemplate.execute(userSQLQuery);
		userSQLQuery = "INSERT INTO USERS (ID, USERNAME,PASSWORD) VALUES (\n"
				+ "   1, 'tutorialspoint@gmail.com','$2a$08$fL7u5xcvsZl78su29x1ti.dxI.9rYO8t0q5wk2ROJ.1cdR53bmaVG');";
		jdbcTemplate.execute(userSQLQuery);
		userSQLQuery = "INSERT INTO USERS (ID, USERNAME,PASSWORD) VALUES (\n"
				+ "   2, 'myemail@gmail.com','$2a$08$fL7u5xcvsZl78su29x1ti.dxI.9rYO8t0q5wk2ROJ.1cdR53bmaVG'); ";
		jdbcTemplate.execute(userSQLQuery);*/

		String userSQLQuery = "SELECT * FROM USERS";
		List<UserEntity> list = jdbcTemplate.query(userSQLQuery, (ResultSet rs, int rowNum) -> {

			UserEntity user = new UserEntity();
			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			return user;
		});
	}

}