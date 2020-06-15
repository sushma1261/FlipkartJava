package com.flipkart.exception;

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	
	String username;
	
	// Constructor to initialize username  
	public UserNotFoundException(String username) {
		super();
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	
}
