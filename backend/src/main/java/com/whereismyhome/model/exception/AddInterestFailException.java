package com.whereismyhome.model.exception;

public class AddInterestFailException extends Exception{
	private static final long serialVersionUID = 1L;

	public AddInterestFailException(String message)
	{
		super(message);
	}
	
	public AddInterestFailException() {
		super();
	}
}
