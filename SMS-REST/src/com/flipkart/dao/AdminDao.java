package com.flipkart.dao;

import java.util.ArrayList;

import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.User;


public interface AdminDao {
	
	// View Users from database
	public ArrayList<User> viewUsers();
	
	// Assign course to professor
	public void assignProfessor(Professor professor, int courseId);
	
	// Add new courses to catalog
	public String addNewCourseInCatalog(Course course);
	
	// Delete course from catalog
	public void dropCourse(Course course);
	
	// Register new professor
	
	// Delete a user
}
