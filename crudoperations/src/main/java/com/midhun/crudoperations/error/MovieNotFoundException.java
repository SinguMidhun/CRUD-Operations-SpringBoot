package com.midhun.crudoperations.error;


public class MovieNotFoundException extends Exception{
	public MovieNotFoundException(String message) {
		super(message);
	}
}
