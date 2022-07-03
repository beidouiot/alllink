package com.beidouiot.alllink.community.common.base.exception;

import com.beidouiot.alllink.community.common.base.utils.IExceptionCode;

public class OldPasswordException extends BaseUncheckedException {
	private static final long serialVersionUID = -1427062612547404110L;
	
	public OldPasswordException(Throwable cause) {
		super(cause);
	}

	public OldPasswordException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public OldPasswordException(String message) {
		super(-1, message);
	}

	public OldPasswordException(int code, String message) {
		super(code, message);
	}

	public OldPasswordException(int code, String message, Object... args) {
		super(code, message, args);
	}
	
	public OldPasswordException(IExceptionCode iExceptionCode) {
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
	public static OldPasswordException wrap(int code, String message, Object... args) {
		return new OldPasswordException(code, message, args);
	}

	public static OldPasswordException wrap(String message, Object... args) {
		return new OldPasswordException(-1, message, args);
	}

	public static OldPasswordException validFail(String message, Object... args) {
		return new OldPasswordException(-9, message, args);
	}

	public static OldPasswordException wrap(IExceptionCode ex) {
		return new OldPasswordException(ex.getCode(), ex.getMsg());
	}

	/**
	 * 抛出业务异常
	 * 
	 * @param msg <br>
	 *            -----------------------------------------------------<br>
	 */
	public static void throwException(String msg) {
		throw new OldPasswordException(msg);
	}

	@Override
	public String toString() {
		return "ServiceException [message=" + message + ", code=" + code + "]";
	}
    
}
