package com.whereismyhome.controller.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.whereismyhome.config.exception.InternalServerErrorException;
import com.whereismyhome.config.exception.NotFoundErrorException;
import com.whereismyhome.config.exception.UnauthorizedErrorException;
import com.whereismyhome.util.ResponseManager;

@RestControllerAdvice
public class GlobalExceptionAdvice extends ResponseManager{

	@ExceptionHandler
	public ResponseEntity<?> handleExceptions(Exception e) {
		System.out.println(e.getMessage());
		return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
	}
	
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<?> handleInternalServerErrorException(Exception e) {
		System.out.println(e.getMessage());
		return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
	
	@ExceptionHandler(NotFoundErrorException.class)
	public ResponseEntity<?> handleNotFoundErrorException(Exception e) {
		System.out.println(e.getMessage());
		return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
	}
	
	@ExceptionHandler(UnauthorizedErrorException.class)
	public ResponseEntity<?> handleUnauthorizedErrorException(Exception e) {
		System.out.println(e.getMessage());
		return createResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
	}
}