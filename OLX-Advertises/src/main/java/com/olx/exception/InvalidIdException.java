package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidIdException extends RuntimeException{

	private String msg;

	public InvalidIdException() {
		this.msg = "";
	}

	public InvalidIdException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Invalid " + this.msg ;
	}
}