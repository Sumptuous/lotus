package com.lotus.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wyy
 */
@SuppressWarnings("serial")
public class BaseException extends RuntimeException {
	
	private final ErrorCode errorCode;
	private final Class<?> errorInClass;

	public BaseException(ErrorCode errorCode)
	{
		super(errorCode.getMsg());
		this.errorCode = errorCode;
		this.errorInClass = null;
	}
	
	public BaseException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorInClass = null;
	}

	public BaseException(ErrorCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorInClass = null;
	}

	public BaseException(ErrorCode errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorInClass = null;
	}
	public BaseException(ErrorCode errorCode, String message, Throwable cause, Class<?> errorInClass) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorInClass = errorInClass;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public Class<?> getErrorInClass() {
		return errorInClass;
	}
	
	public static void throwException(ErrorCode errorCode, Exception e, Class<?> errorInClass, String message) {
		if (e instanceof BaseException) {
			throw (BaseException)e;
		}
		throw new BaseException(errorCode, StringUtils.defaultIfBlank(message, e.getMessage()), e, errorInClass);
	}
}
