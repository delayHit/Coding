import java.util.concurrent.*;
import java.util.concurrent.locks.*;
public class StampedLockCPUDemo{
	static Thread[] holdCpuThreads = new Thread[3];
	static final StampedLock lock = new StampedLock();

	public static void main(String[] args) throws InterruptedException {
		new Thread(){
			public void run(){
				long readLong = lock.writeLock();
				LockSupport.parkNanos(600000000000L);
				lock.unlockWrite(readLong);
			}
		}.start();
		Thread.sleep(100);
		for(int i=0;i<3;i++){
			holdCpuThreads[i] = new Thread(new HoldCPUReadThread());
			holdCpuThreads[i].start();
		}
		Thread.sleep(10000);
		for(int i=0;i<3;i++){
			holdCpuThreads[i].interrupt();
		}
	}

	public static class HoldCPUReadThread implements Runnable{
		public void run(){
			long lockr = lock.readLock();
			System.out.println(Thread.currentThread().getName()+"get read lock");
			lock.unlockRead(lockr);
		}
	}
}
