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
	
	public List<Student> viewStudents(Professor professor){
		PreparedStatement stmt = null;
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_STUDENTS_TAUGHT);
			stmt.setInt(1, professor.getProfessorId());
			ResultSet rs = stmt.executeQuery();
			logger.info("--------------Student List--------------");
			logger.info("Course Id \t Student Id\tStudent Name\tBranch\tgender\tSemester");
			while(rs.next()) {
				logger.info(rs.getInt("courseId") + "\t\t" + rs.getInt("studentId") + "\t\t" + rs.getString("studentName") + "\t\t" + rs.getString("branch") + "\t" + rs.getString("gender") + "\t" + rs.getInt("semester"));
			}
			
			logger.info("----------------------------------------");
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return studentList;
	}
	
	public Student getStudentDetails(int studentId) {
		PreparedStatement stmt = null;
		Student student = new Student();
		
		try {
		
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_NAME_QUERY);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				student.setName(rs.getString("studentName"));
				student.setStudentId(rs.getInt("studentId"));
				student.setBranch(rs.getString("branch"));
				student.setGender(rs.getString("gender"));
				student.setSemester(rs.getInt("semester"));
			}
			
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return student;
			
	}
	
	@Override
	public List<Course> getCourseTaught(Professor professor) {
		Set<Integer> courseIdList = getCourseIdTaught(professor);
		CatalogDao catalogDao = new CatalogDaoImpl();
		List<Course> courseList = new ArrayList<Course>();
		courseIdList.forEach(
				courseId ->   {
					Course c = catalogDao.viewCourseFromCatalog(courseId);
					courseList.add(c);
				}
				);
		return courseList;
	}
	
	public Set<Integer> getStudentIdWithCourseId(int courseId) {
		PreparedStatement stmt = null;
		Set<Integer> studentIdList = new HashSet<Integer>();
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_IDS_FOR_COURSE_QUERY);
			stmt.setInt(1, courseId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				studentIdList.add(rs.getInt(1));
			}
			
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return studentIdList;
	}
	
		Set<Integer> getCourseIdTaught(Professor professor) {
		PreparedStatement stmt = null;
		Set<Integer> courseList = new HashSet<Integer>();
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.GET_COURSE_TAUGHT_BY_PROFESSOR);
			stmt.setInt(1,professor.getProfessorId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				courseList.add(rs.getInt(1));
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
