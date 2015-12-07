package com.huayi.doupo.logic.handler.util;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huayi.doupo.activity.cost.TotalCostManager;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.dal.base.DALFather;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictAchievement;
import com.huayi.doupo.base.model.DictAchievementType;
import com.huayi.doupo.base.model.DictActivityLimitTimeHeroJiFenReward;
import com.huayi.doupo.base.model.DictActivityLimitTimeHeroRankReward;
import com.huayi.doupo.base.model.DictActivityStrogerHero;
import com.huayi.doupo.base.model.DictArenaReward;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.DictConstell;
import com.huayi.doupo.base.model.DictDailyTask;
import com.huayi.doupo.base.model.DictEquipAdvance;
import com.huayi.doupo.base.model.DictEquipQuality;
import com.huayi.doupo.base.model.DictEquipment;
import com.huayi.doupo.base.model.DictFire;
import com.huayi.doupo.base.model.DictGuipStep;
import com.huayi.doupo.base.model.DictLevelProp;
import com.huayi.doupo.base.model.DictMagic;
import com.huayi.doupo.base.model.DictRecharge;
import com.huayi.doupo.base.model.DictVIP;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.InstActivityOnlineRewards;
import com.huayi.doupo.base.model.InstActivityOpenServiceBag;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerAchievement;
import com.huayi.doupo.base.model.InstPlayerAchievementValue;
import com.huayi.doupo.base.model.InstPlayerAward;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerCardSoul;
import com.huayi.doupo.base.model.InstPlayerConstell;
import com.huayi.doupo.base.model.InstPlayerDailyTask;
import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.InstPlayerFire;
import com.huayi.doupo.base.model.InstPlayerFormation;
import com.huayi.doupo.base.model.InstPlayerGmReward;
import com.huayi.doupo.base.model.InstPlayerGoldStatics;
import com.huayi.doupo.base.model.InstPlayerLineup;
import com.huayi.doupo.base.model.InstPlayerLoot;
import com.huayi.doupo.base.model.InstPlayerMagic;
import com.huayi.doupo.base.model.InstPlayerMail;
import com.huayi.doupo.base.model.InstPlayerManualSkill;
import com.huayi.doupo.base.model.InstPlayerPartner;
import com.huayi.doupo.base.model.InstPlayerRecharge;
import com.huayi.doupo.base.model.InstPlayerRechargeCallBack;
import com.huayi.doupo.base.model.InstPlayerResolveTemp;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.SysGmReward;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.player.DelayRechargePlayer;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.model.player.PlayerMemObjMapUtil;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticQuality;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticSysConfigStr;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.DateUtil.DateType;
import com.huayi.doupo.base.util.base.EncryptUtil;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.base.JsonUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.LogicLogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.base.util.logic.system.log.ThreadOper;
import com.huayi.doupo.base.util.logic.system.log.ThreadPoolUtils;
import com.huayi.doupo.logic.util.ChannelMapUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 玩家帮助类
 * 
 * @author mp
 * @date 2013-10-11 下午1:55:10
 */
public class PlayerUtil extends DALFactory {

	/**
	 * 微信登录验证token
	 * 
	 * @author mp
	 * @date 2015-1-27 上午9:53:27
	 * @param appid
	 * @param appKey
	 * @param openid
	 * @param accessToken
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static boolean validateToken(String appid, String appKey, String openid, String accessToken) throws Exception {

		String msdkUrl = SysConfigUtil.getValue("msdk.url");

		// 组织URL
		StringBuilder sb = new StringBuilder();
		// String url = "http://msdktest.qq.com/auth/check_token/";
		String url = "http://" + msdkUrl + "/auth/check_token/";

		long timestamp = DateUtil.getCurrMill() / 1000;
		sb.append("?timestamp=").append(timestamp);
		sb.append("&appid=").append(appid);
		String sig = EncryptUtil.md5Hex(appKey + timestamp);
		sb.append("&sig=").append(sig);
		sb.append("&openid=").append(openid);
		sb.append("&encode=1");
		url = url + sb.toString();
		// System.out.println("url = " + url);

		// 组织参数[JSON]
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("accessToken", accessToken);
		jsonMap.put("openid", openid);
		String param = JsonUtil.toJson(jsonMap);
		// System.out.println("json = " + param);

		// 发送请求[返回{"ret":0,"msg":"user is logged in"}表示验证成功]
		String result = HttpClientUtil.postSubmit(url, param);
		// System.out.println("result = " + result);

		if (result.contains("\"ret\":0")) {
			// System.out.println("---validate sucess---");
			return true;
		}
		return false;
	}

	/**
	 * 手Q登录验证OpenKey
	 * 
	 * @author mp
	 * @date 2015-1-24 下午2:23:15
	 * @param appid
	 * @param appKey
	 * @param openid
	 * @param openkey
	 * @param userip
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static boolean validateOpenKey(String appid, String appKey, String openid, String openkey, String userip) throws Exception {

		String msdkUrl = SysConfigUtil.getValue("msdk.url");

		// 组织URL
		StringBuilder sb = new StringBuilder();
		String url = "http://" + msdkUrl + "/auth/verify_login/";

		long timestamp = DateUtil.getCurrSec();
		sb.append("?timestamp=").append(timestamp);
		sb.append("&appid=").append(appid);
		String sig = EncryptUtil.md5Hex(appKey + timestamp);
		sb.append("&sig=").append(sig);
		sb.append("&openid=").append(openid);
		sb.append("&encode=1");
		url = url + sb.toString();
		// System.out.println("url = " + url);

		// 组织参数[JSON]
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("appid", appid);
		jsonMap.put("openid", openid);
		jsonMap.put("openkey", openkey);
		jsonMap.put("userip", userip);
		String param = JsonUtil.toJson(jsonMap);
		// System.out.println("json = " + param);

		// 发送请求[返回{"ret":0,"msg":"user is logged in"}表示验证成功]
		String result = HttpClientUtil.postSubmit(url, param);
		// System.out.println("result = " + result);

		if (result.contains("\"ret\":0")) {
			// System.out.println("---validate sucess---");
			return true;
		}
		return false;
	}

	/**
	 * 初始化高级账号 玩家数据
	 * 
	 * @author mp
	 * @date 2014-11-29 下午5:34:34
	 * @param openId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayer initHighPlayer(String openId, Map<String, Object> msgMap) throws Exception {
		InstPlayer instPlayer = new InstPlayer();
		instPlayer.setOpenId(openId);
		instPlayer.setName("");
		instPlayer.setLevel(50);
		// 高级账号初始元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, 999999, msgMap);
		instPlayer.setCopper(99999999 + "");
		instPlayer.setExp(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initExp));
		instPlayer.setEnergy(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initEnergy));
		instPlayer.setMaxEnergy(DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy));
		instPlayer.setVigor(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initVigor));
		instPlayer.setMaxVigor(DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor));
		instPlayer.setCardNum(6);
		instPlayer.setMaxCardNum(6);
		instPlayer.setGuidStep("&");
		instPlayer.setChapterId(0);
		instPlayer.setBarrierId(0);
		instPlayer.setFireGainRuleId(1);
		instPlayer.setInstPlayerFireId(0);
		instPlayer.setVipLevel(10);
		instPlayer.setVigour(0);
		instPlayer.setCulture(999999);
		instPlayer.setLastEnergyRecoverTime("");
		instPlayer.setLastVigorRecoverTime("");
		instPlayer.setBarrierNum(0);
		instPlayer.setBuyEnergyNum(0);
		instPlayer.setBuyVigorNum(0);
		instPlayer.setLastBuyEnergyTime("");
		instPlayer.setLastBuyVigorTime("");
		instPlayer.setGuipedBarrier("11B10&11B2;");
		instPlayer.setWashTime("");
		instPlayer.setHeaderCardId(DictMapUtil.getSysConfigIntValue(StaticSysConfig.headerCardId));
		instPlayer.setDailyTashTime(DateUtil.getYmdDate());
		instPlayer.setVipIds("");
		instPlayer.setPullBlack(0);
		instPlayer = getInstPlayerDAL().add(instPlayer, 0);
		return instPlayer;
	}

	/**
	 * 初始化玩家实例
	 * 
	 * @author mp
	 * @date 2013-10-11 下午1:56:03
	 * @param openId
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayer initPlayer(String openId, Map<String, Object> msgMap, String channel, String serverId) throws Exception {
		InstPlayer instPlayer = new InstPlayer();
		instPlayer.setOpenId(openId);
		instPlayer.setName("");
		instPlayer.setLevel(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initLevel));
		// 加初始元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, DictMapUtil.getSysConfigIntValue(StaticSysConfig.initGold), msgMap);
		instPlayer.setCopper(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initCopper) + "");
		instPlayer.setExp(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initExp));
		instPlayer.setEnergy(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initEnergy));
		instPlayer.setMaxEnergy(DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy));
		instPlayer.setVigor(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initVigor));
		instPlayer.setMaxVigor(DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor));
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
		instPlayer.setCardNum(dictLevelProp.getInTeamCard());
		instPlayer.setMaxCardNum(dictLevelProp.getInTeamCard());
		instPlayer.setGuidStep("&");
		instPlayer.setChapterId(0);
		instPlayer.setBarrierId(0);
		instPlayer.setFireGainRuleId(1);
		instPlayer.setInstPlayerFireId(0);
		instPlayer.setVipLevel(0);
		instPlayer.setVigour(0);
		instPlayer.setCulture(0);
		instPlayer.setLastEnergyRecoverTime("");
		instPlayer.setLastVigorRecoverTime("");
		instPlayer.setBarrierNum(0);
		instPlayer.setBuyEnergyNum(0);
		instPlayer.setBuyVigorNum(0);
		instPlayer.setLastBuyEnergyTime("");
		instPlayer.setLastBuyVigorTime("");
		instPlayer.setGuipedBarrier("&");
		instPlayer.setWashTime("");
		instPlayer.setHeaderCardId(DictMapUtil.getSysConfigIntValue(StaticSysConfig.headerCardId));
		instPlayer.setDailyTashTime(DateUtil.getYmdDate());
		instPlayer.setVipIds("");
		instPlayer.setPullBlack(0);
		instPlayer.setIsGetFirstRechargeGift(0);
		instPlayer.setChannel(channel);
		if (serverId != null && !serverId.equals("")) {
			instPlayer.setServerId(Integer.valueOf(serverId));
		}
		instPlayer = getInstPlayerDAL().add(instPlayer, 0);
		return instPlayer;
	}

	/**
	 * 游戏启动，初始化500个NPC玩家
	 * 
	 * @author mp
	 * @date 2014-1-3 下午1:59:04
	 * @param openId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayer initNpcPlayer(String openId, int level, Map<String, Object> msgMap) throws Exception {
		InstPlayer instPlayer = new InstPlayer();
		instPlayer.setOpenId(openId);
		instPlayer.setName("");
		instPlayer.setLevel(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initLevel));
		// 加初始元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, DictMapUtil.getSysConfigIntValue(StaticSysConfig.initGold), msgMap);
		instPlayer.setCopper(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initCopper) + "");
		instPlayer.setExp(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initExp));
		instPlayer.setEnergy(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initEnergy));
		instPlayer.setMaxEnergy(DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy));
		instPlayer.setVigor(DictMapUtil.getSysConfigIntValue(StaticSysConfig.initVigor));
		instPlayer.setMaxVigor(DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor));
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
		instPlayer.setCardNum(dictLevelProp.getInTeamCard());
		instPlayer.setMaxCardNum(dictLevelProp.getInTeamCard());
		instPlayer.setGuidStep("&");
		instPlayer.setChapterId(0);
		instPlayer.setBarrierId(0);
		instPlayer.setFireGainRuleId(1);
		instPlayer.setInstPlayerFireId(0);
		instPlayer.setVipLevel(0);
		instPlayer.setVigour(0);
		instPlayer.setCulture(0);
		instPlayer.setLastEnergyRecoverTime("");
		instPlayer.setLastVigorRecoverTime("");
		instPlayer.setBarrierNum(0);
		instPlayer.setBuyEnergyNum(0);
		instPlayer.setBuyVigorNum(0);
		instPlayer.setLastBuyEnergyTime("");
		instPlayer.setLastBuyVigorTime("");
		instPlayer.setGuipedBarrier("&");

		instPlayer = getInstPlayerDAL().add(instPlayer, 0);

		// InstPlayerDuel instPlayerDuel = new InstPlayerDuel();
		// instPlayerDuel.setCurrentDuelIntegral(0);
		// instPlayerDuel.setCurrentDuelRank(instPlayer.getId());
		// instPlayerDuel.setCurrentDuelRankIntegral(0);
		// instPlayerDuel.setDuelChallengTimes(3);
		// instPlayerDuel.setInstPlayerId(instPlayer.getId());
		// getInstPlayerDuelDAL().add(instPlayerDuel);

		return instPlayer;
	}

	/**
	 * 绑定Socket中的Player
	 * 
	 * @author mp
	 * @date 2013-10-11 下午2:25:28
	 * @param channelId
	 * @param openId
	 * @param instPlayer
	 * @Description
	 */
	public static void bindSocketPlayer(String channelId, String channel, String openId, InstPlayer instPlayer, Map<String, Object> msgMap, String session_id, String session_type, String pay_token, int onlineState, String accountId, String loginServer, String aloneServerId, String channel_sub) throws Exception {

		int playerId = instPlayer.getId();
		Player player = new Player();
		Channel nettyChannel = ChannelMapUtil.getByChannelId(channelId);

		String env = SysConfigUtil.getValue("env");

		if (!env.equals("0")) {
			InetSocketAddress insocket = (InetSocketAddress) nettyChannel.remoteAddress();
			player.setIp(insocket.getAddress().getHostAddress());// 获取客户端IP地址

			String appid = (String) msgMap.get("appid");
			String appKey = (String) msgMap.get("appKey");
			String openkey = (String) msgMap.get("openkey");
			String userip = (String) msgMap.get("userip");
			String pf = (String) msgMap.get("pf");// **新加的**
			String pfkey = (String) msgMap.get("pfkey");// **新加的**
			String zoneid = (String) msgMap.get("zoneid");// **新加的**

			// 以下几个参数用于日志统计
			String clientVersion = (String) msgMap.get("clientVersion");// 客户端版本号
			String devOs = (String) msgMap.get("devOs");// 设备OS[ios/android]
			String imei = (String) msgMap.get("imei");// 设备IMEI
			String idfa = (String) msgMap.get("idfa");// 设备IDFA

			player.setClientVersion(clientVersion);
			player.setDevOs(devOs);
			player.setImei(imei);
			player.setIdfa(idfa);
			player.setLoginServer(loginServer);
			player.setAloneServerId(aloneServerId);
			player.setChannelSub(channel_sub);

			player.setAppid(appid);
			player.setAppKey(appKey);
			player.setOpenkey(openkey);
			player.setUserip(userip);
			player.setPf(pf);
			player.setPfkey(pfkey);
			player.setZoneid(zoneid);
			player.setChannel(channel);

			player.setSession_id(session_id);
			player.setSession_type(session_type);
			player.setPay_token(pay_token);// **新加的**
		}

		player.setOnlineState(onlineState);
		player.setChannelId(channelId);
		player.setOpenId(openId);
		player.setPlayerId(playerId);
		player.setPlayerName(instPlayer.getName());
		player.setLevel(instPlayer.getLevel());
		player.setAccountId(accountId);
		PlayerMapUtil.add(channelId, player);

		// 如果配置为使用缓存,登录时需向缓存中添加对象,add只有登录这一个入口
		if (DALFather.isUseCach()) {
			PlayerMemObj playerMemObj = PlayerMemObjMapUtil.getPlayerMemObjByPlayerId(playerId);
			if (playerMemObj == null) {
				PlayerMemObjMapUtil.add(playerId, new PlayerMemObj(0));//登录跟初始化的时候设置成0,玩家退出时设置为当前时间,清除的是退出游戏2个小时的人,0的时候表示在线,不处理
			} else {
				playerMemObj.currTime = 0;//登录跟初始化的时候设置成0,玩家退出时设置为当前时间,清除的是退出游戏2个小时的人,0的时候表示在线,不处理
			}
		}
	}

