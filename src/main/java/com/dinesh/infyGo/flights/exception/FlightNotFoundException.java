package com.dinesh.infyGo.flights.exception;

public class FlightNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	private String errorCode;

	public FlightNotFoundException(String code, String message) {
		super(message);
		this.errorCode = code;
	}

	public FlightNotFoundException(String message) {
		super(message);

	}
}
