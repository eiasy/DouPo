package com.hygame.dpcq.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 1.将字节流转换成字符串返回 ： String readFileByLines(InputStream is) 
 * 2.将文件一行一行的读成List返回： List<String> readFileToList(File file)
 * 3.将文件按照一定的编码方式一行一行的读成List返回:List<String> readFileToList(File file, String encodType)
 * 4.将指定的字符串内容以指定的方式写入到指定的文件中：void writeFile(File file, String content, Boolean flag)
 * 5.将指定的字符串内容以指定的方式及编码写入到指定的文件中：void writeFile(File file, String content, Boolean flag, String encodType)
 * 6.拷贝文件夹:void copyFolder(String oldPath, String newPath)
 * 7.将文件重命名:  void reName(String oldName, String newName)
 * 8.删除文件列表： boolean deleteFiles(List<String> files)
 * 9.删除文件或文件夹： boolean delete(String fileName) 
 * 10.删除文件： boolean deleteFiles(List<String> files)
 * 11.删除目录及目录下的文件：boolean deleteDirectory(String dir) 
 */
public class FileUtil {
	
	 /**
     * 将字节流转换成字符串返回
     * 
     * @param is
     *   输入流
     * @return 字符串
     */
    public static String readFileByLines(InputStream is) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将文件一行一行的读成List返回
     * 
     * @param file
     *            需要读取的文件
     * @return 文件的一行就是一个List的Item的返回
     */
    public static List<String> readFileToList(File file) {
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        if (!file.exists()) {
			return null;
		}
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                list.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }

