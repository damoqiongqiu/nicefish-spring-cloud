package com.fish.user.controller;

import com.fish.user.entity.PermissionRepository;
import com.fish.user.entity.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PermissionController {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    @PreAuthorize("hasAnyRole('view_permission')")
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public ResponseEntity<Object> getListOfPermissions() {
        return new ResponseEntity<Object>(permissionRepository.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('view_permissions_by_role')")
    @RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.GET)
    public ResponseEntity<Object> viewPermissionsByRole(@PathVariable("id") Integer roleId) {
        return new ResponseEntity<>(roleRepository.findOne(roleId).getPermissions(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('assign_permissions_to_role')")
    @RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.PUT)
    public ResponseEntity<Object> assignPermissions2Role(@PathVariable("id") Integer roleId, @RequestBody ArrayList<String> permissionsList) {
        //TODO:这里需要重新实现
        return new ResponseEntity<>("Permissions are assigned to role successfully", HttpStatus.OK);
    }
}
