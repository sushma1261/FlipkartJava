package com.flipkart.dao;

import java.util.ArrayList;
import java.util.Map;

import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Student;


public interface StudentDao {
	
//	// Get All courses from DB
//	public ArrayList<Course> viewCatalog();
	
	// To choose courses
	public boolean chooseCourse(Student student, int courseId) throws CourseNotFoundException;
	
	// Get Student Details
	public Student getStudentDetails(String name);
	
	
	// Student registered courses
	public ArrayList<Course> viewRegisteredCourses(Student student);
	
	// Delete course selected
	public void dropCourse(int courseId, int studentId) throws CourseNotFoundException;
	
	public boolean checkRegisteredCourses(Student student, int courseId);
	
	public int numberOfRegisteredCourse(Student student);
	
	public Map<String, String> viewGrades(Student student);
}
