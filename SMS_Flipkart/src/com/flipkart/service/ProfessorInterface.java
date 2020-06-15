package com.flipkart.service;

import com.flipkart.model.Professor;

public interface ProfessorInterface extends UserInterface{
	
	
	void viewStudents(Professor professor);
	
	//Grade a student
	String gradeStudent(Professor professor, int studentId, String grades, int courseId);
	
	public void getCourseIdsTaught(Professor professor);

}
