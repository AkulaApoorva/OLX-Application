package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidStatusIdException extends RuntimeException{
	private String msg;

	public InvalidStatusIdException() {
		this.msg = "";
	}

	public InvalidStatusIdException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Invalid Status id " + this.msg;
	}
}
