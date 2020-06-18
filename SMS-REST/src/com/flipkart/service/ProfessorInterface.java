package com.flipkart.service;

import java.util.List;

import com.flipkart.model.Professor;
import com.flipkart.model.Student;

public interface ProfessorInterface extends UserInterface{
	
	
	List<Student> viewStudents(Professor professor);
	
	//Grade a student
	String gradeStudent(Professor professor, int studentId, String grades, int courseId);
	
	public void getCourseIdsTaught(Professor professor);

}
