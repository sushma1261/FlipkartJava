package com.flipkart.service;

import java.util.ArrayList;
import java.util.Map;

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
	public ArrayList<Course> viewRegisteredCourses(Student student) {
		ArrayList<Course> courseList = studentDao.viewRegisteredCourses(student);
		if(courseList.size() == 0) {
			logger.info("No registered courses");
		}
		else {
			logger.info("Course Id\tCourse Name");
			courseList.forEach(course -> logger.info(course.getCourseId() +  "\t\t " + course.getCourseName()));
		}
		return courseList;
	}


	@Override
	public Map<String, String> viewGrades(Student student) {
		return studentDao.viewGrades(student);
	}

	@Override
	public int numberOfRegisteredCourse(Student student) {
		return studentDao.numberOfRegisteredCourse(student);
	}

	@Override
	public int calculateTotalFee(Student student) {
		return studentDao.calculateTotalFee(student);
	}
	
	@Override
	public void makePayment(Student student, int paymentMethod, int fees) {
		logger.info(studentDao.makePayment(student, paymentMethod, fees));
	}
	

}
