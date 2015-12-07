package mmo.tools.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * 使用规则:
 * <ul>
 * <li><b>debug</b> 用来调试.
 * <li><b>info</b> 输出信息描述
 * <li><b>warn</b> 警告
 * <li><b>error</b> 异常
 * <li><b>fatal</b> 严重错误
 * </ul>
 */
public class MyLog4J {

	/** 是否已经成功初始化 */
	private static boolean configured = false;

	/**
	 * 初始化属性文件
	 * 
	 * @param filename
	 *            log4j.properties
	 */
	public static void init(String filename) {
		if (configured) {
			return;
		}
		try {
			init(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化属性文件
	 * 
	 * @param filename
	 *            log4j.properties
	 */
	public static void init(InputStream propsFile) {
		if (configured) {
			return;
		}
		Properties props = new Properties();
		try {
			props.load(propsFile);
			PropertyConfigurator.configure(props);
			configured = true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (propsFile != null) {
				try {
					propsFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				propsFile = null;
			}
		}
	}

	/**
	 * 用默认的配置文件来初始化log4j
	 */
	public static void init() {
		init(MyLog4J.class.getResourceAsStream("/mmo/tools/log/log4jConfig.properties"));
	}
}