	/**
	 * 添加小伙伴
	 * 
	 * @author mp
	 * @date 2014-7-3 下午12:03:19
	 * @param instPlayerId
	 * @param instCardId
	 * @param cardId
	 * @param position
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayerPartner addPartner(int instPlayerId, int instCardId, int cardId, int position) throws Exception {
		InstPlayerPartner instPlayerPartner = new InstPlayerPartner();
		instPlayerPartner.setCardId(cardId);
		instPlayerPartner.setInstCardId(instCardId);
		instPlayerPartner.setPosition(position);
		instPlayerPartner.setInstPlayerId(instPlayerId);
		getInstPlayerPartnerDAL().add(instPlayerPartner, instPlayerId);
		return instPlayerPartner;
	}

	/**
	 * 分解-装备处理
	 * 
	 * @author mp
	 * @date 2014-7-28 下午3:00:45
	 * @param equipList
	 * @return
	 * @Description
	 */
	private static List<String> resolve_equip(String equipList, int instPlayerId) throws Exception {

		// 定义参数
		int useTalentValue = 0;// 分解得到的火能石
		int copperNum = 0;// 分解得到的铜币数量
		int copperConsum = 0;// 分解消耗铜币数量
		String resolveResult = "";// 分解结果
		String filterEquipList = "";// 过滤后的装备列表
		String equipNames = "";

		// 解析装备列表,并计算相关数量
		for (String equipId : equipList.split(";")) {
			int instPlayerEquipId = ConvertUtil.toInt(equipId);
			InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);

			int equipnum = 0;
			int coppnum = 0;

			int equipAdvanceId = instPlayerEquip.getEquipAdvanceId();
			DictEquipAdvance dictEquipAdvance = DictMap.dictEquipAdvanceMap.get(equipAdvanceId + "");
			if (dictEquipAdvance != null) {
				int startLevel = dictEquipAdvance.getStarLevel();
				int equipType = dictEquipAdvance.getEquipTypeId();
				int equipQuality = dictEquipAdvance.getEquipQualityId();
				for (DictEquipAdvance obj : DictList.dictEquipAdvanceList) {
					if (obj.getEquipTypeId() == equipType && obj.getStarLevel() <= startLevel && obj.getEquipQualityId() == equipQuality) {
						if (obj.getContions() != null && obj.getContions().contains("_")) {
							equipnum += ConvertUtil.toInt(obj.getContions().split("_")[0]);
							coppnum += ConvertUtil.toInt(obj.getContions().split("_")[1]);
						}
					}
				}
			}

			// 如果玩家Id不一致或者此装备处于装备状态,不予处理
			if (instPlayerEquip.getInstPlayerId() != instPlayerId || instPlayerEquip.getInstCardId() != 0) {
				continue;
			}

			filterEquipList += equipId + ";";

			// 根据装备品质计算此装备能分解多少火能石和消耗多少铜币
			DictEquipment dictEquipment = DictMap.dictEquipmentMap.get(instPlayerEquip.getEquipId() + "");
			int equipQualityId = dictEquipment.getEquipQualityId();
			
			if (dictEquipment != null) {
				equipNames += dictEquipment.getName() + ";";
			}
			
			DictEquipQuality dictEquipQuality = DictMap.dictEquipQualityMap.get(equipQualityId + "");
			copperConsum += dictEquipQuality.getResolveNeedCopper();

			// 计算分解后能得到多少铜币
			int equipLevel = instPlayerEquip.getLevel();
			if (equipLevel > 0) {
				// 强化到此等级所需总费用 * 0.6 = 分解返还的铜币 ****后修改为从装备强化表中去找****
				copperNum = copperNum + FormulaUtil.calcStrengFeeByLevel(equipLevel, instPlayerEquip.getEquipId());
			}
			int thingNum = dictEquipQuality.getThingNum();
			if (thingNum == 0) {
				thingNum = 1;
			}
			copperNum = copperNum + coppnum;
			useTalentValue += dictEquipment.getUseTalentValue() * thingNum * (equipnum + 1);
		}

		// 分解后得到哪些物品
		if (copperNum > 0) {
			resolveResult = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.copper + "_" + copperNum + ";";
		}
		resolveResult += StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.culture + "_" + useTalentValue;
		
		String equipResolveLog  = "分解-分解预览：instPlayerId=" + instPlayerId + " 分解的装备=" + equipNames + " 分解装备得到的物品=" + resolveResult + " 分解的装备的实例Id=" + filterEquipList;
		LogUtil.info(equipResolveLog);
		
