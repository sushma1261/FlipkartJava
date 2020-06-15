package com.flipkart.model;

public class Course {
	int courseId;
	String courseName;
	String professorAssigned;
	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the professorAssigned
	 */
	public String getProfessorAssigned() {
		return professorAssigned;
	}
	/**
	 * @param professorAssigned the professorAssigned to set
	 */
	public void setProfessorAssigned(String professorAssigned) {
		this.professorAssigned = professorAssigned;
	}
	
}
