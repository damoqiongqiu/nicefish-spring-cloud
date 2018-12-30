package com.fish.oauth.service;

import com.fish.oauth.entity.CustomUserEntity;
import com.fish.oauth.entity.UserEntity;
import com.fish.oauth.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(userName);

        List<String> authroties=userRepository.findAuthoritiesByEmail(userName);
        for(String authority:authroties){
            authority="ROLE_"+authority;
            userEntity.getGrantedAuthoritiesList().add(new SimpleGrantedAuthority(authority));
        }

        CustomUserEntity customUser = new CustomUserEntity(userEntity);
        return customUser;
    }
}