		return ImmutableList.of(copperConsum + "", resolveResult, filterEquipList);
	}

	/**
	 * 分解-卡牌处理
	 * 
	 * @author mp
	 * @date 2014-7-29 上午11:43:44
	 * @param cardList
	 * @param instPlayerId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	private static List<String> resolve_card(String cardList, int instPlayerId) throws Exception {

		// 定义参数
		int soulNum = 0;// 分解得到的魂源数量
		int copperNum = 0;// 分解得到的铜币数量
		int copperConsum = 0;// 分解消耗铜币数量
		int sumExp = 0;// 分解卡牌的总经验
		String resolveResult = "";// 分解结果
		String filterCaradList = "";// 过滤后的卡牌列表
		String cardNames = "";

		// 解析卡牌列表,并计算相关数量
		String fightSoulList = "";
		for (String cardId : cardList.split(";")) {
			int instPlayerCardId = ConvertUtil.toInt(cardId);
			
			fightSoulList += FightSoulUtil.getFightSoulIfCardExsits(instPlayerId, instPlayerCardId);
			
			
			InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);

			// 如果玩家Id不一致或者此卡牌为白色或绿色或上阵状态,不予处理[只有蓝紫金卡可以分解]##2015-04-15, 颜色普遍提升过一个档次，改成绿蓝紫金，现在要求蓝色可以分解， 修改人：小强
			if (instPlayerCard.getInstPlayerId() != instPlayerId || instPlayerCard.getInTeam() == 1 || instPlayerCard.getQualityId() == StaticQuality.white) {
				continue;
			}

			filterCaradList += cardId + ";";

			// 根据卡牌品质计算此卡牌能分解多少魂源和消耗多少铜币
			int cardQualityId = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "").getQualityId();
			
			cardNames += DictMap.dictCardMap.get(instPlayerCard.getCardId() + "").getName() + ";";
			
			soulNum += DictMap.dictQualityMap.get(cardQualityId + "").getResolveSoulNum();
			copperConsum += DictMap.dictQualityMap.get(cardQualityId + "").getResolveNeedCopper();

			// 计算分解后能得到多少铜币
			int cardLevel = instPlayerCard.getLevel();
			if (cardLevel >= 1) {
				if (cardLevel == 1 && instPlayerCard.getExp() == 0) {
					continue;
				}
				// copperNum += FormulaUtil.calcUpgradeFeeByLevel(cardLevel);//到底是直接查表还是计算有效经验?...........
				// 升级到此等级所需总费用 * 0.6 = 分解返还的铜币 ****后修改为查DictCardExp表 1exp=1copper******在后来修改为总经验*系数[SysConfig里边配置的比例]2014-12-10
				// copperNum += (upgradeSumFee * DictMap.dictSysConfigMap.get(StaticSysConfig.resolveCardFeePer + "").getValue());
				sumExp += (FormulaUtil.calcSumExpByLevel(cardLevel) + instPlayerCard.getExp());
			}
		}

		copperNum = (int) (sumExp * DictMapUtil.getSysConfigFloatValue(StaticSysConfig.eatExpCopper));

		// 分解后得到哪些物品
		if (copperNum != 0) {
			resolveResult = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.copper + "_" + copperNum + ";";
		}
		if (soulNum != 0) {
			resolveResult += StaticTableType.DictThing + "_" + StaticThing.soulSource + "_" + soulNum + ";";
		}

		// 根据总经验计算获得的经验丹
		String pillResult = FormulaUtil.calcExpPillBySumExp(sumExp);

		// 将计算出的经验丹放入结果中
		if (!pillResult.equals("")) {
			resolveResult += pillResult;
		}
		resolveResult = resolveResult.substring(0, resolveResult.length() - 1);

		String cardResolveLog  = "分解-分解预览：instPlayerId=" + instPlayerId + " 分解的卡牌=" + cardNames + " 分解卡牌得到的物品=" + resolveResult + " 分解的卡牌的实例Id=" + filterCaradList;
		LogUtil.info(cardResolveLog);
		
		return ImmutableList.of(copperConsum + "", resolveResult, filterCaradList, fightSoulList.length() > 0 ? StringUtil.noContainLastString(fightSoulList) : fightSoulList);
	}

	/**
	 * 分解-异火处理
	 * 
	 * @author mp
	 * @date 2014-7-29 上午11:44:19
	 * @param cardList
	 * @param instPlayerId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	private static List<String> resolve_fire(String fireList, int instPlayerId) throws Exception {

		// 定义参数
		int fireCrysNum = 0;// 分解得到的魂源数量
		int copperConsum = 0;// 分解消耗铜币数量
		String resolveResult = "";// 分解结果
		String filterFireList = "";// 过滤后的异火列表

		// 解析异火列表,并计算相关数量
		for (String fireId : fireList.split(";")) {
			int instPlayerFireId = ConvertUtil.toInt(fireId);
			InstPlayerFire instPlayerFire = getInstPlayerFireDAL().getModel(instPlayerFireId, instPlayerId);
			DictFire dictFire = DictMap.dictFireMap.get(instPlayerFire.getFireId() + "");
			int fireType = dictFire.getType();

			// fileType = 2 表示为兽火,不做处理;分解只能分解异火,不能分解兽火
			if (instPlayerFire.getInstPlayerId() != instPlayerId || fireType == 2 || instPlayerFire.getIsUse() == 1) {
				continue;
			}

			filterFireList += fireId + ";";

			// 累积火晶数量
			fireCrysNum += DictMap.dictSysConfigMap.get(StaticSysConfig.fireCrys + "").getValue();
			copperConsum += DictMap.dictSysConfigMap.get(StaticSysConfig.resolveFireCopper + "").getValue();
		}

		// 组织分解结果
		resolveResult += StaticTableType.DictThing + "_" + StaticThing.fireCrystal + "_" + fireCrysNum;

		return ImmutableList.of(copperConsum + "", resolveResult, filterFireList);
	}

	/**
	 * 分解功法法宝
	 * @author cui
	 * @date	15/11/05
	 * @param magicList
	 * @param instPlayerId
	 * @return
	 * @throws Exception
	 */
	private static List<String> resolve_magic(String magicList, int instPlayerId) throws Exception {

		// 定义参数
		String resolveResult = "";// 分解结果
		String filterMagicList = "";// 过滤后的异火列表
		int sum = 0; //分解能获得的灵宝精魄
		int copperConsum = 0;// 分解消耗铜币数量

		//302
		// 解析异火列表,并计算相关数量
		for (String magicId : magicList.split(";")) {
			int instPlayerMagicId = ConvertUtil.toInt(magicId);
			InstPlayerMagic instPlayerMagic = getInstPlayerMagicDAL().getModel(instPlayerMagicId, instPlayerId);
			DictMagic dictMagic = DictMap.dictMagicMap.get(instPlayerMagic.getMagicId() + "");
			int magicType = dictMagic.getType();
			int magicQuality = dictMagic.getMagicQualityId();

			//紫色以下无法分解
			if(instPlayerMagic.getInstPlayerId() != instPlayerId || instPlayerMagic.getInstCardId() != 0 || magicQuality < 1 || magicQuality > 2){
				continue;
			}

			filterMagicList += magicId + ";";

			if(magicQuality == 1){//橙色
				sum += 25;
			}else if(magicQuality == 2){//紫色
				sum += 1;
			}

		}

		// 组织分解结果
		resolveResult += StaticTableType.DictThing + "_" + StaticThing.thing302 + "_" + sum;

		return ImmutableList.of(copperConsum + "", resolveResult, filterMagicList);
	}

	/**
	 * 分解
	 * 
	 * @author mp
	 * @date 2014-7-28 下午4:05:55
	 * @param equipList
	 * @param instPlayerId
	 * @param retMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void resolve(int resolveType, String resolveList, int instPlayerId, String resoType, MessageData retMsgData) throws Exception {

		List<String> retList = null;

		// 分解-装备处理
		if (resoType.equals("EQUIP")) {
			retList = resolve_equip(resolveList, instPlayerId);
			// 分解-卡牌处理
		} else if (resoType.equals("CARD")) {
			retList = resolve_card(resolveList, instPlayerId);
			// 分解-异火处理
		} else if (resoType.equals("FIRE")) {
			retList = resolve_fire(resolveList, instPlayerId);
		} else if(resoType.equals("MAGIC")){
			// 分解-功法法宝
			retList = resolve_magic(resolveList, instPlayerId);
		}

		// 插入分解临时表,此表只供服务器使用,不需同步给客户端,插入前先删除之前的旧数据
		List<InstPlayerResolveTemp> instPlayerResolveTempList = getInstPlayerResolveTempDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		// 用for循环删除,提高容错性
		for (InstPlayerResolveTemp obj : instPlayerResolveTempList) {
			getInstPlayerResolveTempDAL().deleteById(obj.getId(), instPlayerId);
		}
		// 插入
		InstPlayerResolveTemp instPlayerResolveTemp = new InstPlayerResolveTemp();
		instPlayerResolveTemp.setInstPlayerId(instPlayerId);
		instPlayerResolveTemp.setResolveResult(retList.get(1));
		instPlayerResolveTemp.setConsumCopper(ConvertUtil.toInt(retList.get(0)));
		instPlayerResolveTemp.setResolveType(resolveType);
		instPlayerResolveTemp.setResolveList(retList.get(2));
		getInstPlayerResolveTempDAL().add(instPlayerResolveTemp, instPlayerId);

		// 为客户端组织分解后得到的物品
		retMsgData.putIntItem("1", instPlayerResolveTemp.getId());// id
		retMsgData.putStringItem("2", retList.get(1));// 分解结果 tableTypeId_tableFieldId_tableValue;...的格式,最后没有分号
		if (resoType.equals("CARD")) {
			retMsgData.putStringItem("3", retList.get(3));// 斗魂实例Id;，最后没有分号
		} else {
			retMsgData.putStringItem("3", "");// 斗魂实例Id;最后没有分号
		}
	}

	/**
	 * 结算体力 加体力有4: 1-洗澡， 2-吃体力丹， 3-时间恢复 ， 4-升级送体力 减体力有1： 普通副本胜利后
	 * 
	 * @author mp
	 * @date 2014-9-22 下午3:14:38
	 * @param instPlayer
	 * @return 时间间隔；是否更新Player? 0-不更新 1-更新
	 * @throws Exception
	 * @Description
	 */
	public static void settleEngery(InstPlayer instPlayer, MessageData syncMsgData, MessageData retMsgData) throws Exception {

		int retMill = 0;
		int isUpdatePlayer = 1;
		long timeInerval = 0;// 返回0表示倒计时为00:00,体力已恢复至最大100;否则体力还未恢复到最大,返回距下一点体力恢复的倒计时时间[单位-毫秒]
		String lastEneryRecoverTime = instPlayer.getLastEnergyRecoverTime();
		int howLongRecoverEngery = DictMapUtil.getSysConfigIntValue(StaticSysConfig.howLongRecoverEngery);
		int maxEngery = DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy);

		// 只有体力未恢复到最大时才进行逻辑处理 [体力恢复到最大时LastEnergyRecoverTime字段为空]
		if (lastEneryRecoverTime != null && !lastEneryRecoverTime.equals("")) {
			// 计算时间间隔 = 当前时间 - 上次体力恢复时间
			long intevalTime = DateUtil.getCurrMill() - DateUtil.getMillSecond(lastEneryRecoverTime);
			// 计算这段时间能恢复多少体力, 6分钟恢复1点
			long recoverEngeryNum = intevalTime / (howLongRecoverEngery * 60 * 1000);
			if (recoverEngeryNum > 0) {
				if (instPlayer.getEnergy() < maxEngery) {
					if ((instPlayer.getEnergy() + recoverEngeryNum) < maxEngery) {
						// 结算体力,更新时间
						instPlayer.setEnergy(instPlayer.getEnergy() + (int) recoverEngeryNum);
						long recoverTime = DateUtil.getMillSecond(lastEneryRecoverTime) + recoverEngeryNum * DictMapUtil.getSysConfigIntValue(StaticSysConfig.howLongRecoverEngery) * 60 * 1000;
						instPlayer.setLastEnergyRecoverTime(DateUtil.getTimeByMill(recoverTime));
						// 计算时间差
						timeInerval = (howLongRecoverEngery * 60 * 1000) - (intevalTime % (howLongRecoverEngery * 60 * 1000));
					} else {
						// 结算体力为最大,并清空时间
						instPlayer.setEnergy(maxEngery);
						instPlayer.setLastEnergyRecoverTime("");
						// 计算时间差
						timeInerval = 0;
					}
				} else {
					instPlayer.setLastEnergyRecoverTime("");
				}
			} else {
				// 计算时间差
				timeInerval = (howLongRecoverEngery * 60 * 1000) - intevalTime;
				isUpdatePlayer = 0;
			}

			if (isUpdatePlayer == 1) {
				getInstPlayerDAL().update(instPlayer, instPlayer.getId());
				if (syncMsgData != null) {
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				}
			}

			// 5秒延迟
			if (timeInerval != 0) {
				retMill = (int) (timeInerval + 5 * 1000);
			}
		}

		MessageData energyMsgData = new MessageData();
		energyMsgData.putIntItem("1", retMill);
		retMsgData.putMessageItem("energy", energyMsgData.getMsgData());
	}

	/**
	 * 结算耐力 加耐力有3 1-时间恢复 2-吃耐力丹 3-升级送耐力 减耐力有1 抢夺东西
	 * 
	 * @author mp
	 * @date 2014-9-22 下午3:47:55
	 * @param instPlayer
	 * @param syncMsgData
	 * @param retMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void settleVigor(InstPlayer instPlayer, MessageData syncMsgData, MessageData retMsgData) throws Exception {

		int retMill = 0;
		int isUpdatePlayer = 1;
		long timeInerval = 0;// 返回0表示倒计时为00:00,耐力已恢复至最大30;否则耐力还未恢复到最大,返回距下一点耐力恢复的倒计时时间[单位-毫秒]
		String lastVigorRecoverTime = instPlayer.getLastVigorRecoverTime();
		int howLongRecoverVigor = DictMapUtil.getSysConfigIntValue(StaticSysConfig.howLongRecoverVigor);
		int maxVigor = DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor);

		// 只有耐力未恢复到最大时才进行逻辑处理 [耐力恢复到最大时LastVigorRecoverTime字段为空]
		if (lastVigorRecoverTime != null && !lastVigorRecoverTime.equals("")) {
			// 计算时间间隔 = 当前时间 - 上次耐力恢复时间
			long intevalTime = DateUtil.getCurrMill() - DateUtil.getMillSecond(lastVigorRecoverTime);
			// 计算这段时间能恢复多少耐力, 30分钟恢复1点
			long recoverVigorNum = intevalTime / (howLongRecoverVigor * 60 * 1000);
			if (recoverVigorNum > 0) {
				if (instPlayer.getVigor() < maxVigor) {
					if ((instPlayer.getVigor() + recoverVigorNum) < maxVigor) {
						// 结算耐力,更新时间
						instPlayer.setVigor(instPlayer.getVigor() + (int) recoverVigorNum);
						long recoverTime = DateUtil.getMillSecond(lastVigorRecoverTime) + recoverVigorNum * DictMapUtil.getSysConfigIntValue(StaticSysConfig.howLongRecoverVigor) * 60 * 1000;
						instPlayer.setLastVigorRecoverTime(DateUtil.getTimeByMill(recoverTime));
						// 计算时间差
						timeInerval = (howLongRecoverVigor * 60 * 1000) - (intevalTime % (howLongRecoverVigor * 60 * 1000));
					} else {
						// 结算耐力为最大,并清空时间
						instPlayer.setVigor(maxVigor);
						instPlayer.setLastVigorRecoverTime("");
						// 计算时间差
						timeInerval = 0;
					}
				} else {
					instPlayer.setLastVigorRecoverTime("");
				}
			} else {
				// 计算时间差
				timeInerval = (howLongRecoverVigor * 60 * 1000) - intevalTime;
				isUpdatePlayer = 0;
			}

			if (isUpdatePlayer == 1) {
				getInstPlayerDAL().update(instPlayer, instPlayer.getId());
				if (syncMsgData != null) {
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				}
			}
			// 5秒延迟
			if (timeInerval != 0) {
				retMill = (int) (timeInerval + 5 * 1000);
			}
		}

		MessageData vigorMsgData = new MessageData();
		vigorMsgData.putIntItem("1", retMill);
		retMsgData.putMessageItem("vigor", vigorMsgData.getMsgData());
	}

	/**
	 * 初始化体力和耐力时间
	 * 
	 * @author mp
	 * @date 2014-9-22 下午4:23:35
	 * @param retMsgData
	 * @Description
	 */
	public static void initEngeryAndVigor(MessageData retMsgData) {
		MessageData energyMsgData = new MessageData();
		energyMsgData.putIntItem("1", 0);
		retMsgData.putMessageItem("energy", energyMsgData.getMsgData());

		MessageData fightAwareMsgData = new MessageData();
		fightAwareMsgData.putIntItem("1", 0);
		retMsgData.putMessageItem("vigor", fightAwareMsgData.getMsgData());
	}

	/**
	 * 结算体力和耐力
	 * 
	 * @author mp
	 * @date 2014-9-22 下午4:35:53
	 * @param instPlayer
	 * @param retMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void settleEngeryAndVigor(InstPlayer instPlayer, MessageData retMsgData) throws Exception {

		// 容错处理-体力
		if (instPlayer.getLastEnergyRecoverTime() == null || instPlayer.getLastEnergyRecoverTime().equals("")) {
			if (instPlayer.getEnergy() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy)) {
				instPlayer.setEnergy(DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy));
				getInstPlayerDAL().update(instPlayer, 0);
			}
		}

		// 容错处理-耐力
		if (instPlayer.getLastVigorRecoverTime() == null || instPlayer.getLastVigorRecoverTime().equals("")) {
			if (instPlayer.getVigor() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor)) {
				instPlayer.setVigor(DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor));
				getInstPlayerDAL().update(instPlayer, 0);
			}
		}

		if (instPlayer.getEnergy() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy) && !instPlayer.getLastEnergyRecoverTime().equals("")) {
			instPlayer.setLastEnergyRecoverTime("");
			getInstPlayerDAL().update(instPlayer, 0);
		}

		if (instPlayer.getVigor() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor) && !instPlayer.getLastVigorRecoverTime().equals("")) {
			instPlayer.setLastVigorRecoverTime("");
			getInstPlayerDAL().update(instPlayer, 0);
		}

		// 结算体力
		settleEngery(instPlayer, null, retMsgData);

		// 结算耐力
		settleVigor(instPlayer, null, retMsgData);

	}

	/**
	 * 处理登录（非首次）牵扯到的事
	 * 
	 * @author hzw
	 * @date 2014-10-14上午11:24:59
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void disposeLogin(InstPlayer instPlayer, InstUser instUser, MessageData retMsgData) throws Exception {
		int instPlayerId = instPlayer.getId();

		// 领奖中心
		List<InstPlayerAward> instPlayerAwardList = getInstPlayerAwardDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerAward obj : instPlayerAwardList) {
			if (DateUtil.dayDiff(obj.getInsertTime(), DateUtil.getCurrTime()) > DictMapUtil.getSysConfigIntValue(StaticSysConfig.awardDay)) {
				getInstPlayerAwardDAL().deleteById(obj.getId(), instPlayerId);
			}
		}

		// 竞技场每日奖励
		Map<Integer, Map<String, Integer>> AwarMap = AwardUtil.getMap();
		List<DictArenaReward> dictArenaRewardList = DictList.dictArenaRewardList;
		int arenaAwardTime = DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaAwardTime) * 60 * 60 * 1000;
		if (AwarMap.containsKey(instPlayerId)) {
			Map<String, Integer> tempMap = AwarMap.get(instPlayerId);
			for (Entry<String, Integer> obj : tempMap.entrySet()) {
				InstPlayerAward instPlayerAward = new InstPlayerAward();
				instPlayerAward.setInstPlayerId(instPlayerId);
				instPlayerAward.setName(2);
				String things = "";
				for (DictArenaReward dictArenaReward : dictArenaRewardList) {
					if (obj.getValue() >= dictArenaReward.getDownRank() && obj.getValue() <= dictArenaReward.getUpRank()) {
						things = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.gold + "_" + dictArenaReward.getGold() + ";" + StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.prestige + "_" + dictArenaReward.getPrestige();
					}
				}
				instPlayerAward.setThings(things);
				instPlayerAward.setOperTime(DateUtil.getTimeByMill(DateUtil.getMillSecond(obj.getKey() + " 00:00:00") + arenaAwardTime));
				instPlayerAward.setDescription("恭喜您在竞技场中获得排名奖励：");
				instPlayerAward.setInsertTime(DateUtil.getCurrTime());
				getInstPlayerAwardDAL().add(instPlayerAward, instPlayerId);
				LogUtil.info("领奖中心发奖:instPlayerId=" + instPlayerId + " name=2 things=" + things + " operTime=" + DateUtil.getCurrTime() + " description=恭喜您在竞技场中获得排名奖励：");
			}
			AwarMap.remove(instPlayerId);
		}

		// 处理Gm全服发奖[全服发奖后才注册的新玩家,不享受奖励]
		String redgistTime = instUser.getFirstLoginTime();

		// 查看 是否有全服奖励[近三天的]
		int receiveDay = DictMapUtil.getSysConfigIntValue(StaticSysConfig.receiveDay);
		String receiveDayYhs = DateUtil.getNumDayDate(receiveDay * -1);
		List<SysGmReward> sysGmRewardList = new ArrayList<SysGmReward>();
		for (SysGmReward sysGmReward : DictList.sysGmRewardList) {
			if (DateUtil.getMillSecond(sysGmReward.getRewardTime()) > DateUtil.getMillSecond(receiveDayYhs + " 00:00:00")) {
				sysGmRewardList.add(sysGmReward);
			}
		}
		// List<SysGmReward> sysGmRewardList = getSysGmRewardDAL().getList("rewardTime > '"+receiveDayYhs+"'", 0);
		if (sysGmRewardList.size() > 0) {

			// 先将全服发奖记录表查出-查一次数据库
			List<InstPlayerGmReward> instPlayerGmRewardList = getInstPlayerGmRewardDAL().getList("instPlayerId = " + instPlayerId, 0);

			for (SysGmReward sysGmReward : sysGmRewardList) {

				boolean flag = true;

				// 看注册时间是否小于发奖时间
				if (DateUtil.getMillSecond(redgistTime) >= DateUtil.getMillSecond(sysGmReward.getRewardTime())) {
					flag = false;
				}

				// 看玩家是否已领
				for (InstPlayerGmReward instPlayerGmReward : instPlayerGmRewardList) {
					if (instPlayerGmReward.getSendRewardTime().equals(sysGmReward.getRewardTime())) {
						flag = false;
					}
				}

				// 看是否已发到领奖中心
				for (InstPlayerAward instPlayerAward : instPlayerAwardList) {
					if (instPlayerAward.getOperTime().equals(sysGmReward.getRewardTime())) {
						flag = false;
					}
				}

				// 如果都没有,将奖励信息插入领奖中心
				if (flag) {
					ActivityUtil.addInstPlayerAward(instPlayerId, 3, sysGmReward.getParamList(), sysGmReward.getRewardTime(), sysGmReward.getContent(), new MessageData());
				}
			}
		}

		// 如果日期不同，初始化每日任务 [如果是刚注册,不走此方法]
		String ymdDate = DateUtil.getYmdDate();
		if (instUser.getLoginTotalTimes() != 1 && !ymdDate.equals(instPlayer.getDailyTashTime())) {
			getInstPlayerDailyTaskDAL().update("update Inst_Player_DailyTask set times = 0 , rewardState = 0 where instPlayerId = " + instPlayerId);
			instPlayer.setDailyTashTime(ymdDate);
			instPlayer = getInstPlayerDAL().update(instPlayer, instPlayerId);

			// 清空内存数据[切记内存时要注意,上面更新时把version更新一下也可以 version = version + 1]
			if (PlayerMemObjMapUtil.getMap().get(instPlayerId) != null) {
				ConcurrentHashMap<Integer, InstPlayerDailyTask> daiConcurrentHashMap = PlayerMemObjMapUtil.getMap().get(instPlayerId).instPlayerDailyTaskMap;
				if (daiConcurrentHashMap != null) {
					daiConcurrentHashMap.clear();
				}
			}
		}

		// 处理没在阵却显示在阵状态的补丁-先查个数,个数不一致再遍历[首次登录不处理]
		if (instUser.getLoginTotalTimes() != 1) {
			int formationNum = getInstPlayerFormationDAL().getCount("instPlayerId = " + instPlayerId);
			int partnerNum = getInstPlayerPartnerDAL().getCount("instPlayerId = " + instPlayerId);
			int inTeamNum = getInstPlayerCardDAL().getCount("instPlayerId = " + instPlayerId + " and inTeam = 1");
			if (inTeamNum > (formationNum + partnerNum)) {
				List<InstPlayerFormation> instPlayerFormationList = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId, 0);
				List<InstPlayerPartner> instPlayerPartnerList = getInstPlayerPartnerDAL().getList("instPlayerId = " + instPlayerId, 0);
				List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId + " and inTeam = 1", 0);
				// 组织新的list
				List<String> cardList = new ArrayList<>();
				for (InstPlayerFormation instPlayerFormation : instPlayerFormationList) {
					cardList.add(instPlayerFormation.getInstCardId() + ";" + instPlayerFormation.getCardId());
				}
				for (InstPlayerPartner instPlayerPartner : instPlayerPartnerList) {
					cardList.add(instPlayerPartner.getInstCardId() + ";" + instPlayerPartner.getCardId());
				}
				// 验证并修改
				for (InstPlayerCard instPlayerCard : instPlayerCardList) {
					if (!cardList.contains(instPlayerCard.getId() + ";" + instPlayerCard.getCardId())) {
						instPlayerCard.setInTeam(StaticCustomDict.unTeam);
						getInstPlayerCardDAL().update(instPlayerCard, 0);
					}
				}
			}
		}
		
		//打阵型重复补丁    
		int normalNum = 0;//正规军个数
		int lightNum = 0;//替补军个数
		HashSet<Integer> normalSet = new HashSet<>();
		HashSet<Integer> lightSet = new HashSet<>();
		List<InstPlayerFormation> instPlayerFormationList = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId, 0);
		for (InstPlayerFormation playerFormation : instPlayerFormationList) {
			//1-正规军 2-替补队员
			if (playerFormation.getType() == 1) {
				normalNum++;
				normalSet.add(playerFormation.getPosition());
			}
			if (playerFormation.getType() == 2) {
				lightNum++;
				lightSet.add(playerFormation.getPosition());
			}
		}
		//当正规军人数大于规定的人数，或正规军中位置有重复的，表示数据错误。替补军亦然。
		int isReOrg = 0;//是否需要重新组织 0-不需要  1-需要
		if (normalNum > DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxFightPlayer) || normalNum != normalSet.size()) {
			isReOrg = 1;
		}
		if (lightNum > DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxLightPlayer) || lightNum != lightSet.size()) {
			isReOrg = 1;
		}
		//如果发现数据不正常,重新组织（成正确的）
		if (isReOrg == 1) {
			int index = 0;
			for (InstPlayerFormation playerFormation : instPlayerFormationList) {
				index++;
				if (index > DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxFightPlayer)) {
					playerFormation.setType(2);
					playerFormation.setPosition(index-DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxFightPlayer));
				} else {
					playerFormation.setType(1);
					playerFormation.setPosition(index);
				}
				getInstPlayerFormationDAL().update(playerFormation, 0);
			}
		}

		
		// 开服礼包登录处理
		ActivityUtil.loginInstActivityOpenServiceBag(instPlayerId, instUser);

		// 发限时英雄奖励
		limitTimePeapleReward(instPlayerId);

		// 最强英雄发奖
		strogerHeroReward(instPlayerId);

		// 处理充值返利逻辑
		String openId = instPlayer.getOpenId();
		// 判断此用户是否为充值返利用户
		if (ParamConfig.rechargeMap.containsKey(openId)) {
			// 判断在充值返利记录表中有无记录,无记录说明此用户在其他服已开始领取返利,不处理逻辑
			List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '" + StaticBigTable.rechargeRetGold + "'", 0);
			if (instPlayerBigTableList.size() > 0) {
				InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);

				// 判断是否在有效期内,不在不处理
				long jiange = DateUtil.getCurrMill() - DateUtil.getMillSecond(DictMap.dictSysConfigStrMap.get(StaticSysConfigStr.rechargeRetGoldStartTime + "").getValue());// 需要改,按8.27 11:00公测时间开始,不按开服时间算
				int rechargeRetGoldInDate = DictMapUtil.getSysConfigIntValue(StaticSysConfig.rechargeRetGoldInDate);
				long youxiaoqi = rechargeRetGoldInDate * 24 * 60 * 60 * 1000L;
				if (jiange <= youxiaoqi) {
					// 判断是否已经返完,已领完不处理
					int haveRetNum = instPlayerBigTable.getValue1().split(";").length;
					if (haveRetNum < DictMapUtil.getSysConfigIntValue(StaticSysConfig.rechargeRetGoldDays)) {
						// 判断当前日期,是否在领取的日期列表中,在的话,表示今天已领,不处理
						if (!StringUtil.contains(instPlayerBigTable.getValue1(), DateUtil.getYmdDate(), ";")) {
							// 返元宝到领奖中心, 如果不为最后一次,本次返还元宝为 返还总元宝/返还天数, 如果为最后一次返还元宝,本次返还元宝为 返还总元宝%返还天数
							int haveGetTimes = instPlayerBigTable.getValue1().split(";").length;// 已经领了多少次了
							int currGetTimes = haveGetTimes + 1;// 本次是第几次领

							int retSumRmb = ParamConfig.rechargeMap.get(openId);// 一共充了多少钱
							int shoudGold = retSumRmb * 10;// 应该返多少元宝
							int currRetGold = ConvertUtil.toInt(shoudGold * ParamConfig.rechargeRetRuleMap.get(currGetTimes));// 本次返多少元宝

							// 将本次返利发到领奖中心 name-名目 1-天焚炼气塔 2-竞技场 3-系统 4-世界Boss
							ActivityUtil.addInstPlayerAward(instPlayer.getId(), 3, "3_1_" + currRetGold, DateUtil.getCurrTime(), "计费删档期间老玩家第" + currGetTimes + "次充值返利:", new MessageData());

							// 修改返利记录
							instPlayerBigTable.setValue1(instPlayerBigTable.getValue1() + ";" + DateUtil.getYmdDate());
							instPlayerBigTable.setValue2(instPlayerBigTable.getValue2() + ";" + currRetGold);
							getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
						}
					}
				}
			}
		}
	}

	/**
	 * 发限时英雄奖励
	 * 
	 * @author mp
	 * @date 2015-8-18 下午9:34:09
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void limitTimePeapleReward(int instPlayerId) throws Exception {
		// 发限时英雄积分奖励
		if (ParamConfig.recruitJifenRewardMap.containsKey(instPlayerId)) {
			ConcurrentHashMap<String, Integer> innerMap = ParamConfig.recruitJifenRewardMap.get(instPlayerId);
			String rewardDate = "";
			for (Entry<String, Integer> entry : innerMap.entrySet()) {
				String things = "";
				rewardDate = entry.getKey();
				int jifen = entry.getValue();
				List<DictActivityLimitTimeHeroJiFenReward> activityLimitTimeHeroJiFenRewardList = DictList.dictActivityLimitTimeHeroJiFenRewardList;
				for (DictActivityLimitTimeHeroJiFenReward obj : activityLimitTimeHeroJiFenRewardList) {
					if (jifen >= obj.getSaveJifenNum()) {
						things += obj.getRewards() + ";";
					}
				}
				if (things.length() > 0) {
					ActivityUtil.addInstPlayerAward(instPlayerId, 3, StringUtil.noContainLastString(things), rewardDate, "您在限时英雄活动中积分为 " + jifen + ",获得奖励：", new MessageData());
				}
			}

			// 清空此玩家数据
			ParamConfig.recruitJifenRewardMap.remove(instPlayerId);
		}

		// 发限时英雄排行奖励
		if (ParamConfig.recruitRankRewardMap.containsKey(instPlayerId)) {
			ConcurrentHashMap<String, Integer> innerMap = ParamConfig.recruitRankRewardMap.get(instPlayerId);
			String rewardDate = "";
			for (Entry<String, Integer> entry : innerMap.entrySet()) {
				String things = "";
				rewardDate = entry.getKey();
				int rank = entry.getValue();
				List<DictActivityLimitTimeHeroRankReward> activityLimitTimeHeroRankRewardList = DictList.dictActivityLimitTimeHeroRankRewardList;
				for (DictActivityLimitTimeHeroRankReward obj : activityLimitTimeHeroRankRewardList) {
					if (obj.getStartRankNum() <= rank && rank <= obj.getEndRankNum()) {
						things += obj.getRewards() + ";";
						break;
					}
				}
				if (things.length() > 0) {
					ActivityUtil.addInstPlayerAward(instPlayerId, 3, StringUtil.noContainLastString(things), rewardDate, "您在限时英雄活动中排名为" + rank + ",获得奖励：", new MessageData());
				}
			}

			// 清空此玩家数据
			ParamConfig.recruitRankRewardMap.remove(instPlayerId);
		}
	}

	/**
	 * 最强英雄发奖-登录
	 * 
	 * @author mp
	 * @date 2015-8-19 下午5:02:20
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void strogerHeroReward(int instPlayerId) throws Exception {
		if (ParamConfig.strogerRankRewardMap.containsKey(instPlayerId)) {
			ConcurrentHashMap<String, Integer> innerMap = ParamConfig.strogerRankRewardMap.get(instPlayerId);
			String rewardDate = "";
			for (Entry<String, Integer> entry : innerMap.entrySet()) {
				String things = "";
				rewardDate = entry.getKey();
				int rank = entry.getValue();
				int hh = DateUtil.getTimeInfoHms(rewardDate, DateType.HOUR_OF_DAY);
				List<DictActivityStrogerHero> strogerHeroList = DictList.dictActivityStrogerHeroList;
				for (DictActivityStrogerHero obj : strogerHeroList) {
					if (hh == obj.getRewardTimePoint() && rank == obj.getRank()) {
						things += obj.getRewards() + ";";
					}
				}
				if (things.length() > 0) {
					ActivityUtil.addInstPlayerAward(instPlayerId, 3, StringUtil.noContainLastString(things), rewardDate, "您在巅峰强者活动中排名为" + rank + ", 获得奖励：", new MessageData());
				}
			}

			// 清空此玩家数据
			ParamConfig.strogerRankRewardMap.remove(instPlayerId);
		}
	}

	/**
	 * 处理退出牵扯到的事
	 * 
	 * @author hzw
	 * @date 2014-10-14下午4:35:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void disposeClose(Player player) throws Exception {
		InstUser instUser = getInstUserDAL().getList("openId='" + player.getOpenId() + "'", 0).get(0);
		int instPlayerId = player.getPlayerId();
		// 在线奖励活动
		SysActivity activity = DictMap.sysActivityMap.get(StaticActivity.onlineRewards + "");
		if (activity.getIsForevery() == 1 || (DateUtil.getMillSecond(DateUtil.getCurrTime()) >= DateUtil.getMillSecond(activity.getStartTime()) && DateUtil.getMillSecond(DateUtil.getCurrTime()) <= DateUtil.getMillSecond(activity.getEndTime()))) {
			List<InstActivityOnlineRewards> instActivityOnlineRewardsList = (List<InstActivityOnlineRewards>) getInstActivityOnlineRewardsDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if (instActivityOnlineRewardsList.size() > 0) {
				InstActivityOnlineRewards obj = instActivityOnlineRewardsList.get(0);
				if (obj.getOnlineTime() != 0) {
					long onlineTime = 0;
					if (DateUtil.getMillSecond(obj.getUpdateTime()) > DateUtil.getMillSecond(instUser.getLastLoginTime())) {
						onlineTime = DateUtil.getMillSecond(DateUtil.getCurrTime()) - DateUtil.getMillSecond(obj.getUpdateTime());
					} else {
						onlineTime = DateUtil.getMillSecond(DateUtil.getCurrTime()) - DateUtil.getMillSecond(instUser.getLastLoginTime());
					}
					int time = (int) (obj.getOnlineTime() - onlineTime);
					if (time <= 0) {
						time = 1000;
					}
					obj.setOnlineTime(time);
					getInstActivityOnlineRewardsDAL().update(obj, instPlayerId);
				}
			}
		}

		// 开服礼包
		List<InstActivityOpenServiceBag> instActivityOpenServiceBagList = (List<InstActivityOpenServiceBag>) getInstActivityOpenServiceBagDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if (instUser.getLastLeaveTime() != null && !instUser.getLastLeaveTime().equals("")) {
			if (!DateUtil.isSameDay(instUser.getLastLoginTime(), instUser.getLastLeaveTime(), DateFormat.YMDHMS)) {
				InstActivityOpenServiceBag instActivityOpenServiceBag = instActivityOpenServiceBagList.get(0);
				if (instActivityOpenServiceBag.getDay() < DictList.dictActivityOpenServiceBagList.size() && instActivityOpenServiceBag.getDay() != 0) {
					instActivityOpenServiceBag.setDay(instActivityOpenServiceBag.getDay() + 1);
					if (instActivityOpenServiceBag.getCan().equals("")) {
						instActivityOpenServiceBag.setCan(instActivityOpenServiceBag.getDay() + "");
					} else {
						instActivityOpenServiceBag.setCan(instActivityOpenServiceBag.getCan() + ";" + instActivityOpenServiceBag.getDay());
					}
					getInstActivityOpenServiceBagDAL().update(instActivityOpenServiceBag, instPlayerId);
				} else if (instActivityOpenServiceBag.getDay() == DictList.dictActivityOpenServiceBagList.size()) {
					instActivityOpenServiceBag.setDay(0);
					getInstActivityOpenServiceBagDAL().update(instActivityOpenServiceBag, instPlayerId);
				}
			}
		}
	}

	/**
	 * 选初始人物初始数据
	 * 
	 * @author mp
	 * @date 2014-10-7 上午10:05:52
	 * @param instPlayerId
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void initWarriorInitData(int instPlayerId, String channelId, InstPlayer instPlayer, Map<String, Object> msgMap) throws Exception {
		MessageData syncMsgData = new MessageData();
		Map<String, Integer> things = Maps.newHashMap();
		for (int i = 80; i <= 100; i++) {
			things.put(StaticTableType.DictCard + "_" + i, 1);
		}
		// 添加
		for (Entry<String, Integer> entry : things.entrySet()) {
			int tableTypeId = ConvertUtil.toInt(entry.getKey().split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(entry.getKey().split("_")[1]);
			int value = entry.getValue();
			ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
		}
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
	}

	/**
	 * 11.8号第2个版本的初始数据
	 * 
	 * @author mp
	 * @date 2014-11-8 下午3:06:27
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void initData2(InstPlayer instPlayer, Map<String, Object> msgMap) throws Exception {

		int instPlayerId = instPlayer.getId();

		/**
		 * 物品清单： 装备洗练锁 999 Dict_Thing 3 装备洗练石 999 Dict_Thing 8 装备开孔器 999 Dict_Thing 1 装备碎片 选取一种装备碎片30 Dict_Thing 201 菩提子 9999 Dict_Thing 2 卡边进阶的绿色材料 999 Dict_Thing 83 卡边进阶的蓝色材料 999 Dict_Thing 84 卡边进阶的紫色材料 999 Dict_Thing 85 境界突破材料 每种材料999 Dict_Thing 4——6
		 */

		// 格式为 thingId_num;
		String thingList = "3_999;8_999;1_999;201_30;2_999;83_999;84_999;85_999;4_999;5_999;6_999";
		for (String thing : thingList.split(";")) {
			int thingId = ConvertUtil.toInt(thing.split("_")[0]);
			int num = ConvertUtil.toInt(thing.split("_")[1]);
			ThingUtil.groupThing(instPlayer, StaticTableType.DictThing, thingId, num, new MessageData(), msgMap);
		}

		/**
		 * 萧炎魂魄 60 Dict_Card_Soul 88
		 */
		InstPlayerCardSoul instPlayerCardSoul = new InstPlayerCardSoul();
		instPlayerCardSoul.setInstPlayerId(instPlayerId);
		instPlayerCardSoul.setCardId(88);
		instPlayerCardSoul.setCardSoulId(88);
		instPlayerCardSoul.setNum(60);
		getInstPlayerCardSoulDAL().add(instPlayerCardSoul, instPlayerId);

		/**
		 * 1.2.3品丹药材料 每种材料999 Dict_PillThing 1——21
		 */
		for (int i = 1; i <= 21; i++) {
			ThingUtil.groupThing(instPlayer, StaticTableType.DictPillThing, i, 999, new MessageData(), msgMap);
		}

	}

	/**
	 * 10.8号第1个版本的初始数据
	 * 
	 * @author mp
	 * @date 2014-10-1 上午11:52:50
	 * @param instPlayerId
	 * @Description
	 */
	public static void initData1(int instPlayerId, Map<String, Object> msgMap) throws Exception {
		MessageData syncMsgData = new MessageData();
		Map<String, Integer> things = Maps.newHashMap();
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		// 卡牌
		// for(int i = 80; i <= 100; i++){
		// things.put(StaticTableType.DictCard + "_" + i, 1);
		// }
		// 装备
		int[] equips = { 45, 46, 47, 105, 106, 107, 85, 86, 87, 65, 66, 67, 1, 2, 3, 125, 126, 127 };
		for (int i = 0; i < equips.length; i++) {
			int equipId = equips[i];
			things.put(StaticTableType.DictEquipment + "_" + equipId, 1);
		}
		// 装备碎片
		for (int i = 200; i <= 220; i++) {
			things.put(StaticTableType.DictThing + "_" + i, 1);
		}
		// 丹药
		Map<Integer, Integer> dictPills = new HashMap<Integer, Integer>();
		dictPills.put(28, 33);
		dictPills.put(37, 42);
		dictPills.put(46, 51);
		for (Entry<Integer, Integer> entry : dictPills.entrySet()) {
			Integer key = entry.getKey();
			for (int i = key; i <= entry.getValue(); i++) {
				things.put(StaticTableType.DictPill + "_" + i, 50);
			}
		}

		// 丹方
		for (Entry<Integer, Integer> entry : dictPills.entrySet()) {
			Integer key = entry.getKey();
			for (int i = key; i <= entry.getValue(); i++) {
				PillUtil.addInstPlayerPillRecipe(instPlayerId, syncMsgData);
			}
		}

		// 丹材
		for (int i = 1; i <= 60; i++) {
			things.put(StaticTableType.DictPillThing + "_" + i, 50);
		}

		// 功法
		for (int i = 1; i <= 20; i++) {
			KungFuUtil.addInstPlayerKungFu(instPlayerId, i, 0);
		}

		// 功法碎片
		for (int i = 1; i <= 20; i++) {
			things.put(StaticTableType.DictChip + "_" + i, 5);
		}

		// 其它物品
		Map<Integer, Integer> thingMap = new HashMap<Integer, Integer>();
		thingMap.put(1, 100);
		thingMap.put(2, 100);
		thingMap.put(3, 100);
		thingMap.put(7, 100);
		thingMap.put(8, 100);
		thingMap.put(12, 100000);
		for (Entry<Integer, Integer> entry : thingMap.entrySet()) {
			things.put(StaticTableType.DictThing + "_" + entry.getKey(), entry.getValue());
		}

		// 添加
		for (Entry<String, Integer> entry : things.entrySet()) {
			int tableTypeId = ConvertUtil.toInt(entry.getKey().split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(entry.getKey().split("_")[1]);
			int value = entry.getValue();
			ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
		}

		// 手动技能
		for (int i = 400; i <= 405; i++) {
			ManualSkillUtil.addInstPlayerManualSkill(instPlayerId, i);
		}

	}

	/**
	 * 为引导准备的初始数据
	 * 
	 * @author mp
	 * @date 2014-11-1 上午9:38:59
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */

	public static void guipInitData(int instPlayerId, MessageData syncMsgData) throws Exception {
		// 为引导准备的402和403两个手动技能
		int[] manualSkills = { 402, 403 };
		for (int j = 0; j < manualSkills.length; j++) {
			int manualSkill = manualSkills[j];
			InstPlayerManualSkill instPlayerMannualSkill = ManualSkillUtil.addInstPlayerManualSkill(instPlayerId, manualSkill);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerMannualSkill, instPlayerMannualSkill.getId(), instPlayerMannualSkill.getResult(), syncMsgData);
		}
	}

	/**
	 * 为高级账号玩家初始化阵容数据
	 * 
	 * @author mp
	 * @date 2014-11-29 下午5:50:22
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void initHighPlayerLineup(int instPlayerId) throws Exception {

		HashMap<Integer, Integer> initMap = Maps.newLinkedHashMap();
		initMap.put(146, 7);
		initMap.put(134, 8);
		initMap.put(145, 9);
		initMap.put(151, 5);
		initMap.put(51, 4);
		initMap.put(125, 2);
		initMap.put(89, 6);
		initMap.put(88, 1);
		initMap.put(136, 3);

		// 装备和阵容
		HashMap<Integer, String> lineupMap = Maps.newLinkedHashMap();

		lineupMap.put(146, "47;107;87;3");
		lineupMap.put(134, "50;110;90;6");
		lineupMap.put(145, "46;106;86;8");
		lineupMap.put(151, "45;105;85;7");
		lineupMap.put(51, "46;106;86;2");
		lineupMap.put(125, "48;108;88;4");
		lineupMap.put(89, "49;109;89;5");
		lineupMap.put(88, "45;105;85;1");
		lineupMap.put(136, "48;108;88;10");

		// 给所有功法和法宝
		List<DictMagic> magicList = DictList.dictMagicList;
		for (DictMagic obj : magicList) {
			InstPlayerMagic instPlayerMagic = new InstPlayerMagic();
			instPlayerMagic.setExp(0);
			instPlayerMagic.setInstCardId(0);
			instPlayerMagic.setInstPlayerId(instPlayerId);
			instPlayerMagic.setMagicId(obj.getId());
			// 类型 1-法宝 2-功法
			if (obj.getType() == 1) {
				instPlayerMagic.setMagicLevelId(1);
			} else if (obj.getType() == 2) {
				instPlayerMagic.setMagicLevelId(21);
			}
			instPlayerMagic.setMagicType(obj.getType());
			instPlayerMagic.setQuality(1);
			getInstPlayerMagicDAL().add(instPlayerMagic, 0);
		}

		for (Entry<Integer, Integer> entry : initMap.entrySet()) {
			int cardId = entry.getKey();
			int pos = entry.getValue();
			int formationType = 1;

			// 卡牌
			InstPlayerCard instCard = CardUtil.initPlayerCard(instPlayerId, cardId, 40);

			// 放入功法
			List<InstPlayerMagic> instPlayerMagicList1 = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId + " and magicType = 1 and instCardId = 0", instPlayerId);
			InstPlayerMagic instPlayerMagic1 = instPlayerMagicList1.get(0);
			instPlayerMagic1.setInstCardId(instCard.getId());
			getInstPlayerMagicDAL().update(instPlayerMagic1, 0);

			// 放入法宝
			List<InstPlayerMagic> instPlayerMagicList2 = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId + " and magicType = 2 and instCardId = 0", instPlayerId);
			InstPlayerMagic instPlayerMagic2 = instPlayerMagicList2.get(0);
			instPlayerMagic2.setInstCardId(instCard.getId());
			getInstPlayerMagicDAL().update(instPlayerMagic2, 0);

			// 更新卡牌潜力
			instCard.setPotential(999999);
			getInstPlayerCardDAL().update(instCard, instPlayerId);

			// 阵型
			if (pos > 6) {
				pos = pos - 6;
				formationType = 2;
			}
			InstPlayerFormation instPlayerFormation = CardUtil.initPlayerFormation(instPlayerId, instCard, pos, formationType);
			int instPlayerFormationId = instPlayerFormation.getId();

			// 添加装备
			for (String equipIdString : lineupMap.get(cardId).split(";")) {
				int equipId = ConvertUtil.toInt(equipIdString);
				DictEquipment equipment = DictMap.dictEquipmentMap.get(equipId + "");
				InstPlayerEquip instPlayerEquip = EquipmentUtil.addEquipment(instPlayerId, equipId, instCard.getId());
				// 添加阵容
				InstPlayerLineup instPlayerLineup = new InstPlayerLineup();
				instPlayerLineup.setInstPlayerId(instPlayerId);
				instPlayerLineup.setInstPlayerFormationId(instPlayerFormationId);
				instPlayerLineup.setEquipTypeId(equipment.getEquipTypeId());
				instPlayerLineup.setInstPlayerEquipId(instPlayerEquip.getId());
				getInstPlayerLineupDAL().add(instPlayerLineup, instPlayerId);
			}

			// 命宫
			DictCard dictCard = DictMap.dictCardMap.get(instCard.getCardId() + "");
			if (!dictCard.getConstells().equals("")) {
				String constells[] = dictCard.getConstells().split(";");
				String instPlayerConstells = "";
				for (String constell : constells) {
					DictConstell dictConstell = DictMap.dictConstellMap.get(ConvertUtil.toInt(constell) + "");
					InstPlayerConstell obj = PillUtil.initPlayerConstell(instPlayerId, instCard.getId(), ConvertUtil.toInt(constell), dictConstell.getPills().split(";").length);
					instPlayerConstells += obj.getId() + ";";
				}
				instCard.setInstPlayerConstells(instPlayerConstells.substring(0, instPlayerConstells.length() - 1));
				getInstPlayerCardDAL().update(instCard, instPlayerId);
			}

		}
	}

	/**
	 * 测试用数据
	 * 
	 * @author mp
	 * @date 2014-10-7 下午12:11:12
	 * @param CardId
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void lineupTestInitData(int CardId, int instPlayerId) throws Exception {
		HashMap<Integer, Integer> initMap = Maps.newLinkedHashMap();
		initMap.put(88, 1);
		initMap.put(125, 2);
		initMap.put(51, 4);
		initMap.put(CardId, 3);
		initMap.put(151, 5);
		initMap.put(89, 6);
		initMap.put(138, 7);
		initMap.put(91, 8);
		initMap.put(115, 9);

		// 装备和阵容
		HashMap<Integer, String> lineupMap = Maps.newLinkedHashMap();
		lineupMap.put(88, "45;105;85;65;1;125");
		lineupMap.put(51, "46;106;86;66;2;126");
		lineupMap.put(138, "47;107;87;67;3;127");
		lineupMap.put(125, "48;108;88;68;4;128");
		lineupMap.put(89, "49;109;89;69;5;129");
		lineupMap.put(151, "50;110;90;70;6;130");
		lineupMap.put(115, "45;105;85;65;7;131");
		lineupMap.put(91, "46;106;86;66;8;132");
		lineupMap.put(145, "47;107;87;67;9;133");
		lineupMap.put(136, "48;108;88;68;10;134");
		lineupMap.put(150, "49;109;89;69;11;125");

		for (Entry<Integer, Integer> entry : initMap.entrySet()) {
			int cardId = entry.getKey();
			int pos = entry.getValue();
			int formationType = 1;

			// 卡牌
			InstPlayerCard instCard = CardUtil.initPlayerCard(instPlayerId, cardId, 40);

			// 阵型
			if (pos > 6) {
				pos = pos - 6;
				formationType = 2;
			}
			InstPlayerFormation instPlayerFormation = CardUtil.initPlayerFormation(instPlayerId, instCard, pos, formationType);
			int instPlayerFormationId = instPlayerFormation.getId();

			// 添加装备
			for (String equipIdString : lineupMap.get(cardId).split(";")) {
				int equipId = ConvertUtil.toInt(equipIdString);
				DictEquipment equipment = DictMap.dictEquipmentMap.get(equipId + "");
				InstPlayerEquip instPlayerEquip = EquipmentUtil.addEquipment(instPlayerId, equipId, instCard.getId());
				// 添加阵容
				InstPlayerLineup instPlayerLineup = new InstPlayerLineup();
				instPlayerLineup.setInstPlayerId(instPlayerId);
				instPlayerLineup.setInstPlayerFormationId(instPlayerFormationId);
				instPlayerLineup.setEquipTypeId(equipment.getEquipTypeId());
				instPlayerLineup.setInstPlayerEquipId(instPlayerEquip.getId());
				getInstPlayerLineupDAL().add(instPlayerLineup, instPlayerId);
			}

			// 命宫
			DictCard dictCard = DictMap.dictCardMap.get(instCard.getCardId() + "");
			if (!dictCard.getConstells().equals("")) {
				String constells[] = dictCard.getConstells().split(";");
				String instPlayerConstells = "";
				for (String constell : constells) {
					DictConstell dictConstell = DictMap.dictConstellMap.get(ConvertUtil.toInt(constell) + "");
					InstPlayerConstell obj = PillUtil.initPlayerConstell(instPlayerId, instCard.getId(), ConvertUtil.toInt(constell), dictConstell.getPills().split(";").length);
					instPlayerConstells += obj.getId() + ";";
				}
				instCard.setInstPlayerConstells(instPlayerConstells.substring(0, instPlayerConstells.length() - 1));
				getInstPlayerCardDAL().update(instCard, instPlayerId);
			}

		}
	}

	/**
	 * 注册账号时,初始化每日任务
	 * 
	 * @author mp
	 * @date 2014-12-29 下午2:40:10
	 * @param instPlayer
	 * @Description
	 */
	public static void initDailyTask(InstPlayer instPlayer) {

		// 将欲插入的对象放入List,为批量插入做准备
		List<InstPlayerDailyTask> instPlayerDailyTaskList = Lists.newArrayList();
		List<DictDailyTask> dailyTaskList = DictList.dictDailyTaskList;
		for (DictDailyTask obj : dailyTaskList) {
			String currTime = DateUtil.getCurrTime();
			InstPlayerDailyTask instPlayerDailyTask = new InstPlayerDailyTask();
			instPlayerDailyTask.setDailyTashkId(obj.getId());
			instPlayerDailyTask.setInsertTime(currTime);
			instPlayerDailyTask.setInstPlayerId(instPlayer.getId());
			instPlayerDailyTask.setRewardState(0);
			instPlayerDailyTask.setTimes(0);
			instPlayerDailyTask.setUpdateTime(currTime);
			instPlayerDailyTaskList.add(instPlayerDailyTask);
		}

		// 批量插入
		getInstPlayerDailyTaskDAL().batchAdd(instPlayerDailyTaskList);
	}

	/**
	 * 更新每日任务
	 * 
	 * @author mp
	 * @date 2014-12-29 下午3:48:59
	 * @Description
	 */
	public static void updateDailyTask(InstPlayer instPlayer, int dailyTashkId, int times, MessageData syncMsgData) throws Exception {

		// 只有开启第四个副本的时候才开始计数
		int count = getInstPlayerChapterDAL().getCount("instPlayerId = " + instPlayer.getId() + " and chapterId = 3 and isPass = 1");

		if (count != 0) {
			// 获取参数
			int instPlayerId = instPlayer.getId();
			String ymdDate = DateUtil.getYmdDate();
			DictDailyTask dictDailyTask = DictMap.dictDailyTaskMap.get(dailyTashkId + "");
			int finishTimes = dictDailyTask.getTimes();

			// 先验证是否跨天,如果跨天需重置每日任务,然后更新当前任务完成次数为1
			if (!instPlayer.getDailyTashTime().equals(ymdDate)) {
				List<InstPlayerDailyTask> instPlayerDailyTaskList = getInstPlayerDailyTaskDAL().getList("instPlayerId =  " + instPlayerId, instPlayerId);
				for (InstPlayerDailyTask obj : instPlayerDailyTaskList) {
					if (obj.getDailyTashkId() == dailyTashkId) {
						obj.setTimes(times > finishTimes ? finishTimes : times);
					} else {
						obj.setTimes(0);
					}
					obj.setRewardState(0);
					getInstPlayerDailyTaskDAL().update(obj, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, obj.getId(), obj.getResult(), syncMsgData);
				}

				// 更新每日任务日期-服务器用,不用给客户端同步
				instPlayer.setDailyTashTime(ymdDate);
				getInstPlayerDAL().update(instPlayer, instPlayerId);

				// 如果没跨天,直接更新此任务已完成次数
			} else {
				List<InstPlayerDailyTask> instPlayerDailyList = getInstPlayerDailyTaskDAL().getList("instPlayerId =  " + instPlayerId + " and dailyTashkId = " + dailyTashkId, instPlayerId);
				if (instPlayerDailyList.size() > 0) {
					InstPlayerDailyTask instPlayerDailyTask = instPlayerDailyList.get(0);
					if (instPlayerDailyTask.getTimes() < finishTimes) {
						instPlayerDailyTask.setTimes((instPlayerDailyTask.getTimes() + times) > finishTimes ? finishTimes : (instPlayerDailyTask.getTimes() + times));
						getInstPlayerDailyTaskDAL().update(instPlayerDailyTask, instPlayerId);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerDailyTask, instPlayerDailyTask.getId(), instPlayerDailyTask.getResult(), syncMsgData);
					}
				}
			}
		}
	}

	/**
	 * 注册账号时,初始化成就
	 * 
	 * @author hzw
	 * @date 2015-1-21下午3:38:10
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void initAchievement(InstPlayer instPlayer) throws Exception {
		int instPlayerId = instPlayer.getId();

		// 将欲插入的对象放入List,为批量插入做准备
		List<InstPlayerAchievement> instPlayerAchievementList = Lists.newArrayList();
		List<InstPlayerAchievementValue> instPlayerAchievementValueList = Lists.newArrayList();
		List<DictAchievementType> achievementTypeList = DictList.dictAchievementTypeList;
		for (DictAchievementType obj : achievementTypeList) {
			List<DictAchievement> dictAchievementList = (List<DictAchievement>) DictMapList.dictAchievementMap.get(obj.getId());
			if (dictAchievementList != null && dictAchievementList.size() > 0) {
				DictAchievement dictAchievement = dictAchievementList.get(0);
				InstPlayerAchievement instPlayerAchievement = new InstPlayerAchievement();
				instPlayerAchievement.setInstPlayerId(instPlayerId);
				instPlayerAchievement.setAchievementTypeId(obj.getId());
				instPlayerAchievement.setAchievementId(dictAchievement.getId());
				instPlayerAchievementList.add(instPlayerAchievement);

				if (obj.getDescription() != null && !obj.getDescription().equals("")) {
					InstPlayerAchievementValue instPlayerAchievementValue = new InstPlayerAchievementValue();
					instPlayerAchievementValue.setInstPlayerId(instPlayerId);
					instPlayerAchievementValue.setAchievementTypeId(obj.getId());
					instPlayerAchievementValueList.add(instPlayerAchievementValue);
				}
			}
		}

		// 批量插入成就实例数据
		getInstPlayerAchievementDAL().batchAdd(instPlayerAchievementList);

		// 批量插入成就计数实例数据
		getInstPlayerAchievementValueDAL().batchAdd(instPlayerAchievementValueList);

	}

	/**
	 * 初始化默认抢夺的功法法宝
	 * 
	 * @author hzw
	 * @date 2015-3-13上午11:36:10
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void initInstPlayerLoot(int instPlayerId) throws Exception {

		// 初始化默认抢夺的功法法宝
		InstPlayerLoot instPlayerLoot = new InstPlayerLoot();
		instPlayerLoot.setInstPlayerId(instPlayerId);
		instPlayerLoot.setProtectTime("0");
		instPlayerLoot.setSkills("");
		instPlayerLoot.setKungFus(DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId1) + "_" + 999);
		instPlayerLoot.setMagics(DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId2) + "_" + 999);
		getInstPlayerLootDAL().add(instPlayerLoot, instPlayerId);

	}

	/**
	 * 初始化限时抢购
	 * 
	 * @author hzw
	 * @date 2015-5-5上午11:15:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void initInstActivity(int instPlayerId) throws Exception {

		// 初始化限时抢购
		InstActivity instActivity = new InstActivity();
		instActivity.setInstPlayerId(instPlayerId);
		instActivity.setActivityId(StaticActivity.flashSale);
		instActivity.setActivityTime("");
		instActivity.setIsForever(1);
		instActivity.setUseNum(ConvertUtil.toInt((DateUtil.getCurrSec() + DictMapUtil.getSysConfigIntValue(StaticSysConfig.flashSaleDeadline) * 60 * 60)));
		getInstActivityDAL().add(instActivity, instPlayerId);

	}

	/**
	 * 元宝统计-不能以协议区分出来,比如商城购买不知道买的什么
	 * 
	 * @author mp
	 * @date 2015-4-27 下午12:00:08
	 * @param instPlayer
	 * @param type
	 * @param value
	 * @param msgMap
	 * @throws Exception
	 * @Description
	 */
	public static void goldStatics(InstPlayer instPlayer, int type, final int value, Map<String, Object> msgMap, String logParams) throws Exception {

		final int instPlayerId = instPlayer.getId();
//		final String openId = instPlayer.getOpenId();
		int operBeforeGoldNum = instPlayer.getGold();
		// thing.getName() + ";" + thing.getId() + ";" + num;
		String thingName = logParams.split(";")[0];
		String thingId = logParams.split(";")[1];
		String buyNum = logParams.split(";")[2];

//		String env = SysConfigUtil.getValue("env");

		int source = 0;// 来源-协议号

		String desc = "";// 来源描述

		if (msgMap != null) {

			source = (int) msgMap.get("header");

			desc = DictMap.sysMsgRuleMap.get(source + "").getName() + " " + thingName;
		}

		// 入库
		if (value != 0) {
			// 统一处理元宝的加减法
			if (type == GoldStaticsType.add) {
				instPlayer.setGold(instPlayer.getGold() + value);

/*				if (!env.equals("0")) {
					// 异步通知 腾讯直接赠送接口
					ThreadPoolUtils.execute(new ThreadOper() {
						@Override
						public void innerRun() {
							try {
								SDKUtil.present_m(openId, value + "");
							} catch (Exception e) {
								// e.printStackTrace();
							}
						}
					});
				}*/
			} else if (type == GoldStaticsType.del) {
				instPlayer.setGold(instPlayer.getGold() - value);

				// 判断是否在最强英雄活动期内,在的话，统计积分
				SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.StrongHero + "");
				if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
					if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= DateUtil.getCurrMill() && DateUtil.getCurrMill() <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
						
						//在活动期内,并且当前时间在23点以内
						long endTime = DateUtil.getMillSecond(DateUtil.getYmdDate()  + " 23:00:00");
						if (DateUtil.getCurrMill() <= endTime) {
							if (ParamConfig.strogerHeroJifenMap.containsKey(instPlayerId)) {
								int jifen = ParamConfig.strogerHeroJifenMap.get(instPlayerId) + value;
								ParamConfig.strogerHeroJifenMap.put(instPlayerId, jifen);
							} else {
								ParamConfig.strogerHeroJifenMap.put(instPlayerId, value);
							}
						}
						
					}
				}
				try {
					//统计累计消耗
					TotalCostManager.getInstance().costYuanbao(instPlayerId, Math.abs(value));
				} catch (Exception e) {
					LogUtil.error("统计累计消耗Error", e);
				}
				
