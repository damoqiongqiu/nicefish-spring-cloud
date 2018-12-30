package com.fish.user.controller;

import com.fish.user.entity.UserRoleEntity;
import com.fish.user.dao.RoleDAO;
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
public class RoleController {

	@Autowired
    RoleDAO roleDAO;

	@PreAuthorize("hasAnyRole('view_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> getListOfRoles() {
		return new ResponseEntity<Object>(roleDAO.getListOfRoles(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('delete_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteRole(@PathVariable("id") String role_id) {
		roleDAO.deleteRole(role_id);
		return new ResponseEntity<Object>("Role deleted successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('edit_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> deleteRole(@PathVariable("id") String role_id, @RequestBody UserRoleEntity role) {
		roleDAO.updateRole(role_id, role);
		return new ResponseEntity<Object>("Role updated successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('create_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<Object> createRole(@RequestBody UserRoleEntity role) {
		roleDAO.createRole(role);
		return new ResponseEntity<Object>("Role created successfully", HttpStatus.OK);
	}
}