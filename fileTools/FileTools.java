package fileTools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * create by Intellij IDEA
 * Author: Al-assad
 * E-mail: yulinying@1994.com
 * Github: https://github.com/Al-assad
 * Date: 2017/1/26 1:59
 * Description:  文件处理工厂类，用于文件的各种操作，类似批处理和Linux下的各种文件操作指令
 */
public class FileTools {

    /**@TODO 删除文件或文件夹:支持多级目录文件
     * @param path 待删除的文件路径
     * @return true 删除过程成功执行
     *         flase 删除文件或目录不存在
     */
    public static boolean remove(String path){
        File file = new File(path);
       if(!file.exists())
           return false;

        remove(new File(path));
        return true;

    }
    private static void remove(File file){
        if(file.delete())  //当file为文件或空目录时，直接删除
            return;

        File[] list = file.listFiles();  //当file为非空目录时，递归删除文件
        for(int i=0;i<list.length;i++){
            boolean flag = list[i].delete();
            if(!flag)
                remove(list[i]);
        }
        file.delete();
    }

    /**@TODO 复制文件或目录到指定路径 ：支持多级目录文件，但复制目录不存在时，自动创建目录
    *
     * @param srcPath  复制目录或文件
     * @param targetPath  目标目录
     * @param cover   当目标文件下存在于复制文件文件名相同的文件时，是都覆盖该文件
     * @return true 复制操作成功执行
     *          false 源目录无效，在没有开启cover的条件下，复制目录冲突
     */
    public static boolean copy(String srcPath,String targetPath,boolean cover) {
        File src = new File(srcPath);
        File dest = new File(targetPath);
        if(!src.exists())
            return false;
        if(dest.exists()){
            File[] filelist = dest.listFiles();
            for(File file : filelist){
                if(file.getName().equals(src.getName())){
                    if(cover) {
                        remove(file);  //当允许覆盖，且在dest目录中找到与src文件相同文件名的文件时，删除该文件
                    }else{
                        return false;
                    }
                }
            }
        }else{
            dest.mkdir();  //当dest目录不存在时，创建目录
        }
        File destfile = new File(dest,src.getName());  //创建一个主目录
        copy(src,destfile);
        return true;
    }
    private static void copy(File src,File dest){
        if(src.isFile()){   //当src是文件时，直接复制
            try {
                BufferedInputStream in  = new BufferedInputStream(new FileInputStream(src));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
                while(in.available() != 0 ){
                    out.write(in.read());
                }
                in.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{      //src是目录时，递归复制目录
            dest.mkdir();
            File[] fileList = src.listFiles();
            for(File srcfile : fileList){
                File destfile = new File(dest,srcfile.getName());
                copy(srcfile,destfile);
            }
        }

    }

    /**@TODO 移动文件或目录:支持多级目录文件
     * @param srcPath
     * @param targetPath
     * @param cover
     * @return
     */
    public static boolean move(String srcPath,String targetPath,boolean cover){
        boolean flag = copy(srcPath,targetPath,cover);
        if(flag){
            remove(srcPath);
            return true;
        }else{
            return false;
        }
    }


    /** 查找指定目录下是否含有某个文件或文件夹
     * @param searchPath 查找的目标目录
     * @param filePath   查找的目录文件，或文件夹
     * @return  查找到的文件的绝对路径，查找失败返回null
     */
    public static String search(String searchPath,String filename){
        File rootfile = new File(searchPath);
        if(!rootfile.exists()) {
            return null;
        }
        return search(rootfile,filename);
    }
    private static String search(File curfile,String filename){
        //终止条件
        if(curfile.getName().equals(filename))
            return curfile.getAbsolutePath();
        if(curfile.isFile())
            return null;
        File[] list = curfile.listFiles();
        if(list.length == 0)
            return null;
        //递归过程
        String path="";
        for(int i=0;i<list.length;i++){
            path = search(list[i],filename);
            if(path != null)
                break;
        }
        //最终结果
        return path;
    }

    /**文件或目录重命名,支持绝对路径和相对路径
     * @param filePath 源文件或目录路径，
     * @param newName 命名的新文件名或路径名；
     * @return String 新命名的文件或目录名；
     * return true：命名过程成功
     * return false：源文件或目录不存在，或文件目录名已存在与原路径中
     */
    public static boolean rename(String filePath,String newName) {
        File srcfile = new File(filePath);
        //源文件或目录不存在，返回false
        if(!srcfile.exists()){
            return false;
        }
        //格式判定
        if((new File(newName).isFile() && srcfile.isDirectory() )|| (new File(newName).isDirectory() && srcfile.isFile()))
            return false;
        //获取源文件名和新文件名对比,相同时返回false
        String oldName = srcfile.getName();
        if(oldName.equals(newName)){
            return false;
        }
        //文件重命名过程
        String path = srcfile.getAbsoluteFile().getParent();
        srcfile.renameTo(new File(path+"/"+newName));
        return true;
    }

    /**创建目录：支持多级目录
     * @param   path 要创建的目录
     * @return true:创建成功
     *          false：目录已经存在
     */
    public static boolean createDir(String path){
        File file = new File(path);
        if(file.exists())
            return false;
       file.mkdir();
        return true;
    }

    /** 统计目录下符合某个尾缀的文件数量
     * @param src  原文件或目录路径
     * @param suffix  尾缀字符串
     * @return  int
     */
    public static int countSuffix(File src,String suffix){
        int count = 0;
        if(src.isFile() && src.getName().endsWith(suffix)){
            return ++count;
        }
        if(src.isDirectory()) {
            File[] list = src.listFiles();
            for(File element : list){
                count += countSuffix(element,suffix);
            }
        }
        return count;
    }

    /** 统计目录下符合某个尾缀的文件列表
     * @param src  原文件或目录路径
     * @param suffix 尾缀字符串
     * @return List<File>
     */
    public static List<File> getFilesWithSuffix(File src, String suffix){
        List<File> result = new ArrayList<File>();
        getFilesWithSuffix(src,suffix,result);
        return result;
    }
    private static void getFilesWithSuffix(File src, String suffix,List<File> fileList){
        if(src.isFile() && src.getName().endsWith(suffix)){
            fileList.add(src);
        }
        if(src.isDirectory()) {
            File[] list = src.listFiles();
            for(File element : list){
                getFilesWithSuffix(element,suffix,fileList);
            }
        }
    }

    /**统计目录下匹配某个正则表达式的文件数量
     * @param src  原文件或目录路径
     * @param regex  正则表达式字符串
     * @return int
     */
    public static int countRegex(File src,String regex){
        int count = 0;
        if(src.isFile() && src.getName().matches(regex)){
            return ++count;
        }
        if(src.isDirectory()) {
            File[] list = src.listFiles();
            for(File element : list){
                count += countSuffix(element,regex);
            }
        }
        return count;
    }

    /**统计目录下匹配某个正则表达式的文件数量
     *
     * @param src  原文件或目录路径
     * @param regex  正则表达式字符串
     * @return  List<File>
     */

    public static List<File> getFilesWithRegex(File src, String regex){
        List<File> result = new ArrayList<File>();
        getFilesWithRegex(src,regex,result);
        return result;
    }
    private static void getFilesWithRegex(File src, String regex,List<File> fileList){
        if(src.isFile() && src.getName().matches(regex)){
            fileList.add(src);
        }
        if(src.isDirectory()) {
            File[] list = src.listFiles();
            for(File element : list){
                getFilesWithRegex(element,regex,fileList);
            }
        }
    }











}
