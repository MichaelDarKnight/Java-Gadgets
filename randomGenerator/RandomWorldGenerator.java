package randomGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* @see AbstractPlaceGenerator.java  Place.java
* @author Al_assad 2415875132@qq.com
* @date 2016��9��24�� ����11:57:45
* @version V1.0  
* Description: ������Һ��׶�������

*/
import java.util.*;

public class RandomWorldGenerator extends AbstractPlaceGenerator{
	
	public static int ASIA = 0;
	public static int EUROPE = 1;
	public static int AFRICA = 2;
	public static int AMERICA = 3;
	public static int OCEANIA = 4;
	
	private List<ArrayList<String>> keyLists = new ArrayList<ArrayList<String>>();/**��Ϊԭʼ�ز�Map�������ȡԪ�ص�����*/
	private List<HashMap<String,String>> kyMaps = new ArrayList<HashMap<String,String>>();/**��¼ԭʼ�زļ�ֵ�Թ�ϵ*/
	private List<String> fileURLs = new ArrayList<String>(Arrays.asList(
			"randomGenerator/metrial/Country_City_World/Country_City_Asia.txt",
			"randomGenerator/metrial/Country_City_World/Country_City_Europe.txt",
			"randomGenerator/metrial/Country_City_World/Country_City_Africa.txt",
			"randomGenerator/metrial/Country_City_World/Country_City_America.txt",
			"randomGenerator/metrial/Country_City_World/Country_City_Oceania.txt"));
	
	/**��������Ĭ������Ϊ10������ģʽΪȫ�����������*/
	public RandomWorldGenerator(){
		this(10,0,1,2,3,4);
	}
	public RandomWorldGenerator(int size){
		this(size,0,1,2,3,4);
	}
	/**��������ָ������������ģʽ(ʹ���������еľ�̬��)*/
	public RandomWorldGenerator(int size,int...types){
		outputURL = "output_RandomWorldGenerator.txt";
		if(!checkTypes(types)){
			System.err.println(this.getClass().getSimpleName()+"\nError:The types is illegal!");
			System.exit(0);
		}
		for(int i=0;i<fileURLs.size();i++){
			keyLists.add(new ArrayList<String>());
			kyMaps.add(new HashMap<String,String>());
		}
		loadAllFile();
		//��ȡsize֮�󣬶�size�����ݴ���
		int sumSize = 0;
		for(ArrayList<String> list : keyLists)
			sumSize += list.size();
		if(size>sumSize)
			size = sumSize;
		this.size = size;
		createRandomWorldMap(types);
	}
	
	/**�������types�Ƿ�Ϸ�*/
	private boolean checkTypes(int[] types){
		int modelSize = fileURLs.size();
		int[] model = new int[modelSize];
		for(int i=0;i<types.length;i++){
			if(types[i] >= modelSize)
				return false;
			model[types[i]]++;
		}
		for(int i=0;i<model.length;i++){
			if(model[i]>1)
				return false;
		}
		return true;
	}
	
	
	
	/**װ�ض�ȡ�ز�*/
	private void loadAllFile( ){
		for(int i=0;i<fileURLs.size();i++){
			loadFile(fileURLs.get(i),kyMaps.get(i),keyLists.get(i));
		}
			
	}
	
	/**����ģʽ�����������Map*/
	private void  createRandomWorldMap(int[] types){
		List<String> sumkeyList  = new ArrayList<String> ();
		for(int i=0;i<types.length;i++){
			sumkeyList.addAll(keyLists.get(types[i]));
		}		
		Collections.shuffle(sumkeyList);
		for(int i=0;i<size;i++){
			String key = sumkeyList.get(i);
			map.put(key, kyMaps.get(getListIndexOfKey(key)).get(key));
		}
	}
	/**����ĳ��key���ڵ��ز�keyLists��keyList���*/
	private int getListIndexOfKey(String key){
		for(int i=0;i<keyLists.size();i++){
			if(keyLists.get(i).contains(key))
				return i;
		}
		return -1;
	}
	
	/**����һ�����ڲ������޹ص���PlaceԪ�أ��������Һ��׶���Ϣ*/
	@Override
	public Place newElement() {
		return newElement(0,1,2,3,4);
	}
	public Place newElement(int...types){
		if(!checkTypes(types)){
			System.err.println(this.getClass().getSimpleName()+"\nmethod: newElement(int...types) : The types is illegal ");
			System.exit(0);
		}
		Random rand = new Random();
		int typeIndex = types[rand.nextInt(types.length)];
		int keyIndex = rand.nextInt(keyLists.get(typeIndex).size());
		String key = keyLists.get(typeIndex).get(keyIndex);
		return new Place(key,kyMaps.get(typeIndex).get(key));
	}
	

	/**��ȡһ�����ڲ�������޹صĹ���������ָ������ģʽ�������ޣ������ȣ��Ƿ������ظ�*/
	public List<String> getRandomKeyList(int length,boolean repeat,int...types){
		if(!checkTypes(types)){
			System.err.println(this.getClass().getSimpleName()+"\nmethod: getRandomKeyList(int length,boolean repeat,int...types) : The types is illegal ");
			System.exit(0);
		}
		List<String> list = new ArrayList<String>();
		List<String> sumkeyList = new ArrayList<String>();
		for(int i=0;i<types.length;i++)
			sumkeyList.addAll(new ArrayList<String>(keyLists.get(types[i])));
		
		if(!repeat){
			Collections.shuffle(sumkeyList);
			for(int i=0;i<length && i<sumkeyList.size();i++)
				list.add(sumkeyList.get(i));
		}else{
			Random rand = new Random();
			for(int i=0;i<length;i++){
				int index = rand.nextInt(sumkeyList.size());
				list.add(sumkeyList.get(index));
			}
		}
		return list;
	}
	public List<String> getRandomKeyList(int length,boolean repeat){
		return this.getRandomKeyList(length,repeat,0,1,2,3,4);
	}
	public List<String> getRandomKeyList(int length){
		return this.getRandomKeyList(length,false);
	}

	/**��ȡһ�����ڲ�������޹ص��׶�����������ָ������ģʽ�������ޣ������ȣ��Ƿ������ظ�*/
	public List<String> getRandomValueList(int length,boolean repeat,int...types){
		List<String> keyList = getRandomKeyList(length,repeat,types);
		List<String> list = new ArrayList<String>();
		for(int i=0;i<keyList.size();i++){
			String key = keyList.get(i);
			list.add(kyMaps.get(getListIndexOfKey(key)).get(key));
		}
		return list;
	}
	public List<String> getRandomValueList(int length,boolean repeat){
		return getRandomValueList(length,repeat,0,1,2,3,4);
	}
	public List<String> getRandomValueList(int length){
		return getRandomValueList(length,false,0,1,2,3,4);
	}
	
	
	
	
/*	public static void main(String[] args){
		RandomWorldGenerator gen = new RandomWorldGenerator();
//		gen.printContent();
//		gen.writeIntoFile();
//		System.out.println(gen.newElement(1,1));  
		List<String> list = gen.getRandomValueList(20,false,0);
//		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i));
	}
	*/


}



