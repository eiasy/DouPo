package com.hygame.dpcq.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 根据某个文件名字，查找该文件在本工程下的物理位置
 * 
 */
public class FilePathUtil {
	
	/**
	 * 文件名
	 */
	private static String fileName = "";
	
	/**
	 * java 工程路径
	 */
	private static String projectPath = "";
	
	/**
	 * 存储查找出来的文件路径
	 */
	private static ArrayList<String> lists = new ArrayList<String>();
	
	/**
	 * 构造方法为文件名和工程路径赋值
	 * @param fileName
	 */
	private FilePathUtil(){
	}
	
	/**
	 * 返回文件在工程中的路径
	 * @return 工程路径
	 */
	public static String getFilePath(String fNames){
		try {
			projectPath = new File("").getCanonicalPath();
			fileName = fNames;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dir(projectPath);
		if (lists.size() > 0) {
			if (lists.size() == 1) {
				return lists.get(0).replace('\\','/');
			} else {
				for (int i = 0; i < lists.size(); i++) {
					String path = lists.get(i);
					String convPath = path.replace('\\','/');
					if (convPath.indexOf("src") > 0 ) {
						return convPath;
					}
				}
			}
		} else {
			return "";
		}
		return "";
	}
	
	/**
	 * 递归查找文件路径
	 * @param filepath 工程路径
	 */
	private static void dir(String filepath) {
		File f = new File(filepath);
		if(f.exists() && f.isDirectory()){
		   if(f.listFiles().length == 0){
		      f.delete();   
		   }else{
		      File delFile[] = f.listFiles();   
		      int i = f.listFiles().length;
		      for(int j = 0; j < i; j++) {
	        	  dir(delFile[j].getAbsolutePath());
	        	  if (delFile[j].getName().equals(fileName)) {
	        		  String xmlPath = delFile[j].getAbsolutePath();
	        		  lists.add(xmlPath);
	        	  }
		      }
		   } 
	    }
	}
}
