package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictWorldBossReward;
import com.huayi.doupo.base.model.DictWorldBossStore;
import com.huayi.doupo.base.model.DictWorldBossTimesReward;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstWorldBoss;
import com.huayi.doupo.base.model.InstWorldBossRank;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.WorldBossPlayer;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticDailyTask;
import com.huayi.doupo.base.model.statics.StaticMsgRule;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.quartz.WorldBossStartHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.VerifyUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.WorldBossPlayerMapUtil;

public class WorldBossHandler extends BaseHandler{
	
	public static final String SEP_BAR = "|";   // 字符串分割
	
	public static final String SEP_SEM = "/";   // 字符串分割
	
	public static final String NULL = "";   	// 字符串空
	
	/**
	 * 进入世界Boss主页
	 * @author hzw
	 * @date 2015-1-5下午4:05:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void clickWorldBossBtn(Map<String, Object> msgMap, String channelId) throws Exception {
		
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//判断当前时间是否在21-21:30之间
//		String currYmd = DateUtil.getYmdDate();
//		String bossStartTime = currYmd + " 21:00:00";
//		String bossEndTime = currYmd + " 21:30:00";
//		if (DateUtil.getCurrMill() < DateUtil.getMillSecond(bossStartTime) || DateUtil.getCurrMill() > DateUtil.getMillSecond(bossEndTime)) {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
//			return;
//		}
		
		MessageData syncMsgData = new MessageData();
		
		MessageData playersMsgData = new MessageData();
		MessageData playersMsgDataTemp = new MessageData();
		//组织伤害排行，世界Boss开启显示当前伤害排行，否则显示最终伤害排行
		if(ParamConfig.worldBossstate == 1){
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
			int index = 0;
			for(WorldBossPlayer obj : worldBossPlayerList){
				index ++;
				MessageData playerMsgData = new MessageData();
				playerMsgData.putIntItem("1", index);
				playerMsgData.putIntItem("2", obj.getPlayerId());
				playerMsgData.putStringItem("3", obj.getPlayerName());
				playerMsgData.putIntItem("4", obj.getLevel());
				playerMsgData.putIntItem("5", obj.getBossSumHurt());
				String unionNameString = "";
				if(ParamConfig.unionPlayer.get(obj.getPlayerId()) != null){
					unionNameString = ParamConfig.unionMap.get(ParamConfig.unionPlayer.get(obj.getPlayerId()));
				}
				playerMsgData.putStringItem("6", unionNameString);
				playersMsgDataTemp.putMessageItem(obj.getPlayerId() + "",playerMsgData.getMsgData());
				if(index >= 10){
					break;
				}
			}
		}else{
			List<InstWorldBossRank> instWorldBossRans = getInstWorldBossRankDAL().getList("", 0);
			for(InstWorldBossRank instWorldBossRank : instWorldBossRans){
				InstPlayer player = getInstPlayerDAL().getModel(instWorldBossRank.getInstPlayerId(), instWorldBossRank.getInstPlayerId());
				MessageData playerMsgData = new MessageData();
				playerMsgData.putIntItem("1", instWorldBossRank.getRank());
				playerMsgData.putIntItem("2", instWorldBossRank.getInstPlayerId());
				playerMsgData.putStringItem("3", player.getName());
				playerMsgData.putIntItem("4", player.getLevel());
				playerMsgData.putIntItem("5", instWorldBossRank.getHurt());
				String unionNameString = "";
				if(ParamConfig.unionPlayer.get(player.getId()) != null){
					unionNameString = ParamConfig.unionMap.get(ParamConfig.unionPlayer.get(player.getId()));
				}
				playerMsgData.putStringItem("6", unionNameString);
				playersMsgDataTemp.putMessageItem(instWorldBossRank.getRank() + "",playerMsgData.getMsgData());
			}
			
			//世界Boss状态（在21点到21点半之间结束）
			long nowTime = DateUtil.getMillSecond(DateUtil.getCurrTime());
			long worldBossStartTime = DateUtil.getMillSecond(DateUtil.getYmdDate() + " " + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossStartTime) + ":00:00");
			long worldBossEndTime = worldBossStartTime + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossLongTime) * 60 * 1000;
			if(nowTime >= worldBossStartTime && nowTime <= worldBossEndTime){
				playersMsgData.putIntItem("worldBossState", 0);
			}
		}
		playersMsgData.putMessageItem("rank", playersMsgDataTemp.getMsgData());

		//FIXED by cui
		//BOSS奖励列表改成服务器来发
		StringBuilder sb = new StringBuilder();
		List<DictWorldBossReward> dictWorldBossRewardList = DictList.dictWorldBossRewardList;
		for(DictWorldBossReward dictWorldBossReward : dictWorldBossRewardList){
			sb.append(dictWorldBossReward.getDownRank()).append(SEP_BAR);
			sb.append(dictWorldBossReward.getUpRank()).append(SEP_BAR);
			if(dictWorldBossReward.getDescription() == null){
				sb.append(NULL).append(SEP_SEM);
			}else{
				sb.append(dictWorldBossReward.getDescription()).append(SEP_SEM);
			}
		}
		if(sb.length() > 0){
			sb.deleteCharAt(sb.length() - 1);
		}
		playersMsgData.putStringItem("reward", sb.toString());
		
		//本次打的世界Boss ID
		int currBossId = getInstWorldBossDAL().getList("", 0).get(0).getCurrBossId();
		playersMsgData.putIntItem("bossId", currBossId);
		
		//屠魔积分
		int bossIntergral = 0;
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + checkInstPlayerId + " and properties = '"+StaticBigTable.bossIntegral+"'", 0);
		if (instPlayerBigTableList.size() > 0) {
			bossIntergral = ConvertUtil.toInt(instPlayerBigTableList.get(0).getValue1());
		}
		playersMsgData.putIntItem("bossIntergral", bossIntergral);
		
		//大宝箱状态 0-关闭 1-可领取 2-已领取
		int bigBoxState = 0;
		
		//活动结束,并且在21-24点以内
		if (ParamConfig.worldBossstate == 0) {
			if (DateUtil.getCurrMill() <= DateUtil.getMillSecond(DateUtil.getYmdDate() + " 23:59:59") && DateUtil.getCurrMill() >= DateUtil.getMillSecond(DateUtil.getYmdDate() + " 21:00:00")) {
				WorldBossPlayer worldBossPlayer = null;
				for (WorldBossPlayer obj : ParamConfig.worldBossRankList) {
					if (obj.getPlayerId() == checkInstPlayerId) {
						worldBossPlayer = obj;
						break;
					}
				}
				if (worldBossPlayer != null && worldBossPlayer.getOpenBigBoxState() == 0) {
					bigBoxState = 1;
				} else if (worldBossPlayer != null && worldBossPlayer.getOpenBigBoxState() == 1) {
					bigBoxState = 2;
				}
			}
		}
		playersMsgData.putIntItem("bigBoxState", bigBoxState);//大宝箱状态 0-关闭 1-可领取 2-已领取
		
		//击杀状态 0-未击杀 1-击杀
		int hitState = 0;
//		if (ParamConfig.worldBossstate == 0) {
//			if (ParamConfig.worldBossBlood <= 0) {
//				hitState = 1;
//			}
//		}
		List<InstWorldBossRank> instWorldBossRankList = getInstWorldBossRankDAL().getList("rank = 0", 0);
		if (instWorldBossRankList.size() > 0) {
			InstWorldBossRank instWorldBossRank = instWorldBossRankList.get(0);
			if (DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instWorldBossRank.getUpdateTime()))) {
				hitState = 1;
			}
		}
		playersMsgData.putIntItem("hitState", hitState);//击杀状态 0-未击杀 1-击杀
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, playersMsgData);
	}
	
	/**
	 * 参战世界Boss
	 * @author hzw
	 * @date 2015-1-5下午4:06:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void joinWorldBoss(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//判断当前时间是否在21-21:30之间
		String currYmd = DateUtil.getYmdDate();
		String bossStartTime = currYmd + " 21:00:00";
		String bossEndTime = currYmd + " 21:20:00";
		if (DateUtil.getCurrMill() < DateUtil.getMillSecond(bossStartTime) || DateUtil.getCurrMill() > DateUtil.getMillSecond(bossEndTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_activityOver);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		MessageData playerMsgData = new MessageData();
		if(ParamConfig.worldBossstate == 0){
			playerMsgData.putIntItem("worldBossState", 0);
		}else{
			WorldBossPlayer worldBossPlayer = WorldBossPlayerMapUtil.getWorldBossPlayerByChannelId(instPlayerId);
			if(worldBossPlayer == null){
				//向WorldBossPlayerMap中添加参战玩家
				worldBossPlayer = new WorldBossPlayer();
				worldBossPlayer.setPlayerId(instPlayerId);
				worldBossPlayer.setPlayerName(instPlayer.getName());
				worldBossPlayer.setLevel(instPlayer.getLevel());
				worldBossPlayer.setBossFightTimes(0);
				worldBossPlayer.setBossOnceHurt(0);
				worldBossPlayer.setBossSumHurt(0);
				worldBossPlayer.setCopperInspireNum(0);
				worldBossPlayer.setGoldInspireNum(0);
				worldBossPlayer.setGoldRebirthNum(0);
				worldBossPlayer.setOverTime("");
				worldBossPlayer.setExitState(1);
				worldBossPlayer.setOpenBigBoxState(0);
				WorldBossPlayerMapUtil.add(instPlayerId, worldBossPlayer);
				
				//更新玩家成就计数实例数据
				AchievementUtil.updateAchievementValue(instPlayerId, StaticAchievementType.worldBoss, 1, syncMsgData);
			}else{
				if(worldBossPlayer.getExitState() == 0){
					worldBossPlayer.setExitState(1);
					WorldBossPlayerMapUtil.add(instPlayerId, worldBossPlayer);
				}	
			}
			
			//给worldBossPlayerList排序
			Map<Integer, WorldBossPlayer> worldBossPlayerMap = WorldBossPlayerMapUtil.getMap();
			Iterator<WorldBossPlayer> iterator = worldBossPlayerMap.values().iterator();
			List<WorldBossPlayer> worldBossPlayerList = new ArrayList<WorldBossPlayer>();
			while (iterator.hasNext()) {
				WorldBossPlayer obj = iterator.next();
				worldBossPlayerList.add(obj);
			}
			Collections.sort(worldBossPlayerList, new Comparator<Object>() {
				public int compare(Object a, Object b) {
					long one = ((WorldBossPlayer)a).getBossSumHurt();
					long two = ((WorldBossPlayer)b).getBossSumHurt(); 
					return (int)(two - one); 
				}
			}); 
			
			MessageData playerMsg = new MessageData();
			playerMsg.putIntItem("1", worldBossPlayer.getBossFightTimes());
			playerMsg.putIntItem("2", worldBossPlayer.getBossSumHurt());
			playerMsg.putIntItem("3", worldBossPlayer.getCopperInspireNum());
			playerMsg.putIntItem("4", worldBossPlayer.getGoldInspireNum());
			playerMsg.putIntItem("5", worldBossPlayer.getGoldRebirthNum());
			long nowTime = DateUtil.getMillSecond(DateUtil.getCurrTime());
			long overTimeTemp = 0;
			String overTime = worldBossPlayer.getOverTime();
			if(overTime != null && !overTime.equals("") && DateUtil.getMillSecond(overTime) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossWaitTime) * 60000 > nowTime){
				overTimeTemp = DateUtil.getMillSecond(overTime) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossWaitTime) * 60000 - nowTime;
			}
			playerMsg.putLongItem("6", overTimeTemp);
			playerMsg.putIntItem("7", worldBossPlayerList.indexOf(worldBossPlayer) + 1);
			playerMsg.putIntItem("8", ParamConfig.worldBossBlood);
			
			/*int bossBlood = DictMapUtil.getSysConfigIntValue(StaticSysConfig.minBossBlood);
			List<InstWorldBoss> instWorldBosseList = getInstWorldBossDAL().getList("", 0);
			if (instWorldBosseList.size() > 0) {
				bossBlood = instWorldBosseList.get(0).getCurrBossBlood();
			}
			playerMsg.putIntItem("8", bossBlood);*/
			playerMsgData.putMessageItem("playerMsg", playerMsg.getMsgData());
			
		}
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.worldBoss, 1, syncMsgData);
		
