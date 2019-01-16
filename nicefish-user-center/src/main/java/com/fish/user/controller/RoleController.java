package com.fish.user.controller;

import com.fish.user.entity.RoleEntity;
import com.fish.user.entity.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * @author 大漠穷秋
 */
@RestController
@RequestMapping("/users")
public class RoleController {
	@Autowired
	RoleRepository roleRepository;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> getListOfRoles() {
		return new ResponseEntity<Object>(roleRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteRole(@PathVariable("id") Integer roleId) {
		roleRepository.delete(roleId);
		return new ResponseEntity<Object>("Role deleted successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateRole(@PathVariable("id") Integer roleId, @RequestBody RoleEntity roleEntity) {
		roleRepository.save(roleEntity);
		return new ResponseEntity<Object>("Role updated successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<Object> createRole(@RequestBody RoleEntity roleEntity) {
		roleRepository.save(roleEntity);
		return new ResponseEntity<Object>("Role created successfully", HttpStatus.OK);
	}
}