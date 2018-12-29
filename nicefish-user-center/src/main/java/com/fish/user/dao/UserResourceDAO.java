package com.fish.user.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fish.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserResourceDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
	public List<UserEntity> getListOfUsers() {
		
		Collection<Map<String, Object>> rows3 = jdbcTemplate.queryForList("select * from users");
		List<UserEntity> usersList = new ArrayList<>();
		rows3.stream().map((row) -> {
			UserEntity user = new UserEntity();
			user.setCountry((String) row.get("country"));
			user.setEmail_id((String) row.get("email_id"));
			user.setFirst_name((String) row.get("first_name"));
			user.setId(String.valueOf(row.get("id")));
			user.setLast_name((String) row.get("last_name"));
			user.setMobile((String) row.get("mobile"));
			user.setUser_type((String) row.get("user_type"));
			return user;
		}).forEach((ss3) -> {
			usersList.add(ss3);
		});
		return usersList;
	}

	public void deleteUser(String user_id) {
		jdbcTemplate.update("delete from users where id=?", new Object[] { user_id });
	}

	public void updateUser(String user_id, UserEntity userEntity) {
		jdbcTemplate.update("update users set country=?, first_name=?, last_name=?, mobile=? where id=?",
				new Object[] { userEntity.getCountry(), userEntity.getFirst_name(), userEntity.getLast_name(),
						userEntity.getMobile(), user_id });
	}

	public void createUser(UserEntity userEntity) {
		jdbcTemplate.update(
				"insert into users (country, first_name, last_name, mobile, email_id, password, user_type) values "
						+ "(?,?,?,?,?,?,?)",
				new Object[] { userEntity.getCountry(), userEntity.getFirst_name(), userEntity.getLast_name(),
						userEntity.getMobile(), userEntity.getEmail_id(), passwordEncoder.encode(userEntity.getPassword()),
						userEntity.getUser_type() });
	}
	
	
	public boolean isSuperAdmin(String id) {
		return jdbcTemplate.queryForObject("select count(id) from users where user_type=? and id=?", new Object[] {"super_admin",id} , Integer.class) >0;
	}

}