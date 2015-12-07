package com.hygame.dpcq.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	private static Properties props = null;
	
	private static Properties getProperties () {
		if (props == null) {
			InputStream in;
			props = new Properties();
			try {
				String path="/"+Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
				in = new BufferedInputStream (new FileInputStream(path+"jdbc.properties"));
				props.load(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return props;
		} else {
			return props;
		}
	}
	
	public static String getValue(String key) {
		return getProperties().getProperty (key);
	}
}
