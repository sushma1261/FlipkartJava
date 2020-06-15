package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.dao.ProfessorDao;
import com.flipkart.dao.ProfessorDaoImpl;
import com.flipkart.dao.StudentDao;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.service.LoginImplementation;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class UserClient {

	private static Logger logger = Logger.getLogger(UserClient.class);
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		logger.info("Welcome to student management system!!");
		UserClient.showMainUserMenu();
		logger.info("Exited");
		sc.close();
	}
	public static boolean loggedIn; 
	public static boolean showMenu; 
	public static void showMainUserMenu() {
		showMenu = true;
		int choice;
		do {
			logger.info("Enter 1 to login");
			logger.info("Enter 2  to register as a student");
			logger.info("Enter any other number to exit");
			choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
				case 1:
					new UserClient().login();
					break;
					
				case 2:
					try {
						new UserClient().registerStudent();
						break;
					}catch(Exception e) {
						logger.error(e.getMessage());
					}
				default:
					choice = 3;
					showMenu = false;
					loggedIn = false;
					DBUtil.closeConnection();
					logger.error("--------------------Exiting--------------------");
					break;
			}
		}while(showMenu);
	}
	
	public void registerStudent() {
		Student student = new Student();
		logger.info("Enter student id");
		student.setStudentId(Integer.parseInt(sc.nextLine()));
		logger.info("Enter student name");
		student.setName(sc.nextLine());
		logger.info("Enter password");
		String password = sc.nextLine();
		logger.info("Enter branch");
		student.setBranch(sc.nextLine());
		logger.info("Enter gender: 'm' for male and 'f' for female");
		student.setGender(sc.nextLine());
		logger.info("Enter semester:");
		student.setSemester(Integer.parseInt(sc.nextLine()));
		logger.info("If you have scholarship enter 1, else enter 0");
		int scholarShip = Integer.parseInt(sc.nextLine());
		if(scholarShip == 1)
			student.setHasScholarship(true);
		else
			student.setHasScholarship(false);
		LoginImplementation loginImpl = new LoginImplementation();
		loginImpl.registerStudent(student, password);
	}

	public void login() {
//		boolean loggedOut = true;
		do {
			logger.info("Enter username");
			String username = sc.nextLine();
			logger.info("Enter password");
			String password = sc.nextLine();
			LoginImplementation loginImpl = new LoginImplementation();
			try {
				String role = loginImpl.login(username, password);
				loggedIn = true;
				logger.info("Successfully logged in as " + role);
				logger.info("Logged in at: " + DateTimeUtil.getDateTime());
				switch(role) {
					case "student":
						StudentClient studentClient = new StudentClient();
						StudentDao studentDao = new StudentDaoImpl();
						// get student details from db
						Student student = studentDao.getStudentDetails(username);	
						// displayMenu and perform student menu operations
						studentClient.displayMenu(student);
						break;
						
					case "professor":
						ProfessorClient professorClient = new ProfessorClient();
						ProfessorDao professorDao = new ProfessorDaoImpl();
						Professor professor = professorDao.getProfessorDetails(username);
						// get professor details from db
						
						// displayMenu and perform professor menu operations
						professorClient.displayMenu(professor);
						break;
						
					case "admin":
						AdminClient adminClient = new AdminClient();
						adminClient.displayMenu();
						break;
						
					default:
						logger.debug("No such user");
				}
				
			}catch (UserNotFoundException e) {
				logger.error(e.getUsername() + " not found!!");
			}catch (Exception e) {
//				logger.info("error");
				logger.error(e.getMessage());
			}
			
		}while(loggedIn);
	}
	
	public static void logout() {
		UserClient.loggedIn = false;
		logger.info("--------------------Logged out at: " + DateTimeUtil.getDateTime() + "--------------------");
		UserClient.showMainUserMenu();
	}
	
}
