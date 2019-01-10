package com.fish.blog.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FishJwtTokenConverter extends DefaultAccessTokenConverter{
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication auth = super.extractAuthentication(map);
        AccessTokenMapper details = new AccessTokenMapper();
        if (map.get("id") != null)
            details.setId((Integer) map.get("id"));
        if (map.get("first_name") != null)
            details.setFirst_name((String) map.get("first_name"));
        if (map.get("last_name") != null)
            details.setLast_name((String) map.get("last_name"));
        if (map.get("mobile") != null)
            details.setMobile((String) map.get("mobile"));
        if (auth.getAuthorities() != null && !auth.getAuthorities().isEmpty()) {
            List<String> authorities = new ArrayList<>();
            for (GrantedAuthority gn : auth.getAuthorities()) {
                authorities.add(gn.getAuthority());
            }
            details.setAuthorities(authorities);
        }
        auth.setDetails(details);
        return auth;
    }

    class AccessTokenMapper {
        private Integer id;
        private String first_name;
        private String last_name;
        private String mobile;
        private String country;
        private List<String> authorities = new ArrayList<String>();

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<String> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(List<String> authorities) {
            this.authorities = authorities;
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
}