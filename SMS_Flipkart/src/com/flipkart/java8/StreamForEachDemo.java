package com.flipkart.java8;
import java.util.ArrayList;
import java.util.List;

public class StreamForEachDemo {

	public static void main(String args[]) {
		List<Integer> javaVersionList = new ArrayList<>();
		javaVersionList.add(7);
		javaVersionList.add(8);
		javaVersionList.add(9);
		javaVersionList.add(10);
		javaVersionList.add(11);
		javaVersionList.add(12);

		System.out.println("fromJava8 - Java even versions: ");
		 javaVersionList
		 .stream()
		 .filter(javaVersionEvenList -> javaVersionEvenList % 2 == 0)
		 .forEach(System.out::println);

	}

}
