package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.model.Course;

public interface CatalogInterface {
	
	// Fetch all available courses
	public ArrayList<Course> searchAndFetchDetails();
	
	public Course viewCourseFromCatalog(int courseId);
}
