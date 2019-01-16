package com.fish.oauth.service;

import com.fish.oauth.entity.CustomUserEntity;
import com.fish.user.entity.UserEntity;
import com.fish.user.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义用户详情服务，目的是在用户详情里面增加自定义字段，这些字段会被存储到JWT的Token里面。
 * @see com.fish.oauth.config.OAuthWebSecurityConfig 里面会利用此服务来查询数据库中的用户详情。
 * @author 大漠穷秋
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(userName);

        List<String> authorities=userRepository.findAuthoritiesByEmail(userName);
        for(String authority:authorities){
            authority="ROLE_"+authority;
            userEntity.getGrantedAuthoritiesList().add(new SimpleGrantedAuthority(authority));
        }

        CustomUserEntity customUser = new CustomUserEntity(userEntity);
        return customUser;
    }
}
