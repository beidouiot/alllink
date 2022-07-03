package com.beidouiot.alllink.community.web.gateway.authorization;

import cn.hutool.core.convert.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.beidouiot.alllink.community.common.base.utils.CacheKeyConstants;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.utils.AlllinkStringUtils;

import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 *
 * @Description 鉴权管理器，用于判断是否有资源的访问权限
 * @author longww
 * @date 2021年4月11日
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Value("${app.api.prefix}")
	private String prefix;

	@Override
	public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
		// 从Redis中获取当前路径可访问角色列表
		URI uri = authorizationContext.getExchange().getRequest().getURI();
		Object obj = redisTemplate.opsForHash().get(CacheKeyConstants.RESOURCE_ROLES_MAP,
				AlllinkStringUtils.substringAfter(uri.getPath(), prefix));
		if (null == obj) {
			Set<Object> sets = redisTemplate.opsForHash().keys(CacheKeyConstants.RESOURCE_ROLES_MAP);
			AntPathMatcher pathMatcher = new AntPathMatcher();
			for (Object object : sets) {
				if (pathMatcher.match(object.toString(), AlllinkStringUtils.substringAfter(uri.getPath(), prefix))) {
					obj = redisTemplate.opsForHash().get(CacheKeyConstants.RESOURCE_ROLES_MAP, object.toString());
					break;
				}
			}
		}
		List<String> authorities = Convert.toList(String.class, obj);
		authorities = authorities.stream().map(i -> i = Constants.AUTHORITY_PREFIX + i).collect(Collectors.toList());
		// 认证通过且角色匹配的用户可访问当前路径
		return mono
				.filter(Authentication::isAuthenticated)
				.flatMapIterable(Authentication::getAuthorities)
				.map(GrantedAuthority::getAuthority)
				.any(authorities::contains)
				.map(AuthorizationDecision::new)
				.defaultIfEmpty(new AuthorizationDecision(false));
	}

}
