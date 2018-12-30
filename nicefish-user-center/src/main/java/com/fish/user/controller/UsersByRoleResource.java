package com.fish.user.controller;

import com.fish.user.dao.UserByRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UsersByRoleResource {
	@Autowired
	UserByRoleDAO userByRoleDAO;

	@PreAuthorize("hasAnyRole('view_users_by_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.GET)
	public ResponseEntity<Object> viewUsersByRole(@PathVariable("id") String role_id) {
		return new ResponseEntity<>(userByRoleDAO.viewUsersByRole(role_id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('assign_users_to_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.PUT)
	public ResponseEntity<Object> assignUsers2Role(@PathVariable("id") String role_id, @RequestBody ArrayList<String> usersList) {
		userByRoleDAO.assignUsers2Role(role_id, usersList);
		return new ResponseEntity<>("Users are assigned to role successfully", HttpStatus.OK);
	}
}
