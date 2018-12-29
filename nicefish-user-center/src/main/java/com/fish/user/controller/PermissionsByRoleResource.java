package com.fish.user.controller;

import java.util.ArrayList;

import com.fish.user.dao.PermissionsByRoleResourceDAO;
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
public class PermissionsByRoleResource {

	@Autowired
	PermissionsByRoleResourceDAO permissionsByRoleResourceDAO;

	@PreAuthorize("hasAnyRole('view_permissions_by_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.GET)
	public ResponseEntity<Object> viewPermissionsByRole(@PathVariable("id") String role_id) {
		return new ResponseEntity<>(permissionsByRoleResourceDAO.getViewPermissionsByRole(role_id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('assign_permissions_to_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.PUT)
	public ResponseEntity<Object> assignPermissions2Role(@PathVariable("id") String role_id, @RequestBody ArrayList<String> permissionsList) {
		permissionsByRoleResourceDAO.assignPermissions2Role(role_id, permissionsList);
		return new ResponseEntity<>("Permissions are assigned to role successfully", HttpStatus.OK);
	}
		
}