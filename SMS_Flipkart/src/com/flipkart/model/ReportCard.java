package com.flipkart.model;

import java.util.HashMap;

public class ReportCard {
	int reportCardId;
	int studentId;
	HashMap<String, Integer> reports;
	/**
	 * @return the reportCardId
	 */
	public int getReportCardId() {
		return reportCardId;
	}
	/**
	 * @param reportCardId the reportCardId to set
	 */
	public void setReportCardId(int reportCardId) {
		this.reportCardId = reportCardId;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the reports
	 */
	public HashMap<String, Integer> getReports() {
		return reports;
	}
	/**
	 * @param reports the reports to set
	 */
	public void setReports(HashMap<String, Integer> reports) {
		this.reports = reports;
	}
	
}
