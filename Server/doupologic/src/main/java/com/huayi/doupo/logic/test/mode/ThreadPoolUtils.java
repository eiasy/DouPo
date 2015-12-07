package com.huayi.doupo.logic.test.mode;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理类
 * @author mp
 * @date 2015-1-12 下午2:18:58
 */
public class ThreadPoolUtils{
	
	/**
	 * 私有化构造方法
	 */
	private ThreadPoolUtils(){
		
	}
	
	/**
	 * 固定线程池线程个数
	 */
	private static final int nThread = Runtime.getRuntime().availableProcessors();
	
	/**
	 * 静态初始化线程池执行对象
	 */
	private static ThreadPoolExecutor workPool = new ThreadPoolExecutor(nThread, nThread, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	
	/**
	 * 执行方法
	 * @author mp
	 * @date 2015-1-12 下午2:18:35
	 * @param runnable
	 * @Description
	 */
	public static void execute (Runnable runnable) {
		try {
			workPool.execute(runnable);
			System.out.println("-- execute --");
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 线程池执行方法
	 * @author mp
	 * @date 2015-1-12 下午3:38:09
	 * @param args
	 * @Description
	 */
	public static void startPool () {
		while (true) {
			execute(QueueUtil.take());
		}
	}
}