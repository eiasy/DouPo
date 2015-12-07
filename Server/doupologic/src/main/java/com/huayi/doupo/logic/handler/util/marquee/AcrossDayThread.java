package com.huayi.doupo.logic.handler.util.marquee;

import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.HttpClientUtil;

/**
 * 跨天时,向账号服务器发在线玩家,以便账号服务器统计跨天日志,准确统计留存
 * @author mp
 * @date 2014-9-12 下午4:29:07
 */
public class AcrossDayThread extends Thread implements Runnable{
		
	@Override
	public void run() {
		
		while(true){
			try {
				//每2秒执行一次
				TimeUnit.SECONDS.sleep(2);
				
//				System.out.println("--------");
				
				if (ParamConfig.acrossDayQueue.size() > 0) {
					//取出并删除头数据,向账号服发送在线玩家信息
					String params = ParamConfig.acrossDayQueue.poll();
					if (params != null) {
						HttpClientUtil.postMapSubmit(ParamConfig.serverAcrossDayUrl, params);
//						System.out.println(DateUtil.getCurrTime() + "+++++++++");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}