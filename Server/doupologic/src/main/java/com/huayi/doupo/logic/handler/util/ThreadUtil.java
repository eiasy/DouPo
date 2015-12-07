package com.huayi.doupo.logic.handler.util;

import com.huayi.doupo.logic.handler.util.marquee.StaticsThread;

/**
 * 线程管理工具类
 * @author mp
 * @date 2014-9-12 下午4:22:01
 */
public class ThreadUtil {

	/**
	 * 启动统计线程
	 * @author mp
	 * @date 2014-9-12 下午4:23:25
	 * @Description
	 */
	public static void startStaticThread () {
		new StaticsThread().start();
	}
	
}
