package com.fish.user.controller;

import java.util.ArrayList;

import com.fish.user.dao.UsersByRoleResourceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersByRoleResource {
	@Autowired
	UsersByRoleResourceDAO usersByRoleResourceDAO;

	@PreAuthorize("hasAnyRole('view_users_by_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.GET)
	public ResponseEntity<Object> viewUsersByRole(@PathVariable("id") String role_id) {
		return new ResponseEntity<>(usersByRoleResourceDAO.viewUsersByRole(role_id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('assign_users_to_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.PUT)
	public ResponseEntity<Object> assignUsers2Role(@PathVariable("id") String role_id, @RequestBody ArrayList<String> usersList) {
		usersByRoleResourceDAO.assignUsers2Role(role_id, usersList);
		return new ResponseEntity<>("Users are assigned to role successfully", HttpStatus.OK);
	}
}
