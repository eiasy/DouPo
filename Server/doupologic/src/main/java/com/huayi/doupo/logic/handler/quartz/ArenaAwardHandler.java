package com.huayi.doupo.logic.handler.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.huayi.doupo.base.model.DictArenaReward;
import com.huayi.doupo.base.model.InstPlayerArena;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.AwardUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 竞技场每日发奖
 * @author hzw
 * @date 2014-11-4上午11:39:47
 */
public class ArenaAwardHandler extends BaseHandler implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<InstPlayerArena> instPlayerArenaList = getInstPlayerArenaDAL().getList(" upRank != -1", 0);
		List<DictArenaReward> dictArenaRewardList = DictList.dictArenaRewardList;
		for(InstPlayerArena obj : instPlayerArenaList){
			Player Player = PlayerMapUtil.getPlayerByPlayerId(obj.getInstPlayerId());
			if(Player != null){
				String description = "恭喜您在竞技场中获得排名奖励：";
				String things = "";
				for(DictArenaReward dictArenaReward : dictArenaRewardList){
					if(obj.getRank() >= dictArenaReward.getDownRank() && obj.getRank() <= dictArenaReward.getUpRank()){
						things = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.gold + "_" + dictArenaReward.getGold() + ";" + 
								StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.prestige + "_" + dictArenaReward.getPrestige();
					}
				}
				MessageData otherSyncMsgData = new MessageData();
				try {
					ActivityUtil.addInstPlayerAward(obj.getInstPlayerId(), 2, things, DateUtil.getCurrTime(), description, otherSyncMsgData);
					MessageUtil.sendSyncMsgToOne(Player.getOpenId(), otherSyncMsgData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				Map<Integer, Map<String, Integer>> AwarMap = AwardUtil.getMap();
				String operTime = DateUtil.getCurrTime();
				Map<String, Integer> tempMap = new HashMap<String, Integer>();
				if(AwarMap.containsKey(obj.getInstPlayerId())){
					tempMap = AwarMap.get(obj.getInstPlayerId());
					try {
						String key = DateUtil.getNumDayDate(operTime, -DictMapUtil.getSysConfigIntValue(StaticSysConfig.awardDay));
						if(tempMap.containsKey(key)){
							tempMap.remove(key);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				tempMap.put(DateUtil.getYmdDate(operTime), obj.getRank());
				AwarMap.put(obj.getInstPlayerId(), tempMap);
			}
		}
	}

}
