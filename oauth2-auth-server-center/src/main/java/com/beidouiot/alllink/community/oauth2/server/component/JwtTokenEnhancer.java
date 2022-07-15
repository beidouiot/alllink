package com.beidouiot.alllink.community.oauth2.server.component;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.beidouiot.alllink.community.common.data.entity.user.center.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @Description JWT内容增强器
 * @author longww
 * @date 2021年4月11日
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//    	SecurityUser user = (SecurityUser) authentication.getPrincipal();
        User user = (User) authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        //把用户ID及用户姓名设置到JWT中
        info.put("id", user.getId());
        info.put("name", user.getName());
        
        info.put("tenantId", user.getTenantId() == null ? "" : user.getTenantId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
