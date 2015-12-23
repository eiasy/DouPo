package com.huayi.doupo.logic.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictActivityAddRecharge;
import com.huayi.doupo.base.model.DictActivityDailyDeals;
import com.huayi.doupo.base.model.DictActivityGrabTheHour;
import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictActivityLimitShopping;
import com.huayi.doupo.base.model.DictActivityLimitTimeHeroJiFenReward;
import com.huayi.doupo.base.model.DictActivityLimitTimeHeroRankReward;
import com.huayi.doupo.base.model.DictActivityLuck;
import com.huayi.doupo.base.model.DictActivitySoulChapterDrop;
import com.huayi.doupo.base.model.DictActivityStrogerHero;
import com.huayi.doupo.base.model.DictActivityTreasures;
import com.huayi.doupo.base.model.DictMineBoxThing;
import com.huayi.doupo.base.model.DictWorldBoss;
import com.huayi.doupo.base.model.DictWorldBossReward;
import com.huayi.doupo.base.model.DictWorldBossStore;
import com.huayi.doupo.base.model.DictactivityExchange;
import com.huayi.doupo.base.model.DictactivityTotalCost;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstWorldBoss;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.logic.handler.util.ActivityUtil;

/**
 * 开服初始化Util
 * @author mp
 * @date 2015-8-19 下午5:49:08
 */
public class InitUtil extends DALFactory {
	
	
	/**
	 * 获取某个类型的整点抢列表
	 * @author mp
	 * @date 2015-10-10 上午10:10
	 * @throws Exception
	 * @Description
	 */
	private static List<DictActivityGrabTheHour> getActivityGrabTheHourByType (int type, List<DictActivityGrabTheHour> oldList) {
		List<DictActivityGrabTheHour>  newList = new ArrayList<>();
		for (DictActivityGrabTheHour obj : oldList) {
			if (obj.getType() == type) {
				newList.add(obj);
			}
		}
		return newList;
	}
	
	/**
	 * 获取某个星期的每日特惠列表
	 * @author mp
	 * @date 2015-10-10 上午10:24
	 * @throws Exception
	 * @Description
	 */
	private static List<DictActivityDailyDeals> getActivityDailyDealsByDay (int day, List<DictActivityDailyDeals> oldList) {
		List<DictActivityDailyDeals>  newList = new ArrayList<>();
		for (DictActivityDailyDeals obj : oldList) {
			if (obj.getDay() == day) {
				newList.add(obj);
			}
		}
		return newList;
	}
	