/*				if (!env.equals("0")) {
					// 异步通知 腾讯扣费接口
					ThreadPoolUtils.execute(new ThreadOper() {
						@Override
						public void innerRun() {
							try {
								// 将结果以日志的方式记录起来（留个备份吧,万一有用到呢。去他妈的，不记录了）
								// Map<String, String> retMap = SDKUtil.pay_m(openId, value + "");
								SDKUtil.pay_m(openId, value + "");
								// 玩家id#玩家名字#玩家openId#协议描述#扣费金额#操作时间#,返回结果
								// String content = instPlayerId + "#" + playerName + "#" + openId + "#" + headerdesc + "#" + value + "#" + DateUtil.getCurrTime() + "#" + retMap.toString();
								// LogicLogUtil.info("paym", content);
							} catch (Exception e) {
								// e.printStackTrace();
							}
						}
					});
				}*/
			}
			int operAfterGoldNum = instPlayer.getGold();
			addInstPlayerGoldStatics(instPlayerId, type, value, source, desc, operBeforeGoldNum, operAfterGoldNum, thingId, thingName, buyNum);
		}
	}

	/**
	 * 元宝统计-能以协议区分出来
	 * 
	 * @author mp
	 * @date 2015-3-18 下午2:37:07
	 * @param instPlayer
	 * @param type
	 * @param value
	 * @param msgMap
	 * @Description
	 */
	public static void goldStatics(InstPlayer instPlayer, int type, final int value, Map<String, Object> msgMap) throws Exception {

		final int instPlayerId = instPlayer.getId();
//		final String openId = instPlayer.getOpenId();

		int operBeforeGoldNum = instPlayer.getGold();

//		String env = SysConfigUtil.getValue("env");

		int source = 0;// 来源-协议号

		String desc = "";// 来源描述

		if (msgMap != null) {

			source = (int) msgMap.get("header");

			desc = DictMap.sysMsgRuleMap.get(source + "").getName();
		}

//		Player player = PlayerMapUtil.getPlayerByOpenId(openId);

		// 入库
		if (value != 0) {
			// 统一处理元宝的加减法
			if (type == GoldStaticsType.add) {
				instPlayer.setGold(instPlayer.getGold() + value);
/*				if (!env.equals("0")) {
					if (player != null && player.getChannel() != null && !player.getChannel().equals("")) {
						if (player.getChannel().equals("qq") || player.getChannel().equals("wx")) {
							// 异步通知 腾讯直接赠送接口
							ThreadPoolUtils.execute(new ThreadOper() {
								@Override
								public void innerRun() {
									try {
										SDKUtil.present_m(openId, value + "");
									} catch (Exception e) {
										// e.printStackTrace();
									}
								}
							});
						}
					}
				}*/

			} else if (type == GoldStaticsType.del) {
				instPlayer.setGold(instPlayer.getGold() - value);

				// 判断是否在最强英雄活动期内,在的话，统计积分
				SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.StrongHero + "");
				if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
					if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= DateUtil.getCurrMill() && DateUtil.getCurrMill() <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
						//在活动期内,并且当前时间在23点以内
						long endTime = DateUtil.getMillSecond(DateUtil.getYmdDate()  + " 23:00:00");
						if (DateUtil.getCurrMill() <= endTime) {
							if (ParamConfig.strogerHeroJifenMap.containsKey(instPlayerId)) {
								int jifen = ParamConfig.strogerHeroJifenMap.get(instPlayerId) + value;
								ParamConfig.strogerHeroJifenMap.put(instPlayerId, jifen);
							} else {
								ParamConfig.strogerHeroJifenMap.put(instPlayerId, value);
							}
						}
					}
				}
				
				//统计累计消耗
				try {
					TotalCostManager.getInstance().costYuanbao(instPlayerId, Math.abs(value));
				} catch (Exception e) {
					LogUtil.error("统计累计消耗Error", e);
				}
				
