package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.dao.ProfessorDaoImpl;
import com.flipkart.model.Professor;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;


public class ProfessorClient {
	
	private static Logger logger = Logger.getLogger(ProfessorClient.class);
	
	ProfessorInterface professorOperation = new ProfessorOperation();

	Scanner sc = new Scanner(System.in);
	
	public void displayMenu(Professor professor) {
		
		
		int choice;
		
		
		do {
			logger.info("Enter your choice:");
			logger.info("1. To view available courses");
			logger.info("2. To view courses taught");
			logger.info("3. To view students in a course");
			logger.info("4. Grade a student");
			logger.info("0. To logout");
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1:
					professorOperation.viewCatalog();
					break;
				case 2:
					professorOperation.getCourseIdsTaught(professor);
					break;
				case 3:
					professorOperation.viewStudents(professor);
					break;
				case 4:
					logger.info("Enter course Id: ");
					int courseId = Integer.parseInt(sc.nextLine());
					if(ProfessorDaoImpl.checkValidCourseForProfessor(professor, courseId)) {
						logger.info("Enter student Id: ");
						int studentId = Integer.parseInt(sc.nextLine());
						if(ProfessorDaoImpl.checkValidCourseForStudent(studentId, courseId)) {
							logger.info("Enter grades:");
							String grades = sc.nextLine();
							professorOperation.gradeStudent(professor, studentId, grades, courseId);
						}
						else {
							logger.info("Student has not registered for this course");
						}
					}
					else {
						logger.info("You cannot grade for that course");
					}
					
					break;
				case 0:
					UserClient.logout();
			}
			
		}while(UserClient.loggedIn);
		sc.close();	
	}

}
