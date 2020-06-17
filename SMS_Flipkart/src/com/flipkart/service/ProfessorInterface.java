package com.flipkart.service;

import com.flipkart.model.Professor;

// Professor Interface
public interface ProfessorInterface extends UserInterface{
	
	// View students in professor's course
	void viewStudents(Professor professor);
	
	//Grade a student
	String gradeStudent(Professor professor, int studentId, String grades, int courseId);
	
	// Get Courses Taught
	public void getCourseTaught(Professor professor);

}
