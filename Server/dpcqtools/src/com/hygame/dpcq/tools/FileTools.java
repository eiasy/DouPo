package com.hygame.dpcq.tools;

import java.io.File;

public class FileTools {
	public boolean deleteFile(String sPath) {   
		boolean flag = false;   
		File    file = new File(sPath);   
		// 路径为文件且不为空则进行删除   
		if (file.isFile() && file.exists()) {   
			file.delete();   
			flag = true;   
		}   
		return flag;   
	}  

}
