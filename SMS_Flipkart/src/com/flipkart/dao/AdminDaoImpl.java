package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.contants.SQLQueriesConstants;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.User;
import com.flipkart.utils.DBUtil;

// Class that implements all methods in admin dao
public class AdminDaoImpl implements AdminDao {

	private static Logger logger = Logger.getLogger(AdminDaoImpl.class);
	Connection connection = DBUtil.getConnection();
	
	
	// Returns list of users registered to application
	@Override
	public ArrayList<User> viewUsers() {
		PreparedStatement stmt = null;
		
		ArrayList<User> userList = new ArrayList<User>();
		
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.VIEW_USERS_QUERY);
			ResultSet rs = stmt.executeQuery(SQLQueriesConstants.VIEW_USERS_QUERY);
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setRole(rs.getString(3));
				userList.add(user);
			}
			
		}catch(SQLException se) {
			logger.info(se.getMessage());
			
		}catch(Exception e) {
			logger.info(e.getMessage());
		}
		return userList;
	}


	// Assigns a professor with a particular course
	@Override
	public void assignProfessor(Professor professor, int courseId) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.ASSIGN_PROFESSOR_QUERY);
			stmt.setInt(2, professor.getProfessorId());
			stmt.setInt(1, courseId);
			int rows = stmt.executeUpdate();
			logger.info(rows + " updated");
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
	}


	// Add new course
	@Override
	public String addNewCourseInCatalog(Course course) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.ADD_NEW_COURSE_QUERY);
			stmt.setInt(1,course.getCourseId());
			stmt.setString(2, course.getCourseName());
			stmt.setInt(3, course.getFees());
			stmt.setString(4, course.getDescription());
			stmt.setInt(5,course.getCatalogId());
			int rows = stmt.executeUpdate();
			logger.info(rows + " course added");
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}


	// Drop existing course
	@Override
	public void dropCourse(Course course) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.DELETE_COURSE_QUERY);
			stmt.setInt(1,course.getCourseId());
			int rows = stmt.executeUpdate();
			logger.info(rows + " deleted");
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}


	@Override
	public void deleteUser(int userId) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.DELETE_USER_QUERY);
			stmt.setInt(1,userId);
			int rows = stmt.executeUpdate();
			logger.info(rows + " deleted");
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
}
