package com.huayi.doupo.logic.handler.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictActivityStrogerHero;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.model.player.PlayerMemObjMapUtil;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.CollectionUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 定时器工具类-用于最强英雄定点发奖
 * @author mp
 * @date 2015-8-19 下午2:42:51
 */
public class ScheduleUtil {

	public static void main(String[] args) throws Exception {
		DictData.loadDictData();
		start21();
		
	/*	long currMill = DateUtil.getCurrMill();
		String currTime = DateUtil.getTimeByMill(currMill);
		String currYmdDate = DateUtil.getYmdDate(currTime);
		
		System.out.println(currTime + "    " + currYmdDate);
		System.out.println(DateUtil.getTimeInfoHms(currTime, DateType.HOUR_OF_DAY));
		
//		String currYmdDate = DateUtil.getYmdDate();
		String nextDayZero = DateUtil.getNumDayDate(currYmdDate, 1) + " 00:00:00";
		System.out.println(nextDayZero);
		System.out.println(DateUtil.getMillSecond(nextDayZero) - DateUtil.getCurrMill());*/
		
//		DictData.loadDictData();
//		System.out.println(DateUtil.getMillSecond(DateUtil.getYmdDate() + " 09:00:00"));
//		System.out.println(DateUtil.getMillSecond(DateUtil.getYmdDate() + " 9:00:00"));
//		System.out.println(DateUtil.getMillSecond(DateUtil.getYmdDate() + " " + DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime1) + ":00:00"));
	}
	
	/**
	 * 启动最强英雄12点发奖调度器
	 * @author mp
	 * @date 2015-8-19 下午2:44:03
	 * @Description
	 */
	public static void start12 () {
		try {
			long delayMill = 0;
			long currMill = DateUtil.getCurrMill();
			String currTime = DateUtil.getTimeByMill(currMill);
			String currYmdDate = DateUtil.getYmdDate(currTime);
			long reward12Mill = DateUtil.getMillSecond(currYmdDate + " " + DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime1) + ":00:00");
			if (currMill > reward12Mill) {
				//等待时间 = (次日凌晨时间毫秒数-当前时间毫秒数) + 12个小时的毫秒数
				String nextDayZero = DateUtil.getNumDayDate(currYmdDate, 1) + " 00:00:00";
				delayMill = (DateUtil.getMillSecond(nextDayZero) - currMill) + (DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime1) * 60 * 60 * 1000);
			} else if (currMill < reward12Mill) {
				//等待时间 = 当天12点毫秒数-当前时间毫秒数
				delayMill = reward12Mill - currMill;
			}
			
