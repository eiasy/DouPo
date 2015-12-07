package com.huayi.doupo.logic.handler;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.huayi.doupo.activity.PlayerActivityManager;
import com.huayi.doupo.activity.cost.TotalCostManager;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictAchievement;
import com.huayi.doupo.base.model.DictActivityAddRecharge;
import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.DictConstell;
import com.huayi.doupo.base.model.DictDailyTask;
import com.huayi.doupo.base.model.DictGuipStep;
import com.huayi.doupo.base.model.DictLevelProp;
import com.huayi.doupo.base.model.DictRecharge;
import com.huayi.doupo.base.model.DictTitleDetail;
import com.huayi.doupo.base.model.DictVIP;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerAchievement;
import com.huayi.doupo.base.model.InstPlayerAchievementValue;
import com.huayi.doupo.base.model.InstPlayerArenaNPC;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerChapter;
import com.huayi.doupo.base.model.InstPlayerConstell;
import com.huayi.doupo.base.model.InstPlayerDailyTask;
import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.InstPlayerFormation;
import com.huayi.doupo.base.model.InstPlayerLineup;
import com.huayi.doupo.base.model.InstPlayerMagic;
import com.huayi.doupo.base.model.InstPlayerPagoda;
import com.huayi.doupo.base.model.InstPlayerPartner;
import com.huayi.doupo.base.model.InstPlayerRecharge;
import com.huayi.doupo.base.model.InstPlayerWing;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticActivityHoliday;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.base.model.statics.StaticDailyTask;
import com.huayi.doupo.base.model.statics.StaticMsgRule;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticSysConfigStr;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.DateUtil.DateType;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.LogicLogUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.base.util.logic.wordfilter.WordFilterUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.CardUtil;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.NPCPlayerUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PagodaUtil;
import com.huayi.doupo.logic.handler.util.PillUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.UserUtil;
import com.huayi.doupo.logic.handler.util.YFireUtil;
import com.huayi.doupo.logic.util.ChannelMapUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.NameUtil;
import com.huayi.doupo.logic.util.PlayerFightValueMapUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

/**
 * 玩家处理类
 * @author mp
 * @date 2013-11-7 下午3:56:25
 */
public class PlayerHandler extends BaseHandler{
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testGuid(Map<String, Object> msgMap, String channelId) throws Exception {
		
//		getInstChapterActivityDAL().add(model, instPlayerId)
		
		
		
		/*
		
		
		
		
//		System.out.println(DateUtil.getCurrTime() + " testGuid");
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		
		int currLevelOrBarrier = 0;
		int currStep = 0;
		int type = 0;
		String givedLevelGuidStep = "";
		
		//处理等级相关的引导
		if (step.contains("_")) {
			type = 1;
			currLevelOrBarrier = ConvertUtil.toInt(((String)msgMap.get("step")).split("_")[0]);
			currStep = ConvertUtil.toInt(((String)msgMap.get("step")).split("_")[1]);
			if(instPlayer.getGuidStep().substring(instPlayer.getGuidStep().indexOf('&') + 1, instPlayer.getGuidStep().length()).length() > 1){
				givedLevelGuidStep = instPlayer.getGuidStep().split("&")[1];
			}
		} else {
			//处理关卡相关的引导
			type = 2;
			currLevelOrBarrier = ConvertUtil.toInt(((String)msgMap.get("step")).split("B")[0]);
			currStep = ConvertUtil.toInt(((String)msgMap.get("step")).split("B")[1]);
			
			if(instPlayer.getGuipedBarrier().substring(instPlayer.getGuipedBarrier().indexOf('&') + 1, instPlayer.getGuipedBarrier().length()).length() > 1){
				givedLevelGuidStep = instPlayer.getGuipedBarrier().split("&")[1];
			}
		}
		
		//需要时,送物品
		int ifSync = 0;
		for (DictGuipStep obj : DictList.dictGuipStepList) {
			if (obj.getType() == type && obj.getLevelOrBarrierId() == currLevelOrBarrier && obj.getStep() == currStep) {
				
				//验证此等级步骤是否已经送过物品[初始化玩家的时候,将'&',写入]
				if (StringUtil.contains(givedLevelGuidStep, step, ";")) {
					MessageUtil.sendFailMsg(channelId, msgMap, "");
					return;
				}
				ifSync++;
				String thingString = obj.getThings();
				for (String thing : thingString.split(";")) {
					int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
					int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
					int tableValue = ConvertUtil.toInt(thing.split("_")[2]);
					ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, tableValue, syncMsgData, msgMap);
				}
				
				//记录此给物品的等级和步骤
				givedLevelGuidStep = givedLevelGuidStep + step + ";";
			}
		}
		
		String guidStep = step + "&" + givedLevelGuidStep;

		//记录步骤
		if (type == 1) {
			instPlayer.setGuidStep(guidStep);
		} else {
			instPlayer.setGuipedBarrier(guidStep);
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		if(ifSync != 0){
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
		}
		MessageUtil.sendSuccMsg(channelId, msgMap);
	*/}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testGiveName(Map<String, Object> msgMap, String channelId) throws Exception {/*
//		System.out.println(DateUtil.getCurrTime() + " testGiveName");
		//获取参数
		String name = (String)msgMap.get("name");
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//验证名字是否含有敏感字符
		if(WordFilterUtil.filterText(name, '*').getBadWords().length() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_filtername);
			return ;
		}
		
		//验证名字是否含特殊字符 类似空格 
		if(name.contains(" ") || name.contains("\\") || name.contains("/")){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_playerName_Rule);
			return ;
		}
		
		//验证是否已有此名字
		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("name = '" + name + "'", instPlayerId);//不区分大小写，只要字母一样就不行，不管你大写小写[相对来说更为严格了]
		if(instPlayerList.size() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_havePlayer);
			return ;
		}
		
		//验证竞技场NPC里边是否有此名字
		for (Entry<Integer, InstPlayerArenaNPC> entry : NPCPlayerUtil.instPlayerArenaNPCMap.entrySet()) {
			InstPlayerArenaNPC instPlayerArenaNPC = entry.getValue();
			if (instPlayerArenaNPC.getName().equals(name)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_havePlayer);
				return ;
			}
		}
		
		//更新数据库
		instPlayer.setName(name);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//设置内存Player对象中的玩家名
		Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
		player.setPlayerName(name);
		
		List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		int bs = 0;
		if (instPlayerCardList.size() <= 0) {
			
			bs++;
			
			//初始时只有一个萧炎, 没有选人界面了
			int [] initCards = {154};
			int index = 1;
			
			for (int cid : initCards) {
				
				InstPlayerCard instCard = null;
				
				//初始化卡牌实例表
				instCard = CardUtil.initPlayerCard(instPlayerId, cid, 1);
				
				//初始化玩家阵型表
				CardUtil.initPlayerFormation(instPlayerId, instCard, index, 1);
				
				//初始化玩家卡牌命宫实例表
				DictCard dictCard = DictMap.dictCardMap.get(instCard.getCardId() + "");
				if (!dictCard.getConstells().equals("")) {
					String constells[] = dictCard.getConstells().split(";");
					String instPlayerConstells = "";
					for(String constell : constells){
						DictConstell dictConstell = DictMap.dictConstellMap.get(ConvertUtil.toInt(constell) + "");
						InstPlayerConstell obj = PillUtil.initPlayerConstell(instPlayerId, instCard.getId(), ConvertUtil.toInt(constell), dictConstell.getPills().split(";").length);
						instPlayerConstells += obj.getId() + ";";
					}
					instCard.setInstPlayerConstells(instPlayerConstells.substring(0, instPlayerConstells.length() - 1));
					getInstPlayerCardDAL().update(instCard, instPlayerId);
				}
				index++;
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		
		//组织前端数据
		if (bs != 0) {
			
			//初始化成就
			PlayerUtil.initAchievement(instPlayer);
			
			//初始化爬塔
			PagodaUtil.initInstPlayerPagoda(instPlayer.getId());
			
			//初始化每日任务
			PlayerUtil.initDailyTask(instPlayer);
			
			//初始化默认抢夺
			PlayerUtil.initInstPlayerLoot(instPlayer.getId());
			
			//初始化活动
			PlayerUtil.initInstActivity(instPlayer.getId());
			
			//初始化活动
			ActivityUtil.addInstActivity(instPlayer.getId(), StaticActivity.addRecharge, new MessageData());
			
			//初始化在线奖励活动
			SysActivity activity = DictMap.sysActivityMap.get(StaticActivity.onlineRewards + "");
			if(activity.getIsForevery() == 1 || (DateUtil.getMillSecond(DateUtil.getCurrTime()) >= DateUtil.getMillSecond(activity.getStartTime()) && DateUtil.getMillSecond(DateUtil.getCurrTime()) <= DateUtil.getMillSecond(activity.getEndTime()))){ 
				ActivityUtil.addInstActivityOnlineRewards(instPlayer.getId());
			}
			
			//初始化开服礼包
			SysActivity openServiceBag = DictMap.sysActivityMap.get(StaticActivity.openServiceBag + "");
			if(openServiceBag.getIsForevery() == 1 || (DateUtil.getMillSecond(DateUtil.getCurrTime()) >= DateUtil.getMillSecond(openServiceBag.getStartTime()) && DateUtil.getMillSecond(DateUtil.getCurrTime()) <= DateUtil.getMillSecond(openServiceBag.getEndTime()))){ 
				ActivityUtil.addInstActivityOpenServiceBag(instPlayer.getId());
			}
			
			MessageData retMsgData = OrgFrontMsgUtil.retCardMsgData(instPlayerId);
			MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		} else {
			MessageUtil.sendSuccMsg(channelId, msgMap);
		}
	*/}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFight(Map<String, Object> msgMap, String channelId) throws Exception {/*
//		System.out.println(DateUtil.getCurrTime() + " testFight");
		
		//3个读1个更新
		for (int i = 0; i < 2; i++) {
			int playerId = RandomUtil.getRangeInt(0, PlayerMemObjMapUtil.getMap().size());
			getInstPlayerDAL().getModel(playerId, playerId);
		}
		
//		InstPlayerThing instPlayerThing = new InstPlayerThing();
//		instPlayerThing.setBagTypeId(1);
//		instPlayerThing.setIndexOrder(1);
//		instPlayerThing.setInstPlayerId(1);
//		instPlayerThing.setLevel(1);
//		instPlayerThing.setNum(1);
//		getInstPlayerThingDAL().add(instPlayerThing, 0);
		
		MessageUtil.sendSuccMsg(channelId, msgMap);
	*/}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testOther(Map<String, Object> msgMap, String channelId) throws Exception {/*
//		System.out.println(DateUtil.getCurrTime() + " testOther");
		
		//3个读1个更新
		for (int i = 0; i < 1; i++) {
			int playerId = RandomUtil.getRangeInt(0, PlayerMemObjMapUtil.getMap().size());
			getInstPlayerDAL().getModel(playerId, playerId);
		}
		
//		InstPlayerThing instPlayerThing = new InstPlayerThing();
//		instPlayerThing.setBagTypeId(1);
//		instPlayerThing.setIndexOrder(1);
//		instPlayerThing.setInstPlayerId(1);
//		instPlayerThing.setLevel(1);
//		instPlayerThing.setNum(1);
//		getInstPlayerThingDAL().add(instPlayerThing, 0);
		
		MessageUtil.sendSuccMsg(channelId, msgMap);
	*/}
	