    /**
     * 将文件按照一定的编码方式一行一行的读成List返回
     * 
     * @param file
     *            需要读取的文件
     * @param encodType
     *            字符编码
     * @return 文件的一行就是一个List的Item的返回
     */
    public static List<String> readFileToList(File file, String encodType) {
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encodType));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                list.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }

    /**
     * 将指定字符串以指定的方式写入到指定的文件中
     * @author mp
     * @date 2014-4-1 上午10:26:30
     * @param file
     * @param content
     * @param flag
     * @Description
     */
    public static void writeFile(File file, String content, Boolean flag) {
        try {
            if (!file.exists()) {
            	file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, flag);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 将List写入文件
     * @author mp
     * @date 2014-3-31 下午2:23:16
     * @param file
     * @param conList
     * @param flag
     * @Description
     */
    public static void writeFileForList(File file, List<Integer> conList, Boolean flag) {
        try {
            if (!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file, flag);
            for(Integer string : conList){
            	writer.write(string + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取某个文件夹下的文件名列表
     * @author mp
     * @date 2014-3-31 下午6:17:33
     * @param filepath
     * @return
     * @throws Exception
     * @Description
     */
    public static List<String> getFileNameList (String filepath) throws Exception{
    	List<String> fileNameList = new LinkedList<String>();
        File file = new File(filepath);
        if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
            	String fileName = filelist[i];
            	fileNameList.add(fileName);
           }
        }
        return fileNameList;
    }
    
    /**
     * 将指定的字符串内容以指定的方式及编码写入到指定的文件中
     * 
     * @param file
     *            需要写人的文件
     * @param content
     *            需要写入的内容
     * @param flag
     *            是否追加写入
     * @param encodType
     *            文件编码
     */
    public static void writeFile(File file, String content, Boolean flag, String encodType) {
        try {
            FileOutputStream writerStream = new FileOutputStream(file, flag);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    writerStream, encodType));
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拷贝文件夹
     * 
     * @param oldPath
     *            源目录
     * @param newPath
     *            目标目录
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs();
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件重命名
     * 
     * @param oldName
     *            源文件名
     * @param newName
     *            新文件名
     */
    public static void reName(String oldName, String newName) {
        File oldF = new File(oldName);
        File newF = new File(newName);
        oldF.renameTo(newF);
    }
    
    /**
     * 删除文件列表
     * @author mp
     * @date 2014-4-2 上午11:35:57
     * @param files
     * @return
     * @Description
     */
    public static boolean deleteFiles(List<String> files) {
        boolean flag = true;
        for (String file : files) {
            flag = delete(file);
            if (!flag) {
            	break;
            }
        }
        return flag;
    }
    
    /**
     * 删除某目录下的所有文件列表
     * @author mp
     * @date 2014-4-2 下午12:06:41
     * @param directoryPath
     * @Description
     */
    public static void delDirectoryFiles (String directoryPath) {
    	File dirFile = new File(directoryPath);
    	File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                deleteFile(files[i].getAbsolutePath());
            }
        }
    }

    /**
     * 删除单个文件或完全删除某个目录
     * @author mp
     * @date 2014-4-2 上午11:29:04
     * @param fileName
     * @return
     * @Description
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
            	return deleteFile(fileName);
            } else {
            	return fullyDelDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     * @author mp
     * @date 2014-4-2 上午11:29:50
     * @param fileName
     * @return
     * @Description
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile())
            return file.delete();
        return false;
    }

    /**
     * 完全删除目录含目录自己
     * @author mp
     * @date 2014-4-2 上午11:31:23
     * @param dir
     * @return
     * @Description
     */
    public static boolean fullyDelDirectory(String dir) {
        if (!dir.endsWith(File.separator)) {
        	dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
        	return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                	break;
                }
            } else if (files[i].isDirectory()) {
                flag = fullyDelDirectory(files[i].getAbsolutePath());
                if (!flag) {
                	break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        return dirFile.delete();
    }
    
    /**
     * 将字节数组写入文件
     * @author mp
     * @date 2014-3-19 上午9:38:08
     * @param filePath
     * @param content
     * @Description
     */
    public static void writeFile(String filePath, byte[] content){
    	try {
    	   	FileOutputStream fos = new FileOutputStream(filePath);
    		fos.write(content);
    		fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * 复制文件
	 * @author mp
	 * @date 2014-8-20 下午4:15:49
	 * @param sourPath
	 * @param sourFileName
	 * @param targPath
	 * @param targFileName
	 * @throws Exception
	 * @Description
	 */
	public static void copyFile (String sourPath, String sourFileName, String targPath, String targFileName) throws Exception{
		try {
			Path sour = Paths.get(sourPath , sourFileName);
			Path targ = Paths.get(targPath , targFileName);
			Files.copy(sour, targ, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 复制文件
	 * @author mp
	 * @date 2015-4-8 上午9:53:25
	 * @param sourPath
	 * @param targPath
	 * @throws Exception
	 * @Description
	 */
	public static void copyFile (String sourPath, String targPath) throws Exception{
		try {
			Path sour = Paths.get(sourPath);
			Path targ = Paths.get(targPath);
			Files.copy(sour, targ, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 读取文件到list
	 * @author mp
	 * @date 2015-4-8 上午9:53:37
	 * @param filePath
	 * @param fileName
	 * @param fileCharset
	 * @return
	 * @Description
	 */
    public static List<String> readFileToList(String filePath, String fileName, String fileCharset) {
		
    	List<String> retList = new ArrayList<String>();
    	Path path = Paths.get(filePath, fileName);
		Charset charset = Charset.forName(fileCharset);
		
		//读取内容,组装List
		try(BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String content = "";
			while ((content = reader.readLine()) != null){
				retList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retList;
    }
    
    /**
     * 将文件按照一定的编码方式一行一行的读成String返回
     * @author mp
     * @date 2015-8-10 上午11:34:42
     * @param file
     * @param encodType
     * @return
     * @Description
     */
    public static String readFileToStr(String filePath, String fileName, String fileCharset) {

    	StringBuffer sb = new StringBuffer();
    	Path path = Paths.get(filePath, fileName);
		Charset charset = Charset.forName(fileCharset);
		
		//读取内容,组装List
		try(BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String content = "";
			while ((content = reader.readLine()) != null){
				sb.append(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
    }
    
    /**
     * 删除单个文件
     * @author mp
     * @date 2015-4-8 上午9:53:51
     * @param filePath
     * @param fileName
     * @Description
     */
	public static void deleFile (String filePath, String fileName){
		try {
			Files.deleteIfExists(Paths.get(filePath, fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
//		copyFile("D:\\InstallBall\\tomcat\\apache-tomcat-6.0.36\\work\\Catalina\\localhost\\dpcqtools\\upload_24734e26_14c73f96530__8000_00000000.tmp", "D://xx.sql");
		System.out.println(readFileToList("D:\\InstallBall\\tomcat\\apache-tomcat-6.0.36\\webapps\\dpcqtools\\upload","serverlist.txt","utf-8").size());
	}
     
}
