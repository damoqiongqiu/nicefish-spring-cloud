package com.fish.user.dao;

import com.fish.user.entity.PermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class PermissionDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<PermissionEntity> getListOfPermissions() {
		Collection<Map<String, Object>> rows3 = jdbcTemplate.queryForList("select * from auth_permission");
		List<PermissionEntity> permissionsList = new ArrayList<>();
		rows3.stream().map((row) -> {
			PermissionEntity p = new PermissionEntity();
			p.setPermission_name((String)row.get("permission_name"));
			p.setId(String.valueOf(row.get("id")));
			return p;
		}).forEach((ss3) -> {
			permissionsList.add(ss3);
		});
		return permissionsList;
	}
}