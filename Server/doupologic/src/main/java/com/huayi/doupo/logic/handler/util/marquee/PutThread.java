package com.huayi.doupo.logic.handler.util.marquee;

import java.util.concurrent.TimeUnit;

/**
 * 放数据线程 每30分钟放一次数据
 * @author mp
 * @date 2013-12-20 下午5:16:06
 */
public class PutThread extends Thread implements Runnable{
		
	@Override
	public void run() {
		while(true){
			try {
				TimeUnit.MINUTES.sleep(30);
				String contents = MarqueeUtil.quartzMarqueeContent;
				
				if(MarqueeUtil.openSysMarquee == 1){
					String str[] = contents.split(";");
					for(String s : str){
						if(!s.equals("")){
							MarqueeUtil.blockingDeque.putFirst(s);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}