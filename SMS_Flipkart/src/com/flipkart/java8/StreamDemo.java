// StreamDEMO
 package com.flipkart.java8;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

	public static void main(String args[]) {
		List<Integer> javaVersionList = new ArrayList<>();
		javaVersionList.add(7);
		javaVersionList.add(8);
		javaVersionList.add(9);
		javaVersionList.add(10);
		javaVersionList.add(11);
		javaVersionList.add(12);

		beforeJava8(javaVersionList);
		fromJava8(javaVersionList);

	}

	private static void beforeJava8(List<Integer> javaVersionList) {

		List<Integer> evenList = new ArrayList<>();//Temporary List
		for (Integer javaVersion : javaVersionList) {
			if (javaVersion % 2 == 0) {

				evenList.add(javaVersion);
			}

		}

		System.out.println("beforeJava8 Java version even  list" + evenList);

	}

	private static void fromJava8(List<Integer> javaVersionList) {

		
		// Creating Stream from Collection(List)
		Stream<Integer> javaVersionStream = javaVersionList.stream();//List -->Stream

		//Stream is used for processing object from collections, processing means filtering from collection
		
		Stream<Integer> evenJavaVersionStream = javaVersionStream.filter(javaVersion -> javaVersion % 2 == 0);// Output type is stream
		
		
		List<Integer> evevJavaVersionList = evenJavaVersionStream.collect(Collectors.toList());// Output type is List
		
		//					OR  ( We can traverse Stream only once, if we run both , it will give runtime exception)
		//Set<Integer> evevJavaVersionSet = evenJavaVersionStream.collect(Collectors.toSet());// Output type is Set 
			
		
		System.out.println("fromJava8 javaVersionEvenList : " + evevJavaVersionList);
	//	System.out.println("fromJava8 evevJavaVersionSet : " + evevJavaVersionSet);
		
		
		/*
		 * javaVersionList.stream() .filter(javaVersionEvenList -> javaVersionEvenList %
		 * 2 == 0) .collect(Collectors.toList());
		 */
		
		


	}

}

    
    
