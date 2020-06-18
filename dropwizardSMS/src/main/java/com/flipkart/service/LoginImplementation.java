package com.flipkart.service;

import com.flipkart.dao.LoginDaoImpl;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

public class LoginImplementation implements LoginInterface {

	public String login(String username, String password) throws UserNotFoundException {
		
		LoginDaoImpl loginDaoImpl = new  LoginDaoImpl();
		
		String role = loginDaoImpl.login(username, password);
		
		return role;
	}

	public void registerStudent(Student student, String password) {
		LoginDaoImpl loginDaoImpl = new  LoginDaoImpl();
		
		loginDaoImpl.registerStudent(student, password);
		
	}
	
	public void registerProfessor(Professor professor, String password) {
		LoginDaoImpl loginDaoImpl = new  LoginDaoImpl();
		
		loginDaoImpl.registerProfessor(professor, password);
	}

}