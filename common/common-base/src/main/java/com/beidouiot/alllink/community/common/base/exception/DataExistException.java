package com.beidouiot.alllink.community.common.base.exception;

import com.beidouiot.alllink.community.common.base.utils.IExceptionCode;

public class DataExistException extends BaseUncheckedException {
	private static final long serialVersionUID = -1427062612547404110L;
	
	public DataExistException(Throwable cause) {
		super(cause);
	}

	public DataExistException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public DataExistException(String message) {
		super(-1, message);
	}

	public DataExistException(int code, String message) {
		super(code, message);
	}

	public DataExistException(int code, String message, Object... args) {
		super(code, message, args);
	}
	
	public DataExistException(IExceptionCode iExceptionCode) {
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
	public static DataExistException wrap(int code, String message, Object... args) {
		return new DataExistException(code, message, args);
	}

	public static DataExistException wrap(String message, Object... args) {
		return new DataExistException(-1, message, args);
	}

	public static DataExistException validFail(String message, Object... args) {
		return new DataExistException(-9, message, args);
	}

	public static DataExistException wrap(IExceptionCode ex) {
		return new DataExistException(ex.getCode(), ex.getMsg());
	}

	/**
	 * 抛出业务异常
	 * 
	 * @param msg <br>
	 *            -----------------------------------------------------<br>
	 */
	public static void throwException(String msg) {
		throw new DataExistException(msg);
	}

	@Override
	public String toString() {
		return "ServiceException [message=" + message + ", code=" + code + "]";
	}
    
}
