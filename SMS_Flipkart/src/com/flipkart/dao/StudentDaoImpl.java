package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.flipkart.contants.SQLQueriesConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Student;
import com.flipkart.utils.DBUtil;

public class StudentDaoImpl implements StudentDao {
	
	private static Logger logger = Logger.getLogger(StudentDaoImpl.class);
	
	Connection connection = DBUtil.getConnection();
	CatalogDao catalogDao = new CatalogDaoImpl();
	
	// Add the course chosen by student which throws CourseNotFoundException
	// when the course doesn't exists in DB
	@Override
	public boolean chooseCourse(Student student, int courseId) throws CourseNotFoundException {
//		logger.info("choose courses");
		PreparedStatement stmt = null;
		if(numberOfRegisteredCourse(student) >= 4) {
			logger.info("You cannot add courses as you have already selected 4 courses");
		}
		else if(checkRegisteredCourses(student, courseId)) {
			logger.info("You have already selected this course");
		}
		 // Check if course in catalog
		else if(catalogDao.viewCourseFromCatalog(courseId) == null) {
				throw new CourseNotFoundException("course with id " + courseId);
			}
		else {
			try {
				
				stmt = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE_STUDENT_QUERY);
				stmt.setInt(1,student.getStudentId());
				stmt.setInt(2, courseId);
				stmt.setString(3, "regular");
				LocalDateTime dateTime = LocalDateTime.now();
				stmt.setObject(4, dateTime);
				int rows = stmt.executeUpdate();
				if(rows > 0) {
					logger.info("Added course sucessfully");
				}
				else {
					logger.info("Error during insertion");
				}
			}catch(SQLException se) {
				logger.error(se.getMessage());
			}catch(Exception e){
			  logger.error(e.getMessage());
			}
		}
		return false;
	}
	
	// Get Student Details from Database
	@Override
	public Student getStudentDetails(String name) {
		Student student = new Student();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_DETAILS_QUERY);
			stmt.setString(1,name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				student.setName(rs.getString(2));
				student.setStudentId(rs.getInt(1));
				student.setBranch(rs.getString(3));
				student.setGender(rs.getString(5));
				if(rs.getInt(4) != 0) {
					student.setHasScholarship(true);
				}
				else {
					student.setHasScholarship(false);
				}
			}
			else {
				throw new UserNotFoundException(name);
			}
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(UserNotFoundException ue) {
			logger.error(ue.getMessage()+ " not found");
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return student;
	}

	@Override
	public ArrayList<Course> viewRegisteredCourses(Student student) {
		ArrayList<Course> courseList = new ArrayList<Course>();
  		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_REGISTERED_COURSES_QUERY);
			stmt.setInt(1, student.getStudentId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				courseList.add(catalogDao.viewCourseFromCatalog(rs.getInt(2)));
			}
			
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return courseList;
	}
	
	

	@Override
	public void dropCourse(int courseId, int studentId) throws CourseNotFoundException {
		PreparedStatement stmt = null;
		if(catalogDao.viewCourseFromCatalog(courseId) == null) {
			throw new CourseNotFoundException("course with id " + courseId);
		}
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.DROP_COURSE_STUDENT_QUERY);
			stmt.setInt(1,courseId);
			stmt.setInt(2, studentId);
			logger.info("Id"+studentId);
			int rows = stmt.executeUpdate();
			logger.info("Rows deleted " + rows);
			stmt.close();
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
	}

	@Override
	public int numberOfRegisteredCourse(Student student) {
		int count = 0;
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.COUNT_REGISTERED_COURSES_QUERY);
			stmt.setInt(1, student.getStudentId());
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
		return count;
	}
	
	public boolean checkRegisteredCourses(Student student, int courseId) {
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.CHECK_IF_REGISTERED_TO_COURSE_QUERY);
			stmt.setInt(1, student.getStudentId());
			stmt.setInt(2,courseId);
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
		return count > 0;
		
	}

	@Override
	public Map<String, String> viewGrades(Student student) {
		Map<String, String> reports = new HashMap<String, String>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.VIEW_GRADES_QUERY);
			stmt.setInt(1, student.getStudentId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				reports.put(rs.getString(2), rs.getString(3));
			}
			stmt.close();
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return reports;
	}

	public int calculateTotalFee(Student student) {
		int totalFees = 0;
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.CALCULATE_TOTAL_FEE);
			stmt.setInt(1, student.getStudentId());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				totalFees = rs.getInt(1);
			}
			stmt.close();
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return totalFees;
		
	}
	
	public String makePayment(Student student, int paymentMethod, int fees) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.MAKE_PAYMENT_QUERY);
			stmt.setInt(1, student.getStudentId());
			stmt.setInt(2, fees);
			stmt.setInt(3, paymentMethod);
			LocalDateTime dateTime = LocalDateTime.now();
			stmt.setObject(4, dateTime);
			int rows = stmt.executeUpdate();
			if(rows > 0) {
				PreparedStatement stmt1 = null;
				try {
					stmt1 = connection.prepareStatement(SQLQueriesConstants.UPDATE_AFTER_PAYMENT);
					stmt1.setInt(1, student.getStudentId());
					if(stmt1.executeUpdate() > 0) {
						return "Payment successful";
					}
				}catch(SQLException se){
					logger.error(se.getMessage());
				}catch(Exception e){
				  logger.error(e.getMessage());
				}
			}
			stmt.close();
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return "Payment Failed";
	}
}
