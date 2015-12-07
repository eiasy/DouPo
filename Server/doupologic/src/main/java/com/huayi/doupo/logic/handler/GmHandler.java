package com.huayi.doupo.logic.handler;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Maps;
import com.huayi.doupo.activity.luck.ActivityLuckManager;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictChapterActivityVip;
import com.huayi.doupo.base.model.DictRecharge;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerRecharge;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.SysGmReward;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.model.player.PlayerMemObjMapUtil;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticActivityHoliday;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticMsgRule;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.JsonUtil;
import com.huayi.doupo.base.util.base.SerializeUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.GmUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.ChannelMapUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerFightValueMapUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * Gm处理类
 * @author mp
 * @date 2014-10-10 下午3:07:57
 */
public class GmHandler  extends BaseHandler{

	public void highPlayer(HashMap<String, Object> msgMap, String channelId)throws Exception{
		
		long time = DateUtil.getCurrMill();
		List<DictChapterActivityVip> tempList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			DictChapterActivityVip obj = new DictChapterActivityVip();
			obj.setBuyGold(100);
			obj.setBuyGoldAdd(10);
			obj.setDescription("aaaa");
			obj.setVip1(1);
			obj.setVip10(10);
			obj.setVip11(11);
			obj.setVip13(13);
			tempList.add(obj);
		}
		getDictChapterActivityVipDAL().batchAdd(tempList);
		long usedTime = DateUtil.getCurrMill() - time;
		
		MessageUtil.sendFailMsg(channelId, msgMap, usedTime + "");
		
