package com.flipkart.service;

import com.flipkart.dao.LoginDaoImpl;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

public class LoginImplementation implements LoginInterface {

	@Override
	public String login(String username, String password) throws UserNotFoundException {
		
		LoginDaoImpl loginDaoImpl = new  LoginDaoImpl();
		
		String role = loginDaoImpl.login(username, password);
		
		return role;
	}

	@Override
	public void registerStudent(Student student, String password) {
		LoginDaoImpl loginDaoImpl = new  LoginDaoImpl();
		
		loginDaoImpl.registerStudent(student, password);
		
	}
	
	@Override
	public void registerProfessor(Professor professor, String password) {
		LoginDaoImpl loginDaoImpl = new  LoginDaoImpl();
		
		loginDaoImpl.registerProfessor(professor, password);
	}

}