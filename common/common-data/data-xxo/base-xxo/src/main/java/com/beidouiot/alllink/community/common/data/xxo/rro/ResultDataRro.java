package com.beidouiot.alllink.community.common.data.xxo.rro;

import java.io.Serializable;

import com.beidouiot.alllink.community.common.base.enums.ErrorCodeConstants;
import com.beidouiot.alllink.community.common.base.utils.IExceptionCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 *
 * @Description 返回信息
 * @author longww
 * @date 2021年4月11日
 * @param <T>
 */
@ApiModel(description = "返回信息")
@Data
public class ResultDataRro<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7587847141299714352L;
	
	@ApiModelProperty(value = "API调用返回结果编码（0:API成功调用，并返回正确结果,非0:API调用错误代码，返回出错信息）", dataType = "Integer")
	private int code;
	
	@ApiModelProperty(value = "返回信息", dataType = "String")
	private String msg;
	
	@ApiModelProperty(value = "返回详细结果数据")
	private T data;
	
	public ResultDataRro() {
	}
	
	public ResultDataRro(IExceptionCode iExceptionCode) {
		this.code = iExceptionCode.getCode();
		this.msg = iExceptionCode.getMsg();
		this.data = null;
	}
	
	/**
	 * @param code
	 * @param msg
	 * @param data
	 */
	public ResultDataRro(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public ResultDataRro(IExceptionCode iExceptionCode, T data) {
		this.code = iExceptionCode.getCode();
		this.msg = iExceptionCode.getMsg();
		this.data = data;
	}
	
	public static <T> ResultDataRro<T> success() {
		return new ResultDataRro<T> (ErrorCodeConstants.SUCCESS, null);
	}
	
	public static <T> ResultDataRro<T> success(T data) {
		return new ResultDataRro<T> (ErrorCodeConstants.SUCCESS, data);
	}
	
	/**
	 * 操作成功
	 * @param <T>
	 * @param msg 自定义消息
	 * @param data
	 * @return
	 */
	public static <T> ResultDataRro<T> success(String msg, T data) {
		return new ResultDataRro<T> (ErrorCodeConstants.SUCCESS.getCode(),msg, data);
	}
	
	/**
	 * 操作失败
	 * @param <T>
	 * @return
	 */
	public static <T> ResultDataRro<T> failed() {
		return new ResultDataRro<T> (ErrorCodeConstants.FAILED, null);
	}
	
	/**
	 * 操作失败
	 * @param <T>
	 * @param iExceptionCode
	 * @return
	 */
	public static <T> ResultDataRro<T> failed(IExceptionCode iExceptionCode) {
		return new ResultDataRro<T> (iExceptionCode.getCode(),iExceptionCode.getMsg(), null);
	}
	
	/**
	 * 操作失败
	 * @param <T>
	 * @param iExceptionCode
	 * @param data
	 * @return
	 */
	public static <T> ResultDataRro<T> failed(IExceptionCode iExceptionCode,T data) {
		return new ResultDataRro<T> (iExceptionCode.getCode(),iExceptionCode.getMsg(), data);
	}
	
	/**
	 * 操作失败
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static <T> ResultDataRro<T> failed(T data) {
		return new ResultDataRro<T> (ErrorCodeConstants.FAILED, data);
	}
	
	/**
	 * 操作失败
	 * @param <T>
	 * @param msg 自定义消息
	 * @param data
	 * @return
	 */
	public static <T> ResultDataRro<T> failed(String msg, T data) {
		return new ResultDataRro<T> (ErrorCodeConstants.FAILED.getCode(),msg, data);
	}
	
	/**
	 * 参数验证失败
	 * @param <T>
	 * @return
	 */
    public static <T> ResultDataRro<T> validateFailed() {
        return failed(ErrorCodeConstants.BASE_VALID_PARAM);
    }
    
    /**
     * 参数验证失败
     * @param <T>
     * @param msg 自定义消息
     * @return
     */
    public static <T> ResultDataRro<T> validateFailed(String msg) {
        return new ResultDataRro<T>(ErrorCodeConstants.BASE_VALID_PARAM.getCode(),msg,null);
    }
    
    /**
     * 参数验证失败
     * @param <T>
     * @param msg 自定义消息
     * @param data
     * @return
     */
    public static <T> ResultDataRro<T> validateFailed(String msg, T data) {
        return new ResultDataRro<T>(ErrorCodeConstants.BASE_VALID_PARAM.getCode(),msg,data);
    }
    
    /**
     * 未登录
     * @param <T>
     * @param msg 自定义消息
     * @param data
     * @return
     */
    public static <T> ResultDataRro<T> unauthorized(String msg, T data) {
        return new ResultDataRro<T>(ErrorCodeConstants.UNAUTHORIZED.getCode(),msg,data);
    }
    /**
     * 未登录
     * @param <T>
     * @param data 返回数据
     * @return
     */
    public static <T> ResultDataRro<T> unauthorized(T data) {
        return new ResultDataRro<T>(ErrorCodeConstants.UNAUTHORIZED,data);
    }
    
    /**
     * 未登录
     * @param <T>
     * @return
     */
    public static <T> ResultDataRro<T> unauthorized() {
        return new ResultDataRro<T>(ErrorCodeConstants.UNAUTHORIZED);
    }
    
    /**
     * 未授权
     * @param <T>
     * @return
     */
    public static <T> ResultDataRro<T> forbidden() {
        return new ResultDataRro<T>(ErrorCodeConstants.FORBIDDEN);
    }
    
    /**
     * 未授权
     * @param <T>
     * @param data 返回数据
     * @return
     */
    public static <T> ResultDataRro<T> forbidden(T data) {
        return new ResultDataRro<T>(ErrorCodeConstants.FORBIDDEN, data);
    }
    
    /**
     * 未授权
     * @param <T>
     * @param msg 自定义消息
     * @param data 返回数据
     * @return
     */
    public static <T> ResultDataRro<T> forbidden(String msg, T data) {
        return new ResultDataRro<T>(ErrorCodeConstants.FORBIDDEN.getCode(), msg, data);
    }

}
