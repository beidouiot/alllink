package com.beidouiot.alllink.community.oauth2.server.granter;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.CacheKeyConstants;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.user.center.User;
import com.beidouiot.alllink.community.feign.user.UserFeignClient;
import com.beidouiot.alllink.community.oauth2.server.service.UserService;

/**
*
* @Description 手机短信验证码登录认证模式
* @author longww
* @date 2021年5月7日
*/
public class ResourceOwnerSmsTokenGranter extends AbstractCustomTokenGranter {
	
	protected UserService userService;
	
	private RedisTemplate<String, Object> redisTemplate;
	
	
	public ResourceOwnerSmsTokenGranter(UserService userService, AuthorizationServerTokenServices tokenServices,
			ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,RedisTemplate<String, Object> redisTemplate) {
		super(tokenServices, clientDetailsService, requestFactory, "sms");
		this.userService = userService;
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	protected User getCustomUser(Map<String, String> parameters) {
		String mobile = parameters.get(Constants.MOBILE);
		if (verifySms(mobile, parameters.get(Constants.SMS_CODE))) {
			User user = (User) userService.loadUserByMobile(mobile);
			return user;
		} else {
			throw new ServiceException("验证码错误或验证码已失效");
		}
	}
	
	/**
	 * 校验手机短信验证码
	 * @param mobile
	 * @param smsCode
	 */
	private boolean verifySms(String mobile, String smsCode) {
        if (StringUtils.isBlank(mobile)) {
            throw new ServiceException("手机号不能为空");
        }
        
        if (StringUtils.isBlank(smsCode)) {
            throw new ServiceException("验证码不能为空");
        }
       Object obj =  redisTemplate.opsForHash().get(CacheKeyConstants.MOBILE_SMS_CODE_CACHE_PREFIX,mobile);
        if (obj == null) {
        	return false;
        }
       
        if (!StringUtils.equals(smsCode, obj.toString())) {
        	return false;
        }
        return true;
    }

}
