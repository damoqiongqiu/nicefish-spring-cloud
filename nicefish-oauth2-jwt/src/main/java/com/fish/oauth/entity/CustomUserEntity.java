package com.fish.oauth.entity;

import org.springframework.security.core.userdetails.User;

/**
 * User包装类，用来包装从数据库获得的实体类，同时继承SpringSecurity原生的User类。
 * 包装的目的是为了向SpringSecurity原生的User类上增加一些属性。
 *
 * @author 大漠穷秋
 */
public class CustomUserEntity extends User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String country;
    private String email;

    public CustomUserEntity(UserEntity user) {
        super(user.getEmail(), user.getPassword(), user.getGrantedAuthoritiesList());
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
