package com.huayi.doupo.base.util.logic.system.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 平台日志帮助类
 * @author mp
 * @date 2013-12-25 下午7:41:18
 */
public class OperLog{
	
	private Logger logger = null;
	
	public OperLog(String logType) {
		logger = LoggerFactory.getLogger(logType);
	}
	
	/**
	 * 将info类别信息写到文件中
	 * @author mp
	 * @date 2014-5-5 上午10:42:44
	 * @param info
	 * @Description
	 */
	public void info(String info){
		try {
			if ("1".equals("1")) {
				if (logger != null) {
					logger.info(info);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
