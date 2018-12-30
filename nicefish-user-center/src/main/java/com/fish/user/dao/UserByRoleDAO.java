package com.fish.user.dao;

import com.fish.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class UserByRoleDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Object viewUsersByRole(String role_id) {

		Collection<Map<String, Object>> rows3 = jdbcTemplate.queryForList(
				"select u.id,u.first_name, u.last_name, u.email, u.country, u.mobile from auth_user u "
						+ "inner join auth_user_role role_u on u.id=role_u.user_id " + "where role_u.role_id=?",
				new Object[] { role_id });
		List<UserEntity> usersList = new ArrayList<>();
		rows3.stream().map((row) -> {
			UserEntity user = new UserEntity();
			user.setCountry((String) row.get("country"));
			user.setEmail((String) row.get("email"));
			user.setFirst_name((String) row.get("first_name"));
			user.setId(String.valueOf(row.get("id")));
			user.setLast_name((String) row.get("last_name"));
			user.setMobile((String) row.get("mobile"));
			return user;
		}).forEach((ss3) -> {
			usersList.add(ss3);
		});
		return usersList;

	}

	public void assignUsers2Role(String role_id, ArrayList<String> usersList) { 	
		jdbcTemplate.update("delete from auth_user_role where role_id=?", new Object[] {role_id});
		for(String id:usersList) {
			jdbcTemplate.update("insert into auth_user_role (role_id, user_id) values (?,?)", new Object[]{role_id,id});
		}
	}
}