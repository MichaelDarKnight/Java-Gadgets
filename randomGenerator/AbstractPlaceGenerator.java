package randomGenerator;

/**
* @see RandomGenerator.java 
* @author Al_assad 2415875132@qq.com
* @date 2016年9月24日 上午12:05:22
* @version V1.0  
* Description:  抽象随机地点生成器,
* 				1、每个地点素材都应该有一一对应的key和value:
* 				2、对结果集Map的遍历要通过Map的entrySet()或keySet(),访问元素；
* 				3、只有提供外部调用方法getRandomKeyList和getRandomValueLis，及其拓展方法或包装方法
* 					可以返回重复或不重复的结果集，其余的方法产生的结果集是元素不可重复的；
*/

import java.io.*;
import java.util.*;

abstract class AbstractPlaceGenerator implements RandomGenerator<Map<String,String>,Place>{
	
	protected Map<String,String> map = new HashMap<String,String>();	/**记录内部生成结果容器*/
	protected int size = 0;						/**记录list的长度*/
	protected String outputURL;	/**写出文件地址*/

	public AbstractPlaceGenerator(){
		
	}
	
	/**读取一个文件中的key和value，并将其写入到原始素材kyMap中,同时生成记录kyMap中key的list*/
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
	/**根据srcKeyList，获取一组和内部Map无关的随机keyList，可指定条件：长度，是否允许重复*/
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
	/**根据srcKeyList和srcMap，获取一组和内部Map无关的随机valueList，可指定条件：长度，是否允许重复*/
	protected List<String> getRandomValueList(List<String> srcKeyList,Map<String,String> srcMap,int length,boolean repeat){
		List<String> key =  getRandomKeyList(srcKeyList,length,repeat);
		List<String> value = new ArrayList<String>();
		for(int i=0;i<key.size();i++)
			value.add(srcMap.get(key.get(i)));
		return value;
	}
	
	/**返回该表的遍历器，由此实现对map的遍历*/
	public Iterator<Map.Entry<String,String>> iterator(){
		return map.entrySet().iterator();
	}
	
	/**获取一个与内部容器无关的元素,只定义外部调用*/
	@Override
	public abstract Place newElement();
	
	@Override
	public  Map<String,String> getContent() {
		return this.map;
	}
	
	/**打印元素：默认全部写入*/
	@Override
	public void printContent() {
		for(Map.Entry<String,String> e : map.entrySet())
			System.out.println(e.getKey()+"—"+e.getValue());
	}
	/**打印元素：指定数量，对数量进行容错*/
	@Override
	public void printContent(int length) {
		Iterator<Map.Entry<String,String>> iter = iterator();
		int count = 0;
		while(iter.hasNext() && count<length){
			Map.Entry<String ,String> e = iter.next();
			System.out.println(e.getKey()+"—"+e.getValue());
		}
		
	}
	/**向默认文件写入结果集：默认全部写入*/
	@Override
	public void writeIntoFile() {
		writeIntoFile(size);
	}
	/**向默认文件写入指定数量的结果集：对结果进行容错*/
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
				output.write(e.getKey()+"—"+e.getValue());
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
