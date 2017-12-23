package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author atlantis0617
 *对于解决这个问题的方法有两种：其一是通过锁机制，其二是通过volatile修饰符。
 *可以发现，写方法和读方法都被同步了，只同步写方法是不能实现，如果没有对读和写方法都进行同步的话，同步是不会起作用的。
 */
public class StopThread {
	private static boolean stopRequested;
	
	static Thread backgroudThread = new Thread(new Runnable() {
		@Override
		public void run() {
			int i = 0;
			while(!stopRequested){
				System.out.println(i++);
				i++;
			}
		}
	});
	
	private static synchronized void requestStop(){
		stopRequested = true;
	}
	
	@SuppressWarnings("unused")
	private static synchronized boolean stopRequest(){
		return stopRequested;
	}
	public static void main(String[] args) throws InterruptedException {
		backgroudThread.start();
		TimeUnit.SECONDS.sleep(1);
		requestStop();
	}
}
