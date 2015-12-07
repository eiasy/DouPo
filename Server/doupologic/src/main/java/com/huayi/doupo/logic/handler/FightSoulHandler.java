package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictFightSoul;
import com.huayi.doupo.base.model.DictFightSoulHuntRule;
import com.huayi.doupo.base.model.DictFightSoulPositionCond;
import com.huayi.doupo.base.model.DictFightSoulUpgradeExp;
import com.huayi.doupo.base.model.DictFunctionOpen;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBarrier;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerFightSoul;
import com.huayi.doupo.base.model.InstPlayerFightSoulHuntRule;
import com.huayi.doupo.base.model.InstPlayerStore;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticActivityHoliday;
import com.huayi.doupo.base.model.statics.StaticBagType;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticFightSoulQuality;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.FightSoulUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 斗魂处理类
 * 银魂,经验斗魂两个都不能被锁定，上阵，升级
 * @author mp
 * @date 2015-10-17 上午10:31:02
 */
public class FightSoulHandler extends BaseHandler{
	
	/**
	 * 猎魂
	 * @author mp
	 * @date 2015-10-17 上午10:31:40
	 * @param msgMap
	 *  1-猎一次  2-猎十次
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void huntFightSoul(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int fightSouleHuntRuleId = (Integer)msgMap.get("fightSouleHuntRuleId");//猎魂位置Id
//		int guideStep = 0;//引导步骤 值=1表示第一次引导； 值=2表示第二次引导；  值=0表示非引导
		int guideStep = (Integer)msgMap.get("guideStep");//引导步骤 值=1表示第一次引导； 值=2表示第二次引导；  值=0表示非引导
		DictFightSoulHuntRule fightSoulHuntRule = DictMap.dictFightSoulHuntRuleMap.get(fightSouleHuntRuleId + "");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//验证引导
		if (guideStep < 0 || guideStep > 2) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证开启条件
		DictFunctionOpen functionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.fight + "");
		List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + functionOpen.getLevel(), instPlayerId);
		if (instPlayerBarrierList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_functionNoOpen);
			return;
		}
		
		//验证此位置有效范围
		if (fightSouleHuntRuleId < 1 || fightSouleHuntRuleId > DictList.dictFightSoulHuntRuleList.size()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证背包是否达到最大(包含锁定的和上阵的)
		List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if (instPlayerFightSoulList.size() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.fightSoulBagUpLimit)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_upBagUpLimit);
			return;
		}
		
		//验证银币是否足够
		if (guideStep == 0) {
			if (ConvertUtil.toInt(instPlayer.getCopper()) < fightSoulHuntRule.getNeedSilver()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
				return;
			}
		}
		
		//不等于1时,验证此位置点亮状态
		if (fightSouleHuntRuleId != 1) {
			List<InstPlayerFightSoulHuntRule> instPlayerFightSoulHuntRuleList = getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId + " and fightSouleHuntRuleId = " + fightSouleHuntRuleId, instPlayerId); 
			if (instPlayerFightSoulHuntRuleList.size() <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noLight);
				return;
			}
		}
		
		//处理点击后的移动位置
		FightSoulUtil.huntMovePosition(fightSouleHuntRuleId, fightSoulHuntRule, instPlayerId, syncMsgData, 1, guideStep);
		
		//给相应斗魂
		DictFightSoul dictFightSoul = FightSoulUtil.huntFightSoul(fightSoulHuntRule, instPlayer, syncMsgData, guideStep);
		
		//扣除银币
		if (guideStep == 0) {
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - fightSoulHuntRule.getNeedSilver()) + "");
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("1", dictFightSoul.getId());//猎取到的斗魂字典id
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 猎10次
	 * @author mp
	 * @date 2015-10-19 下午1:53:42
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void huntTenTimes(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//规则：始终默认点的是最后一个点亮的猎魂位置
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//验证开启条件
		DictFunctionOpen functionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.fight + "");
		List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + functionOpen.getLevel(), instPlayerId);
		if (instPlayerBarrierList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_functionNoOpen);
			return;
		}
		
		//验证背包是否达到最大(包含锁定的和上阵的)
		List<InstPlayerFightSoul > instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if (instPlayerFightSoulList.size() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.fightSoulBagUpLimit)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_upBagUpLimit);
			return;
		}
		
		//验证是否达到32级
		if (instPlayer.getLevel() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.huntTenLevel)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		List<InstPlayerFightSoulHuntRule> oldInstPlayerFightSoulHuntRuleList = getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		
		//循环猎魂10次[里面含背包容量, 银币的判断]
		int huntTimes = 0;
		int consumSilverSum = 0;
		
		int calcSilver = ConvertUtil.toInt(instPlayer.getCopper());
		
		String fightSoulIds = "";
		
		for (int i = 0; i < 10; i++) {
			
			//取出当前最后一个点亮的猎魂位置
			int fightSouleHuntRuleId = FightSoulUtil.getTheLastHuntRuleId(instPlayerId);
			DictFightSoulHuntRule fightSoulHuntRule = DictMap.dictFightSoulHuntRuleMap.get(fightSouleHuntRuleId + "");
			
			//验证银币是否足够
			if (calcSilver < fightSoulHuntRule.getNeedSilver()) {
				break;
			} else {
				calcSilver = calcSilver - fightSoulHuntRule.getNeedSilver();
			}
			
			//得到并处理点击后的移动位置
			FightSoulUtil.huntMovePosition(fightSouleHuntRuleId, fightSoulHuntRule, instPlayerId, syncMsgData, 2, 0);
			
			//得到并处理当前位置下获得的猎魂
			DictFightSoul dictFightSoul = FightSoulUtil.huntFightSoul(fightSoulHuntRule, instPlayer, syncMsgData, 0);
			fightSoulIds += dictFightSoul.getId() + ";";
			
			consumSilverSum += fightSoulHuntRule.getNeedSilver();
			huntTimes ++;
		}
		
		if (huntTimes == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		} else {
			//扣除银币
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - consumSilverSum) + "");
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		
		//组织InstPlayerFightSoulHuntRule同步信息
		List<InstPlayerFightSoulHuntRule> newInstPlayerFightSoulHuntRuleList = getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		
		for (InstPlayerFightSoulHuntRule newObj : newInstPlayerFightSoulHuntRuleList) {
			int isContain = 0;//新的id在旧的列表里面没有
			for (InstPlayerFightSoulHuntRule oldObj : oldInstPlayerFightSoulHuntRuleList) {
				if (oldObj.getId() == newObj.getId()) {
					isContain = 1;//新的id在旧的列表里面有
				}
			}
			if (isContain == 0) {//新的id在旧的列表里面没有,add
				newObj.setId(newObj.getId());
				newObj.setInstPlayerId(newObj.getInstPlayerId());
				newObj.setFightSouleHuntRuleId(newObj.getFightSouleHuntRuleId());
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, newObj, newObj.getId(), newObj.getResult(), syncMsgData);
			}
		}
		
		for (InstPlayerFightSoulHuntRule oldObj : oldInstPlayerFightSoulHuntRuleList) {
			int isContain = 0;//旧的id在新的列表里面没有 del
			for (InstPlayerFightSoulHuntRule newObj : newInstPlayerFightSoulHuntRuleList) {
				if (newObj.getId() == oldObj.getId()) {
					isContain = 1;//旧的id在新的列表里面有
				}
			}
			if (isContain == 0) {//旧的id在新的列表里面没有,del
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, oldObj, oldObj.getId(), "", syncMsgData);
			}
		}
		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("1", huntTimes);//由于银币不足,本次共猎魂n次
		retMsgData.putStringItem("2", StringUtil.noContainLastString(fightSoulIds));//猎取到的斗魂字典id列表,用分号分开的
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 点亮
	 * @author mp
	 * @date 2015-10-17 下午2:51:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fightSoulLight(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int fightSouleHuntRuleId = (Integer)msgMap.get("fightSouleHuntRuleId");//猎魂位置Id
		DictFightSoulHuntRule fightSoulHuntRule = DictMap.dictFightSoulHuntRuleMap.get(fightSouleHuntRuleId + "");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//验证此位置有效范围
		if (fightSouleHuntRuleId < 1 || fightSouleHuntRuleId > DictList.dictFightSoulHuntRuleList.size()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证当前位置是否为可点亮
		if (fightSoulHuntRule.getNeedGold() == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noCanLight);
			return;
		}
		
		//验证当前是否已经点亮
		if (getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId + " and fightSouleHuntRuleId = " + fightSouleHuntRuleId , instPlayerId).size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_haveLight);
			return;
		}
		
		//验证元宝是否足够
		if (instPlayer.getGold() < fightSoulHuntRule.getNeedGold()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		
		//扣除元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, fightSoulHuntRule.getNeedGold(), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//点亮
		FightSoulUtil.addInstPlayerFightSoulHuntRule(instPlayerId, fightSouleHuntRuleId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 一键出售斗魂
	 * @author mp
	 * @date 2015-10-17 下午3:11:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void oneKeySell(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//验证是否有可卖斗魂(这里可卖的只有白色斗魂)
		List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and fightSoulQualityId = " + StaticFightSoulQuality.wu + " and lockState = 0 and instCardId = 0 and position = 0", instPlayerId);
		if (instPlayerFightSoulList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noSell);
			return;
		}
		
		//删除斗魂并计算获得的银币
		int sellSilver = 0;
		for (InstPlayerFightSoul instPlayerFightSoul : instPlayerFightSoulList) {
			int fightSoulId = instPlayerFightSoul.getFightSoulId();
			int level = instPlayerFightSoul.getLevel();
			sellSilver += FightSoulUtil.getSilverByLevel(fightSoulId, level);
			getInstPlayerFightSoulDAL().deleteById(instPlayerFightSoul.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerFightSoul, instPlayerFightSoul.getId(), "", syncMsgData);
		}
		
		//获取银币
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + sellSilver) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 出售斗魂
	 * @author mp
	 * @date 2015-10-17 下午3:52:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sellFightSoul(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
	
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		String fightSouleList = (String)msgMap.get("fightSouleList");//出售的斗魂列表
		
		//验证是否超过最大数量（规定最大不超过50个）
		if (fightSouleList.split(";").length > DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxSellFightSoulNum)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyUpLimit);
			return;
		}
		
		//是否选择斗魂
		if (fightSouleList.split(";").length <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noSelectedFightSoul);
			return;
		}
		
		//这里的斗魂只要是未锁定的,不在阵的都可以卖
		int sellSilver = 0;
		for (String playerFightSoulId : fightSouleList.split(";")) {
			int instPlayerFightSoulId = ConvertUtil.toInt(playerFightSoulId);
			InstPlayerFightSoul instPlayerFightSoul = getInstPlayerFightSoulDAL().getModel(instPlayerFightSoulId, instPlayerId);
			if (instPlayerFightSoul == null) {
				continue;
			}
			if (instPlayerFightSoul.getLockState() != 0) {//锁定状态不可卖 0-未锁定 1-锁定
				continue;
			}
			if (instPlayerFightSoul.getInstCardId() != 0) {//在阵的不可卖
				continue;
			}
			
			sellSilver += FightSoulUtil.getSilverByLevel(instPlayerFightSoul.getFightSoulId(), instPlayerFightSoul.getLevel());
			
			getInstPlayerFightSoulDAL().deleteById(instPlayerFightSoul.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerFightSoul, instPlayerFightSoul.getId(), "", syncMsgData);
		}
		
		//获取银币
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + sellSilver) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 锁定斗魂
	 * @author mp
	 * @date 2015-10-19 下午4:44:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lockFightSoul(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instPlayerFightSoulId = (Integer)msgMap.get("instPlayerFightSoulId");//要锁定的斗魂实例Id
		InstPlayerFightSoul instPlayerFightSoul = getInstPlayerFightSoulDAL().getModel(instPlayerFightSoulId, instPlayerId);
		
		//验证是否有此斗魂
		if (instPlayerFightSoul == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noHaveFightSoul);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerFightSoul.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(instPlayerFightSoul.getFightSoulId() + "");
		
		//经验斗魂不可被锁定
		if (fightSoul.getIsExpFightSoul() == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_expFightSoulNoLock);
			return;
		}
		
		//银魂不可被锁定
		if (fightSoul.getFightSoulQualityId() == StaticFightSoulQuality.wu) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_expFightSoulNoLock);
			return;
		}
		
		//是否已被锁定  0-未锁定 1-锁定
		if (instPlayerFightSoul.getLockState() == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_fightSoulHaveLock);
			return;
		}
		
		//锁定斗魂
		instPlayerFightSoul.setLockState(1);
		getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 解锁斗魂
	 * @author mp
	 * @date 2015-10-19 下午4:44:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void unLockFightSoul(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instPlayerFightSoulId = (Integer)msgMap.get("instPlayerFightSoulId");//要解锁的斗魂实例Id
		InstPlayerFightSoul instPlayerFightSoul = getInstPlayerFightSoulDAL().getModel(instPlayerFightSoulId, instPlayerId);
		
		//验证是否有此斗魂
		if (instPlayerFightSoul == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noHaveFightSoul);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerFightSoul.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//是否未被锁定  0-未锁定 1-锁定
		if (instPlayerFightSoul.getLockState() == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_fightSoulHaveLock);
			return;
		}
		
		//锁定斗魂
		instPlayerFightSoul.setLockState(0);
		getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 附魂
	 * @author mp
	 * @date 2015-10-19 下午4:44:59
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void stickFightSoul(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instPlayerFightSoulId = (Integer)msgMap.get("instPlayerFightSoulId");//要附着的斗魂实例Id
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");//要附着的卡牌实例Id
		int position = (Integer)msgMap.get("position");//位置
		
		InstPlayerFightSoul instPlayerFightSoul = getInstPlayerFightSoulDAL().getModel(instPlayerFightSoulId, instPlayerId);
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		
		//验证是否有此斗魂
		if (instPlayerFightSoul == null || instPlayerCard == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerFightSoul.getInstPlayerId() != instPlayerId || instPlayerCard.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(instPlayerFightSoul.getFightSoulId() + "");
		
		//经验斗魂不可被附魂
		if (fightSoul.getIsExpFightSoul() == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_expFightSoulNoStick);
			return;
		}
		
		//银魂不可被附魂
		if (fightSoul.getFightSoulQualityId() == StaticFightSoulQuality.wu) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_silverFightSoulNoStick);
			return;
		}
		
		//验证位置合理性
		if (position < 1 || position > DictList.dictFightSoulPositionCondList.size()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证属性是否冲突
		List<String> fightPropTypeList = new ArrayList<>();
		List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
		for (InstPlayerFightSoul obj : instPlayerFightSoulList) {
			if (obj.getPosition() != position) {
				DictFightSoul fightSoulObj = DictMap.dictFightSoulMap.get(obj.getFightSoulId() + "");
				String fightPropType = FightSoulUtil.getFightTypeByFightSoulId(fightSoulObj.getId());
				fightPropTypeList.add(fightPropType);
			}
		}
		String upFormatFightPropType = FightSoulUtil.getFightTypeByFightSoulId(fightSoul.getId());
		if (fightPropTypeList.contains(upFormatFightPropType)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noUpFightPropCommon);
			return;
		}
		
		
		//验证此位置是否已有斗魂附着
//		if (instPlayerFightSoul.getPosition() != 0 || instPlayerFightSoul.getInstCardId() != 0) {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_fightSoulHaveStick);
//			return;
//		}
//		if (getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId + " and position = " + position, instPlayerId).size() > 0) {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_fightSoulHaveStick);
//			return;
//		}
		
		//不用判断这个斗魂是否在别人身上,可以理解成直接把别人的斗魂附着到这个人的身上,只需要判断当前位置是否有斗魂,如果有,先卸下来
		List<InstPlayerFightSoul> isHaveFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId + " and position = " + position, instPlayerId);
		if (isHaveFightSoulList.size() > 0) {
			
			if (isHaveFightSoulList.get(0).getId() == instPlayerFightSoulId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noExchange);
				return;
			}
			
			
			InstPlayerFightSoul haveInstPlayerFightSoul = isHaveFightSoulList.get(0);
			haveInstPlayerFightSoul.setInstCardId(0);
			haveInstPlayerFightSoul.setPosition(0);
			getInstPlayerFightSoulDAL().update(haveInstPlayerFightSoul, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, haveInstPlayerFightSoul, haveInstPlayerFightSoul.getId(), haveInstPlayerFightSoul.getResult(), syncMsgData);
		}
		
		//验证此位置是否已开启
		DictFightSoulPositionCond fightSoulPositionCond = null;
		for (DictFightSoulPositionCond obj : DictList.dictFightSoulPositionCondList) {
			if (obj.getPosition() == position) {
				fightSoulPositionCond = obj;
				break;
			}
		}
		if (DictMap.dictTitleDetailMap.get(instPlayerCard.getTitleDetailId() + "").getTitleId() < fightSoulPositionCond.getOpenTitleId()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_positionNoOpen);
			return;
		}
		
		//附魂
		instPlayerFightSoul.setInstCardId(instPlayerCardId);
		instPlayerFightSoul.setPosition(position);
		getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 将斗魂从卡牌身上卸下
	 * @author mp
	 * @date 2015-10-19 下午5:59:09
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dropFightSoul(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");//要卸下的卡牌实例Id
		int position = (Integer)msgMap.get("position");//位置
		
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		
		//验证是否有此卡牌
		if (instPlayerCard == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerCard.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证位置合理性
		if (position < 1 || position > DictList.dictFightSoulPositionCondList.size()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证此卡牌此位置上有斗魂
		List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId + " and position = " + position, instPlayerId);
		if (instPlayerFightSoulList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_posNoHaveFightSoul);
			return;
		}
		
		//卸下斗魂
		InstPlayerFightSoul instPlayerFightSoul = instPlayerFightSoulList.get(0);
		instPlayerFightSoul.setInstCardId(0);
		instPlayerFightSoul.setPosition(0);
		getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 斗魂吞噬（升级）
	 * @author mp
	 * @date 2015-10-20 上午10:47:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fightSoulUpgrade (Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instPlayerFightSoulId = (Integer)msgMap.get("instPlayerFightSoulId");//要升级的斗魂id
		String instPlayerFightSoulIdList = (String)msgMap.get("instPlayerFightSoulIdList");//被吞噬的斗魂列表,用；分开
		
		if (instPlayerFightSoulIdList == null || instPlayerFightSoulIdList.equals("")) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证要升级的斗魂是否存在
		InstPlayerFightSoul instPlayerFightSoul = getInstPlayerFightSoulDAL().getModel(instPlayerFightSoulId, 0);
		if (instPlayerFightSoul == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noHaveFightSoul);
			return;
		}
		
		//验证是否达到最高等级
		List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList1 = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(instPlayerFightSoul.getFightSoulQualityId());
		if (instPlayerFightSoul.getLevel() >= fightSoulUpgradeExpList1.get(fightSoulUpgradeExpList1.size() - 1).getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_upLevel);
			return;
		}
		
		//验证要升级的斗魂是否为经验,白色斗魂
		DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(instPlayerFightSoul.getFightSoulId() + "");
		
		//经验斗魂不可升级
		if (fightSoul.getIsExpFightSoul() == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_expFightSoulNoUpgrade);
			return;
		}
		
		//银魂不可升级
		if (fightSoul.getFightSoulQualityId() == StaticFightSoulQuality.wu) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_silverFightSoulNoUpgrade);
			return;
		}
		
		//验证是否超过最大数量（规定最大不超过50个）
		if (instPlayerFightSoulIdList.split(";").length > DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxSellFightSoulNum)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyUpLimit);
			return;
		}
		
		//是否选择斗魂
		if (instPlayerFightSoulIdList.split(";").length <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noSelectedFightSoul);
			return;
		}
		
		int saveExp = 0;
		int currExp = 0;
		if (instPlayerFightSoul.getLevel() <= 1) {
			currExp = instPlayerFightSoul.getExp();
		} else {
			//计算当前等级能得到的累计经验
			currExp = FightSoulUtil.getCurrLevelExp(instPlayerFightSoul.getFightSoulQualityId(), instPlayerFightSoul.getLevel()) + instPlayerFightSoul.getExp();
		}
		saveExp = saveExp + currExp;
		
		int upLevelExp = FightSoulUtil.upLevelExp(fightSoul.getFightSoulQualityId());
		//被吞噬的斗魂不处理不存在的,白色斗魂
		for (String id : instPlayerFightSoulIdList.split(";")) {
			int instPlayerFightSoulObjId = ConvertUtil.toInt(id);
			InstPlayerFightSoul instPlayerFightSoulObj = getInstPlayerFightSoulDAL().getModel(instPlayerFightSoulObjId, 0);
			
			//是否存在
			if (instPlayerFightSoulObj == null) {
				continue;
			}
			
			//玩家是否一致
			if (instPlayerFightSoulObj.getInstPlayerId() != instPlayerId) {
				continue;
			}
			
			//是否为银魂
			DictFightSoul fightSoulObj = DictMap.dictFightSoulMap.get(instPlayerFightSoulObj.getFightSoulId() + "");
			if (fightSoulObj.getFightSoulQualityId() == StaticFightSoulQuality.wu) {
				continue;
			}
			
			//是否为锁定状态
			if (instPlayerFightSoulObj.getLockState() == 1) {
				continue;
			}
			
			//是否为上阵状态
			if (instPlayerFightSoulObj.getInstCardId() != 0 || instPlayerFightSoulObj.getPosition() != 0) {
				continue;
			}
			
			//删除此斗魂
			getInstPlayerFightSoulDAL().deleteById(instPlayerFightSoulObjId, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerFightSoulObj, instPlayerFightSoulObj.getId(), "", syncMsgData);
			
			int otherFightSoulExp = 0;
			if (instPlayerFightSoulObj.getLevel() <= 1) {
				otherFightSoulExp = DictMap.dictFightSoulMap.get(instPlayerFightSoulObj.getFightSoulId() + "").getInitExp() + instPlayerFightSoulObj.getExp();
			} else {
				otherFightSoulExp = DictMap.dictFightSoulMap.get(instPlayerFightSoulObj.getFightSoulId() + "").getInitExp() + FightSoulUtil.getCurrLevelExp(instPlayerFightSoulObj.getFightSoulQualityId(), instPlayerFightSoulObj.getLevel()) + instPlayerFightSoulObj.getExp();
			}
			saveExp += otherFightSoulExp;
			
			if (saveExp >= upLevelExp) {
				break;
			}
		}
		
//		System.out.println("saveExp = " + saveExp);
		
		//由经验算出等级和剩余经验
		if (saveExp >= upLevelExp) {
			List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(fightSoul.getFightSoulQualityId());
			instPlayerFightSoul.setLevel(fightSoulUpgradeExpList.get(fightSoulUpgradeExpList.size() - 1).getLevel());
			instPlayerFightSoul.setExp(0);
//			System.out.println("level = " + fightSoulUpgradeExpList.get(fightSoulUpgradeExpList.size() - 1).getLevel());
		} else {
			int[] levelAndRemainExp = FightSoulUtil.getLevelAndRemainExpByExp(saveExp, instPlayerFightSoul.getFightSoulQualityId());
			instPlayerFightSoul.setLevel(levelAndRemainExp[0]);
			instPlayerFightSoul.setExp(levelAndRemainExp[1]);
//			System.out.println("level = " + levelAndRemainExp[0]);
		}
		getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 斗魂一键吞噬（升级）
	 * @author mp
	 * @date 2015-10-20 上午10:47:46
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fightSoulOneKeyUpgrade (Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//验证是否有可升级的斗魂[不能为上阵，不能为银魂，不能为经验斗魂，不能为天阶斗魂，不能是满级（但可以为锁定斗魂） ]
		List<InstPlayerFightSoul> newInstPlayerFightSoulList = new ArrayList<>();
		List<InstPlayerFightSoul> verfPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = 0 and position = 0 and fightSoulQualityId != " + StaticFightSoulQuality.wu, instPlayerId);
		for (InstPlayerFightSoul obj : verfPlayerFightSoulList) {
			int fightSoulId = obj.getFightSoulId();
			DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(fightSoulId + "");
			List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList1 = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(fightSoul.getFightSoulQualityId());
			if (fightSoul.getIsExpFightSoul() == 0 && fightSoul.getFightSoulQualityId() != StaticFightSoulQuality.tian && obj.getLevel() < fightSoulUpgradeExpList1.get(fightSoulUpgradeExpList1.size() - 1).getLevel()) {
				newInstPlayerFightSoulList.add(obj);
			}
		}
		if (newInstPlayerFightSoulList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noCanUpgradeFightSoul);
			return;
		}
		
		//找到品质最高（品质id最小）且id最小的当做要升级的斗魂
		List<InstPlayerFightSoul> minQualityIdInstPlayerFightSoulList = new ArrayList<>();
		int qualityId = 10;
		for (InstPlayerFightSoul obj : newInstPlayerFightSoulList) {
			if (obj.getFightSoulQualityId() < qualityId) {
				qualityId = obj.getFightSoulQualityId();
			}
		}
		
		for (InstPlayerFightSoul obj : newInstPlayerFightSoulList) {
			if (obj.getFightSoulQualityId() == qualityId) {
				minQualityIdInstPlayerFightSoulList.add(obj);
			}
		}
		
		Collections.sort(minQualityIdInstPlayerFightSoulList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((InstPlayerFightSoul)a).getId();
				int two = ((InstPlayerFightSoul)b).getId();
				return (int)(one - two); //升序排列 - 从小到大
			}
		}); 
		InstPlayerFightSoul instPlayerFightSoul = minQualityIdInstPlayerFightSoulList.get(0);//要升级的斗魂
		
		//验证是否达到最高等级
//		List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList1 = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(instPlayerFightSoul.getFightSoulQualityId());
//		if (instPlayerFightSoul.getLevel() >= fightSoulUpgradeExpList1.get(fightSoulUpgradeExpList1.size() - 1).getLevel()) {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_upLevel);
//			return;
//		}
		
		//找到被吞噬的斗魂列表[不是天阶，不是满级，不能为锁定，不能为升级斗魂，不能为上阵，不能为银魂（但可以为经验斗魂）]
		List<InstPlayerFightSoul> upgradedFightSoulList = new ArrayList<>();
		for (InstPlayerFightSoul obj : verfPlayerFightSoulList) {
			int fightSoulId = obj.getFightSoulId();
			DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(fightSoulId + "");
			List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList1 = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(fightSoul.getFightSoulQualityId());
			if (obj.getId() != instPlayerFightSoul.getId() && obj.getLockState() == 0 && fightSoul.getFightSoulQualityId() != StaticFightSoulQuality.tian && obj.getLevel() < fightSoulUpgradeExpList1.get(fightSoulUpgradeExpList1.size() - 1).getLevel()) {
				upgradedFightSoulList.add(obj);
			}
		}
		
		//验证是否有被吞噬的斗魂
		if (upgradedFightSoulList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noCanUpgradedFightSoul);
			return;
		}
		
		//处理升级逻辑
		int saveExp = 0;
		int currExp = 0;
		if (instPlayerFightSoul.getLevel() <= 1) {
			currExp = instPlayerFightSoul.getExp();
		} else {
			//计算当前等级能得到的累计经验
			currExp = FightSoulUtil.getCurrLevelExp(instPlayerFightSoul.getFightSoulQualityId(), instPlayerFightSoul.getLevel()) + instPlayerFightSoul.getExp();
		}
		saveExp = saveExp + currExp;
		
		int upLevelExp = FightSoulUtil.upLevelExp(instPlayerFightSoul.getFightSoulQualityId());
		for (InstPlayerFightSoul instPlayerFightSoulObj : upgradedFightSoulList) {
			//删除此斗魂
			getInstPlayerFightSoulDAL().deleteById(instPlayerFightSoulObj.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerFightSoulObj, instPlayerFightSoulObj.getId(), "", syncMsgData);
			
			int otherFightSoulExp = 0;
			if (instPlayerFightSoulObj.getLevel() <= 1) {
				otherFightSoulExp = DictMap.dictFightSoulMap.get(instPlayerFightSoulObj.getFightSoulId() + "").getInitExp() + instPlayerFightSoulObj.getExp();
			} else {
				otherFightSoulExp = DictMap.dictFightSoulMap.get(instPlayerFightSoulObj.getFightSoulId() + "").getInitExp() + FightSoulUtil.getCurrLevelExp(instPlayerFightSoulObj.getFightSoulQualityId(), instPlayerFightSoulObj.getLevel()) + instPlayerFightSoulObj.getExp();
			}
			saveExp += otherFightSoulExp;
			
			if (saveExp >= upLevelExp) {
				break;
			}
		}
		
		//由经验算出等级和剩余经验
		if (saveExp >= upLevelExp) {
			List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(instPlayerFightSoul.getFightSoulQualityId());
			instPlayerFightSoul.setLevel(fightSoulUpgradeExpList.get(fightSoulUpgradeExpList.size() - 1).getLevel());
//			System.out.println("level = " + fightSoulUpgradeExpList.get(fightSoulUpgradeExpList.size() - 1).getLevel());
			instPlayerFightSoul.setExp(0);
		} else {
			int[] levelAndRemainExp = FightSoulUtil.getLevelAndRemainExpByExp(saveExp, instPlayerFightSoul.getFightSoulQualityId());
			instPlayerFightSoul.setLevel(levelAndRemainExp[0]);
//			System.out.println("level = " + levelAndRemainExp[0]);
			instPlayerFightSoul.setExp(levelAndRemainExp[1]);
		}
		getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 猎魂一键吞噬时下发要升级的斗魂字典Id
	 * @author mp
	 * @date 2015-10-20 下午4:10:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void upgradeFightSoulId (Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//验证是否有可升级的斗魂[不能为上阵，不能为银魂，不能为经验斗魂，不能为天阶斗魂，不能是满级（但可以为锁定斗魂，但不可以有经验斗魂） ]
		List<InstPlayerFightSoul> newInstPlayerFightSoulList = new ArrayList<>();
		//找的是 不在阵上，不是银魂的斗魂列表
		List<InstPlayerFightSoul> verfPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = 0 and position = 0 and fightSoulQualityId != " + StaticFightSoulQuality.wu, instPlayerId);
		for (InstPlayerFightSoul obj : verfPlayerFightSoulList) {
			int fightSoulId = obj.getFightSoulId();
			DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(fightSoulId + "");
			List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList1 = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(fightSoul.getFightSoulQualityId());
			if (fightSoul.getIsExpFightSoul() == 0 && fightSoul.getFightSoulQualityId() != StaticFightSoulQuality.tian && obj.getLevel() < fightSoulUpgradeExpList1.get(fightSoulUpgradeExpList1.size() - 1).getLevel()) {
				newInstPlayerFightSoulList.add(obj);
			}
		}
		if (newInstPlayerFightSoulList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noCanUpgradeFightSoul);
			return;
		}
		
		//找到品质最高（品质id最小）且id最小的当做要升级的斗魂
		List<InstPlayerFightSoul> minQualityIdInstPlayerFightSoulList = new ArrayList<>();
		int qualityId = 10;
		for (InstPlayerFightSoul obj : newInstPlayerFightSoulList) {
			if (obj.getFightSoulQualityId() < qualityId) {
				qualityId = obj.getFightSoulQualityId();
			}
		}
		
		for (InstPlayerFightSoul obj : newInstPlayerFightSoulList) {
			if (obj.getFightSoulQualityId() == qualityId) {
				minQualityIdInstPlayerFightSoulList.add(obj);
			}
		}
		
		Collections.sort(minQualityIdInstPlayerFightSoulList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((InstPlayerFightSoul)a).getId();
				int two = ((InstPlayerFightSoul)b).getId();
				return (int)(one - two); //升序排列 - 从小到大
			}
		}); 
		InstPlayerFightSoul instPlayerFightSoul = minQualityIdInstPlayerFightSoulList.get(0);//要升级的斗魂
		
		//验证是否达到最高等级
//		List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList1 = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(instPlayerFightSoul.getFightSoulQualityId());
//		if (instPlayerFightSoul.getLevel() >= fightSoulUpgradeExpList1.get(fightSoulUpgradeExpList1.size() - 1).getLevel()) {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_upLevel);
//			return;
//		}
		
		//找到被吞噬的斗魂列表[不是天阶，不是满级，不能为锁定，不能为升级斗魂，不能为上阵，不能为银魂（但可以为经验斗魂，但必须是未锁定的）]
		int isHaveDiFightSoul = 0;
		List<InstPlayerFightSoul> upgradedFightSoulList = new ArrayList<>();
		for (InstPlayerFightSoul obj : verfPlayerFightSoulList) {
			int fightSoulId = obj.getFightSoulId();
			DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(fightSoulId + "");
			List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList1 = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(fightSoul.getFightSoulQualityId());
			if (obj.getId() != instPlayerFightSoul.getId() && obj.getLockState() == 0 && fightSoul.getFightSoulQualityId() != StaticFightSoulQuality.tian && obj.getLevel() < fightSoulUpgradeExpList1.get(fightSoulUpgradeExpList1.size() - 1).getLevel()) {
				upgradedFightSoulList.add(obj);
				if (obj.getFightSoulQualityId() == StaticFightSoulQuality.di) {
					isHaveDiFightSoul = 1;
				}
			}
		}
		
		//验证是否有被吞噬的斗魂
		if (upgradedFightSoulList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noCanUpgradedFightSoul);
			return;
		}
		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("id", instPlayerFightSoul.getFightSoulId());
		retMsgData.putIntItem("isHaveDiFightSoul", isHaveDiFightSoul);//被吞噬的斗魂里边是否含有地阶斗魂  0-没有 1-有
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 一键附魂
	 * @author mp
	 * @date 2015-10-21 下午1:42:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void oneKeyStick(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");//要附着的卡牌实例Id
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		
		//验证是否有此斗魂
		if (instPlayerCard == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerCard.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//计算此卡牌上开启的位置
		List<Integer> positions = new ArrayList<>();
		for (DictFightSoulPositionCond obj : DictList.dictFightSoulPositionCondList) {
			int openTitleId = obj.getOpenTitleId();
			int position = obj.getPosition();
			if (DictMap.dictTitleDetailMap.get(instPlayerCard.getTitleDetailId() + "").getTitleId() >= openTitleId) {
				if (getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId + " and position = " + position, instPlayerId).size() <= 0) {
					positions.add(position);
				}
			}
		}
		
		//验证当前称号是否有位置开启
		if (positions.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noPosition);
			return;
		}
		
		//找出可附着的斗魂[不能是银魂，不能是经验斗魂，不能是已上阵在其他卡牌身上的， 按品质和等级进行排序的]
		List<String> fightPropTypeList = new ArrayList<>();
		List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = 0 and position = 0 and fightSoulQualityId != " + StaticFightSoulQuality.wu + " order by fightSoulQualityId asc, level desc", instPlayerId);
		List<InstPlayerFightSoul> haveInstPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
		List<String> haveFightTypeList = new ArrayList<>();
		for (InstPlayerFightSoul instPlayerFightSoulObj : haveInstPlayerFightSoulList) {
			String fightPropType = FightSoulUtil.getFightTypeByFightSoulId(instPlayerFightSoulObj.getFightSoulId());
			haveFightTypeList.add(fightPropType);
		}
		
		
		List<InstPlayerFightSoul> realInstPlayerFightSoulList = new ArrayList<>();
		for (InstPlayerFightSoul obj : instPlayerFightSoulList) {
			DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(obj.getFightSoulId() + "");
			if (fightSoul.getIsExpFightSoul() == 0) {
				String fightPropType = FightSoulUtil.getFightTypeByFightSoulId(fightSoul.getId());
				if (!fightPropTypeList.contains(fightPropType)) {
					if (!haveFightTypeList.contains(fightPropType)) {
						realInstPlayerFightSoulList.add(obj);
					}
					fightPropTypeList.add(fightPropType);
				}
			}
		}
		
		//验证是否有可上阵的斗魂
		if (realInstPlayerFightSoulList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fightSoul_noFormatFightSoul);
			return;
		}
		
		//上阵
		for (int i = 0; i < positions.size(); i++) {
			int position = positions.get(i);
			if (realInstPlayerFightSoulList.size() >= (i+1)) {
				InstPlayerFightSoul instPlayerFightSoul = realInstPlayerFightSoulList.get(i);
				instPlayerFightSoul.setInstCardId(instPlayerCardId);
				instPlayerFightSoul.setPosition(position);
				getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 一键卸下
	 * @author mp
	 * @date 2015-10-21 下午1:42:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void oneKeyDrop(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");//要卸下的卡牌实例Id
		
		List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
		for (InstPlayerFightSoul instPlayerFightSoul : instPlayerFightSoulList) {
			instPlayerFightSoul.setInstCardId(0);
			instPlayerFightSoul.setPosition(0);
			getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 购买银币
	 * @author mp
	 * @date 2015-11-3 下午5:39:29
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fightSoulBuySilver(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int num = (Integer)msgMap.get("num");//购买银票个数
		
		//验证个数合理范围
		if (num <= 0 || num > 999) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证银票是否存在
		DictThing thingObj = DictMap.dictThingMap.get(StaticThing.silverNote10000 + "");
		if (thingObj == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noThing);
			return;
		}
		
		//验证元宝数
		int needGold = thingObj.getBuyGold() * num;
		float multipleExp = 1f;
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.storeDiscont)) {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.storeDiscont + "");
			multipleExp = activityHoliday.getMultiple();
		}
		if (multipleExp != 1f) {
			needGold = ConvertUtil.toInt(needGold * multipleExp);
		}
		if (instPlayer.getGold() < needGold) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		
		// 获取玩家当日的商店购买记录
		Map<Integer, String> todayBuyItemMap = ThingUtil.getTodayBuyItemMap(instPlayerId);

		// 根据玩家vip等级获取商店购买的道具列表
		Map<Integer, Integer> vipItemMap = ThingUtil.getItemsByVipLevel(instPlayer.getVipLevel());

		// 验证当日购买个数
		int canBuyNum = -1;
		if (vipItemMap.containsKey(StaticThing.silverNote10000)) {

			int todayBuyNum = 0;
			int buyLimitNum = vipItemMap.get(StaticThing.silverNote10000);
			if (todayBuyItemMap.containsKey(StaticThing.silverNote10000)) {
				todayBuyNum = ConvertUtil.toInt(todayBuyItemMap.get(StaticThing.silverNote10000).split("_")[0]);
			}

			canBuyNum = (buyLimitNum - todayBuyNum) < 0 ? 0 : (buyLimitNum - todayBuyNum);

			if ((canBuyNum - num) < 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_timesNull);
				return;
			}
		}
		
		//给银币扣除元宝
		int getSilver = thingObj.getSellCopper() * num;
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + getSilver) + "");
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, needGold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		// 处理商店购买记录,插入前对于道具, 魔核只保留今日和昨日数据,以便查询, vip礼包永久
		String beforeYesterday = DateUtil.getNumDayDate(-2);
		List<InstPlayerStore> todayBuyItemList = getInstPlayerStoreDAL().getList("instPlayerId = " + instPlayerId + " and bagType = " + StaticBagType.item + " and buyTime < '" + beforeYesterday + "'", 0);
		for (InstPlayerStore instPlayerStore : todayBuyItemList) {
			getInstPlayerStoreDAL().deleteById(instPlayerStore.getId(), instPlayerId);
		}

		// 添加记录
		DictThing thing = DictMap.dictThingMap.get(StaticThing.silverNote10000 + "");
		ThingUtil.addPlayerStore(1, thing, instPlayerId, num, StaticThing.silverNote10000);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 购买银币/票个数
	 * @author mp
	 * @date 2015-11-6 下午4:38:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getFightSoulBuySilverTimes(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		// 获取玩家当日的商店购买记录
		Map<Integer, String> todayBuyItemMap = ThingUtil.getTodayBuyItemMap(instPlayerId);

		// 根据玩家vip等级获取商店购买的道具列表
		Map<Integer, Integer> vipItemMap = ThingUtil.getItemsByVipLevel(instPlayer.getVipLevel());

		// 验证当日购买个数
		int canBuyNum = -1;
		if (vipItemMap.containsKey(StaticThing.silverNote10000)) {
			int todayBuyNum = 0;
			int buyLimitNum = vipItemMap.get(StaticThing.silverNote10000);
			if (todayBuyItemMap.containsKey(StaticThing.silverNote10000)) {
				todayBuyNum = ConvertUtil.toInt(todayBuyItemMap.get(StaticThing.silverNote10000).split("_")[0]);
			}
			canBuyNum = (buyLimitNum - todayBuyNum) < 0 ? 0 : (buyLimitNum - todayBuyNum);
		}
		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("canBuyNum", canBuyNum);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
}
