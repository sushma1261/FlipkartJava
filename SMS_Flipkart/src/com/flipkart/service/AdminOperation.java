package com.flipkart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.dao.AdminDao;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.User;

// Admin service class that implements AdminInterface
public class AdminOperation implements AdminInterface {

	private static Logger logger = Logger.getLogger(AdminOperation.class);
	AdminDao adminDao = new AdminDaoImpl();
	@Override
	public String assignProfessor(Professor professor, int courseId) {
		try {
			adminDao.assignProfessor(professor, courseId);
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public String registerProfessor(Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addNewCourseInCatalog(Course course) {
		adminDao.addNewCourseInCatalog(course);
		return null;
	}
	
	// View Users present
	@Override
	public void viewUsers() {
		AdminDao adminDao = new AdminDaoImpl();
		List<User> userList = adminDao.viewUsers();
		
		logger.info("--Professors are:--");
		printUsersBasedOnRole(userList, "professor");
		
		logger.info("--Students are:--");
		printUsersBasedOnRole(userList, "student");
		
		logger.info("--Admins are:--");
		printUsersBasedOnRole(userList, "admin");
		
	}

	@Override
	public void deleteCourse(Course course) {
		adminDao.dropCourse(course);
		
	}
	
	// print user details based on role
	public void printUsersBasedOnRole(List<User> userList, String role) {
		userList.stream().filter(user -> user.getRole().equals(role))
		.collect(Collectors.toList())
		.forEach(user -> logger.info(user.getUserId() + "\t" + user.getUsername()));
	}

}
