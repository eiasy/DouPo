package com.hygame.dpcq.tools;

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
	 * 该方法把指定信息字符串记录到日志中
	 * @throws Exception
	 */
	public static void info(String info) {
		try {
			logger.info(info);
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
			logger.error(error);
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
			logger.error(e.getMessage(), e);
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
			logger.error(error + "\n" + getExceptionDetail(e) + "\n");
		} catch (Exception e1) {
			e1.printStackTrace();
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
