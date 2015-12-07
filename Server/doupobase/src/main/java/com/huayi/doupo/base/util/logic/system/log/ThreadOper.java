package com.huayi.doupo.base.util.logic.system.log;

/**
 * 抽象类-具体的线程操作类
 * @author mp
 * @date 2014-5-6 上午9:37:55
 */
public abstract class ThreadOper implements Runnable{
	
	/**
	 * 日志内容
	 */
	private String content;
	
	/**
	 * 日志操作对象
	 */
	private OperLog operLog;
	
	public ThreadOper () {
		
	}
	
	/**
	 * 抽象方法,为重写run方法
	 * @author hzw
	 * @date 2014-9-16下午4:54:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public abstract void innerRun();
	
	/**
	 * Runnable接口的实现方法
	 */
	@Override
	public void run() {
		innerRun();
	}
}
