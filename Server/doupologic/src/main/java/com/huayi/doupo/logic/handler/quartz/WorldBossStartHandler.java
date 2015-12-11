package com.huayi.doupo.logic.handler.quartz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.InstWorldBoss;
import com.huayi.doupo.base.model.InstWorldBossRank;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.socket.WorldBossPlayer;
import com.huayi.doupo.base.model.statics.StaticMsgRule;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import com.huayi.doupo.logic.util.WorldBossPlayerMapUtil;

/**
 * 世界Boss开启
 * @author hzw
 * @date 2015-1-4下午2:38:55
 */
public class WorldBossStartHandler extends BaseHandler implements Job {

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		
//		ParamConfig.worldBossBlood = ParamConfig.initWorldBossBlood;
		int bossBlood = DictMapUtil.getSysConfigIntValue(StaticSysConfig.minBossBlood);
		List<InstWorldBoss> instWorldBosseList = getInstWorldBossDAL().getList("", 0);
		if (instWorldBosseList.size() > 0) {
			bossBlood = instWorldBosseList.get(0).getCurrBossBlood();
		}
		ParamConfig.worldBossBlood = bossBlood;
		ParamConfig.worldBossInitBlood = bossBlood;
		ParamConfig.worldBossstate = 1;
		new SyncRandomOtherPlayer().start();
		new SyncWorldBossBlood().start();
//		System.out.println("------------世界Boss开启");
	}
	
	
	/**
	 * 同步随机打世界Boss的8个玩家线程
	 * 每15秒同步一次
	 * @author hzw
	 * @date 2015-1-8下午9:17:09
	 */
	class SyncRandomOtherPlayer extends Thread{
		@Override
		public void run() {
			while(ParamConfig.worldBossstate == 1){
				try {
//					System.out.println("------------同步随机打世界Boss的8个玩家");
					MessageData retMsgData = new MessageData();
					
					MessageData randomFightBossPlayerdate = new MessageData();
					Map<Integer, WorldBossPlayer> newWorldBossPlayerMap = new ConcurrentHashMap<Integer, WorldBossPlayer>();
					Map<Integer, WorldBossPlayer> allWorldBossPlayerMap = WorldBossPlayerMapUtil.getMap();
					
					for(Entry<Integer, WorldBossPlayer> map : allWorldBossPlayerMap.entrySet()){
						WorldBossPlayer worldBossPlayer = map.getValue();
						if(worldBossPlayer.getBossOnceHurt() > 0){
							newWorldBossPlayerMap.put(map.getKey(), worldBossPlayer);
						}
					}
					
					int indexNoThree = 0;
					int indexKey = 0;
					if(newWorldBossPlayerMap.size() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossOtherPC)){
						for(Entry<Integer, WorldBossPlayer> map : newWorldBossPlayerMap.entrySet()){
							WorldBossPlayer worldBossPlayer = map.getValue();
							if(worldBossPlayer.getBossOnceHurt() > 0){
								indexNoThree++;
								MessageData msgData = new MessageData();
								msgData.putStringItem("1", worldBossPlayer.getPlayerName());//名字
								msgData.putIntItem("2", worldBossPlayer.getBossOnceHurt());//单次伤害
								randomFightBossPlayerdate.putMessageItem(indexNoThree+"", msgData.getMsgData());
							}
						}
					}else{
						List<Integer> keyList = RandomUtil.getRandomNoRepeat(DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossOtherPC), newWorldBossPlayerMap);
						for(int i : keyList){
							indexKey++;
							WorldBossPlayer worldBossPlayer = newWorldBossPlayerMap.get(i);
							MessageData msgData = new MessageData();
							msgData.putStringItem("1", worldBossPlayer.getPlayerName());//名字
							msgData.putIntItem("2", worldBossPlayer.getBossOnceHurt());//单次伤害
							randomFightBossPlayerdate.putMessageItem(indexKey+"", msgData.getMsgData());
						}
					}
					retMsgData.putMessageItem("randomFightBossPlayer", randomFightBossPlayerdate.getMsgData());
					
					Map<Integer, WorldBossPlayer> currAllWorldBossPlayerMap = WorldBossPlayerMapUtil.getMap();
					for(Entry<Integer, WorldBossPlayer> map : currAllWorldBossPlayerMap.entrySet()){
						WorldBossPlayer worldBossPlayer = map.getValue();
						if(worldBossPlayer.getExitState() == 1){
							int instPlayerId = map.getKey();
							Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
							if(player != null){
								String channelId = player.getChannelId();
								if(channelId != null && !channelId.equals("")){
									MessageUtil.pushMsg(channelId, StaticMsgRule.pushWorldBossData, retMsgData);
								}
							}else{
								worldBossPlayer.setExitState(0);
								WorldBossPlayerMapUtil.add(instPlayerId, worldBossPlayer);
							}
						}
					}
					TimeUnit.SECONDS.sleep(DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossOtherPCTime));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 同步世界Boss血量
	 * @author hzw
	 * @date 2015-1-8下午11:04:52
	 */
	class SyncWorldBossBlood extends Thread{
		@Override
		public void run() {
			int i = 1;
			
			while(ParamConfig.worldBossstate == 1){
				try {
//					System.out.println("------------同步世界Boss血量");
					i++;
					long endMills = DateUtil.getMillSecond(DateUtil.getYmdDate() + " 21:20:00");
					if(ParamConfig.worldBossBlood <= 0 || DateUtil.getCurrMill() >= endMills){
//						if(ParamConfig.worldBossBlood <= 0 || DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossBloodTime) * i >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossLongTime) * 60){
						
						//统计本次世界Boss信息
						InstWorldBoss instWorldBoss = getInstWorldBossDAL().getList("", 0).get(0);
						if (WorldBossPlayerMapUtil.getMap() != null && WorldBossPlayerMapUtil.getMap().size() > 0) {//很多时候游戏服是提前预开启的，世界boss有进行，只是没人参与。所以这里要判断一下参与条件
							if (ParamConfig.worldBossBlood <= 0) {//世界Boss已死
								long currTime = DateUtil.getCurrMill();
								long startTime = DateUtil.getMillSecond(DateUtil.getYmdDate() + " 21:00:00");
								instWorldBoss.setCurrHitBossSecs((int)(currTime - startTime)/1000);
								getInstWorldBossDAL().update(instWorldBoss, 0);
							} else {//活动结束,但是世界Boss未被打死
								instWorldBoss.setCurrHitBossSecs(0);
								getInstWorldBossDAL().update(instWorldBoss, 0);
							}
						}
						
						//shijieBoss状态置为0
						ParamConfig.worldBossstate = 0;
						
						//启动统计并发送世界Boss排名、战斗奖励
						new worldBossRankReward().start();
					}
					
					//下发Boss血量
					MessageData retMsgData = new MessageData();
					retMsgData.putIntItem("worldBossBlood", ParamConfig.worldBossBlood);
//					retMsgData.putIntItem("initWorldBossBlood", ParamConfig.initWorldBossBlood);
					
					retMsgData.putIntItem("initWorldBossBlood", ParamConfig.worldBossInitBlood);
					
					Map<Integer, WorldBossPlayer> currAllWorldBossPlayerMap = WorldBossPlayerMapUtil.getMap();
					for(Entry<Integer, WorldBossPlayer> map : currAllWorldBossPlayerMap.entrySet()){
						WorldBossPlayer worldBossPlayer = map.getValue();
						if(worldBossPlayer.getExitState() == 1){
							int instPlayerId = map.getKey();
							Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
							if(player != null){
								String channelId = player.getChannelId();
								if(channelId != null && !channelId.equals("")){
//									System.out.println("------------"+instPlayerId);
									MessageUtil.pushMsg(channelId, StaticMsgRule.pushWorldBossData, retMsgData);
								}
							}
							else{
								worldBossPlayer.setExitState(0);
								WorldBossPlayerMapUtil.add(instPlayerId, worldBossPlayer);
							}
						}
					}
					
					TimeUnit.SECONDS.sleep(DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossBloodTime));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 统计并发送世界Boss排名、战斗奖励
	 * @author hzw
	 * @date 2015-1-9下午3:49:10
	 */
	public class worldBossRankReward extends Thread{
		@Override
		public void run() {
			try {
//				TimeUnit.SECONDS.sleep(DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossRewardTime));
//				System.out.println("------------统计并发送世界Boss排名、战斗奖励");
				
				//先删除世界Boss排行实例数据
				getInstWorldBossRankDAL().deleteByWhere("");
				Map<Integer, WorldBossPlayer> worldBossPlayerMap = WorldBossPlayerMapUtil.getMap();
				Iterator<WorldBossPlayer> iterator = worldBossPlayerMap.values().iterator();
				List<WorldBossPlayer> worldBossPlayerList = new ArrayList<WorldBossPlayer>();
				while (iterator.hasNext()) {
					WorldBossPlayer worldBossPlayer = iterator.next();
					worldBossPlayerList.add(worldBossPlayer);
				}

				Collections.sort(worldBossPlayerList, new Comparator<Object>() {
					public int compare(Object a, Object b) {
						long one = ((WorldBossPlayer)a).getBossSumHurt();
						long two = ((WorldBossPlayer)b).getBossSumHurt(); 
						return (int)(two - one); 
					}
				}); 
				
				//内存存储[要做序列化反序列化处理]
				ParamConfig.worldBossRankList = worldBossPlayerList;
				
				//记录本次名次日志
				int rank = 0;
				StringBuilder bossRankLogSb = new StringBuilder(DateUtil.getCurrTime() + "世界BOSS排行信息: ");
				for(WorldBossPlayer obj : worldBossPlayerList){
					rank ++ ;
					bossRankLogSb.append("rank=" + rank + " instPlayerId=" + obj.getPlayerId() + " 打世界boss总伤害=" + obj.getBossSumHurt() + " 打世界boss总次数=" + obj.getBossFightTimes()).append(";");
				}
				bossRankLogSb.append("最后一击玩家Id=").append(WorldBossPlayerMapUtil.lastFightInstPlayerId);
				LogUtil.info(bossRankLogSb.toString());
				
				int index = 0;
				for(WorldBossPlayer obj : worldBossPlayerList){
					index ++;
//					MessageData syncMsgData = new MessageData();
					
					//存储前10名玩家
					if(index <= 10){
						InstWorldBossRank instWorldBossRank = new InstWorldBossRank();
						instWorldBossRank.setInstPlayerId(obj.getPlayerId());
						instWorldBossRank.setRank(index);
						instWorldBossRank.setHurt(obj.getBossSumHurt());
						getInstWorldBossRankDAL().add(instWorldBossRank, 0);
					}
					
//					List<DictWorldBossReward> dictWorldBossRewardList = DictList.dictWorldBossRewardList;
					// 存储最后一击
					if(WorldBossPlayerMapUtil.lastFightInstPlayerId == obj.getPlayerId()){
						InstWorldBossRank instWorldBossRank = new InstWorldBossRank();
						instWorldBossRank.setInstPlayerId(obj.getPlayerId());
						instWorldBossRank.setRank(0);
						instWorldBossRank.setHurt(obj.getBossSumHurt());
						getInstWorldBossRankDAL().add(instWorldBossRank, 0);
						//将最后一击玩家记录置为0
						WorldBossPlayerMapUtil.lastFightInstPlayerId = 0;

					/*
						 *最后一击也在大宝箱里 						
						String description = "恭喜您在血战魂殿中获得最后一击奖励：";
						for(DictWorldBossReward dictWorldBossReward : dictWorldBossRewardList){
							if(0 >= dictWorldBossReward.getUpRank() && 0 <= dictWorldBossReward.getDownRank()){
//								String things = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.copper + "_" + dictWorldBossReward.getCopper() + ";" + StaticTableType.DictThing + "_" + StaticThing.linden + "_" + dictWorldBossReward.getPrestige();
								String things = dictWorldBossReward.getDescription();
								ActivityUtil.addInstPlayerAward(obj.getPlayerId(), 4, things, DateUtil.getCurrTime(), description, syncMsgData);
								int instPlayerId = obj.getPlayerId();
								Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
								if(player != null){
									String channelId = player.getChannelId();
									if(channelId != null && !channelId.equals("")){
										MessageUtil.sendSyncMsg(channelId, syncMsgData);
									}
								}
								break;
							}
						}*/
						
					}
					
					/*排名奖励放到大宝箱里    战斗奖励,每次战斗完立即按次数结算
					//FIXED by cui
					//修改世界BOSS 奖励
//					int copper = 0;
//					int prestige = 0;
					String things = "";
					for(DictWorldBossReward dictWorldBossReward : dictWorldBossRewardList){
						if(index >= dictWorldBossReward.getUpRank() && index <= dictWorldBossReward.getDownRank()){
//							copper = dictWorldBossReward.getCopper();
//							prestige = dictWorldBossReward.getPrestige();
							things = dictWorldBossReward.getDescription();
							break;
						}
					}
					
					
					
  					String description = "恭喜您在血战魂殿中获得排名奖励：";
//					String things = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.copper + "_" + copper + ";" + StaticTableType.DictThing + "_" + StaticThing.linden + "_" + prestige;
					ActivityUtil.addInstPlayerAward(obj.getPlayerId(), 4, things, DateUtil.getCurrTime(), description, syncMsgData);
					
					if(obj.getBossFightTimes() > 0){
						description = "恭喜您在血战魂殿中获得战斗奖励：";
						things = StaticTableType.DictThing + "_" + StaticThing.linden + "_" + obj.getBossFightTimes() * DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossFightReward);
						ActivityUtil.addInstPlayerAward(obj.getPlayerId(), 4, things, DateUtil.getCurrTime(), description, syncMsgData);
					}
					
					
					int instPlayerId = obj.getPlayerId();
					Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
					if(player != null){
						String channelId = player.getChannelId();
						if(channelId != null && !channelId.equals("")){
							MessageUtil.sendSyncMsg(channelId, syncMsgData);
						}
					}*/
				}
			
				//清空世界Boss玩家管理Map
				WorldBossPlayerMapUtil.clearAll();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
