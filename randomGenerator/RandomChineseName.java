package randomGenerator;


/**
* @see RandomGenerator.java 
* @author Al_assad 2415875132@qq.com
* @date 2016年9月23日 上午8:40:11
* @version V3.0 
* Description: 中文姓名随机生成器
* 				使用List记录最终生成结果的容器；
* 			使用List作为获取素材的容器；
* API：
* 	Constructor：RandomChineseName(int size):指定容量；
* 		      	RandomChieseNanem():默认，指定容量
* 	Method：String newElement():获取一个新元素，该元素独立于本体List中；
* 			String newElement(boolean three,boolean man):获取一个自定义的随机元素；
* 			String next():返回结果列表中的下一个元素，访问该元素的引用由reset维护；
* 			List<String> getContent([length]):返回结果列表,可指定长度（允许自动容错）；
* 			void printContent([length]):打印结果列表,可指定长度；
* 			void wirteIntoFile([length]):将结果列表写入txt文件中；
*/
import java.util.*;
import java.io.*;


public class RandomChineseName implements RandomGenerator<List<String>,String>{
	
	private List<String> list = new ArrayList<String>();	/**储存固定数量的随机人名*/
	private List<String> firstNameList ;	/**储存所有读取的姓字符串*/
	private List<String> lastNameList_M ; 	/**储存所有读取的名字(男)符串*/
	private List<String> lastNameList_F ; 	/**储存所有读取的名字(女)符串*/
	private int size ;						/**记录list的长度*/
	private int cur = 0;						/**next所使用的游标*/
	private String firstNameFileURL = "randomGenerator/metrial/Chinese_Name/First_Name_Chinese.txt" ;	/**读取姓文件的默认位置*/
	private String lastNameFileURL_M = "randomGenerator/metrial/Chinese_Name/Last_Name_M_Chinese.txt" ;		/**读取名文件的默认位置*/
	private String lastNameFileURL_F = "randomGenerator/metrial/Chinese_Name/Last_Name_F_Chinese.txt" ;		/**读取名文件的默认位置*/
	private String outputURL = "output_RandomChieseName.txt";
	
	/**构造方式:指定数量*/
	public RandomChineseName(int size) {
		this.size = size;
		try{
			firstNameList = loadFile(firstNameFileURL);
			lastNameList_M = loadFile(lastNameFileURL_M);
			lastNameList_F = loadFile(lastNameFileURL_F);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		createRandomNameList();
	}
	/**构造方式：默认数量50*/
	public RandomChineseName(){
		this(50);
	}
	
	
	/**读取目标文件中所有中文字符串，并加载到相应的线性表中,同时将线性表处理为合适长度（size或读取长度）*/
	private List<String> loadFile(String fileURL) throws IOException,EOFException{
		String readStr = "";
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileURL)));
	    String temp = "";
	    while((temp = input.readLine()) != null){
	    	readStr += temp;
	    }
		String[] splitStr = readStr.trim().split(" ");
		input.close();
		return new ArrayList<String>(Arrays.asList(splitStr));
	}
	
	
	/**生成指定数量随机人名字符串list*/
	private void createRandomNameList(){
		for(int i=0;i<size;i++){
			list.add(newElement());
		}
	}
	
	
	/**以下为对构造器的操作方法*/
	/**获取下一个元素,下标超过时候返回null*/
	@Override
	public String newElement(){
		Random rand = new Random();
		return newElement(rand.nextBoolean(),rand.nextBoolean());
	}
	public  String newElement(boolean threeType,boolean manType) {
		//由一个随机数决定生成名字的组合：3字名，2字名，另一个随机数产生男名，女名
		Random rand = new Random();
		String name = "";
		if(manType){
			name = firstNameList.get(rand.nextInt(firstNameList.size()))
					+ lastNameList_M.get(rand.nextInt(lastNameList_M.size()));
			if(threeType){
				 name += lastNameList_M.get(rand.nextInt(lastNameList_M.size()));
			}
		}else{
			name = firstNameList.get(rand.nextInt(firstNameList.size()))
					+ lastNameList_F.get(rand.nextInt(lastNameList_F.size()));
			if(threeType){
				name += lastNameList_M.get(rand.nextInt(lastNameList_F.size()));
			}
		}
		return name;
		
	}
	
	/**获取随机生成器内部列表的下一个随机量*/
	public String next(){
		return cur<size ? list.get(cur++) : null;
		
	}
	public void resetCur(){
		cur = 0;
	}

	/**获取指定数量的随机名字列表*/
	@Override
	public List<String> getContent() {
		return this.list;
	}
	/**打印指定数量的*/
	@Override
	public void printContent(int length) {
		for(int i=0;i<length && i<size;i++)
			System.out.println(list.get(i));
	}
	public void printContent(){
		printContent(size);
	}
	
	
	
	
	/**将结果打印到默认输出文件中*/
	@Override
	public void writeIntoFile(int length){
		if(length > size)
			length = size;
		try{
			File file = new File(outputURL);
			if(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			for(int i=0;i<length;i++){
				output.write(list.get(i));
				output.newLine();
			}
			
			output.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}	
	}
	public void writeIntoFile() {
		writeIntoFile(size);
	}
	
	public String getOutputURL() {
		return outputURL;
		
	}
	
	public void setOutputURL(String URL) {
		outputURL = URL;
		
	}

	
/*	public static void main(String[] args){
		RandomChineseName name = new RandomChineseName();
		Scanner input = new Scanner(System.in);
		System.out.println("create your new name!");
//		while(true){
//			System.out.print("Please input the type of your name(twotype?man?)[y/n]:");
//			boolean type1  = false; 
//			boolean type2  = false;
//			String[] str = input.nextLine().trim().split(" ");
//			if(str[0].equals("y"))
//				type1 = !(type1);
//			if(str[1].equals("y"))
//				type2 = !(type2);
//			System.out.println(name.newElement(type1,type2));
//		}
		while(true){
			System.out.print("Please the size you want:");
			int size = input.nextInt();
			name.printContent(size);
			name.writeIntoFile();
		}
	
//		for(int i=0;i<= 60;i++){
//			System.out.println(name.next());
//		}
		
	}*/
	

}
