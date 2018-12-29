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
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        String userSQLQuery = "select * from users where email_id=?";
        List<UserEntity> list = jdbcTemplate.query(userSQLQuery, new String[]{userName},
                (ResultSet rs, int rowNum) -> {
                    UserEntity user = new UserEntity();
                    user.setCountry(rs.getString("country"));
                    user.setEmail_id(userName);
                    user.setFirst_name(rs.getString("first_name"));
                    user.setId(rs.getString("id"));
                    user.setLast_name(rs.getString("last_name"));
                    user.setMobile(rs.getString("mobile"));
                    user.setUser_type(rs.getString("user_type"));
                    user.setPasssword(rs.getString("password"));
                    return user;
                });

        if (!list.isEmpty()) {
            UserEntity userEntity = list.get(0);

            if (userEntity.getUser_type() != null) {
                if (!userEntity.getUser_type().trim().equalsIgnoreCase("super_admin")) {
                    String permissionQuery = "select distinct p.permission_name from users u inner join role_users r_u on u.id=r_u.user_id "
                            + "inner join role r on r_u.role_id=r.id "
                            + "inner join role_permission r_p on r_p.role_id=r.id "
                            + "inner join permission p on p.id=r_p.permission_id where u.email_id=?";
                    List<String> permissionList = jdbcTemplate.query(permissionQuery.toString(),
                            new String[]{userName}, (ResultSet rs, int rowNum) -> {
                                return "ROLE_" + rs.getString("permission_name");
                            });
                    if (permissionList != null && !permissionList.isEmpty()) {
                        for (String permission : permissionList) {
                            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
                            grantedAuthoritiesList.add(grantedAuthority);
                        }
                        list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
                    }
                    return list.get(0);
                } else {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SUPERADMIN");
                    grantedAuthoritiesList.add(grantedAuthority);
                    list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
                    return list.get(0);
                }
            } else {
                return null;
            }
        }
        return null;
    }

}
