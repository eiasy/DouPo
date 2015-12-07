package com.huayi.doupo.base.util.logic.system;

import com.huayi.doupo.base.util.logic.system.log.LogFaction;
import com.huayi.doupo.base.util.logic.system.log.OperLog;


/**
 * 多线程日志处理工具类
 * @author mp
 * @date 2014-5-5 下午3:46:26
 */
public class LogicLogUtil {
	
	/**
	 * 写日志
	 * @author mp
	 * @date 2014-8-15 上午9:39:02
	 * @param logType
	 * @param content
	 * @Description
	 */
	public static void info (String logType, String content){
		OperLog operLog = LogFaction.getLogInstance(logType);
		operLog.info(content);
	}
	
	
	public static void main(String[] args) throws Exception{
		for (int i = 0; i < 10; i++) {
			LogicLogUtil.info("bagExpand", "xxxxxxxxxxxxx");
		}
	}
	
}
