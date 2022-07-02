package com.beidouiot.alllink.community.common.base.exception;

/**
 * 
 *
 * @Description 基本异常
 * @author longww
 * @date 2021年5月7日
 */
public class BaseUncheckedException extends RuntimeException implements IException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3608181280173113487L;

	/**
     * 异常信息
     */
    protected String message;

    /**
     * 具体异常码
     */
    protected int code;

    public BaseUncheckedException(Throwable cause) {
        super(cause);
    }

    public BaseUncheckedException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }


    public BaseUncheckedException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(int code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }


    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
