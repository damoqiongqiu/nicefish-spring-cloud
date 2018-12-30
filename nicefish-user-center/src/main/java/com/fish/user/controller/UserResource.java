package com.fish.user.controller;

import com.fish.user.dao.UserDAO;
import com.fish.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserResource {

	@Autowired
	UserDAO userDAO;

	@PreAuthorize("hasAnyRole('view_users', 'SUPERADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Object> getListOfUsers() {
		return new ResponseEntity<>(userDAO.getListOfUsers(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('delete_users', 'SUPERADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String user_id) {
		//TODO:数据保护，不允许删除比自己权限高的用户
		userDAO.deleteUser(user_id);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('edit_users', 'SUPERADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@PathVariable("id") String user_id, @RequestBody UserEntity userEntity) {
		//TODO:数据保护，不允许编辑比自己权限高的用户
		userDAO.updateUser(user_id, userEntity);
		return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('create_users', 'SUPERADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity) {
		//TODO:不允许创建大于或等于自己权限的用户
		userDAO.createUser(userEntity);
		return new ResponseEntity<>("User created successfully", HttpStatus.OK);
	}
}
