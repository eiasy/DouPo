package com.huayi.doupo.logic.handler.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictActivityGroupDiscount;
import com.huayi.doupo.base.model.DictActivityGroupRate;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.DictWorldBoss;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerGroup;
import com.huayi.doupo.base.model.InstPlayerHoldStar;
import com.huayi.doupo.base.model.InstWorldBoss;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.HoldStarUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 归档世界boss相关信息[世界bossId, 血量等]
 * @author mp
 * @date 2015-11-26 下午2:02:23
 */
public class WoldBossResetHandler extends BaseHandler implements Job {
	
	/**
	 * 执行逻辑
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("aaaaaaaaaaa---WoldBossResetHandler---aaaaaaaaaaaaaaa");
		worldBossReset();
		resetHoldStar();
		goupBoxReward ();
		clearGroupBoxInfo();
		clearNormalExchange();
	}
	
	/**
	 * 处理团购箱子奖励
	 * @author mp
	 * @date 2015-12-21 上午9:23:30
	 * @Description
	 */
	private static void goupBoxReward() {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.groupon + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getEndTime()).equals(DateUtil.getYmdDate())) {
					
					//计算折扣
					int groupBoxNum = 0;
					List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '" + StaticBigTable.groupBoxNum + "'", 0);
					if (instPlayerBigTableList.size() > 0) {
						groupBoxNum = ConvertUtil.toInt(instPlayerBigTableList.get(0).getValue1());
					}
					float discount = 1.0f;
					for (DictActivityGroupDiscount obj : DictList.dictActivityGroupDiscountList) {
						if (groupBoxNum >= obj.getStartNum() && groupBoxNum <= obj.getEndNum()) {
							discount = obj.getDiscount();
							break;
						}
					}
					if (discount == 1.0f) {
						if (groupBoxNum > DictList.dictActivityGroupDiscountList.get(DictList.dictActivityGroupDiscountList.size() - 1).getEndNum()) {
							discount = DictList.dictActivityGroupDiscountList.get(DictList.dictActivityGroupDiscountList.size() - 1).getDiscount();
						}
					}
					DictThing thing = DictMap.dictThingMap.get(StaticThing.groupBox + "");
					
					List<InstPlayerGroup> instPlayerRankGroupList = getInstPlayerGroupDAL().getList(" buyBoxNum != 0 order by buyBoxNum desc, buyBoxTime asc", 0);
					int rank = 0;
					StringBuilder logSb = new StringBuilder(DateUtil.getCurrTime() + "团购信息：");
					for (InstPlayerGroup instPlayerGroup : instPlayerRankGroupList) {
						rank++;
						int instPlayerId = instPlayerGroup.getInstPlayerId();
						Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
						
						int buyGroupBoxNum = instPlayerGroup.getBuyBoxNum();
						int oldPrice = buyGroupBoxNum * thing.getOldBuyGold();//原价
						int discountPrice = buyGroupBoxNum * (int)(thing.getOldBuyGold() * discount);//折扣价
						int price = oldPrice - discountPrice;//返利(元宝)
						logSb.append("instPlayerId=" + instPlayerId + " 排名 = " + rank + " 购买箱子数=" + buyGroupBoxNum + " 购买箱子共花总钱数=" + oldPrice + " 所享折扣=" + discount + " 折扣以后的总价格=" + discountPrice + " 返利=" + price).append("/");
						
						// 在线玩家
						if (player != null) {
							
							//团购返利
							String things = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.gold + "_" + price;
							try {
								MessageData otherSyncMsgData = new MessageData();
								ActivityUtil.addInstPlayerAward(instPlayerId, 3, things, DateUtil.getCurrTime(), "您在团购活动中获得元宝返利：", otherSyncMsgData);
								MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//前十名有额外返利
							if (rank <= DictList.dictActivityGroupRateList.size()) {
								int rankRetBold = 0;
								for (DictActivityGroupRate obj : DictList.dictActivityGroupRateList) {
									if (rank == obj.getRank()) {
										rankRetBold = (int)(oldPrice * obj.getRebate());
										break;
									}
								}
								String rankThings = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.gold + "_" + rankRetBold;
								try {
									MessageData otherSyncMsgData = new MessageData();
									ActivityUtil.addInstPlayerAward(instPlayerId, 3, rankThings, DateUtil.getCurrTime(), "您在团购活动中排名为" + rank + ",获得奖励：", otherSyncMsgData);
									MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else {
							
							//将前十名的返利记录
							if (rank <= DictList.dictActivityGroupRateList.size()) {
								int rankRetBold = 0;
								for (DictActivityGroupRate obj : DictList.dictActivityGroupRateList) {
									if (rank == obj.getRank()) {
										rankRetBold = (int)(oldPrice * obj.getRebate());
										break;
									}
								}
								if (ParamConfig.groupRankRetGoldMap.containsKey(instPlayerId)) {
									ConcurrentHashMap<String, String> innerMap = ParamConfig.groupRankRetGoldMap.get(instPlayerId);
									innerMap.put(dictActivity.getEndTime(), rank + "_" + rankRetBold);
								} else {
									ConcurrentHashMap<String, String> innerMap = new ConcurrentHashMap<String, String>();
									innerMap.put(dictActivity.getEndTime(), rank + "_" + rankRetBold);
									ParamConfig.groupRankRetGoldMap.put(instPlayerId, innerMap);
								}
							}
							
							//退换团购多余元宝记录
							if (ParamConfig.groupRetGoldMap.containsKey(instPlayerId)) {
								ConcurrentHashMap<String, Integer> innerMap = ParamConfig.groupRetGoldMap.get(instPlayerId);
								innerMap.put(dictActivity.getEndTime(), price);
							} else {
								ConcurrentHashMap<String, Integer> innerMap = new ConcurrentHashMap<String, Integer>();
								innerMap.put(dictActivity.getEndTime(), price);
								ParamConfig.groupRetGoldMap.put(instPlayerId, innerMap);
							}
						}
					}
					LogUtil.info(logSb.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("团购返利Error", e);
		}
	}
	
	/**
	 * 清除超值兑换数据
	 * @author mp
	 * @date 2015-12-21 上午11:38:52
	 * @Description
	 */
	private static void clearNormalExchange () {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.normalExchange + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getEndTime()).equals(DateUtil.getYmdDate())) {
					try {
						getInstActivityExchangeDAL().deleteByWhere("");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			LogUtil.error("清除超值兑换Error", e);
		}
	}
	
	/**
	 * 清空团购相关数据（含发奖内存数据-发奖只在内存保留15天，过期不候）
	 * @author mp
	 * @date 2015-12-21 上午9:50:03
	 * @Description
	 */
	private static void clearGroupBoxInfo () {
		try {
			
			//如果活动结束，且发奖逻辑处理完成,清空实例数据
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.groupon + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getEndTime()).equals(DateUtil.getYmdDate())) {
					try {
						getInstActivityDAL().update("update Inst_Player_Group set buyBoxNum = 0, rewardState = 0, buyBoxTime = ''");
						getInstPlayerBigTableDAL().deleteByWhere(" instPlayerId = 0 and properties = '" + StaticBigTable.groupBoxNum + "'");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			//清空过15天未登录玩家的奖励内存数据-团购返元宝
			for (Entry<Integer, ConcurrentHashMap<String, Integer>> entry : ParamConfig.groupRetGoldMap.entrySet()) {
				ConcurrentHashMap<String, Integer> innerMap = new ConcurrentHashMap<String, Integer>();
				innerMap = entry.getValue();
				try {
					String key = DateUtil.getNumDayDate(DateUtil.getYmdDate(), -DictMapUtil.getSysConfigIntValue(StaticSysConfig.awardDay));
					if (innerMap.containsKey(key)) {
						innerMap.remove(key);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//清空过15天未登录玩家的奖励内存数据-前十名的返利
			for (Entry<Integer, ConcurrentHashMap<String, String>> entry : ParamConfig.groupRankRetGoldMap.entrySet()) {
				ConcurrentHashMap<String, String> innerMap = new ConcurrentHashMap<String, String>();
				innerMap = entry.getValue();
				try {
					String key = DateUtil.getNumDayDate(DateUtil.getYmdDate(), -DictMapUtil.getSysConfigIntValue(StaticSysConfig.awardDay));
					if (innerMap.containsKey(key)) {
						innerMap.remove(key);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("清理不符合条件的团购相关数据Error", e);
		}
	}
	
	/**
	 * 重置占星相关
	 * @author mp
	 * @date 2015-12-8 上午10:19:49
	 * @Description
	 */
	private static void resetHoldStar() {
		try {
			for (Entry<String, Player> entry : PlayerMapUtil.getMap().entrySet()) {
				String channelId = entry.getKey();
				Player player = entry.getValue();
				int instPlayerId = player.getPlayerId();
				
				//给在线玩家初始化占星信息
				List<InstPlayerHoldStar> instPlayerHoldStarList = getInstPlayerHoldStarDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
				for (InstPlayerHoldStar instPlayerHoldStar : instPlayerHoldStarList) {
					MessageData syncMsgData = new MessageData();
					instPlayerHoldStar.setStep(1);
					instPlayerHoldStar.setStarNum(0);
					instPlayerHoldStar.setHoldStarTimes(0);
					instPlayerHoldStar.setHoldStarTime("");
					instPlayerHoldStar.setHoldStarFreeRefreshedTimes(0);
					instPlayerHoldStar.setHoldStarNoFreeRefreshedTimes(0);
					instPlayerHoldStar.setHoldStarRefreshedTime("");
					instPlayerHoldStar.setRewardRefreshedTimes(0);
					instPlayerHoldStar.setRewardRefreshedTime("");
					instPlayerHoldStar.setUpStars(HoldStarUtil.refreshUpStars());
					instPlayerHoldStar.setDownStars(HoldStarUtil.refreshDownStars(instPlayerHoldStar.getHoldStarGradeId(), 1, instPlayerHoldStar.getUpStars()));
					instPlayerHoldStar.setRewards(HoldStarUtil.refreshReward(instPlayerHoldStar.getHoldStarGradeId(), 1));//0-自然刷新 1-系统刷新, 初始化时按系统刷新算
					instPlayerHoldStar.setSysRefreshTime(DateUtil.getCurrTime());
					getInstPlayerHoldStarDAL().update(instPlayerHoldStar, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerHoldStar, instPlayerHoldStar.getId(), instPlayerHoldStar.getResult(), syncMsgData);
					MessageUtil.sendSyncMsg(channelId, syncMsgData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 世界Boss重置
	 * @author mp
	 * @date 2015-12-7 下午7:01:38
	 * @Description
	 */
	private static void worldBossReset () {
		try {
			
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
			LogUtil.error("归档世界boss相关信息Error", e);
		}
	}

}
