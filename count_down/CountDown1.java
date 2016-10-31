package count_down;
/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016年10月18日 上午2:14:43
* @version V1.0  
* Description: 倒计时实现方式1：使用ScheduledExecutor实现
* 								使用两个线程；
*/
import java.util.concurrent.*;

public class CountDown1 {
	private volatile int limitSec ; //记录倒计时时间
	private int curSec;   //记录倒计时当下时间
	public CountDown1(int limitSec) throws InterruptedException{
		this.limitSec = limitSec;
		this.curSec = limitSec;
		System.out.println("count down form "+limitSec);
		
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(new Task(),0,1,TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(limitSec);   //暂停本线程
		exec.shutdown();
		System.out.println("Time out！");
	}
	private class Task implements Runnable{
		public void run(){
			System.out.println("Time remains "+ --curSec +" s");
		}
	}
	//Test
	public static void main(String[] args) throws InterruptedException{
		new CountDown1(10);
	}
	

}
