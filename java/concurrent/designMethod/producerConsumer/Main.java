import java.text.*;
import java.util.Random;
import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
public class Main{
	public static void main(String[] args) throws InterruptedException{
		BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>(10); //memory cache

		Producer producer1 = new Producer(queue);
		Producer producer2 = new Producer(queue);
		Producer producer3 = new Producer(queue);

		Consumer consumer1 = new Consumer(queue);
		Consumer consumer2 = new Consumer(queue);
		Consumer consumer3 = new Consumer(queue);

		ExecutorService service = Executors.newCachedThreadPool(); //thread pool
		service.execute(producer1);
		service.execute(producer2);
		service.execute(producer3);

		service.execute(consumer1);
		service.execute(consumer2);
		service.execute(consumer3);

		Thread.sleep(10*1000);
		producer1.stop();
		producer2.stop();
		producer3.stop();
		Thread.sleep(3000);
		service.shutdown();
	}
}
