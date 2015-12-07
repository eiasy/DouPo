package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictArenaConvert;
import com.huayi.doupo.base.model.DictArenaDrop;
import com.huayi.doupo.base.model.DictArenaInterval;
import com.huayi.doupo.base.model.DictArenaReward;
import com.huayi.doupo.base.model.DictLevelProp;
import com.huayi.doupo.base.model.DictVIP;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerArena;
import com.huayi.doupo.base.model.InstPlayerArenaConvert;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticDailyTask;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.CustomMarqueeType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.ArenaUtil;
import com.huayi.doupo.logic.handler.util.CardUtil;
import com.huayi.doupo.logic.handler.util.EnemyPlayerUtil;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.VerifyUtil;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class ArenaHandler extends BaseHandler{
	
	/**
	 * 初始竞技场
	 * @author hzw
	 * @date 2014-10-25下午3:24:18
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void arena(Map<String, Object> msgMap, String channelId) throws Exception {
		synchronized(ParamConfig.arenaRankLock){
			MessageData syncMsgData = new MessageData();
			int instPlayerId = getInstPlayerId(channelId);
		
			if (instPlayerId == 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
				return;
			}
			
			List<InstPlayerArena> instPlayerArenaList = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, 0);
			if(instPlayerArenaList.size() <= 0){
				InstPlayerArena instPlayerArena = new InstPlayerArena();
				int cnt  = getInstPlayerArenaDAL().getCount("");
				
				instPlayerArena.setInstPlayerId(instPlayerId);
				instPlayerArena.setRank(cnt + 1);
				instPlayerArena.setUpRank(cnt + 1);
				instPlayerArena.setArenaNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNum));
				instPlayerArena.setArenaTime("");
				instPlayerArena.setPrestige(0);
				instPlayerArena.setBuyArenaNum(0);
				instPlayerArena.setAwardRank(0);
				instPlayerArena.setOperTime(DateUtil.getCurrTime());
				getInstPlayerArenaDAL().add(instPlayerArena, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
			}
			InstPlayerArena instPlayerArena = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, 0).get(0);
			int intervalNum = 0;
			List<DictArenaInterval> dictArenaIntervalList = DictList.dictArenaIntervalList;
			for(DictArenaInterval obj : dictArenaIntervalList){
				if(obj.getDownRank() <= instPlayerArena.getRank() && instPlayerArena.getRank() <= obj.getUpRank()){
					intervalNum = obj.getIntervalNum();
				}
			}
			List<DictArenaReward> dictArenaRewardList = DictList.dictArenaRewardList;
			MessageData playersMsgData = new MessageData();
			//组织前边5个排名的玩家包括自己共六个
			int i = 0; 
			int rank = instPlayerArena.getRank();
			for(; i <= 5; i++){
				if(rank <= 0){
					break;
				}
//				InstPlayerArena objArena = getInstPlayerArenaDAL().getList(" rank = " + rank, 0).get(0);
				
				List<InstPlayerArena> objArenaList = getInstPlayerArenaDAL().getList(" rank = " + rank, 0);
				if (objArenaList.size() <= 0) {
					rank = rank - 1;
					continue;
				}
				
				InstPlayerArena objArena = objArenaList.get(0);
				
				
				ArenaUtil.orgPlayer(objArena.getInstPlayerId(), rank, objArena.getUpRank(), dictArenaRewardList, playersMsgData);
				for(DictArenaInterval obj : dictArenaIntervalList){
					if(obj.getDownRank() <= objArena.getRank() && objArena.getRank() <= obj.getUpRank()){
						intervalNum = obj.getIntervalNum();
					}
				}
				rank = rank - intervalNum;
			}
			
			//组织后边的玩家（不足8个补足）
			rank = instPlayerArena.getRank();
			for(int j = 1; j <= 2 + (5 - (i - 1)); j++){
				if(rank <= 0){
					break;
				}
				List<InstPlayerArena> objList = getInstPlayerArenaDAL().getList(" rank = " + rank, 0);
				if(objList.size() > 0){
					InstPlayerArena objArena = objList.get(0);
					ArenaUtil.orgPlayer(objArena.getInstPlayerId(), rank, objArena.getUpRank(), dictArenaRewardList, playersMsgData);
					for(DictArenaInterval obj : dictArenaIntervalList){
						if(obj.getDownRank() <= objArena.getRank() && objArena.getRank() <= obj.getUpRank()){
							intervalNum = obj.getIntervalNum();
						}
					}
				}
				rank = rank + intervalNum;
			}
			
			
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
			MessageUtil.sendSuccMsg(channelId, msgMap, playersMsgData);
		}
	}
	
	/**
	 * 竞技场排行（前十名）
	 * @author hzw
	 * @date 2014-10-29下午4:28:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void arenaList(Map<String, Object> msgMap, String channelId) throws Exception {
		
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		MessageData syncMsgData = new MessageData();
		
		List<DictArenaReward> dictArenaRewardList = DictList.dictArenaRewardList;
		MessageData playersMsgData = new MessageData();
		//组织前10名的玩家
		List<InstPlayerArena> arenaList = getInstPlayerArenaDAL().getList(" rank <= " + 10, 0);
		for(InstPlayerArena arena : arenaList){
			ArenaUtil.orgPlayer(arena.getInstPlayerId(), arena.getRank(), arena.getUpRank(), dictArenaRewardList, playersMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, playersMsgData);
	}
	
	/**
	 * 挑战
	 * @author hzw
	 * @date 2014-10-29下午5:54:03
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void arenaWar(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int playerId = (Integer)msgMap.get("playerId"); //挑战的玩家Id
		int rank = (Integer)msgMap.get("rank"); //挑战的竞技排名
//		int ownRank = (Integer)msgMap.get("ownRank"); //自己的排名
		
		MessageData retMsgData = new MessageData();
//		String currTime = DateUtil.getCurrTime();
		InstPlayerArena playerArena = getInstPlayerArenaDAL().getList("rank = " + rank, 0).get(0);
		int change = 0; //用于提示排名是否发生变化  1-变化
		if(playerArena == null || playerArena.getInstPlayerId() != playerId){
			change = 1;
			MessageUtil.sendFailMsg(channelId, msgMap, "排名已变,请刷新重试!");
			return;
		}else{
//			InstPlayerArena instPlayerArena = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId).get(0);
			/*long arenaTime = 0;
			if(instPlayerArena.getArenaTime() != null && !instPlayerArena.getArenaTime().equals("")){
				arenaTime = DateUtil.getMillSecond(instPlayerArena.getArenaTime());
			}
			if(DateUtil.getMillSecond(currTime) < (arenaTime + DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaTime) * 60 *1000)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotTime);
				return;
			}
			if(instPlayerArena.getArenaNum() < 1 && DateUtil.isSameDay(instPlayerArena.getOperTime(), currTime, DateFormat.YMDHMS)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notArenaNum);
				return;
			}*/
			
			//由之前的每天的次数，改为通过消耗耐力
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			if(instPlayer.getVigor() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notVigor);
				return;
			}
		}
		retMsgData.putIntItem("1", change);
		if(playerArena.getUpRank() == -1){
			//组织NPC敌方玩家战斗相关数据
			retMsgData = EnemyPlayerUtil.retEnemyNPCInfoMsgData(retMsgData, playerId);
		}else{
			//组织PC敌方玩家战斗相关数据
			retMsgData = EnemyPlayerUtil.retEnemyPlayerInfoMsgData(retMsgData, playerId);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 挑战胜利/失败
	 * @author hzw
	 * @date 2014-10-28上午11:33:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void arenaWarWin(Map<String, Object> msgMap, String channelId) throws Exception {
		synchronized(ParamConfig.arenaLock){
			MessageData syncMsgData = new MessageData();
			
			String dictId = "";//封装跑马灯参数
			int instPlayerId = getInstPlayerId(channelId);
			
			if (instPlayerId == 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
				return;
			}
			
			int playerId = (Integer)msgMap.get("playerId"); //挑战的玩家Id
			int rank = (Integer)msgMap.get("rank"); //挑战的竞技排名Id
			int warWin = (Integer)msgMap.get("warWin"); //0-失败 1-胜利
			String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
//			int ownRank = (Integer)msgMap.get("ownRank"); //自己的排名
			
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			MessageData retMsgData = new MessageData();
			String currTime = DateUtil.getCurrTime();
			InstPlayerArena instPlayerArena = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, 0).get(0);  //玩家自己排名数据
			/*long arenaTime = 0;
			if(instPlayerArena.getArenaTime() != null && !instPlayerArena.getArenaTime().equals("")){
				arenaTime = DateUtil.getMillSecond(instPlayerArena.getArenaTime());
			}
			if(DateUtil.getMillSecond(currTime) < (arenaTime + DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaTime) * 60 *1000)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotTime);
				return;
			}
			if(instPlayerArena.getArenaNum() < 1 && DateUtil.isSameDay(instPlayerArena.getOperTime(), currTime, DateFormat.YMDHMS)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notArenaNum);
				return;
			}*/
			
			//由之前的每天的次数，改为通过消耗耐力
			if(instPlayer.getVigor() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notVigor);
				return;
			}
			//用于验证玩家是否利用烧饼等修改器
			if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
				return;
			}
		
			
			
			InstPlayerArena playerArena = getInstPlayerArenaDAL().getList(" rank = " + rank, 0).get(0);							//对方玩家排名数据
			
			dictId = instPlayerArena.getRank() + ";" + playerArena.getRank();
			
			//如果对方玩家排名小于了自己排名就提示更新否则互换位置
			
			if(playerArena.getInstPlayerId() != playerId){
				playerArena = getInstPlayerArenaDAL().getList(" instPlayerId = " + playerId, 0).get(0);
				rank = playerArena.getRank();
			}
			
			if(warWin == 1){
				if(playerArena.getRank() < instPlayerArena.getRank()){
					//更新对方玩家排名
					playerArena.setRank(instPlayerArena.getRank());
					getInstPlayerArenaDAL().update(playerArena, playerId);
					
					//更新自己排名，历史最高排名，挑战得到的奖励
					int awardRank = instPlayerArena.getAwardRank();
					instPlayerArena.setRank(rank);
					
					if(rank < instPlayerArena.getUpRank()){
						instPlayerArena.setUpRank(rank);
					}
					if(rank < awardRank || awardRank == 0){
						instPlayerArena.setAwardRank(ArenaUtil.arenaReward(instPlayerId, instPlayerArena.getUpRank(), awardRank, syncMsgData));
					}
				}
				
				//更新自己挑战次数，挑战时间
				if(!DateUtil.isSameDay(instPlayerArena.getOperTime(), currTime, DateFormat.YMDHMS)){
					instPlayerArena.setArenaNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNum) - 1);
					instPlayerArena.setBuyArenaNum(0);
				}else{
					instPlayerArena.setArenaNum(instPlayerArena.getArenaNum() - 1);
				}
				instPlayerArena.setArenaTime(currTime);
				
				instPlayerArena.setOperTime(currTime);
				instPlayerArena.setPrestige(instPlayerArena.getPrestige() + DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaWarWinPrestige));
				getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
				
				//处理物品奖励
				List<DictArenaDrop> dictArenaDropList = (List<DictArenaDrop>)DictList.dictArenaDropList;
				List<DictArenaDrop> dictArenaDrops = new ArrayList<>();
				for(DictArenaDrop obj : dictArenaDropList){
					dictArenaDrops.add(obj);
				}
				String things = "";
				DictArenaDrop dictArenaDrop = null;
				Float num = 0.0f;
				for(DictArenaDrop obj : dictArenaDrops){
					num += obj.getChance();
				}
				for(int i = 0; i < 3; i++){
					float random = RandomUtil.getRandomFloat();
					Float sum = 0.00f;
					for(DictArenaDrop obj : dictArenaDrops){
						sum += (float)obj.getChance() / (float)num;
						if(random <= sum){
							if(i == 0){
								dictArenaDrop = obj;
							}
							things += obj.getId() + ";";
							dictArenaDrops.remove(obj);
							num = num - obj.getChance();
							break;
						}
					}
				}
				
				//或得的物品
				ThingUtil.groupThing(instPlayer, dictArenaDrop.getTableTypeId(), dictArenaDrop.getTableFieldId(), dictArenaDrop.getValue(), syncMsgData, msgMap);
				
				retMsgData.putStringItem("2", things.substring(0, things.length() - 1));
				
				//发邮件 -老版本
