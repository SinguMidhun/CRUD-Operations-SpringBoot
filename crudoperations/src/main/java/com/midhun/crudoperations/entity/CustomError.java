package com.midhun.crudoperations.entity;


import org.springframework.stereotype.Component;


@Component
public class CustomError {
	
	private String errorMessage;

	public CustomError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public CustomError() {

	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
