package randomGenerator;


/**
* @see RandomGenerator.java 
* @author Al_assad 2415875132@qq.com
* @date 2016��9��23�� ����8:40:11
* @version V3.0 
* Description: �����������������
* 				ʹ��List��¼�������ɽ����������
* 			ʹ��List��Ϊ��ȡ�زĵ�������
* API��
* 	Constructor��RandomChineseName(int size):ָ��������
* 		      	RandomChieseNanem():Ĭ�ϣ�ָ������
* 	Method��String newElement():��ȡһ����Ԫ�أ���Ԫ�ض����ڱ���List�У�
* 			String newElement(boolean three,boolean man):��ȡһ���Զ�������Ԫ�أ�
* 			String next():���ؽ���б��е���һ��Ԫ�أ����ʸ�Ԫ�ص�������resetά����
* 			List<String> getContent([length]):���ؽ���б�,��ָ�����ȣ������Զ��ݴ���
* 			void printContent([length]):��ӡ����б�,��ָ�����ȣ�
* 			void wirteIntoFile([length]):������б�д��txt�ļ��У�
*/
import java.util.*;
import java.io.*;


public class RandomChineseName implements RandomGenerator<List<String>,String>{
	
	private List<String> list = new ArrayList<String>();	/**����̶��������������*/
	private List<String> firstNameList ;	/**�������ж�ȡ�����ַ���*/
	private List<String> lastNameList_M ; 	/**�������ж�ȡ������(��)����*/
	private List<String> lastNameList_F ; 	/**�������ж�ȡ������(Ů)����*/
	private int size ;						/**��¼list�ĳ���*/
	private int cur = 0;						/**next��ʹ�õ��α�*/
	private String firstNameFileURL = "randomGenerator/metrial/Chinese_Name/First_Name_Chinese.txt" ;	/**��ȡ���ļ���Ĭ��λ��*/
	private String lastNameFileURL_M = "randomGenerator/metrial/Chinese_Name/Last_Name_M_Chinese.txt" ;		/**��ȡ���ļ���Ĭ��λ��*/
	private String lastNameFileURL_F = "randomGenerator/metrial/Chinese_Name/Last_Name_F_Chinese.txt" ;		/**��ȡ���ļ���Ĭ��λ��*/
	private String outputURL = "output_RandomChieseName.txt";
	
	/**���췽ʽ:ָ������*/
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
	/**���췽ʽ��Ĭ������50*/
	public RandomChineseName(){
		this(50);
	}
	
	
	/**��ȡĿ���ļ������������ַ����������ص���Ӧ�����Ա���,ͬʱ�����Ա���Ϊ���ʳ��ȣ�size���ȡ���ȣ�*/
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
	
	
	/**����ָ��������������ַ���list*/
	private void createRandomNameList(){
		for(int i=0;i<size;i++){
			list.add(newElement());
		}
	}
	
	
	/**����Ϊ�Թ������Ĳ�������*/
	/**��ȡ��һ��Ԫ��,�±곬��ʱ�򷵻�null*/
	@Override
	public String newElement(){
		Random rand = new Random();
		return newElement(rand.nextBoolean(),rand.nextBoolean());
	}
	public  String newElement(boolean threeType,boolean manType) {
		//��һ������������������ֵ���ϣ�3������2��������һ�����������������Ů��
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
	
	/**��ȡ����������ڲ��б����һ�������*/
	public String next(){
		return cur<size ? list.get(cur++) : null;
		
	}
	public void resetCur(){
		cur = 0;
	}

	/**��ȡָ����������������б�*/
	@Override
	public List<String> getContent() {
		return this.list;
	}
	/**��ӡָ��������*/
	@Override
	public void printContent(int length) {
		for(int i=0;i<length && i<size;i++)
			System.out.println(list.get(i));
	}
	public void printContent(){
		printContent(size);
	}
	
	
	
	
	/**�������ӡ��Ĭ������ļ���*/
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
