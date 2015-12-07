package mmo.tools.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {
	private Properties properties = null;
	private String[]   keys       = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PropertiesUtil(String file) {
		try {
			InputStream inStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(inStream);
			inStream.close();
			Set tempKeys = properties.keySet();
			keys = new String[tempKeys.size()];
			tempKeys.toArray(keys);
			tempKeys = null;
		} catch (Exception e) {
			System.err.println("不能读取属性文件!");
			e.printStackTrace();
		}
	}

	public String getValue(int CateValue) {
		String Value = properties.getProperty(CateValue + "");
		return Value == null ? "未知" : Value;
	}

	public String getValue(String CateValue) {
		String Value = properties.getProperty(CateValue);
		return Value == null ? "未知" : Value;
	}

	public String[] getKeys() {
		return keys;
	}

	public String getKey(int index) {
		return keys[index];
	}

	public void put(String key, String value) {
		properties.put(key, value);
	}

	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	public void storeProperties(String file, String comments) {
		try {
			OutputStream out = new FileOutputStream(file);
			properties.store(out, comments);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
