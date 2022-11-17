package com.whereismyhome.model.exception;

public class SetInterestDistrictFailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SetInterestDistrictFailException(String message)
	{
		super(message);
	}
	
	public SetInterestDistrictFailException() {
		super();
	}
}
