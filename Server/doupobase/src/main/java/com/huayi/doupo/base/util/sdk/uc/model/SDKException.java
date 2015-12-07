package com.huayi.doupo.base.util.sdk.uc.model;

/**
 * 自定义异常信息
 * <br>
 */
public class SDKException extends Exception {
    private int errorCode = -1;
    private String error;
    private static final long serialVersionUID = 1L;

    public SDKException(String msg) {
        super(msg);
    }

    public SDKException(Exception cause) {
        super(cause);
    }
    
    public SDKException(String msg , int errorCode) {
    	super(msg);
    	this.errorCode = errorCode;
    }

    public SDKException(String msg, Exception cause) {
        super(msg, cause);
    }

    public SDKException(String msg, Exception cause, int errorCode) {
        super(msg, cause);
        this.errorCode = errorCode;

    }

	public int getErrorCode() {
		return errorCode;
	}

	public String getError() {
		return error;
	}
    
}
