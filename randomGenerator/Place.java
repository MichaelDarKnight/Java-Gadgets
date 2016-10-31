package randomGenerator;
/**
* @see
* @author Al_assad 2415875132@qq.com
* @date 2016年9月24日 上午2:38:28
* @version V1.0  
* Description: 定义地点类，供地点生成器使用
*/

public class Place {
	private String key;
	private String value;
	public Place(String key,String value){
		this.key = key;
		this.value = value;
	}
	public String getKey(){
		return key;
	}
	public String getValue(){
		return value;
	}
	public String toString(){
		return this.key+"—"+this.value;
	}

}
