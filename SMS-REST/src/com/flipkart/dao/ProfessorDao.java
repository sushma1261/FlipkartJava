package com.flipkart.dao;

import java.util.List;

import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

public interface ProfessorDao {
	public List<Student> viewStudents(Professor professor);
	
	public Professor getProfessorDetails(String username);
	
	public List<Course> getCourseTaught(Professor professor);
	
	
	
	public void gradeStudent(Professor professor, int courseId, int studentId, String grade );
	
}
