package com.beidouiot.alllink.community.common.base.exception;

import com.beidouiot.alllink.community.common.base.utils.IExceptionCode;

public class ServiceException extends BaseUncheckedException {
	private static final long serialVersionUID = -1427062612547404110L;
	
	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public ServiceException(String message) {
		super(-1, message);
	}

	public ServiceException(int code, String message) {
		super(code, message);
	}

	public ServiceException(int code, String message, Object... args) {
		super(code, message, args);
	}
	
	public ServiceException(IExceptionCode iExceptionCode) {
		super(iExceptionCode.getCode(),iExceptionCode.getMsg());
	}
	
	/**
	 * 实例化异常
	 *
	 * @param code    自定义异常编码
	 * @param message 自定义异常消息
	 * @param args    已定义异常参数
	 * @return
	 */
	public static ServiceException wrap(int code, String message, Object... args) {
		return new ServiceException(code, message, args);
	}

	public static ServiceException wrap(String message, Object... args) {
		return new ServiceException(-1, message, args);
	}

	public static ServiceException validFail(String message, Object... args) {
		return new ServiceException(-9, message, args);
	}

	public static ServiceException wrap(IExceptionCode ex) {
		return new ServiceException(ex.getCode(), ex.getMsg());
	}

	/**
	 * 抛出业务异常
	 * 
	 * @param msg <br>
	 *            -----------------------------------------------------<br>
	 */
	public static void throwException(String msg) {
		throw new ServiceException(msg);
	}

	@Override
	public String toString() {
		return "ServiceException [message=" + message + ", code=" + code + "]";
	}
    
}
