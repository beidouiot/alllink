package com.beidouiot.alllink.community.oauth2.server.controller;

import com.beidouiot.alllink.community.common.base.enums.ErrorCodeConstants;
import com.beidouiot.alllink.community.common.base.utils.IExceptionCode;
import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.oauth2.server.dto.Oauth2TokenDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * 
 *
 * @Description 自定义Oauth2获取令牌接口
 * @author longww
 * @date 2021年4月11日
 */
@Api(tags = "Oauth2认证中心")
@RestController
@RequestMapping(ServiceConstants.OAUTH2_URI_BASE)
public class AuthController {

	@Autowired
	private TokenEndpoint tokenEndpoint;

	/**
	 * Oauth2登录认证
	 */
	@ApiOperation(value = "获取token", notes = "login")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
			@ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
			@ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥", required = true),
			@ApiImplicitParam(name = "refresh_token", value = "刷新token"),
			@ApiImplicitParam(name = "username", value = "登录用户名"),
			@ApiImplicitParam(name = "password", value = "登录密码") })
	@PostMapping(value = "token")
	public ResponseEntity<?> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
			throws HttpRequestMethodNotSupportedException {
		OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
		Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder().token(oAuth2AccessToken.getValue())
				.refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
				.expiresIn(oAuth2AccessToken.getExpiresIn()).scope(oAuth2AccessToken.getScope()).tokenHead("Bearer ")
				.build();
		ResultDataRro<Oauth2TokenDto> resultDataRro = new ResultDataRro<Oauth2TokenDto>(ErrorCodeConstants.SUCCESS,
				oauth2TokenDto);
		return new ResponseEntity<ResultDataRro<Oauth2TokenDto>>(resultDataRro, HttpStatus.OK);// ResultDataRro.success(oauth2TokenDto);
	}
}
