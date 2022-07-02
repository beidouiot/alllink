package com.beidouiot.alllink.community.common.base.exception;


public abstract class BaseCheckedException extends Exception implements IException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2443933202089441073L;

	/**
     * 异常信息
     */
    protected String message;

    /**
     * 具体异常码
     */
    protected int code;

    public BaseCheckedException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseCheckedException(int code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
