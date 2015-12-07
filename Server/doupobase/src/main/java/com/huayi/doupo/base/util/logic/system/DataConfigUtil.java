package com.huayi.doupo.base.util.logic.system;

import java.io.InputStream;
import java.util.Properties;

import com.huayi.doupo.base.config.PathConfig;

/**
 * 属性文件帮助类
 * @author mp
 * @version 1.0, 2013-4-3 下午4:35:36
 */
public class DataConfigUtil {
	
	private DataConfigUtil() {
	}

	private static Properties configProp = null;

	public static Properties getProp()throws Exception {
		if (configProp == null) {
			InputStream is = DataConfigUtil.class.getClassLoader()
					.getResourceAsStream(PathConfig.DATACONFIG);
			if (is == null) {
				is = Object.class.getResourceAsStream(PathConfig.DATACONFIG);
			}
			configProp = new Properties();
			configProp.load(is);
		}
		
		return configProp;
	}

	/**
	 * 
	 * 该方法根据properties中的key取值
	 */
	public static String getValue(String key)throws Exception {

		String value = getProp().getProperty(key);
		return value;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getValue("rmi.port"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
