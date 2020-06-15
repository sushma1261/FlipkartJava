package com.flipkart.threadsolution;

public class AccessClientThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Table obj = new Table();//only one object  
		MyThread1 t1=new MyThread1(obj);  
		MyThread2 t2=new MyThread2(obj);  
		t1.start();  
		t2.start();
		
	}

}
