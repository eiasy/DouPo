package com.hygame.dpcq.action;

import java.io.File;

import com.hygame.dpcq.config.ParamConfig;
import com.hygame.dpcq.tools.FileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private File file;

	// 提交过来的file的名字
	private String fileFileName;

	// 提交过来的file的MIME类型
	private String fileContentType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * 上传字典数据更新文件
	 * @author mp
	 * @date 2015-4-1 下午4:00:59
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String uploadfile() throws Exception {
		try {
			FileUtil.copyFile(file.getPath(), ParamConfig.uploadFilePath + "/update.txt");
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 上传服务器列表
	 * @author mp
	 * @date 2015-4-1 下午5:34:43
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String uploadserverlist() throws Exception {
		try {
			FileUtil.copyFile(file.getPath(), ParamConfig.uploadFilePath + "/serverlist.txt");
			ServerAttributeAction.orgServerList();
		} catch (Exception e) {
			return "fail";
		}
		return "fqpz";
	}
}