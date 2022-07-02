package com.beidouiot.alllink.community.oauth2.server.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * @Description Oauth2获取Token返回信息封装
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
public class Oauth2TokenDto implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -482231018985918167L;
	
	/**
     * 访问令牌
     */
    private String token;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    private String tokenHead;
    /**
     * 有效时长（秒）
     */
    private int expiresIn;
    
    private Set<String> scope;
}
