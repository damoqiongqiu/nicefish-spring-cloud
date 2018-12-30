package com.fish.user.controller;

import com.fish.user.AccessTokenMapper;
import com.fish.user.dao.PermissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionResource {

    @Autowired
    PermissionDAO permissionDAO;

    @PreAuthorize("hasAnyRole('view_permission', 'SUPERADMIN')")
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public ResponseEntity<Object> getListOfPermissions() {
        AccessTokenMapper accessTokenMapper = (AccessTokenMapper)
                ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getDecodedDetails();
        System.out.println("accessTokenMapper.getFirst_name()::" + accessTokenMapper.getFirst_name());
        System.out.println("accessTokenMapper.getLast_name()::" + accessTokenMapper.getLast_name());
        return new ResponseEntity<Object>(permissionDAO.getListOfPermissions(), HttpStatus.OK);
    }
}
