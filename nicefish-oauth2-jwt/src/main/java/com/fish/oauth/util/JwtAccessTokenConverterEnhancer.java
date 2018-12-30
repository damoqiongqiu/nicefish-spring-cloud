package com.fish.oauth.util;

import com.fish.oauth.entity.CustomUserEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义 JWT Token 转换器，把用户名、邮箱、手机号等信息存到Token里面，使用者在拿到JWT的Token时可以直接从里面获取用户资料
 *
 * @author 大漠穷秋
 */
public class JwtAccessTokenConverterEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        CustomUserEntity user = (CustomUserEntity) authentication.getPrincipal();

        Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
        if (user.getId() != null)
            info.put("id", user.getId());
        if (user.getFirst_name() != null)
            info.put("first_name", user.getFirst_name());
        if (user.getLast_name() != null)
            info.put("last_name", user.getLast_name());
        if (user.getCountry() != null)
            info.put("country", user.getCountry());
        if (user.getMobile() != null)
            info.put("mobile", user.getMobile());

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);
        return super.enhance(customAccessToken, authentication);
    }
}