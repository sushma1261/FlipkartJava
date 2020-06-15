package com.flipkart.java8;

interface DefaultMethodDemo {
	public int addTwoNumber(int number1, int number2);
}




class ClassOne implements DefaultMethodDemo {
	public int addTwoNumber(int number1, int number2) {
		int sum = number1 + number2;// Common Code
		return sum;
	}
}

class ClassTwo implements DefaultMethodDemo {
	public int addTwoNumber(int number1, int number2) {
		int sum = number1 + number2;// Common Code
		return sum;
	}

}

class ClassThree implements DefaultMethodDemo {
	public int addTwoNumber(int number1, int number2) {
		int sum = number1 + number2;// Common Code
		return sum;
	}
}

public class DefaultMethodDemoBeforeJava8 {

	public static void main(String args[]) {

		ClassOne class1 = new ClassOne();
		int sum = class1.addTwoNumber(100, 200);
		System.out.println("Sum of two numbers is: " + sum);

	}
}
