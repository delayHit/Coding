import java.util.concurrent.*;
import java.util.ArrayList;

public class CountTask extends RecursiveTask<Long> {
	private static final int THRESHOLD = 10000;
	private long start;
	private long end;

	public CountTask(long start, long end){
		this.start = start;
		this.end = end;
	}

	public Long compute(){
		long sum = 0;
		boolean canCompute = (end-start)<THRESHOLD;
		System.out.println("start:"+start+" end:"+end);
		if (canCompute){
			for (long i=start;i<=end;i++){
				sum+=i;
			}
		} else {
			// div into 100 small task
			System.out.println("div into 100 small task");
			long step = (start+end)/100;
			ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
			long pos = start;
			for (int i=0;i<100;i++){
				long lastOne=pos+step;
				if(lastOne>end)
					lastOne = end;
				CountTask subTask = new CountTask(pos,lastOne);
				System.out.println("new CountTask("+pos+","+lastOne+")");
				pos+=step+1;
				subTasks.add(subTask);
				subTask.fork();
			}
			for (CountTask t:subTasks){
				System.out.println("join:"+t.join());
				sum+=t.join();
			}
		}
		return sum;
	}

	public static void main(String[] args){
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		CountTask task = new CountTask(0,200000L);
		ForkJoinTask<Long> result = forkJoinPool.submit(task);
		try{
			long res = result.get();
			System.out.println("sum="+res);
		} catch (InterruptedException e){
			e.printStackTrace();
		} catch (ExecutionException e){
			e.printStackTrace();
		}
	}
}
