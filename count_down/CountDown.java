package count_down;
/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016年10月18日 上午3:10:13
* @version V1.0  
* Description: 倒计时简易实现，只用单线程
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
