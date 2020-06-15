package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.contants.SQLQueriesConstants;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.utils.DBUtil;

public class LoginDaoImpl implements LoginDao {

	private static Logger logger = Logger.getLogger(LoginDaoImpl.class);
	Connection connection = DBUtil.getConnection();
	
	
	// Login Check
	@Override
	public String login(String username, String password) {
		
		PreparedStatement stmt = null;
		String role = "";
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.LOGIN_QUERY);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
//				logger.info(rs.getString("username"));
//				logger.info(rs.getString("password"));
//				logger.info(rs.getString("role"));
				role = rs.getString("role");
			}
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		
		return role;
	}

	@Override
	public void registerProfessor(Professor professor, String password) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.REGISTER_PROFESSOR_QUERY);
			stmt.setInt(1, professor.getProfessorId());
			stmt.setString(2, professor.getName());
			stmt.setString(3, professor.getGender());
			int rows = stmt.executeUpdate();
			if(rows > 0) {
				registerUser(professor.getProfessorId(), professor.getName(), "professor", password);
			}
			else {
				logger.info("Couldn't register please try again!!");
			}
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
	}

	
	// Register student
	@Override
	public void registerStudent(Student student, String password) {
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.REGISTER_STUDENT_QUERY);
			stmt.setInt(1,student.getStudentId());
			stmt.setString(2, student.getName());
			stmt.setString(3, student.getBranch());
			if(student.isHasScholarship())
				stmt.setInt(4, 1);
			else
				stmt.setInt(4,0);
			stmt.setString(5, student.getGender());
			stmt.setInt(6, student.getSemester());
			int rows = stmt.executeUpdate();
			if(rows > 0) {
				registerUser(student.getStudentId(), student.getName(), "student", password);
			}
			else {
				logger.info("Couldn't register please try again!!");
			}
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		
	}
	
	
	public void registerUser(int userId, String username, String role, String password) {
		PreparedStatement insertInUserTablestmt = null;
		try {
			insertInUserTablestmt = connection.prepareStatement(SQLQueriesConstants.REGISTER_USER_QUERY);
			insertInUserTablestmt.setInt(1, userId);
			insertInUserTablestmt.setString(2, username);
			insertInUserTablestmt.setString(3, password);
			insertInUserTablestmt.setString(4, role);
			int rows1 = insertInUserTablestmt.executeUpdate();
			if(rows1 > 0) {
				logger.info("Successfully registered");
			}
			else {
				logger.info("Couldn't register please try again!!");
			}
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
}
