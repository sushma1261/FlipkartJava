package com.flipkart.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.dao.StudentDao;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.model.Course;
import com.flipkart.model.Student;


// Student service operations are implemented here.
public class StudentOperation implements StudentInterface {

	private static Logger logger = Logger.getLogger(StudentOperation.class);
	
	StudentDao studentDao = new StudentDaoImpl();
	
	@Override
	public String chooseCourse(Student student, int courseId) {
		try {
			studentDao.chooseCourse(student, courseId);
		}catch(CourseNotFoundException ce) {
			logger.error(ce.getCourseName() + "not found");
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String dropCourse(Course course, Student student) {
		try {
			studentDao.dropCourse(course.getCourseId(), student.getStudentId());
		}catch(CourseNotFoundException ce) {
			logger.error(ce.getCourseName() + "not found");
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void viewRegisteredCourses(Student student) {
		ArrayList<Course> courseList = studentDao.viewRegisteredCourses(student);
		if(courseList.size() == 0) {
			logger.info("No registered courses");
		}
		else {
			logger.info("Course Id\tCourse Name");
			courseList.forEach(course -> logger.info(course.getCourseId() +  "\t\t " + course.getCourseName()));
		}
	}

	@Override
	public void payFees(int studentId, Student student, int amount) {
		
		
	}

	@Override
	public void viewGrades(Student student) {
		studentDao.viewGrades(student).forEach(
				(k,v) -> logger.info(k + "\t" + v)
				);
	}

	@Override
	public int numberOfRegisteredCourse(Student student) {
		return studentDao.numberOfRegisteredCourse(student);
	}

	

}
