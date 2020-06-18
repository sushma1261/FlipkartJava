package com.flipkart.model;

public class Student {
	int studentId;
	String name;
	String branch;
	boolean hasScholarship;
	String gender;
	int semester;
	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param string the studentId to set
	 */
	public void setStudentId(int string) {
		this.studentId = string;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the hasScholarship
	 */
	public boolean isHasScholarship() {
		return hasScholarship;
	}
	/**
	 * @param hasScholarship the hasScholarship to set
	 */
	public void setHasScholarship(boolean hasScholarship) {
		this.hasScholarship = hasScholarship;
	}
	
	
}
