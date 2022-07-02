package com.beidouiot.alllink.community.common.base.exception;

import com.beidouiot.alllink.community.common.base.utils.IExceptionCode;

public class CanNotDeleteDataException extends BaseUncheckedException {
	private static final long serialVersionUID = -1427062612547404110L;
	
	public CanNotDeleteDataException(Throwable cause) {
		super(cause);
	}

	public CanNotDeleteDataException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public CanNotDeleteDataException(String message) {
		super(-1, message);
	}

	public CanNotDeleteDataException(int code, String message) {
		super(code, message);
	}

	public CanNotDeleteDataException(int code, String message, Object... args) {
		super(code, message, args);
	}
	
	public CanNotDeleteDataException(IExceptionCode iExceptionCode) {
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
	public static CanNotDeleteDataException wrap(int code, String message, Object... args) {
		return new CanNotDeleteDataException(code, message, args);
	}

	public static CanNotDeleteDataException wrap(String message, Object... args) {
		return new CanNotDeleteDataException(-1, message, args);
	}

	public static CanNotDeleteDataException validFail(String message, Object... args) {
		return new CanNotDeleteDataException(-9, message, args);
	}

	public static CanNotDeleteDataException wrap(IExceptionCode ex) {
		return new CanNotDeleteDataException(ex.getCode(), ex.getMsg());
	}

	/**
	 * 抛出业务异常
	 * 
	 * @param msg <br>
	 *            -----------------------------------------------------<br>
	 */
	public static void throwException(String msg) {
		throw new CanNotDeleteDataException(msg);
	}

	@Override
	public String toString() {
		return "ServiceException [message=" + message + ", code=" + code + "]";
	}
    
}
