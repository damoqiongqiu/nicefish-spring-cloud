package com.fish.user.controller;

import com.fish.user.entity.RoleRepository;
import com.fish.user.entity.UserEntity;
import com.fish.user.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@PreAuthorize("hasAnyRole('view_users')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Object> getListOfUsers() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('delete_users')")
	@RequestMapping(value = "/users/delete/{email}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("email") String email) {
		//TODO:数据保护，不允许删除比自己权限高的用户
		userRepository.deleteByEmail(email);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('edit_users')")
	@RequestMapping(value = "/users/modify/{email}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@PathVariable("email") String email, @RequestBody UserEntity userEntity) {
		//TODO:数据保护，不允许编辑比自己权限高的用户
		userRepository.save(userEntity);
		return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('create_users')")
	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity) {
		//TODO:不允许创建大于或等于自己权限的用户
		userRepository.save(userEntity);
		return new ResponseEntity<>("User created successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('view_users_by_role')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.GET)
	public ResponseEntity<Object> viewUsersByRole(@PathVariable("id") Integer roleId) {
		return new ResponseEntity<>(roleRepository.findOne(roleId).getUsers(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('assign_users_to_role')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.PUT)
	public ResponseEntity<Object> assignUsers2Role(@PathVariable("id") Integer roleId, @RequestBody ArrayList<String> usersList) {
		//TODO:这里需要重新实现
		return new ResponseEntity<>("Users are assigned to role successfully", HttpStatus.OK);
	}
}
