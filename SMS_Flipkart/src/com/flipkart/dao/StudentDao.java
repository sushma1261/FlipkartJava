package com.flipkart.dao;

import java.util.ArrayList;
import java.util.Map;

import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Student;


public interface StudentDao {
	
	// To choose courses
	public boolean chooseCourse(Student student, int courseId) throws CourseNotFoundException;
	
	// Get Student Details
	public Student getStudentDetails(String name);
	
	
	// Student registered courses
	public ArrayList<Course> viewRegisteredCourses(Student student);
	
	// Delete course selected
	public void dropCourse(int courseId, int studentId) throws CourseNotFoundException;
	
	// Check if student has registered for a course
	public boolean checkRegisteredCourses(Student student, int courseId);
	
	// Get number of registered courses
	public int numberOfRegisteredCourse(Student student);
	
	// View Grades
	public Map<String, String> viewGrades(Student student);
	
	// Calculate fees
	public int calculateTotalFee(Student student);
	
	//Proceed for payment
	public String makePayment(Student student, int paymentMethod, int fees);
}

