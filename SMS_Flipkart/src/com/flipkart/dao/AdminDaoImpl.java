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

public class AdminDaoImpl implements AdminDao {

	private static Logger logger = Logger.getLogger(AdminDaoImpl.class);
	Connection connection = DBUtil.getConnection();
	
	
	
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
				user.setRole(rs.getString(4));
				userList.add(user);
			}
			
		}catch(SQLException se) {
			logger.info(se.getMessage());
			
		}catch(Exception e) {
			logger.info(e.getMessage());
		}
		return userList;
	}



	@Override
	public void assignProfessor(Professor professor, int courseId) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.ASSIGN_PROFESSOR_QUERY);
			stmt.setInt(1, professor.getProfessorId());
			stmt.setInt(2, courseId);
			int rows = stmt.executeUpdate();
			logger.info(rows + " updated");
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
	}



	@Override
	public String addNewCourseInCatalog(Course course) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.ADD_NEW_COURSE_QUERY);
			stmt.setInt(1,course.getCourseId());
			stmt.setString(2, course.getCourseName());
			int rows = stmt.executeUpdate();
			logger.info(rows + " updated");
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}



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
	
}
