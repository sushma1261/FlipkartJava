package com.flipkart.java8;

import java.util.ArrayList;
import java.util.stream.Collectors;

class Student1 {
	String name;
	String gender;
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
	public Student1(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
	}
	
}

public class StreamDemoFilter {
	public static String returnTitle(Student1 s) {
		if(s.getGender() == "female")
			return "Ms."+s.getName();
		else
			return "Mr." + s.getName();
	}
	public static void main(String[] args) {
		ArrayList<Student1> students = new ArrayList<Student1>();
		
		Student1 s1 = new Student1("Sushma", "female");
		Student1 s2 = new Student1("Rishi", "male");
		Student1 s3 = new Student1("Pradeep", "male");
		Student1 s4 = new Student1("Vishnu", "female");
		
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		
		// Names of Students Before Adding Titles
		System.out.println("Names of students before adding titles");
		
		students.forEach(student -> System.out.println(student.getName()));
		
		// Names of Students After Adding Titles
		System.out.println("\nNames of students after adding titles");
		
		students.stream()
				.map(s -> StreamDemoFilter.returnTitle(s))
				.collect(Collectors.toList())
				.forEach(student -> System.out.println(student));
	}
}
