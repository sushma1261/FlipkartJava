package com.flipkart.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.model.Course;

public interface UserInterface {
	
	// default function for viewing catalog
	default public void viewCatalog() {
		
		Logger logger = Logger.getLogger(UserInterface.class);
		
		CatalogInterface catalogOperation = new CatalogOperation();
		
		List<Course> courses = catalogOperation.searchAndFetchDetails();
		logger.info("Course Id\tCourse Name\tFees\tCourse Description");
		
		courses.forEach(course -> logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + " \t\t"  + course.getFees() + "\t" + course.getDescription()));
	}

	
	
}
