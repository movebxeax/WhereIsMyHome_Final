package com.whereismyhome.model.exception;

public class DeleteUserFailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DeleteUserFailException(String message)
	{
		super(message);
	}
	
	public DeleteUserFailException() {
		super();
	}
}
