package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.contants.SQLQueriesConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.utils.DBUtil;

public class CatalogDaoImpl implements CatalogDao {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogDaoImpl.class);
	
	Connection connection = DBUtil.getConnection();
	
	public ArrayList<Course> viewCatalog() {
		
		PreparedStatement stmt = null;
		// Store all courses from result set
		ArrayList<Course> courseList = new ArrayList<Course>();
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.VIEW_CATALOG_QUERY);
			ResultSet rs = stmt.executeQuery(SQLQueriesConstants.VIEW_CATALOG_QUERY);
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
				course.setDescription(rs.getString("courseDescription"));
				course.setFees(rs.getInt("fees"));
				courseList.add(course);
			}
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		
		return courseList;
	}
	
	public Course viewCourseFromCatalog(int courseId) {
		PreparedStatement stmt = null;
		Course course = null;
		try {
			stmt = connection.prepareStatement(SQLQueriesConstants.VIEW_COURSE_QUERY);
			stmt.setInt(1, courseId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				course = new Course();
				course.setCourseId(courseId);
				course.setCourseName(rs.getString("courseName"));
				course.setDescription(rs.getString(4));
				course.setFees(rs.getInt("fees"));
			}
			else {
				throw new CourseNotFoundException("course id: " + courseId + "not found");
			}
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(CourseNotFoundException ce){
			logger.error(ce.getMessage());
		}catch(Exception e){
		  logger.error(e.getMessage());
		}
		return course;
		
	}
}