//				ActivityUtil.addInstPlayerMail(playerId, 2, instPlayer.getName(), playerArena.getRank());
				
/*
 * 正式需求
 */
  				Player player = PlayerMapUtil.getPlayerByPlayerId(playerId);
				PlayerUtil.addPlayerMail(player, playerId, "", playerArena.getRank(), 0, instPlayer.getName(), 2);
				
				//跑马灯相关
				MarqueeUtil.putMsgToMarquee(channelId, dictId, CustomMarqueeType.MARQUEE_TYPE_OTHER, CustomMarqueeType.MARQUEE_SOURCE_ARENA);
				
			}else{ 
/*
 * 				正式需求
 */
  				Player player = PlayerMapUtil.getPlayerByPlayerId(playerId);
				PlayerUtil.addPlayerMail(player, playerId, "", 0, 1, instPlayer.getName(), 2);
				
				if(!DateUtil.isSameDay(instPlayerArena.getOperTime(), currTime, DateFormat.YMDHMS)){
					instPlayerArena.setArenaNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNum) - 1);
					instPlayerArena.setBuyArenaNum(0);
				}else{
					instPlayerArena.setArenaNum(instPlayerArena.getArenaNum() - 1);
				}
				
				instPlayerArena.setArenaTime(currTime);
				instPlayerArena.setOperTime(currTime);
				instPlayerArena.setPrestige(instPlayerArena.getPrestige() + DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaWarLostPrestige));
				getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
			}
		
			
			//处理决斗得到的银币，经验，扣除的耐力
			DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictLevelProp.getDuelFleetCopper()) + "");
			
			//处理消耗的耐力
			if(instPlayer.getVigor() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor) &&  (instPlayer.getVigor() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor)) < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor)){
				instPlayer.setLastVigorRecoverTime(DateUtil.getCurrTime());
			}
			instPlayer.setVigor(instPlayer.getVigor() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor));
			
			//处理战队能达到多少级和剩余经验
			FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + dictLevelProp.getDuelFleetExp(), syncMsgData, msgMap);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			
			//处理每日任务
			PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.area, 1, syncMsgData);
			
			//更新玩家成就计数实例数据
			AchievementUtil.updateAchievementValue(instPlayerId, StaticAchievementType.arena, 1, syncMsgData);
			
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
			MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		}
	
	}
	
	/**
	 * 清除冷却时间(不用)
	 * @author hzw
	 * @date 2014-10-29下午2:32:34
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buyArenaTime(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerArenaId = (Integer)msgMap.get("instPlayerArenaId"); //玩家竞技场实例Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerArena instPlayerArena = getInstPlayerArenaDAL().getModel(instPlayerArenaId, 0);  //玩家自己排名数据
		if (instPlayerArena.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		if(instPlayer.getGold() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaTimeGold)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		
		//消耗元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, (int)DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaTimeGold), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		instPlayerArena.setArenaTime("");
		getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 购买挑战次数(不用)
	 * @author hzw
	 * @date 2014-10-29下午2:49:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buyArenaNum(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerArenaId = (Integer)msgMap.get("instPlayerArenaId"); //玩家竞技场实例Id
		String currTime = DateUtil.getCurrTime();
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerArena instPlayerArena = getInstPlayerArenaDAL().getModel(instPlayerArenaId, 0);  //玩家自己排名数据
		if (instPlayerArena.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (instPlayerArena.getArenaNum() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_arenaNum);
			return;
		}
		DictVIP dictVIP = CardUtil.getVipByLevel(instPlayer.getVipLevel());
		int buyArenaNum = instPlayerArena.getBuyArenaNum() + 1;
		if(!DateUtil.isSameDay(instPlayerArena.getOperTime(), currTime, DateFormat.YMDHMS)){
			buyArenaNum = 1;
		}
		if (buyArenaNum > dictVIP.getBuyArenaNum()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notBuyEliteFightNum);
			return;
		}
		int gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNumGold) + (buyArenaNum - 1) * DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNumGoldAdd);
		if(instPlayer.getGold() < gold){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		
		//消耗元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		instPlayerArena.setArenaNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNum));
		instPlayerArena.setBuyArenaNum(buyArenaNum);
		instPlayerArena.setOperTime(currTime);
		getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 竞技场兑换
	 * @author hzw
	 * @date 2014-10-30上午10:56:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void arenaConvert(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerArenaId = (Integer)msgMap.get("instPlayerArenaId"); //玩家竞技场实例Id
		int arenaConvertId = (Integer)msgMap.get("arenaConvertId"); //竞技场兑换字典Id
		int convertNum = (Integer)msgMap.get("convertNum"); //兑换次数
		
		//防止传来的参数为负数或0
		if (convertNum <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		String currTime = DateUtil.getCurrTime();
		InstPlayerArena instPlayerArena = getInstPlayerArenaDAL().getModel(instPlayerArenaId, 0);  //玩家自己排名数据
		DictArenaConvert dictArenaConvert = DictMap.dictArenaConvertMap.get(arenaConvertId + "");
		if (instPlayerArena.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (instPlayerArena.getPrestige() < dictArenaConvert.getPrestige() * convertNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_arenaNum);
			return;
		}
		
		List<InstPlayerArenaConvert> instPlayerArenaConvertList = getInstPlayerArenaConvertDAL().getList(" instPlayerId = " + instPlayerId + " and arenaConvertId = " + arenaConvertId, instPlayerId);
		if(instPlayerArenaConvertList.size() <= 0){
			if(convertNum > dictArenaConvert.getConvertNum()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notConvertNum);
				return;
			}
			InstPlayerArenaConvert instPlayerArenaConvert = new InstPlayerArenaConvert();
			instPlayerArenaConvert.setInstPlayerId(instPlayerId);
			instPlayerArenaConvert.setArenaConvertId(arenaConvertId);
			instPlayerArenaConvert.setConvertNum(convertNum);
			instPlayerArenaConvert.setOperTime(currTime);
			getInstPlayerArenaConvertDAL().add(instPlayerArenaConvert, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerArenaConvert, instPlayerArenaConvert.getId(), instPlayerArenaConvert.getResult(), syncMsgData);
			
		}else{
			InstPlayerArenaConvert instPlayerArenaConvert = instPlayerArenaConvertList.get(0);
			if(dictArenaConvert.getConvertType() == 1){ //1-总共
				if(convertNum + instPlayerArenaConvert.getConvertNum() > dictArenaConvert.getConvertNum()){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notConvertNum);
					return;
				}
			}else{
				if(!DateUtil.isSameDay(instPlayerArenaConvert.getOperTime(), currTime, DateFormat.YMDHMS)){
					instPlayerArenaConvert.setConvertNum(0);
				}
				if(convertNum + instPlayerArenaConvert.getConvertNum() > dictArenaConvert.getConvertNum()){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notConvertNum);
					return;
				}
			}
			instPlayerArenaConvert.setConvertNum(instPlayerArenaConvert.getConvertNum() + convertNum);
			instPlayerArenaConvert.setOperTime(currTime);
			getInstPlayerArenaConvertDAL().update(instPlayerArenaConvert, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArenaConvert, instPlayerArenaConvert.getId(), instPlayerArenaConvert.getResult(), syncMsgData);
		}
		//添加玩家兑换的物品
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		ThingUtil.groupThing(instPlayer, dictArenaConvert.getTableTypeId(), dictArenaConvert.getTableFieldId(), dictArenaConvert.getValue() * convertNum, syncMsgData, msgMap);
		
		instPlayerArena.setPrestige(instPlayerArena.getPrestige() - dictArenaConvert.getPrestige() * convertNum);
		getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
		
		String log = "竞技场兑换： instPlayerId=" + instPlayerId + " 兑换个数=" + convertNum + " 消耗威望=" + dictArenaConvert.getPrestige() * convertNum + " 获得物品=" + (dictArenaConvert.getTableTypeId() + "_" + dictArenaConvert.getTableFieldId() + "_" + dictArenaConvert.getValue() * convertNum);
		LogUtil.info(log);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 获取玩家玩家战斗相关数据
	 * @author hzw
	 * @date 2014-11-5下午5:43:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void enemyPlayerInfo(Map<String, Object> msgMap, String channelId) throws Exception {
		
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		MessageData syncMsgData = new MessageData();
		int playerId = (Integer)msgMap.get("playerId"); //挑战的玩家Id
		
		MessageData retMsgData = new MessageData();
		if(playerId > 1000000){
			//组织NPC战斗相关数据
			retMsgData = EnemyPlayerUtil.retEnemyNPCInfoMsgData(retMsgData, playerId);
		}else{
			//组织敌方玩家战斗相关数据
			retMsgData = EnemyPlayerUtil.retEnemyPlayerInfoMsgData(retMsgData, playerId);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

}
