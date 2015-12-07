package com.hygame.dpcq.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

/**
 * 配置参数
 * @author mp
 * @date 2015-4-8 上午10:30:24
 */
public class ParamConfig {
	
	/**
	 * 存放文件的目录
	 */
	public static String uploadFilePath = ServletActionContext.getServletContext().getRealPath("/upload");
	
	/**
	 * 成功编码
	 */
	public static int succ = 0;
	
	/**
	 * 失败编码
	 */
	public static int fail = -1;
	
	/**
	 * 消息返回结果
	 */
	public static String result;
	
}
