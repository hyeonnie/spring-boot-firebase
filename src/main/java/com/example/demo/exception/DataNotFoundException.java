package com.example.demo.exception;

public class DataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6754767023122935455L;

	public DataNotFoundException() {
		super();
	}
	
	@Override
	public String getMessage() {
		return "DataNotFoundException";
	}
	
}
