package com.huayi.doupo.base.util.logic.system.log;


/**
 * 多线程日志处理工具类
 * @author mp
 * @date 2014-5-5 下午3:46:26
 */
public class MoThreadLogUtil {
	
	public static void main(String[] args) {
		
//		for (int i = 0; i < 5000; i++) {
//			System.out.println("========="+i);
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while (true) {
//						try {
//							writeLog("test1", Thread.currentThread().getName()+ "---test1---" + System.currentTimeMillis());
//							writeLog("test2", Thread.currentThread().getName()+ "---test2---" + System.currentTimeMillis());
//							TimeUnit.SECONDS.sleep(1);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}).start();
//		}
		
		for (int i = 0; i < 1500; i++) {
			System.out.println("======================" + i);
			MoThreadLogUtil.writeLog("test1", "---test1---");
			MoThreadLogUtil.writeLog("test2", "---test2---");
		}
	}
	
	/**
	 * 模拟写入日志
	 * @author mp
	 * @date 2014-5-5 下午3:53:07
	 * @Description
	 */
	public static void writeLog (String logType, String content){
		OperLog operLog = LogFaction.getLogInstance(logType);
		ThreadPoolUtils.execute(new ThreadOper() {
			@Override
			public void innerRun() {
			}
			
		});
	}
	
}
