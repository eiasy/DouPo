package com.huayi.doupo.logic.test.mode;

import java.util.concurrent.LinkedBlockingQueue;


/**
 * 任务队列工具类
 * @author mp
 * @date 2015-1-12 上午9:53:18
 */
public class QueueUtil {
	
	/**
	 * 任务队列
	 */
	private static LinkedBlockingQueue<RunnableObj> taskqueue = new LinkedBlockingQueue<RunnableObj>();

	/**
	 * 放数据
	 * @author mp
	 * @date 2015-1-12 上午10:02:00
	 * @param obj
	 * @Description
	 */
	public static void put (RunnableObj runnable) {
		try {
			taskqueue.put(runnable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取数据
	 * @author mp
	 * @date 2015-1-12 上午10:02:13
	 * @Description
	 */
	public static RunnableObj take () {
		try {
			return taskqueue.take();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
