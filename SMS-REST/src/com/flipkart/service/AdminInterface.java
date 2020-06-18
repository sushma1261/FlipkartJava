package com.flipkart.service;

import java.util.List;

import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.User;

public interface AdminInterface extends UserInterface{
	
	
	//Assign course to a professor
	public String assignProfessor(Professor professor, int courseId);
	
	
	//Add a new course in the catalog
	public String addNewCourseInCatalog(Course course);
	
	// View Users
	public List<User> viewUsers();
	
	public void deleteCourse(Course course);
	
}
