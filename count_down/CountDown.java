package count_down;
/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��10��18�� ����3:10:13
* @version V1.0  
* Description: ����ʱ����ʵ�֣�ֻ�õ��߳�
*/
import java.util.*;
import java.util.concurrent.*;

public class CountDown {
	private int limitSec;
	public CountDown(int limitSec) throws InterruptedException{
		this.limitSec = limitSec;
		System.out.println("Count from "+limitSec);
		while(limitSec > 0){
			System.out.println("remians "+ --limitSec +" s");
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println("Time is out");
	}
	/*public static void main(String[] args) throws InterruptedException {
		new CountDown(10);
	}*/

}
