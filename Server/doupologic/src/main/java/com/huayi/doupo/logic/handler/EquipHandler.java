package com.huayi.doupo.logic.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.model.*;
import com.huayi.doupo.logic.handler.util.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.base.model.statics.StaticDailyTask;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.LogicLogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 装备处理类
 * @author mp
 * @date 2014-6-30 下午2:23:03
 */
public class EquipHandler extends BaseHandler{
	
	/**
	 * 卸装备
	 * @author mp
	 * @date 2014-6-30 上午11:09:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void putOffEquip(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		int instPlayerEquipId = (Integer)msgMap.get("instPlayerEquipId");
		
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		
		//验证玩家是否一致
		if (instPlayerCard.getInstPlayerId() != instPlayerId || instPlayerEquip.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证此装备是否在阵容上
		if (instPlayerEquip.getInstCardId() == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipNotInLineup);
			return;
		}
		
		//修改装备实例中"装备在哪张卡牌"字段为0，装备卸掉表示此装备没装备在任何卡牌上，这时该字段为0
		instPlayerEquip.setInstCardId(0);
		getInstPlayerEquipDAL().update(instPlayerEquip, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerEquip, instPlayerEquip.getId(), instPlayerEquip.getResult(), syncMsgData);
		
		//删除阵容表中此卡牌下的这个装备记录
		InstPlayerFormation instPlayerFormation = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId + " and instCardId =  " + instPlayerCardId, instPlayerId).get(0);
		InstPlayerLineup instPlayerLineup = getInstPlayerLineupDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerFormationId =  " + instPlayerFormation.getId() + " and instPlayerEquipId = " + instPlayerEquipId, instPlayerId).get(0);
		getInstPlayerLineupDAL().deleteById(instPlayerLineup.getId(), instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerLineup, instPlayerLineup.getId(), "", syncMsgData);
		
		//验证洗练成就
		AchievementUtil.wash(instPlayerId, syncMsgData);
		
		//验证强化装备成就
		AchievementUtil.strengthen(instPlayerId, syncMsgData);
		
		//验证穿齐装备成就
		AchievementUtil.addEquip(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 添加/更换装备
	 * @author hzw
	 * @date 2014-6-30下午3:34:34
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addEquipment(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerEquipId = (Integer)msgMap.get("instPlayerEquipId");
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		int equipTypeId = (Integer)msgMap.get("equipTypeId");
		int operate = (Integer)msgMap.get("operate");//operate = 1 添加  2 更换
		
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		
		//验证参数
		if (instPlayerCard.getInstPlayerId() != instPlayerId || instPlayerEquip.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (instPlayerEquip.getEquipTypeId() != equipTypeId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notEquipType);
			return;
		}
		
		//验证此装备是否在阵容上
		if (instPlayerEquip.getInstCardId() != 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipInLineup);
			return;
		}
		
		InstPlayerFormation instPlayerFormation = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId).get(0);
		int instPlayerFormationId = instPlayerFormation.getId();
		
		if(operate == 1){
			//添加装备
			InstPlayerLineup instPlayerLineup = new InstPlayerLineup();
			instPlayerLineup.setInstPlayerId(instPlayerId);
			instPlayerLineup.setInstPlayerEquipId(instPlayerEquipId);
			instPlayerLineup.setEquipTypeId(DictMap.dictEquipmentMap.get(instPlayerEquip.getEquipId()+"").getEquipTypeId());
			instPlayerLineup.setInstPlayerFormationId(instPlayerFormationId);
			getInstPlayerLineupDAL().add(instPlayerLineup, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerLineup, instPlayerLineup.getId(), instPlayerLineup.getResult(), syncMsgData);
			
		}else if(operate == 2){
			//更换装备
			List<InstPlayerLineup> InstPlayerLineupList = getInstPlayerLineupDAL().getList("instPlayerId = " + instPlayerId + " and equipTypeId = " + equipTypeId + " and instPlayerFormationId = " + instPlayerFormationId, instPlayerId);
			InstPlayerLineup instPlayerLineup = InstPlayerLineupList.get(0);
			
			//更新就装备的卡牌字段
			InstPlayerEquip oldinstPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerLineup.getInstPlayerEquipId(), instPlayerId);
			oldinstPlayerEquip.setInstCardId(0);
			getInstPlayerEquipDAL().update(oldinstPlayerEquip, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, oldinstPlayerEquip, oldinstPlayerEquip.getId(), oldinstPlayerEquip.getResult(), syncMsgData);
			
			//更新阵容
			instPlayerLineup.setInstPlayerEquipId(instPlayerEquipId);
			instPlayerLineup.setEquipTypeId(DictMap.dictEquipmentMap.get(instPlayerEquip.getEquipId()+"").getEquipTypeId());
			getInstPlayerLineupDAL().update(instPlayerLineup, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerLineup, instPlayerLineup.getId(), instPlayerLineup.getResult(), syncMsgData);
			
		}
		
		//更新装备的卡牌字段
		instPlayerEquip.setInstCardId(instPlayerFormation.getInstCardId());
		getInstPlayerEquipDAL().update(instPlayerEquip, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerEquip, instPlayerEquip.getId(), instPlayerEquip.getResult(), syncMsgData);
		
		//验证洗练成就
	    AchievementUtil.wash(instPlayerId, syncMsgData);
	    
	    //验证强化装备成就
	  	AchievementUtil.strengthen(instPlayerId, syncMsgData);
	  	
	    //验证穿齐装备成就
	    AchievementUtil.addEquip(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 强化装备
	 * @author hzw
	 * @date 2014-7-1上午10:17:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void strengthen(HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerEquipId = (Integer)msgMap.get("instPlayerEquipId");
		InstPlayerEquip playerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		//验证参数
		if (playerEquip.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int level = 0;
		if(instPlayer.getLevel() * 2 > playerEquip.getLevel()){
			level = 1;
		}
		if(level <= 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_streng_level);
			return;
		}
		Map<String, Integer> retMap = EquipmentUtil.strengthen(playerEquip, level, ConvertUtil.toInt(instPlayer.getCopper()));
		int copper = retMap.get("copper");
		level = retMap.get("addLevel");
		//验证强化条件
		if(copper <= 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}
		
		
		//更新装备等级
		playerEquip.setLevel(playerEquip.getLevel() + level);
		getInstPlayerEquipDAL().update(playerEquip, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, playerEquip, playerEquip.getId(), playerEquip.getResult(), syncMsgData);
		
		//更新玩家因强化而消耗的龙币
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - copper) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.equipStrength, 1, syncMsgData);
		
		//验证强化装备成就
	  	AchievementUtil.strengthen(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("1", level);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 一键强化
	 * @author hzw
	 * @date 2014-7-2下午3:30:23
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void quickStrengthen(HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerEquipId = (Integer)msgMap.get("instPlayerEquipId");
		InstPlayerEquip playerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		//验证参数
		if (playerEquip.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int level = instPlayer.getLevel() * 2 - playerEquip.getLevel();
		if(level <= 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_streng_level);
			return;
		}
		
		//验证强化条件
		Map<String, Integer> retMap = EquipmentUtil.strengthen(playerEquip, level, ConvertUtil.toInt(instPlayer.getCopper()));
		int copper = retMap.get("copper");
		level = retMap.get("addLevel");
		//验证强化条件
		if(copper <= 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}
		
		//更新装备等级
		playerEquip.setLevel(playerEquip.getLevel() + level);
		getInstPlayerEquipDAL().update(playerEquip, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, playerEquip, playerEquip.getId(), playerEquip.getResult(), syncMsgData);
		
		//更新玩家因强化而消耗的龙币
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - copper) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.equipStrength, level, syncMsgData);
		
		//验证强化装备成就
	  	AchievementUtil.strengthen(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("1", level);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/*
	*//**
	 * 洗练
	 * @author hzw
	 * @date 2014-7-2下午4:37:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 *//*
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void refinement(HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		int instPlayerEquipId = (Integer)msgMap.get("instPlayerEquipId");
		String fightPropIdList = (String)msgMap.get("fightPropIdList");   //instPlayerWashId_fightPropId   instPlayerWashId用于删除实例数据    fightPropId用于生成实例数据
		InstPlayerEquip playerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		DictEquipment dictEquipment = DictMap.dictEquipmentMap.get(playerEquip.getEquipId()+"");
		String[] paramList = fightPropIdList.split(";");
		//验证参数
		if (playerEquip.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		List<InstPlayerThing> instPlayerThingWashRocks = (List<InstPlayerThing>)getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.washRock, instPlayerId);
		if(instPlayerThingWashRocks.size() > 0){
			if(instPlayerThingWashRocks.get(0).getNum() < dictEquipment.getUseTalentValue()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notWashRockNum);
				return;
			}
		}
		else{
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notWashRockNum);
			return;
		}
		List<InstPlayerThing> instPlayerThingLocks = null;
		if(paramList.length < 4){
			instPlayerThingLocks = (List<InstPlayerThing>)getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.lock, instPlayerId);
			if(instPlayerThingLocks.size() > 0){
				if(instPlayerThingLocks.get(0).getNum() < (4 - paramList.length)){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLockNum);
					return;
				}
			}
			else{
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLockNum);
					return;
			}
		}
		
		
		
		List<DictEquipWash> objList = DictList.dictEquipWashList;
		List<DictEquipWash> dictEquipWashList = new ArrayList<DictEquipWash>();
		
		for(DictEquipWash obj : objList){
			if(obj.getEquipQualityId() == dictEquipment.getEquipQualityId()){
				dictEquipWashList.add(obj);
			}
		}
		for(String str : paramList){
			float random = RandomUtil.getRandomFloat();
			List<DictEquipWash> dictEquipWashs = new ArrayList<DictEquipWash>();
			DictEquipWash tempDictEquipWash = new DictEquipWash();
			Float num = 0.0f;
			for(DictEquipWash obj : dictEquipWashList){
				if(obj.getFightPropId() == ConvertUtil.toInt(str.split("_")[1])){
					num += obj.getChance();
					dictEquipWashs.add(obj);
				}
			}
			Float sum = 0.00f;
			for(DictEquipWash obj : dictEquipWashs){
				sum += (float)obj.getChance() / (float)num;
				if(random <= sum){
					tempDictEquipWash = obj;
					break;
				}
			}
			
			if(ConvertUtil.toInt(str.split("_")[0]) != 0){
				InstPlayerWash instPlayerWash = getInstPlayerWashDAL().getModel(ConvertUtil.toInt(str.split("_")[0]), instPlayerId);
				getInstPlayerWashDAL().deleteById(ConvertUtil.toInt(str.split("_")[0]), instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerWash, instPlayerWash.getId(), "", syncMsgData);
			}
			
			InstPlayerWash instPlayerWash = new InstPlayerWash();
			instPlayerWash.setInstPlayerId(instPlayerId);
			instPlayerWash.setInstPlayerEquipId(instPlayerEquipId);
			instPlayerWash.setFightPropId(tempDictEquipWash.getFightPropId());
			instPlayerWash.setEquipWashId(tempDictEquipWash.getId());
			getInstPlayerWashDAL().add(instPlayerWash, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerWash, instPlayerWash.getId(), instPlayerWash.getResult(), syncMsgData);
			
		}
		
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThingWashRocks.get(0), dictEquipment.getUseTalentValue(), syncMsgData);
		if(paramList.length < 4){
			ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThingLocks.get(0), 4 - paramList.length, syncMsgData);
		}
		
		//验证洗练成就
		AchievementUtil.wash(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	*/

