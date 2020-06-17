package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.model.Course;
import com.flipkart.model.Student;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;

public class StudentClient {

	private static Logger logger = Logger.getLogger(StudentClient.class);
	
	public Student student;
	StudentInterface studentOperation = new StudentOperation();
	Scanner sc = new Scanner(System.in);

	// Displays the Student Menu with choices for student
	public void displayMenu(Student student) {
		int choice;
		do {
			this.student = student;
			showChoices();
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1:
					studentOperation.viewCatalog();
					break;
					
				case 2:
					chooseCourse();
					break;
					
				case 3:
					dropCourse();
					break;
				
				case 4:
					studentOperation.viewRegisteredCourses(student);
					break;
					
				case 5:
					payFees();
					break;
					
				case 6:
					printReportCard();
					break;
					
				case 0:
					UserClient.logout();
			}
			
		}while(UserClient.loggedIn);
		sc.close();	
		
		
	}
	void showChoices() {
		logger.info("Enter your choice:");
		logger.info("1. To view available courses");
		logger.info("2. To choose a course");
		logger.info("3. To drop a course");
		logger.info("4. View registered courses");
		logger.info("5. Pay fees");
		logger.info("6. View grades");
		logger.info("0. To logout");
	}
	
	void chooseCourse() {
		if(studentOperation.numberOfRegisteredCourse(student) >= 4) {
			logger.info("You cannot add courses as you have already selected 4 courses");
		}
		else {
			logger.info("Enter courseId");
			int courseId = Integer.parseInt(sc.nextLine());
			studentOperation.chooseCourse(student, courseId);
		}
		
	}
	
	void dropCourse() {
		Course course1 = new Course();
		logger.info("Enter course id:");
		int courseId = Integer.parseInt(sc.nextLine());
		course1.setCourseId(courseId);
		studentOperation.dropCourse(course1, student);
	}
	
	void printReportCard() {
		logger.info("--------Report Card--------");
		logger.info("Course\tGrade");
		studentOperation.viewGrades(student);
		logger.info("---------------------------");
	}
	
	void payFees() {
		// Get amount to be paid
		int fee = studentOperation.calculateTotalFee(student);
		logger.info("Want to continue to pay Rs."+ fee + " press 'y' to continue...");
		if(sc.nextLine().equals("y")) {
			logger.info("Enter 1 to pay with credit card.");
			logger.info("Enter 2 to pay with debit card.");
			logger.info("Enter 3 to pay as cash.");
			int choice = Integer.parseInt(sc.nextLine());
			if(choice >= 1 && choice <= 3) {
				studentOperation.makePayment(student, choice, fee);
			}
			else {
				logger.info("Cannot do such payment!!");
			}
		}
		else {
			logger.info("Payment not done.");
		}
	}

}
