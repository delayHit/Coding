public class BadLockOnInteger implements Runnable {
	public static Integer i = 0;
	static BadLockOnInteger instance = new BadLockOnInteger();

	@Override
	public void run() {
		for (int j=0; j<10000000; j++) {
			synchronized(instance) {
			//synchronized(i) {
				i++;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
