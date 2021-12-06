package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCategoryIdException extends RuntimeException {
	private String msg;

	public InvalidCategoryIdException() {
		this.msg = "";
	}

	public InvalidCategoryIdException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Invalid Category id " + this.msg;
	}

}