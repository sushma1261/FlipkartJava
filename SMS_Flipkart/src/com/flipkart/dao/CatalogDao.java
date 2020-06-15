package com.flipkart.dao;

import java.util.ArrayList;

import com.flipkart.model.Course;

public interface CatalogDao {
	public ArrayList<Course> viewCatalog();
	public Course viewCourseFromCatalog(int courseId);
}
