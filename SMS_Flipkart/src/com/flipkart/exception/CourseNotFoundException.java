package com.flipkart.exception;

// Exception class for course not found exception
public class CourseNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	String courseName;

	// Constructor to initialize courseName
	public CourseNotFoundException(String courseName) {
		super();
		this.courseName = courseName;
	}


	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	
	

}
