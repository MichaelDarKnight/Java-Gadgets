package randomGenerator;

/**
* @see AbstractPlaceGenerator.java  Place.java
* @author Al_assad 2415875132@qq.com
* @date 2016��9��24�� ����4:51:32
* @version V1.0  
* Description: �й�ʡ�ݺͳ���������
*/

import java.util.*;

public class RandomChinaGenerator extends AbstractPlaceGenerator {
	
	private List<String> keyList = new ArrayList<String>(); /**��Ϊԭʼ�ز�Map�������ȡԪ�ص�����*/
	private Map<String,String> kyMap = new HashMap<String,String>();  /**��¼ԭʼ�زļ�ֵ�Թ�ϵ*/
	private String fileURL = "metrial/Province_City_World/Province_City_China.txt";	
	
	public RandomChinaGenerator(){
		this(20);
	}
	public RandomChinaGenerator(int size){
		outputURL = "output_RandomChinaGenerator.txt";	
		
		loadFile(fileURL,kyMap,keyList);
		//��size���Ƚ����ݴ�
		if(size > kyMap.size())
			size = kyMap.size();
		this.size = size;
		createRandomChinaMap();
	
	}
	
	/**����Ԫ�ز������ظ������Ԫ�ؼ�*/
	private void createRandomChinaMap(){
		Collections.shuffle(keyList);
		for(int i=0;i<size;i++){
			String key = keyList.get(i);
			map.put(key,kyMap.get(key));
		}
	}

	
	
	/**��ȡһ�����ڲ������޹ص�Ԫ��,ֻ�����ⲿ����*/
	@Override
	public Place newElement(){
		Random rand = new Random();
		int index = rand.nextInt(keyList.size());
		String key = keyList.get(index);
		return new Place(key,kyMap.get(key));
	}
	
	/**��ȡһ�����ڲ������޹ص�ʡ�����ƣ�ֻ�����ⲿ����*/
	public List<String> getRandomKeyList(int length,boolean repeat){
		return getRandomKeyList(keyList,length,repeat);
	}
	public List<String> getRandomKeyList(int length){
		return getRandomKeyList(length,false);
	}
	
	/**��ȡһ�����ڲ������޹ص�ʡ�����ƣ�ֻ�����ⲿ����*/
	public List<String> getRandomValueList(int length,boolean repeat){
		return getRandomValueList(keyList,kyMap,length,repeat);
	}
	public List<String> getRandomValueList(int length){
		return getRandomValueList(length,false);
	}
	
	
/*	public static void main(String[] args){
		RandomChinaGenerator generator = new RandomChinaGenerator(3);
//		System.out.println(generator.newElement());
		generator.printContent();
		generator.writeIntoFile();
		List<String> list = generator.getRandomKeyList(100,true);
		System.out.println(list+"\n"+list.size());
		
	}
*/

	
	
		
		
		
		
		

}
