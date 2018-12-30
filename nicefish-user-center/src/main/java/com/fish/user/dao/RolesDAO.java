package com.fish.user.dao;

import com.fish.user.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class RolesDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<UserRoleEntity> getListOfRoles() {

		Collection<Map<String, Object>> rows3 = jdbcTemplate.queryForList("select * from auth_role order by id asc");
		List<UserRoleEntity> rolesList = new ArrayList<>();
		rows3.stream().map((row) -> {
			UserRoleEntity role = new UserRoleEntity();
			role.setId(String.valueOf(row.get("id")));
			role.setRole_name((String) row.get("role_name"));
			return role;
		}).forEach((ss3) -> {
			rolesList.add(ss3);
		});
		return rolesList;

	}

	public void deleteRole(String role_id) {
		jdbcTemplate.update("delete from auth_role where id=?", new Object[] { role_id });
	}

	public void updateRole(String role_id, UserRoleEntity role) {
		jdbcTemplate.update("update auth_role set role_name=? where id=?", new Object[] { role.getRole_name(), role_id });
	}

	public void createRole(UserRoleEntity role) {
		jdbcTemplate.update("insert into auth_role (role_name) values (?)", new Object[] { role.getRole_name() });
	}

}