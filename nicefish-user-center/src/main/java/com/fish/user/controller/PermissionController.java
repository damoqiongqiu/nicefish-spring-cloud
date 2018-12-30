package com.fish.user.controller;

import com.fish.user.AccessTokenMapper;
import com.fish.user.dao.PermissionDAO;
import com.fish.user.dao.PermissionsByRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PermissionController {

    @Autowired
    PermissionDAO permissionDAO;

    @Autowired
    PermissionsByRoleDAO permissionsByRoleDAO;

    @PreAuthorize("hasAnyRole('view_permission', 'SUPERADMIN')")
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public ResponseEntity<Object> getListOfPermissions() {
        AccessTokenMapper accessTokenMapper = (AccessTokenMapper)
                ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getDecodedDetails();
        System.out.println("accessTokenMapper.getFirst_name()::" + accessTokenMapper.getFirst_name());
        System.out.println("accessTokenMapper.getLast_name()::" + accessTokenMapper.getLast_name());
        return new ResponseEntity<Object>(permissionDAO.getListOfPermissions(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('view_permissions_by_role', 'SUPERADMIN')")
    @RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.GET)
    public ResponseEntity<Object> viewPermissionsByRole(@PathVariable("id") String role_id) {
        return new ResponseEntity<>(permissionsByRoleDAO.getViewPermissionsByRole(role_id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('assign_permissions_to_role', 'SUPERADMIN')")
    @RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.PUT)
    public ResponseEntity<Object> assignPermissions2Role(@PathVariable("id") String role_id, @RequestBody ArrayList<String> permissionsList) {
        permissionsByRoleDAO.assignPermissions2Role(role_id, permissionsList);
        return new ResponseEntity<>("Permissions are assigned to role successfully", HttpStatus.OK);
    }
}
