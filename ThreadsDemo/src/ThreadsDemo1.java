
class RunnableClass implements  Runnable {

	String name;
	Thread t;
	
	
	public RunnableClass(String name) {
		super();
		System.out.println("Created "+ name);
		this.name = name;
	}


	@Override
	public void run() {
		
		try {
			for (int i = 0; i < 5; i++) {
			System.out.println(name + " " + i );
			
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	void start() {
		System.out.println("Starting " +  name );
	      if (t == null) {
	         t = new Thread (this, name);
	         t.start ();
	      }
	}
	
}

public class ThreadsDemo1 {
	public static void main(String[] args) {
		RunnableClass r1 = new RunnableClass("Thread1");
		r1.start();
		
		RunnableClass r2 = new RunnableClass("Thread2");
		r2.start();
		
		RunnableClass r3 = new RunnableClass("Thread3");
		r3.start();
	}
}
