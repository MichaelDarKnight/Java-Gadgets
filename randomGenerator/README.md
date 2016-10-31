#randomGenerator随机名称生成器   
 
**使用说明：**  

###RandomChineseName.java随机中文名称生成器 
>**Constructor：**  
>>RandomChineseName(int size):指定容量;    
>>RandomChieseNanem():默认，指定容量;  

>**Method：**  
>>String newElement():获取一个新元素，该元素独立于本体List中；  
>>String newElement(boolean three,boolean man):获取一个自定义的随机元素；  
>>String next():返回结果列表中的下一个元素，访问该元素的引用由reset维护；  
>>List<String> getContent([length]):返回结果列表,可指定长度（允许自动容错）；  
>>void printContent([length]):打印结果列表,可指定长度；  
>>void wirteIntoFile([length]):将结果列表写入txt文件中；  
