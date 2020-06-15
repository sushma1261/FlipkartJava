package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.dao.CatalogDao;
import com.flipkart.dao.CatalogDaoImpl;
import com.flipkart.model.Course;

public class CatalogOperation implements CatalogInterface {

	CatalogDao catalogDao = new CatalogDaoImpl();
	@Override
	public ArrayList<Course> searchAndFetchDetails() {
		
		return catalogDao.viewCatalog();
	}
	@Override
	public Course viewCourseFromCatalog(int courseId) {
		return catalogDao.viewCourseFromCatalog(courseId);
	}
	

}
