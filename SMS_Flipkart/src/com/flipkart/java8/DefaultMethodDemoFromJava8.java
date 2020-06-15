package com.flipkart.java8;

interface DefaultMethodDemo2 {
	default public int addTwoNumber(int number1, int number2) {
		int sum = number1 + number2;// Common Code
		return sum;
	}

	default public int multiplyTwoNumber2(int number1, int number2) {
		int result = number1 * number2;// Common Code
		return result;
	}
}


interface DefaultMethod extends DefaultMethodDemo2 {
	
}


class Class1 implements DefaultMethod {
	public int addNumbers(int number1, int number2) {
		// reuse common code
		return addTwoNumber(number1, number2);
	}
}

class Class2 implements DefaultMethodDemo2 {
	// It is not compulsory to implement default mehtod

}

class Class3 implements DefaultMethodDemo2 {
	public int addNumbers(int number1, int number2) {
		// reuse common code
		return addTwoNumber(number1, number2);
	}
}

public class DefaultMethodDemoFromJava8 {

	public static void main(String args[]) {

		Class1 class1 = new Class1();
		int sum = class1.addNumbers(100, 200);
		System.out.println("Sum of two numbers is: " + sum);

	}
}
