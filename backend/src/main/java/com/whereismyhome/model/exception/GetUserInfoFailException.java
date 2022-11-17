package com.whereismyhome.model.exception;

public class GetUserInfoFailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GetUserInfoFailException(String message)
	{
		super(message);
	}
	
	public GetUserInfoFailException() {
		super();
	}
}
