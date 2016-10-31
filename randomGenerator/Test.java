package randomGenerator;
/**
* @see
* @author Al_assad 2415875132@qq.com
* @date 2016年9月23日 上午9:31:20
* @version V1.0  
* Description:
*/

public class Test {
	static final int ONE = 0;
	static final int ALL = 1;
	static final int RANDOM = 2;
	public Test(int style){
		switch(style){
		case ONE:System.out.println(0);break;
		case ALL:System.out.println(0);break;
		case RANDOM:System.out.println(0);break;
		default:
		}
	}
	public static void main(String[] args){
		new Test(ONE);
	}

}
