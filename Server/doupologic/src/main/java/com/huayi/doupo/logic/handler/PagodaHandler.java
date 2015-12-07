package com.huayi.doupo.logic.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictFunctionOpen;
import com.huayi.doupo.base.model.DictPagodaDrop;
import com.huayi.doupo.base.model.DictPagodaFormation;
import com.huayi.doupo.base.model.DictPagodaStore;
import com.huayi.doupo.base.model.DictPagodaStorey;
import com.huayi.doupo.base.model.DictStrogerEquip;
import com.huayi.doupo.base.model.DictVIP;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerPagoda;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticDailyTask;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.VerifyUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class PagodaHandler extends BaseHandler{
	
	/**
	 * 爬塔——战
	 * @author hzw
	 * @date 2014-8-12下午3:16:37
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void war(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerPagodaId = (Integer)msgMap.get("instPlayerPagodaId");
		int pagodaStoreyId = (Integer)msgMap.get("pagodaStoreyId");
		String victoryValue = (String)msgMap.get("victoryValue");//战斗是否胜利_通关途径值  1-胜利 0-失败
		int type = (Integer)msgMap.get("type");   //0-普通层   1-神秘层
		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		String victoryValue2[] = victoryValue.split("_");
		InstPlayerPagoda instPlayerPagoda = getInstPlayerPagodaDAL().getModel(instPlayerPagodaId, instPlayerId);
		//验证玩家是否一致
		if (instPlayerPagoda.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证玩家打的层数是否为当前层,如果不是,可能是在刷
		int currPagodaStoreyId = instPlayerPagoda.getPagodaStoreyId();
		if (currPagodaStoreyId != pagodaStoreyId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		//用于验证玩家是否利用烧饼等修改器
		if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
			return;
		}
		
		//是否达到开放等级
		DictFunctionOpen dictFunctionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.tower + "");
		if (instPlayer.getLevel() < dictFunctionOpen.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		DictPagodaStorey dictPagodaStorey = DictMap.dictPagodaStoreyMap.get(pagodaStoreyId + "");
		DictPagodaFormation dictPagodaFormation = DictMap.dictPagodaFormationMap.get(dictPagodaStorey.getPagodaFormationId() + "");
		int victory = 0;  //1-通过   0-不通过
		if(((ConvertUtil.toInt(victoryValue2[0]) == 1) && dictPagodaStorey.getVictoryMeans() == 4 && ConvertUtil.toInt(victoryValue2[1]) == dictPagodaStorey.getVictoryValue()) || (
				(ConvertUtil.toInt(victoryValue2[0]) == 1) && dictPagodaStorey.getVictoryMeans() == 3 && ConvertUtil.toInt(victoryValue2[1]) >= dictPagodaStorey.getVictoryValue()) 
				|| (ConvertUtil.toInt(victoryValue2[0]) == 1) && (dictPagodaStorey.getVictoryMeans() == 1 || dictPagodaStorey.getVictoryMeans() == 2) && ConvertUtil.toInt(victoryValue2[1]) <= dictPagodaStorey.getVictoryValue()){
			victory = 1;
		}
		
		String thinglist = "";
		if(ConvertUtil.toInt(victoryValue2[0]) == 1){
			//获取胜利奖励
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictPagodaStorey.getCopper()) + "");
			instPlayer.setCulture(instPlayer.getCulture() + dictPagodaStorey.getCulture());
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		if(victory == 1){
			if(dictPagodaFormation.getPagodaStorey5() == pagodaStoreyId){
				String things[] = dictPagodaFormation.getReward().split(";");
				for(String thing : things){
					ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(thing.split("_")[0]), ConvertUtil.toInt(thing.split("_")[1]), ConvertUtil.toInt(thing.split("_")[2]), syncMsgData, msgMap);
				}
			}
			
			if(type == 1){
				List<DictPagodaDrop> objList = (List<DictPagodaDrop>)DictMapList.dictPagodaDropMap.get(dictPagodaStorey.getId());
				Float num = 0.0f;
				for(DictPagodaDrop obj : objList){
					num += obj.getChance();
				}
				int thingNum = RandomUtil.getRangeInt(dictPagodaStorey.getThingMin(), dictPagodaStorey.getThingMax());
				for(int i = 0; i < thingNum; i++){
					float random = RandomUtil.getRandomFloat();
					Float sum = 0.00f;
					for(DictPagodaDrop obj : objList){
						sum += (float)obj.getChance() / (float)num;
						if(random <= sum){
							thinglist += obj.getId() + ";";
							ThingUtil.groupThing(instPlayer, obj.getTableTypeId(), obj.getTableFieldId(), obj.getValue(), syncMsgData, msgMap);
							break;
						}
					}
				}
			}
			
			
			//更新爬塔实例数据
			instPlayerPagoda.setNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.instPlayerPagodaNum));
			instPlayerPagoda.setPagodaStoreyId(pagodaStoreyId + 1);
		}else{
			instPlayerPagoda.setNum(instPlayerPagoda.getNum() - 1);
		}
		
		
		//只有是普通层才更新爬塔实例数据
		if(type == 0){
			if(instPlayerPagoda.getPagodaStoreyId() > instPlayerPagoda.getMaxPagodaStoreyId() && victory == 1){
				instPlayerPagoda.setMaxPagodaStoreyId(pagodaStoreyId);
			}
			
			getInstPlayerPagodaDAL().update(instPlayerPagoda, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPagoda, instPlayerPagoda.getId(), instPlayerPagoda.getResult(), syncMsgData);
			
			//更新成就
			AchievementUtil.updateAchievement (instPlayerId, StaticAchievementType.pagoda, instPlayerPagoda.getMaxPagodaStoreyId(), syncMsgData, null);
		}
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.tower, 1, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("1", victory);
		if(type == 1 && victory == 1){
			retMsgData.putStringItem("2", thinglist.substring(0,thinglist.length() - 1));
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 爬塔——重置
	 * @author hzw
	 * @date 2014-8-12下午4:57:26
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void reset(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int instPlayerPagodaId = (Integer)msgMap.get("instPlayerPagodaId");
		InstPlayerPagoda instPlayerPagoda = getInstPlayerPagodaDAL().getModel(instPlayerPagodaId, instPlayerId);
		
		//是否达到开放等级
		DictFunctionOpen dictFunctionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.tower + "");
		if (instPlayer.getLevel() < dictFunctionOpen.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		//验证玩家是否一致
		String currTime = DateUtil.getCurrTime();
		if (instPlayerPagoda.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
//		后将SearchNum的计算方法由之前的减法改成加法了 , time:2015-04-19, member:miao and hu, source: 孙毅完善vip功能
//		if(instPlayerPagoda.getResetNum() <= 0 && DateUtil.isSameDay(instPlayerPagoda.getOperTime(), currTime, DateFormat.YMDHMS)){
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notResetNum);
//			return;
//		}
		
		if(!DateUtil.isSameDay(instPlayerPagoda.getOperTime(), currTime, DateFormat.YMDHMS)){
			instPlayerPagoda.setSearchNum(0);
			instPlayerPagoda.setResetNum(0);
		}
		
		//验证vip次数
		DictVIP dictVIP = DictMap.dictVIPMap.get((instPlayer.getVipLevel() + 1) + "");
		if (dictVIP != null) {
			if (instPlayerPagoda.getResetNum() >= dictVIP.getPagodaResetNum()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notResetNum);
				return;
			}
		}
		
		int resetNum = instPlayerPagoda.getResetNum() - 2;
		int gold = 0;
		if(resetNum >= 0){
			switch (resetNum) {
			case 0:
				gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.pagodaResetOne);
				break;
			case 1:
				gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.pagodaResetTwo);
				break;
			case 2:
				gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.pagodaResetThree);
				break;
			}
			if(gold > 0){
				
				//验证元宝是否足够
				if (instPlayer.getGold() < gold) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
					return;
				}
				
//				instPlayer.setGold(instPlayer.getGold() - gold);
				PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
				getInstPlayerDAL().update(instPlayer, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
		}
		
		
		instPlayerPagoda.setNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.instPlayerPagodaNum));
		instPlayerPagoda.setPagodaStoreyId(DictMapUtil.getSysConfigIntValue(StaticSysConfig.pagodaStoreyId));
		instPlayerPagoda.setResetNum(instPlayerPagoda.getResetNum() + 1);
		instPlayerPagoda.setOperTime(currTime);
		getInstPlayerPagodaDAL().update(instPlayerPagoda, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPagoda, instPlayerPagoda.getId(), instPlayerPagoda.getResult(), syncMsgData);
		
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 爬塔——扫荡
	 * @author hzw
	 * @date 2014-8-13上午11:10:03
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void mop(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//是否达到开放等级
		DictFunctionOpen dictFunctionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.tower + "");
		if (instPlayer.getLevel() < dictFunctionOpen.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		int instPlayerPagodaId = (Integer)msgMap.get("instPlayerPagodaId");
		InstPlayerPagoda instPlayerPagoda = getInstPlayerPagodaDAL().getModel(instPlayerPagodaId, instPlayerId);
		//验证玩家是否一致
		if (instPlayerPagoda.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		/*if (instPlayerPagoda.getMaxPagodaStoreyId() < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.mopNum)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mopNum);
			return;
		}*/
		if (instPlayerPagoda.getPagodaStoreyId() >= instPlayerPagoda.getMaxPagodaStoreyId()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notMopNum);
			return;
		}
		int num = instPlayerPagoda.getMaxPagodaStoreyId() - instPlayerPagoda.getPagodaStoreyId() + 1;
		
		//处理扫塔奖励
		Map<String, Integer> thingMap = new HashMap<String, Integer>();  //返还的物品Map
		for(int i = 0; i < num; i++){
			DictPagodaStorey dictPagodaStorey = DictMap.dictPagodaStoreyMap.get((instPlayerPagoda.getPagodaStoreyId() + i) + "");
			DictPagodaFormation dictPagodaFormation = DictMap.dictPagodaFormationMap.get(dictPagodaStorey.getPagodaFormationId() + "");
			int pagodaStoreyId = dictPagodaStorey.getId();
			//获取胜利奖励
			if(thingMap.containsKey(3+"_"+2)){
				thingMap.put(3+"_"+2, thingMap.get(3+"_"+2) + dictPagodaStorey.getCopper());
			}
			else{
				thingMap.put(3+"_"+2, dictPagodaStorey.getCopper());
			}
			if(thingMap.containsKey(3+"_"+4)){
				thingMap.put(3+"_"+4, thingMap.get(3+"_"+4) + dictPagodaStorey.getCulture());
			}
			else{
				thingMap.put(3+"_"+4, dictPagodaStorey.getCulture());
			}
			
			if(dictPagodaFormation.getPagodaStorey5() == pagodaStoreyId){
				String things[] = dictPagodaFormation.getReward().split(";");
				for(String thing : things){
					int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
					int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
					int value = ConvertUtil.toInt(thing.split("_")[2]);
					if(thingMap.containsKey(tableTypeId+"_"+tableFieldId)){
						thingMap.put(tableTypeId+"_"+tableFieldId, thingMap.get(tableTypeId+"_"+tableFieldId) + value);
					}
					else{
						thingMap.put(tableTypeId+"_"+tableFieldId, value);
					}
				}
			}
		}
		
		//添加领奖中心实例数据
		String description = "恭喜您在天焚炼气塔扫荡中或得如下奖励：";
		String things = "";
		for(Entry<String, Integer> str : thingMap.entrySet()){
			things += str.getKey() + "_" + str.getValue() + ";";
		}
		if(things.length() > 0){
			things = things.substring(0,things.length() - 1);
		}
		ActivityUtil.addInstPlayerAward(instPlayerId, 1, things, DateUtil.getCurrTime(), description, syncMsgData);
		
		//更新玩家爬塔实例数据
		instPlayerPagoda.setNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.instPlayerPagodaNum));
		instPlayerPagoda.setPagodaStoreyId(instPlayerPagoda.getPagodaStoreyId() + num);
		getInstPlayerPagodaDAL().update(instPlayerPagoda, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPagoda, instPlayerPagoda.getId(), instPlayerPagoda.getResult(), syncMsgData);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.tower, 1, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 爬塔——搜寻
	 * @author hzw
	 * @date 2014-8-13下午2:59:37
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void search(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//是否达到开放等级
		DictFunctionOpen dictFunctionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.tower + "");
		if (instPlayer.getLevel() < dictFunctionOpen.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		int instPlayerPagodaId = (Integer)msgMap.get("instPlayerPagodaId");
		InstPlayerPagoda instPlayerPagoda = getInstPlayerPagodaDAL().getModel(instPlayerPagodaId, instPlayerId);
		//验证玩家是否一致
		String currTime = DateUtil.getCurrTime();
		if (instPlayerPagoda.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
//		后将SearchNum的计算方法由之前的减法改成加法了 , time:2015-04-19, member:miao and hu, source: 孙毅完善vip功能
//		if(instPlayerPagoda.getSearchNum() <= 0 && DateUtil.isSameDay(instPlayerPagoda.getOperTime(), currTime, DateFormat.YMDHMS)){
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notSearchNum);
//			return;
//		}
		
		if(!DateUtil.isSameDay(instPlayerPagoda.getOperTime(), currTime, DateFormat.YMDHMS)){
			instPlayerPagoda.setSearchNum(0);
			instPlayerPagoda.setResetNum(0);
		}
		
		//验证vip次数
		DictVIP dictVIP = DictMap.dictVIPMap.get((instPlayer.getVipLevel() + 1) + "");
		if (dictVIP != null) {
			if (instPlayerPagoda.getSearchNum() >= dictVIP.getPagodaSearchNum()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notSearchNum);
				return;
			}
		}
		
		//更新玩家爬塔实例数据
		instPlayerPagoda.setSearchNum(instPlayerPagoda.getSearchNum() + 1);
		instPlayerPagoda.setOperTime(currTime);
		getInstPlayerPagodaDAL().update(instPlayerPagoda, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPagoda, instPlayerPagoda.getId(), instPlayerPagoda.getResult(), syncMsgData);
		
		int tag = 0; //默认搜寻失败
		Float random = RandomUtil.getRandomFloat();
		if(random <= DictMap.dictSysConfigMap.get(StaticSysConfig.searchChance+ "").getValue()){
			tag = 1;
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("1", tag);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 爬塔———商店
	 * @author hzw
	 * @date 2015-7-11下午3:48:46
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void store(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int instPlayerPagodaId = (Integer)msgMap.get("instPlayerPagodaId");
		int dictPagodaStoreId = (Integer)msgMap.get("dictPagodaStoreId");
		int num = (Integer)msgMap.get("num");
		
		//是否达到开放等级
		DictFunctionOpen dictFunctionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.tower + "");
		if (instPlayer.getLevel() < dictFunctionOpen.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		//验证火能石是否小于等于0
		if (instPlayer.getCulture() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCultureNum);
			return;
		}
		
		//防止传来的参数为负数或0
		if (num <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		DictPagodaStore dictPagodaStore = DictMap.dictPagodaStoreMap.get(dictPagodaStoreId + "");
		InstPlayerPagoda instPlayerPagoda = getInstPlayerPagodaDAL().getModel(instPlayerPagodaId, instPlayerId);
		//验证玩家是否一致
		if (instPlayerPagoda.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		int type = dictPagodaStore.getType();
		if(type != 3){
			
			// 验证上限-防止整数溢出
			int buyUpLimit = DictMapUtil.getSysConfigIntValue(StaticSysConfig.buyUpLimit);
			if (num > buyUpLimit) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyUpLimit + " " + buyUpLimit);
				return;
			}
			
			if(type == 2 && instPlayerPagoda.getMaxPagodaStoreyId() < dictPagodaStore.getPagodaStoreyId()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPagodaStore);
				return;
			}
			if (instPlayer.getCulture() < (dictPagodaStore.getCulture() * num)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCultureNum);
				return;
			}
		}else{
			
			//当type=3的时候,num如果有值，只能是1
			if (num != 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_upBuyNum);
				return;
			}
			if (instPlayerPagoda.getMaxPagodaStoreyId() < dictPagodaStore.getPagodaStoreyId()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPagodaStore);
				return;
			}
			if (StringUtil.contains(instPlayerPagoda.getPagodaStores(), dictPagodaStoreId + "", ";")) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_openServiceBag);
				return;
			}
			
			if (instPlayer.getCulture() < (dictPagodaStore.getCulture() * num)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCultureNum);
				return;
			}
		}
		
		int consumNum =  dictPagodaStore.getCulture() * num;
	
		instPlayer.setCulture(instPlayer.getCulture() - consumNum);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		if(type == 3){
			//更新玩家爬塔实例数据
			if(instPlayerPagoda.getPagodaStores() == null || instPlayerPagoda.getPagodaStores().equals("")){
				instPlayerPagoda.setPagodaStores(dictPagodaStoreId + "");
			}else{
				instPlayerPagoda.setPagodaStores(instPlayerPagoda.getPagodaStores() + ";" + dictPagodaStoreId);
			}
			getInstPlayerPagodaDAL().update(instPlayerPagoda, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPagoda, instPlayerPagoda.getId(), instPlayerPagoda.getResult(), syncMsgData);
		}
		
		//给物品
		ThingUtil.groupThing(instPlayer, dictPagodaStore.getTableTypeId(), dictPagodaStore.getTableFieldId(), (dictPagodaStore.getValue() * num), syncMsgData, msgMap);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 进入丹塔
	 * @author mp
	 * @date 2015-10-27 下午5:06:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void intoPagoda (Map<String, Object> msgMap, String channelId) throws Exception{
		
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int haveBuy = 0; 
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.strongEquip+"'", 0);
		if (instPlayerBigTableList.size() > 0) {
			haveBuy = 1;
		}
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("1", haveBuy);//0-未购买  1-已购买
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 下发强力装备
	 * @author mp
	 * @date 2015-10-27 下午5:07:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sendStrongEquip (Map<String, Object> msgMap, String channelId) throws Exception{
		
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		DictStrogerEquip strogerEquip = DictList.dictStrogerEquipList.get(0);
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("id", strogerEquip.getId());
		retMsgData.putStringItem("things", strogerEquip.getThings());
		retMsgData.putIntItem("needGold", strogerEquip.getNeedGold());
		retMsgData.putStringItem("donateThings", strogerEquip.getDonateThings());
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 点击强力装备
	 * @author mp
	 * @date 2015-10-27 下午5:07:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void clickStrongEquip (Map<String, Object> msgMap, String channelId) throws Exception{
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		//记录日志
		String log = "点击强力装备: instPlayerId=" + instPlayerId;
		LogUtil.info(log);
	}
	
	/**
	 * 购买强力装备
	 * @author mp
	 * @date 2015-10-27 下午5:08:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buyStrongEquip (Map<String, Object> msgMap, String channelId) throws Exception{
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int strongerEquipId = (Integer)msgMap.get("strongerEquipId");//购买的强力装备Id
		DictStrogerEquip strogerEquip = DictMap.dictStrogerEquipMap.get(strongerEquipId + "");
		
		//验证是否已购买
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.strongEquip+"'", 0);
		if (instPlayerBigTableList.size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_haveBuy);
			return;
		}
		
		//验证元宝是否充足
		if (instPlayer.getGold() < strogerEquip.getNeedGold()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		
		//给物品
		MessageData syncMsgData = new MessageData();
		Map<String, String> thingMap = new HashMap<String, String>();
		for (String thing : strogerEquip.getThings().split(";")) {
			int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
			int value = ConvertUtil.toInt(thing.split("_")[2]);
			ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);
		}
		
		//给额外赠送的物品
		for (String thing : strogerEquip.getDonateThings().split(";")) {
			int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
			int value = ConvertUtil.toInt(thing.split("_")[2]);
			ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);
		}
		ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);
		
		//扣除元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, strogerEquip.getNeedGold(), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//添加购买记录
		InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
		instPlayerBigTable.setInstPlayerId(instPlayerId);
		instPlayerBigTable.setProperties(StaticBigTable.strongEquip);
		getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
		
		//添加购买日志
		String log = "购买强力装备: instPlayerId=" + instPlayerId + " 花费元宝=" + strogerEquip.getNeedGold() + " 获得物品=" + strogerEquip.getThings() + " 额外赠送物品=" + strogerEquip.getDonateThings();
		LogUtil.info(log);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
}
