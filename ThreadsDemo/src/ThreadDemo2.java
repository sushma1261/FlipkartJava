

class Runnable2 implements Runnable {
	String name;
	
	public Runnable2(String name) {
		super();
		System.out.println("Created thread " + name);
		this.name = name;
	}

	public void run() {
		System.out.println("Started thread: " + name);
		try {
			for (int i = 0; i < 3; i++) {
				System.out.println( name + " = " + i);
					Thread.sleep(500);
			}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

class MyThread extends Thread {
	public void run() {
		System.out.println("Created a thread extending thread class");
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Running Thread");
	}
}

public class ThreadDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread run1 = new Thread(new Runnable2("Thread 1"));
		run1.start();
		MyThread t1 = new MyThread();
		t1.start();
		Thread run2 = new Thread(new Runnable2("Thread 2"));
		run2.start();
		
	}	

}
