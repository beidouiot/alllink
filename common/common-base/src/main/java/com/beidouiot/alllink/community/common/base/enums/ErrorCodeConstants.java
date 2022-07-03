package com.beidouiot.alllink.community.common.base.enums;

import com.beidouiot.alllink.community.common.base.utils.IExceptionCode;

/**
 * 
 *
 * @Description 错误代码
 * @author longww
 * @date 2021年4月11日
 */
public enum ErrorCodeConstants implements IExceptionCode {

    //系统相关 start
	
	SYSTEM_UNKNOW_ERROR(-2,"系统未知错误"),
	FAILED(-1,"操作失败"),
    SUCCESS(0, "操作成功"),
    SYSTEM_BUSY(1, "系统繁忙,请稍后再试!"),
    SYSTEM_TIMEOUT(2, "系统维护中~请稍后再试~"),
    PARAM_EX(3, "参数类型解析异常"),
    SQL_EX(4, "运行SQL出现异常"),
    NULL_POINT_EX(5, "空指针异常"),
    ILLEGALA_ARGUMENT_EX(6, "无效参数异常"),
    MEDIA_TYPE_EX(7, "请求类型异常"),
    LOAD_RESOURCES_ERROR(8, "加载资源出错"),
    BASE_VALID_PARAM(9, "统一验证参数异常"),
    OPERATION_EX(10, "操作异常"),
    SERVICE_MAPPER_ERROR(11, "Mapper类转换异常"),
    CAPTCHA_ERROR(12, "验证码校验失败"),
    JSON_PARSE_ERROR(13, "JSON解析异常"),
    SIGNATURE_ERROR(14, "签名失败"),
    PARAM_EXIST(16, "参数已存在"),
    CAN_NOT_DELETE_DATA(17, "不能删除数据"),


    OK(200, "OK"),
    BAD_REQUEST(400, "错误的请求"),
    /**
     * {@code 401 Unauthorized}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7235#section-3.1">HTTP/1.1: Authentication, section 3.1</a>
     */
    UNAUTHORIZED(401, "未登录或token已经过期"),
    
    FORBIDDEN(403, "没有访问权限"),
    /**
     * {@code 404 Not Found}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.4">HTTP/1.1: Semantics and Content, section 6.5.4</a>
     */
    NOT_FOUND(404, "没有找到资源"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求类型"),

    TOO_MANY_REQUESTS(429, "请求超过次数限制"),
    INTERNAL_SERVER_ERROR(500, "内部服务错误"),
    BAD_GATEWAY(502, "网关错误"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    //系统相关 end

    REQUIRED_FILE_PARAM_EX(1001, "请求中必须至少包含一个有效文件"),

    DATA_SAVE_ERROR(2000, "新增数据失败"),
    DATA_UPDATE_ERROR(2001, "修改数据失败"),
    TOO_MUCH_DATA_ERROR(2002, "批量新增数据过多"),
    DATA_SELECT_ERROR(2003,"查询数据失败"),
    AUTHORITY_VERIFICATION_ERROR(2004,"权限校验错误"),
    AUTHENTICATION_VERIFICATION_ERROR(2005,"身份校验错误"),
    //jwt token 相关 start

    JWT_BASIC_INVALID(40000, "无效的基本身份验证令牌"),
    JWT_TOKEN_EXPIRED(40001, "会话超时，请重新登录"),
    JWT_SIGNATURE(40002, "不合法的token，请确认token值"),
    JWT_ILLEGAL_ARGUMENT(40003, "缺少token参数"),
    JWT_GEN_TOKEN_FAIL(40004, "生成token失败"),
    JWT_PARSER_TOKEN_FAIL(40005, "解析用户身份错误，请重新登录！"),
    JWT_USER_INVALID(40006, "用户名或密码错误"),
    JWT_USER_ENABLED(40007, "用户已经被禁用！"),
    JWT_OFFLINE(40008, "您已在另一个设备登录！"),
    JWT_GET_TOKEN_FAIL(40009,"获取token失败"),
    
    //jwt token 相关 end
    
    ;

    private int code;
    private String msg;

    ErrorCodeConstants(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public ErrorCodeConstants build(String msg, Object... param) {
        this.msg = String.format(msg, param);
        return this;
    }

    public ErrorCodeConstants param(Object... param) {
        msg = String.format(msg, param);
        return this;
    }
}
