package com.rootlab.ch10.common.exception;

import com.rootlab.ch10.common.Constants;
import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

	private static final long serialVersionUID = 3038451986656827679L;
	private final Constants.ExceptionClass exceptionClass;
	private final HttpStatus httpStatus;

	public CustomException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
		super(exceptionClass.toString() + message);
		this.exceptionClass = exceptionClass;
		this.httpStatus = httpStatus;
	}

	public Constants.ExceptionClass getExceptionClass() {
		return exceptionClass;
	}

	public int getHttpStatusCode() {
		return httpStatus.value();
	}

	public String getHttpStatusType() {
		return httpStatus.getReasonPhrase();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
