package com.flipkart.service;


import java.util.ArrayList;
import java.util.Map;

import com.flipkart.model.Course;
import com.flipkart.model.Student;

public interface StudentInterface extends UserInterface {
	
	int numberOfRegisteredCourse(Student student);
	
	// Insert a particular course chosen by student
	String chooseCourse(Student student, int courseId);
	
	//Delete course chosen by student
	String dropCourse(Course course, Student student);
	
	// View Registered Courses
	ArrayList<Course> viewRegisteredCourses(Student student);
		
	
	Map<String, String> viewGrades(Student studentId);
	
	public int calculateTotalFee(Student student);
	
	public void makePayment(Student student, int paymentMethod, int fees);
}
