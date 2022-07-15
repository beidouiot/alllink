package com.beidouiot.alllink.community.web.gateway.filter;

import cn.hutool.core.util.StrUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.enums.ErrorCodeConstants;
import com.beidouiot.alllink.community.common.base.utils.CacheKeyConstants;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * 
 *
 * @Description 将登录用户的JWT转化成用户信息的全局过滤器
 * @author longww
 * @date 2021年4月11日
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(Constants.AUTHORIZE_TOKEN);
        ServerHttpResponse response = exchange.getResponse();
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace(Constants.BEARER, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
			String userStr = jwsObject.getPayload().toString();
			
			JSONObject jsonObject = JSONObject.parseObject(userStr);
	        String jti = jsonObject.getString(Constants.JTI);
	        Boolean isBlack = redisTemplate.hasKey(CacheKeyConstants.TOKEN_BLACKLIST_PREFIX + jti);
	        if (isBlack) {
	        	response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json; charset=UTF-8");
                ResultDataRro<Object> responseResult = new ResultDataRro<Object>(ErrorCodeConstants.UNAUTHORIZED);
        		DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(responseResult).getBytes());
        		
                return response.writeWith(Flux.just(dataBuffer));
	        }
	        JSONObject userJson = new JSONObject();
	        userJson.put("id", jsonObject.getLong(Constants.ID));
	        userJson.put("username", jsonObject.getString(Constants.USER_NAME));
	        userJson.put("name", jsonObject.getString(Constants.NAME));
	        userJson.put("exp", jsonObject.getString(Constants.EXP));
	        userJson.put("tenantId", jsonObject.getString(Constants.TENANT_ID));
	        userJson.put("jti", jti);
            ServerHttpRequest request = exchange.getRequest().mutate().header(Constants.USER, userJson.toJSONString()).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
        	response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.getHeaders().add("Content-Type", "application/json; charset=UTF-8");
            ResultDataRro<Object> responseResult = new ResultDataRro<Object>(ErrorCodeConstants.INTERNAL_SERVER_ERROR,e.getMessage());
    		DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(responseResult).getBytes());
    		
            return response.writeWith(Flux.just(dataBuffer));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
