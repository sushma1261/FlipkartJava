// Stream Flatmap Demo code 

package com.flipkart.java8;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFlatMapDemo {

	public static void main(String args[]) {

		List<String> javaVersionList = new ArrayList<>();
		javaVersionList.add("Java 7");
		javaVersionList.add("Java 8");
		javaVersionList.add("Java 9");
		javaVersionList.add("Java 10");
		javaVersionList.add("Java 11");
		javaVersionList.add("Java 12");
		
		System.out.println(" javaVersionList  \\t    : " + javaVersionList);

		List<String> javaVersionUpperCaseList = 
				javaVersionList.stream().
				flatMap(javaVersion -> Stream.of(javaVersion.toUpperCase(),javaVersion.toLowerCase(),javaVersion.concat(" JFF")))
				.collect(Collectors.toList());
		System.out.println("javaVersionUpperCaseList: " + javaVersionUpperCaseList);
		
		/*
		o/p
		 [Java 7, Java 8, Java 9, Java 10, Java 11, Java 12]
				 
				 [JAVA 7, java 7, Java 7 JFF,
				  JAVA 8, java 8, Java 8 JFF, 
				  JAVA 9, java 9, Java 9 JFF, 
				  JAVA 10, java 10, Java 10 JFF,
				  JAVA 11, java 11, Java 11 JFF, 
				  JAVA 12, java 12, Java 12 JFF]
				  */
	}

}