	/**
	 * 端口号：3000,测试登录(只包含数据库操作）
	 * @author mp
	 * @date 2015-7-2 下午3:00:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void login2(Map<String, Object> msgMap, String channelId) throws Exception {/*
		
		MessageData retMsgData =  new MessageData();
		
		LogUtil.out("----login2---");
//		long start = DateUtil.getCurrMill();
		
		String openId = (String)msgMap.get("openId");
		String channel = (String)msgMap.get("channel");
//		String zoneid = (String)msgMap.get("zoneid");
		String params = (String)msgMap.get("params");
		
		List<InstUser> userList = getInstUserDAL().getList(" openId = '" + openId + "'", 0);
		
		InstUser instUser = null;
		InstPlayer instPlayer = null;
		
		if(userList == null || userList.size() <= 0){
			
			//验证是否达到最高同时在线(新用户注册发现系统已达到最大在线人数时,不允许登录)
			int maxOnline = PlayerMapUtil.getSize();
			if (maxOnline >= ParamConfig.canAllowPlayerNum) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_serverBao);
				return ;
			}
			
			//创建用户账号
			instUser = UserUtil.initUser(openId, channel, "0", "0");
			
			//创建玩家角色
			instPlayer = PlayerUtil.initPlayer(openId, null, channel, "0");
			
			//初始化体力和耐力
			PlayerUtil.initEngeryAndVigor(retMsgData);
			
			//首次登录（注册）时,向客户端发送的实例表缓存数据
			retMsgData = OrgFrontMsgUtil.retFirstLoginPlayerInfoMsgData(retMsgData,instPlayer, 0);
			
		}
		
	*/}
	
	/**
	 * 端口号：3000,测试登录(不包含数据库操作）
	 * @author mp
	 * @date 2015-7-2 下午3:00:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void login1(Map<String, Object> msgMap, String channelId) throws Exception {/*
		
		MessageData retMsgData =  new MessageData();
		
		LogUtil.out("----login1---");
//		long start = DateUtil.getCurrMill();
		
		String openId = (String)msgMap.get("openId");
		String channel = (String)msgMap.get("channel");
//		String zoneid = (String)msgMap.get("zoneid");
		String params = (String)msgMap.get("params");
		if (params != null && !params.equals("")) {
			channel = params.split(" ")[0];
		}
		
		String env = SysConfigUtil.getValue("env");
		String session_id = "";
		String session_type = "";
		String pay_token = "";
		String userIp = (String)msgMap.get("userip");
		
		//测试用代码,压测完需删除
		if (userIp != null && !userIp.equals("")) {
			if (((String)msgMap.get("userip")).equals("test")) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_serverBao);
				return ;
			}
		}

		
//		List<MessageItem> messageItemList = retMsgData.getMsgData().getMessageItemList();
//		for (MessageItem messageItem : messageItemList ) {
//			System.out.println(messageItem.getKey());
//		}
		
//		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		
//		System.out.println("--------------------------Login ----------" + (DateUtil.getCurrMill() - start));
		
	*/}
	
	/**
	 * 登录
	 * @author hzw
	 * @date 2014-6-17下午5:00:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void login(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData retMsgData =  new MessageData();
		
		LogUtil.out("----login---");
//		long start = DateUtil.getCurrMill();
		
		String openId = (String)msgMap.get("openId");
		String channel = (String)msgMap.get("channel");
		if (channel == null) {
			channel = "";
		}
		
		String env = SysConfigUtil.getValue("env");
		
		String accountId = "";
		String session_id = "";
		String session_type = "";
		String pay_token = "";
//		String userIp = (String)msgMap.get("userip");
		String loginServer = "";
		String channel_sub = "";
		
		//对接登录服相关的
		String token = (String)msgMap.get("token");
		String zoneid = (String)msgMap.get("zoneid");
		String aloneServerId = (String)msgMap.get("aloneServerId");//独服Id

		if (ParamConfig.isOpenNoEnv == 1) {
//			if (userIp != null && userIp.equals("1")) {
//				env = "0";
//			}
			if (openId != null) {
				env = "0";
			}
		}
		
		String iosNew = "";
		
		//判断环境[0-内网   非0-外网], 外网时需验证,后需要迁移到登录服中去
		if (!env.equals("0")) {
//			String appid = (String)msgMap.get("appid");
//			String appKey = (String)msgMap.get("appKey");
//			String openkey = (String)msgMap.get("openkey");
//			String userip = (String)msgMap.get("userip");
			
			//登录验证[分qq和微信]
//			if (channel.equals("qq")) {
//				pay_token = (String)msgMap.get("pay_token");//**新加的**
//				session_id = "openid";
//				session_type  = "kp_actoken";
//				if (!PlayerUtil.validateOpenKey(appid, appKey, openId, openkey, userip)) {
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_loginValidate);
//					return ;
//				}
//			} else 
//				if (channel.equals("wx")) {
//				session_id = "hy_gameid";
//				session_type = "wc_actoken";
//				if (!PlayerUtil.validateToken(appid, appKey, openId, openkey)) {
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_loginValidate);
//					return ;
//				}
//			} else
//			{
				String loginServerUrl = SysConfigUtil.getValue("login.server.url");
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("security_code", token);
				paramMap.put("server_id", zoneid);
				paramMap.put("product_id", aloneServerId);
				String params = HttpClientUtil.httpBuildQuery(paramMap);
				String loginResult = "";
				try {
					loginResult = HttpClientUtil.postMapSubmit(loginServerUrl, params);
					if (loginResult == null) {
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_linkLoginServer);
						return ;
					}
				} catch (Exception e) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_linkLoginServer);
					return ;
				}
				JSONObject loginJson = new JSONObject(loginResult);
				String retMsg = String.valueOf(loginJson.get("status"));
				if (!retMsg.equals("1")) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_loginValidate);
					return ;
				}
				channel = String.valueOf(loginJson.get("channel_mark"));
				openId =  String.valueOf(loginJson.get("user_id"));
				accountId =  String.valueOf(loginJson.get("accountId"));
				loginServer = String.valueOf(loginJson.get("loginServer"));
				channel_sub = String.valueOf(loginJson.get("channel_sub"));
				if (channel.equals("yijie")) {
					if (channel_sub.equals("com.doupo.zhangyue.zy")) {
						channel = "zhangyue";
					} else if (channel_sub.equals("com.doupo.zhuoyi.zy")) {
						channel = "zhuoyi";
					} else {
						try {
							channel = channel_sub.substring(channel_sub.lastIndexOf(".") + 1, channel_sub.length());
						} catch (Exception e) {
							LogUtil.error("子渠道表示错误", e);
						}
					}
				} else if (channel.equals("anysdk")) {
					if (channel_sub.equals("com.tongbu.doupo.y2game")) {
						channel = "tongbu";
					} else {
						try {
							channel = channel_sub.substring(channel_sub.lastIndexOf(".") + 1, channel_sub.length());
						} catch (Exception e) {
							LogUtil.error("子渠道表示错误", e);
						}
					}
				} else if (channel.equals("yiyou")) {
					try {
						channel = channel_sub.substring(channel_sub.lastIndexOf(".") + 1, channel_sub.length());
					} catch (Exception e) {
						LogUtil.error("子渠道表示错误", e);
					}
				} else if (channel.equals("iosy2game")) {
					try {
						iosNew = "iosy2game";
						channel = "dpcq";
					} catch (Exception e) {
						LogUtil.error("子渠道表示错误", e);
					}
				}
				openId = openId + "@" + channel;
			}
//		}

		//验证是否已经登录,处理账号异地登录逻辑
		if(PlayerMapUtil.containsUniquePlayer(openId)){
			Player oldPlayer = PlayerMapUtil.getUniquePlayer(openId);
			MessageUtil.sendFailMsg(oldPlayer.getChannelId(), msgMap, StaticCnServer.fail_openIdOtherLogin);
			TimeUnit.MILLISECONDS.sleep(200);
			Channel oldChannel = ChannelMapUtil.getByChannelId(oldPlayer.getChannelId());
			if(oldChannel != null){
				oldChannel.close();
			}
			TimeUnit.MILLISECONDS.sleep(200);
		}
	
		//账号是否已经存在，不存在创建（含玩家），返回玩家信息
		List<InstUser> userList = getInstUserDAL().getList(" openId = '" + openId + "'", 0);
		
		InstUser instUser = null;
		InstPlayer instPlayer = null;
		
		if(userList == null || userList.size() <= 0){
			
			//验证是否达到最高注册数[新用户注册发现系统已达到最大注册人数时,不允许登录]
			int regeditNum = getInstPlayerDAL().getCount("");
			if (regeditNum >= ParamConfig.canAllowMaxRegeditNum) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_serverBao);
				return ;
			}
			
			//验证是否达到最高同时在线(新用户注册发现系统已达到最大在线人数时,不允许登录)
			int maxOnline = PlayerMapUtil.getSize();
			if (maxOnline >= ParamConfig.canAllowPlayerNum) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_serverBao);
				return ;
			}
			
			//创建用户账号
			instUser = UserUtil.initUser(openId, channel, zoneid, accountId);
			
			//创建玩家角色
			instPlayer = PlayerUtil.initPlayer(openId, msgMap, channel, zoneid);
			
			//初始化体力和耐力
			PlayerUtil.initEngeryAndVigor(retMsgData);
			
			//是否为计费删档测试用户
//			int isAward = 0;
			int isAward = PlayerUtil.rechargeRetGold(openId, instPlayer, zoneid, channel, accountId);
			
			//首次登录（注册）时,向客户端发送的实例表缓存数据
			retMsgData = OrgFrontMsgUtil.retFirstLoginPlayerInfoMsgData(retMsgData, instPlayer, isAward);
			
		} else {
			instUser = userList.get(0);
			
			//玩家状态0-离线, 1-在线  2-冻结
			if (instUser.getOnlineState() == 2) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_frozen);
				return;
			}
			
			//更新用户账号信息
			UserUtil.updateUserInfo(instUser);
			
			instPlayer = getInstPlayerDAL().getList(" openId = '" + openId + "'", 0).get(0);
			
			//结算体力和耐力
			PlayerUtil.settleEngeryAndVigor(instPlayer, retMsgData);
			
			//更新活动补丁[内网时必走验证,外网时只验证1服,其他服不验证]    后因舍去应用宝,  此补丁关闭2015-07-06
//			if (channel != null && !env.equals("0") && (channel.equals("qq") || channel.equals("wx"))) {
//				PlayerUtil.updateActivity(instPlayer, env, msgMap);
//			}
		}
		
		//绑定Socket中的Channel 和 Player[放在前边]
		if (iosNew.equals("iosy2game")) {
			channel = "iosy2game";
		}
		PlayerUtil.bindSocketPlayer(channelId, channel, openId, instPlayer, msgMap, session_id, session_type, pay_token, instUser.getOnlineState(), accountId, loginServer, aloneServerId, channel_sub);
		
//		getInstPlayerFormationDAL().add(model, instPlayerId) 

		//非首次登录处理
		if (instUser.getLoginTotalTimes() != 1) {
			
			
//			instPlayer.setGold(gold)
			
			//处理非首次登录,牵扯到的事
			PlayerUtil.disposeLogin(instPlayer, instUser, retMsgData);
			
			//验证是否有未到账的充值
//			if (!env.equals("0") && (channel.equals("qq") || channel.equals("wx"))) {
//				PlayerUtil.rechargeLogin(instPlayer, channel);
//			}
			
			//非首次登录,向前端发的  玩家相关实例数据以便缓存
			retMsgData = OrgFrontMsgUtil.retPlayerInfoMsgData(retMsgData,instPlayer);
			
			//非首次登录时,记录角色登录日志
			try {
				String logContent = PlayerMapUtil.getPlayerByChannelId(channelId).getLogHeader() + "|元宝|" + instPlayer.getGold() + "|银币|" + instPlayer.getCopper();
				LogicLogUtil.info("roleLogin", logContent.replace("null", ""));
			} catch (Exception e) {
				LogUtil.error("角色登录日志错误", e);
			}
		} else {
			//首次登录时,记录创建角色日志
			try {
				LogicLogUtil.info("createRole", PlayerMapUtil.getPlayerByChannelId(channelId).getLogHeader());
			} catch (Exception e) {
				LogUtil.error("创建角色日志错误", e);
			}
		}
		
		//初始给名字
		if (instPlayer.getName() == null || instPlayer.getName().equals("")) {
			String randomName = NameUtil.getRandomName();
			retMsgData.putStringItem("name", randomName);
		}
		
		float multipleExp = 1f;
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.storeDiscont)) {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.storeDiscont + "");
			multipleExp = activityHoliday.getMultiple();
		}
		retMsgData.putIntItem("multipleExp", (int)(multipleExp * 100));
		
		//系统时间
		retMsgData.putStringItem("dateTime", DateUtil.getCurrTime());
		//玩家注册时间
		retMsgData.putStringItem("registerTime", instUser.getFirstLoginTime());
//		List<MessageItem> messageItemList = retMsgData.getMsgData().getMessageItemList();
//		for (MessageItem messageItem : messageItemList ) {
//			System.out.println(messageItem.getKey());
//		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		
		// 加载玩家活动数据（限时兑换，累计消耗）
		try {
			if (instPlayer != null) {
				PlayerActivityManager.getInstance().loadPlayerActivities(instPlayer.getId());
				TotalCostManager.getInstance().loadPlayerActivities(instPlayer.getId());
			}
		} catch (Exception e) {
			LogUtil.error("加载累计消耗时异常", e);
		}
//		System.out.println("--------------------------Login ----------" + (DateUtil.getCurrMill() - start));
		
	}
	
	/**
	  * 初始化卡牌
	  * @author hzw
	  * @date 2014-6-19下午2:06:20
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void choiceCard(Map<String, Object> msgMap, String channelId) throws Exception {/*
		
		int id = (Integer)msgMap.get("cardId");
		int instPlayerId = getInstPlayerId(channelId);
		DictInitCard initCard = DictMap.dictInitCardMap.get(id+"");
		
		int CardId = initCard.getCardId();
		
		List<InstPlayerCard> CardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId + " and CardId = " + CardId, instPlayerId);
		if(CardList.size() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_onlyOneInitCard);
			return;
		}
		
		//初始时要加一个萧炎
		int [] initCards = {154, CardId};
		int index = 1;
		
		for (int cid : initCards) {
			
			InstPlayerCard instCard = null;
			
			if (cid == 51 || cid == 125) {
				//初始化卡牌实例表
				instCard = CardUtil.addPlayerCard(instPlayerId, cid);
				
			} else {
				
				//初始化卡牌实例表
				instCard = CardUtil.initPlayerCard(instPlayerId, cid, 1);
				
				//初始化玩家阵型表
				CardUtil.initPlayerFormation(instPlayerId, instCard, index, 1);
			}
			
			//初始化玩家卡牌命宫实例表
			DictCard dictCard = DictMap.dictCardMap.get(instCard.getCardId() + "");
			String constells[] = dictCard.getConstells().split(";");
			String instPlayerConstells = "";
			for(String constell : constells){
				DictConstell dictConstell = DictMap.dictConstellMap.get(ConvertUtil.toInt(constell) + "");
				InstPlayerConstell obj = PillUtil.initPlayerConstell(instPlayerId, instCard.getId(), ConvertUtil.toInt(constell), dictConstell.getPills().split(";").length);
				instPlayerConstells += obj.getId() + ";";
			}
			instCard.setInstPlayerConstells(instPlayerConstells.substring(0, instPlayerConstells.length() - 1));
			getInstPlayerCardDAL().update(instCard, instPlayerId);
			index++;
		}
		
		
		
		//测试用初始数据
//		PlayerUtil.lineupTestInitData(CardId, instPlayerId);
//		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
//		PlayerUtil.initWarriorInitData(instPlayerId, channelId, instPlayer);
		
		//组织前端数据
		MessageData retMsgData = OrgFrontMsgUtil.retCardMsgData(instPlayerId);
//		retMsgData = OrgFrontMsgUtil.retPlayerInfoMsgData(retMsgData,instPlayer);//测试用初始数据
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	*/}
	
	/**
	  * 卡牌换位
	  * @author hzw
	  * @date 2014-6-24上午9:30:29
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void convertPosition(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		String positionList = (String)msgMap.get("positionList");
		String[] paramList = positionList.split(";");
		
		
		for(int i = 0; i < paramList.length; i++){
			int instCardId = ConvertUtil.toInt((paramList[i].split("_"))[0]);
			int type = ConvertUtil.toInt((paramList[i].split("_"))[1]);
			int position = ConvertUtil.toInt((paramList[i].split("_"))[2]);
			if(instCardId != 0){
				List<InstPlayerFormation> instPlayerFormationList = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instCardId, instPlayerId);
				if(instPlayerFormationList.size() == 0){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
					return;
				}
				InstPlayerFormation instPlayerFormation = instPlayerFormationList.get(0);
				if(instPlayerFormation.getInstPlayerId() != instPlayerId){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
					return;
				}
				if(instPlayerFormation.getPosition() != position || instPlayerFormation.getType() != type){
					instPlayerFormation.setPosition(position);
					instPlayerFormation.setType(type);
					getInstPlayerFormationDAL().update(instPlayerFormation, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFormation, instPlayerFormation.getId(), instPlayerFormation.getResult(), syncMsgData);
				}
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 卡牌上阵
	 * @author mp
	 * @date 2014-6-24 下午2:36:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cardInTeam(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instCardId = (Integer)msgMap.get("instCardId");
		int formationType = (Integer)msgMap.get("type");//阵型类型 1-主力  2-替补
		MessageData syncMsgData = new MessageData();
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		//验证参数
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instCardId , instPlayerId);
		if(instPlayerCard.getInstPlayerId() != instPlayerId){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		//是否已在阵上
		if(StaticCustomDict.inTeam == instPlayerCard.getInTeam() || StaticCustomDict.inPartner == instPlayerCard.getInTeam()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardInFormation);
			return;
		}
		
		//玩家所有上阵卡牌[只查一次库]
		List<InstPlayerFormation> playerFormationList = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		
		//组织正规军
		List<InstPlayerFormation> nomalPlayer = Lists.newArrayList();
		for (InstPlayerFormation instPlayerFormation : playerFormationList) {
			if (instPlayerFormation.getType() == 1) {
				nomalPlayer.add(instPlayerFormation);
			}
		}
		
		//组织替补军
		List<InstPlayerFormation> lightPlayer = Lists.newArrayList();
		for (InstPlayerFormation instPlayerFormation : playerFormationList) {
			if (instPlayerFormation.getType() == 2) {
				lightPlayer.add(instPlayerFormation);
			}
		}
		
		//验证是否阵型已满
		if ((nomalPlayer.size() >= DictMapUtil.getSysConfigFloatValue(StaticSysConfig.maxFightPlayer) && formationType == 1) || (lightPlayer.size() >= DictMapUtil.getSysConfigFloatValue(StaticSysConfig.maxLightPlayer) && formationType == 2)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_formationFull);
			return;
		}
		
		//是否与上阵卡牌 或 小伙伴列表中卡牌 重复
		List<InstPlayerFormation> formationList = Lists.newArrayList();
		for (InstPlayerFormation instPlayerFormation : playerFormationList) {
			if (instPlayerFormation.getCardId() == instPlayerCard.getCardId()) {
				formationList.add(instPlayerFormation);
			}
		}
		List<InstPlayerPartner> instPlayerPartnerList = getInstPlayerPartnerDAL().getList("instPlayerId = " + instPlayerId + " and cardId = " + instPlayerCard.getCardId(), instPlayerId);
		if (formationList.size() > 0 ||  instPlayerPartnerList.size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardRepeat);
			return;
		}
		
		//卡牌上阵处理
		instPlayerCard.setInTeam(StaticCustomDict.inTeam);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		//添加到阵型
		int formationPos = 0;
		if (formationType == 1) {
			formationPos = FormulaUtil.calcFormationPos(nomalPlayer);
		} else if (formationType == 2) {
			formationPos = FormulaUtil.calcFormationPos(lightPlayer);
		}
		InstPlayerFormation instPlayerFormation = CardUtil.initPlayerFormation(instPlayerId, instPlayerCard, formationPos, formationType);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerFormation, instPlayerFormation.getId(), instPlayerFormation.getResult(), syncMsgData);
		
		//验证1人、5人称号成就
		AchievementUtil.title1(instPlayerId, syncMsgData);
		AchievementUtil.title5(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 更换卡牌
	 * @author hzw
	 * @date 2014-6-24下午3:23:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void convertCard(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerFormationId = (Integer)msgMap.get("instPlayerFormationId");
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		
		InstPlayerFormation instPlayerFormation = getInstPlayerFormationDAL().getModel(instPlayerFormationId, instPlayerId);
		InstPlayerCard newInstPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId , instPlayerId);
		if(instPlayerFormation.getInstPlayerId() != instPlayerId || newInstPlayerCard.getInstPlayerId() != instPlayerId){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证此卡牌是否为上阵状态
		if(StaticCustomDict.inTeam == newInstPlayerCard.getInTeam() || StaticCustomDict.inPartner == newInstPlayerCard.getInTeam()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardInFormation);
			return;
		}
		
		//是否与上阵卡牌 或 小伙伴列表中卡牌 重复
		List<InstPlayerFormation> formationList = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId + " and cardId = " + newInstPlayerCard.getCardId(), instPlayerId);//要验证阵型是否已满[替补未确定,先不管]
		List<InstPlayerPartner> instPlayerPartnerList = getInstPlayerPartnerDAL().getList("instPlayerId = " + instPlayerId + " and cardId = " + newInstPlayerCard.getCardId(), instPlayerId);
		if (formationList.size() > 0 ||  instPlayerPartnerList.size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardRepeat);
			return;
		}
		
		//检测上阵卡牌是否达到大斗师级别,如果没有卸下翅膀
		DictTitleDetail titleDetail = DictMap.dictTitleDetailMap.get(newInstPlayerCard.getTitleDetailId() + "");
		int playerTitleId = titleDetail.getTitleId();
		int needTitleId = DictMapUtil.getSysConfigIntValue(StaticSysConfig.wingTitleId);
		if (playerTitleId < needTitleId) {
			//将原来在阵的翅膀卸下
			List<InstPlayerWing> instPlayerWings = getInstPlayerWingDAL().getList(" instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerFormation.getInstCardId(), instPlayerId);
			if(instPlayerWings.size() > 0){
				for(InstPlayerWing obj : instPlayerWings){
					obj.setInstCardId(0);
					getInstPlayerWingDAL().update(obj, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, obj.getId(), obj.getResult(),syncMsgData);
				}
			}
		} else {
			//更新上阵卡牌的翅膀
			List<InstPlayerWing> instPlayerWingList = getInstPlayerWingDAL().getList(" instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
			if(instPlayerWingList.size() > 0){
				for(InstPlayerWing obj : instPlayerWingList){
					obj.setInstCardId(0);
					getInstPlayerWingDAL().update(obj, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, obj.getId(), obj.getResult(),syncMsgData);
				}
			}
			//更新下阵卡牌的翅膀
			List<InstPlayerWing> instPlayerWings = getInstPlayerWingDAL().getList(" instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerFormation.getInstCardId(), instPlayerId);
			if(instPlayerWings.size() > 0){
				for(InstPlayerWing obj : instPlayerWings){
					obj.setInstCardId(instPlayerCardId);
					getInstPlayerWingDAL().update(obj, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, obj.getId(), obj.getResult(),syncMsgData);
				}
			}
		}
		
		//更新上阵卡牌的功法法宝
		List<InstPlayerMagic> instPlayerMagicList = getInstPlayerMagicDAL().getList(" instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
		if(instPlayerMagicList.size() > 0){
			for(InstPlayerMagic obj : instPlayerMagicList){
				obj.setInstCardId(0);
				getInstPlayerMagicDAL().update(obj, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, obj.getId(), obj.getResult(),syncMsgData);
			}
		}
		
		//更新下阵卡牌的功法法宝
		List<InstPlayerMagic> instPlayerMagics = getInstPlayerMagicDAL().getList(" instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerFormation.getInstCardId(), instPlayerId);
		if(instPlayerMagics.size() > 0){
			for(InstPlayerMagic obj : instPlayerMagics){
				obj.setInstCardId(instPlayerCardId);
				getInstPlayerMagicDAL().update(obj, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, obj.getId(), obj.getResult(),syncMsgData);
			}
		}
		
		//更新下阵卡牌的上阵状态
		InstPlayerCard oldInstPlayerCard = getInstPlayerCardDAL().getModel(instPlayerFormation.getInstCardId(), instPlayerId);
		oldInstPlayerCard.setInTeam(StaticCustomDict.unTeam);
		getInstPlayerCardDAL().update(oldInstPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, oldInstPlayerCard, oldInstPlayerCard.getId(), oldInstPlayerCard.getResult(), syncMsgData);
		
		//更新上阵卡牌的上阵状态
		newInstPlayerCard.setInTeam(StaticCustomDict.inTeam);
		getInstPlayerCardDAL().update(newInstPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, newInstPlayerCard, newInstPlayerCard.getId(), newInstPlayerCard.getResult(), syncMsgData);
		
		//修改InstPlayerFormation中的instCardId
		instPlayerFormation.setInstCardId(instPlayerCardId);
		instPlayerFormation.setCardId(newInstPlayerCard.getCardId());
		getInstPlayerFormationDAL().update(instPlayerFormation, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFormation, instPlayerFormation.getId(), instPlayerFormation.getResult(),syncMsgData);
		
		//更新上阵卡牌装备的状态
		List<InstPlayerLineup> InstPlayerLineupList = getInstPlayerLineupDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerFormationId = " + instPlayerFormationId, instPlayerId);
		if(InstPlayerLineupList.size() > 0){
			for(InstPlayerLineup instPlayerLineup : InstPlayerLineupList){
				InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerLineup.getInstPlayerEquipId(), instPlayerId);
				instPlayerEquip.setInstCardId(instPlayerCardId);
				getInstPlayerEquipDAL().update(instPlayerEquip, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerEquip, instPlayerEquip.getId(), instPlayerEquip.getResult(),syncMsgData);
			}
		}
		
		//验证1人、5人称号成就
		AchievementUtil.title1(instPlayerId, syncMsgData);
		AchievementUtil.title5(instPlayerId, syncMsgData);

		//更新异火
		YFireUtil.updateFire(instPlayerId,oldInstPlayerCard,newInstPlayerCard,syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 小伙伴上阵
	 * @author mp
	 * @date 2014-7-3 上午11:34:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cardInPartner(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instCardId = (Integer)msgMap.get("instCardId");
		final int position = (Integer)msgMap.get("position");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instCardId, instPlayerId);
		
		//验证玩家是否一致
		if (instPlayerCard.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证此卡牌是否为上阵状态
		if(StaticCustomDict.inTeam == instPlayerCard.getInTeam() || StaticCustomDict.inPartner == instPlayerCard.getInTeam()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardInFormation);
			return;
		}
		
		//验证是否与上阵卡牌 或 小伙伴列表中卡牌 重复
		List<InstPlayerFormation> formationList = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId + " and cardId = " + instPlayerCard.getCardId(), instPlayerId);//要验证阵型是否已满[替补未确定,先不管]
		List<InstPlayerPartner> instPlayerPartnerList = getInstPlayerPartnerDAL().getList("instPlayerId = " + instPlayerId + " and cardId = " + instPlayerCard.getCardId(), instPlayerId);
		if (formationList.size() > 0 ||  instPlayerPartnerList.size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardRepeat);
			return;
		}
		
		//验证是否达到等级
		int openLevel = 0;
		List<DictLevelProp> levelPropList = DictList.dictLevelPropList;
		for (DictLevelProp obj : levelPropList) {
			if (obj.getPartnerCard() == position) {
				openLevel = obj.getId();
				break;
			}
		}
		if (instPlayer.getLevel() < openLevel) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		//更新卡牌上阵状态
		instPlayerCard.setInTeam(StaticCustomDict.inPartner);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		//判断该位置是否已有卡牌,没有添加,有则更换
		List<InstPlayerPartner> instPlayerPartnerPosList = getInstPlayerPartnerDAL().getList("instPlayerId = " + instPlayerId + " and position = " + position, instPlayerId);
		if (instPlayerPartnerPosList.size() <= 0) {
			
			//添加到小伙伴列表
			InstPlayerPartner instPlayerPartner = PlayerUtil.addPartner(instPlayerId, instCardId, instPlayerCard.getCardId(), position);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerPartner, instPlayerPartner.getId(), instPlayerPartner.getResult(), syncMsgData);
		} else {
			
			InstPlayerPartner instPlayerPartner = instPlayerPartnerPosList.get(0);
			
			//将原有卡牌上阵状态修改为不在小伙伴中
			InstPlayerCard oldInstPlayerCard = getInstPlayerCardDAL().getModel(instPlayerPartner.getInstCardId(), instPlayerId);
			oldInstPlayerCard.setInTeam(StaticCustomDict.unPartner);
			getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, oldInstPlayerCard, oldInstPlayerCard.getId(), oldInstPlayerCard.getResult(), syncMsgData);
			
			//更新卡牌Id
			instPlayerPartner.setInstCardId(instCardId);
			instPlayerPartner.setCardId(instPlayerCard.getCardId());
			getInstPlayerPartnerDAL().update(instPlayerPartner, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPartner, instPlayerPartner.getId(), instPlayerPartner.getResult(), syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 小伙伴下阵
	 * @author mp
	 * @date 2014-7-3 上午11:37:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cardOutPartner(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerPartnerId = (Integer)msgMap.get("instPlayerPartnerId");
		InstPlayerPartner instPlayerPartner = getInstPlayerPartnerDAL().getModel(instPlayerPartnerId, instPlayerId);
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerPartner.getInstCardId(), instPlayerId);
		
		//验证玩家是否一致
		if (instPlayerPartner.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证参数是否在小伙伴列表中
		if (instPlayerCard.getInTeam() != StaticCustomDict.inPartner) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		//从小伙伴列表中移除
		getInstPlayerPartnerDAL().deleteById(instPlayerPartnerId, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerPartner, instPlayerPartner.getId(), "", syncMsgData);
		
		//修改卡牌上阵状态为0
		instPlayerCard.setInTeam(StaticCustomDict.unPartner);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 聊天
	 * @author mp
	 * @date 2014-8-28 上午11:47:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void chat(Map<String, Object> msgMap, String channelId) throws Exception {
		
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//获取参数
		int channelType = (Integer)msgMap.get("channelType");//聊天类型：1-世界, 2-联盟, 3-私聊
		String content = (String)msgMap.get("content");//聊天内容
		String chatObjName = (String)msgMap.get("chatObjName");//私聊时,聊天对象的角色名
		String otherSendInfo = (String)msgMap.get("otherSendInfo");//其他信息：头像卡牌Id_角色名_vip等级_战队等级_战力_所属联盟
		
		Player currPlayer = PlayerMapUtil.getPlayerByChannelId(channelId); 
		
		//判断此玩家的禁言状态
		if (currPlayer != null && currPlayer.getOnlineState() == 3) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_talkForbid);
			return;
		}
		
		//聊天内容不可超过50字
		if (content.length() > 50) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_chatLength);
			return;
		}
		
		//过滤敏感字符
		content = WordFilterUtil.simpleFilter(content, '*');
		
		//组织转发对象
		List<String> channelIdList = Lists.newArrayList();
		
		if (channelType == 1) {
			//当为世界聊天时,判断cd是否超过指定时间
			if (currPlayer.getWorldChatTime() != 0) {
				if ((DateUtil.getCurrMill() - currPlayer.getWorldChatTime()) <= (ParamConfig.chatCd * 1000)) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_chatNoCd);
					return;
				}
			}
			currPlayer.setWorldChatTime(DateUtil.getCurrMill());
			for(Entry<String, Player> map : PlayerMapUtil.getMap().entrySet()){
				String playerChannelId = map.getKey();
				Player player = map.getValue();
				if (player != null) {
					channelIdList.add(playerChannelId);
				}
			}
		} else if (channelType == 2) {
			
			if (currPlayer.getUnionChatTime() != 0) {
				if ((DateUtil.getCurrMill() - currPlayer.getUnionChatTime()) <= (ParamConfig.chatCd * 1000)) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_chatNoCd);
					return;
				}
			}
			currPlayer.setUnionChatTime(DateUtil.getCurrMill());
			
			
//			String unionName = otherSendInfo.split("_")[5];
			int instUnionId = Integer.valueOf(otherSendInfo.split("\\|")[5]);
			
			//是否存在联盟
//			if (unionName == null || unionName.equals("")) {
//				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_chatNoUnion);
//				return;
//			}
			//根据联盟名字获取所在联盟Id
//			List<InstUnion> instUnionList = getInstUnionDAL().getList("name = '"+unionName+"'", 0);
//			if (instUnionList.size() <= 0) {
//				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_chatNoUnion);
//				return;
//			}
//			int instUnionId = instUnionList.get(0).getId();
			//根据联盟id获取联盟成员列表
			List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList("instUnionId = " + instUnionId, 0);
			if (instUnionMemberList.size() <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_chatNoUnion);
				return;
			}
			
			for (InstUnionMember instUnionMember : instUnionMemberList) {
				Player player = PlayerMapUtil.getPlayerByPlayerId(instUnionMember.getInstPlayerId());
				if (player != null) {
					channelIdList.add(player.getChannelId());
				}
			}
		} else if (channelType == 3) {
			//聊天对象为自己
			if (currPlayer.getPlayerName().equals(chatObjName)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_chatNoMyself);
				return;
			}
			//聊天对象不在线
			Player player = PlayerMapUtil.getPlayerByPlayerName(chatObjName);
			if (player == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_chatNoOnline);
				return;
			}
			channelIdList.add(player.getChannelId());
			channelIdList.add(channelId);
		}
		
		//组织数据对象
		MessageData chatMsgData = new MessageData();
		chatMsgData.putIntItem("1", channelType);//聊天类型：1-世界, 2-联盟, 3-私聊
		chatMsgData.putStringItem("2", content);//聊天内容
		int instUnionId = Integer.valueOf(otherSendInfo.split("\\|")[5]);
		
		String unionName = "";
		if (ParamConfig.unionMap.containsKey(instUnionId)) {
			unionName = ParamConfig.unionMap.get(instUnionId);
		}
		chatMsgData.putStringItem("3", otherSendInfo + "|" + unionName);//其他信息：头像卡牌Id_角色名_vip等级_战队等级_战力_所属联盟_联盟名字_玩家Id
		chatMsgData.putStringItem("4", chatObjName);
		
		//发送消息
		MessageUtil.pushMsg(channelIdList, StaticMsgRule.pushChatData, chatMsgData);
	}
	
	/**
	 * 体力恢复
	 * @author mp
	 * @date 2014-9-19 上午10:34:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void energyRecover(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//结算体力
		PlayerUtil.settleEngery(instPlayer, syncMsgData, retMsgData);
		
		if (syncMsgData.getMsgData().getMessageItemCount() != 0) {
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 耐力恢复
	 * @author mp
	 * @date 2014-9-22 下午4:14:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void vigorRecover(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//结算耐力
		PlayerUtil.settleVigor(instPlayer, syncMsgData, retMsgData);
		
		if (syncMsgData.getMsgData().getMessageItemCount() != 0) {
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 快速升级（战队）
	 * @author hzw
	 * @date 2014-9-29下午3:04:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void quickUpgrade(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//开发环境是开着的，其他不验证
		String env = SysConfigUtil.getValue("env");
		if (env.equals("0")) {
			//获取参数
			MessageData syncMsgData = new MessageData();
			MessageData retMsgData = new MessageData();
			int instPlayerId = getInstPlayerId(channelId);
			
			if (instPlayerId == 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
				return;
			}
			
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			
			DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
			FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + dictLevelProp.getFleetExp(), syncMsgData, msgMap);
			
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
			MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		}
		
	}
	
	/**
	 * 引导步骤
	 * @author mp
	 * @date 2014-10-25 下午3:25:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void guidStep(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		
		int currLevelOrBarrier = 0;
		int currStep = 0;
		int type = 0;
		String givedLevelGuidStep = "";
		
		//处理等级相关的引导
		if (step.contains("_")) {
			type = 1;
			currLevelOrBarrier = ConvertUtil.toInt(((String)msgMap.get("step")).split("_")[0]);
			currStep = ConvertUtil.toInt(((String)msgMap.get("step")).split("_")[1]);
			if(instPlayer.getGuidStep().substring(instPlayer.getGuidStep().indexOf('&') + 1, instPlayer.getGuidStep().length()).length() > 1){
				givedLevelGuidStep = instPlayer.getGuidStep().split("&")[1];
			}
		} else {
			//处理关卡相关的引导
			type = 2;
			currLevelOrBarrier = ConvertUtil.toInt(((String)msgMap.get("step")).split("B")[0]);//客户端传来的关卡
			currStep = ConvertUtil.toInt(((String)msgMap.get("step")).split("B")[1]);
			
			//判断客户端传来的步骤不能小于当前步骤
			if(instPlayer.getGuipedBarrier().substring(0, instPlayer.getGuipedBarrier().indexOf('&')).length() > 1){
				String record = instPlayer.getGuipedBarrier().split("&")[0];
				int recordBarrier = ConvertUtil.toInt(record.split("B")[0]);
				
//				记录关卡和客户端传的关卡号如果相隔大于1
//				System.out.println("recordBarrier=" + recordBarrier + "   currLevelOrBarrier=" + currLevelOrBarrier);
				if (recordBarrier - currLevelOrBarrier > 1) {
					MessageUtil.sendFailMsg(channelId, msgMap, "");
					return;
				}
				
//				考虑到客户端引导会回滚,不采用以下方式
//				int recordStep = ConvertUtil.toInt(record.split("B")[1]);
//				//传入关卡小于记录关卡
//				if (currLevelOrBarrier < recordBarrier) {
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_step);
//					return;
//				//传入关卡等于记录关卡时,判断关卡步骤
//				} else if (currLevelOrBarrier == recordBarrier) {
//					if (currStep <= recordStep) {
//						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_step);
//						return;
//					}
//				}
			}
			
			
			if(instPlayer.getGuipedBarrier().substring(instPlayer.getGuipedBarrier().indexOf('&') + 1, instPlayer.getGuipedBarrier().length()).length() > 1){
				givedLevelGuidStep = instPlayer.getGuipedBarrier().split("&")[1];
			}
		}
		
		//需要时,送物品
		int ifSync = 0;
		for (DictGuipStep obj : DictList.dictGuipStepList) {
			if (obj.getType() == type && obj.getLevelOrBarrierId() == currLevelOrBarrier && obj.getStep() == currStep) {
				
				//验证此等级步骤是否已经送过物品[初始化玩家的时候,将'&',写入]
				if (StringUtil.contains(givedLevelGuidStep, step, ";")) {
					MessageUtil.sendFailMsg(channelId, msgMap, "");
					return;
				}
				
				ifSync++;
				
				String thingString = obj.getThings();
				for (String thing : thingString.split(";")) {
					int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
					int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
					int tableValue = ConvertUtil.toInt(thing.split("_")[2]);
					ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, tableValue, syncMsgData, msgMap);
				}
				
				//记录此给物品的等级和步骤
				givedLevelGuidStep = givedLevelGuidStep + step + ";";
			}
		}
		
		String guidStep = step + "&" + givedLevelGuidStep;

		//记录步骤
		if (type == 1) {
			instPlayer.setGuidStep(guidStep);
		} else {
			instPlayer.setGuipedBarrier(guidStep);
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		if(ifSync != 0){
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
		}
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 断线重连  - 这里仅限于重新连接和登录的含义不一样
	 * @author mp
	 * @date 2014-11-5 上午9:31:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void relink (Map<String, Object> msgMap, String channelId) throws Exception {
		
		String openId = (String)msgMap.get("openId");
		String channel = (String)msgMap.get("channel");
		if (channel == null) {
			channel = "";
		}
		
		//对接登录服相关的
		String token = (String)msgMap.get("token");
		String zoneid = (String)msgMap.get("zoneid");
		String aloneServerId = (String)msgMap.get("aloneServerId");//独服Id
		String accountId = "";
		String env = SysConfigUtil.getValue("env");
		String session_id = "";
		String session_type  = "";
		String pay_token = "";
		String loginServer = "";
		String channel_sub = "";

		//判断环境[0-内网   非0-外网], 外网时需验证
		if (!env.equals("0")) {
			if (channel.equals("qq")) {
				pay_token = (String)msgMap.get("pay_token");
				session_id = "openid";
				session_type  = "kp_actoken";
			} else if (channel.equals("wx")) {
				session_id = "hy_gameid";
				session_type  = "wc_actoken";
			} else {
				String loginServerUrl = SysConfigUtil.getValue("login.server.url");
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("security_code", token);
				paramMap.put("server_id", zoneid);
				paramMap.put("product_id", aloneServerId);
				String params = HttpClientUtil.httpBuildQuery(paramMap);
				String loginResult = HttpClientUtil.postMapSubmit(loginServerUrl, params);
				JSONObject loginJson = new JSONObject(loginResult);
				String retMsg = String.valueOf(loginJson.get("status"));
				if (!retMsg.equals("1")) {
					MessageUtil.sendFailMsg(channelId, msgMap, ":");
					return ;
				}
				channel = String.valueOf(loginJson.get("channel_mark"));
				openId =  String.valueOf(loginJson.get("user_id"));
				accountId =  String.valueOf(loginJson.get("accountId"));
				loginServer = String.valueOf(loginJson.get("loginServer"));
				channel_sub = String.valueOf(loginJson.get("channel_sub"));
				if (channel.equals("yijie")) {
					if (channel_sub.equals("com.doupo.zhangyue.zy")) {
						channel = "zhangyue";
					} else if (channel_sub.equals("com.doupo.zhuoyi.zy")) {
						channel = "zhuoyi";
					} else {
						try {
							channel = channel_sub.substring(channel_sub.lastIndexOf(".") + 1, channel_sub.length());
						} catch (Exception e) {
							LogUtil.error("子渠道表示错误", e);
						}
					}
				} else if (channel.equals("anysdk")) {
					if (channel_sub.equals("com.tongbu.doupo.y2game")) {
						channel = "tongbu";
					} else {
						try {
							channel = channel_sub.substring(channel_sub.lastIndexOf(".") + 1, channel_sub.length());
						} catch (Exception e) {
							LogUtil.error("子渠道表示错误", e);
						}
					}
				}else if (channel.equals("yiyou")) {
					try {
						channel = channel_sub.substring(channel_sub.lastIndexOf(".") + 1, channel_sub.length());
					} catch (Exception e) {
						LogUtil.error("子渠道表示错误", e);
					}
				} else if (channel.equals("iosy2game")) {
					try {
						channel = "dpcq";
					} catch (Exception e) {
						LogUtil.error("子渠道表示错误", e);
					}
				}
			}
			openId = openId + "@" + channel;
		}

		//验证是否已经登录,处理账号异地登录逻辑
		if(PlayerMapUtil.containsUniquePlayer(openId)){
			Player oldPlayer = PlayerMapUtil.getUniquePlayer(openId);
			MessageUtil.sendFailMsg(oldPlayer.getChannelId(), msgMap, StaticCnServer.fail_openIdOtherLogin);
			TimeUnit.MILLISECONDS.sleep(200);
			Channel oldChannel = ChannelMapUtil.getByChannelId(oldPlayer.getChannelId());
			if(oldChannel != null){
				oldChannel.close();
			}
			TimeUnit.MILLISECONDS.sleep(200);
		}
		
		//账号是否已经存在，不存在创建（含玩家），返回玩家信息
		List<InstUser> userList = getInstUserDAL().getList(" openId = '" + openId + "'", 0);
		InstPlayer instPlayer = getInstPlayerDAL().getList(" openId = '" + openId + "'", 0).get(0);
		
		InstUser instUser = userList.get(0);
		
		// 开服礼包登录处理-在修改玩家登录时间之前，如果跨天处理开服礼包逻辑
		ActivityUtil.loginInstActivityOpenServiceBag(instPlayer.getId(), instUser);

		//更新用户账号信息
		UserUtil.updateUserInfo(instUser);
		
		//绑定Socket中的Channel 和 Player
		PlayerUtil.bindSocketPlayer(channelId, channel, openId, instPlayer, msgMap, session_id, session_type, pay_token, userList.get(0).getOnlineState(), accountId, loginServer, aloneServerId, channel_sub);
		
		//断线重连时,记录角色登录日志
		try {
			String logContent = PlayerMapUtil.getPlayerByChannelId(channelId).getLogHeader() + "|元宝|" + instPlayer.getGold() + "|银币|" + instPlayer.getCopper();
			LogicLogUtil.info("roleLogin", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("角色登录日志错误", e);
		}
		
		try {
			if (instPlayer != null) {
				PlayerActivityManager.getInstance().loadPlayerActivities(instPlayer.getId());
				TotalCostManager.getInstance().loadPlayerActivities(instPlayer.getId());
			}
		} catch (Exception e) {
			LogUtil.error("加载累计消耗时异常", e);
		}
		
		MessageUtil.sendSuccMsg(channelId, msgMap);
		
	}
	
	/**
	 * 起名字
	 * @author mp
	 * @date 2014-11-7 上午10:15:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void giveName (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		String name = (String)msgMap.get("name");
		name = name.trim();//默认给玩家去前后空格
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//如果玩家有名字，不在走起名字逻辑
		if (instPlayer.getName() != null && !instPlayer.getName().equals("")) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveName);
			return ;
		}
		
		//验证名字是否含有敏感字符
		if(WordFilterUtil.filterText(name, '*').getBadWords().length() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_filtername);
			return ;
		}
		
		//验证名字是否含特殊字符 类似空格 
		if(name.contains(" ") || name.contains("\\") || name.contains("/") || name.contains("|") || name.contains("\"") || name.contains("\'") || name.contains(";'")){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_playerName_Rule);
			return ;
		}
		
		//验证是否已有此名字
		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("name = '" + name + "'", instPlayerId);//不区分大小写，只要字母一样就不行，不管你大写小写[相对来说更为严格了]
		if(instPlayerList.size() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_havePlayer);
			return ;
		}
		
		//验证竞技场NPC里边是否有此名字
		for (Entry<Integer, InstPlayerArenaNPC> entry : NPCPlayerUtil.instPlayerArenaNPCMap.entrySet()) {
			InstPlayerArenaNPC instPlayerArenaNPC = entry.getValue();
			if (instPlayerArenaNPC.getName().equals(name)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_havePlayer);
				return ;
			}
		}
		
		//更新数据库
		instPlayer.setName(name);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//设置内存Player对象中的玩家名
		Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
		player.setPlayerName(name);
		
		//设置全局玩家名字
		ParamConfig.playerNameMap.put(instPlayerId, name);
		
		List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		int bs = 0;
		if (instPlayerCardList.size() <= 0) {
			
			bs++;
			
			//初始时只有一个萧炎, 没有选人界面了
			int [] initCards = {154};
			int index = 1;
			
			for (int cid : initCards) {
				
				InstPlayerCard instCard = null;
				
				//初始化卡牌实例表
				instCard = CardUtil.initPlayerCard(instPlayerId, cid, 1);
				
				//初始化玩家阵型表
				CardUtil.initPlayerFormation(instPlayerId, instCard, index, 1);
				
				//初始化玩家卡牌命宫实例表
				DictCard dictCard = DictMap.dictCardMap.get(instCard.getCardId() + "");
				if (!dictCard.getConstells().equals("")) {
					String constells[] = dictCard.getConstells().split(";");
					String instPlayerConstells = "";
					for(String constell : constells){
						DictConstell dictConstell = DictMap.dictConstellMap.get(ConvertUtil.toInt(constell) + "");
						InstPlayerConstell obj = PillUtil.initPlayerConstell(instPlayerId, instCard.getId(), ConvertUtil.toInt(constell), dictConstell.getPills().split(";").length);
						instPlayerConstells += obj.getId() + ";";
					}
					instCard.setInstPlayerConstells(instPlayerConstells.substring(0, instPlayerConstells.length() - 1));
					getInstPlayerCardDAL().update(instCard, instPlayerId);
				}
				index++;
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		
		//组织前端数据
		if (bs != 0) {
			
//			StaticActivity.hJYStore
			
			//初始化成就
			PlayerUtil.initAchievement(instPlayer);
			
			//初始化爬塔
			PagodaUtil.initInstPlayerPagoda(instPlayer.getId());
			
			//初始化每日任务
			PlayerUtil.initDailyTask(instPlayer);
			
			//初始化默认抢夺
			PlayerUtil.initInstPlayerLoot(instPlayer.getId());
			
			//初始化限时抢购
			PlayerUtil.initInstActivity(instPlayer.getId());
			
			//初始化活动
			ActivityUtil.addInstActivity(instPlayer.getId(), StaticActivity.addRecharge, new MessageData());
			
			//初始化在线奖励活动
			SysActivity activity = DictMap.sysActivityMap.get(StaticActivity.onlineRewards + "");
			if(activity.getIsForevery() == 1 || (DateUtil.getMillSecond(DateUtil.getCurrTime()) >= DateUtil.getMillSecond(activity.getStartTime()) && DateUtil.getMillSecond(DateUtil.getCurrTime()) <= DateUtil.getMillSecond(activity.getEndTime()))){ 
				ActivityUtil.addInstActivityOnlineRewards(instPlayer.getId());
			}
			
			//初始化开服礼包
			SysActivity openServiceBag = DictMap.sysActivityMap.get(StaticActivity.openServiceBag + "");
			if(openServiceBag.getIsForevery() == 1 || (DateUtil.getMillSecond(DateUtil.getCurrTime()) >= DateUtil.getMillSecond(openServiceBag.getStartTime()) && DateUtil.getMillSecond(DateUtil.getCurrTime()) <= DateUtil.getMillSecond(openServiceBag.getEndTime()))){ 
				ActivityUtil.addInstActivityOpenServiceBag(instPlayer.getId());
			}
			
			MessageData retMsgData = OrgFrontMsgUtil.retCardMsgData(instPlayerId);
			MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		} else {
			MessageUtil.sendSuccMsg(channelId, msgMap);
		}
	}
	
	/**
	 * 洗澡恢复体力
	 * @author mp
	 * @date 2014-12-18 下午2:52:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wash (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		long currTime = DateUtil.getCurrMill();
		long firstWashStartTime = DateUtil.getMillSecond(DateUtil.getYmdDate() + " " + ParamConfig.firstWashStartTime);
		long firstWashEndTime = DateUtil.getMillSecond(DateUtil.getYmdDate() + " " + ParamConfig.firstWashEndTime);
		long lastWashStartTime = DateUtil.getMillSecond(DateUtil.getYmdDate() + " " + ParamConfig.lastWashStartTime);
		long lastWashEndTime = DateUtil.getMillSecond(DateUtil.getYmdDate() + " " + ParamConfig.lastWashEndTime);
		long washTime = 0;
		if(instPlayer.getWashTime() != null && !instPlayer.getWashTime().equals("")){
			washTime = DateUtil.getMillSecond(instPlayer.getWashTime());
		}
		
		//在第一次洗澡时间内
		if (currTime >= firstWashStartTime && currTime <= firstWashEndTime) {
			if (washTime != 0) {
				//验证是否已洗
				if (washTime >= firstWashStartTime && washTime <= firstWashEndTime) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveWash);
					return ;
				}
			}
		//在第二次洗澡时间内
		} else if (currTime >= lastWashStartTime && currTime <= lastWashEndTime) {
			if (washTime != 0) {
				//验证是否已洗
				if (washTime >= lastWashStartTime && washTime <= lastWashEndTime) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveWash);
					return ;
				}
			}
		//非洗澡时间
		} else {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noWashTime);
			return ;
		}
		
		//更新体力和洗澡时间
		instPlayer.setEnergy(instPlayer.getEnergy() + DictMapUtil.getSysConfigIntValue(StaticSysConfig.washReceiveEngery));
		instPlayer.setWashTime(DateUtil.getTimeByMill(currTime));
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		MessageData syncMsgData = new MessageData();
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 每日任务领奖
	 * @author mp
	 * @date 2014-12-27 下午5:27:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dailyTashReward (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerDailyTaskId = (int)msgMap.get("instPlayerDailyTaskId");//每日任务表实例Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		String dailyTaskTime = instPlayer.getDailyTashTime();
		InstPlayerDailyTask instPlayerDailyTask = getInstPlayerDailyTaskDAL().getModel(instPlayerDailyTaskId, instPlayerId);
		
		//验证玩家是否一致
		if (instPlayerDailyTask.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证时间是否为当天
		if (!dailyTaskTime.equals(DateUtil.getYmdDate())) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noCommonTime);
			return;
		}

		//验证是否达到任务完成次数
		DictDailyTask dictDailyTask = DictMap.dictDailyTaskMap.get(instPlayerDailyTask.getDailyTashkId() + "");
		int finishTimes = dictDailyTask.getTimes();
		if (dictDailyTask.getId() < StaticDailyTask.box1 && finishTimes != instPlayerDailyTask.getTimes()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noFinishTimes);
			return;
		}
		
//		if (dictDailyTask.getId() >= StaticDailyTask.box1) {
// 			List<Map<String, Object>> dailyTaskMap = getInstPlayerDailyTaskDAL().sqlHelper("SELECT sum(a.plan) as plan from Dict_DailyTask a, Inst_Player_DailyTask b where a.id = b.dailyTashkId and b.times >= a.times and instPlayerId = " + instPlayerId);
//			int plan = Integer.valueOf(dailyTaskMap.get(0).get("plan").toString());
//			if(plan < dictDailyTask.getPlan()){
//				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noFinishTimes);
//				return;
//			}
//		}
		
		//验证是否已经领过
		if (instPlayerDailyTask.getRewardState() == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveWash);
			return;
		}
		
		//领取奖励
		String rewardList = dictDailyTask.getRewards();
		MessageData syncMsgData = new MessageData();
		for (String reward : rewardList.split(";")) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(reward.split("_")[0]), ConvertUtil.toInt(reward.split("_")[1]), ConvertUtil.toInt(reward.split("_")[2]), syncMsgData, msgMap);
		}
		
		//更新领奖状态
		instPlayerDailyTask.setRewardState(1);
		getInstPlayerDailyTaskDAL().update(instPlayerDailyTask, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerDailyTask, instPlayerDailyTask.getId(), instPlayerDailyTask.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 玩家选择头像
	 * @author mp
	 * @date 2015-1-15 下午5:24:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void selectHeader (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int cardId = (int)msgMap.get("cardId");//卡牌字典表Id
		MessageData syncMsgData = new MessageData();
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//更新头像CardId
		instPlayer.setHeaderCardId(cardId);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 成就领奖
	 * @author hzw
	 * @date 2015-1-22上午11:24:19
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void achievementReward (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerAchievementId = (int)msgMap.get("instPlayerAchievementId");//成就实例Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getModel(instPlayerAchievementId, instPlayerId);
		
		//验证玩家是否一致
		if (instPlayerAchievement.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		//验证成就是否可领取
		if (instPlayerAchievement.getCanAchievementId() == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noAchievement);
			return;
		}
		
		DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getCanAchievementId() + "");
		//领取奖励
		String rewardList = dictAchievement.getRewards();
		MessageData syncMsgData = new MessageData();
		for (String reward : rewardList.split(";")) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(reward.split("_")[0]), ConvertUtil.toInt(reward.split("_")[1]), ConvertUtil.toInt(reward.split("_")[2]), syncMsgData, msgMap);
		}
		
		//更新可领取状态
		instPlayerAchievement.setCanAchievementId(0);
		getInstPlayerAchievementDAL().update(instPlayerAchievement, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerAchievement, instPlayerAchievement.getId(), instPlayerAchievement.getResult(), syncMsgData);
		
		//更新成就
		int achievementTypeId = instPlayerAchievement.getAchievementTypeId();
		if(instPlayerAchievement.getAchievementTypeId() == StaticAchievementType.pcLevel){
			AchievementUtil.updateAchievement(instPlayer.getId(), achievementTypeId, instPlayer.getLevel(), syncMsgData, null);
		}else if(achievementTypeId == StaticAchievementType.chapter || achievementTypeId == StaticAchievementType.hJYStore || achievementTypeId == StaticAchievementType.arena 
				|| achievementTypeId == StaticAchievementType.worldBoss){
			InstPlayerAchievementValue instPlayerAchievementValue = getInstPlayerAchievementValueDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + achievementTypeId, instPlayerId).get(0);
			AchievementUtil.updateAchievement (instPlayerId, achievementTypeId, instPlayerAchievementValue.getValue(), syncMsgData, null);
		}
		/*else if(achievementTypeId == StaticAchievementType.wash){
			AchievementUtil.wash(instPlayerId, syncMsgData);
		}*/
		else if(achievementTypeId == StaticAchievementType.strengthen){
			AchievementUtil.strengthen(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.addEquip){
			AchievementUtil.addEquip(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.inlay){
			AchievementUtil.inlay(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.magic1){
			AchievementUtil.magic1(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.magic2){
			AchievementUtil.magic2(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.pagoda){
			InstPlayerPagoda instPlayerPagoda = getInstPlayerPagodaDAL().getList("instPlayerId =  " + instPlayerId, instPlayerId).get(0);
			AchievementUtil.updateAchievement(instPlayer.getId(), achievementTypeId, instPlayerPagoda.getMaxPagodaStoreyId(), syncMsgData, null);
		}else if(achievementTypeId == StaticAchievementType.addPill){
			DictAchievement dictAchievement2 = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			InstPlayerAchievementValue instPlayerAchievementValue = getInstPlayerAchievementValueDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + achievementTypeId, instPlayerId).get(0);
			if(ConvertUtil.toInt(dictAchievement2.getConditions()) != 1){
				AchievementUtil.updateAchievementValue(instPlayerId, StaticAchievementType.addPill, -instPlayerAchievementValue.getValue(), syncMsgData);
			}else{
				AchievementUtil.updateAchievementValue(instPlayerId, StaticAchievementType.addPill, instPlayerAchievementValue.getValue(), syncMsgData);
			}
		}else if(achievementTypeId == StaticAchievementType.title1){
			AchievementUtil.title1(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.title5){
			AchievementUtil.title5(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.advance){
			AchievementUtil.advance(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.purpleEquip){
			AchievementUtil.purpleEquip(instPlayerId, syncMsgData);
		}else if(achievementTypeId == StaticAchievementType.barrier){
			dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			
			if (dictAchievement == null) {
				instPlayerAchievement.setCanAchievementId(0);
				getInstPlayerAchievementDAL().update(instPlayerAchievement, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerAchievement, instPlayerAchievement.getId(), instPlayerAchievement.getResult(), syncMsgData);
				
			} else {
				List<InstPlayerChapter> instPlayerChapterList = getInstPlayerChapterDAL().getList("instPlayerId = " + instPlayerId + " and chapterId = " + dictAchievement.getConditions(), instPlayerId);
				if(instPlayerChapterList.size() > 0){
					AchievementUtil.barrier(instPlayerId, instPlayerChapterList.get(0), syncMsgData);
				}
			}
			
		}else if(achievementTypeId == StaticAchievementType.vip){
			AchievementUtil.updateAchievement(instPlayer.getId(), achievementTypeId, instPlayer.getVipLevel(), syncMsgData, null);
		}
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 随机获取名字
	 * @author mp
	 * @date 2015-1-22 下午2:23:43
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void randomName (Map<String, Object> msgMap, String channelId) throws Exception {

		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//随机获取名字[在登录的时候也有-用于初始起名字]
		String randomName = NameUtil.getRandomName();

		//将名字返回给客户端
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", randomName);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 领取vip礼包
	 * @author mp
	 * @date 2015-1-28 下午3:26:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getVipGiftBag (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int vipId = (int)msgMap.get("vipId");//VIP字典表Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//验证vip等级是否达到
		DictVIP vip = DictMap.dictVIPMap.get(vipId + "");
		if (instPlayer.getVipLevel() < vip.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_vipLevel);
			return;
		}

		//验证是否已领
		String vipIds = instPlayer.getVipIds();
		if (StringUtil.contains(vipIds, vipId + "", ";")) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveWash);
			return;
		}

		//领取礼包
		MessageData syncMsgData = new MessageData();
		for (String giftThings : vip.getGiftBagThings().split(";")) {
			int tableTypeId = ConvertUtil.toInt(giftThings.split("_")[0]);
			int tableFiledId = ConvertUtil.toInt(giftThings.split("_")[1]);
			int value = ConvertUtil.toInt(giftThings.split("_")[2]);
			ThingUtil.groupThing(instPlayer, tableTypeId, tableFiledId, value, syncMsgData, msgMap);
		}
		
		//更新玩家表vipId
		if(vipIds != null && !vipIds.equals("")){
			instPlayer.setVipIds(vipIds+ ";" + vipId );
		}else{
			instPlayer.setVipIds(vipId + "");
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 随机起名字
	 * @author mp
	 * @date 2015-3-25 下午4:40:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void authCode (Map<String, Object> msgMap, String channelId) throws Exception {

		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//随机获取名字[在登录的时候也有-用于初始起名字]
		String randomName = NameUtil.getRandomName();

		//将名字返回给客户端
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", randomName);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 充值回调
	 * @author mp
	 * @date 2015-3-25 下午4:43:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 *   1.腾讯给出的解说-关于到账延迟
		        因为有些渠道的扣费有延时  所以需要在2分钟内多次调用查询余额接口（建议间隔时间20秒）   直到确认到账
		        如果出现网络问题或者延迟而没有查询到导致不到账 在下一次登录成功再刷新一次余额即可
		 2. 支付回调信息sdk解说-应用侧先检查resultCode，如果为PAYRESULT_SUCC则可以继续检查是否支付成功和发货成功，否则表明用户未支付或者支付出错。
		 
		 当返回结果resultCode为成功,并且支付状态payState为成功的情况下,调用查询余额接口验证是否到账,确认到账后给玩家发游戏币,如果到账延迟,放入延迟队列,由固定的一个线程每隔20s重新调用一次，
		 如果两分钟内还没到账,将此任务从队列中去除,等玩家下次登录时再去看是否到账,如还没到账，那就没天理了，腾讯说2分钟内能到账的
		 如果每隔20s调用，发现到账了,把元宝加到玩家身上,判断vip逻辑,如果玩家在线,还需要通知玩家
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void rechargeCallBack (Map<String, Object> msgMap, String channelId) throws Exception {/*
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int operBeforeGoldNum = instPlayer.getGold();
		Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
		String playerName = player.getPlayerName();
		final String openId = player.getOpenId();
		String channel = player.getChannel();
		
		int resultCode = (int)msgMap.get("resultCode");//结果码
		int payChannel = (int)msgMap.get("payChannel");//支付渠道
		int payState = (int)msgMap.get("payState");//支付状态
		int saveNum = (int)msgMap.get("saveNum");//下单成功时购买的数量(游戏币)
		int rechargeId = (int)msgMap.get("rechargeId");//充值字典表Id
		int saveAmt = 0;
		int lastSaveAmt = 0;
		Map<String, Object> retMap = null; 
		MessageData syncMsgData = new MessageData();
		
		try {
			//充值回调结果日志记录
			PlayerUtil.rechargeCallBackLog(msgMap, instPlayerId, playerName, openId);
			
			//支付流程成功 并且 支付状态成功/未知
			if ((resultCode == 0 && payState == 0) || (resultCode == 0 && payState == -1)) {
				
				//初始化月卡活动实例数据（这里只是初始，只有在确认支付以后才会让实例数据生效）
				DictRecharge recharge = DictMap.dictRechargeMap.get(rechargeId  + "");
				if( recharge.getFirstAmt() == 0){
					ActivityUtil.initInstActivity(instPlayerId, StaticActivity.monthCard, syncMsgData, rechargeId);
				}
				
				retMap = SDKUtil.get_balance_m(openId);
				if (retMap != null &&  (Double)retMap.get("ret") == 0) {
					saveAmt = (int)(double)retMap.get("save_amt");//腾讯端累计充值金额（元宝）
					
					//倒叙排序
					List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
					Collections.sort(instPlayerRechargeList, new Comparator<Object>() {
						public int compare(Object a, Object b) {
							int one = ((InstPlayerRecharge)a).getSaveamt();
							int two = ((InstPlayerRecharge)b).getSaveamt();
							return (int)(two - one); 
						}
					}); 
					
					if (instPlayerRechargeList.size() > 0) {
						lastSaveAmt = instPlayerRechargeList.get(0).getSaveamt();//数据库中存储的上次累计充值金额
					}
					if (saveAmt > lastSaveAmt) {
						int thisamt = saveAmt - lastSaveAmt;//本次充值金额(游戏币)
						int thisrmb = thisamt / 10;//本次充值金额(人民币)
						int thisGold = 0;
						//月卡跟普通充值得到的元宝规则不一致
						if(thisrmb == recharge.getRmb() && recharge.getFirstAmt() == 0){
							thisGold = thisrmb * DictMapUtil.getSysConfigIntValue(StaticSysConfig.monthCardTimes);
							instPlayer.setGold(instPlayer.getGold() + thisGold);
							
							//确认月卡实例数据生效
							List<InstActivity> instActivityList = getInstActivityDAL().getList(" instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and useNum = " + recharge.getId() + " and (activityTime = \"\" or activityTime is null)", instPlayerId);
							ActivityUtil.addInstActivity(instPlayerId, instActivityList, syncMsgData, 1);
						}else{
							//处理额外赠送元宝（首充/非首充/没有这一档）
							int freeGold = PlayerUtil.getFreeGold(thisrmb, instPlayerRechargeList, openId);
							
							//处理元宝  赠送的元宝 + 充值的元宝
							thisGold = freeGold + thisamt;
							instPlayer.setGold(instPlayer.getGold() + thisGold);
						}
						
						
						//处理vip逻辑-根据总充值rmb金额,计算vip等级
						int vipLevel = PlayerUtil.getVipLevel(instPlayer, saveAmt / 10);
						instPlayer.setVipLevel(vipLevel);
						
						getInstPlayerDAL().update(instPlayer, instPlayerId);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
						
						//更新玩家成就计数实例数据
						AchievementUtil.updateAchievement (instPlayerId, StaticAchievementType.vip, vipLevel, syncMsgData, null);
						
						//记录充值日志
						PlayerUtil.addInstPlayerRecharge(retMap, channel, instPlayerId, openId, payChannel, playerName, saveAmt, saveNum, thisamt, thisrmb, "充值");
						
						int operAfterGoldNum = instPlayer.getGold();
						PlayerUtil.addInstPlayerGoldStatics(instPlayerId, GoldStaticsType.add, thisGold, 0, "充值(含赠送游戏币)", operBeforeGoldNum, operAfterGoldNum, "", "", "");
						
						MessageUtil.sendSyncMsg(channelId, syncMsgData);
					} else {
						//暂未到账,放入延迟队列
						DelayRechargePlayer delayRechargePlayer = PlayerUtil.orgDelayRechargePlayer(channel, instPlayerId, openId, payChannel, playerName, saveNum);
						ParamConfig.blockingQueue.put(delayRechargePlayer);
						
						if (syncMsgData.getMsgData().getMessageItemCount() > 0) {
							MessageUtil.sendSyncMsg(channelId, syncMsgData);
						}
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_recharge_queue);
						return;
					}
				} else {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_reLogin);
					return;
				}
			}
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_reLogin);
			return;
		}
		
		MessageUtil.sendSuccMsg(channelId, msgMap);
	*/}
	
	/**
	 * 查看当前累计充值元宝
	 * @author mp
	 * @date 2015-3-26 上午10:32:42
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lookSaveAmt (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int saveAmt = 0;
		int saveAmt2 = 0;
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		String openId = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getOpenId();
	
//		使用自己的方式吧,不太信任腾讯查询余额接口的健壮性
//		String openId = PlayerMapUtil.getPlayerByChannelId(channelId).getOpenId();
//		Map<String, String> retMap = SDKUtil.get_balance_m(openId);
//		
//		if (retMap != null && retMap.get("ret").equals("0")) {
//			saveAmt = ConvertUtil.toInt(retMap.get("save_amt"));//累计充值金额(元宝)
//		} else {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_saveAmt);
//			return;
//		}
		
		int type = (int)msgMap.get("type");//是否要充值列表首充状态 0-不要 1-要 3-累计充值活动里在活动期间的充值总数（rmb）4-当天充值 5-总共充值金额 6-(当天充值;活动总充值)
		
		List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
		
		//倒叙排序
//		Collections.sort(instPlayerRechargeList, new Comparator<Object>() {
//			public int compare(Object a, Object b) {
//				int one = ((InstPlayerRecharge)a).getSaveamt();
//				int two = ((InstPlayerRecharge)b).getSaveamt(); 
//				return (int)(two - one); 
//			}
//		}); 
		
		if (instPlayerRechargeList.size() > 0) {
			int sumAmt = 0;
			for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
				sumAmt += instPlayerRecharge.getThisamt();
			}
			saveAmt = sumAmt;
		}
		
		//累计充值活动里在活动期间的充值总数（rmb）
		if (type == 3 || type == 6) {
			int sumrmb = 0;
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.addRecharge + "");
			if (dictActivity.getEndTime() == null || dictActivity.getStartTime() == null) {
				if (type == 3) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noActivityDate);
					return;
				}
			} else {
				for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
					if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= DateUtil.getMillSecond(instPlayerRecharge.getOperateTime()) && DateUtil.getMillSecond(instPlayerRecharge.getOperateTime()) <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
						sumrmb += instPlayerRecharge.getThisrmb();
					}
				}
			}
			saveAmt = sumrmb;
			saveAmt2 = sumrmb;
		}
		
		String retList = "";
		if (type == 1) {
			for (DictRecharge dictRecharge : DictList.dictRechargeList) {
				String isFirst = "0";//0-未充过钱  1-充过钱
				int dictrmb = dictRecharge.getRmb();
				for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
					
					//月卡-30元
					if (dictRecharge.getRmb() == 30 && dictRecharge.getFirstAmt() == 0) {
						if (instPlayerRecharge.getThisrmb() == dictrmb && instPlayerRecharge.getGoodsId() == dictRecharge.getId()) {
							isFirst = "1";
							break;
						}
					//非月卡-30元
					} else if (dictRecharge.getRmb() == 30 && dictRecharge.getFirstAmt() != 0) {
						if (instPlayerRecharge.getThisrmb() == dictrmb && instPlayerRecharge.getGoodsId() == dictRecharge.getId()) {
							if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
								SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
								long recharegeTime = DateUtil.getMillSecond(instPlayerRecharge.getOperateTime());
								if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
									isFirst = "1";
									break;
								}
							} else {
								isFirst = "1";
								break;
							}
						}
					//50 或 68月卡
					} else if ((dictRecharge.getRmb() == 50 || dictRecharge.getRmb() == 68) && dictRecharge.getFirstAmt() == 0) {
						if (instPlayerRecharge.getThisrmb() == dictrmb && instPlayerRecharge.getGoodsId() == dictRecharge.getId()) {
							isFirst = "1";
							break;
						}
					//50 或 68非月卡
					} else if ((dictRecharge.getRmb() == 50 || dictRecharge.getRmb() == 68) && dictRecharge.getFirstAmt() != 0) {
						if (instPlayerRecharge.getThisrmb() == dictrmb && instPlayerRecharge.getGoodsId() == dictRecharge.getId()) {
							if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
								SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
								long recharegeTime = DateUtil.getMillSecond(instPlayerRecharge.getOperateTime());
								if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
									isFirst = "1";
									break;
								}
							} else {
								isFirst = "1";
								break;
							}
						}
					}
					//其他金额
					else {
						if (instPlayerRecharge.getThisrmb() == dictrmb) {
							if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
								SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
								long recharegeTime = DateUtil.getMillSecond(instPlayerRecharge.getOperateTime());
								if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
									isFirst = "1";
									break;
								}
							} else {
								isFirst = "1";
								break;
							}
						}
					}
					
				}
				retList = retList + dictRecharge.getId() + "_" + isFirst + ";";
			}
		}
		String retList2 = "";
		if (type == 6) {
			int dayOfWeek = DateUtil.getTimeInfo(DateType.DayOfWeek);
			int idMin = dayOfWeek * 10 + 1; // [
			int idMax = dayOfWeek * 10 + 9; // ]
//			List<DictActivityAddRecharge> dictActivityAddRechargeList = getDictActivityAddRechargeDAL().getList(
//					"id >= " + idMin + " and id <= " + idMax, 0);
			
			List<DictActivityAddRecharge> dictActivityAddRechargeList = new ArrayList<>();
			for (DictActivityAddRecharge dictActivityAddRecharge : DictList.dictActivityAddRechargeList) {
				if (dictActivityAddRecharge.getId() >= idMin && dictActivityAddRecharge.getId() <= idMax) {
					dictActivityAddRechargeList.add(dictActivityAddRecharge);
				}
			}
			
			int count = dictActivityAddRechargeList.size();
			int[] todayIDs = new int[count];
			for (int i = 0; i < count; ++i) {
				todayIDs[i] = dictActivityAddRechargeList.get(i).getId();
			}
			List<InstActivity> activitieList = getInstActivityDAL().getList(
					"instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.addRecharge, instPlayerId);
			InstActivity instActivity = activitieList.get(0); // todo新用户存在,老用户呢?
			String spareOne = instActivity.getSpareOne();
			String spareTwo = instActivity.getSpareTwo();
			if (spareOne == null || spareOne.isEmpty() || spareTwo == null || spareTwo.isEmpty()
					|| !DateUtil.isSameDay(spareOne, DateUtil.getYmdDate(), DateFormat.YMD)) {
				for (int id : todayIDs) {
					retList2 = retList2 + id + "_1;"; // todo未判断钱数
				}
			} else {
				String[] arrTwo = spareTwo.split(";");
				for (int id : todayIDs) {
					boolean found = false;
					for (String str : arrTwo) {
						if (String.valueOf(id).equals(str)) {
							found = true;
							break;
						}
					}
					retList2 = retList2 + id + (found ? "_0;" : "_1;"); // todo未判断钱数
				}
			}
		}
		//当天充值金额
		if (type == 4 || type == 6) {
			int dayrmb = 0;
			for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
				if (DateUtil.isSameDay(DateUtil.getCurrTime(), instPlayerRecharge.getOperateTime(), DateFormat.YMDHMS)) {
					dayrmb += instPlayerRecharge.getThisrmb();
				}
			}
			saveAmt = dayrmb;
		}
		//总共充值金额
		if (type == 5) {
			int sumrmb = 0;
			for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
				sumrmb += instPlayerRecharge.getThisrmb();
			}
			saveAmt = sumrmb;
		}
		MessageData retMsgData = new MessageData();
		
		//点充值界面,调用此方法时有充值返利逻辑[vip经验条]
		if (type == 1) {
			if (ParamConfig.rechargeMap.containsKey(openId)) {
				List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.rechargeRetGold+"'", 0);
				if (instPlayerBigTableList.size() > 0) {
					InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
					saveAmt = saveAmt + Integer.valueOf(instPlayerBigTable.getValue4());//应该返还的元宝数
				}
			}
		}
		
		retMsgData.putIntItem("1", saveAmt);
		if (type == 1) {
			retMsgData.putStringItem("2", StringUtil.noContainLastString(retList));
			retMsgData.putStringItem("3", "是否已购买");//超过30天,置为未购买
		} else if (type == 6) {
			retMsgData.putIntItem("2",saveAmt2);
			retMsgData.putStringItem("3",retList2);
			List<DictActivityAddRecharge> dictActivityAddRechargeList = DictList.dictActivityAddRechargeList;
			int dayOfWeek = DateUtil.getTimeInfo(DateType.DayOfWeek);
			int idMin = dayOfWeek * 10 + 1; // [
			int idMax = dayOfWeek * 10 + 9; // [
			for (DictActivityAddRecharge obj : dictActivityAddRechargeList) {
				int id = obj.getId();
				if ((idMin <= id && id <= idMax) || (id > 100)) {
					MessageData mdItem = new MessageData();
					mdItem.putIntItem("id", id);
					mdItem.putIntItem("progress", obj.getProgress());
					mdItem.putStringItem("things", obj.getThings());
					mdItem.putStringItem("description", obj.getDescription());
					retMsgData.putMessageItem("" + id, mdItem.getMsgData());
				}
			}
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 领取首充大礼包
	 * @author mp
	 * @date 2015-3-26 下午2:49:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getFirstRechargeGift (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//验证是否已经领取[ 0-未领取 1-已领取]
		if (instPlayer.getIsGetFirstRechargeGift() == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notGetStarType);
			return;
		}
		
		//验证是否达到领取首充大礼包的条件
		List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
		if (instPlayerRechargeList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_recharge);
			return;
		}
		
		//赠送大礼包
		MessageData syncMsgData = new MessageData();
		String gift = DictMap.dictSysConfigStrMap.get(StaticSysConfigStr.firstRechargeGift + "").getValue();
		for (String thing : gift.split(";")) {
			int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
			int tableValue = ConvertUtil.toInt(thing.split("_")[2]);
			ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, tableValue, syncMsgData, msgMap);
		}
		
		//修改玩家表中首充大礼包状态[ 0-未领取 1-已领取]
		instPlayer.setIsGetFirstRechargeGift(1);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 获取玩家的战力值
	 * @author hzw
	 * @date 2015-5-29上午10:58:21
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fightValue (Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int fightValue = (int)msgMap.get("fightValue");//玩家战力值
		PlayerFightValueMapUtil.add(instPlayerId, fightValue);
//		PlayerFightValueMapUtil.rank();
	}
	
	/**
	 * 用于测试ios充值
	 * @author hzw
	 * @date 2015-6-20下午4:21:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void rechargeTest (Map<String, Object> msgMap, String channelId) throws Exception {/*
		
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		// 获取参数
		int instPlayerId = (int) msgMap.get("instPlayerId");
		int rechargeId = (int) msgMap.get("rechargeId");// 充值字典表Id
		int regState = (int) msgMap.get("regState");// 充值状态
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId,instPlayerId);
		int operBeforeGoldNum = instPlayer.getGold();
		String openId = instPlayer.getOpenId();
		MessageData syncMsgData = new MessageData();
		if (regState == 0) {
			DictRecharge recharge = DictMap.dictRechargeMap.get(rechargeId + "");
			int thisGold = 0;
			int thisrmb = recharge.getRmb();
			int thisamt = thisrmb * 10;// 本次充值金额(游戏币)
			if(recharge.getFirstAmt() == 0){
				thisGold = thisrmb * DictMapUtil.getSysConfigIntValue(StaticSysConfig.monthCardTimes);
				instPlayer.setGold(instPlayer.getGold() + thisGold);
				
				//确认月卡实例数据生效
				ActivityUtil.addMonthCard(instPlayerId, StaticActivity.monthCard, syncMsgData, 1);
			}else{
				//倒叙排序
				List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
				Collections.sort(instPlayerRechargeList, new Comparator<Object>() {
					public int compare(Object a, Object b) {
						int one = ((InstPlayerRecharge)a).getSaveamt();
						int two = ((InstPlayerRecharge)b).getSaveamt();
						return (int)(two - one); 
					}
				}); 
				
				//处理额外赠送元宝（首充/非首充/没有这一档）
				int freeGold = PlayerUtil.getFreeGold(thisrmb, instPlayerRechargeList, openId);
				
				//处理元宝  赠送的元宝 + 充值的元宝
				thisGold = freeGold + thisamt;
				instPlayer.setGold(instPlayer.getGold() + thisGold);
			}

			// 处理vip逻辑-根据总充值rmb金额,计算vip等级
			int savermb = getInstPlayerRechargeDAL().getStatResult("sum", "thisrmb", "instPlayerId = " + instPlayerId) + thisrmb; // 累计充值金额 = 原始记录 + 本次记录
					
			int vipLevel = PlayerUtil.getVipLevel(instPlayer, savermb);
			instPlayer.setVipLevel(vipLevel);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

			// 更新玩家成就计数实例数据
			AchievementUtil.updateAchievement(instPlayerId, StaticAchievementType.vip, vipLevel, syncMsgData, null);

			// 记录充值日志
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("balance", new Double(0));
			retMap.put("gen_balance", new Double(0));
			PlayerUtil.addInstPlayerRecharge(retMap, "huayi", instPlayerId, openId, 0, instPlayer.getName(), savermb * 10, thisamt, thisamt, thisrmb, "ios充值");
			int operAfterGoldNum = instPlayer.getGold();
			PlayerUtil.addInstPlayerGoldStatics(instPlayerId, GoldStaticsType.add, thisGold, 0, "充值(含赠送游戏币)", operBeforeGoldNum, operAfterGoldNum, "", "", "");

			// 同步消息
			MessageUtil.sendSyncMsgToOne(openId, syncMsgData);
		}
		Player player = PlayerMapUtil.getPlayerByOpenId(openId);
		if(player != null){
			MessageData retMsgData = new MessageData();
			retMsgData.putIntItem("regState", regState);
			MessageUtil.sendSuccMsg(player.getChannelId(), msgMap, retMsgData);
		}
	*/}
	
	/**
	 * 获取充值订单（号）
	 * 没有数据库操作,无需用事务
	 * @author mp
	 * @date 2015-7-13 上午9:30:03
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void getOrder (Map<String, Object> msgMap, String channelId) throws Exception {
		
		int rechargeId = (int) msgMap.get("rechargeId");// 充值字典表Id
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		if(rechargeId == 1){
			List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and useNum = 1", instPlayerId);
			if (instActivityList.size() > 0) {
				InstActivity instActivity = instActivityList.get(0);
				if (DateUtil.getCurrMill() <= DateUtil.getMillSecond(instActivity.getActivityTime())) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_monthCardUndue);
					return;
				}
			}
		}
		
		if(rechargeId == 9){
			List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and useNum = 2", instPlayerId);
			if (instActivityList.size() > 0) {
				InstActivity instActivity = instActivityList.get(0);
				if (DateUtil.getCurrMill() <= DateUtil.getMillSecond(instActivity.getActivityTime())) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_monthCardUndue);
					return;
				}
			}
		}
		
		Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
		
		//IOS换sdk后的
		String imei = "";
		if (!player.getChannelSub().equals("com.y2game.dp")) {
			if (rechargeId == 9) {
				rechargeId = 4;
				imei = "9";
			} else {
				imei = rechargeId + "";
			}
		}
		
		DictRecharge recharge = DictMap.dictRechargeMap.get(rechargeId + "");
		
		//组织获取订单号的地址和参数
		String url = SysConfigUtil.getValue("recharge.getOrder.url");
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("gameid", player.getAloneServerId());//游戏编号（int）
		paramMap.put("serverid", player.getZoneid());//服务器编号（int）
		paramMap.put("channelid", player.getChannel());//渠道标识
		paramMap.put("channelsub", player.getChannelSub());//子渠道
		paramMap.put("accountid", player.getAccountId());//自有账号ID（int）
		paramMap.put("roleid", instPlayerId + "");//角色ID（int）
		paramMap.put("rolelevel", player.getLevel() + "");//角色等级
		paramMap.put("rolename", player.getPlayerName());//角色名称
		paramMap.put("itemid", rechargeId + "");//道具编号
		paramMap.put("itemname", (recharge.getRmb() * 10) + "元宝");//道具名称
		paramMap.put("itemprice", (recharge.getRmb() * 10 * 10) + "");//单价（分）
		paramMap.put("itemcount", 1 + "");//道具数量
		paramMap.put("deviceos", "");//充值发起时的操作系统
		paramMap.put("userid", player.getOpenId().substring(0, player.getOpenId().lastIndexOf("@")));//用户ID(openId)
		paramMap.put("deviceImei", imei);//设备imei
		paramMap.put("customData", "");//自定义字段
//		paramMap.put("imei", imei);
//		paramMap.put("requestTencent", false + "");//是否要查询腾讯服务器--这个字段可以不发
		paramMap.put("ip", "");//客户端IP地址
		
		System.out.println("获取订单号参数=" + paramMap);
		
		String params = HttpClientUtil.httpBuildQuery(paramMap);
		String loginResult = HttpClientUtil.postMapSubmit(url, params);
		if (loginResult == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_getRechargeOrder);
			return ;
		}
		
		JSONObject rechargeJson = new JSONObject(loginResult);
		String retMsg = String.valueOf(rechargeJson.get("code"));
		if (!retMsg.equals("1")) {
			if (retMsg.equals("2")) {
				MessageUtil.sendFailMsg(channelId, msgMap, rechargeJson.get("message") + "");
				return ;
			} else {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_getRechargeOrder);
				return ;
			}
		}
		String rechargeOrder = String.valueOf(rechargeJson.get("customData"));
		String channelData = String.valueOf(rechargeJson.get("channel_data"));
//		System.out.println("==order==" + rechargeOrder);
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", rechargeOrder);
		retMsgData.putStringItem("2", channelData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		
	}
	
	/**
	 * 发起邮件
	 * @author mp
	 * @date 2015-7-28 上午10:48:05
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void sendMail (Map<String, Object> msgMap, String channelId) throws Exception {
		
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//获取参数
		String oppoName = (String) msgMap.get("oppoName");// 对方角色名
		String oppoContent = (String) msgMap.get("oppoContent");// 为对方发送的邮件内容
		Player ownPlayer = PlayerMapUtil.getPlayerByChannelId(channelId);
		
		//判断自己是否在线
		if (ownPlayer == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mailNoOnline);
			return ;
		}
		
		//判断邮件对象是否为自己
		if (oppoName.equals(ownPlayer.getPlayerName())) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mailNoMyself);
			return ;
		}
		
		//判断内容长度
		if (oppoContent.length() > DictMapUtil.getSysConfigIntValue(StaticSysConfig.contentLimitNum)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mailContentUpMaxNum + DictMapUtil.getSysConfigIntValue(StaticSysConfig.contentLimitNum));
			return ;
		}
		
		//过滤敏感字符
		oppoContent = WordFilterUtil.simpleFilter(oppoContent, '*');
		
		//获取对方玩家Id
		int oppoPlayerId = 0;
		Player oppoPlayer = PlayerMapUtil.getPlayerByPlayerName(oppoName);
		if (oppoPlayer == null) {
			List<InstPlayer> oppoPlayerList = getInstPlayerDAL().getList("name = '"+oppoName+"'", 0); 
			if (oppoPlayerList.size() <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mailNoPlayer);
				return ;
			}
			oppoPlayerId = oppoPlayerList.get(0).getId();
		} else {
			oppoPlayerId = oppoPlayer.getPlayerId();
		}
		
		//发送邮件
		PlayerUtil.addPlayerMail(oppoPlayer, oppoPlayerId, oppoContent, 0, 0, ownPlayer.getPlayerName(), 3);
		
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 打开聊天窗口
	 * @author mp
	 * @date 2015-7-30 下午4:01:38
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void openChatWindow (Map<String, Object> msgMap, String channelId) throws Exception {/*
		Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
		player.setChatWindowState(1);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	*/}
	
	/**
	 * 关闭聊天窗口
	 * @author mp
	 * @date 2015-7-30 下午4:01:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void closeChatWindow (Map<String, Object> msgMap, String channelId) throws Exception {/*
		Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
		player.setChatWindowState(0);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	*/}
	
	/**
	 * 获取系统时间
	 * @author mp
	 * @date 2015-8-23 下午10:44:56
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void getSysTime (Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", DateUtil.getCurrTime());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	@Test
	public void test(){
		try {
			SpringUtil.getSpringContext();
			DictData.loadDictData();
			
			for (DictVIP obj : DictList.dictVIPList) {
				System.out.println(obj.getLimit());
			}
			
			List<DictVIP> vipList = DictList.dictVIPList;
			
			Collections.sort(vipList, new Comparator<Object>() {
				public int compare(Object a, Object b) {
					int one = ((DictVIP)a).getLimit();
					int two = ((DictVIP)b).getLimit(); 
					return (int)(two - one); 
				}
			}); 
			
			for (DictVIP obj : DictList.dictVIPList) {
				System.out.println(obj.getLimit());
			}
			
//			NameUtil.initName();
//			randomName(null, null);
			
//			String str = "哈哈哈哈哈哈哈哈哈哈哈哈哈哈";
//			System.out.println(str.substring(0, 8));
//			for(int i = 1; i <= 100; i++){
//				System.out.println(RandomUtil.getRandomFloat());
//			}
			
			/*MessageData syncMsgData = new MessageData();
	/*		InstPlayerCard newInstPlayerCard = getInstPlayerCardDAL().getModel(708, 26);
			newInstPlayerCard.setInstPlayerConstells("");
			System.out.println(newInstPlayerCard.getResult());
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, newInstPlayerCard, newInstPlayerCard.getId(), newInstPlayerCard.getResult(),syncMsgData);*/
			
		/*	
			InstPlayerFormation instPlayerFormation = new InstPlayerFormation();
			instPlayerFormation.setInstCardId(1111);
			instPlayerFormation.setInstPlayerId(1111);
			instPlayerFormation.setPosition(1111);
			instPlayerFormation = getInstPlayerFormationDAL().add(instPlayerFormation, 2);
			System.out.println(instPlayerFormation.getResult());
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerFormation, instPlayerFormation.getId(), instPlayerFormation.getResult(),syncMsgData);
			*/System.out.println("aaaaaaaaaa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