	/**
	 * 装备-打孔
	 * @author mp
	 * @date 2014-7-2 上午10:51:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void openHole(HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerEquipId = (Integer)msgMap.get("instPlayerEquipId");
		
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
		InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		if(instPlayerId != instPlayerEquip.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//计算打孔器数量
		int qualityId = DictMap.dictEquipmentMap.get(instPlayerEquip.getEquipId() + "").getEquipQualityId();
		List<InstEquipGem> instEquipGemList = getInstEquipGemDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerEquipId = " + instPlayerEquipId, instPlayerId);
		int times = instEquipGemList.size();
		List<DictHoleConsume> holeConsumeList = (List<DictHoleConsume>)DictMapList.dictHoleConsumeMap.get(qualityId);
		DictHoleConsume holeConsume = DictUtil.getDictHoleConsume(holeConsumeList, times);
		if (holeConsume == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_openHole_upMaxNum);
			return ;
		}
		int openHoleNum = holeConsume.getNum();
		
		//验证打孔器数量是否充足
		int ownOpenHoleNum = 0;
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.openHole, instPlayerId);
		InstPlayerThing instPlayerThing = null;
		if(instPlayerThingList.size() > 0){
			instPlayerThing = instPlayerThingList.get(0);
			ownOpenHoleNum = instPlayerThing.getNum();
		}
		if(ownOpenHoleNum < openHoleNum){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_openHole_notEnough);
			return ;
		}
		
		//记录日志
		try {
			int source = 0;//来源-协议号
			String desc = "";//来源描述
			if (msgMap != null) {
				source = (int)msgMap.get("header");
				desc = DictMap.sysMsgRuleMap.get(source + "").getName();
			}
			int thingId = instPlayerThing.getThingId();
			DictThing dictThing = DictMap.dictThingMap.get(thingId + "");
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|2|" + "" + "|" + "" + "|" + instPlayerThing.getThingId() + "|" + dictThing.getName() + "|" + ownOpenHoleNum + "|" + (ownOpenHoleNum - openHoleNum) + "|" + openHoleNum + "|" + source + "|" + desc;
			LogicLogUtil.info("things", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("道具/物品日志错误", e);
		}
		
		//减去消耗的打孔器
		if(ownOpenHoleNum - openHoleNum <= 0){
			getInstPlayerThingDAL().deleteById(instPlayerThing.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerThing, instPlayerThing.getId(), "", syncMsgData);
		}else{
			instPlayerThing.setNum(ownOpenHoleNum - openHoleNum);
			getInstPlayerThingDAL().update(instPlayerThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
		}
		
		//向装备上添加宝石槽
		InstEquipGem instEquipGem = EquipmentUtil.addEquipmentGem(times, instPlayerEquipId, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instEquipGem, instEquipGem.getId(), instEquipGem.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 装备-镶嵌宝石
	 * @author mp
	 * @date 2014-7-2 上午10:51:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void equipInlay(Map<String, Object> msgMap, String channelId) throws Exception {

		//获取参数
		MessageData syncMsgData = new MessageData();
		int position = (Integer)msgMap.get("position");
		int instPlayerThingId = (Integer)msgMap.get("instPlayerThingId");
		int instPlayerEquipId = (Integer)msgMap.get("instPlayerEquipId");
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		//是否达到开放等级
		DictFunctionOpen functionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.inly + "");
		if(instPlayer.getLevel() < functionOpen.getLevel()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return ;
		}
		
		//获取参数
		InstPlayerThing instPlayerThing = getInstPlayerThingDAL().getModel(instPlayerThingId, instPlayerId);
		InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		List<InstEquipGem> instEquipGemList = getInstEquipGemDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerEquipId = " + instPlayerEquipId + " and position = " + position, instPlayerId);
		
		if (instPlayerThing == null || instPlayerEquip == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noThing);
			return;
		}
		
		//玩家是否一致
		if(instPlayerId != instPlayerThing.getInstPlayerId() || instPlayerId != instPlayerEquip.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		//是否打孔
		if(instEquipGemList.size() <= 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_inlay_notOpenHole);
			return ;
		}
		
		//防止数量为负数
		if (instPlayerThing.getNum() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return ;
		}
		
		//将宝石放入槽中
		InstEquipGem instEquipGem = instEquipGemList.get(0);
		instEquipGem.setThingId(instPlayerThing.getThingId());
		getInstEquipGemDAL().update(instEquipGem, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instEquipGem, instEquipGem.getId(), instEquipGem.getResult(), syncMsgData);
		
		//记录日志
		try {
			int source = 0;//来源-协议号
			String desc = "";//来源描述
			if (msgMap != null) {
				source = (int)msgMap.get("header");
				desc = DictMap.sysMsgRuleMap.get(source + "").getName();
			}
			int thingId = instPlayerThing.getThingId();
			DictThing dictThing = DictMap.dictThingMap.get(thingId + "");
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|2|" + "" + "|" + "" + "|" + instPlayerThing.getThingId() + "|" + dictThing.getName() + "|" + instPlayerThing.getNum() + "|" + (instPlayerThing.getNum() - 1) + "|" + "1" + "|" + source + "|" + desc;
			LogicLogUtil.info("things", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("道具/物品日志错误", e);
		}
		
		//宝石数量对应减1
		if(instPlayerThing.getNum() - 1 <= 0){
			getInstPlayerThingDAL().deleteById(instPlayerThing.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerThing, instPlayerThing.getId(), "", syncMsgData);
		}else{
			instPlayerThing.setNum(instPlayerThing.getNum() - 1);
			getInstPlayerThingDAL().update(instPlayerThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
		}
		
		//给此装备打上镶嵌标记,供客户端使用[服务端慷慨]
		instPlayerEquip.setIsInlay(StaticCustomDict.inInlay);
		getInstPlayerEquipDAL().update(instPlayerEquip, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerEquip, instPlayerEquip.getId(), instPlayerEquip.getResult(), syncMsgData);
		
		//验证镶嵌魔核成就
	    AchievementUtil.inlay(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 装备-宝石拆除
	 * @author mp
	 * @date 2014-7-2 上午10:52:21
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void takeOffGem(HashMap<String, Object> msgMap, String channelId) throws Exception{
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instEquipmentGemId = (Integer)msgMap.get("instEquipGemId");
		InstEquipGem instEquipGem = getInstEquipGemDAL().getModel(instEquipmentGemId, instPlayerId);
		
		//玩家是否一致
		if(instPlayerId != instEquipGem.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//拆除宝石后,更新宝石数量[数量加1]
		int thingId = instEquipGem.getThingId();
		ThingUtil.addInstPlayerThing(instPlayerId, thingId, 1, syncMsgData, msgMap);
		
		//将宝石从槽中拆除
		instEquipGem.setThingId(0);
		getInstEquipGemDAL().update(instEquipGem, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instEquipGem, instEquipGem.getId(), instEquipGem.getResult(), syncMsgData);
		
		//检查更新此装备是否镶嵌宝石的状态
		int instPlayerEquipmentId = instEquipGem.getInstPlayerEquipId();
		InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipmentId, instPlayerId);
		List<InstEquipGem> instEquipGemList = getInstEquipGemDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerEquipId = " + instPlayerEquipmentId + " and thingId != 0", instPlayerId);
		if(instEquipGemList.size() <= 0){
			instPlayerEquip.setIsInlay(StaticCustomDict.unInlay);
			getInstPlayerEquipDAL().update(instPlayerEquip, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerEquip, instPlayerEquip.getId(), instPlayerEquip.getResult(), syncMsgData);
		}
		
		//验证镶嵌魔核成就
	    AchievementUtil.inlay(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 装备拼合
	 * @author hzw
	 * @date 2014-7-4上午11:04:38
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void equipPiece(HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instThingId = (Integer)msgMap.get("instThingId");
		InstPlayerThing instPlayerThing = getInstPlayerThingDAL().getModel(instThingId, instPlayerId);
		
		//验证玩家是否一致
		if(instPlayerId != instPlayerThing.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		DictThing dictThing = DictMap.dictThingMap.get(instPlayerThing.getThingId() + "");
		
		DictEquipment dictEquipment = DictMap.dictEquipmentMap.get(dictThing.getEquipmentId() + "");
		DictEquipQuality dictEquipQuality = DictMap.dictEquipQualityMap.get(dictEquipment.getEquipQualityId() + "");
		
		if(instPlayerThing.getNum() < dictEquipQuality.getThingNum()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notThingNum);
			return;
		}
		
		//处理消耗的碎片
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, dictEquipQuality.getThingNum(), syncMsgData, msgMap);
		
		InstPlayerEquip instPlayerEquip = EquipmentUtil.addEquipment(instPlayerId, dictThing.getEquipmentId());
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerEquip, instPlayerEquip.getId(), instPlayerEquip.getResult(), syncMsgData);
		
		//验证紫色装备成就
		AchievementUtil.purpleEquip(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 装备进阶
	 * @author hzw
	 * @date 2015-7-2上午11:43:58
	 * @update	2015/12/08 by cui
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void equipAdvance(HashMap<String, Object> msgMap, String channelId) throws Exception {


		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerEquipId = (Integer)msgMap.get("instPlayerEquipId");
		int wishWaterNum = (Integer)msgMap.get("wishWaterNum");//祝福神水个数
		int luckStoreNum = (Integer)msgMap.get("luckStoreNum");//幸运石个数
		
		//验证客户端发来的个数是否为负数
		if (wishWaterNum < 0 || luckStoreNum < 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证最大数
		if (wishWaterNum > 1 || luckStoreNum > 10) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证参数
		InstPlayerEquip playerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		if (playerEquip.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		List<DictEquipAdvance> equipAdvanceList = (List<DictEquipAdvance>)DictMapList.dictEquipAdvanceMap.get(playerEquip.getEquipTypeId());
		int equipAdvanceId = playerEquip.getEquipAdvanceId();

		//Updated by cui
		MessageData syncMsgData = new MessageData();

		//验证当前是否已经为满级了,满级了不允许再次操作
//		if (equipAdvanceId != 0) {
//			DictEquipAdvance dictEquipAdvanceYz = DictMap.dictEquipAdvanceMap.get(equipAdvanceId + "");
//			if(dictEquipAdvanceYz.getEquipQualityId() > 4){ //红品质装备
//				if(dictEquipAdvanceYz.getStarLevel() >= 5){
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
//					return;
//				}
//				//判断消费是否可进阶
//				List<DictEquipAdvancered> dictEquipAdvancereds = (List<DictEquipAdvancered>) DictMapList.dictEquipAdvanceredMap.get(playerEquip.getEquipId());
//				int contions  = 0;
//				for (DictEquipAdvancered obj : dictEquipAdvancereds){
//					if(obj.getStarLevel() <= dictEquipAdvanceYz.getStarLevel()) {
//						contions += Integer.parseInt(obj.getContions());
//					}
//				}
//
//				if(contions == 0){ //可能没有开放，所以强制返回
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
//					return;
//				}
//				List<InstPlayerRedEquip> instPlayerRedEquip = getInstPlayerRedEquipDAL().getList(playerEquip.getId() + "",instPlayerId);
//				if(instPlayerRedEquip == null || contions > instPlayerRedEquip.get(0).getContions()){
//					MessageUtil.sendFailMsg(channelId, msgMap, "淬炼值不足");
//					return;
//				}
//				int playerContions = instPlayerRedEquip.get(0).getContions();
//
//				//进阶到红装
//				DictEquipAdvance dictEquipAdvanceNew = null;
//				for(DictEquipAdvance obj : equipAdvanceList){
//					if(obj.getEquipQualityId() == 5 && obj.getStarLevel() == dictEquipAdvanceYz.getStarLevel() + 1){
//						dictEquipAdvanceNew = obj;
//						break;
//					}
//				}
//				if(dictEquipAdvanceNew == null){
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
//					return;
//				}
//				playerEquip.setEquipAdvanceId(dictEquipAdvanceNew.getId());
//
//				getInstPlayerEquipDAL().update(playerEquip, instPlayerId);
//				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, playerEquip, playerEquip.getId(), playerEquip.getResult(), syncMsgData);
//
//				MessageData retMsgData = new MessageData();
//				retMsgData.putIntItem("1", 1);
//
//				//记录日志
//				String log = "装备进阶红色：instPlayerId=" + instPlayerId + " 当前淬炼值=" + playerContions + " 进阶等级=" + dictEquipAdvanceNew.getId() + " 进阶结果=成功";
//				LogUtil.info(log);
//
//				MessageUtil.sendSyncMsg(channelId, syncMsgData);
//				MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
//				return;
//			}else if(dictEquipAdvanceYz.getEquipQualityId() == 4 && dictEquipAdvanceYz.getStarLevel() >= 5){
//				//判断消费是否可进阶
//				List<DictEquipAdvancered> dictEquipAdvancereds = (List<DictEquipAdvancered>) DictMapList.dictEquipAdvanceredMap.get(playerEquip.getEquipId());
//				int contions  = 0;
//				for (DictEquipAdvancered obj : dictEquipAdvancereds){
//					if(obj.getStarLevel() == 0) {
//						contions += Integer.parseInt(obj.getContions());
//						break;
//					}
//				}
//				if(contions == 0){ //可能没有开放，所以强制返回
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
//					return;
//				}
//				List<InstPlayerRedEquip> instPlayerRedEquip = getInstPlayerRedEquipDAL().getList(playerEquip.getId() + "",instPlayerId);
//				if(instPlayerRedEquip == null || contions > instPlayerRedEquip.get(0).getContions()){
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
//					return;
//				}
//				int playerContions = instPlayerRedEquip.get(0).getContions();
//
//				//进阶到红装
//				DictEquipAdvance dictEquipAdvanceNew = null;
//				for(DictEquipAdvance obj : equipAdvanceList){
//					if(obj.getEquipQualityId() == 5 && obj.getStarLevel() == 0){
//						dictEquipAdvanceNew = obj;
//						break;
//					}
//				}
//				if(dictEquipAdvanceNew == null){
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
//					return;
//				}
//				playerEquip.setEquipAdvanceId(dictEquipAdvanceNew.getId());
//
//				getInstPlayerEquipDAL().update(playerEquip, instPlayerId);
//				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, playerEquip, playerEquip.getId(), playerEquip.getResult(), syncMsgData);
//
//				MessageData retMsgData = new MessageData();
//				retMsgData.putIntItem("1", 1);
//
//				//记录日志
//				String log = "装备进阶红色：instPlayerId=" + instPlayerId + " 当前淬炼值=" + playerContions + " 进阶等级=" + dictEquipAdvanceNew.getId() + " 进阶结果=成功";
//				LogUtil.info(log);
//
//				MessageUtil.sendSyncMsg(channelId, syncMsgData);
//				MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
//				return;
//			}else{
//				if (dictEquipAdvanceYz.getEquipQualityId() < 3 || (dictEquipAdvanceYz.getEquipQualityId() == 3 && dictEquipAdvanceYz.getStarLevel() >= 3)) {
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
//					return;
//				}
//			}
//
//		}


		//验证当前是否已经为满级了,满级了不允许再次操作   ,红卡时不通过这个协议
		if (equipAdvanceId != 0) {
			DictEquipAdvance dictEquipAdvanceYz = DictMap.dictEquipAdvanceMap.get(equipAdvanceId + "");
			if (dictEquipAdvanceYz.getStarLevel() >= 5 || dictEquipAdvanceYz.getEquipQualityId() > 4) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
				return;
			}
		}
		
		DictEquipAdvance dictEquipAdvance = null;
		if(equipAdvanceId == 0){
			dictEquipAdvance = DictMap.dictEquipAdvanceMap.get(DictMap.dictEquipmentMap.get(playerEquip.getEquipId() + "").getEquipAdvanceId() + "");
		}else{
			dictEquipAdvance = DictMap.dictEquipAdvanceMap.get(equipAdvanceId + "");
			for(DictEquipAdvance obj : equipAdvanceList){
				if(obj.getEquipQualityId() == dictEquipAdvance.getEquipQualityId() && obj.getStarLevel() == dictEquipAdvance.getStarLevel() + 1){
					dictEquipAdvance = obj;
					break;
				}
			}
		}
		
		//验证银币
		String conditions[] = dictEquipAdvance.getContions().split("_");
		if(ConvertUtil.toInt(instPlayer.getCopper()) < ConvertUtil.toInt(conditions[1])){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}
		
		//验证装备是否足够
		List<InstPlayerEquip> instPlayerEquipList = getInstPlayerEquipDAL().getList("instPlayerId = " + instPlayerId + " and equipId = " + playerEquip.getEquipId() + " and isInlay = 0 and instCardId = 0 and equipAdvanceId = 0 and id != " + instPlayerEquipId, instPlayerId);
		if(instPlayerEquipList.size() < ConvertUtil.toInt(conditions[0])){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveMagicCore);
			return;
		}
		
		//验证祝福神水
		int ifFailDropStar = 0; //会掉星（重置为初始）
		int consumeWishWaterNum = 0;
		
		//验证祝福神水个数是否足够（是否够1）
		if (wishWaterNum > 0) {
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.wishWater, instPlayerId);
			if (instPlayerThingList.size() <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
				return;
			}
			InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
			//个数不可为负数
			if (instPlayerThing.getNum() < 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
				return;
			}
			if (instPlayerThing.getNum() < 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
				return;
			}
			consumeWishWaterNum = 1;//不论客户端传来的是几个祝福神水,只扣1个,规则是这样的
			ifFailDropStar = 1;//等于1,表示进阶失败时,不会掉星
		}
		
		//验证幸运石个数是否足够
		if (luckStoreNum > 0) {
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.luckStore, instPlayerId);
			if (instPlayerThingList.size() <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
				return;
			}
			InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
			//个数不可为负数
			if (instPlayerThing.getNum() < 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
				return;
			}
			if (instPlayerThing.getNum() < luckStoreNum) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
				return;
			}
		}
		
		//更新玩家因强化而消耗的龙币
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - ConvertUtil.toInt(conditions[1])) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//消耗装备
		String consumEquips = "";
		for(int i = 0; i < ConvertUtil.toInt(conditions[0]); i++){
//			getInstPlayerEquipDAL().deleteById(instPlayerEquipList.get(i).getId(), instPlayerId);
			//Update by cui @date 2015/12/09,删除装备时需要多部操作、所以封装到一个方法上
			EquipUtil.deletePlayerEquip(instPlayerEquipList.get(i).getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerEquipList.get(i), instPlayerEquipList.get(i).getId(), "", syncMsgData);
			consumEquips += DictMap.dictEquipmentMap.get(instPlayerEquipList.get(i).getEquipId() + "").getName() + ";";
		}
		
		//消耗祝福神水
		if (wishWaterNum > 0) {
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.wishWater, instPlayerId);
			InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
			ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, consumeWishWaterNum, syncMsgData, msgMap);
		}
		
		//消耗幸运石
		if (luckStoreNum > 0) {
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.luckStore, instPlayerId);
			InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
			ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, luckStoreNum, syncMsgData, msgMap);
		}
		
		//验证进阶结果
		int basePerNum = 0;//基础概率值
		if (dictEquipAdvance.getStarLevel() == 1) {//表示0->1
			basePerNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.equipAdwance0To1);
		} else if (dictEquipAdvance.getStarLevel() == 2) {//表示1->2
			basePerNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.equipAdwance1To2);
		} else if (dictEquipAdvance.getStarLevel() == 3) {//表示2->3
			basePerNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.equipAdwance2To3);
		} else if (dictEquipAdvance.getStarLevel() == 4) {//表示3->4
			basePerNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.equipAdwance3To4);
		} else if (dictEquipAdvance.getStarLevel() == 5) {//表示4->5
			basePerNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.equipAdwance4To5);
		}
		
		//幸运石增加的概率
		int extraPerNum = 0;//幸运石额外增加的概率值
		if (luckStoreNum > 0) {
			extraPerNum = luckStoreNum * DictMap.dictThingMap.get(StaticThing.luckStore + "").getValue();
		}
		int sumPerNum = basePerNum + extraPerNum;
		int random = RandomUtil.getRangeInt(1, 100);
		int isAdwanceSucc = 0;
		if (random <= sumPerNum) {
			isAdwanceSucc = 1;
		}
		
		//更新装备等级
		if (isAdwanceSucc == 1) {//进阶成功
			playerEquip.setEquipAdvanceId(dictEquipAdvance.getId());
			getInstPlayerEquipDAL().update(playerEquip, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, playerEquip, playerEquip.getId(), playerEquip.getResult(), syncMsgData);
		} else {//进阶失败
			if (ifFailDropStar == 0) {//没有祝福神水的时候,装备进阶重置为0星
				playerEquip.setEquipAdvanceId(0);
				getInstPlayerEquipDAL().update(playerEquip, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, playerEquip, playerEquip.getId(), playerEquip.getResult(), syncMsgData);
			}
		}
		
		//1-成功  2-失败0星  3-失败不变
		String result = "";
		int resul = 0;
		if (isAdwanceSucc == 1) {
			resul = 1;
			result = "成功";
		} else {
			if (ifFailDropStar == 0) {
				resul = 2;
				result = "失败0星";
			} else {
				resul = 3;
				result = "失败星数不变";
			}
		}
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("1", resul);
		
		//记录日志
		String log = "装备进阶：instPlayerId=" + instPlayerId + " 消耗祝福水个数=" + consumeWishWaterNum + " 消耗幸运石个数=" + luckStoreNum + " 消耗装备列表=" + consumEquips + " 进阶结果=" + result;
		LogUtil.info(log);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 使用淬炼石
	 * @author cui
	 * @date	2015/12/08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void useAdvanceThing(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);


		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}

		int instPlayerEquipId = (Integer) msgMap.get("instPlayerEquipId"); //装备ID
		int thingid = (Integer) msgMap.get("thingid");//使用的淬炼石实力ID
		int count = (Integer) msgMap.get("count");//淬炼石个数

		//验证参数
		InstPlayerEquip playerEquip = getInstPlayerEquipDAL().getModel(instPlayerEquipId, instPlayerId);
		if (playerEquip.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		if (count <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "非法数据-1");
			return;
		}

		int equipAdvanceId = playerEquip.getEquipAdvanceId();
		if (equipAdvanceId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "对这装备无法使用淬炼石");
			return;
		}

		DictEquipAdvance dictEquipAdvance = DictMap.dictEquipAdvanceMap.get(equipAdvanceId + "");
		if (dictEquipAdvance.getEquipQualityId() < 4 || (dictEquipAdvance.getEquipQualityId() == 4 && dictEquipAdvance.getStarLevel() < 5)) {
			MessageUtil.sendFailMsg(channelId, msgMap, "对这装备无法使用淬炼石");
			return;
		}

		if (dictEquipAdvance.getEquipQualityId() == 5 && dictEquipAdvance.getStarLevel() >= 5) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
			return;
		}



		//判断玩家身上有没有这么多淬炼石
		List<InstPlayerThing> instPlayerThings = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and id = " + thingid, instPlayerId);
		if (instPlayerThings == null || instPlayerThings.size() == 0 || instPlayerThings.get(0).getNum() < count) {
			MessageUtil.sendFailMsg(channelId, msgMap, "淬炼石不足");
			return;
		}


		//判断消费是否可进阶
		List<DictEquipAdvancered> dictEquipAdvancereds = (List<DictEquipAdvancered>) DictMapList.dictEquipAdvanceredMap.get(playerEquip.getEquipId());
		int contions = 0;
		for (DictEquipAdvancered obj : dictEquipAdvancereds) {
			if(dictEquipAdvance.getEquipQualityId() >= 5) {
				if (obj.getStarLevel() <= dictEquipAdvance.getStarLevel()) {
					contions += Integer.parseInt(obj.getContions());
				}
				//因橙色物品变成红色时也会使用到淬炼值，所以这里必须额外多增加淬炼值
				if(obj.getStarLevel() == 0){
					contions += Integer.parseInt(obj.getContions());
				}
			}else{
				if(obj.getStarLevel() == 0){
					contions = Integer.parseInt(obj.getContions());
					break;
				}
			}
		}

		if (contions == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_equipUpAdavance);
			return;
		}

		List<InstPlayerRedEquip> instPlayerRedEquipList = getInstPlayerRedEquipDAL().getList("equipInstId = " + playerEquip.getId(), instPlayerId);
//		if (instPlayerRedEquipList != null && instPlayerRedEquipList.size() > 0 && contions <= instPlayerRedEquipList.get(0).getContions()) {
//			MessageUtil.sendFailMsg(channelId, msgMap, "淬炼值已满，快快进阶吧");
//			return;
//		}

		int playerContions = (instPlayerRedEquipList != null && instPlayerRedEquipList.size() > 0)?instPlayerRedEquipList.get(0).getContions():0;
		InstPlayerRedEquip instPlayerRedEquip = (instPlayerRedEquipList != null && instPlayerRedEquipList.size() > 0)?instPlayerRedEquipList.get(0):null;

		//计算淬炼值增加多少
		int dictThingid = instPlayerThings.get(0).getThingId();
		DictThing dictThing = DictMap.dictThingMap.get(dictThingid + "");
		if (dictThing == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "物品不存在");
			return;
		}
		if (dictThing.getLevel() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "数据异常");
			return;
		}
		int addValue = dictThing.getLevel() * count;

		//判断物品是否使用过量，当使用过量时进行智能处理
		int overrage = (playerContions + addValue - contions) / dictThing.getLevel();
		int consumeCount = count;
		if (overrage > 0) {
			consumeCount = count - overrage;
			addValue = dictThing.getLevel() * consumeCount;
		}

		MessageData syncMsgData = new MessageData();
		//消耗物品
		InstPlayerThing instPlayerThing = instPlayerThings.get(0);
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, consumeCount, syncMsgData, msgMap);

		//增加淬炼值
		if(instPlayerRedEquip == null){
			InstPlayerRedEquip newRedEquip = new InstPlayerRedEquip();
			newRedEquip.setContions(addValue);
			newRedEquip.setEquipInstId(instPlayerEquipId);
			newRedEquip.setInstPlayerId(instPlayerId);
			newRedEquip.setVersion(0);
			getInstPlayerRedEquipDAL().add(newRedEquip,instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, newRedEquip, newRedEquip.getId(), newRedEquip.getResult(), syncMsgData);
		}else{
			instPlayerRedEquip.setContions(playerContions + addValue);
			getInstPlayerRedEquipDAL().update(instPlayerRedEquip,instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerRedEquip, instPlayerRedEquip.getId(), instPlayerRedEquip.getResult(), syncMsgData);
		}

		MessageData retMsgData = new MessageData();
		//判断是否能进阶
		retMsgData.putIntItem("isAc", 0);  //不会进阶
		if (contions <= playerContions + addValue) {
			EquipUtil.checkEquipAdvance(syncMsgData,retMsgData,playerEquip,instPlayerId);
		}


		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);

	}
	
	
}
