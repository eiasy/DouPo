package com.huayi.doupo.logic.handler.util.marquee;

import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import com.huayi.doupo.logic.util.WorldBossPlayerMapUtil;

/**
 * 统计线程类, 每1分钟统计一次
 * @author mp
 * @date 2014-9-12 下午4:29:07
 */
public class StaticsThread extends Thread implements Runnable{
		
	@Override
	public void run() {
		
		while(true){
			
			try {
				
				//每20秒执行一次
				TimeUnit.SECONDS.sleep(20);
				
				//获取当前在线人数和打世界Boss的人数
				int onlinePlayerNum = PlayerMapUtil.getSize();
				int worldBossNum = WorldBossPlayerMapUtil.getSize();
				
				//统计最大在线人数及时间
				if(onlinePlayerNum > ParamConfig.maxOnlineNum){
					ParamConfig.maxOnlineNum = onlinePlayerNum;
					ParamConfig.maxOnlineTime = DateUtil.getCurrTime();
				}
				
				//统计最小在线人数及时间-开服1小时后统计
				if ((DateUtil.getCurrMill() - ParamConfig.startServerTime) > (1 * 60 * 60 * 1000)) {
					if (onlinePlayerNum < ParamConfig.minOnlineNum) {
						ParamConfig.minOnlineNum = onlinePlayerNum;
						ParamConfig.minOnlineTime = DateUtil.getCurrTime();
					}
				}
				
				//统计打世界Boss的最大人数
				if(worldBossNum > ParamConfig.maxWorldBossNum){
					ParamConfig.maxWorldBossNum = worldBossNum;
				}
				
				//处理延迟到账
//				PlayerUtil.rechargeQueue();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}