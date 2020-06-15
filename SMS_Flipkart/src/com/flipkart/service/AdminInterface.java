package com.flipkart.service;

import com.flipkart.model.Course;
import com.flipkart.model.Professor;

public interface AdminInterface extends UserInterface{
	
	
	//Assign course to a professor
	public String assignProfessor(Professor professor, int courseId);
	
	//Register a professor
	public String registerProfessor(Professor professor);
	
	//Add a new course in the catalog
	public String addNewCourseInCatalog(Course course);
	
	// View Users
	public void viewUsers();
	
	public void deleteCourse(Course course);
	
}
