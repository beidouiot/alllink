package com.beidouiot.alllink.community.oauth2.server.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;

/**
 * 
 *
 * @Description 全局处理Oauth2抛出的异常
 * @author longww
 * @date 2021年4月11日
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public ResultDataRro<Object> handleOauth2(OAuth2Exception e) {
        return ResultDataRro.failed(e.getOAuth2ErrorCode(),e.getMessage());
    }
}
