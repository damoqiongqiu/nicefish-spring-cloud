package com.fish.oauth.service;

import com.fish.oauth.dao.UserDAO;
import com.fish.oauth.entity.CustomUserEntity;
import com.fish.oauth.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userDAO.getUserDetails(userName);
        if (userEntity == null || userEntity.getId() == null || "".equalsIgnoreCase(userEntity.getId())) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        CustomUserEntity customUser = new CustomUserEntity(userEntity);
        return customUser;
    }
}
