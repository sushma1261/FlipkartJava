package com.flipkart.dao;

import java.util.ArrayList;

import com.flipkart.model.Course;

//Interface for catalog dao to view courses  
public interface CatalogDao {
	// View all courses in catalog
	public ArrayList<Course> viewCatalog();
	
	// View details of particular course
	public Course viewCourseFromCatalog(int courseId);
}