			ParamConfig.exec12.scheduleAtFixedRate (new Runnable() {
				@Override
				public void run() {
					
					//是否在活动期内
					if (ActivityUtil.isInActivity(StaticActivity.StrongHero)) {
						//按积分降序排序最强英雄积分map
						Map<Integer, Integer> map = new HashMap<Integer, Integer>();
						for (Entry<Integer, Integer> entry : ParamConfig.strogerHeroJifenMap.entrySet()) {
							map.put(entry.getKey(), entry.getValue());
						}
						Map<Integer, Integer> sortedMap = CollectionUtil.sortByValueDown(map);
						int rank = 0;
						LogUtil.info(DateUtil.getCurrTime() + " 巅峰英雄12点积分排行情况[playerId, jifen] = " + sortedMap);
						
						for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
							rank ++;
							if (rank > DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardNum)) {
								break;
							}
							int instPlayerId = entry.getKey();
							Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
							//在线玩家
							if(player != null){
								String things = "";
								List<DictActivityStrogerHero> activityStrogerHeroList = DictList.dictActivityStrogerHeroList;
								for (DictActivityStrogerHero obj : activityStrogerHeroList) {
									if (DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime1) == obj.getRewardTimePoint() && rank == obj.getRank()) {
										things = obj.getRewards();
										break;
									}
								}
								if (things.length() > 0) {
									try {
										MessageData otherSyncMsgData = new MessageData();
										ActivityUtil.addInstPlayerAward(instPlayerId, 3, things, DateUtil.getCurrTime(), "您在巅峰强者活动中排名为" + rank + ",获得奖励：", otherSyncMsgData);
										MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							} else {
								//不在线玩家,放入缓存,停服后序列化此map
								if (ParamConfig.strogerRankRewardMap.containsKey(instPlayerId)) {
									ConcurrentHashMap<String, Integer> innerMap = ParamConfig.strogerRankRewardMap.get(instPlayerId);
									innerMap.put(DateUtil.getCurrTime(), rank);
//								System.out.println("schedule update map " + ParamConfig.strogerRankRewardMap);
								} else {
									ConcurrentHashMap<String, Integer> innerMap = new ConcurrentHashMap<String, Integer>();
									innerMap.put(DateUtil.getCurrTime(), rank);
									ParamConfig.strogerRankRewardMap.put(instPlayerId, innerMap);
//								System.out.println("schedule add map " + ParamConfig.strogerRankRewardMap);
								}
							}
						}
					}
					
				}
			}, delayMill, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			LogUtil.error("最强英雄12点发奖调度器 Error", e);
		}
	}
	
	/**
	 * 启动最强英雄21点发奖调度器
	 * @author mp
	 * @date 2015-8-19 下午2:44:20
	 * @Description
	 */
	public static void start21 () {
		try {
			long delayMill = 0;
			long currMill = DateUtil.getCurrMill();
			String currTime = DateUtil.getTimeByMill(currMill);
			String currYmdDate = DateUtil.getYmdDate(currTime);
			long reward12Mill = DateUtil.getMillSecond(currYmdDate + " " + DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime2) + ":00:00");
			if (currMill > reward12Mill) {
				//等待时间 = (次日凌晨时间毫秒数-当前时间毫秒数) + 21个小时的毫秒数
				String nextDayZero = DateUtil.getNumDayDate(currYmdDate, 1) + " 00:00:00";
				delayMill = (DateUtil.getMillSecond(nextDayZero) - currMill) + (DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime2) * 60 * 60 * 1000);
			} else if (currMill < reward12Mill) {
				//等待时间 = 当天21点毫秒数-当前时间毫秒数
				delayMill = reward12Mill - currMill;
			}
			
			ParamConfig.exec21.scheduleAtFixedRate (new Runnable() {
				
				@Override
				public void run() {
					
					//是否在活动期内
					if (ActivityUtil.isInActivity(StaticActivity.StrongHero)) {
						
						//按积分降序排序最强英雄积分map
						Map<Integer, Integer> map = new HashMap<Integer, Integer>();
						for (Entry<Integer, Integer> entry : ParamConfig.strogerHeroJifenMap.entrySet()) {
							map.put(entry.getKey(), entry.getValue());
						}
						Map<Integer, Integer> sortedMap = CollectionUtil.sortByValueDown(map);
						int rank = 0;
						LogUtil.info(DateUtil.getCurrTime() + " 巅峰英雄21点积分排行情况[playerId, jifen] = " + sortedMap);
						for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
							rank ++;
							if (rank > DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardNum)) {
								break;
							}
							int instPlayerId = entry.getKey();
							Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
							//在线玩家
							if(player != null){
								String things = "";
								List<DictActivityStrogerHero> activityStrogerHeroList = DictList.dictActivityStrogerHeroList;
								for (DictActivityStrogerHero obj : activityStrogerHeroList) {
									if (DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime2) == obj.getRewardTimePoint() && rank == obj.getRank()) {
										things = obj.getRewards();
										break;
									}
								}
								if (things.length() > 0) {
									try {
										MessageData otherSyncMsgData = new MessageData();
										ActivityUtil.addInstPlayerAward(instPlayerId, 3, things, DateUtil.getCurrTime(), "您在巅峰强者活动中排名为" + rank + ",获得奖励：", otherSyncMsgData);
										MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							} else {
								//不在线玩家,放入缓存,停服后序列化此map
								if (ParamConfig.strogerRankRewardMap.containsKey(instPlayerId)) {
									ConcurrentHashMap<String, Integer> innerMap = ParamConfig.strogerRankRewardMap.get(instPlayerId);
									innerMap.put(DateUtil.getCurrTime(), rank);
								} else {
									ConcurrentHashMap<String, Integer> innerMap = new ConcurrentHashMap<String, Integer>();
									innerMap.put(DateUtil.getCurrTime(), rank);
									ParamConfig.strogerRankRewardMap.put(instPlayerId, innerMap);
								}
							}
						}
					}
				}
			}, delayMill, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			LogUtil.error("最强英雄21点发奖调度器 Error", e);
		}
	}
	
	/**
	 * 启动最强英雄23点发奖调度器
	 * @author mp
	 * @date 2015-8-19 下午2:44:33
	 * @Description
	 */
	public static void start23 () {
		try {
			long delayMill = 0;
			long currMill = DateUtil.getCurrMill();
			String currTime = DateUtil.getTimeByMill(currMill);
			String currYmdDate = DateUtil.getYmdDate(currTime);
			long reward12Mill = DateUtil.getMillSecond(currYmdDate + " " + DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime3) + ":00:00");
			if (currMill > reward12Mill) {
				//等待时间 = (次日凌晨时间毫秒数-当前时间毫秒数) + 23个小时的毫秒数
				String nextDayZero = DateUtil.getNumDayDate(currYmdDate, 1) + " 00:00:00";
				delayMill = (DateUtil.getMillSecond(nextDayZero) - currMill) + (DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime3) * 60 * 60 * 1000);
			} else if (currMill < reward12Mill) {
				//等待时间 = 当天23点毫秒数-当前时间毫秒数
				delayMill = reward12Mill - currMill;
			}
			
			ParamConfig.exec23.scheduleAtFixedRate (new Runnable() {
				@Override
				public void run() {
					
					if (ActivityUtil.isInActivity(StaticActivity.StrongHero)) {
						
						//按积分降序排序最强英雄积分map
						Map<Integer, Integer> map = new HashMap<Integer, Integer>();
						for (Entry<Integer, Integer> entry : ParamConfig.strogerHeroJifenMap.entrySet()) {
							map.put(entry.getKey(), entry.getValue());
						}
						Map<Integer, Integer> sortedMap = CollectionUtil.sortByValueDown(map);
						int rank = 0;
						LogUtil.info(DateUtil.getCurrTime() + " 巅峰英雄23点积分排行情况[playerId, jifen] = " + sortedMap);
						for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
							rank ++;
							if (rank > DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardNum)) {
								break;
							}
							int instPlayerId = entry.getKey();
							Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
							//在线玩家
							if(player != null){
								String things = "";
								List<DictActivityStrogerHero> activityStrogerHeroList = DictList.dictActivityStrogerHeroList;
								for (DictActivityStrogerHero obj : activityStrogerHeroList) {
									if (DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime3) == obj.getRewardTimePoint() && rank == obj.getRank()) {
										things = obj.getRewards();
										break;
									}
								}
								if (things.length() > 0) {
									try {
										MessageData otherSyncMsgData = new MessageData();
										ActivityUtil.addInstPlayerAward(instPlayerId, 3, things, DateUtil.getCurrTime(), "您在巅峰强者活动中排名为" + rank + ",获得奖励：", otherSyncMsgData);
										MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							} else {
								//不在线玩家,放入缓存,停服后序列化此map
								if (ParamConfig.strogerRankRewardMap.containsKey(instPlayerId)) {
									ConcurrentHashMap<String, Integer> innerMap = ParamConfig.strogerRankRewardMap.get(instPlayerId);
									innerMap.put(DateUtil.getCurrTime(), rank);
								} else {
									ConcurrentHashMap<String, Integer> innerMap = new ConcurrentHashMap<String, Integer>();
									innerMap.put(DateUtil.getCurrTime(), rank);
									ParamConfig.strogerRankRewardMap.put(instPlayerId, innerMap);
								}
							}
						}
					}
				}
			}, delayMill, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			LogUtil.error("最强英雄23点发奖调度器 Error", e);
		}
	}
	
	/**
	 * 关闭最强英雄12点发奖调度器
	 * @author mp
	 * @date 2015-8-19 下午2:44:03
	 * @Description
	 */
	public static void close12 () {
		ParamConfig.exec12.shutdown();
	}
	
	/**
	 * 关闭最强英雄21点发奖调度器
	 * @author mp
	 * @date 2015-8-19 下午2:44:20
	 * @Description
	 */
	public static void close21 () {
		ParamConfig.exec21.shutdown();
	}
	
	/**
	 * 关闭最强英雄23点发奖调度器
	 * @author mp
	 * @date 2015-8-19 下午2:44:33
	 * @Description
	 */
	public static void close23 () {
		ParamConfig.exec23.shutdown();
	}
	
	/**
	 * 开启所有
	 * @author mp
	 * @date 2015-8-19 下午2:46:01
	 * @Description
	 */
	public static void startAll () {
		start12();
		start21();
		start23();
		start1Hour();
	}
	
	/**
	 * 关闭所有
	 * @author mp
	 * @date 2015-8-19 下午2:46:17
	 * @Description
	 */
	public static void closeAll () {
		close12();
		close21();
		close23();
	}

	/**
	 * 每小时检测一下玩家缓存对象,将2[gm工具可调配]个小时之前且不再先的玩家去掉
	 * 登录跟初始化的时候玩家缓存对象设置成0,玩家退出时设置为当前时间,清除的是退出游戏2个小时的人,0的时候表示在线,不处理
	 * @author mp
	 * @date 2015-8-30 上午10:37:20
	 * @Description
	 */
	public static void start1Hour () {
		try {
			ParamConfig.exec1Hour.scheduleAtFixedRate (new Runnable() {
				@Override
				public void run() {
					System.out.println(DateUtil.getCurrTime() + "0000000000=============0000000000000");
					try {
						long twoDayMill = ParamConfig.clearHourNum * 60 * 60 * 1000;
//						long twoDayMill = 5 * 60 * 1000;
						long currMill = DateUtil.getCurrMill();
						List<Integer> rmKeyList = Lists.newArrayList();
						for(Entry<Integer, PlayerMemObj> entry : PlayerMemObjMapUtil.getMap().entrySet()){
							int key = entry.getKey();
							PlayerMemObj playerMemObj = entry.getValue();
							//等于0的时候,表示玩家在线,[登录的时候设置成0,只有玩家退出的时候才把0设置成当前时间]
							if (playerMemObj.currTime != 0) {
								if ((currMill - playerMemObj.currTime) >= twoDayMill) {
									rmKeyList.add(key);
								}
							}
						}
						System.out.println("del instPlayerId = " + rmKeyList.toString());
						System.out.println("del before " + PlayerMemObjMapUtil.getSize());
						for (int rmKey : rmKeyList) {
							PlayerMemObjMapUtil.removeByPlayerId(rmKey);
						}
						System.out.println("del after " + PlayerMemObjMapUtil.getSize());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 0, 1, TimeUnit.HOURS);
//			}, 0, 1, TimeUnit.MINUTES);
			
		} catch (Exception e) {
			LogUtil.error("每小时检测一下玩家缓存对象Error", e);
		}
	}

}
