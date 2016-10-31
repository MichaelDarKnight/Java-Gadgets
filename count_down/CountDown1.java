package count_down;
/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��10��18�� ����2:14:43
* @version V1.0  
* Description: ����ʱʵ�ַ�ʽ1��ʹ��ScheduledExecutorʵ��
* 								ʹ�������̣߳�
*/
import java.util.concurrent.*;

public class CountDown1 {
	private volatile int limitSec ; //��¼����ʱʱ��
	private int curSec;   //��¼����ʱ����ʱ��
	public CountDown1(int limitSec) throws InterruptedException{
		this.limitSec = limitSec;
		this.curSec = limitSec;
		System.out.println("count down form "+limitSec);
		
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(new Task(),0,1,TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(limitSec);   //��ͣ���߳�
		exec.shutdown();
		System.out.println("Time out��");
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
