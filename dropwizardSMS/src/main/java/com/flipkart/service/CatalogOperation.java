package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.dao.CatalogDao;
import com.flipkart.dao.CatalogDaoImpl;
import com.flipkart.model.Course;

public class CatalogOperation implements CatalogInterface {

	CatalogDao catalogDao = new CatalogDaoImpl();
	public ArrayList<Course> searchAndFetchDetails() {
		
		return catalogDao.viewCatalog();
	}
	public Course viewCourseFromCatalog(int courseId) {
		return catalogDao.viewCourseFromCatalog(courseId);
	}
	

}
