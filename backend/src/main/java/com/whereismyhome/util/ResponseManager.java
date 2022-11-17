package com.whereismyhome.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseManager {
	protected ResponseEntity<?> createResponse(HttpStatus status, Object response)
	{
		if(status == HttpStatus.OK)
		{
			return ResponseEntity.ok().body(response);
		}
		else
		{
			return ResponseEntity.status(status).body(response);
		}
	}
	
	protected ResponseEntity<?> createResponse(Object response)
	{
		if(response != null)
		{
			return ResponseEntity.ok().body(response);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	protected ResponseEntity<?> createResponse(HttpStatus status)
	{
		return createResponse(status, null);
	}
}
