package com.flipkart.service;

import com.flipkart.dao.LoginDaoImpl;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.model.Admin;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

// Class that implements LoginInterface 
public class LoginImplementation implements LoginInterface {

	LoginDaoImpl loginDaoImpl = new  LoginDaoImpl();
	
	@Override
	public String login(String username, String password) throws UserNotFoundException {
		String role = loginDaoImpl.login(username, password);
		return role;
	}

	@Override
	public void registerStudent(Student student, String password) {
		loginDaoImpl.registerStudent(student, password);
	}
	
	@Override
	public void registerProfessor(Professor professor, String password) {
		loginDaoImpl.registerProfessor(professor, password);
	}

	@Override
	public void registerAdmin(Admin admin, String password) {
		loginDaoImpl.registerAdmin(admin, password);	
	}
	
	

}