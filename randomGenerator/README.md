#randomGenerator随机名称生成器   
 
**使用说明：**  


###RandomChineseName.java 随机中文名称生成器
**实现接口：**RandomGenerator\<K,T>

>**Constructor：**  
>>RandomChineseName(int size):	指定容量;    
>>RandomChieseNanem():	默认，指定容量;  

>**Method：**  
>>String newElement():	获取一个新元素，该元素独立于本体List中；  
>>String newElement(boolean three,boolean man):		获取一个自定义的随机元素；  
>>String next():	返回结果列表中的下一个元素，访问该元素的引用由reset维护；  
>>List\<String> getContent([length]):	返回结果列表,可指定长度（允许自动容错）；  
>>void printContent([length]):	打印结果列表,可指定长度；  
>>void wirteIntoFile([length]):		将结果列表写入txt文件中;  


###AbstractPlaceGenerator.java 随机地名生成器抽象类
**实现接口：**RandomGenerator\<K,T>  
**说明：**  
1、每个地点素材都应该有一一对应的key和value:  
2、对结果集Map的遍历要通过Map的entrySet()或keySet(),访问元素；  
3、只有提供外部调用方法getRandomKeyList和getRandomValueLis，及其拓展方法或包装方法；  
可以返回重复或不重复的结果集，其余的方法产生的结果集是元素不可重复的；  

>**Constructor：**  
>>AbstractPlaceGenerator() 

>**Method：**  
>>void loadFile(String fileURL,Map<String,String> kyMap,List<String> keyList)： 读取一个文件中的key和value，并将其写入到原始素材kyMap中,同时生成记录kyMap中key的list；  
>>List<String> getRandomKeyList(List<String> srcKeyList,int length,boolean repeat): 根据srcKeyList，获取一组和内部Map无关的随机keyList，可指定条件：长度，是否允许重复;    
>>List<String> getRandomValueList(List<String> srcKeyList,Map<String,String> srcMap,int length,boolean repeat): 根据srcKeyList和srcMap，获取一组和内部Map无关的随机valueList，可指定条件：长度，是否允许重复;  
>>public Iterator<Map.Entry<String,String>> iterator()；  返回该表的遍历器，由此实现对map的遍历  
>>abstract Place newElement();  获取一个与内部容器无关的元素,只定义外部调用
>>void printContent(): 打印元素：默认全部写入;
>>void printContent(int length): 打印元素：指定数量，对数量进行容错;  
>>void writeIntoFile(): 向默认文件写入结果集：默认全部写入;  
>>void writeIntoFile(int length):向默认文件写入指定数量的结果集：对结果进行容错; 
>>public int getSize():返回列表长度；




###RandomChineseGenerator.java随机中国省份和城市生成器  
**继承父类：AbstractPlaceGenerator**
>**Constructor：**  
>>RandomChinaGenerator(int size):	指定容量;    
>>RandomChinaGenerator():	默认容量;  

>**Method：**  
>>String newElement():	获取一个新元素，该元素独立于本体List中；  
>>List<String> getRandomKeyList(int length,boolean repeat)：   获取一组与内部容器无关的省份名称，只定义外部调用；
>>public List<String> getRandomKeyList(int length): 同上，默认结果不重复
>>List<String> getRandomValueList(int length,boolean repeat)：获取一组与内部容器无关的城市名称，只定义外部调用


###RandomWorldGenerator.java随机中国省份和城市生成器  
**继承父类：AbstractPlaceGenerator**
>**Constructor：**  
>>RandomChinaGenerator(int size):	指定容量;    
>>RandomChinaGenerator():	默认容量;  

>**Method：**  
>>String newElement():	获取一个新元素，该元素独立于本体List中；  
>>List<String> getRandomKeyList(int length,boolean repeat)：   获取一组与内部容器无关的国家名称，只定义外部调用；
>>public List<String> getRandomKeyList(int length): 同上，默认结果不重复
>>List<String> getRandomValueList(int length,boolean repeat)：获取一组与内部容器无关的首都名称，只定义外部调用；



