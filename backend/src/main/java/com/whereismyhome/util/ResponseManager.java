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
	
	protected ResponseEntity<?> createResponse(HttpStatus status)
	{
		return createResponse(status, null);
	}
}
