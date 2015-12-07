package com.huayi.doupo.base.util.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性加在工具类
 * @author mp
 * @date 2014-3-20 下午2:30:26
 */
public class PropertiesUtil {

	public static Properties loadProperties(String propertiesPath) {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream(propertiesPath);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