		/*
		
		long start = System.currentTimeMillis();
		
		String type = (String)msgMap.get("type");
		Integer count = (Integer)msgMap.get("count");
		
		//加载资源
		SpringUtil.getSpringContext();
		DictData.dictData(0);
		RandomNameUtil.initRandomNameUtil();
		
		//创建用户账号
		for (int i = 1; i <= count; i++) {
			
			String openId = type + i;
			
			UserUtil.initUser(openId, "gj");
			
			//创建玩家
			InstPlayer instPlayer = PlayerUtil.initHighPlayer(openId);
			int instPlayerId = instPlayer.getId();
			
			//创建阵容
			PlayerUtil.initHighPlayerLineup(instPlayerId);
			
			//创建物品
			*//**
			 * 
			    3种境界突破材料	Dict_Thing	4-6	每种999个
			           菩提子Dict_Thing 	2	99999个
			           卡牌颜色3种颜色进阶材料	Dict_Thing	83-85	每种999个
				打孔器	Dict_Thing	1	999
				洗练石	Dict_Thing	8	9999
				洗练锁	Dict_Thing	3	9999
				耐力丹	Dict_Thing	79	999个
				体力丹	Dict_Thing	80	999个
				魂源	Dict_Thing	12	99999
			 *//*
			//格式为 thingId_num;
			String thingList = "4_999;5_999;6_999;2_99999;83_999;84_999;85_999;1_999;8_9999;3_9999;79_999;80_999;12_99999";
			for (String thing : thingList.split(";")) {
				int thingId = ConvertUtil.toInt(thing.split("_")[0]);
				int num = ConvertUtil.toInt(thing.split("_")[1]);
				ThingUtil.groupThing(instPlayer, StaticTableType.DictThing, thingId, num, new MessageData());
			}
			
			
			//丹药材料  tableFieldId=Dict_PillThing.id
			for (int tableFieldId = 1; tableFieldId <= 60; tableFieldId++) {
				int value = 9999;
				PillUtil.addInstPlayerPillThing(instPlayerId, tableFieldId, value, new MessageData());
			}
			
			
			//装备
			String equipmentList = "1;1;45;45;65;65;88;88;112;112;127;127";
			for (String equipmentId : equipmentList.split(";")) {
				EquipmentUtil.addEquipment(instPlayerId, ConvertUtil.toInt(equipmentId));
			}
			
			//功法
//			for (int kungFuId = 1; kungFuId <= 10; kungFuId++) {
//				KungFuUtil.addInstPlayerKungFu(instPlayerId, kungFuId, 0);
//			}
			
			//威望
			InstPlayerArena instPlayerArena = new InstPlayerArena();
			instPlayerArena.setInstPlayerId(instPlayerId);
			instPlayerArena.setRank(getInstPlayerArenaDAL().getList("", 0).size() + 1);
			instPlayerArena.setUpRank(0);
			instPlayerArena.setArenaNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNum));
			instPlayerArena.setArenaTime("");
			instPlayerArena.setPrestige(999999);
			instPlayerArena.setAwardRank(0);
			instPlayerArena.setOperTime(DateUtil.getCurrTime());
			getInstPlayerArenaDAL().add(instPlayerArena, instPlayerId);
			
			//普通副本(简单全部通过)
			for (int barrierLevelId = 1; barrierLevelId <= 298; barrierLevelId++) {
				DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
				if(dictBarrierLevel.getLevel() == 1){
					ChapterUtil.initInstPlayerBarrierLevel(instPlayerId, barrierLevelId, 1, DateUtil.getCurrTime(), new MessageData());
				}
			}
			
			//精英副本(全部通过)
			for (int barrierLevelId = 302; barrierLevelId <= 306; barrierLevelId++) {
				DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
				DictBarrier dictBarrier = DictMap.dictBarrierMap.get(dictBarrierLevel.getBarrierId() + "");
				ChapterUtil.initInstPlayerChapterType(instPlayerId, 2, dictBarrier.getChapterId(), 1, DateUtil.getCurrTime(), new MessageData());
			}
		}
		
		System.out.println("use time " + (System.currentTimeMillis() - start));
		
		GmUtil.sendSuccMsg(channelId, msgMap);
	*/}
	
	/**
	 * 获取在线人数
	 * @author mp
	 * @date 2014-10-10 下午3:16:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void onlineNum(HashMap<String, Object> msgMap, String channelId)throws Exception{
		
		//组织结果
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("r", "s");
		
		//获取在线人数
		int onlineNum = PlayerMapUtil.getSize();
		retMsgData.putStringItem("当前在线人数", onlineNum+"");

		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 关服
	 * @author mp
	 * @date 2014-10-10 下午3:32:02
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void closeServer (HashMap<String, Object> msgMap, String channelId) throws Exception{
		try {
			String closeServerMsg = (String)msgMap.get("closeServerMsg");
			
			MessageData retMsgData = new MessageData();
			retMsgData.putStringItem("1", closeServerMsg);
			
			//修改关服状态-暂时无用,因为是真关服
			ParamConfig.closeServer = true;
			
			//序列化玩家战力保存到本地txt
			PlayerFightValueMapUtil.serializeFightValue();
			
			//序列化限时英雄积分奖励
			GmUtil.seriLimitTimePeapleJifen();
			
			//序列化限时英雄排行奖励
			GmUtil.seriLimitTimePeapleRank();
			
			//序列化最强英雄排行奖励
			GmUtil.seriStrogerHeroRank();
			
			//序列化竞技场发奖
			GmUtil.seriAreaReward();
			
			//序列化世界boss排行
			GmUtil.seriWorldBossRank();
			
			//为所有在线玩家发送关服通知
			for(Entry<String, Player> onlinePlayer : PlayerMapUtil.getMap().entrySet()){
				String pushChannelId = onlinePlayer.getKey();
				MessageUtil.pushMsg(pushChannelId, StaticMsgRule.pushCloseServerData, retMsgData);
			}
			
			//关闭所有客户端[是不含gm的]，触发Channel关闭方法
			for(Entry<String, Player> onlinePlayer : PlayerMapUtil.getMap().entrySet()){
				String pushChannelId = onlinePlayer.getKey();
				Channel channel = ChannelMapUtil.getByChannelId(pushChannelId);
				channel.close();
			}
			TimeUnit.SECONDS.sleep(2);
			
			GmUtil.sendSuccMsg(channelId, msgMap);
		} catch (Exception e) {
			throw e;
		}
		
		//关掉进程
		System.exit(0);
	}
	
	/**
	 * 使用缓存
	 * @author mp
	 * @date 2014-10-13 上午9:34:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void cacheControl (HashMap<String, Object> msgMap, String channelId) throws Exception{
		
		//获取参数 0-不使用缓存  1-使用缓存
		String type = (String)msgMap.get("type");
		
		//设置成使用缓存
		if (type.equals("0")) {
			ParamConfig.isUseCache = false;
			PlayerMemObjMapUtil.clearAll();
		} else if (type.equals("1")) {
			ParamConfig.isUseCache = true;
		}
		
		//返回结果
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 刷新字典数据
	 * @author mp
	 * @date 2014-11-5 下午2:54:59
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void refreshDictData (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		String tableNameList = (String)msgMap.get("tableNameList");
		
		if (tableNameList != null && !tableNameList.equals("")) {
			DictData.isAll = 0;
			DictData.tableNameList = tableNameList.replace("_", "");
		}
		DictData.dictData(0);
		
		//刷新完以后重置初始值
		DictData.isAll = 1;
		DictData.tableNameList = "";
		
		//返回结果
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 派发奖励
	 * @author mp
	 * @date 2014-11-13 下午2:10:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void sendReward (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//参数列表
		String playerNameList = (String)msgMap.get("playerName");//玩家名字, ""表示全服发放,
		String content = (String)msgMap.get("content");//内容
		String thingList = (String)msgMap.get("thingList");//奖励的物品列表, 格式： tableTypeId_tableField_value;

		char lastChar = thingList.charAt(thingList.length() - 1);
		if (lastChar == ';') {
			thingList = thingList.substring(0, thingList.length() - 1);
		}
		
		String result = "";
		
		//""表示全服发放
		String currTime = DateUtil.getCurrTime();
		if (playerNameList.equals("")) {
			
			//插之前删除3天前的数据,防止数据不断增加,玩家领奖也只能领近三天的全服奖励
			int receiveDay = DictMapUtil.getSysConfigIntValue(StaticSysConfig.receiveDay);
			String receiveDayYhs = DateUtil.getNumDayDate(receiveDay * -1);
			List<SysGmReward> sysGmRewardList = getSysGmRewardDAL().getList("rewardTime <= '"+receiveDayYhs+"'", 0);
			for (SysGmReward obj : sysGmRewardList) {
				getSysGmRewardDAL().deleteById(obj.getId(), 0);
			}
			
			//插入GM全服发奖系统表
			SysGmReward sysGmReward = new SysGmReward();
			sysGmReward.setContent(content);
			
			sysGmReward.setParamList(thingList);
			sysGmReward.setRewardTime(currTime);
			getSysGmRewardDAL().add(sysGmReward, 0);
			
			//给当前在线玩家发奖
			for (Entry<String, Player> entry : PlayerMapUtil.getMap().entrySet()) {
				String onlineChannelId = entry.getKey();
				Player onlinePlayer = entry.getValue();
				int onlinePlayerId = onlinePlayer.getPlayerId();
				
				//同步消息
				MessageData otherSyncMsgData = new MessageData();
				ActivityUtil.addInstPlayerAward(onlinePlayerId, 3, thingList, currTime, content, otherSyncMsgData);
				MessageUtil.sendSyncMsg(onlineChannelId, otherSyncMsgData);
			}
		} else {
			//给具体玩家发放
			for (String playerName : playerNameList.split(";")) {
				List<InstPlayer> instPlayerList = getInstPlayerDAL().getList(" name = '" + playerName + "'", 0);
				if (instPlayerList.size() > 0) {
					InstPlayer instPlayer = instPlayerList.get(0);
					
					//同步消息
					MessageData otherSyncMsgData = new MessageData();
					ActivityUtil.addInstPlayerAward(instPlayer.getId(), 3, thingList, currTime, content, otherSyncMsgData);
					MessageUtil.sendSyncMsgToOne(instPlayer.getOpenId(), otherSyncMsgData);
				} else {
					result += playerName + ";";
				}
			}
		}
		
		//刷新SysGmReward字典表
		String tableNameList = "Sys_GmReward";
		
		if (tableNameList != null && !tableNameList.equals("")) {
			DictData.isAll = 0;
			DictData.tableNameList = tableNameList.replace("_", "");
		}
		DictData.dictData(0);
		
		//刷新完以后重置初始值
		DictData.isAll = 1;
		DictData.tableNameList = "";
		
		//返回结果
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("r", "s");
		
		//列表
		if (!result.equals("")) {
			result = "无此玩家：" + result;
		} else {
			result = "发放成功";
		}
		retMsgData.putStringItem("派发奖励结果", result);
		
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
		
//		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 获取自定义跑马灯
	 * @author mp
	 * @date 2014-12-10 下午3:59:05
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void getUserMarquee (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//组织结果
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("r", "s");
		
		//列表
		retMsgData.putStringItem("跑马灯列表", JsonUtil.toJson(getSysMarqueeDAL().getList("", 0)));
		
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 添加自定义跑马灯
	 * @author mp
	 * @date 2014-12-10 下午3:59:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addUserMarquee (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		String startTime = (String)msgMap.get("startTime");
		String endTime = (String)msgMap.get("endTime");
		Integer inteval = (int)msgMap.get("inteval");
		String content = (String)msgMap.get("content");
		
		//验证[开始时间不能大于结束时间 , 当前时间不能大于结束时间,  inteval不能小于等于 0]
		if (DateUtil.getMillSecond(startTime) > DateUtil.getMillSecond(endTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		if (inteval < 30) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		if (DateUtil.getCurrMill() > DateUtil.getMillSecond(endTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		//添加跑马灯线程
		MarqueeUtil.addUserMarquee(content, endTime, inteval, startTime);
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 更新自定义跑马灯
	 * @author mp
	 * @date 2014-12-10 下午3:59:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void updateUserMarquee (HashMap<String, Object> msgMap, String channelId) throws Exception {
		Integer id = (int)msgMap.get("id");
		String startTime = (String)msgMap.get("startTime");
		String endTime = (String)msgMap.get("endTime");
		Integer inteval = (int)msgMap.get("inteval");
		String content = (String)msgMap.get("content");
		
		//验证[开始时间不能大于结束时间 , 当前时间不能大于结束时间,  inteval不能小于等于 0]
		if (startTime.equals("") || endTime.equals("")) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		if (DateUtil.getMillSecond(startTime) > DateUtil.getMillSecond(endTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		if (inteval < 30) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		if (DateUtil.getCurrMill() > DateUtil.getMillSecond(endTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		//修改指定自定义跑马灯
		MarqueeUtil.updateUserMarquee(id, content, endTime, inteval, startTime);
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 删除自定义跑马灯
	 * @author mp
	 * @date 2014-12-10 下午3:59:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void delUserMarquee (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		Integer id = (int)msgMap.get("id");
		
		//删除指定跑马灯
		MarqueeUtil.delUserMarquee(id);
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 获取活动
	 * @author mp
	 * @date 2014-12-12 下午1:50:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void getActivity (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//组织结果
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("r", "s");
		
		//列表
		retMsgData.putStringItem("活动列表", JsonUtil.toJson(getSysActivityDAL().getList("", 0)));
		
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 更新活动
	 * @author mp
	 * @date 2014-12-12 上午11:29:17
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void updateActivity (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		Integer id = (int)msgMap.get("id");
		String name = (String)msgMap.get("name");
		int smallUiId = (int)msgMap.get("smallUiId");
		String startTime = (String)msgMap.get("startTime");
		String endTime = (String)msgMap.get("endTime");
		int isNew = (int)msgMap.get("isNew");
		int isForevery = (int)msgMap.get("isForevery");
		int isHotKey = (int)msgMap.get("isHotKey");
		int isView = (int)msgMap.get("isView");
		int rank = (int)msgMap.get("rank");
		
		if (startTime != null && !startTime.equals("")) {
			if (endTime == null || endTime.equals("")) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
				return;
			}
		}
		
		if (endTime != null && !endTime.equals("")) {
			if (startTime == null || startTime.equals("")) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
				return;
			}
		}
		
		//验证 1.如果isForevery=1,startTime和endTime必须为空, 如果startTime和endTime不为空,isForevery必须等于0
//		if (isForevery == 1) {
//			if (startTime != null && !startTime.equals("")) {
//				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
//				return;
//			}
//			if (endTime != null && !endTime.equals("")) {
//				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
//				return;
//			}
//		}
//		if (isForevery == 0) {
//			if (startTime == null || startTime.equals("")) {
//				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
//				return;
//			}
//			if (endTime == null || endTime.equals("")) {
//				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
//				return;
//			}
//		}
		
		//更新数据库
		SysActivity dictActivity = getSysActivityDAL().getModel(id, 0);
		dictActivity.setName(name);
		dictActivity.setSmallUiId(smallUiId);
		dictActivity.setStartTime(startTime);
		dictActivity.setEndTime(endTime);
		dictActivity.setIsNew(isNew);
//		dictActivity.setIsForevery(isForevery);
		dictActivity.setIsView(isView);
		dictActivity.setRank(rank);
		dictActivity.setIsHotKey(isHotKey);
		getSysActivityDAL().update(dictActivity, 0);
		
		//组织发送消息对象
		MessageData syncMsgData = new MessageData();
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, dictActivity, dictActivity.getId(), dictActivity.getResult(), syncMsgData);
		
		//给在线的人做同步
		for (Entry<String, Player> entry : PlayerMapUtil.getMap().entrySet()) {
			String onlineChannelId = entry.getKey();
			MessageUtil.sendSyncMsg(onlineChannelId, syncMsgData);
		}
		
		//刷新活动表字典数据字典数据
		String tableNameList = "Sys_Activity";
		
		if (tableNameList != null && !tableNameList.equals("")) {
			DictData.isAll = 0;
			DictData.tableNameList = tableNameList.replace("_", "");
		}
		DictData.dictData(0);
		
		//刷新完以后重置初始值
		DictData.isAll = 1;
		DictData.tableNameList = "";
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 查看玩家内存map
	 * @author mp
	 * @date 2015-2-5 下午4:02:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lookPlayerMap (HashMap<String, Object> msgMap, String channelId) throws Exception {
		String name = (String)msgMap.get("name");
		Player player = PlayerMapUtil.getPlayerByPlayerName(name);
		if (player == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "player is null ");
			return;
		}
		
		int instPlayerId = player.getPlayerId();
		PlayerMemObj playerMemObj = PlayerMemObjMapUtil.getPlayerMemObjByPlayerId(instPlayerId);
		if (playerMemObj == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "playerMemObj is null ");
			return;
		}
		
		String json = JsonUtil.toJson(playerMemObj);
		
		//组织结果
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("retMsg", json);
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 查看玩家缓存大小
	 * @author mp
	 * @date 2015-7-7 上午10:11:26
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lookPlayerMapSize (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData retMsgData = new MessageData();
		ConcurrentHashMap<Integer, PlayerMemObj> playerMemObjMap = PlayerMemObjMapUtil.getMap();
		if (playerMemObjMap != null) {
			
			byte[] mapByte = SerializeUtil.serialize(playerMemObjMap);
			
			//组织结果
			retMsgData.putStringItem("retMsg", mapByte.length + "");
		}
		
		
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * Boss初始血量
	 * @author hzw
	 * @date 2015-2-11上午9:47:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void initWorldBossBlood (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int value = (int)msgMap.get("value");
		ParamConfig.initWorldBossBlood = value;
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 多服更新字典数据
	 * @author mp
	 * @date 2015-4-3 下午4:15:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void updateDict (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		String updateSql = (String)msgMap.get("updateSql");
		JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.GetObjectWithSpringContext("JdbcTemplate");
		jdbcTemplate.batchUpdate(updateSql.toString().split("#"));
		
		//刷新字典数据
		StringBuilder tablesBuilder = new StringBuilder();
		for (String sql : updateSql.toString().split("#")) {
			if (sql.contains("truncate table") && (sql.contains("Dict") || sql.contains("Sys"))) {
				tablesBuilder.append(sql.replace("truncate table", "").replace("`", "").trim());
			}
		}
		String tableNameList = tablesBuilder.toString();
		if (tableNameList != null && !tableNameList.equals("")) {
			DictData.isAll = 0;
			DictData.tableNameList = tableNameList.replace("_", "");
			DictData.dictData(0);
			
			//刷新完以后重置初始值
			DictData.isAll = 1;
			DictData.tableNameList = "";
		}
		
		//只包含这一个String值
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("多服更新成功", 0+"");
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 冻结账号
	 * @author mp
	 * @date 2015-4-9 下午4:50:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void frozen (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		String playerNameList = (String)msgMap.get("playerNameList");//不可为空,多玩家用;分开
		
		for (String playerName : playerNameList.split(";")) {
			List<InstPlayer> instPlayerList = getInstPlayerDAL().getList(" name = '" + playerName + "'", 0);
			if (instPlayerList.size() > 0) {
				try {
					InstPlayer instPlayer = instPlayerList.get(0);
					String openId = instPlayer.getOpenId();
					List<InstUser> userList = getInstUserDAL().getList(" openId = '" + openId + "'", 0);
					if (userList.size() > 0) {
						//更新数据库
						InstUser instUser = userList.get(0);
						instUser.setOnlineState(2);
						instUser.setFrozenTime(DateUtil.getCurrTime());
						getInstUserDAL().update(instUser, 0);
						
						//更新玩家内存对象
						Player player = PlayerMapUtil.getPlayerByOpenId(openId);
						if (player != null) {
							player.setOnlineState(2);
						}
					}
				} catch (Exception e) {
					LogUtil.info("冻结账号异常：玩家名字=" + playerName);
					LogUtil.error("冻结账号异常", e);
					e.printStackTrace();
				}
			}
		}
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 禁言
	 * @author mp
	 * @date 2015-8-6 下午8:05:12
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void shutup (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		String playerNameList = (String)msgMap.get("playerNameList");//不可为空,多玩家用;分开
		
		for (String playerName : playerNameList.split(";")) {
			List<InstPlayer> instPlayerList = getInstPlayerDAL().getList(" name = '" + playerName + "'", 0);
			if (instPlayerList.size() > 0) {
				InstPlayer instPlayer = instPlayerList.get(0);
				String openId = instPlayer.getOpenId();
				List<InstUser> userList = getInstUserDAL().getList(" openId = '" + openId + "'", 0);
				if (userList.size() > 0) {
					//更新数据库
					InstUser instUser = userList.get(0);
					instUser.setOnlineState(3);
					instUser.setFrozenTime(DateUtil.getCurrTime());
					getInstUserDAL().update(instUser, 0);
					
					//更新玩家内存对象
					Player player = PlayerMapUtil.getPlayerByOpenId(openId);
					if (player != null) {
						player.setOnlineState(3);
					}
				}
			}
		}
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 获取冻结账号列表
	 * @author mp
	 * @date 2015-4-10 上午11:18:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void frozenList (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData retMsgData = new MessageData();
		List<FrozenUser> retList = new ArrayList<FrozenUser>();
		
		List<InstUser> instUserList = getInstUserDAL().getList("onlineState = 2", 0);
		for (InstUser instUser : instUserList) {
			int id = instUser.getId();
			InstPlayer instPlayer = getInstPlayerDAL().getList(" openId = '" + instUser.getOpenId() + "'", 0).get(0);
			String name = instPlayer.getName();
			String frozenTime = instUser.getFrozenTime();
			
			retList.add(new FrozenUser(id, name, frozenTime));
		}
		
		//组织列表
		retMsgData.putStringItem("冻结账号列表", JsonUtil.toJson(retList));
		
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 获取禁言列表
	 * @author mp
	 * @date 2015-8-6 下午8:09:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void shutupList (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData retMsgData = new MessageData();
		List<ShutUpUser> retList = new ArrayList<ShutUpUser>();
		
		List<InstUser> instUserList = getInstUserDAL().getList("onlineState = 3", 0);
		for (InstUser instUser : instUserList) {
			int id = instUser.getId();
			InstPlayer instPlayer = getInstPlayerDAL().getList(" openId = '" + instUser.getOpenId() + "'", 0).get(0);
			String name = instPlayer.getName();
			String frozenTime = instUser.getFrozenTime();
			
			retList.add(new ShutUpUser(id, name, frozenTime));
		}
		
		//组织列表
		retMsgData.putStringItem("禁言账号列表", JsonUtil.toJson(retList));
		
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 解除冻结
	 * @author mp
	 * @date 2015-4-10 下午12:16:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void frozenFree (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		Integer id = (int)msgMap.get("id");
		
		InstUser instUser = getInstUserDAL().getModel(id, 0);
		
		Player player = PlayerMapUtil.getPlayerByOpenId(instUser.getOpenId());
		
		int onlineState = 0; //是否在线 0-离线, 1-在线, 2-冻结
		
		if (player != null) {
			onlineState = 1;
			player.setOnlineState(onlineState);
		}
		
		instUser.setOnlineState(onlineState);
		getInstUserDAL().update(instUser, 0);
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 解除禁言
	 * @author mp
	 * @date 2015-8-6 下午8:14:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void shutupFree (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		Integer id = (int)msgMap.get("id");
		
		InstUser instUser = getInstUserDAL().getModel(id, 0);
		
		Player player = PlayerMapUtil.getPlayerByOpenId(instUser.getOpenId());
		
		int onlineState = 0; //是否在线 0-离线, 1-在线, 2-冻结 3-禁言
		
		if (player != null) {
			onlineState = 1;
			player.setOnlineState(onlineState);
		}
		
		instUser.setOnlineState(onlineState);
		getInstUserDAL().update(instUser, 0);
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 设置允许最大同时在线
	 * @author mp
	 * @date 2015-4-13 上午11:10:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void allowMaxOnline (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int maxOnlineNum = (int)msgMap.get("maxOnlineNum");
		ParamConfig.canAllowPlayerNum = maxOnlineNum;
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 获取总值
	 * @author mp
	 * @date 2015-4-13 上午11:11:09
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lookRecargeSum (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int rechargeSum = getInstPlayerRechargeDAL().getStatResult("sum", "thisrmb", "");
		MessageData retMsg = new MessageData();
		retMsg.putIntItem("总额", rechargeSum);
		MessageUtil.sendGMMsg(channelId, msgMap, retMsg);
	}
	
	/**
	 * 模拟充值
	 * @author mp
	 * @date 2015-5-1 下午3:45:02
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void analogRecharge (HashMap<String, Object> msgMap, String channelId) throws Exception {
		String playerName = (String)msgMap.get("playerName");
		int thisrmb = (int)msgMap.get("rmb");//本次充值金额(人民币)
		int thisamt = thisrmb * 10;//本次充值金额(游戏币)
		
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.vipExp)) {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.vipExp + "");
			thisamt = (int)(thisamt * activityHoliday.getMultiple());
		}
		
		InstPlayer instPlayer = null;
		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList(" name = '" + playerName + "'", 0);
		if (instPlayerList.size() > 0) {
			instPlayer = instPlayerList.get(0);
		} else {
			MessageUtil.sendFailMsg(channelId, msgMap, "查无此人");
			return;
		}
		int instPlayerId = instPlayer.getId();
		String openId = instPlayer.getOpenId();
		int operBeforeGoldNum = instPlayer.getGold();
		
		//处理元宝  赠送的元宝  + 充值的元宝
		List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
		Collections.sort(instPlayerRechargeList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((InstPlayerRecharge)a).getSaveamt();
				int two = ((InstPlayerRecharge)b).getSaveamt(); 
				return (int)(two - one); 
			}
		}); 
		
		DictRecharge dictRecharge = null;
		for (DictRecharge obj : DictList.dictRechargeList) {
			if (thisrmb == obj.getRmb() && obj.getFirstAmt() != 0) {
				dictRecharge = obj;
				break;
			}
		}
		
		//如果无此计费点，不处理
		if (dictRecharge == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "无此计费点");
			return;
		}
		
		//处理额外赠送元宝（首充/非首充/没有这一档）
		int freeGold = PlayerUtil.getFreeGold(thisrmb, instPlayerRechargeList);
		
		int thisGold = freeGold + thisrmb * 10;
		instPlayer.setGold(instPlayer.getGold() + thisGold);
		
		//处理vip逻辑-根据总充值rmb金额,计算vip等级
//		int savermb = getInstPlayerRechargeDAL().getStatResult("sum", "thisrmb", "instPlayerId = " + instPlayerId) + thisrmb; //累计充值金额 = 原始记录 + 本次记录
		int savermb = (getInstPlayerRechargeDAL().getStatResult("sum", "thisamt", "instPlayerId = " + instPlayerId) / 10) + (thisamt / 10); // 累计充值金额 = 原始记录 + 本次记录
		int vipSaveRmb = savermb;
		if (ParamConfig.rechargeMap.containsKey(openId)) {
			List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.rechargeRetGold+"'", 0);
			if (instPlayerBigTableList.size() > 0) {
				InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
				vipSaveRmb = vipSaveRmb + (Integer.valueOf(instPlayerBigTable.getValue4()) / 10);
			}
		}
		
		int vipLevel = PlayerUtil.getVipLevel(instPlayer, vipSaveRmb);
		if (vipLevel > instPlayer.getVipLevel()) {
			instPlayer.setVipLevel(vipLevel);
		}
		
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		MessageData syncMsgData = new MessageData();
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//更新玩家成就计数实例数据
		AchievementUtil.updateAchievement (instPlayerId, StaticAchievementType.vip, vipLevel, syncMsgData, null);
		
		//记录充值日志
		Map<String, Object> retMap = Maps.newHashMap();
		retMap.put("balance", new Double(0));
		retMap.put("gen_balance", new Double(0));
		PlayerUtil.addInstPlayerRechargeGm(retMap, "huayi", instPlayerId, openId, 0, playerName, savermb * 10, thisamt, thisamt, thisrmb, "模拟充值", dictRecharge);
		
		int operAfterGoldNum = instPlayer.getGold();
		
		PlayerUtil.addInstPlayerGoldStatics(instPlayerId, GoldStaticsType.add, thisGold, 0, "充值(含赠送游戏币)", operBeforeGoldNum, operAfterGoldNum, "", "", "");
		
		//同步消息
		MessageUtil.sendSyncMsgToOne(openId, syncMsgData);
		
		GmUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 设置聊天cd时间
	 * @author mp
	 * @date 2015-8-6 下午7:54:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void setChatCd (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int value = (int)msgMap.get("value");
		ParamConfig.chatCd = value;
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 设置登录不验证状态 0-验证 1-不验证
	 * @author mp
	 * @date 2015-8-12 上午11:42:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void setLoginEvnState (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int value = (int)msgMap.get("value");
		ParamConfig.isOpenNoEnv = value;
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 设置购买开服基金总人数
	 * @author mp
	 * @date 2015-8-20 下午5:25:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void setOpenServerFundNum (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int value = (int)msgMap.get("value");
		
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '"+StaticBigTable.sumBuyFundNum+"'", 0);
		if (instPlayerBigTableList.size() > 0) {
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			instPlayerBigTable.setValue1(value + "");
			getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
			ParamConfig.sumBuyFundNum = value;
		}
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 是否向客服工具推送数据
	 * @author mp
	 * @date 2015-8-25 下午2:22:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void pushData (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int value = (int)msgMap.get("value");
		ParamConfig.isOpenPushDataCenter = value;
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 给玩家去引导
	 * @author mp
	 * @date 2015-8-28 下午3:53:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void dropGuip (HashMap<String, Object> msgMap, String channelId) throws Exception {
		String playerName = (String)msgMap.get("value");
		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("name = '"+playerName+"'", 0);
		if (instPlayerList.size() > 0) {
			InstPlayer instPlayer = instPlayerList.get(0);
//			int instPlayerId = instPlayer.getId();
			//从缓存数据里边将玩家清空
//			 PlayerMemObj playerMemObj = DALFather.getPlayerMemObjByPlayerId(instPlayerId);
//			 playerMemObj.instPlayerMap = null;
			
			instPlayer.setGuidStep("f&f");
			instPlayer.setGuipedBarrier("f&f");
			getInstPlayerDAL().update(instPlayer, 0);
		}
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 查看游戏服中map/list大小信息
	 * @author mp
	 * @date 2015-8-29 下午4:35:34
	 * @Description
	 */
	public void lookMapSizeInfo (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		int PlayerMemObjMapUtilSize = PlayerMemObjMapUtil.getSize();
		int nameFirstListSize = ParamConfig.nameFirstList.size();
		int nameSecondListSize = ParamConfig.nameSecondList.size();
		int nameThridListSize = ParamConfig.nameThridList.size();
		int blockingQueueSize = ParamConfig.blockingQueue.size();
		int acrossDayQueue = ParamConfig.acrossDayQueue.size();
		int unionMapSize = ParamConfig.unionMap.size();
		int unionPlayerSize = ParamConfig.unionPlayer.size();
		int openIdsSize = ParamConfig.openIds.size();
		int rechargeMapSize = ParamConfig.rechargeMap.size();
		int rechargeRetRuleMapSize = ParamConfig.rechargeRetRuleMap.size();
		int playerNameMapSize = ParamConfig.playerNameMap.size();
		int recruitJifenMapSize = ParamConfig.recruitJifenMap.size();
		int recruitJifenRewardMapSize = ParamConfig.recruitJifenRewardMap.size();
		int recruitRankRewardMapSize = ParamConfig.recruitRankRewardMap.size();
		int strogerHeroJifenMapSize = ParamConfig.strogerHeroJifenMap.size();
		int strogerRankRewardMapSize = ParamConfig.strogerRankRewardMap.size();
		
		int commonAwardSize = ActivityLuckManager.getInstance().getCommonAward().size();
		int limitAwardSize = ActivityLuckManager.getInstance().getLimitAward().size();
		int rankItemMapSize = ActivityLuckManager.getInstance().getRankItemMap().size();
		int luckRankListSize = ActivityLuckManager.getInstance().getLuckRankList().size();
		int luckRankMapSize = ActivityLuckManager.getInstance().getLuckRankMap().size();
		int orderAwardsSize = ActivityLuckManager.getInstance().getOrderAwards().size();
		int limitRemainSize = ActivityLuckManager.getInstance().getLimitRemain().size();
		
		int blockingDequeSize = MarqueeUtil.blockingDeque.size();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("PlayerMemObjMapUtilSize").append("=").append(PlayerMemObjMapUtilSize).append("\n");
		sb.append("nameFirstListSize").append("=").append(nameFirstListSize).append("\n");
		sb.append("nameSecondListSize").append("=").append(nameSecondListSize).append("\n");
		sb.append("nameThridListSize").append("=").append(nameThridListSize).append("\n");
		sb.append("blockingQueueSize").append("=").append(blockingQueueSize).append("\n");
		sb.append("acrossDayQueue").append("=").append(acrossDayQueue).append("\n");
		sb.append("unionMapSize").append("=").append(unionMapSize).append("\n");
		sb.append("unionPlayerSize").append("=").append(unionPlayerSize).append("\n");
		sb.append("openIdsSize").append("=").append(openIdsSize).append("\n");
		sb.append("rechargeMapSize").append("=").append(rechargeMapSize).append("\n");
		sb.append("rechargeRetRuleMapSize").append("=").append(rechargeRetRuleMapSize).append("\n");
		sb.append("playerNameMapSize").append("=").append(playerNameMapSize).append("\n");
		sb.append("recruitJifenMapSize").append("=").append(recruitJifenMapSize).append("\n");
		sb.append("recruitJifenRewardMapSize").append("=").append(recruitJifenRewardMapSize).append("\n");
		sb.append("recruitRankRewardMapSize").append("=").append(recruitRankRewardMapSize).append("\n");
		sb.append("strogerHeroJifenMapSize").append("=").append(strogerHeroJifenMapSize).append("\n");
		sb.append("strogerRankRewardMapSize").append("=").append(strogerRankRewardMapSize).append("\n");
		
		sb.append("commonAwardSize").append("=").append(commonAwardSize).append("\n");
		sb.append("limitAwardSize").append("=").append(limitAwardSize).append("\n");
		sb.append("rankItemMapSize").append("=").append(rankItemMapSize).append("\n");
		sb.append("luckRankListSize").append("=").append(luckRankListSize).append("\n");
		sb.append("luckRankMapSize").append("=").append(luckRankMapSize).append("\n");
		sb.append("orderAwardsSize").append("=").append(orderAwardsSize).append("\n");
		sb.append("limitRemainSize").append("=").append(limitRemainSize).append("\n");

		sb.append("blockingDequeSize").append("=").append(blockingDequeSize).append("\n");
		
		
		
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", sb.toString());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		
	}
	
	/**
	 * 查看游戏服中map/list容量信息
	 * @author mp
	 * @date 2015-8-29 下午5:32:02
	 * @Description
	 */
	public void lookMapCaptainInfo (HashMap<String, Object> msgMap, String channelId) throws Exception {
		
//		int PlayerMemObjMapUtilCap = SerializeUtil.serialize(PlayerMemObjMapUtil.getMap()).length;
		int nameFirstListCap = SerializeUtil.serialize(ParamConfig.nameFirstList).length;
		int nameSecondListCap = SerializeUtil.serialize(ParamConfig.nameSecondList).length;
		int nameThridListCap = SerializeUtil.serialize(ParamConfig.nameThridList).length;
		int blockingQueueCap = SerializeUtil.serialize(ParamConfig.blockingQueue).length;
		int acrossDayQueueCap = SerializeUtil.serialize(ParamConfig.acrossDayQueue).length;
		int unionMapCap = SerializeUtil.serialize(ParamConfig.unionMap).length;
		int unionPlayerCap = SerializeUtil.serialize(ParamConfig.unionPlayer).length;
		int openIdsCap = SerializeUtil.serialize(ParamConfig.openIds).length;
		int rechargeMapCap = SerializeUtil.serialize(ParamConfig.rechargeMap).length;
		int rechargeRetRuleMapCap = SerializeUtil.serialize(ParamConfig.rechargeRetRuleMap).length;
		int playerNameMapCap = SerializeUtil.serialize(ParamConfig.playerNameMap).length;
		int recruitJifenMapCap = SerializeUtil.serialize(ParamConfig.recruitJifenMap).length;
		int recruitJifenRewardMapCap = SerializeUtil.serialize(ParamConfig.recruitJifenRewardMap).length;
		int recruitRankRewardMapCap = SerializeUtil.serialize(ParamConfig.recruitRankRewardMap).length;
		int strogerHeroJifenMapCap = SerializeUtil.serialize(ParamConfig.strogerHeroJifenMap).length;
		int strogerRankRewardMapCap = SerializeUtil.serialize(ParamConfig.strogerRankRewardMap).length;
		
		int commonAwardCap = SerializeUtil.serialize(ActivityLuckManager.getInstance().getCommonAward()).length;
		int limitAwardCap = SerializeUtil.serialize(ActivityLuckManager.getInstance().getLimitAward()).length;
		int rankItemMapCap = SerializeUtil.serialize(ActivityLuckManager.getInstance().getRankItemMap()).length;
		int luckRankListCap = SerializeUtil.serialize(ActivityLuckManager.getInstance().getLuckRankList()).length;
		int luckRankMapCap = SerializeUtil.serialize(ActivityLuckManager.getInstance().getLuckRankMap()).length;
		int orderAwardsCap = SerializeUtil.serialize(ActivityLuckManager.getInstance().getOrderAwards()).length;
		int limitRemainCap = SerializeUtil.serialize(ActivityLuckManager.getInstance().getLimitRemain()).length;
		
		int blockingDequeCap = SerializeUtil.serialize(MarqueeUtil.blockingDeque).length;
		
		
		StringBuffer sb = new StringBuffer();
//		sb.append("PlayerMemObjMapUtilCap").append("=").append(PlayerMemObjMapUtilCap).append("\n");
		sb.append("nameFirstListCap").append("=").append(nameFirstListCap).append("\n");
		sb.append("nameSecondListCap").append("=").append(nameSecondListCap).append("\n");
		sb.append("nameThridListCap").append("=").append(nameThridListCap).append("\n");
		sb.append("blockingQueueCap").append("=").append(blockingQueueCap).append("\n");
		sb.append("acrossDayQueueCap").append("=").append(acrossDayQueueCap).append("\n");
		sb.append("unionMapCap").append("=").append(unionMapCap).append("\n");
		sb.append("unionPlayerCap").append("=").append(unionPlayerCap).append("\n");
		sb.append("openIdsCap").append("=").append(openIdsCap).append("\n");
		sb.append("rechargeMapCap").append("=").append(rechargeMapCap).append("\n");
		sb.append("rechargeRetRuleMapCap").append("=").append(rechargeRetRuleMapCap).append("\n");
		sb.append("playerNameMapCap").append("=").append(playerNameMapCap).append("\n");
		sb.append("recruitJifenMapCap").append("=").append(recruitJifenMapCap).append("\n");
		sb.append("recruitJifenRewardMapCap").append("=").append(recruitJifenRewardMapCap).append("\n");
		sb.append("recruitRankRewardMapCap").append("=").append(recruitRankRewardMapCap).append("\n");
		sb.append("strogerHeroJifenMapCap").append("=").append(strogerHeroJifenMapCap).append("\n");
		sb.append("strogerRankRewardMapCap").append("=").append(strogerRankRewardMapCap).append("\n");
		
		sb.append("commonAwardCap").append("=").append(commonAwardCap).append("\n");
		sb.append("limitAwardCap").append("=").append(limitAwardCap).append("\n");
		sb.append("rankItemMapCap").append("=").append(rankItemMapCap).append("\n");
		sb.append("luckRankListCap").append("=").append(luckRankListCap).append("\n");
		sb.append("luckRankMapCap").append("=").append(luckRankMapCap).append("\n");
		sb.append("orderAwardsCap").append("=").append(orderAwardsCap).append("\n");
		sb.append("limitRemainCap").append("=").append(limitRemainCap).append("\n");

		sb.append("blockingDequeCap").append("=").append(blockingDequeCap).append("\n");
		
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", sb.toString());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 查看玩家缓存容量
	 * @author mp
	 * @date 2015-8-29 下午6:43:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lookPlayerMapCaptainInfo (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int PlayerMemObjMapUtilSize = PlayerMemObjMapUtil.getSize();
		int PlayerMemObjMapUtilCap = SerializeUtil.serialize(PlayerMemObjMapUtil.getMap()).length;
		StringBuffer sb = new StringBuffer();
		sb.append("PlayerMemObjMapUtilSize").append("=").append(PlayerMemObjMapUtilSize).append("\n");
		sb.append("PlayerMemObjMapUtilCap").append("=").append(PlayerMemObjMapUtilCap).append("\n");
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", sb.toString());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 清除下线几个小时的玩家缓存对象,默认2个小时
	 * @author mp
	 * @date 2015-8-30 下午4:57:28
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void clearNHour (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int value = (int)msgMap.get("value");
		if (value > 0) {
			ParamConfig.clearHourNum = value;
		}
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 设置允许的最大注册数
	 * @author mp
	 * @date 2015-8-31 上午7:16:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void setCanAllowMaxRegeditNum (HashMap<String, Object> msgMap, String channelId) throws Exception {
		int value = (int)msgMap.get("value");
		if (value > 0) {
			//更新数据库
			List<InstPlayerBigTable> maxRegeditNumList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '"+StaticBigTable.maxRegeditNum+"'", 0);
			if (maxRegeditNumList.size() > 0) {
				InstPlayerBigTable instPlayerBigTable = maxRegeditNumList.get(0);
				instPlayerBigTable.setValue1(value + "");
				getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
			}
			
			//更新缓存
			ParamConfig.canAllowMaxRegeditNum = value;
		}
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 添加白金月卡
	 * @author mp
	 * @date 2015-9-1 下午1:46:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addMonthCard (HashMap<String, Object> msgMap, String channelId) throws Exception {
		String monthCardPname = (String)msgMap.get("monthCardPname");
		String monthCardStartTime = (String)msgMap.get("monthCardStartTime");
		
		//验证是否有此玩家
		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("name = '"+monthCardPname+"'", 0);
		if (instPlayerList.size() > 0) {
			InstPlayer instPlayer = instPlayerList.get(0);
			int instPlayerId = instPlayer.getId();
			
			//验证是否已购买月卡
			List<InstActivity> instActivitieList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and useNum = 1", 0);
			if (instActivitieList.size() > 0) {
				for (InstActivity instActivity : instActivitieList) {
					getInstActivityDAL().deleteById(instActivity.getId(), instPlayerId);
					
					//如果玩家在线,给玩家同步消息
					MessageData syncMsgData = new MessageData();
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instActivity, instActivity.getId(), "", syncMsgData);
					Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
					if (player != null) {
						MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
					}
				}
			}
			InstActivity instActivity = new InstActivity();
			instActivity.setInstPlayerId(instPlayerId);
			instActivity.setActivityId(StaticActivity.monthCard);
			instActivity.setActivityTime(DateUtil.getNumDayDate(monthCardStartTime, 30) + " 00:00:00");
			instActivity.setSpareOne("");
			instActivity.setIsForever(0);
			instActivity.setUseNum(1);
			getInstActivityDAL().add(instActivity, instPlayerId);
			
			//如果玩家在线,给玩家同步消息
			MessageData syncMsgData = new MessageData();
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
			Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
			if (player != null) {
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
			}
		}
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 添加黄金月卡
	 * @author mp
	 * @date 2015-10-28 上午11:42:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addGoldMonthCard (HashMap<String, Object> msgMap, String channelId) throws Exception {
		String monthCardPname = (String)msgMap.get("goldMonthCardPname");
		String monthCardStartTime = (String)msgMap.get("goldMonthCardStartTime");
		
		//验证是否有此玩家
		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("name = '"+monthCardPname+"'", 0);
		if (instPlayerList.size() > 0) {
			InstPlayer instPlayer = instPlayerList.get(0);
			int instPlayerId = instPlayer.getId();
			
			//验证是否已购买月卡
			List<InstActivity> instActivitieList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and useNum = 2", 0);
			if (instActivitieList.size() > 0) {
				for (InstActivity instActivity : instActivitieList) {
					getInstActivityDAL().deleteById(instActivity.getId(), instPlayerId);
					
					//如果玩家在线,给玩家同步消息
					MessageData syncMsgData = new MessageData();
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instActivity, instActivity.getId(), "", syncMsgData);
					Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
					if (player != null) {
						MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
					}
				}
			}
			InstActivity instActivity = new InstActivity();
			instActivity.setInstPlayerId(instPlayerId);
			instActivity.setActivityId(StaticActivity.monthCard);
			instActivity.setActivityTime(DateUtil.getNumDayDate(monthCardStartTime, 30) + " 00:00:00");
			instActivity.setSpareOne("");
			instActivity.setIsForever(0);
			instActivity.setUseNum(2);
			getInstActivityDAL().add(instActivity, instPlayerId);
			
			//如果玩家在线,给玩家同步消息
			MessageData syncMsgData = new MessageData();
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
			Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
			if (player != null) {
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
			}
		}
		
		//发送成功消息
		GmUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 查询当前设置的最大注册数
	 * @author mp
	 * @date 2015-9-2 上午11:26:19
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void currMaxRegeditNum(HashMap<String, Object> msgMap, String channelId)throws Exception{
		
		//组织结果
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("r", "s");
		
		//获取在线人数
		int onlineNum = ParamConfig.canAllowMaxRegeditNum;
		retMsgData.putStringItem("当前设置的最大注册人数", onlineNum+"");

		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
}

/**
 * 冻结用户
 * @author mp
 * @date 2015-4-10 下午12:16:13
 */
class FrozenUser {
	
	private int id;
	
	private String name;
	
	private String frozenTime;

	public FrozenUser () {
		
	}
	
	public FrozenUser (int id, String name, String frozenTime) {
		this.id = id;
		this.name = name;
		this.frozenTime = frozenTime;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrozenTime() {
		return frozenTime;
	}

	public void setFrozenTime(String frozenTime) {
		this.frozenTime = frozenTime;
	}
	
}

/**
 * 聊天禁言
 * @author mp
 * @date 2015-8-6 下午8:04:32
 */
class ShutUpUser {
	
	private int id;
	
	private String name;
	
	private String frozenTime;

	public ShutUpUser () {
		
	}
	
	public ShutUpUser (int id, String name, String frozenTime) {
		this.id = id;
		this.name = name;
		this.frozenTime = frozenTime;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrozenTime() {
		return frozenTime;
	}

	public void setFrozenTime(String frozenTime) {
		this.frozenTime = frozenTime;
	}
	
}