/*				if (!env.equals("0")) {
					if (player != null && player.getChannel() != null && !player.getChannel().equals("")) {
						if (player.getChannel().equals("qq") || player.getChannel().equals("wx")) {
							// 异步通知 腾讯扣费接口
							ThreadPoolUtils.execute(new ThreadOper() {
								@Override
								public void innerRun() {
									try {
										// 将结果以日志的方式记录起来（留个备份吧,万一有用到呢。去他妈的，不记录了）
										// Map<String, String> retMap = SDKUtil.pay_m(openId, value + "");
										SDKUtil.pay_m(openId, value + "");
										// 玩家id#玩家名字#玩家openId#协议描述#扣费金额#操作时间#,返回结果
										// String content = instPlayerId + "#" + playerName + "#" + openId + "#" + headerdesc + "#" + value + "#" + DateUtil.getCurrTime() + "#" + retMap.toString();
										// LogicLogUtil.info("paym", content);
									} catch (Exception e) {
										// e.printStackTrace();
									}
								}
							});
						}
					}
				}*/
			}
			int operAfterGoldNum = instPlayer.getGold();
			addInstPlayerGoldStatics(instPlayerId, type, value, source, desc, operBeforeGoldNum, operAfterGoldNum, "", "", "");
		}
	}

	/**
	 * 添加元宝统计记录
	 * 
	 * @author mp
	 * @date 2015-3-27 上午11:12:08
	 * @param instPlayerId
	 * @param type
	 * @param value
	 * @param source
	 * @param desc
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerGoldStatics(int instPlayerId, int type, int value, int source, String desc, int operBeforeGoldNum, int operAfterGoldNum, String thingId, String thingName, String buyNum) throws Exception {
		InstPlayerGoldStatics instPlayerGoldStatics = new InstPlayerGoldStatics();
		instPlayerGoldStatics.setInstPlayerId(instPlayerId);
		instPlayerGoldStatics.setType(type);
		instPlayerGoldStatics.setValue(value);
		instPlayerGoldStatics.setSource(source);
		instPlayerGoldStatics.setDescrption(desc);
		instPlayerGoldStatics.setOperDate(DateUtil.getYmdDate());
		instPlayerGoldStatics.setOperTime(DateUtil.getCurrTime());
		getInstPlayerGoldStaticsDAL().add(instPlayerGoldStatics, 0);
		// 1-得到 2-失去
		int operType = 0;
		if (type == GoldStaticsType.del) {
			operType = 2;
		} else if (type == GoldStaticsType.add) {
			operType = 1;
		}
		// 记录元宝加减法日志
		try {
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + operType + "|" + operBeforeGoldNum + "|" + operAfterGoldNum + "|" + value + "|" + source + "|" + desc + "|" + thingId + "|" + thingName + "|" + buyNum;
			LogicLogUtil.info("gold", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("元宝操作日志错误", e);
		}

	}

	/**
	 * 添加充值记录
	 * 
	 * @author mp
	 * @date 2015-3-27 上午11:44:39
	 * @param retMap
	 * @param channel
	 * @param instPlayerId
	 * @param openId
	 * @param payChannel
	 * @param playerName
	 * @param saveAmt
	 * @param saveNum
	 * @param thisamt
	 * @param thisrmb
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerRecharge(Map<String, Object> retMap, String channel, int instPlayerId, String openId, int payChannel, String playerName, int saveAmt, int saveNum, int thisamt, int thisrmb, String source) throws Exception {
		InstPlayerRecharge instPlayerRecharge = new InstPlayerRecharge();
		instPlayerRecharge.setBalance((int) (double) retMap.get("balance"));
		instPlayerRecharge.setChannel(channel);
		instPlayerRecharge.setGenbalance((int) (double) retMap.get("gen_balance"));
		instPlayerRecharge.setInstPlayerId(instPlayerId);
		instPlayerRecharge.setOpenId(openId);
		instPlayerRecharge.setOperateDate(DateUtil.getYmdDate());
		instPlayerRecharge.setOperateTime(DateUtil.getCurrTime());
		instPlayerRecharge.setPayChannel(payChannel);
		instPlayerRecharge.setPlayerName(playerName);
		instPlayerRecharge.setSaveamt(saveAmt);
		instPlayerRecharge.setSaveNum(saveNum);
		instPlayerRecharge.setThisamt(thisamt);
		instPlayerRecharge.setThisrmb(thisrmb);
		instPlayerRecharge.setSource(source);
		getInstPlayerRechargeDAL().add(instPlayerRecharge, 0);
	}

	/**
	 * Gm工具充值记录
	 * @author mp
	 * @date 2015-9-11 上午11:23:44
	 * @param retMap
	 * @param channel
	 * @param instPlayerId
	 * @param openId
	 * @param payChannel
	 * @param playerName
	 * @param saveAmt
	 * @param saveNum
	 * @param thisamt
	 * @param thisrmb
	 * @param source
	 * @param dictRecharge
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerRechargeGm(Map<String, Object> retMap, String channel, int instPlayerId, String openId, int payChannel, String playerName, int saveAmt, int saveNum, int thisamt, int thisrmb, String source, DictRecharge dictRecharge) throws Exception {
		InstPlayerRecharge instPlayerRecharge = new InstPlayerRecharge();
		instPlayerRecharge.setBalance((int) (double) retMap.get("balance"));
		instPlayerRecharge.setChannel(channel);
		instPlayerRecharge.setGenbalance((int) (double) retMap.get("gen_balance"));
		instPlayerRecharge.setInstPlayerId(instPlayerId);
		instPlayerRecharge.setOpenId(openId);
		instPlayerRecharge.setOperateDate(DateUtil.getYmdDate());
		instPlayerRecharge.setOperateTime(DateUtil.getCurrTime());
		instPlayerRecharge.setPayChannel(payChannel);
		instPlayerRecharge.setPlayerName(playerName);
		instPlayerRecharge.setSaveamt(saveAmt);
		instPlayerRecharge.setSaveNum(saveNum);
		instPlayerRecharge.setThisamt(thisamt);
		instPlayerRecharge.setThisrmb(thisrmb);
		instPlayerRecharge.setSource(source);
		instPlayerRecharge.setGoodsId(dictRecharge.getId());
		getInstPlayerRechargeDAL().add(instPlayerRecharge, 0);
	}
	
	/**
	 * 组织充值延迟玩家对象
	 * 
	 * @author mp
	 * @date 2015-3-27 下午4:39:00
	 * @param channel
	 * @param instPlayerId
	 * @param openId
	 * @param payChannel
	 * @param playerName
	 * @return
	 * @Description
	 */
	public static DelayRechargePlayer orgDelayRechargePlayer(String channel, int instPlayerId, String openId, int payChannel, String playerName, int saveNum) {
		DelayRechargePlayer delayRechargePlayer = new DelayRechargePlayer();
		delayRechargePlayer.setChannel(channel);
		delayRechargePlayer.setCurrMill(DateUtil.getCurrMill());
		delayRechargePlayer.setInstPlayerId(instPlayerId);
		delayRechargePlayer.setOpenId(openId);
		delayRechargePlayer.setPayChannel(payChannel);
		delayRechargePlayer.setPlayerName(playerName);
		delayRechargePlayer.setSaveNum(saveNum);
		return delayRechargePlayer;
	}

	/**
	 * 根据充值总金额,获取vip等级
	 * 
	 * @author mp
	 * @date 2015-3-27 下午2:54:15
	 * @param instPlayer
	 * @param saveAmt
	 * @return
	 * @Description
	 */
	public static int getVipLevel(InstPlayer instPlayer, int savermb) {
		int vipLevel = instPlayer.getVipLevel();
		for (DictVIP dictVIP : DictList.dictVIPList) {
			if (savermb >= dictVIP.getLimit()) {
				vipLevel = dictVIP.getLevel();
			}
		}
		return vipLevel;
	}

	/**
	 * 获取充值额外赠送的元宝数
	 * 
	 * @author mp
	 * @date 2015-3-27 下午2:52:14
	 * @param thisrmb
	 * @param instPlayerRechargeList
	 * @param openId
	 * @return
	 * @Description
	 */
	public static int getFreeGold(int thisrmb, List<InstPlayerRecharge> instPlayerRechargeList, final String openId) throws Exception{

		int freeGold = 0;

		// 找到玩家充的是哪一档
		DictRecharge dictRecharge = null;
		for (DictRecharge obj : DictList.dictRechargeList) {
			if (thisrmb == obj.getRmb() && obj.getFirstAmt() > 0) {
				dictRecharge = obj;
				break;
			}
		}

		// 查看是否有本档的充值记录
		if (dictRecharge != null) {
			int isRecharge = 0;
			for (InstPlayerRecharge obj : instPlayerRechargeList) {
				
				//30元非月卡
				if (dictRecharge.getRmb() == 30) {
					if (obj.getThisrmb() == dictRecharge.getRmb() && obj.getGoodsId() != 1) {
						if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
							SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
							long recharegeTime = DateUtil.getMillSecond(obj.getOperateTime());
							if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
								isRecharge = 1;
								break;
							}
						} else {
							isRecharge = 1;
							break;
						}
					}
				//50、68元非月卡
				} else if (dictRecharge.getRmb() == 50 || dictRecharge.getRmb() == 68) {
					if (obj.getThisrmb() == dictRecharge.getRmb() && obj.getGoodsId() != 9) {
						if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
							SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
							long recharegeTime = DateUtil.getMillSecond(obj.getOperateTime());
							if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
								isRecharge = 1;
								break;
							}
						} else {
							isRecharge = 1;
							break;
						}
					}
				//其他金额
				} else {
					if (obj.getThisrmb() == dictRecharge.getRmb()) {
						
						if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
							SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
							long recharegeTime = DateUtil.getMillSecond(obj.getOperateTime());
							if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
								isRecharge = 1;
								break;
							}
						} else {
							isRecharge = 1;
							break;
						}
						
