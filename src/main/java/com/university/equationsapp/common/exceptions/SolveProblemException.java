package com.university.equationsapp.common.exceptions;

public class SolveProblemException extends Exception {

	private static final long serialVersionUID = -1L;

	private String message = "This is an exception..";

	public SolveProblemException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
