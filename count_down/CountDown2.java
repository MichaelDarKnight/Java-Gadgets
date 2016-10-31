package count_down;
/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��10��18�� ����2:47:44
* @version V1.0  
* Description: ����ʱʵ�ַ�ʽ2��ʹ��java.uitl.Timerʵ��
* 								ʹ�������߳�
*/
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CountDown2 {
	private int limitSec;
	private int curSec;
	public CountDown2(int limitSec) throws InterruptedException{
		this.limitSec = limitSec;
		this.curSec = limitSec;
		System.out.println("count down from "+limitSec+" s ");
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				System.out.println("Time remians "+ --curSec +" s");
			}
		},0,1000);
		TimeUnit.SECONDS.sleep(limitSec);
		timer.cancel();
		System.out.println("Time is out!");
	}
	//Test
	public static void main(String[] args) throws InterruptedException{
		new CountDown2(10);
	}

}


