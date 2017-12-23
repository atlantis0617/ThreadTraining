package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author atlantis0617
 *Volatile的修饰符不执行互斥访问，但是可以保证一个线程在读取该域的时候就讲看到最近刚刚被写入的值。
 */
public class StopThread2 {
	 
	private static volatile boolean stopRequest;
	
	static Thread backgroundThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			int i = 0;
			while(!stopRequest){
				System.err.println(i++);
				i++;
			}
		}
	});
	public static void main(String[] args) throws InterruptedException {
		backgroundThread.start();
		TimeUnit.SECONDS.sleep(1);
		stopRequest = true;
	}
}
