package randomGenerator;

/**
* @see AbstractPlaceGenerator.java  Place.java
* @author Al_assad 2415875132@qq.com
* @date 2016年9月24日 下午4:51:32
* @version V1.0  
* Description: 中国省份和城市生成器
*/

import java.util.*;

public class RandomChinaGenerator extends AbstractPlaceGenerator {
	
	private List<String> keyList = new ArrayList<String>(); /**作为原始素材Map随机化获取元素的引用*/
	private Map<String,String> kyMap = new HashMap<String,String>();  /**记录原始素材键值对关系*/
	private String fileURL = "metrial/Province_City_World/Province_City_China.txt";	
	
	public RandomChinaGenerator(){
		this(20);
	}
	public RandomChinaGenerator(int size){
		outputURL = "output_RandomChinaGenerator.txt";	
		
		loadFile(fileURL,kyMap,keyList);
		//对size长度进行容错
		if(size > kyMap.size())
			size = kyMap.size();
		this.size = size;
		createRandomChinaMap();
	
	}
	
	/**创建元素不允许重复的随机元素集*/
	private void createRandomChinaMap(){
		Collections.shuffle(keyList);
		for(int i=0;i<size;i++){
			String key = keyList.get(i);
			map.put(key,kyMap.get(key));
		}
	}

	
	
	/**获取一个与内部容器无关的元素,只定义外部调用*/
	@Override
	public Place newElement(){
		Random rand = new Random();
		int index = rand.nextInt(keyList.size());
		String key = keyList.get(index);
		return new Place(key,kyMap.get(key));
	}
	
	/**获取一组与内部容器无关的省份名称，只定义外部调用*/
	public List<String> getRandomKeyList(int length,boolean repeat){
		return getRandomKeyList(keyList,length,repeat);
	}
	public List<String> getRandomKeyList(int length){
		return getRandomKeyList(length,false);
	}
	
	/**获取一组与内部容器无关的省份名称，只定义外部调用*/
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
