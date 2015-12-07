package com.hygame.dpcq.model.proto.util;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志帮助类
 * @author mp
 * @version 1.0, 2013-4-3 下午4:38:15
 */
public class Log{
	
	private static final Logger logger = LoggerFactory.getLogger(Log.class);
    
	/**
	 * 文件日志Log是否开启
	 * @return
	 * @throws Exception
	 */
	public static String fileLogIsOpen() throws Exception {
        String isOpen="1";
        if(isOpen!=null){
        	isOpen=isOpen.trim();
        }
		return isOpen;
	}
	/**
	 * 控制台输出Log是否开启
	 * @return
	 * @throws Exception
	 */
	public static String consoleLogIsOpen() throws Exception {
		String isOpen="1";
		if(isOpen!=null){
			isOpen=isOpen.trim();
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
		String isOpen="1";
		if(isOpen!=null){
			isOpen=isOpen.trim();
		}
		return isOpen;
	}

	/**
	 * 该方法把指定信息字符串记录到日志中
	 * @throws Exception 
	 */
	public static void info(String info){
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
	public static void error(String error){
		try {
			if (fileLogIsOpen().equals("1")) {
				logger.error(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 该方法把指定的Exceptiopn对象，包含异常堆栈信息记录到日志中
	 */
	public static void error(Exception e) {
		try {
			if (fileLogIsOpen().equals("1")) {
				String expClassName=e.getClass().getSimpleName();
				if (expClassName!=null&&!"OwnException".equals(expClassName)) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 该方法把指定的Exceptiopn对象，包含异常堆栈信息记录到日志中，并指定异常其他描述
	 */
	public static void error(String error, Exception e){
		try {
			if (fileLogIsOpen().equals("1")) {
				logger.error(error, e);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 该方法把指定信息字符串[warn]记录到日志中
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
	public static void warn(String error, Exception e)  {
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
	public static void out(String message){
		try {
			if (consoleLogIsOpen().equals("1")) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("["+sdf.format(new Date())+"]  "+message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 战斗message控制台输出
	 * @author mp
	 * @date 2013-11-5 下午12:04:09
	 * @param message
	 * @Description
	 */
	public static void fightOut(String message){
		try {
			if (consoleFightLogIsOpen().equals("1")) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("["+sdf.format(new Date())+"]  "+message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
