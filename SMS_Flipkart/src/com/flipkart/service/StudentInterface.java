package com.flipkart.service;


import com.flipkart.model.Course;
import com.flipkart.model.Student;

public interface StudentInterface extends UserInterface {
	
	int numberOfRegisteredCourse(Student student);
	
	// Insert a particular course chosen by student
	String chooseCourse(Student student, int courseId);
	
	//Delete course chosen by student
	String dropCourse(Course course, Student student);
	
	// View Registered Courses
	void viewRegisteredCourses(Student student);
	
	
	void payFees(int studentId, Student student, int amount);
	
	
	void viewGrades(Student studentId);
	
	public int calculateTotalFee(Student student);
	
	public void makePayment(Student student, int paymentMethod, int fees);
}
