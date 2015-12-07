package com.huayi.doupo.base.util.logic.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志帮助类
 * @author mp
 * @version 1.0, 2013-4-3 下午4:38:15
 */
public class LogUtil {

	private static final Logger logger = LoggerFactory.getLogger("game");
	private static final Logger monitor = LoggerFactory.getLogger("monitor");

	/**
	 * 文件日志Log是否开启
	 * @return
	 * @throws Exception
	 */
	public static String fileLogIsOpen() throws Exception {
		String isOpen = SysConfigUtil.getValue("log.file.isOpen");
		if (isOpen != null) {
			isOpen = isOpen.trim();
		}
		return isOpen;
	}

	/**
	 * 控制台输出Log是否开启
	 * @return
	 * @throws Exception
	 */
	public static String consoleLogIsOpen() throws Exception {
		String isOpen = SysConfigUtil.getValue("log.console.isOpen");
		if (isOpen != null) {
			isOpen = isOpen.trim();
		}
		return isOpen;
	}

	/**
	 * 战斗控制台输出是否开启
	 * @author mp
	 * @date 2013-11-5 下午12:03:36
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static String consoleFightLogIsOpen() throws Exception {
		String isOpen = SysConfigUtil.getValue("log.console.fight.isOpen");
		if (isOpen != null) {
			isOpen = isOpen.trim();
		}
		return isOpen;
	}

	/**
	 * 该方法把指定信息字符串记录到日志中
	 * @throws Exception
	 */
	public static void info(String info) {
		try {
			if (fileLogIsOpen().equals("1")) {
				logger.info(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 该方法把指定的错误信息字符串记录到日志中
	 * @throws Exception
	 */
	public static void error(String error) {
		try {
			if (fileLogIsOpen().equals("1")) {
				logger.error(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把指定的Exceptiopn对象，包含异常堆栈信息记录到日志中
	 * @author mp
	 * @date 2014-7-23 下午2:22:11
	 * @param e
	 * @Description
	 */
	public static void error(Exception e) {
		try {
			if (fileLogIsOpen().equals("1")) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 获取exception详情信息
	 * 
	 * @param e
	 *            Excetipn type
	 * @return String type
	 */
	public static String getExceptionDetail(Exception e) {

		StringBuffer msg = new StringBuffer("null");

		if (e != null) {
			msg = new StringBuffer("");

			String message = e.toString();

			int length = e.getStackTrace().length;

			if (length > 0) {

				msg.append(message + "\n");

				for (int i = 0; i < length; i++) {

					msg.append("\t" + e.getStackTrace()[i] + "\n");

				}
			} else {

				msg.append(message);
			}

		}
		return msg.toString();

	}

	/**
	 * 把指定的Exceptiopn对象，包含异常堆栈信息记录到日志中，并指定异常其他描述
	 * @author mp
	 * @date 2014-7-23 下午2:22:39
	 * @param error
	 * @param e
	 * @Description
	 */
	public static void error(String error, Exception e) {
		try {
			if (fileLogIsOpen().equals("1")) {
				logger.error(error + "\n" + getExceptionDetail(e) + "\n");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 把指定信息字符串[warn]记录到日志中
	 * @param error
	 * @throws Exception
	 */
	public static void warn(String error) throws Exception {
		if (fileLogIsOpen().equals("1")) {
			logger.warn(error);
		}
	}

	/**
	 * 该方法把指定的错误信息[warn]字符串记录到日志中
	 * @param error
	 * @param e
	 * @throws Exception
	 */
	public static void warn(String error, Exception e) {
		try {
			if (fileLogIsOpen().equals("1")) {
				logger.warn(error, e);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 该方法把指定的message输出到控制台
	 * @throws Exception
	 */
	public static void out(String message) {
		try {
			if (consoleLogIsOpen().equals("1")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("[" + sdf.format(new Date()) + "]  "+ message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 战斗控制台输出
	 * @author mp
	 * @date 2013-11-5 下午12:04:09
	 * @param message
	 * @Description
	 */
	public static void fightOut(String message) {
		try {
			if (consoleFightLogIsOpen().equals("1")) {
				System.out.print(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 战斗控制台输出-LN
	 * @author mp
	 * @date 2014-1-5 下午5:32:57
	 * @param message
	 * @Description
	 */
	public static void fightOutLn(String message) {
		try {
			if (consoleFightLogIsOpen().equals("1")) {
				System.out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			logger.info("logger info");
			monitor.info("monitor info");
			logger.error("logger error");
			monitor.error("monitor error");
		}
	}

}
