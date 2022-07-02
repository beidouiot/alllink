package com.beidouiot.alllink.community.oauth2.server.granter;

import java.util.Map;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.beidouiot.alllink.community.common.data.entity.user.center.User;

/**
*
* @Description TODO
* @author longww
* @date 2021年6月5日
*/

public abstract class AbstractCustomTokenGranter extends AbstractTokenGranter {
	
	protected AbstractCustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }
	
	@Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        User customUser = getCustomUser(parameters);
        if (customUser == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        OAuth2Request storedOAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(customUser, null, customUser.getAuthorities());
        authentication.setDetails(customUser);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(storedOAuth2Request, authentication);
        return oAuth2Authentication;
    }

	protected abstract User getCustomUser(Map<String, String> parameters);
}
