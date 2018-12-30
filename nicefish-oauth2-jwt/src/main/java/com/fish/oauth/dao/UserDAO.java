package com.fish.oauth.dao;

import com.fish.oauth.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserEntity getUserDetails(String userName) {
        String userSQLQuery = "select * from auth_user where email=?";
        List<UserEntity> list = jdbcTemplate.query(userSQLQuery, new String[]{userName},
                (ResultSet rs, int rowNum) -> {
                    UserEntity user = new UserEntity();
                    user.setCountry(rs.getString("country"));
                    user.setEmail(rs.getString("email"));
                    user.setFirst_name(rs.getString("first_name"));
                    user.setId(rs.getString("id"));
                    user.setLast_name(rs.getString("last_name"));
                    user.setMobile(rs.getString("mobile"));
                    user.setPasssword(rs.getString("password"));
                    return user;
                });

        if(list.isEmpty()){
            return null;
        }

        UserEntity userEntity = list.get(0);
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        String permissionQuery =
                "select distinct p.permission_name " +
                        "from auth_user u " +
                        "inner join auth_user_role r_u on u.id=r_u.user_id "
                        + "inner join auth_role r on r_u.role_id=r.id "
                        + "inner join auth_role_permission r_p on r_p.role_id=r.id "
                        + "inner join auth_permission p on p.id=r_p.permission_id where u.email=?";
        List<String> permissionList = jdbcTemplate.query(permissionQuery.toString(),
                new String[]{userName}, (ResultSet rs, int rowNum) -> {
                    //ROLE_这个前缀是SpringSecurity默认实现里面提供的
                    return "ROLE_" + rs.getString("permission_name");
                });
        if (permissionList != null && !permissionList.isEmpty()) {
            for (String permission : permissionList) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
                grantedAuthoritiesList.add(grantedAuthority);
            }
            userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
        }
        return userEntity;
    }

}