//		//发送世界boss血量
//		int bossBlood = getInstWorldBossDAL().getList("", 0).get(0).getCurrBossBlood();
//		playerMsgData.putIntItem("bossBlood", bossBlood);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
	}
	
	/**
	 * 退出世界Boss
	 * @author hzw
	 * @date 2015-1-5下午5:29:17
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void exitWorldBoss(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//判断当前时间是否在21-21:30之间
		String currYmd = DateUtil.getYmdDate();
		String bossStartTime = currYmd + " 21:00:00";
		String bossEndTime = currYmd + " 21:30:00";
		if (DateUtil.getCurrMill() < DateUtil.getMillSecond(bossStartTime) || DateUtil.getCurrMill() > DateUtil.getMillSecond(bossEndTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_activityOver);
			return;
		}
		
		MessageData playerMsgData = new MessageData();
		if(ParamConfig.worldBossstate == 0){
			playerMsgData.putIntItem("worldBossState", 0);
		}else{
			WorldBossPlayer worldBossPlayer = WorldBossPlayerMapUtil.getWorldBossPlayerByChannelId(instPlayerId);
			if(worldBossPlayer != null){
				//修改参战玩家世界Boss状态
				if(worldBossPlayer.getExitState() == 1){
					worldBossPlayer.setExitState(0);
					WorldBossPlayerMapUtil.add(instPlayerId, worldBossPlayer);
				}
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
	}
	
	/**
	 * 挑战世界Boss
	 * @author hzw
	 * @date 2015-1-6上午10:56:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fightWorldBoss(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//判断当前时间是否在21-21:30之间
		String currYmd = DateUtil.getYmdDate();
		String bossStartTime = currYmd + " 21:00:00";
		String bossEndTime = currYmd + " 21:30:00";
		if (DateUtil.getCurrMill() < DateUtil.getMillSecond(bossStartTime) || DateUtil.getCurrMill() > DateUtil.getMillSecond(bossEndTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_activityOver);
			return;
		}
		
		int bossOnceHurt = (Integer)msgMap.get("bossOnceHurt"); //单次伤害
		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//验证单次最大伤害
		if (bossOnceHurt > DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossMaxHurt)) {
			LogUtil.info("WorldBossUpMaxHurt:instPlayerId=" + instPlayerId + ";OnceHurt=" + bossOnceHurt);
			MessageData retBossMsgData = new MessageData();
			retBossMsgData.putStringItem("1", StaticCnServer.fail_worldBossDataVerfy);
			MessageUtil.pushMsg(channelId, StaticMsgRule.pushCloseServerData, retBossMsgData);
			return;
		}
		
		//用于验证玩家是否利用烧饼等修改器
		if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
			return;
		}
		MessageData playerMsgData = new MessageData();
		long nowTime = DateUtil.getMillSecond(DateUtil.getCurrTime());
		if(ParamConfig.worldBossstate == 0){
			playerMsgData.putIntItem("worldBossState", 0);
		}else{
			WorldBossPlayer worldBossPlayer = WorldBossPlayerMapUtil.getWorldBossPlayerByChannelId(instPlayerId);
			if(worldBossPlayer == null){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noOperationWorldBoss);
				return;
			}else{
				String overTime = worldBossPlayer.getOverTime();
				if(overTime != null && !overTime.equals("") && DateUtil.getMillSecond(overTime) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossWaitTime) * 60000 > nowTime){
					playerMsgData.putLongItem("overWaitTime",DateUtil.getMillSecond(overTime) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossWaitTime) * 60000 - nowTime);
				}else{
					//更新世界Boss血量
					ParamConfig.worldBossBlood = (ParamConfig.worldBossBlood - bossOnceHurt) <= 0 ? 0 : (ParamConfig.worldBossBlood - bossOnceHurt);
					if(ParamConfig.worldBossBlood <= 0){
						ParamConfig.worldBossstate = 0;
						
						//统计本次世界Boss信息
						InstWorldBoss instWorldBoss = getInstWorldBossDAL().getList("", 0).get(0);
						if (ParamConfig.worldBossBlood <= 0) {//世界Boss已死
							long currTime = DateUtil.getCurrMill();
							long startTime = DateUtil.getMillSecond(DateUtil.getYmdDate() + " 21:00:00");
							instWorldBoss.setCurrHitBossSecs((int)(currTime - startTime)/1000);
							getInstWorldBossDAL().update(instWorldBoss, 0);
						} else {//活动结束,但是世界Boss未被打死
							instWorldBoss.setCurrHitBossSecs(0);
							getInstWorldBossDAL().update(instWorldBoss, 0);
						}
						WorldBossPlayerMapUtil.lastFightInstPlayerId = instPlayerId;
						new WorldBossStartHandler().new worldBossRankReward().start();
						playerMsgData.putIntItem("worldBossState", 0);
					}
					
					worldBossPlayer.setBossFightTimes(worldBossPlayer.getBossFightTimes() + 1);
					worldBossPlayer.setBossOnceHurt(bossOnceHurt);
					worldBossPlayer.setBossSumHurt(worldBossPlayer.getBossSumHurt() + bossOnceHurt);
					worldBossPlayer.setOverTime(DateUtil.getCurrTime());
					WorldBossPlayerMapUtil.add(instPlayerId, worldBossPlayer);
					
					//给worldBossPlayerList排序
					Map<Integer, WorldBossPlayer> worldBossPlayerMap = WorldBossPlayerMapUtil.getMap();
					Iterator<WorldBossPlayer> iterator = worldBossPlayerMap.values().iterator();
					List<WorldBossPlayer> worldBossPlayerList = new ArrayList<WorldBossPlayer>();
					while (iterator.hasNext()) {
						WorldBossPlayer obj = iterator.next();
						worldBossPlayerList.add(obj);
					}
					Collections.sort(worldBossPlayerList, new Comparator<Object>() {
						public int compare(Object a, Object b) {
							long one = ((WorldBossPlayer)a).getBossSumHurt();
							long two = ((WorldBossPlayer)b).getBossSumHurt(); 
							return (int)(two - one); 
						}
					}); 
					
					MessageData playerMsg = new MessageData();
					playerMsg.putIntItem("1", worldBossPlayer.getBossFightTimes());
					playerMsg.putIntItem("2", worldBossPlayer.getBossSumHurt());
					long overTimeTemp = DateUtil.getMillSecond(worldBossPlayer.getOverTime()) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossWaitTime) * 60000 - nowTime;
					playerMsg.putLongItem("6", overTimeTemp);
					playerMsg.putIntItem("7", worldBossPlayerList.indexOf(worldBossPlayer) + 1);
					playerMsgData.putMessageItem("playerMsg", playerMsg.getMsgData());
					
					//根据战斗次数,取出发奖Id
					int worldBossTimesRewardId = 0;
					int endId = 0;
					List<DictWorldBossTimesReward> worldBossTimesRewardList = DictList.dictWorldBossTimesRewardList;
					for (DictWorldBossTimesReward obj : worldBossTimesRewardList) {
						if (obj.getEndRank() == -1) {
							endId = obj.getId();
						} else {
							if (worldBossPlayer.getBossFightTimes() <= obj.getEndRank() && worldBossPlayer.getBossFightTimes() >= obj.getStartRank()) {
								worldBossTimesRewardId = obj.getId();
							}
						}
					}
					if (worldBossTimesRewardId == 0) {
						worldBossTimesRewardId = endId;
					}
					//发放次数奖励
					DictWorldBossTimesReward dictWorldBossTimesReward = DictMap.dictWorldBossTimesRewardMap.get(worldBossTimesRewardId + "");
					for (String things : dictWorldBossTimesReward.getRewardThings().split(";")) {
						ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(things.split("_")[0]), ConvertUtil.toInt(things.split("_")[1]), ConvertUtil.toInt(things.split("_")[2]), syncMsgData, msgMap);
					}
				}
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
	}
	
	/**
	 * 重生
	 * @author hzw
	 * @date 2015-1-6下午4:13:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void rebirthWorldBoss(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//判断当前时间是否在21-21:30之间
		String currYmd = DateUtil.getYmdDate();
		String bossStartTime = currYmd + " 21:00:00";
		String bossEndTime = currYmd + " 21:20:00";
		if (DateUtil.getCurrMill() < DateUtil.getMillSecond(bossStartTime) || DateUtil.getCurrMill() > DateUtil.getMillSecond(bossEndTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_activityOver);
			return;
		}
		
		MessageData playerMsgData = new MessageData();
		if(ParamConfig.worldBossstate == 0){
			playerMsgData.putIntItem("worldBossState", 0);
		}else{
			
			WorldBossPlayer worldBossPlayer = WorldBossPlayerMapUtil.getWorldBossPlayerByChannelId(instPlayerId);
			if(worldBossPlayer == null){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noOperationWorldBoss);
				return;
			}
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			
			//重生最大值允许3次
			if (worldBossPlayer.getGoldRebirthNum() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxLiveNum)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyUpLimit);
				return;
			}
			int gold = 0;
			if (worldBossPlayer.getGoldRebirthNum() == 0) {
				gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.oneLiveGoldNum);
			} else if (worldBossPlayer.getGoldRebirthNum() == 1) { 
				gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.twoLiveGoldNum);
			} else if (worldBossPlayer.getGoldRebirthNum() == 2) { 
				gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.threeLiveGoldNum);
			}
//			int gold = worldBossPlayer.getGoldRebirthNum() * DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossRebirthGoldAdd) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossRebirthGold);
			
			if(instPlayer.getGold() < gold){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}
			//扣除元宝
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			
			//记录重生次数，清除死亡时间
			worldBossPlayer.setGoldRebirthNum(worldBossPlayer.getGoldRebirthNum() + 1);
			worldBossPlayer.setOverTime("");
			WorldBossPlayerMapUtil.add(instPlayerId, worldBossPlayer);
			
			MessageData playerMsg = new MessageData();
			playerMsg.putIntItem("5", worldBossPlayer.getGoldRebirthNum());
			playerMsg.putIntItem("6", 0);
			playerMsgData.putMessageItem("playerMsg", playerMsg.getMsgData());
			
		}
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
	}
	
	/**
	 * 鼓舞
	 * @author hzw
	 * @date 2015-1-7下午9:56:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void inspireWorldBoss(Map<String, Object> msgMap, String channelId) throws Exception {/*
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//判断当前时间是否在21-21:30之间
		String currYmd = DateUtil.getYmdDate();
		String bossStartTime = currYmd + " 21:00:00";
		String bossEndTime = currYmd + " 21:30:00";
		if (DateUtil.getCurrMill() < DateUtil.getMillSecond(bossStartTime) || DateUtil.getCurrMill() > DateUtil.getMillSecond(bossEndTime)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int type = (Integer)msgMap.get("type"); //1-银币 2-金币
		MessageData playerMsgData = new MessageData();
		if(ParamConfig.worldBossstate == 0){
			playerMsgData.putIntItem("worldBossState", 0);
		}else{
			WorldBossPlayer worldBossPlayer = WorldBossPlayerMapUtil.getWorldBossPlayerByChannelId(instPlayerId);
			if(worldBossPlayer == null){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noOperationWorldBoss);
				return;
			}
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			boolean tag = false;
			if(type == 1){
				if(worldBossPlayer.getCopperInspireNum() == 1){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperInspire);
					return;
				}else if(ConvertUtil.toInt(instPlayer.getCopper()) < DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossCopperInspire)){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
					return;
				}else{
					tag = true;
					instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossCopperInspire)) + "");
					worldBossPlayer.setCopperInspireNum(1);
				}
			}
			if(type == 2){
				int gold = worldBossPlayer.getGoldInspireNum() * DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossGoldInspire) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.worldBossRebirthGold);
				if(worldBossPlayer.getGoldInspireNum() + worldBossPlayer.getCopperInspireNum() == 20){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_inspire);
					return;
				}else if(instPlayer.getGold() < gold){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
					return;
				}else{
					tag = true;
					//扣除元宝
					PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
					worldBossPlayer.setGoldInspireNum(worldBossPlayer.getGoldInspireNum() + 1);
				}
			}
			
			if(tag){
				getInstPlayerDAL().update(instPlayer, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				
				//记录重生次数，清除死亡时间
				WorldBossPlayerMapUtil.add(instPlayerId, worldBossPlayer);
				
				MessageData playerMsg = new MessageData();
				playerMsg.putIntItem("3", worldBossPlayer.getCopperInspireNum());
				playerMsg.putIntItem("4", worldBossPlayer.getGoldInspireNum());
				playerMsgData.putMessageItem("playerMsg", playerMsg.getMsgData());
			}
			
		}
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
	*/}

	/**
	 * 下发世界Boss屠魔商店信息
	 * @author mp
	 * @date 2015-11-26 下午4:55:23
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sendBossShop (Map<String, Object> msgMap, String channelId) throws Exception {
		
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		StringBuilder bossStoreSb = new StringBuilder();
		List<DictWorldBossStore> worldBossStoreList = DictList.dictWorldBossStoreList;
		for (DictWorldBossStore obj : worldBossStoreList) {
			bossStoreSb.append(obj.getId()).append("/");
			bossStoreSb.append(obj.getName()).append("/");
			bossStoreSb.append(obj.getThings()).append("/");
			bossStoreSb.append(obj.getNeedbossIntegral()).append("/");
			bossStoreSb.append(obj.getRank()).append("/");
			bossStoreSb.append(obj.getType()).append("/");
			bossStoreSb.append(obj.getDescription()).append(";");
		}
		
//		List<InstPlayerBigTable> instPlayerBigTableList 
		
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", StringUtil.noContainLastString(bossStoreSb.toString()));
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 屠魔商店兑换
	 * @author mp
	 * @date 2015-11-26 下午5:21:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void exchangeBossShop (Map<String, Object> msgMap, String channelId) throws Exception {
		
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int bossShopId = (Integer)msgMap.get("bossShopId"); //屠魔商店字典表Id
		int num = (Integer)msgMap.get("num"); //兑换数量
		DictWorldBossStore worldBossStore = DictMap.dictWorldBossStoreMap.get(bossShopId + "");
		
		//验证屠魔积分是否足够
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.bossIntegral+"'", 0);
		if (instPlayerBigTableList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_boss_intergralNotEnough);
			return;
		} else {
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			int ownBossIntergral = ConvertUtil.toInt(instPlayerBigTable.getValue1());
			if (ownBossIntergral < worldBossStore.getNeedbossIntegral() * num) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_boss_intergralNotEnough);
				return;
			} else {
				//减去相应积分
				instPlayerBigTable.setValue1((ownBossIntergral - worldBossStore.getNeedbossIntegral() * num) + "");
				getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
			}
		}
		
		//获得物品
		MessageData syncMsgData = new MessageData();
		for (String thingId : worldBossStore.getThings().split(";")) {
			int tableTypeId = ConvertUtil.toInt(thingId.split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(thingId.split("_")[1]);
			int value = ConvertUtil.toInt(thingId.split("_")[2]);
			ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value * num, syncMsgData, msgMap);
		}
		
		String info = "屠魔商店物品兑换 instPlayerId=" + instPlayerId + " 消耗积分=" + worldBossStore.getNeedbossIntegral() + " 购买物品=" + worldBossStore.getThings() + " 购买个数=" + num;
		LogUtil.info(info);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 打开世界Boss大宝箱
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void openBossBigBox (Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//验证世界Boss活动是否在进行中(世界Boss被打死或者过了活动期都会置为0)
		if (ParamConfig.worldBossstate == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_boss_ing);
			return;
		}
		
		//验证是否在规定时间内(21-24)
		long currTime = DateUtil.getCurrMill();
		String currYmd = DateUtil.getYmdDate();
		long startTime = DateUtil.getMillSecond(currYmd + " 21:00:00");
		long endTime = DateUtil.getMillSecond(currYmd + " 23:59:59");
		if (currTime < startTime || currTime > endTime) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_boss_noInTime);
			return;
		}
		
		//验证是否已领
		int rank = 0;
		int index = 0;
		WorldBossPlayer worldBossPlayer = null;
		for (WorldBossPlayer obj : ParamConfig.worldBossRankList) {
			index ++ ;
			if (obj.getPlayerId() == instPlayerId) {
				worldBossPlayer = obj;
				rank = index;
				break;
			}
		}
		if (worldBossPlayer == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_boss_noAdd);
			return;
		}
		if (worldBossPlayer.getOpenBigBoxState() == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_boss_opened);
			return;
		}
		
		//给东西（判断是不是最后一击,如果是最后一击给最后一击的奖励)
		DictWorldBossReward dictWorldBossReward = null;
		DictWorldBossReward lastHitWorldBossReward = null;
		for (DictWorldBossReward worldBossReward : DictList.dictWorldBossRewardList) {
			if (rank >= worldBossReward.getUpRank() && rank <= worldBossReward.getDownRank()) {
				dictWorldBossReward = worldBossReward;
			}
			if (worldBossReward.getUpRank() == 0 && worldBossReward.getDownRank() == 0) {
				lastHitWorldBossReward = worldBossReward;
			}
		}
		
		//判断自己是否为最后一击,如果是最后一击,给最后一击的奖励
		int lastHitPlayerId = 0;
		List<InstWorldBossRank> instWorldBossRankList = getInstWorldBossRankDAL().getList("rank = 0", 0);
		if (instWorldBossRankList.size() > 0) {
			lastHitPlayerId = instWorldBossRankList.get(0).getInstPlayerId();
		}
		
		//世界boss没被打死,排名奖励减半
		float per = 1;
		if (lastHitPlayerId == 0) {
			per = 0.5f;
		}
		
		int isLastHit = 0;
		String awardThings = "";
		MessageData syncMsgData = new MessageData();
		if (lastHitPlayerId != instPlayerId) {
			awardThings = dictWorldBossReward.getDescription();
			for (String things : dictWorldBossReward.getDescription().split(";")) {
				int tableTypeId = ConvertUtil.toInt(things.split("_")[0]);
				int tableFieldId = ConvertUtil.toInt(things.split("_")[1]);
				int value = ConvertUtil.toInt(things.split("_")[2]);
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, (int)(value * per), syncMsgData, msgMap);
			}
		} else {
			isLastHit = 1;
			awardThings = lastHitWorldBossReward.getDescription();
			for (String things : lastHitWorldBossReward.getDescription().split(";")) {
				int tableTypeId = ConvertUtil.toInt(things.split("_")[0]);
				int tableFieldId = ConvertUtil.toInt(things.split("_")[1]);
				int value = ConvertUtil.toInt(things.split("_")[2]);
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, (int)(value * per), syncMsgData, msgMap);
			}
		}
		
		//设置为已打开状态
		worldBossPlayer.setOpenBigBoxState(1);
		
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("things", awardThings);//打开的物品
		
		//世界boss是否被击杀
		retMsgData.putIntItem("bossIsDead", lastHitPlayerId);//0-未被击杀  非0-被击杀
		
		//是否为最后一击
		retMsgData.putIntItem("isLastHit", isLastHit);//0-不是  1-是
		
		//当前为多少名
		int bossRank = 0;
		for(WorldBossPlayer obj : ParamConfig.worldBossRankList) {
			bossRank ++;
			if (obj.getPlayerId() == instPlayerId) {
				break;
			}
		}
		retMsgData.putIntItem("bossRank", bossRank);//伤害排行
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
}
