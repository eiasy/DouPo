package com.huayi.doupo.base.util.logic.system;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文件帮助类
 * @author mp
 * @version 1.0, 2013-4-3 下午4:35:36
 */
public class SysConfigUtil {
	
	/**
	 * 私有构造方法
	 */
	private SysConfigUtil() {
		
	}

	private static Properties configProp = null;

	private static String filePath = System.getProperty("user.dir") + "/config/sysConfig.properties";

	/**
	 * 获取Properties对象
	 * @author mp
	 * @date 2014-5-8 下午4:36:11
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static Properties getProp()throws Exception {
/*		if (configProp == null) {
			InputStream is = SysConfigUtil.class.getClassLoader().getResourceAsStream(PathConfig.SYSCONFIG);
			if (is == null) {
				is = Object.class.getResourceAsStream(PathConfig.SYSCONFIG);
			}
			configProp = new Properties();
			configProp.load(is);
		}
		return configProp;*/
		
		
		if (configProp == null) {
			configProp = new Properties();
			InputStream is = new BufferedInputStream(new FileInputStream(filePath));  
			configProp.load(is);
		}
		return configProp;
	}
	
	/**
	 * 根据Key获取Value
	 * @author mp
	 * @date 2014-5-8 下午4:35:45
	 * @param key
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static String getValue(String key)throws Exception {
		return getProp().getProperty(key);
	}
	
}
