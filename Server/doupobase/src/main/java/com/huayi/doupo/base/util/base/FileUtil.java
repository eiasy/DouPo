package com.huayi.doupo.base.util.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;
import com.huayi.doupo.base.dal.factory.DALFactory;

/**
 * 文件操作工具类
 * @author mp
 * @date 2014-8-20 下午3:37:44
 */
public class FileUtil extends DALFactory{
	
	public final static String ROOT_DIR = System.getProperty("user.dir");//文件所在工程目录    E:\work\Sync\workspace\java\doupobase
	
	public final static String FILE_SEPARATOR = System.getProperty("file.separator");//文件分隔符 ,linux和windows不同     \
	
	public final static String LINE_SEPARATOR = System.getProperty("line.separator");//写文件换行符
	
	/**
	 * 读取文件,将文件内容组装成List返回
	 * @author mp
	 * @date 2014-8-20 下午3:47:11
	 * @param file
	 * @return
	 * @Description
	 */
    public static List<String> readFileToList(String filePath, String fileName, String fileCharset) {
		
    	List<String> retList = Lists.newArrayList();
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
     * 读取文件,将文件内容组装成List返回
     * @author mp
     * @date 2015-1-22 下午5:57:38
     * @param inputStream
     * @return
     * @Description
     */
    public static List<String> readFileToList(InputStream inputStream, String charset) {
    	List<String> retList = Lists.newArrayList();
		//读取内容,组装List
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
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
     * 读取文件,将文件内容组装成HashSet返回 - 去重
     * @author mp
     * @date 2015-8-7 下午5:33:57
     * @param inputStream
     * @param charset
     * @return
     * @Description
     */
    public static HashSet<String> readFileToSet(InputStream inputStream, String charset) {
    	HashSet<String> hashSet = new HashSet<String>();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
			String content = "";
			while ((content = reader.readLine()) != null){
				hashSet.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashSet;
    }
    
    /**
     * 文件是否存在
     * @author mp
     * @date 2015-7-29 下午3:48:04
     * @param filePath
     * @return
     * @Description
     */
    public static boolean isExsits (String filePath) {
    	File file = new File (filePath);
    	return file.exists();
    }
    
    /**
     * 向文件写入内容
     * @author mp
     * @date 2015-4-8 上午10:18:19
     * @param file
     * @param content
     * @param flag
     * @Description
     */
    public static void writeContentToFile(File file, String content, Boolean flag) {
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
     * 向文件写入内容    
     * @author mp
     * @date 2014-12-1 下午12:14:07
     * @param filePath
     * @param fileName
     * @param content
     * @Description
     */
    public static void writeContentToFile(String filePath, String fileName,  String content) {
    	try {
    		Path decPath = Paths.get(filePath);
        	if (!Files.exists(decPath)) {
        		Files.createDirectories(decPath);
    		}
        	Path path = Paths.get(filePath, fileName);
        	if (Files.exists(path)) {
				Files.delete(path);
			}
    		Charset charset = Charset.forName("utf-8");
    		try (BufferedWriter writer = Files.newBufferedWriter(path, charset, StandardOpenOption.CREATE_NEW)){
    			writer.append(content);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * 将内容以指定编码,指定选项[create, append...]写入文件
     * @author mp
     * @date 2014-8-20 下午4:14:24
     * @param filePath
     * @param fileName
     * @param fileCharset
     * @param content
     * @param openOption[StandardOpenOption.CREATE]
     * @Description
     */
    public static void writeContentToFile(String filePath, String fileName, String fileCharset, String content, OpenOption openOption) {
    	
    	//jdk7 IO 方式
    	try {
    		Path decPath = Paths.get(filePath);
        	if (!Files.exists(decPath)) {
        		Files.createDirectories(decPath);
    		}
        	Path path = Paths.get(filePath, fileName);
        	if (Files.exists(path)) {
				Files.delete(path);
			}
    		Charset charset = Charset.forName(fileCharset);
    		try (BufferedWriter writer = Files.newBufferedWriter(path, charset, openOption)){
    			writer.append(content);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
/*	传统IO方式
 *     	try {
        	String fullFileName = filePath + "/" + fileName;
    		BufferedReader reader = null;
    		BufferedWriter writer = null;
    		reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes())));// 字节
    		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fullFileName))));
    		String temp = "";
    		while ((temp = reader.readLine()) != null) {
    			writer.write(temp + "\n");
    			writer.flush();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    	
    	
/*	Nio方式
 * 		try {
			// 指定大小为 1024 的缓冲区
			ByteBuffer bf = ByteBuffer.allocate(1024);
			
			String fullFileName = filePath + "/" + fileName;
	    	FileOutputStream fos = new FileOutputStream(fullFileName);
	    	
	    	// 得到文件通道
	    	FileChannel fc = fos.getChannel();
	    	
	    	byte [] conByte = content.getBytes();
	    		
    		bf.clear();
    		int sum = 0;
    		int index = 0;
    		for (int i = 0; i < conByte.length; i++) {
				index ++;
				bf.put(conByte[i]);
				if (index % 1024 == 0) {
					System.out.println((++sum));
			    	bf.flip();
			    	fc.write(bf);
			    	bf.clear();
				}
			}
	    	
	    	bf.flip();
	    	fc.write(bf);
	    	
	    	fos.close();
	    	fc.close();
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    	
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
     * 创建文件
     * @author mp
     * @date 2014-8-20 下午4:07:22
     * @param diRectPath
     * @param fileName
     * @throws Exception
     * @Description
     */
	public static void createFile (String filePath, String fileName){
		try {
			Path path = Paths.get(filePath, fileName);
			if (Files.exists(path)) {
				System.out.println("---- file have exsits ----");
			} else {
				Files.createFile(path);
				System.out.println("---- create file succ ----");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件
	 * @author mp
	 * @date 2014-8-20 下午4:08:21
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 * @Description
	 */
	public static void deleFile (String filePath, String fileName){
		try {
			Files.deleteIfExists(Paths.get(filePath, fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 移动文件
	 * @author mp
	 * @date 2014-8-20 下午4:13:22
	 * @param sourcePath
	 * @param sourceFileName
	 * @param targetPath
	 * @param targetFileName
	 * @param copyOption [StandardCopyOption.REPLACE_EXISTING...]
	 * @throws Exception
	 * @Description
	 */
	public static void moveFile (String sourcePath, String sourceFileName, String targetPath, String targetFileName, CopyOption copyOption){
		try {
			Path source = Paths.get(sourcePath , sourceFileName);
			Path target = Paths.get(targetPath , targetFileName);
			if (!Files.exists(target)) {
				Files.createDirectories(target);
			}
			Files.move(source, target, copyOption);
			System.out.println("-- file move succ --");
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
	public static void copyFile (String sourPath, String sourFileName, String targPath, String targFileName){
		try {
			Path sour = Paths.get(sourPath , sourFileName);
			Path targ = Paths.get(targPath , targFileName);
			Files.copy(sour, targ, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("---- file copy succ ----");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 复制文件[含文件名的全路径]
	 * @author mp
	 * @date 2015-4-1 下午4:51:54
	 * @param sourPath
	 * @param targPath
	 * @Description
	 */
	public static void copyFile (String sourPath, String targPath) {
		try {
			Path sour = Paths.get(sourPath);
			Path targ = Paths.get(targPath);
			Files.copy(sour, targ, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("---- file copy succ ----");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 文件重命名
    * @param path 文件目录 
    * @param oldname  原来的文件名 
    * @param newname 新文件名 
    */ 
    public static void rename (String path,String oldname,String newname){ 
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
            File oldfile=new File(path+"/"+oldname); 
            File newfile=new File(path+"/"+newname); 
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
                System.out.println(newname+"已经存在!"); 
            else{ 
                oldfile.renameTo(newfile); 
            } 
        } 
    }

	public static void main(String[] args) {
		System.out.println(FileUtil.FILE_SEPARATOR);
		System.out.println(FileUtil.ROOT_DIR);
		System.out.println(FileUtil.LINE_SEPARATOR);
	}

}
