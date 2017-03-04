## FileTools  
###Java File拓展类  

###API  
<br/>
* boolean FileTools.remove(String path)  
>删除文件或文件夹:支持多级目录文件    
@param path 待删除的文件路径  
@return true 删除过程成功执  
        flase 删除文件或目录不存在  
<br/>
* boolean FileTools.copy(String srcPath,String targetPath,boolean cover)  
>复制文件或目录到指定路径 ：支持多级目录文件，但复制目录不存在时，自动创建目录  
@param srcPath  复制目录或文件  
@param targetPath  目标目录  
@param cover   当目标文件下存在于复制文件文件名相同的文件时，是都覆盖该文件  
@return true 复制操作成功执行  
        false 源目录无效，在没有开启cover的条件下，复制目录冲突  
<br/>
* boolean FileTools.move(String srcPath,String targetPath,boolean cover)   
>移动文件或目录:支持多级目录文件  
@param srcPath  移动的目录或文件  
@param targetPath  目标目录  
@param cover   当目标文件下存在与移动文件文件名相同的文件时，是都覆盖该文件  
@return true 移动操作成功执行  
        false 源目录无效，在没有开启cover的条件下，复制目录冲突  
<br/>
* boolean FileTools.search(String searchPath,String filename)  
>查找指定目录下是否含有某个文件或文件夹  
@param searchPath 查找的目标目录  
@param filePath   查找的目录文件，或文件夹  
@return  查找到的文件的绝对路径，查找失败返回null  
<br/>
* boolean FileTools.rename(String filePath,String newName)  
>@param filePath 源文件或目录路径  
@param newName 命名的新文件名或路径名；   
@return true：命名过程成功  
        false：源文件或目录不存在，或文件目录名已存在与原路径中    
<br/>
* int countSuffix(File src,String suffix)  
>统计目录下符合某个尾缀的文件数量  
@param src  原文件或目录路径  
@param suffix  尾缀字符串  
@return  int  
<br/>
* List<File> getFilesWithSuffix(File src, String suffix)
>统计目录下符合某个尾缀的文件列表  
@param src  原文件或目录路径  
@param suffix 尾缀字符串  
@return List &lt;File&gt;  
<br/>
* int countRegex(File src,String regex)  
>统计目录下匹配某个正则表达式的文件数量  
@param src  原文件或目录路径  
@param regex  正则表达式字符串  
@return int   
<br/>     
* List<File> getFilesWithRegex(File src, String regex)  
>统计目录下匹配某个正则表达式的文件数量  
@param src  原文件或目录路径  
@param regex  正则表达式字符串  
@return  List&lt;File&gt;  
<br/>

\>\>continues
