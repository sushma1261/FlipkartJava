package com.flipkart.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.dao.AdminDao;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.User;

public class AdminOperation implements AdminInterface {

	private static final Logger logger = LoggerFactory.getLogger(AdminOperation.class);

	AdminDao adminDao = new AdminDaoImpl();
	
	public String assignProfessor(Professor professor, int courseId) {
		try {
			adminDao.assignProfessor(professor, courseId);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public String addNewCourseInCatalog(Course course) {
		adminDao.addNewCourseInCatalog(course);
		return null;
	}
	
	// View Users present
	public List<User> viewUsers() {
		AdminDao adminDao = new AdminDaoImpl();
		List<User> userList = adminDao.viewUsers();
		return userList;
		
	}

	public void deleteCourse(Course course) {
		adminDao.dropCourse(course);
		
	}

}
