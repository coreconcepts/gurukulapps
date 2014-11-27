package com.follett.connector.util;

public class StatusBean {

	private int errorCode;
	private String errorMessage;
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		
	}

	public void setErrorMessage(String message) {
		this.errorMessage = message;
		
	}

	public int getErrorCode() {
		return errorCode;
		
	}

}
