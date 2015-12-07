package com.huayi.doupo.base.util.logic.system.log;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.config.ParamConfig;

/**
 * 线程池管理类
 * @author mp
 * @date 2014-5-6 上午1:04:20
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
//	private static final int nThread = Runtime.getRuntime().availableProcessors() * 5;
	private static final int nThread = 3;
	
	/**
	 * 静态初始化线程池执行对象
	 */
//	等同于 Executors.newCachedThreadPool()
//	private static ThreadPoolExecutor workPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//	等同于 Executors.newFixedThreadPool()
	private static ThreadPoolExecutor workPool = new ThreadPoolExecutor(nThread, nThread, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            
            
	/**
	 * 执行方法
	 * @author mp
	 * @date 2014-5-5 下午6:05:48
	 * @param runnable
	 * @Description
	 */
	public static void execute (Runnable runnable) {
		try {
			workPool.execute(runnable);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向客服工具推送数据
	 * @author mp
	 * @date 2015-8-24 下午9:43:35
	 * @param runnable
	 * @Description
	 */
	public static void executePushData (Runnable runnable) {
		try {
			if (ParamConfig.isOpenPushDataCenter == 1) {
				workPool.execute(runnable);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}