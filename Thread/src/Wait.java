
public class Wait {
	
	
	public static void main(String[] args) throws InterruptedException {
		MyThread t1=new MyThread();
		t1.start();
		 t1=new MyThread();
		t1.start();
		
		Thread.sleep(1000);
		
	}

}


class MyThread extends Thread{
	
		public void run() {
			synchronized(this) {
				try {
					System.out.println("Wait ");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					notify();
				}
				System.out.println("Cool");
			}
			System.out.println("Cool");
		}
	
}
