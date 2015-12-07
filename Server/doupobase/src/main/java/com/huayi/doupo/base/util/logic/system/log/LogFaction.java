package com.huayi.doupo.base.util.logic.system.log;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志工厂类
 * @author mp
 * @date 2014-5-5 下午4:36:39
 */
public class LogFaction {
	
	/**
	 * 私有构造方法
	 */
	private LogFaction(){
		
	}
	
	/**
	 * 存放日志对象的Map
	 */
	private static ConcurrentHashMap<String, OperLog> logsMap = new ConcurrentHashMap<String, OperLog>();
	
	/**
	 * 获取日志实例
	 * @author mp
	 * @date 2014-5-5 下午4:37:51
	 * @param logType
	 * @return
	 * @Description
	 */
	public static OperLog getLogInstance(String logType){
		if (logsMap.containsKey(logType)) {
			return logsMap.get(logType);
		}else {
			logsMap.put(logType, new OperLog(logType));
		}
		return logsMap.get(logType);
	}
	
}
