package com.whereismyhome.model.exception;

public class UserInfoModifyFailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserInfoModifyFailException(String message)
	{
		super(message);
	}
	
	public UserInfoModifyFailException() {
		super();
	}
}
