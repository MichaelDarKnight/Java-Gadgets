package randomGenerator;

/**
* @see RandomGenerator.java 
* @author Al_assad 2415875132@qq.com
* @date 2016��9��24�� ����12:05:22
* @version V1.0  
* Description:  ��������ص�������,
* 				1��ÿ���ص��زĶ�Ӧ����һһ��Ӧ��key��value:
* 				2���Խ����Map�ı���Ҫͨ��Map��entrySet()��keySet(),����Ԫ�أ�
* 				3��ֻ���ṩ�ⲿ���÷���getRandomKeyList��getRandomValueLis��������չ�������װ����
* 					���Է����ظ����ظ��Ľ����������ķ��������Ľ������Ԫ�ز����ظ��ģ�
*/

import java.io.*;
import java.util.*;

abstract class AbstractPlaceGenerator implements RandomGenerator<Map<String,String>,Place>{
	
	protected Map<String,String> map = new HashMap<String,String>();	/**��¼�ڲ����ɽ������*/
	protected int size = 0;						/**��¼list�ĳ���*/
	protected String outputURL;	/**д���ļ���ַ*/

	public AbstractPlaceGenerator(){
		
	}
	
	/**��ȡһ���ļ��е�key��value��������д�뵽ԭʼ�ز�kyMap��,ͬʱ���ɼ�¼kyMap��key��list*/
	protected void loadFile(String fileURL,Map<String,String> kyMap,List<String> keyList){
		String readStr = "";
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileURL)));
		    String temp = "";
		    while((temp = input.readLine()) != null){
		    	readStr += temp;
		    }
		    input.close();
		}catch(FileNotFoundException ex){
			System.err.println("file is not exists!");
		}catch(IOException ex){
			ex.printStackTrace();
		}
		String[] initialStr = readStr.trim().split(" ");
				
		for(int i=0;i<initialStr.length;i+=2){
			keyList.add(initialStr[i]);
			kyMap.put(initialStr[i],initialStr[i+1]);
		}
	}
	/**����srcKeyList����ȡһ����ڲ�Map�޹ص����keyList����ָ�����������ȣ��Ƿ������ظ�*/
	protected List<String> getRandomKeyList(List<String> srcKeyList,int length,boolean repeat){
		List<String> otherList = new ArrayList<String>();;
		if(repeat){
			Random rand = new Random();
			for(int i=0;i<length;i++){
				otherList.add(srcKeyList.get(rand.nextInt(srcKeyList.size())));
			}
		}else{
			List<String> temp = new ArrayList<String>();
			temp.addAll(srcKeyList);
			Collections.shuffle(temp);
			for(int i=0;i<length && i< srcKeyList.size();i++)
				otherList.add(temp.get(i));
		}
		return otherList;
	}
	/**����srcKeyList��srcMap����ȡһ����ڲ�Map�޹ص����valueList����ָ�����������ȣ��Ƿ������ظ�*/
	protected List<String> getRandomValueList(List<String> srcKeyList,Map<String,String> srcMap,int length,boolean repeat){
		List<String> key =  getRandomKeyList(srcKeyList,length,repeat);
		List<String> value = new ArrayList<String>();
		for(int i=0;i<key.size();i++)
			value.add(srcMap.get(key.get(i)));
		return value;
	}
	
	/**���ظñ�ı��������ɴ�ʵ�ֶ�map�ı���*/
	public Iterator<Map.Entry<String,String>> iterator(){
		return map.entrySet().iterator();
	}
	
	/**��ȡһ�����ڲ������޹ص�Ԫ��,ֻ�����ⲿ����*/
	@Override
	public abstract Place newElement();
	
	@Override
	public  Map<String,String> getContent() {
		return this.map;
	}
	
	/**��ӡԪ�أ�Ĭ��ȫ��д��*/
	@Override
	public void printContent() {
		for(Map.Entry<String,String> e : map.entrySet())
			System.out.println(e.getKey()+"��"+e.getValue());
	}
	/**��ӡԪ�أ�ָ�������������������ݴ�*/
	@Override
	public void printContent(int length) {
		Iterator<Map.Entry<String,String>> iter = iterator();
		int count = 0;
		while(iter.hasNext() && count<length){
			Map.Entry<String ,String> e = iter.next();
			System.out.println(e.getKey()+"��"+e.getValue());
		}
		
	}
	/**��Ĭ���ļ�д��������Ĭ��ȫ��д��*/
	@Override
	public void writeIntoFile() {
		writeIntoFile(size);
	}
	/**��Ĭ���ļ�д��ָ�������Ľ�������Խ�������ݴ�*/
	@Override
	public void writeIntoFile(int length)  {
		if(length>size)
			length = size;
		try{
			File file = new File(outputURL);
			if(!file.exists()){
			file.createNewFile();
			}
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			Iterator<Map.Entry<String,String>> iter = iterator();
			int count = 0;
			while(iter.hasNext() && count<length){
				Map.Entry<String,String> e = iter.next();
				output.write(e.getKey()+"��"+e.getValue());
				output.newLine();
			}
			output.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}	
		
	}
	public int getSize(){
		return size;
	}
	
	
	

}
