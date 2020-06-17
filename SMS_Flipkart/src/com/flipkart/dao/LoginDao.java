package com.flipkart.dao;

import com.flipkart.model.Admin;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

public interface LoginDao {
	
	// Login check 
	// returns role
	String login(String username, String password);
	
	// register as student
	void registerStudent(Student student, String password);
	
	// register as professor
	void registerProfessor(Professor professor, String password);
	
	// register as admin
	public void registerAdmin(Admin admin, String password);
	
}
