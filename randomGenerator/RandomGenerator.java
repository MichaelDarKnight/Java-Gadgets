package randomGenerator;
/**
* @see
* @author Al_assad 2415875132@qq.com
* @date 2016年9月23日 上午9:14:03
* @version V3.0  
* Description: 描述随机生成器的行为，
* 				在每一个随机生成器内部都有一个已生成的自定义类型的列表；
*/

import java.util.*;

interface RandomGenerator<K,T> {
	

	
	/**获取随机列表*/
	public K getContent();
	
	/**由随机生成器的内部素材，直接生成一个新的随机量*/
	public T newElement();
	
	/**使用标准输出打印生成器内部的随机列表*/
	public void printContent();
	/**使用标准输出打印生成器内部的随机列表的指定长度部分*/
	public void printContent(int length);
	
	/**使用指定方式打印生成器内部的随机列表，到默认文件中*/
	public void writeIntoFile() throws java.io.IOException;
	/**使用指定方式打印生成器内部的随机列表的指定长度部分，到默认文件中*/
	public void writeIntoFile(int length) throws java.io.IOException;
	

	

}
