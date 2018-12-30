package com.fish.oauth.entity;

import org.springframework.security.core.userdetails.User;

/**
 * User包装类，用来包装从数据库获得的实体类，同时继承SpringSecurity原生的User类。
 * 包装的目的是为了向SpringSecurity原生的User类上增加一些属性。
 * （这写法是不是有点儿别扭？还不如直接修改JPA的映射）
 *
 * @author 大漠穷秋
 */
public class CustomUserEntity extends User {
    private String id;
    private String first_name;
    private String last_name;
    private String mobile;
    private String country;
    private String email;

    public CustomUserEntity(UserEntity user) {
        super(user.getEmail(), user.getPasssword(), user.getGrantedAuthoritiesList());
        this.id = user.getId();
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
        this.mobile = user.getMobile();
        this.country = user.getCountry();
        this.email=user.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
