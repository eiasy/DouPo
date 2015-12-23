package com.huayi.doupo.logic.handler.quartz;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.huayi.doupo.activity.ThreadManager;
import com.huayi.doupo.activity.luck.ActivityLuckManager;
import com.huayi.doupo.activity.run.ResetDantaRun;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictActivityLimitTimeHeroJiFenReward;
import com.huayi.doupo.base.model.DictActivityLimitTimeHeroRankReward;
import com.huayi.doupo.base.model.DictActivityMonthCardStore;
import com.huayi.doupo.base.model.DictActivityMonthCardStoreTemp;
import com.huayi.doupo.base.model.DictChapter;
import com.huayi.doupo.base.model.InstChapterActivity;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.SysStatics;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.CollectionUtil;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateType;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.base.JsonUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.util.InitUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 重置当天开放的活动副本
 * 
 * @author hzw
 * @date 2014-8-27上午11:20:05
 */
public class ChapterActivityHandler extends BaseHandler implements Job {

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {

		System.out.println("====  execute  ====");

		try {

			ParamConfig.strogherHeroNumOnes = "";//重置巅峰强者每个阶段的第一名名称
			
			// 过幼儿期初始化活动
			initActivity();
			
			// 如果当前日期和团购会开启日期相同，重置开团购箱子相关
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.groupon + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getStartTime()).equals(DateUtil.getYmdDate())) {
					getInstActivityDAL().update("update Inst_Player_Group set openGroupBoxNum = 0, giveZiList = ''");
				}
			}
			
			//从活动服拿数据
			InitUtil.loadDataFromActivityServer();

			// 给登录服发跨天日志-所有玩家
			orgAcrosDayParams();

			// 处理限时英雄积分奖励
			limitTimePeapleJifen();

			// 处理限时英雄排行奖励
			limitTimePeapleRank();

			// clear限时英雄积分缓存map,删除数据库积分数据
			updateLimitTimePeapleJifen();

			// clear最强英雄积分缓存map
			updateStrogerHeroJifen();

			// 更新统计数据
			updateStaticsData();

			// 如果当前时间超过累计充值活动的结束时间,清空累计充值领取数据(要求运营，过一天再开启)
			updateAddRechargeActivity();

			// 清除整点抢无效实例数据
			delGrabTheHour();

			// 清除特卖会无效实例数据
			delPrivateSale();

			// 如果当前时间超过限时抢购活动的结束时间,清空限时抢购的购买记录(要求运营，过一天再开启,保证跨天)
			updateLimitShopActivity();

			// 更新月卡贵族字典临时表
			updateMonthCardStore();

			// 更新/初始活动副本实例表
			chapterActivity();

			// 更新联盟相关数据（重置成员每日捐献）
			union();

			// 清理不符合条件的限时英雄积分奖励和排行奖励
			delLimitTimePeapleData();

			// 清理不符合条件的最强英雄排行奖励数据
			delStrogerHeroData();

			// 清理招财进宝活动实例数据
			delTreasuresInst();

			ActivityLuckManager.getInstance().checkReset();
			ThreadManager.execute(new ResetDantaRun());

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("凌晨执行逻辑Error", e);
		}
	}

	/**
	 * 过幼儿期初始化活动
	 * 
	 * @author mp
	 * @date 2015-8-22 上午11:18:01
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	private static void initActivity() {
		try {
			// 先判断是否已过幼儿期-最初讨论幼儿期为开服后7天[如果8.22号开服,8.23号时就按开服1天算了],过了幼儿期初始化活动
			String currYmd = DateUtil.getYmdDate();
			int openServerDayNum = DateUtil.dayDiff(DateUtil.getYmdDate(ParamConfig.openServerTime), currYmd);// 不用改,还是按开服时间算
			
			Map<String, String> activityMap = null;
			
			if (openServerDayNum >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.childDayNum)) {
				// 开启属于本日的活动
				if (ParamConfig.isUserActivity == 1) {
					String activityServerUrl = SysConfigUtil.getValue("activity.request.url");
					System.out.println("activityServerUrl=" + activityServerUrl);
					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("command", "getWeekActivity");
					String params = HttpClientUtil.httpBuildQuery(paramMap);
					String ret = HttpClientUtil.postMapSubmit(activityServerUrl, params);
					System.out.println("ret= " + ret);
					activityMap = JsonUtil.fromJson(ret, HashMap.class);
				} else {
	  				String checkVersionURL = SysConfigUtil.getValue("activity.request.url");
					URL url = new URL(checkVersionURL);
					URLConnection connection = url.openConnection();
					connection.setConnectTimeout(10000);
					connection.connect();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					InputStream is = connection.getInputStream();
					byte[] buf = new byte[512];
					int len;
					while ((len = is.read(buf)) != -1) {
						baos.write(buf, 0, len);
					}
					String content = new String(baos.toByteArray(), "UTF-8");
					System.out.println("content= " + content);
					activityMap = JsonUtil.fromJson(content, HashMap.class);
					System.out.println("activityMap= " + activityMap);
					// System.out.println("content = " + content);
				}

				// 获取本日活动
				int dayOfWeek = DateUtil.getTimeInfo(DateType.DayOfWeek);
				if (activityMap.containsKey(dayOfWeek + "")) {
					// "13_限时英雄_3;11_巅峰强者_4"
					int isRefreshActivityData = 0;
					String activityList = activityMap.get(dayOfWeek + "");
					for (String activity : activityList.split(";")) {
						int activityId = ConvertUtil.toInt(activity.split("_")[0]);
						int howLong = ConvertUtil.toInt(activity.split("_")[2]);

						// 更新Sys_Activity表
						SysActivity sysActivity = getSysActivityDAL().getModel(activityId, 0);
						if (sysActivity != null) {

							// 默认处理,只有当前时间在活动期内时不处理
							int isHandle = 1;
							if (sysActivity.getStartTime() != null && !sysActivity.getStartTime().equals("") && sysActivity.getEndTime() != null && !sysActivity.getEndTime().equals("")) {
								if (DateUtil.getMillSecond(sysActivity.getStartTime()) <= DateUtil.getCurrMill() && DateUtil.getCurrMill() <= DateUtil.getMillSecond(sysActivity.getEndTime())) {
									isHandle = 0;
								}
							}
							if (isHandle == 1) {
								sysActivity.setStartTime(currYmd + " 00:00:00");
								String endDay = DateUtil.getNumDayDate(currYmd, howLong);
								sysActivity.setEndTime(endDay + " 00:00:00");
								sysActivity.setIsView(1);
								getSysActivityDAL().update(sysActivity, 0);

								// 组织发送消息对象
								MessageData syncMsgData = new MessageData();
								OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, sysActivity, sysActivity.getId(), sysActivity.getResult(), syncMsgData);

								// 给在线的人做同步
								for (Entry<String, Player> entry : PlayerMapUtil.getMap().entrySet()) {
									String onlineChannelId = entry.getKey();
									MessageUtil.sendSyncMsg(onlineChannelId, syncMsgData);
								}
								isRefreshActivityData = 1;
							}
						}
					}

					if (isRefreshActivityData == 1) {
						// 刷新活动表字典数据字典数据
						String tableNameList = "Sys_Activity";

						if (tableNameList != null && !tableNameList.equals("")) {
							DictData.isAll = 0;
							DictData.tableNameList = tableNameList.replace("_", "");
						}
						DictData.dictData(0);

						// 刷新完以后重置初始值
						DictData.isAll = 1;
						DictData.tableNameList = "";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
			LogUtil.error("过幼儿期初始化活动Error", e);
		}
	}

	/**
	 * clear限时英雄积分缓存map,删除数据库积分数据
	 * 
	 * @author mp
	 * @date 2015-8-18 下午8:55:49
	 * @Description
	 */
	private static void updateLimitTimePeapleJifen() {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.LimitTimeHero + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getEndTime()).equals(DateUtil.getYmdDate())) {
					// clear map
					ParamConfig.recruitJifenMap.clear();
					// 更新Inst_Player_BigTable里边存储所有积分为0
					getInstPlayerBigTableDAL().update("update Inst_Player_BigTable set value1 = '0' where properties = '" + StaticBigTable.recruitJiFen + "';");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("clear限时英雄积分缓存map,删除数据库积分数据Error", e);
		}
	}

	/**
	 * clear最强英雄积分缓存map
	 * 
	 * @author mp
	 * @date 2015-8-19 下午4:56:03
	 * @Description
	 */
	private static void updateStrogerHeroJifen() {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.StrongHero + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getEndTime()).equals(DateUtil.getYmdDate())) {
					// clear map
					ParamConfig.strogerHeroJifenMap.clear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("clear限时英雄积分缓存map,删除数据库积分数据Error", e);
		}
	}

	/**
	 * 清理不符合条件的限时英雄积分奖励和排行奖励
	 * 
	 * @author mp
	 * @date 2015-8-18 下午8:38:15
	 * @Description
	 */
	private static void delLimitTimePeapleData() {
		try {
			for (Entry<Integer, ConcurrentHashMap<String, Integer>> entry : ParamConfig.recruitRankRewardMap.entrySet()) {
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

			for (Entry<Integer, ConcurrentHashMap<String, Integer>> entry : ParamConfig.recruitJifenRewardMap.entrySet()) {
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
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("删减不符合条件的限时英雄积分奖励和排行奖励Error", e);
		}

	}

	/**
	 * 清理不符合条件的最强英雄排行奖励数据
	 * 
	 * @author mp
	 * @date 2015-8-19 下午4:53:14
	 * @Description
	 */
	private static void delStrogerHeroData() {
		try {
			for (Entry<Integer, ConcurrentHashMap<String, Integer>> entry : ParamConfig.strogerRankRewardMap.entrySet()) {
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
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("清理不符合条件的最强英雄排行奖励数据Error", e);
		}
	}

	/**
	 * 处理限时英雄排行奖励
	 * 
	 * @author mp
	 * @date 2015-8-18 下午5:27:25
	 * @Description
	 */
	private static void limitTimePeapleRank() {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.LimitTimeHero + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getEndTime()).equals(DateUtil.getYmdDate())) {
					Map<Integer, Integer> map = new HashMap<Integer, Integer>();
					for (Entry<Integer, Integer> entry : ParamConfig.recruitJifenMap.entrySet()) {
						map.put(entry.getKey(), entry.getValue());
					}
					Map<Integer, Integer> sortedMap = CollectionUtil.sortByValueDown(map);
					
					LogUtil.info(DateUtil.getCurrTime() + " 限时英雄积分排行情况[playerId, jifen] = " + sortedMap);
					
					int rank = 0;
					for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
						rank++;
						int instPlayerId = entry.getKey();
						Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
						// 在线玩家
						if (player != null) {
							String things = "";
							List<DictActivityLimitTimeHeroRankReward> activityLimitTimeHeroRankRewardList = DictList.dictActivityLimitTimeHeroRankRewardList;
							for (DictActivityLimitTimeHeroRankReward obj : activityLimitTimeHeroRankRewardList) {
								if (obj.getStartRankNum() <= rank && rank <= obj.getEndRankNum()) {
									things = obj.getRewards();
									break;
								}
							}
							if (things.length() > 0) {
								try {
									MessageData otherSyncMsgData = new MessageData();
									ActivityUtil.addInstPlayerAward(instPlayerId, 3, things, DateUtil.getCurrTime(), "您在限时英雄活动中排名为" + rank + ",获得奖励：", otherSyncMsgData);
									MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else {
							// 不在线玩家,放入缓存,停服后序列化此map
							if (ParamConfig.recruitRankRewardMap.containsKey(instPlayerId)) {
								ConcurrentHashMap<String, Integer> innerMap = ParamConfig.recruitRankRewardMap.get(instPlayerId);
								innerMap.put(dictActivity.getEndTime(), rank);
							} else {
								ConcurrentHashMap<String, Integer> innerMap = new ConcurrentHashMap<String, Integer>();
								innerMap.put(dictActivity.getEndTime(), rank);
								ParamConfig.recruitRankRewardMap.put(instPlayerId, innerMap);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("处理限时英雄排行奖励Error", e);
		}
	}

	/**
	 * 处理限时英雄积分奖励
	 * 
	 * @author mp
	 * @date 2015-8-18 下午5:10:20
	 * @Description
	 */
	private static void limitTimePeapleJifen() {
		try {
			// 活动结束时,发奖
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.LimitTimeHero + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getEndTime()).equals(DateUtil.getYmdDate())) {
					
					//记录限时英雄积分情况
					LogUtil.info(DateUtil.getCurrTime() + " 限时英雄积分情况[playerId, jifen] = " + ParamConfig.recruitJifenMap);
					
					for (Entry<Integer, Integer> entry : ParamConfig.recruitJifenMap.entrySet()) {
						int instPlayerId = entry.getKey();
						int jifen = entry.getValue();

						Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
						// 在线玩家
						if (player != null) {
							String things = "";
							List<DictActivityLimitTimeHeroJiFenReward> activityLimitTimeHeroJiFenRewardList = DictList.dictActivityLimitTimeHeroJiFenRewardList;
							for (DictActivityLimitTimeHeroJiFenReward obj : activityLimitTimeHeroJiFenRewardList) {
								if (jifen >= obj.getSaveJifenNum()) {
									things += obj.getRewards() + ";";
								}
							}
							try {
								if (things.length() > 0) {
									MessageData otherSyncMsgData = new MessageData();
									ActivityUtil.addInstPlayerAward(instPlayerId, 3, StringUtil.noContainLastString(things), DateUtil.getCurrTime(), "您在限时英雄活动中积分为 " + jifen + ",获得奖励：", otherSyncMsgData);
									MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							// 不在线玩家,放入缓存,停服后序列化此map
							if (ParamConfig.recruitJifenRewardMap.containsKey(instPlayerId)) {
								ConcurrentHashMap<String, Integer> innerMap = ParamConfig.recruitJifenRewardMap.get(instPlayerId);
								innerMap.put(dictActivity.getEndTime(), jifen);
							} else {
								ConcurrentHashMap<String, Integer> innerMap = new ConcurrentHashMap<String, Integer>();
								innerMap.put(dictActivity.getEndTime(), jifen);
								ParamConfig.recruitJifenRewardMap.put(instPlayerId, innerMap);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("处理限时英雄积分奖励Error", e);
		}
	}

	/**
	 * 给登录服发跨天日志-所有玩家
	 * 
	 * @author mp
	 * @date 2015-8-7 下午4:27:15
	 * @Description
	 */
	private static void orgAcrosDayParams() {
		try {
			for (Entry<String, Player> map : PlayerMapUtil.getMap().entrySet()) {
				Player player = map.getValue();
				if (player != null) {
					Map<String, String> paramMap = new HashMap<>();
					paramMap.put("account_id", player.getAccountId());// 登录服发过来的账号id
					paramMap.put("channel_id", player.getChannel());// 渠道标识（拼音） channel_mark
					paramMap.put("channel_sub", player.getChannelSub());// 登录时发过来的 channel_sub
					paramMap.put("product_id", player.getAloneServerId());// 独服Id
					paramMap.put("login_server", "GAME_SERVER");// "GAME_SERVER"
					paramMap.put("server_version", "1.1.1.1");// 1.1.1.1
					paramMap.put("real_ip", player.getIp());// 客户端ip
					paramMap.put("permit", player.getOpenId());// openId 带@渠道拼音
					paramMap.put("user_id", player.getOpenId().substring(0, player.getOpenId().lastIndexOf("@")));// openId 不带渠道拼音
					paramMap.put("imei", player.getImei());// IMEI
					paramMap.put("device_os", player.getDevOs());// OS
					String params = HttpClientUtil.httpBuildQuery(paramMap);
					// System.out.println("===========" + params);
					ParamConfig.acrossDayQueue.add(params);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("给登录服发跨天日志Error", e);
		}
	}

	/**
	 * 更新联盟相关数据（重置成员每日捐献）
	 * 
	 * @author hzw
	 * @date 2015-7-22下午4:11:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void union() {
		try {
			getInstUnionDAL().update("update Inst_Union set plan = 0, constructionNum = 0, version = version + 1 where plan > 0 or constructionNum > 0");
			getInstUnionMemberDAL().update("update Inst_Union_Member set currentOffer = 0, unionBuildId = 0, version = version + 1 where currentOffer > 0 or unionBuildId > 0");
			getInstUnionMemberDAL().update("update Inst_Union_Box set unionBoxs = \"\", version = version + 1 where unionBoxs != \"\" or unionBoxs is not null");
			getInstUnionMemberDAL().update("update Inst_Union_Store set unionStoreOnes = \"\", version = version + 1 where unionStoreOnes != \"\" or unionStoreOnes is not null;");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("更新联盟相关数据（重置成员每日捐献）Error", e);
		}
	}

	/**
	 * 更新/初始活动副本实例表
	 * 
	 * @author hzw
	 * @date 2015-7-8下午4:39:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void chapterActivity() {
		List<DictChapter> dictChapters = (List<DictChapter>) DictMapList.dictChapterMap.get(3);
		try {
			getInstChapterActivityDAL().deleteByWhere("");
			for (DictChapter dictChapter : dictChapters) {
				if (dictChapter.getOpenTime().contains(DateUtil.getTimeInfo(DateType.DayOfWeek) + "")) {
					InstChapterActivity instChapterActivity = new InstChapterActivity();
					instChapterActivity.setChapterId(dictChapter.getId());
					getInstChapterActivityDAL().add(instChapterActivity, 0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新月卡贵族字典临时表
	 * 
	 * @author hzw
	 * @date 2015-6-11下午3:24:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	private void updateMonthCardStore() {
		DictActivityMonthCardStoreTemp objTemp = DictList.dictActivityMonthCardStoreTempList.get(0);
		List<DictActivityMonthCardStore> objList = (List<DictActivityMonthCardStore>) DictMapList.dictActivityMonthCardStoreMap.get(objTemp.getType());
		if (objList.size() <= 0) {
			objList = (List<DictActivityMonthCardStore>) DictMapList.dictActivityMonthCardStoreMap.get(1);
		}
		List<DictActivityMonthCardStoreTemp> objTempList = new ArrayList<>();
		for (DictActivityMonthCardStore obj : objList) {
			objTemp.setId(obj.getId());
			objTemp.setType(obj.getBuyType());
			objTemp.setThings(obj.getThings());
			objTemp.setBuyCount(obj.getBuyCount());
			objTemp.setBuyType(obj.getBuyType());
			objTemp.setBuyValue(obj.getBuyValue());
			objTemp.setOriginalValue(obj.getOriginalValue());
			objTemp.setVip(obj.getVip());
			objTemp.setDescription(obj.getDescription());
			objTempList.add(objTemp);
		}
		try {
			getDictActivityMonthCardStoreTempDAL().deleteByWhere(""); // 批量删除之前的旧数据
			getDictActivityMonthCardStoreTempDAL().batchAdd(objTempList); // 批量添加新数据
			DictList.dictActivityMonthCardStoreTempList = objTempList; // 同时更新内存数据
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清除特卖会无效实例数据（由于特卖会每天都会重新卖新一天的物品，所以在零点清除所有的实例数据没有问题）不需要同步
	 * 
	 * @author hzw
	 * @date 2015-6-5上午10:53:06
	 * @Description
	 */
	private void delPrivateSale() {
		try {
			getInstPlayerGrabTheHourDAL().deleteByWhere("insertTime < '" + DateUtil.getYmdDate() + " " + DictMapUtil.getSysConfigIntValue(StaticSysConfig.privateSaleTimeTwo) + ":00:00'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清除整点抢无效实例数据（由于整点抢每天都会重新卖新一天的物品，所以在零点清除所有的实例数据没有问题）不需要同步
	 * 
	 * @author hzw
	 * @date 2015-6-5上午10:44:22
	 * @Description
	 */
	private void delGrabTheHour() {
		try {
			getInstPlayerGrabTheHourDAL().deleteByWhere("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新累计充值活动数据
	 * 
	 * @author mp
	 * @date 2015-5-12 上午12:05:16
	 * @Description
	 */
	private void updateAddRechargeActivity() {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.addRecharge + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getCurrMill() > DateUtil.getMillSecond(dictActivity.getEndTime())) {
					int count = getInstActivityDAL().getCount(" activityId = " + StaticActivity.addRecharge + " and activityTime != '' ");
					if (count > 0) {
						getInstActivityDAL().update("update Inst_Activity set activityTime = '' , version = version + 1 where activityId = " + StaticActivity.addRecharge + " and activityTime != '';");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新限时抢购活动数据
	 * 
	 * @author mp
	 * @date 2015-6-4 下午5:36:22
	 * @Description
	 */
	private void updateLimitShopActivity() {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.limitShopping + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getCurrMill() > DateUtil.getMillSecond(dictActivity.getEndTime())) {
					int count = getInstActivityDAL().getCount(" activityId = " + StaticActivity.limitShopping + " and spareOne != '' ");
					if (count > 0) {
						getInstActivityDAL().update("update Inst_Activity set spareOne = '' where activityId = " + StaticActivity.limitShopping + " and spareOne != '';");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新统计数据
	 * 
	 * @author mp
	 * @date 2014-9-12 下午4:33:50
	 * @throws Exception
	 * @Description
	 */
	private void updateStaticsData() {
		try {
			// 添加记录
			SysStatics obj = new SysStatics();
			obj.setMaxOnlineNum(ParamConfig.maxOnlineNum);
			obj.setMaxOnlineTime(ParamConfig.maxOnlineTime);
			obj.setMinOnlineNum(ParamConfig.minOnlineNum);
			obj.setMinOnlineTime(ParamConfig.minOnlineTime);
			obj.setMaxHitBossNum(ParamConfig.maxWorldBossNum);
			obj.setSettleTime(DateUtil.getCurrTime());

			// 计算留存
			String staticsDate = DateUtil.getNumDayDate(-1);// 统计[当天]日期
			List<InstUser> staticsDateUserList = getInstUserDAL().getList("lastLoginDate = '" + staticsDate + "'", 0);// 当天所有登录用户[只查一次库]

			// 次日留存 [当天登录中，是前1天注册的用户数 / 前1天总注册用户数]
			obj.setTwoDayBackPer(getReLoginAndSignNum(staticsDate, staticsDateUserList, -1)[1] == 0 ? 0 : (getReLoginAndSignNum(staticsDate, staticsDateUserList, -1)[0] / getReLoginAndSignNum(staticsDate, staticsDateUserList, -1)[1]));

			// 三日留存 [当天登录中，是前2天注册的用户数 / 前2天总注册用户数]
			obj.setThreeDayBackPer(getReLoginAndSignNum(staticsDate, staticsDateUserList, -2)[1] == 0 ? 0 : (getReLoginAndSignNum(staticsDate, staticsDateUserList, -2)[0] / getReLoginAndSignNum(staticsDate, staticsDateUserList, -2)[1]));

			// 七日留存 [当天登录中，是前6天注册的用户数 / 前6天总注册用户数]
			obj.setSevenDayBackPer(getReLoginAndSignNum(staticsDate, staticsDateUserList, -6)[1] == 0 ? 0 : (getReLoginAndSignNum(staticsDate, staticsDateUserList, -6)[0] / getReLoginAndSignNum(staticsDate, staticsDateUserList, -6)[1]));

			// 月留存 [当天登录中，是前29天注册的用户数 / 前29天总注册用户数]
			obj.setThirtyDayBackPer(getReLoginAndSignNum(staticsDate, staticsDateUserList, -29)[1] == 0 ? 0 : (getReLoginAndSignNum(staticsDate, staticsDateUserList, -29)[0] / getReLoginAndSignNum(staticsDate, staticsDateUserList, -29)[1]));

			getSysStaticsDAL().add(obj, 0);

			// 重置初始数据
			ParamConfig.maxOnlineNum = 0;
			ParamConfig.maxOnlineTime = "";
			ParamConfig.minOnlineNum = 1000;
			ParamConfig.minOnlineTime = "";
			ParamConfig.maxWorldBossNum = 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清除招财进宝实例表数据
	 */
	private static void delTreasuresInst() {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.MakeMoney + "");
			if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
				if (DateUtil.getYmdDate(dictActivity.getEndTime()).equals(DateUtil.getYmdDate())) {
					getInstActivityTreasuresDAL().deleteByWhere("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据提前的天数,获得当天登录中是前num天注册的用户数和前num天的总注册用户数
	 * 
	 * @author mp
	 * @date 2015-3-19 下午5:49:31
	 * @param staticsDate
	 * @param staticsDateUserList
	 * @param numDayBefore
	 * @return
	 * @throws Exception
	 * @Description
	 */
	private static float[] getReLoginAndSignNum(String staticsDate, List<InstUser> staticsDateUserList, int num) throws Exception {
		String numDay = DateUtil.getNumDayDate(staticsDate, num);
		float numDayLoginUserNum = 0;// 当天登录中，是前num天注册的用户数
		// System.out.println("numDay = "+numDay);

		for (InstUser instUser : staticsDateUserList) {
			// System.out.println("instUser=" + instUser);
			if (instUser.getFirstLoginDate().equals(numDay)) {
				numDayLoginUserNum++;
			}
		}
		float numDaySignUserNum = getInstUserDAL().getList("firstLoginDate = '" + numDay + "'", 0).size();// 前num天总注册用户数
		return new float[] { numDayLoginUserNum, numDaySignUserNum };
	}

	public static void main(String[] args) throws Exception {
		System.out.println(DateUtil.getNumDayDate("2015-04-18", -29));
	}

}
