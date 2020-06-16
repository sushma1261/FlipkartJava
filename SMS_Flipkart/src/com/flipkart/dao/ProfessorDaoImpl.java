package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.flipkart.contants.SQLQueriesConstants;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.utils.DBUtil;

public class ProfessorDaoImpl implements ProfessorDao {
	private static Logger logger = Logger.getLogger(ProfessorDaoImpl.class);
	
	static Connection connection = DBUtil.getConnection();
	
	public void viewStudents(Professor professor){
		PreparedStatement stmt = null;
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_STUDENTS_TAUGHT);
			stmt.setInt(1, professor.getProfessorId());
			ResultSet rs = stmt.executeQuery();
			if(rs != null) {
				logger.info("--------------Student List--------------");
				logger.info("Course Id \t Student Id\tStudent Name\tBranch\tgender\tSemester");
				while(rs.next()) {
					logger.info(rs.getInt("courseId") + "\t\t" + rs.getInt("studentId") + "\t\t" + rs.getString("studentName") + "\t\t" + rs.getString("branch") + "\t" + rs.getString("gender") + "\t" + rs.getInt("semester"));
				}
				logger.info("----------------------------------------");
			}
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		
	}
	
	@Override
	public List<Course> getCourseTaught(Professor professor) {
		List<Course> courseList = new ArrayList<Course>();
		PreparedStatement stmt = null; 
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_COURSE_TAUGHT_BY_PROFESSOR);
			stmt.setInt(1, professor.getProfessorId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setDescription(rs.getString(3));
				courseList.add(course);
			}
			
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return courseList;
	}
		
	

	@Override
	public Professor getProfessorDetails(String username) {
		Professor professor = new Professor();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_PROFESSOR_DETAILS_QUERY);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				professor.setName(rs.getString(2));
				professor.setProfessorId(rs.getInt(1));
				professor.setGender(rs.getString(3));
			}
			else {
				throw new UserNotFoundException(username + " not found!");
			}
			
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(UserNotFoundException ue) {
			logger.error(ue.getMessage()+ " not found");
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return professor;
	}

	public static boolean checkValidCourseForProfessor(Professor professor, int courseId) {
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.VALID_COURSE_FOR_PROFESSOR);
			stmt.setInt(1, professor.getProfessorId());
			stmt.setInt(2, courseId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			stmt.close();
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return count >= 1;
		
	}
	
	public static boolean checkValidCourseForStudent(int studentId, int courseId) {
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.VALID_STUDENT_COURSE);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			stmt.close();
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return count >= 1;
		
	}

	@Override
	public void gradeStudent(Professor professor, int courseId, int studentId, String grade) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GRADE_STUDENT_QUERY);
			stmt.setString(1, grade);
			stmt.setInt(2, studentId);
			stmt.setInt(3, courseId);
			int rowsUpdated = stmt.executeUpdate();
			if(rowsUpdated > 0) {
				logger.info("Uploaded grade");
			}
			else {
				logger.info("Couldn't upload try again");
			}
			stmt.close();
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		
		
	}
	
	
}