	/**
	 * 从活动服拿数据
	 * @author mp
	 * @date 2015-10-10 上午9:46
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void loadDataFromActivityServer () throws Exception{
		if (ParamConfig.isUserActivity == 1) {
			try {
				String activityServerUrl = SysConfigUtil.getValue("activity.request.url");
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("command", "getActivityData");
				String params = HttpClientUtil.httpBuildQuery(paramMap);
				String ret = HttpClientUtil.postMapSubmit(activityServerUrl, params);
				System.out.println("ret=" + ret);
				for (String activity : ret.split("\\|")) {
					String activityName = activity.split("#")[0];
//					System.out.println("activityName=" + activityName);
					String activityData = activity.split("#")[1];
//					System.out.println("activityData=" + activityData);
					JSONArray jsonobj = JSONArray.fromObject(activityData);  
					
					//累计充值  [时间段]
					if (activityName.equals("Dict_Activity_AddRecharge")) {
						if (ActivityUtil.isInActivity(StaticActivity.addRecharge)) {
							List<DictActivityAddRecharge> addRechargeList = (List<DictActivityAddRecharge>) JSONArray.toList(jsonobj,DictActivityAddRecharge.class);
							DictList.dictActivityAddRechargeList = addRechargeList;
							Map<String,DictActivityAddRecharge> activityAddRechargeMap = new HashMap<>();
							for (DictActivityAddRecharge obj : addRechargeList) {
								activityAddRechargeMap.put(obj.getId() + "", obj);
							}
							DictMap.dictActivityAddRechargeMap = activityAddRechargeMap;
						}
					
					//每日特惠 [永久-但是是按31天配置的]
					} else if (activityName.equals("Dict_Activity_DailyDeals")) {
						List<DictActivityDailyDeals> dailyDealsList = (List<DictActivityDailyDeals>) JSONArray.toList(jsonobj,DictActivityDailyDeals.class);
						DictList.dictActivityDailyDealsList = dailyDealsList;
						Map<String,DictActivityDailyDeals> activityDailyDealsMap = new HashMap<>();
						HashSet<Integer> hashSet = new HashSet<>();
						for (DictActivityDailyDeals obj : dailyDealsList) {
							activityDailyDealsMap.put(obj.getId() + "", obj);
							hashSet.add(obj.getDay());
						}
						DictMap.dictActivityDailyDealsMap = activityDailyDealsMap;
						
						//组织mapList
						Iterator<Integer> iterator = hashSet.iterator();
						while(iterator.hasNext()){
							int day = iterator.next();
							List<DictActivityDailyDeals> newList = getActivityDailyDealsByDay(day, dailyDealsList);
							DictMapList.dictActivityDailyDealsMap.put(day, newList);
						}
						
					//整点抢 [时间段]
					} else if (activityName.equals("Dict_Activity_GrabTheHour")) {
						if (ActivityUtil.isInActivity(StaticActivity.grabTheHour)) {
							List<DictActivityGrabTheHour> grabTheHourList = (List<DictActivityGrabTheHour>) JSONArray.toList(jsonobj,DictActivityGrabTheHour.class);
							DictList.dictActivityGrabTheHourList = grabTheHourList;
							Map<String,DictActivityGrabTheHour> activityGrabTheHourMap = new HashMap<>();
							HashSet<Integer> hashSet = new HashSet<>();
							for (DictActivityGrabTheHour obj : grabTheHourList) {
								activityGrabTheHourMap.put(obj.getId() + "", obj);
								hashSet.add(obj.getType());
							}
							DictMap.dictActivityGrabTheHourMap = activityGrabTheHourMap;
							
							//组织mapList
							Iterator<Integer> iterator = hashSet.iterator();
							while(iterator.hasNext()){
								int type = iterator.next();
								List<DictActivityGrabTheHour> newList = getActivityGrabTheHourByType(type, grabTheHourList);
								DictMapList.dictActivityGrabTheHourMap.put(type, newList);
							}
						}
						
					//限时抢购 [时间段]
					} else if (activityName.equals("Dict_Activity_LimitShopping")) {
						if (ActivityUtil.isInActivity(StaticActivity.limitShopping)) {
							List<DictActivityLimitShopping> limitShoppingList = (List<DictActivityLimitShopping>) JSONArray.toList(jsonobj,DictActivityLimitShopping.class);
							DictList.dictActivityLimitShoppingList = limitShoppingList;
							Map<String,DictActivityLimitShopping> activityLimitShoppingMap = new HashMap<>();
							for (DictActivityLimitShopping obj : limitShoppingList) {
								activityLimitShoppingMap.put(obj.getId() + "", obj);
							}
							DictMap.dictActivityLimitShoppingMap = activityLimitShoppingMap;
						}
					
					//限时英雄积分奖励 [时间段]
					} else if (activityName.equals("Dict_Activity_LimitTimeHeroJiFenReward")) {
						if (ActivityUtil.isInActivity(StaticActivity.LimitTimeHero)) {
							List<DictActivityLimitTimeHeroJiFenReward> limitTimeHeroJiFenRewardList = (List<DictActivityLimitTimeHeroJiFenReward>) JSONArray.toList(jsonobj,DictActivityLimitTimeHeroJiFenReward.class);
							DictList.dictActivityLimitTimeHeroJiFenRewardList = limitTimeHeroJiFenRewardList;
							Map<String,DictActivityLimitTimeHeroJiFenReward> activityLimitTimeHeroJiFenRewardMap = new HashMap<>();
							for (DictActivityLimitTimeHeroJiFenReward obj : limitTimeHeroJiFenRewardList) {
								activityLimitTimeHeroJiFenRewardMap.put(obj.getId() + "", obj);
							}
							DictMap.dictActivityLimitTimeHeroJiFenRewardMap = activityLimitTimeHeroJiFenRewardMap;
						}
						
					//限时英雄排行奖励 [时间段]
					} else if (activityName.equals("Dict_Activity_LimitTimeHeroRankReward")) {
						if (ActivityUtil.isInActivity(StaticActivity.LimitTimeHero)) {
							List<DictActivityLimitTimeHeroRankReward> limitTimeHeroRankRewardList = (List<DictActivityLimitTimeHeroRankReward>) JSONArray.toList(jsonobj,DictActivityLimitTimeHeroRankReward.class);
							DictList.dictActivityLimitTimeHeroRankRewardList = limitTimeHeroRankRewardList;
							Map<String,DictActivityLimitTimeHeroRankReward> activityLimitTimeHeroRankRewardMap = new HashMap<>();
							for (DictActivityLimitTimeHeroRankReward obj : limitTimeHeroRankRewardList) {
								activityLimitTimeHeroRankRewardMap.put(obj.getId() + "", obj);
							}
							DictMap.dictActivityLimitTimeHeroRankRewardMap = activityLimitTimeHeroRankRewardMap;
						}
						
					//幸运转盘 [时间段]
					} else if (activityName.equals("Dict_Activity_Luck")) {
						if (ActivityUtil.isInActivity(StaticActivity.lucky)) {
							List<DictActivityLuck> luckList = (List<DictActivityLuck>) JSONArray.toList(jsonobj,DictActivityLuck.class);
							System.out.println(luckList);
							DictList.dictActivityLuckList = luckList;
							Map<String,DictActivityLuck> activityLuckMap = new HashMap<>();
							for (DictActivityLuck obj : luckList) {
								activityLuckMap.put(obj.getId() + "", obj);
							}
							DictMap.dictActivityLuckMap = activityLuckMap;
						}
						
					//巅峰强者 [时间段] 
					} else if (activityName.equals("Dict_Activity_StrogerHero")) {
						if (ActivityUtil.isInActivity(StaticActivity.StrongHero)) {
							List<DictActivityStrogerHero> strogerHeroList = (List<DictActivityStrogerHero>) JSONArray.toList(jsonobj,DictActivityStrogerHero.class);
							DictList.dictActivityStrogerHeroList = strogerHeroList;
							Map<String,DictActivityStrogerHero> activityStrogerHeroMap = new HashMap<>();
							for (DictActivityStrogerHero obj : strogerHeroList) {
								activityStrogerHeroMap.put(obj.getId() + "", obj);
							}
							DictMap.dictActivityStrogerHeroMap = activityStrogerHeroMap;
						}
					
					//累计消耗 [时间段]
					} else if (activityName.equals("Dict_activity_TotalCost")) {
						if (ActivityUtil.isInActivity(StaticActivity.SaveConsume)) {
							List<DictactivityTotalCost> totalCostList = (List<DictactivityTotalCost>) JSONArray.toList(jsonobj,DictactivityTotalCost.class);
							DictList.dictactivityTotalCostList = totalCostList;
							Map<String,DictactivityTotalCost> activityTotalCostMap = new HashMap<>();
							for (DictactivityTotalCost obj : totalCostList) {
								activityTotalCostMap.put(obj.getId() + "", obj);
							}
							DictMap.dictactivityTotalCostMap = activityTotalCostMap;
						}
						
					//招财进宝 [时间段]
					} else if (activityName.equals("Dict_Activity_Treasures")) {
						if (ActivityUtil.isInActivity(StaticActivity.MakeMoney)) {
							List<DictActivityTreasures> treasuresList = (List<DictActivityTreasures>) JSONArray.toList(jsonobj,DictActivityTreasures.class);
							DictList.dictActivityTreasuresList = treasuresList;
							Map<String,DictActivityTreasures> activityTreasuresMap = new HashMap<>();
							for (DictActivityTreasures obj : treasuresList) {
								activityTreasuresMap.put(obj.getId() + "", obj);
							}
							DictMap.dictActivityTreasuresMap = activityTreasuresMap;
						}
						
					//天魂墓地活动副本掉落
					} else if (activityName.equals("Dict_Activity_SoulChapterDrop")) {
						List<DictActivitySoulChapterDrop> soulChapterDropList = (List<DictActivitySoulChapterDrop>) JSONArray.toList(jsonobj,DictActivitySoulChapterDrop.class);
						DictList.dictActivitySoulChapterDropList = soulChapterDropList;
						Map<String,DictActivitySoulChapterDrop> soulChapterDropMap = new HashMap<>();
						for (DictActivitySoulChapterDrop obj : soulChapterDropList) {
							soulChapterDropMap.put(obj.getId() + "", obj);
						}
						DictMap.dictActivitySoulChapterDropMap = soulChapterDropMap;

					//资源矿产出表
					}  else if (activityName.equals("Dict_Mine_BoxThing")) {
						List<DictMineBoxThing> mineBoxThingList = (List<DictMineBoxThing>) JSONArray.toList(jsonobj,DictMineBoxThing.class);
						DictList.dictMineBoxThingList = mineBoxThingList;
						Map<String,DictMineBoxThing> mineBoxThingHashMap = new HashMap<>();
						for (DictMineBoxThing obj : mineBoxThingList) {
							mineBoxThingHashMap.put(obj.getId() + "", obj);
						}
						DictMap.dictMineBoxThingMap = mineBoxThingHashMap;
					//世界BOSS奖励配置表
					}  else if (activityName.equals("Dict_WorldBoss_Reward")) {
						List<DictWorldBossReward> worldBossRewardList = (List<DictWorldBossReward>) JSONArray.toList(jsonobj,DictWorldBossReward.class);
						DictList.dictWorldBossRewardList = worldBossRewardList;
						Map<String,DictWorldBossReward> worldBossRewardHashMap = new HashMap<>();
						for (DictWorldBossReward obj : worldBossRewardList) {
							worldBossRewardHashMap.put(obj.getId() + "", obj);
						}
						DictMap.dictWorldBossRewardMap = worldBossRewardHashMap;
					}
					//节假日活动
					else if (activityName.equals("Dict_Activity_Holiday")) {
						List<DictActivityHoliday> holidayList = (List<DictActivityHoliday>) JSONArray.toList(jsonobj,DictActivityHoliday.class);
						DictList.dictActivityHolidayList = holidayList;
						Map<String,DictActivityHoliday> holidayMap = new HashMap<>();
						for (DictActivityHoliday obj : holidayList) {
							holidayMap.put(obj.getId() + "", obj);
						}
						DictMap.dictActivityHolidayMap = holidayMap;
					} 
					//屠魔商店
					else if (activityName.equals("Dict_WorldBoss_Store")) {
						List<DictWorldBossStore> bossShopList = (List<DictWorldBossStore>) JSONArray.toList(jsonobj,DictWorldBossStore.class);
						DictList.dictWorldBossStoreList = bossShopList;
						Map<String, DictWorldBossStore> bossShopMap = new HashMap<>();
						for (DictWorldBossStore obj : bossShopList) {
							bossShopMap.put(obj.getId() + "", obj);
						}
						DictMap.dictWorldBossStoreMap = bossShopMap;
					}
					//限时兑换
					else if (activityName.equals("Dict_activity_Exchange")) {
						List<DictactivityExchange> activityExchangeList = (List<DictactivityExchange>) JSONArray.toList(jsonobj,DictactivityExchange.class);
						DictList.dictactivityExchangeList = activityExchangeList;
						Map<String, DictactivityExchange> activityExchangeMap = new HashMap<>();
						for (DictactivityExchange obj : activityExchangeList) {
							activityExchangeMap.put(obj.getId() + "", obj);
						}
						DictMap.dictactivityExchangeMap = activityExchangeMap;
					} 
				}
			} catch (Exception e) {
				LogUtil.error("从活动服拿字典数据Error", e);
				DictData.loadDictData();//如果出现错误重新加载本地字典数据，以本地的字典数据为准
			}
		}
	}
	
	/**
	 * 初始化最强英雄积分[如果在活动期内]
	 * @author mp
	 * @date 2015-8-19 下午5:49:18
	 * @throws Exception
	 * @Description
	 */
	public static void initStrogerHeroJifen () throws Exception{
		
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.StrongHero + "");
		if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
			if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= DateUtil.getCurrMill() && DateUtil.getCurrMill() <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
				String sql = "select instPlayerId , sum(value) as jifen from Inst_Player_GoldStatics  where type = 0 and operTime between '" + dictActivity.getStartTime() + "' and '" + dictActivity.getEndTime() + "' group by instPlayerId";
				List<Map<String, Object>> retList = getInstPlayerGoldStaticsDAL().sqlHelper(sql);
				for (Map<String, Object> retMap : retList) {
					int instPlayerId = (int)(double)Double.valueOf(retMap.get("instPlayerId").toString());
					int jifen = (int)(double)Double.valueOf(retMap.get("jifen").toString());
					ParamConfig.strogerHeroJifenMap.put(instPlayerId, jifen);
				}
			}
		}
	}
	
	/**
	 * 在活动期内初始化限时英雄活动招募积分
	 * @author mp
	 * @date 2015-9-7 下午4:55:31
	 * @throws Exception
	 * @Description
	 */
	public static void initLimiHeroJifen () throws Exception {
		
		//在活动期内初始化限时英雄活动招募积分
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.LimitTimeHero + "");
		if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
			if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= DateUtil.getCurrMill() && DateUtil.getCurrMill() <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
				List<InstPlayerBigTable> instPlayerBigTables = getInstPlayerBigTableDAL().getList("properties = '"+StaticBigTable.recruitJiFen+"'", 0);
				for (InstPlayerBigTable obj : instPlayerBigTables) {
					if (ConvertUtil.toInt(obj.getValue1()) > 0) {
						ParamConfig.recruitJifenMap.put(obj.getInstPlayerId(), ConvertUtil.toInt(obj.getValue1()));
					}
				}
			//活动期已过
			} else {
				getInstPlayerBigTableDAL().update("update Inst_Player_BigTable set value1 = '0' where properties = '"+StaticBigTable.recruitJiFen+"';");
			}
		//活动没有开始结束时间-活动为开启
		} else {
			getInstPlayerBigTableDAL().update("update Inst_Player_BigTable set value1 = '0' where properties = '"+StaticBigTable.recruitJiFen+"';");
		}
	}
	
	/**
	 * 不在活动期内，清空团购数据（防止停服的时候，跨天-本来这个操作在跨天的时候有处理）
	 * @author mp
	 * @date 2015-12-21 上午10:27:59
	 * @throws Exception
	 * @Description
	 */
	public static void initGroup () throws Exception {
		if (!ActivityUtil.isInActivity(StaticActivity.groupon)) {
			try {
				getInstActivityDAL().update("update Inst_Player_Group set buyBoxNum = 0, rewardState = 0, buyBoxTime = ''");
				getInstPlayerBigTableDAL().deleteByWhere(" instPlayerId = 0 and properties = '" + StaticBigTable.groupBoxNum + "'");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 不在活动期内，清空超值兑换数据
	 * @author mp
	 * @date 2015-12-21 上午11:40:34
	 * @throws Exception
	 * @Description
	 */
	public static void initExchange () throws Exception {
		if (!ActivityUtil.isInActivity(StaticActivity.normalExchange)) {
			try {
				getInstActivityExchangeDAL().deleteByWhere("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	public static void 
	/**
	 * 初始化世界boss信息
	 * @author mp
	 * @date 2015-11-26 上午11:22:27
	 * @throws Exception
	 * @Description
	 */
	public static void initWorldBossInfo () throws Exception {
		List<DictWorldBoss> worldBosseList = DictList.dictWorldBossList;
		int initWorldBossId = 0;
		DictWorldBoss dictWorldBoss = null;
		for (DictWorldBoss obj : worldBosseList) {
			if (obj.getIsFirstShow() == 1) {
				initWorldBossId = obj.getId();
				dictWorldBoss = obj;
				break;
			}
		}
		int initWorldBossBlood = DictMapUtil.getSysConfigIntValue(StaticSysConfig.minBossBlood);
		
		List<InstWorldBoss> instWorldBossList =  getInstWorldBossDAL().getList("", 0);
		
		if (instWorldBossList.size() <= 0) {
			InstWorldBoss instWorldBoss = new InstWorldBoss();
			instWorldBoss.setCurrBossId(initWorldBossId);
			instWorldBoss.setCurrBossBlood(initWorldBossBlood);
			instWorldBoss.setCurrHitBossSecs(-1);
			instWorldBoss.setCurrBossBloodPer(dictWorldBoss.getBloodPer());
			getInstWorldBossDAL().add(instWorldBoss, 0);
		}
	}
	
}
