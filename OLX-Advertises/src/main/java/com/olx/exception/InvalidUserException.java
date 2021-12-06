package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {
	private String message;

	public InvalidUserException(String message) {
		super();
		this.message = message;
	}
	public InvalidUserException() {
		this.message="";
	}
	@Override
	public String toString() {
		return "Invalid User" + this.message;
	}

}