//						isRecharge = 1;
//						break;
					}
				}
				
			}

			// 如果本档没充过钱,赠送首充元宝
			if (isRecharge == 0) {
				freeGold = dictRecharge.getFirstAmt();
			} else {
				if (dictRecharge.getNoFirstAmt() == -1) {
					freeGold = 0;
				}
				freeGold = dictRecharge.getNoFirstAmt();
			}
			final int value = freeGold;

			Player player = PlayerMapUtil.getPlayerByOpenId(openId);
			if (player != null && player.getChannel() != null && !player.getChannel().equals("")) {
				if (player.getChannel().equals("qq") || player.getChannel().equals("wx")) {
					// 异步通知 腾讯直接赠送接口
					ThreadPoolUtils.execute(new ThreadOper() {
						@Override
						public void innerRun() {
							try {
								SDKUtil.present_m(openId, value + "");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}

		}
		return freeGold;
	}

	/**
	 * 获取充值额外赠送的元宝数 - 不通知腾讯 - GM用
	 * 
	 * @author mp
	 * @date 2015-5-1 下午4:14:11
	 * @param thisrmb
	 * @param instPlayerRechargeList
	 * @param openId
	 * @return
	 * @Description
	 */
	public static int getFreeGold(int thisrmb, List<InstPlayerRecharge> instPlayerRechargeList) throws Exception{

		int freeGold = 0;

		// 找到玩家充的是哪一档
		DictRecharge dictRecharge = null;
		for (DictRecharge obj : DictList.dictRechargeList) {
			if (thisrmb == obj.getRmb() && obj.getFirstAmt() != 0) {
				dictRecharge = obj;
				break;
			}
		}

		// 查看是否有本档的充值记录
		if (dictRecharge != null) {
			int isRecharge = 0;
			for (InstPlayerRecharge obj : instPlayerRechargeList) {
				
				//30元非月卡
				if (dictRecharge.getRmb() == 30) {
					if (obj.getThisrmb() == dictRecharge.getRmb() && obj.getGoodsId() != 1) {
						if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
							SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
							long recharegeTime = DateUtil.getMillSecond(obj.getOperateTime());
							if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
								isRecharge = 1;
								break;
							}
						} else {
							isRecharge = 1;
							break;
						}
					}
				//50 、68元非月卡
				} else if (dictRecharge.getRmb() == 50 || dictRecharge.getRmb() == 68) {
					if (obj.getThisrmb() == dictRecharge.getRmb() && obj.getGoodsId() != 9) {
						if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
							SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
							long recharegeTime = DateUtil.getMillSecond(obj.getOperateTime());
							if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
								isRecharge = 1;
								break;
							}
						} else {
							isRecharge = 1;
							break;
						}
					}
				//其他金额
				} else {
					if (obj.getThisrmb() == dictRecharge.getRmb()) {
						
						if (ActivityUtil.isInActivity(StaticActivity.rechargeDouble)) {
							SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.rechargeDouble + "");
							long recharegeTime = DateUtil.getMillSecond(obj.getOperateTime());
							if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= recharegeTime && recharegeTime <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
								isRecharge = 1;
								break;
							}
						} else {
							isRecharge = 1;
							break;
						}
						
//						isRecharge = 1;
//						break;
					}
				}
				
			}

			// 如果本档没充过钱,赠送首充元宝
			if (isRecharge == 0) {
				freeGold = dictRecharge.getFirstAmt();
			} else {
				if (dictRecharge.getNoFirstAmt() == -1) {
					freeGold = 0;
				}
				freeGold = dictRecharge.getNoFirstAmt();
			}
		}
		return freeGold;
	}

	/**
	 * 充值回调日志
	 * 
	 * @author mp
	 * @date 2015-3-25 下午5:24:29
	 * @param msgMap
	 * @param instPlayerId
	 * @param playerName
	 * @param openId
	 * @Description
	 */
	public static void rechargeCallBackLog(Map<String, Object> msgMap, int instPlayerId, String playerName, String openId) throws Exception {
		int resultCode = (int) msgMap.get("resultCode");// 结果码
		int payChannel = (int) msgMap.get("payChannel");// 支付渠道
		int payState = (int) msgMap.get("payState");// 支付状态
		int provideState = (int) msgMap.get("provideState");// 发货状态
		int saveNum = (int) msgMap.get("saveNum");// 下单成功时购买的数量(游戏币)
		String resultMsg = (String) msgMap.get("resultMsg");// 返回信息
		String extendInfo = (String) msgMap.get("extendInfo");// 扩展信息

		// 将客户端支付回调结果入库
		InstPlayerRechargeCallBack instPlayerRechargeCallBack = new InstPlayerRechargeCallBack();
		instPlayerRechargeCallBack.setInstPlayerId(instPlayerId);
		instPlayerRechargeCallBack.setPlayerName(playerName);
		instPlayerRechargeCallBack.setOpenId(openId);
		instPlayerRechargeCallBack.setResultCode(resultCode);
		instPlayerRechargeCallBack.setPayChannel(payChannel);
		instPlayerRechargeCallBack.setPayState(payState);
		instPlayerRechargeCallBack.setProvideState(provideState);
		instPlayerRechargeCallBack.setSaveNum(saveNum);
		instPlayerRechargeCallBack.setResultMsg(resultMsg);
		instPlayerRechargeCallBack.setExtendInfo(extendInfo);
		instPlayerRechargeCallBack.setOperDate(DateUtil.getYmdDate());
		instPlayerRechargeCallBack.setOperTime(DateUtil.getCurrTime());
		getInstPlayerRechargeCallBackDAL().add(instPlayerRechargeCallBack, 0);
	}

	/**
	 * 队列处理充值
	 * 
	 * @author mp
	 * @date 2015-3-30 上午11:18:24
	 * @throws Exception
	 * @Description
	 */
	public static void rechargeQueue() throws Exception {
		Object[] blockingQueues = ParamConfig.blockingQueue.toArray();
		for (int i = 0; i < blockingQueues.length; i++) {
			DelayRechargePlayer delayRechargePlayer = (DelayRechargePlayer) blockingQueues[i];

			// 先验证时间是否已超过2分钟(腾讯说两分钟内能到账),如果超过从队列中移除
			long interval = DateUtil.getCurrMill() - delayRechargePlayer.getCurrMill();
			if (interval > 2 * 60 * 1000) {
				ParamConfig.blockingQueue.remove(delayRechargePlayer);
			} else {
				// 处理充值逻辑
				int saveAmt = 0;
				int lastSaveAmt = 0;
				int saveNum = delayRechargePlayer.getSaveNum();
				String openId = delayRechargePlayer.getOpenId();
				String channel = delayRechargePlayer.getChannel();
				int payChannel = delayRechargePlayer.getPayChannel();
				String playerName = delayRechargePlayer.getPlayerName();
				int instPlayerId = delayRechargePlayer.getInstPlayerId();
				Map<String, Object> retMap = SDKUtil.get_balance_m(openId);

				if (retMap != null && (Double) retMap.get("ret") == 0) {
					MessageData syncMsgData = new MessageData();
					saveAmt = (int) (double) retMap.get("save_amt");// 腾讯端累计充值金额（元宝）
					// 倒叙排序
					List<InstPlayerRecharge> instPlayerRechargeList = DALFactory.getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
					Collections.sort(instPlayerRechargeList, new Comparator<Object>() {
						public int compare(Object a, Object b) {
							int one = ((InstPlayerRecharge) a).getSaveamt();
							int two = ((InstPlayerRecharge) b).getSaveamt();
							return (int) (two - one);
						}
					});
					if (instPlayerRechargeList.size() > 0) {
						lastSaveAmt = instPlayerRechargeList.get(0).getSaveamt();// 数据库中存储的上次累计充值金额
					}
					if (saveAmt > lastSaveAmt) {
						int thisamt = saveAmt - lastSaveAmt;// 本次充值金额(游戏币)
						int thisrmb = thisamt / 10;// 本次充值金额(人民币)
						InstPlayer instPlayer = DALFactory.getInstPlayerDAL().getModel(instPlayerId, 0);
						int operBeforeGoldNum = instPlayer.getGold();

						int thisGold = 0;
						// 月卡跟普通充值得到的元宝规则不一致
						DictRecharge recharge = null;
						for (DictRecharge obj : DictList.dictRechargeList) {
							if (thisrmb == obj.getRmb() && obj.getFirstAmt() == 0) {
								recharge = obj;
								break;
							}
						}
						List<InstActivity> instActivityList = null;
						if (recharge != null) {
							instActivityList = getInstActivityDAL().getList(" instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and useNum = " + recharge.getId() + " and (activityTime = \"\" or activityTime is null)", instPlayerId);
						}
						if (recharge != null && thisrmb == recharge.getRmb() && recharge.getFirstAmt() == 0 && instActivityList.size() > 0) {
							thisGold = thisrmb * DictMapUtil.getSysConfigIntValue(StaticSysConfig.monthCardTimes);
							instPlayer.setGold(instPlayer.getGold() + thisGold);

							// 确认月卡实例数据生效
							ActivityUtil.addInstActivity(instPlayerId, instActivityList, syncMsgData, 2);
						} else {
							// 处理额外赠送元宝（首充/非首充/没有这一档）
							int freeGold = PlayerUtil.getFreeGold(thisrmb, instPlayerRechargeList, openId);

							// 处理元宝 赠送的元宝 + 充值的元宝
							thisGold = freeGold + thisamt;
							instPlayer.setGold(instPlayer.getGold() + thisGold);
						}

						// 处理vip逻辑-根据总充值rmb金额,计算vip等级
						int vipLevel = PlayerUtil.getVipLevel(instPlayer, saveAmt / 10);
						instPlayer.setVipLevel(vipLevel);

						int operAfterGoldNum = instPlayer.getGold();

						// 处理并发-100次碰撞,每次碰撞间隔1毫秒
						int times = 0;
						List<InstPlayer> instPlayerList = new ArrayList<InstPlayer>();
						instPlayerList.add(instPlayer);
						for (int j = 0; j < instPlayerList.size(); j++) {
							try {
								InstPlayer instPlayer2 = instPlayerList.get(j);
								DALFactory.getInstPlayerDAL().update(instPlayer2, instPlayerId);
								OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer2, instPlayer2.getId(), instPlayer2.getResult(), syncMsgData);
								Player player = PlayerMapUtil.getPlayerByOpenId(openId);

								// 更新玩家成就计数实例数据
								AchievementUtil.updateAchievement(instPlayer2.getId(), StaticAchievementType.vip, vipLevel, syncMsgData, null);

								if (player != null) {
									MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
								}

								// 记录充值日志
								PlayerUtil.addInstPlayerRecharge(retMap, channel, instPlayerId, openId, payChannel, playerName, saveAmt, saveNum, thisamt, thisrmb, "队列");

								PlayerUtil.addInstPlayerGoldStatics(instPlayerId, GoldStaticsType.add, thisGold, 0, "充值(含赠送游戏币)", operBeforeGoldNum, operAfterGoldNum, "", "", "");

								// 检查已到账,处理成功后,将本条从队列中去除
								ParamConfig.blockingQueue.remove(delayRechargePlayer);

							} catch (Exception e) {
								if (times < 100) {
									InstPlayer instPlayer2 = DALFactory.getInstPlayerDAL().getModel(instPlayerId, 0);
									instPlayer2.setGold(instPlayer2.getGold() + thisGold);
									instPlayer2.setVipLevel(vipLevel);
									instPlayerList.add(instPlayer2);
									times++;
									TimeUnit.MILLISECONDS.sleep(1);// 碰撞间隔1毫秒
								} else {
									LogUtil.error("充值碰撞100次,未成功 ", e);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 登录处理充值逻辑
	 * 
	 * @author mp
	 * @date 2015-3-30 上午11:25:56
	 * @param instPlayer
	 * @param channel
	 * @throws Exception
	 * @Description
	 */
	public static void rechargeLogin(InstPlayer instPlayer, String channel) throws Exception {/*
		// 处理充值逻辑
		int saveAmt = 0;
		int lastSaveAmt = 0;
		int saveNum = 0;
		String openId = instPlayer.getOpenId();
		int payChannel = 0;
		String playerName = instPlayer.getName();
		int instPlayerId = instPlayer.getId();
		int operBeforeGoldNum = instPlayer.getGold();

		Map<String, Object> retMap = SDKUtil.get_balance_m(openId);

		if (retMap != null && (Double) retMap.get("ret") == 0) {
			saveAmt = (int) (double) retMap.get("save_amt");// 腾讯端累计充值金额（元宝）
			// 倒叙排序
			List<InstPlayerRecharge> instPlayerRechargeList = DALFactory.getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
			Collections.sort(instPlayerRechargeList, new Comparator<Object>() {
				public int compare(Object a, Object b) {
					int one = ((InstPlayerRecharge) a).getSaveamt();
					int two = ((InstPlayerRecharge) b).getSaveamt();
					return (int) (two - one);
				}
			});
			if (instPlayerRechargeList.size() > 0) {
				lastSaveAmt = instPlayerRechargeList.get(0).getSaveamt();// 数据库中存储的上次累计充值金额
			}
			if (saveAmt > lastSaveAmt) {
				int thisamt = saveAmt - lastSaveAmt;// 本次充值金额(游戏币)
				int thisrmb = thisamt / 10;// 本次充值金额(人民币)

				int thisGold = 0;
				// 月卡跟普通充值得到的元宝规则不一致
				DictRecharge recharge = null;
				for (DictRecharge obj : DictList.dictRechargeList) {
					if (thisrmb >= obj.getRmb() && obj.getFirstAmt() == 0) {
						recharge = obj;
						break;
					}
				}
				List<InstActivity> instActivityList = null;
				if (recharge != null) {
					instActivityList = getInstActivityDAL().getList(" instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and useNum = " + recharge.getId() + " and (activityTime = \"\" or activityTime is null)", instPlayerId);
				}
				if (recharge != null && thisrmb >= recharge.getRmb() && recharge.getFirstAmt() == 0 && instActivityList.size() > 0 && instActivityList.get(0).getActivityTime().equals("")) {
					thisGold = recharge.getRmb() * DictMapUtil.getSysConfigIntValue(StaticSysConfig.monthCardTimes);
					instPlayer.setGold(instPlayer.getGold() + thisGold);

					thisrmb = thisrmb - recharge.getRmb();

					// 确认月卡实例数据生效
					MessageData syncMsgData = new MessageData();
					ActivityUtil.addInstActivity(instPlayerId, instActivityList, syncMsgData, 2);
				}
				if (thisrmb > 0) {
					// 处理额外赠送元宝（首充/非首充/没有这一档）
					int freeGold = PlayerUtil.getFreeGold(thisrmb, instPlayerRechargeList, openId);

					// 处理元宝 赠送的元宝 + 充值的元宝
					thisGold = freeGold + thisrmb * 10;
					instPlayer.setGold(instPlayer.getGold() + thisGold);
				}

				int operAfterGoldNum = instPlayer.getGold();

				// 处理vip逻辑-根据总充值rmb金额,计算vip等级
				int vipLevel = PlayerUtil.getVipLevel(instPlayer, saveAmt / 10);
				instPlayer.setVipLevel(vipLevel);

				DALFactory.getInstPlayerDAL().update(instPlayer, instPlayerId);

				// 更新玩家成就计数实例数据
				AchievementUtil.updateAchievement(instPlayer.getId(), StaticAchievementType.vip, vipLevel, new MessageData(), null);

				// 记录充值日志
				PlayerUtil.addInstPlayerRecharge(retMap, channel, instPlayerId, openId, payChannel, playerName, saveAmt, saveNum, thisamt, thisrmb, "登录");

				PlayerUtil.addInstPlayerGoldStatics(instPlayerId, GoldStaticsType.add, thisGold, 0, "充值(含赠送游戏币)", operBeforeGoldNum, operAfterGoldNum, "", "", "");
			}
		}
	*/}

	/**
	 * 更新活动补丁[内网时必走验证,外网时只验证1服,其他服不验证]
	 * 
	 * @author mp
	 * @date 2015-5-5 下午6:01:51
	 * @param instPlayer
	 * @param env
	 * @param msgMap
	 * @throws Exception
	 * @Description
	 */
	public static void updateActivity(InstPlayer instPlayer, String env, Map<String, Object> msgMap) throws Exception {
		if (!env.equals("0")) {
			String zoneid = (String) msgMap.get("zoneid");// 服务器编号
			if (zoneid != null && zoneid.equals("1")) {
				// 初始化活动
				int activitieCount = getInstActivityDAL().getCount("instPlayerId = " + instPlayer.getId() + " and activityId = " + StaticActivity.flashSale);
				if (activitieCount == 0) {
					PlayerUtil.initInstActivity(instPlayer.getId());
				}

				// 初始化活动
				activitieCount = getInstActivityDAL().getCount("instPlayerId = " + instPlayer.getId() + " and activityId = " + StaticActivity.addRecharge);
				if (activitieCount == 0) {
					ActivityUtil.addInstActivity(instPlayer.getId(), StaticActivity.addRecharge, new MessageData());
				}
			}
		} else {
			// 初始化活动
			int activitieCount = getInstActivityDAL().getCount("instPlayerId = " + instPlayer.getId() + " and activityId = " + StaticActivity.flashSale);
			if (activitieCount == 0) {
				PlayerUtil.initInstActivity(instPlayer.getId());
			}

			// 初始化活动
			activitieCount = getInstActivityDAL().getCount("instPlayerId = " + instPlayer.getId() + " and activityId = " + StaticActivity.addRecharge);
			if (activitieCount == 0) {
				ActivityUtil.addInstActivity(instPlayer.getId(), StaticActivity.addRecharge, new MessageData());
			}
		}
	}

	/**
	 * 如果充值队列中有此玩家,将此玩家移除
	 * 
	 * @author mp
	 * @date 2015-5-6 下午5:29:01
	 * @param player
	 * @Description
	 */
	public static void removeDelayRechargePlayer(Player player) {
		Object[] blockingQueues = ParamConfig.blockingQueue.toArray();
		for (int i = 0; i < blockingQueues.length; i++) {
			DelayRechargePlayer delayRechargePlayer = (DelayRechargePlayer) blockingQueues[i];
			if (delayRechargePlayer.getInstPlayerId() == player.getPlayerId()) {
				ParamConfig.blockingQueue.remove(delayRechargePlayer);
			}
		}
	}

	/**
	 * 添加邮件
	 * 
	 * @author mp
	 * @date 2015-7-28 下午2:14:45
	 * @param oppoPlayer
	 * @param oppoPlayerId
	 * @param oppoContent
	 * @param ownPlayer
	 * @throws Exception
	 * @Description
	 */
	public static void addPlayerMail(Player oppoPlayer, int oppoPlayerId, String oppoContent, int value, int results, String enemyName, int type) throws Exception {
		// 插入前如果达到保留条数上限,删除时间最靠后的n条
		List<InstPlayerMail> instPlayerMailList = getInstPlayerMailDAL().getList("instPlayerId = " + oppoPlayerId + " and type = " + type, 0);// getList为按id的顺序排序(id顺序,时间则为倒序)
		int deleteNum = 0;
		if (instPlayerMailList.size() > DictMapUtil.getSysConfigIntValue(StaticSysConfig.mailReceiveNum)) {
			deleteNum = instPlayerMailList.size() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.mailReceiveNum) + 1;
			for (int i = 0; i < deleteNum; i++) {
				InstPlayerMail oppoPlayerMail = instPlayerMailList.get(i);
				getInstPlayerMailDAL().deleteById(oppoPlayerMail.getId(), 0);
				// 如果对方在线,为对方同步删除消息
				if (oppoPlayer != null) {
					MessageData oppoSyncData = new MessageData();
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, oppoPlayerMail, oppoPlayerMail.getId(), "", oppoSyncData);
					MessageUtil.sendSyncMsg(oppoPlayer.getChannelId(), oppoSyncData);
				}
			}
		}
		InstPlayerMail instPlayerMail = new InstPlayerMail();
		instPlayerMail.setInstPlayerId(oppoPlayerId);
		instPlayerMail.setEnemyName(enemyName);
		instPlayerMail.setType(type);// 1-抢夺 2-竞技场 3-社交 4-系统
		instPlayerMail.setValue(value);
		instPlayerMail.setResults(results);
		instPlayerMail.setContent(oppoContent);
		instPlayerMail.setInsertTime(DateUtil.getCurrTime());
		getInstPlayerMailDAL().add(instPlayerMail, 0);
		// 如果对方在线,为对方同步添加消息
		if (oppoPlayer != null) {
			MessageData oppoSyncData = new MessageData();
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerMail, instPlayerMail.getId(), instPlayerMail.getResult(), oppoSyncData);
			MessageUtil.sendSyncMsg(oppoPlayer.getChannelId(), oppoSyncData);
		}
	}

	/**
	 * 引导步骤
	 * 
	 * @author mp
	 * @date 2015-8-6 上午10:17:55
	 * @Description
	 */
	public static String guidStep(String step, InstPlayer instPlayer, Map<String, Object> msgMap, MessageData syncMsgData) throws Exception {
		String retMsg = "a";
		int currLevelOrBarrier = 0;
		int currStep = 0;
		int type = 0;
		String givedLevelGuidStep = "";
		int instPlayerId = instPlayer.getId();

		// 处理等级相关的引导
		if (step.contains("_")) {
			type = 1;
			currLevelOrBarrier = ConvertUtil.toInt(step.split("_")[0]);
			currStep = ConvertUtil.toInt(step.split("_")[1]);
			if (instPlayer.getGuidStep().substring(instPlayer.getGuidStep().indexOf('&') + 1, instPlayer.getGuidStep().length()).length() > 1) {
				givedLevelGuidStep = instPlayer.getGuidStep().split("&")[1];
			}
		} else {
			// 处理关卡相关的引导
			type = 2;
			currLevelOrBarrier = ConvertUtil.toInt(step.split("B")[0]);
			currStep = ConvertUtil.toInt(step.split("B")[1]);
			
			//判断客户端传来的步骤不能小于当前步骤
			if(instPlayer.getGuipedBarrier().substring(0, instPlayer.getGuipedBarrier().indexOf('&')).length() > 1){
				String record = instPlayer.getGuipedBarrier().split("&")[0];
				int recordBarrier = ConvertUtil.toInt(record.split("B")[0]);
				
//				记录关卡和客户端传的关卡号如果相隔大于1
//				System.out.println("recordBarrier=" + recordBarrier + "   currLevelOrBarrier=" + currLevelOrBarrier);
				if (recordBarrier - currLevelOrBarrier > 1) {
					return "";
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

		// 需要时,送物品
		for (DictGuipStep obj : DictList.dictGuipStepList) {
			if (obj.getType() == type && obj.getLevelOrBarrierId() == currLevelOrBarrier && obj.getStep() == currStep) {

				// 验证此等级步骤是否已经送过物品[初始化玩家的时候,将'&',写入]
				if (StringUtil.contains(givedLevelGuidStep, step, ";")) {
					return retMsg;
				}
				String thingString = obj.getThings();
				for (String thing : thingString.split(";")) {
					int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
					int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
					int tableValue = ConvertUtil.toInt(thing.split("_")[2]);
					ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, tableValue, syncMsgData, msgMap);
				}

				// 记录此给物品的等级和步骤
				givedLevelGuidStep = givedLevelGuidStep + step + ";";
			}
		}

		String guidStep = step + "&" + givedLevelGuidStep;

		// 记录步骤
		if (type == 1) {
			instPlayer.setGuidStep(guidStep);
		} else {
			instPlayer.setGuipedBarrier(guidStep);
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		return retMsg;
	}

	/**
	 * 充值返利,首次登录（注册）处理
	 * 
	 * @author mp
	 * @date 2015-8-13 上午11:39:01
	 * @param openId
	 * @param instPlayer
	 * @param zoneid
	 * @param channel
	 * @param accountId
	 * @throws Exception
	 * @Description
	 */
	public static int rechargeRetGold(String openId, InstPlayer instPlayer, String zoneid, String channel, String accountId) throws Exception {
		int isAward = 0;
		if (ParamConfig.rechargeMap.containsKey(openId)) {
			// 验证是否有返利信息
			String rechargeRetGoldUrl = SysConfigUtil.getValue("cdk.verfy.rechargeRetGold");
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("openId", openId);
			paramMap.put("serverId", zoneid);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String result = "";
			try {
				result = HttpClientUtil.postMapSubmit(rechargeRetGoldUrl, params);
				if (result != null) {
					JSONObject loginJson = new JSONObject(result);
					String code = String.valueOf(loginJson.get("code"));
					if (code.equals("0")) {
						isAward = 1;
						// 记录返利信息
						int retSumRmb = ParamConfig.rechargeMap.get(openId);// 一共充了多少钱
						int shoudGold = retSumRmb * 10;// 应该返多少元宝
						int retSumGold = retSumRmb * 10 * DictMapUtil.getSysConfigIntValue(StaticSysConfig.rechargeRetGoldNum);// 一共能返多少元宝
						int currRetGold = ConvertUtil.toInt(shoudGold * ParamConfig.rechargeRetRuleMap.get(1));// 本次返了多少元宝

						InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
						instPlayerBigTable.setInstPlayerId(instPlayer.getId());
						instPlayerBigTable.setProperties(StaticBigTable.rechargeRetGold);
						instPlayerBigTable.setValue1(DateUtil.getYmdDate());// 领取时间,用分号分开
						instPlayerBigTable.setValue2(currRetGold + "");// 领取元宝数,用分号分开
						instPlayerBigTable.setValue3(retSumGold + "");// 总共能领多少元宝-只有初始化的时候插入
						instPlayerBigTable.setValue4(shoudGold + "");// 应该返多少元宝-只有初始化的时候插入
						getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);

						// 将本次返利发到领奖中心 name-名目 1-天焚炼气塔 2-竞技场 3-系统 4-世界Boss
						ActivityUtil.addInstPlayerAward(instPlayer.getId(), 3, "3_1_" + currRetGold, DateUtil.getCurrTime(), "计费删档期间老玩家第1次充值返利:", new MessageData());

						// 插入充值记录,计算vip并更新
						// RechargeUtil.retGoldRecordRecharge(retSumRmb, instPlayer.getId(), openId, channel, Integer.valueOf(zoneid), accountId);

						int vipLevel = PlayerUtil.getVipLevel(instPlayer, retSumRmb);

						instPlayer.setVipLevel(vipLevel);
						getInstPlayerDAL().update(instPlayer, 0);
					}
				}
			} catch (Exception e) {
				LogUtil.error("充值返利返回后处理异常", e);
				isAward = 0;
			}
		}
		return isAward;
	}

	public static boolean costThingOrProperty(InstPlayer instPlayer, String[] list) {
		boolean result = true;

		return result;
	}



}
