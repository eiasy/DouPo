package com.huayi.doupo.logic.handler.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.huayi.doupo.base.model.DictWorldBoss;
import com.huayi.doupo.base.model.InstWorldBoss;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;

/**
 * 归档世界boss相关信息[世界bossId, 血量等]
 * @author mp
 * @date 2015-11-26 下午2:02:23
 */
public class WoldBossResetHandler extends BaseHandler implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
			
			List<DictWorldBoss> worldBosseList = DictList.dictWorldBossList;
			int initWorldBossId = 0;
			for (DictWorldBoss obj : worldBosseList) {
				if (obj.getIsFirstShow() == 1) {
					initWorldBossId = obj.getId();
					break;
				}
			}
			
			int initWorldBossBlood = DictMapUtil.getSysConfigIntValue(StaticSysConfig.minBossBlood);
			
			List<InstWorldBoss> instWorldBossList = getInstWorldBossDAL().getList("", 0);
			//没有数据,说明是刚开服,初始世界boss是魂天帝,初始血量是2000万[一般情况下,这里的代码不会走,因为在开服的时候就已经插入数据了]
			if (instWorldBossList.size() <= 0) {
				InstWorldBoss instWorldBoss = new InstWorldBoss();
				instWorldBoss.setCurrBossId(initWorldBossId);
				instWorldBoss.setCurrBossBlood(initWorldBossBlood);
				instWorldBoss.setCurrHitBossSecs(-1);
				getInstWorldBossDAL().add(instWorldBoss, 0);
			} else {
				//用打世界boss的耗时来判断是否真开服[因为开服都是预先开的,并非向玩家开放的],初始化状态不处理.
				//-1:表示初始化  0:表示世界boss未打死   >0:表示打死世界boss的用时
				
				InstWorldBoss instWorldBoss = instWorldBossList.get(0);
				if (instWorldBoss.getCurrHitBossSecs() >= 0) {
				
					//将本次打世界boss信息,转移到上次
					instWorldBoss.setLastBossId(instWorldBoss.getCurrBossId());
					instWorldBoss.setLastBossBlood(instWorldBoss.getCurrBossBlood());
					instWorldBoss.setLastBossBloodPer(instWorldBoss.getCurrBossBloodPer());
					instWorldBoss.setLastHitBossSecs(instWorldBoss.getCurrHitBossSecs());
					
					//预设下一次的世界Boss信息
					List<DictWorldBoss> randomWorldBosseList = new ArrayList<>(); 
					for (DictWorldBoss obj : worldBosseList) {
						if (obj.getId() != instWorldBoss.getCurrBossId()) {
							randomWorldBosseList.add(obj);
						}
					}
					int randomIndex = RandomUtil.getRandomInt(randomWorldBosseList.size());
					DictWorldBoss worldBoss = randomWorldBosseList.get(randomIndex);
					instWorldBoss.setCurrBossId(worldBoss.getId());
					instWorldBoss.setCurrBossBloodPer(worldBoss.getBloodPer());
					instWorldBoss.setCurrHitBossSecs(0);//默认设置成0-未打死，具体由世界Boss被打死时处理
					
					/**
					 *  计算下一次世界boss的血量[公式按照xls文档上来的]
					 * (A为前一天BOSS血量
                        B为今天BOSS血量
                        X为前一天活动经历时间X秒
                        Y为前一天BOSS的血量系数
                        Z为今天新刷出来的世界BOSS血量系数)
					 */
					int currBossBlood = 0;
					int lastBossBlood = instWorldBoss.getLastBossBlood();//A
					int lastHitBossSecs = instWorldBoss.getLastHitBossSecs();//X
					float per = instWorldBoss.getCurrBossBloodPer() / instWorldBoss.getLastBossBloodPer();//(Z/Y)
					
					if (lastHitBossSecs == 0) {//世界Boss未打死   B=A*0.6*(Z/Y)
						currBossBlood = (int)(float)(lastBossBlood * 0.6 * per);
					} else if (lastHitBossSecs > 0) {//世界Boss被打死
						if (lastHitBossSecs > 900 && lastHitBossSecs <= 1200) {//B=0.75*A*(Z/Y)
							currBossBlood = (int)(float)(0.75 * lastBossBlood * per);
						} else if (lastHitBossSecs > 600 && lastHitBossSecs <= 900) {//B={1-[(X-600)/1200]} *A*(Z/Y)
							float x = 1 - (float)((lastHitBossSecs - 600) / 1200); //1-[(X-600)/1200]
							currBossBlood = (int)(float)(x * lastBossBlood * per);
						} else if (lastHitBossSecs > 300 && lastHitBossSecs <= 600) {//B={1+[(600-x)/1200]}*A*(Z/Y)
							float x = 1 + (float)((lastHitBossSecs - 600) / 1200); //1+[(X-600)/1200]
							currBossBlood = (int)(float)(x * lastBossBlood * per);
						} else if (lastHitBossSecs > 120 && lastHitBossSecs <= 300) {//B=1.25*A*(Z/Y)
							currBossBlood = (int)(float)(1.25 * lastBossBlood * per);
						} else if (lastHitBossSecs > 0 && lastHitBossSecs <= 120) {//B=2*A*(Z/Y)
							currBossBlood = (int)(float)(2 * lastBossBlood * per);
						}
					}
					instWorldBoss.setCurrBossBlood(currBossBlood < initWorldBossBlood ? initWorldBossBlood : currBossBlood);
					getInstWorldBossDAL().update(instWorldBoss, 0);
				}
			}
		} catch (Exception e) {
			LogUtil.error("归档世界boss相关信息", e);
		}
	}

}
