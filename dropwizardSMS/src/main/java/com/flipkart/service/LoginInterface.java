package com.flipkart.service;

import com.flipkart.exception.UserNotFoundException;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

public interface LoginInterface {
	
	// Check login details from database and return the respective role
	// throws UserNotfoundException
	public String login(String username, String password) throws UserNotFoundException;
	
	public void registerStudent(Student student, String password);
	
	public void registerProfessor(Professor professor, String password);
}