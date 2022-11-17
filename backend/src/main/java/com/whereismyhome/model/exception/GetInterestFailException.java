package com.whereismyhome.model.exception;

public class GetInterestFailException extends Exception{
	private static final long serialVersionUID = 1L;

	public GetInterestFailException(String message)
	{
		super(message);
	}
	
	public GetInterestFailException() {
		super();
	}
}
