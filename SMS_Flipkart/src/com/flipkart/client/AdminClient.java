package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.model.Admin;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;
import com.flipkart.service.LoginImplementation;

// This is admin client class that gives different options to admin
public class AdminClient {
	private static Logger logger = Logger.getLogger(AdminClient.class);
	AdminInterface adminOperation = new AdminOperation();
	LoginImplementation loginImpl = new LoginImplementation();
	Scanner sc = new Scanner(System.in);
	
	// Display menu for the admin
	public void displayMenu() {
		
		int choice;
		do {
			showChoices();
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
				case 1:
					adminOperation.viewCatalog();
					break;
				case 2:
					adminOperation.viewUsers();
					break;
				case 3:
					assignProfessor();
					break;
				case 4:
					registerNewUser();
					break;
				case 5:
					deleteUser();
					break;
				case 6:
					addNewCourse();
					break;
				case 7:
					deleteCourse();
					break;
				case 0:
					UserClient.logout();
			}
			
		}while(UserClient.loggedIn);
		sc.close();
	}
	
	// Show available choices
	void showChoices() {
		
		logger.info("Enter your choice:");
		logger.info("1. To view courses in catalog");
		logger.info("2. To view users");
		logger.info("3. Assign course to a professor");
		logger.info("4. Register a new user");
		logger.info("5. Delete a user");
		logger.info("6. Add a new course to catalog");
		logger.info("7. Drop a course from catalog");
		logger.info("0. To logout");
		
	}
	
	// Gather required inputs to assign a course with a professor
	void assignProfessor() {
		
		logger.info("Enter professor Id");
		int professorId = Integer.parseInt(sc.nextLine());
		Professor professor = new Professor();
		professor.setProfessorId(professorId);
		logger.info("Enter course Id");
		int courseId = Integer.parseInt(sc.nextLine());
		
		adminOperation.assignProfessor(professor, courseId);
	}
	
	// Gather required inputs to add new courses
	void addNewCourse() {
		
		Course course = new Course();
		logger.info("Enter course Id");
		int courseId = Integer.parseInt(sc.nextLine());
		course.setCourseId(courseId);
		logger.info("Enter course Name");
		String courseName = sc.nextLine();
		course.setCourseName(courseName);
		logger.info("Enter catalog Id");
		int catalogId = Integer.parseInt(sc.nextLine());
		course.setCatalogId(catalogId);
		logger.info("Enter Description");
		String description = sc.nextLine();
		course.setDescription(description);
		logger.info("Enter fee for the course");
		int fees = Integer.parseInt(sc.nextLine());
		course.setFees(fees);
		
		adminOperation.addNewCourseInCatalog(course);
	}
	
	// Gather course id to delete a course
	void deleteCourse() {
		logger.info("Enter course Id");
		Course course = new Course();
		int courseId = Integer.parseInt(sc.nextLine());
		course = new Course();
		course.setCourseId(courseId);
		adminOperation.deleteCourse(course);
	}
	
	// Gather required inputs to add a new user
	void registerNewUser() {
		
		logger.info("Enter 1 to add a new admin");
		logger.info("Enter 2 to add a new professor");
		logger.info("Enter 3 to add a new student");
		int option = Integer.parseInt(sc.nextLine());
		
		switch(option) {
		
			case 1:
				Admin admin = new Admin();
				logger.info("Enter admin id:");
				admin.setAdminId(Integer.parseInt(sc.nextLine()));
				logger.info("Enter admin name:");
				admin.setName(sc.nextLine());
				logger.info("Enter password");
				String password = sc.nextLine();
				logger.info("Enter gender: 'm' for male and 'f' for female");
				String gender = sc.nextLine();
				if(gender.equals("m")) {
					admin.setGender("male");
				}
				else {
					admin.setGender("female");
				}
				loginImpl.registerAdmin(admin, password);
				break;
				
			case 2:
				Professor professor = new Professor();
				logger.info("Enter professor id:");
				professor.setProfessorId(Integer.parseInt(sc.nextLine()));
				logger.info("Enter professor name:");
				professor.setName(sc.nextLine());
				logger.info("Enter password");
				password = sc.nextLine();
				logger.info("Enter gender: 'm' for male and 'f' for female");
				gender = sc.nextLine();
				if(gender.equals("m")) {
					professor.setGender("male");
				}
				else {
					professor.setGender("female");
				}
				
				loginImpl.registerProfessor(professor, password);
				break;
				
			case 3:
				Student student = new Student();
				logger.info("Enter student id:");
				student.setStudentId(Integer.parseInt(sc.nextLine()));
				logger.info("Enter student name:");
				student.setName(sc.nextLine());
				logger.info("Enter password");
				password = sc.nextLine();
				logger.info("Enter branch:");
				student.setBranch(sc.nextLine());
				logger.info("Enter gender: 'm' for male and 'f' for female");
				gender = sc.nextLine();
				if(gender.equals("m")) {
					student.setGender("male");
				}
				else {
					student.setGender("female");
				}
				logger.info("Enter semester:");
				student.setSemester(Integer.parseInt(sc.nextLine()));
				logger.info("Enter 1 if student has scholarship or 0 if student doesn't have any scholarship:");
				int hasScholarship = Integer.parseInt(sc.nextLine());
				if(hasScholarship == 1) {
					student.setHasScholarship(true);
				}
				else {
					student.setHasScholarship(false);
				}
				loginImpl.registerStudent(student, password);
				break;
		}
	}
	
	void deleteUser() {
		logger.info("Enter user id:");
		adminOperation.deleteUser(Integer.parseInt(sc.nextLine()));
	}
	
}
