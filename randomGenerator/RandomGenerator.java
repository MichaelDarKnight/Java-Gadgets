package randomGenerator;
/**
* @see
* @author Al_assad 2415875132@qq.com
* @date 2016��9��23�� ����9:14:03
* @version V3.0  
* Description: �����������������Ϊ��
* 				��ÿһ������������ڲ�����һ�������ɵ��Զ������͵��б�
*/

import java.util.*;

interface RandomGenerator<K,T> {
	

	
	/**��ȡ����б�*/
	public K getContent();
	
	/**��������������ڲ��زģ�ֱ������һ���µ������*/
	public T newElement();
	
	/**ʹ�ñ�׼�����ӡ�������ڲ�������б�*/
	public void printContent();
	/**ʹ�ñ�׼�����ӡ�������ڲ�������б��ָ�����Ȳ���*/
	public void printContent(int length);
	
	/**ʹ��ָ����ʽ��ӡ�������ڲ�������б���Ĭ���ļ���*/
	public void writeIntoFile() throws java.io.IOException;
	/**ʹ��ָ����ʽ��ӡ�������ڲ�������б��ָ�����Ȳ��֣���Ĭ���ļ���*/
	public void writeIntoFile(int length) throws java.io.IOException;
	

	

}
