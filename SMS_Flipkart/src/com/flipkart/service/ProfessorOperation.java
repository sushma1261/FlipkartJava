package com.flipkart.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.dao.ProfessorDao;
import com.flipkart.dao.ProfessorDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

// Class that implements ProfessorInterface
public class ProfessorOperation implements ProfessorInterface{

	ProfessorDao professorDao = new ProfessorDaoImpl();
	private static Logger logger = Logger.getLogger(ProfessorOperation.class);
	@Override
	public void viewStudents(Professor professor) {
		professorDao.viewStudents(professor);
			
	}
	
	@Override
	public String gradeStudent(Professor professor, int studentId, String grades, int courseId)  {
		professorDao.gradeStudent(professor, courseId, studentId, grades);
		return null;
	}

	@Override
	public void getCourseTaught(Professor professor) {
		logger.info("--------------Course List--------------");
		logger.info("Course Id\tCourse Name\t\tCourse Description");
		List<Course> courseIdList = professorDao.getCourseTaught(professor);
		courseIdList.forEach(course -> 
			logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t\t" + course.getDescription())
		);
		logger.info("----------------------------------------");
	}
	

}
