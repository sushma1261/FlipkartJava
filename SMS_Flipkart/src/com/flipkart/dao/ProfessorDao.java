package com.flipkart.dao;

import java.util.List;

import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

//Interface for professor dao 
public interface ProfessorDao {
	
	// View students in professor's course
	public void viewStudents(Professor professor);
	
	// Get professor Details
	public Professor getProfessorDetails(String username);
	
	// View courses taught
	public List<Course> getCourseTaught(Professor professor);
	
	// Grade Student
	public void gradeStudent(Professor professor, int courseId, int studentId, String grade );
	
}
