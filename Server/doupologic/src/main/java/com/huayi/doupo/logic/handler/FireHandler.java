package com.huayi.doupo.logic.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictFire;
import com.huayi.doupo.base.model.DictFireGain;
import com.huayi.doupo.base.model.DictFireGainRule;
import com.huayi.doupo.base.model.DictFireSkillChange;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerFire;
import com.huayi.doupo.base.model.InstPlayerFireTemp;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.FireUtil;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 异火处理类
 * @author hzw
 * @date 2014-7-23下午4:52:13
 */
public class FireHandler extends BaseHandler{

	/**
	 * 开启/蜕变异火技能
	 * @author hzw
	 * @date 2014-7-23下午5:06:19
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void changeFireSkill(HashMap<String, Object> msgMap, String channelId)throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int chageType = (Integer)msgMap.get("chageType"); //0-开启1-火晶 2-金之
		int instPlayerFireId = (Integer)msgMap.get("instPlayerFireId");
		int position = (Integer)msgMap.get("position");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerFire instPlayerFire = getInstPlayerFireDAL().getModel(instPlayerFireId, instPlayerId);
		DictFire dictFire = DictMap.dictFireMap.get(instPlayerFire.getFireId() + "");
		if(instPlayerId != instPlayerFire.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(chageType == 0){
			String skillConds = "";
			skillConds = dictFire.getBySkillConds();
			if(instPlayerFire.getLevel() < ConvertUtil.toInt(skillConds.split("_")[position-1])){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFireLevel);
				return;
			}
		}
		
		//处理蜕变消耗
		else if(chageType == 1){
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.fireCrystal, instPlayerId);
			if(instPlayerThingList.size() <= 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFireCrystalNum);
				return;
			}
			int fireCrystalChange = ConvertUtil.toInt(DictMap.dictSysConfigMap.get(StaticSysConfig.fireCrystalChange+ "").getValue());
			if(instPlayerThingList.size() > 0){
				InstPlayerThing fireCrystal = instPlayerThingList.get(0);
				if(fireCrystal.getNum() < fireCrystalChange){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFireCrystalNum);
					return;
				}
				
				//处理消耗的火晶
				ThingUtil.updateInstPlayerThing(instPlayerId, fireCrystal, fireCrystalChange, syncMsgData, msgMap);
			}
			
		}else{
			int fireCrystalChange = ConvertUtil.toInt(DictMap.dictSysConfigMap.get(StaticSysConfig.goldenChange+ "").getValue());
			if(instPlayer.getGold() < fireCrystalChange){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}
			//消耗元宝
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, fireCrystalChange, msgMap);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		
		//组织处玩家异火现有的异火技能
		String fireSkills = "";
		fireSkills = instPlayerFire.getBySkillIds();
		String skills[] = fireSkills.split(";");
		int fireSkillId = 0;
		
		//确定蜕变得到的技能
		float randomSum = 0.00f;
		List<DictFireSkillChange> dictFireSkillChangeList = DictList.dictFireSkillChangeList;
		for(DictFireSkillChange obj : dictFireSkillChangeList){
			if(obj.getPotential() <= dictFire.getPotential()){
				float random = RandomUtil.getRandomFloat();
				randomSum += obj.getChance();
				if(random <= randomSum){
					if(!FireUtil.fireSkillIsHave(obj.getFireSkillId(), skills)){
						fireSkillId = obj.getFireSkillId();
						break;
					}
				}
			}
		} 
		
		List<InstPlayerFireTemp> instPlayerFireTempList = getInstPlayerFireTempDAL().getList("instPlayerFireId = " + instPlayerFireId, 0);
		if(instPlayerFireTempList.size() > 0){
			for(InstPlayerFireTemp obj : instPlayerFireTempList){
				getInstPlayerFireTempDAL().deleteById(obj.getId(), 0);
			}
		}
		
		if(chageType == 0){
			for(String skill : skills){
				if(ConvertUtil.toInt(skill.split("_")[0]) == position){
					fireSkills = fireSkills.replace(skill, position + "_" + fireSkillId);
					break;
				}
			}
			instPlayerFire.setBySkillIds(fireSkills);
			getInstPlayerFireDAL().update(instPlayerFire, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFire, instPlayerFire.getId(), instPlayerFire.getResult(), syncMsgData);
		}else{
			InstPlayerFireTemp instPlayerFireTemp = new InstPlayerFireTemp();
			instPlayerFireTemp.setInstPlayerFireId(instPlayerFireId);
			instPlayerFireTemp.setFireSkillId(fireSkillId);
			instPlayerFireTemp.setPosition(position);
			getInstPlayerFireTempDAL().add(instPlayerFireTemp, 0);
		}
		
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", fireSkillId);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, msgData);
	}
	
	/**
	 * 保留异火斗技
	 * @author hzw
	 * @date 2014-7-24下午3:34:37
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void trainFireSkill(HashMap<String, Object> msgMap, String channelId)throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerFireId = (Integer)msgMap.get("instPlayerFireId");
		InstPlayerFire instPlayerFire = getInstPlayerFireDAL().getModel(instPlayerFireId, instPlayerId);
		if(instPlayerId != instPlayerFire.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		InstPlayerFireTemp instPlayerFireTemp = getInstPlayerFireTempDAL().getList("instPlayerFireId = " + instPlayerFireId, 0).get(0);
		//组织处玩家异火现有的
		String fireSkills = "";
		fireSkills = instPlayerFire.getBySkillIds();
		String skills[] = fireSkills.split(";");
		for(String skill : skills){
			if(ConvertUtil.toInt(skill.split("_")[0]) == instPlayerFireTemp.getPosition()){
				fireSkills = fireSkills.replace(skill, instPlayerFireTemp.getPosition() + "_" + instPlayerFireTemp.getFireSkillId());
				break;
			}
		}
		instPlayerFire.setBySkillIds(fireSkills);
		getInstPlayerFireDAL().update(instPlayerFire, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFire, instPlayerFire.getId(), instPlayerFire.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 异火抓取
	 * @author hzw
	 * @date 2014-7-25上午11:58:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fireGain(HashMap<String, Object> msgMap, String channelId)throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int type = (Integer)msgMap.get("type");   //0-直取高阶  1-收取 2-收取10次
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, 0);
		int position = instPlayer.getFireGainRuleId(); //抓取的位置
		DictFireGainRule dictFireGainRule = DictMap.dictFireGainRuleMap.get(position + "");
		if(type == 0){
			int fireGainGold = ConvertUtil.toInt(DictMap.dictSysConfigMap.get(StaticSysConfig.fireGainGold+ "").getValue());
			if(instPlayer.getGold() < fireGainGold){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}
			position = 4;
			//扣除元宝
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, fireGainGold, msgMap);
		}else{
			if(ConvertUtil.toInt(instPlayer.getCopper()) < dictFireGainRule.getCopper()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
				return;
			}
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - dictFireGainRule.getCopper()) + "");
		}
		int num = 1;
		if(type == 2){
			num = 10;
		}
		String fires = "";
		int copper = 0;
		for(int i = 1; i <= num; i++){
			int positionTemp = position;
			List<DictFireGain> dictFireGainlist =  (List<DictFireGain>) DictMapList.dictFireGainMap.get(positionTemp);
			float sum = 0.0f;
			for(DictFireGain obj : dictFireGainlist){
				sum += obj.getChance();
			}
			float random = RandomUtil.getRandomFloat();
			float randomTemp = 0.00f;
			for(DictFireGain obj : dictFireGainlist){
				randomTemp +=  (obj.getChance() / sum);
				if(random <= randomTemp){
					InstPlayerFire instPlayerFire = FireUtil.addInstPlayerFire(instPlayerId, obj.getFireId());
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerFire, instPlayerFire.getId(), instPlayerFire.getResult(), syncMsgData);
					fires += instPlayerFire.getId() + ";";
					break;
				}
			}
			random = RandomUtil.getRandomFloat();
			if(random <= dictFireGainRule.getChangeUp()){
				positionTemp ++;
			}else{
				positionTemp = 1;
			}
			DictFireGainRule obj = DictMap.dictFireGainRuleMap.get(position + "");
			copper += obj.getCopper();
			if(ConvertUtil.toInt(instPlayer.getCopper()) < copper){
				copper = copper - obj.getCopper();
				break;
			}
			position = positionTemp;
		}
		if(num > 1){
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - copper) + "");
		}
		
		instPlayer.setFireGainRuleId(position);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageData msgData = new MessageData();
		msgData.putStringItem("1", fires.substring(0, fires.length() - 1));
		
		//处理每日任务
//		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.fire, 1, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, msgData);
	}
	
	/**
	 * 异火升级
	 * @author hzw
	 * @date 2014-7-28上午11:12:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fireUpgrade(HashMap<String, Object> msgMap, String channelId)throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerFireId = (Integer)msgMap.get("instPlayerFireId");
		String instPlayerFireIdList = (String)msgMap.get("instPlayerFireIdList");
		String[] paramList = instPlayerFireIdList.split(";");
		int expsum = 0;
		
		InstPlayerFire instPlayerFire = getInstPlayerFireDAL().getModel(instPlayerFireId, instPlayerId);
		if(instPlayerId != instPlayerFire.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
	
		for(String str : paramList){
			InstPlayerFire obj = getInstPlayerFireDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			if(instPlayerId != obj.getInstPlayerId()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if(instPlayerFireId == ConvertUtil.toInt(str)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fireOneself);
				return;
			}
			expsum += FormulaUtil.eatFireExp(obj);
		}
		 
		//更新异火的属性
		
		//等级,经验计算
		Map<String, Integer> retMap = FormulaUtil.calcFireLevel(instPlayerFire.getLevel(), instPlayerFire.getExp() + expsum);
		int level = retMap.get("level");
		int exp = retMap.get("exp");
		
		instPlayerFire.setLevel(level);
		instPlayerFire.setExp(exp);
		getInstPlayerFireDAL().update(instPlayerFire, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFire, instPlayerFire.getId(), instPlayerFire.getResult(), syncMsgData);
		
		//删除被吃异火
		for(String str : paramList){
			InstPlayerFire obj = getInstPlayerFireDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			getInstPlayerFireDAL().deleteById(obj.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 异火传承
	 * @author hzw
	 * @date 2014-7-28下午2:56:20
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fireInherit(HashMap<String, Object> msgMap, String channelId)throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerFireId = (Integer)msgMap.get("instPlayerFireId");
		int oldInstPlayerFireId = (Integer)msgMap.get("oldInstPlayerFireId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, 0);
		InstPlayerFire instPlayerFire = getInstPlayerFireDAL().getModel(instPlayerFireId, instPlayerId);
		if(instPlayerId != instPlayerFire.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		int expsum = 0;
		InstPlayerFire obj = getInstPlayerFireDAL().getModel(oldInstPlayerFireId, instPlayerId);
		if(instPlayerId != obj.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerFireId == oldInstPlayerFireId){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_fireInheritOneself);
			return;
		}
		DictFire dictFire = DictMap.dictFireMap.get(obj.getFireId() + "");
		if(dictFire.getType() != 1){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFireInherit);
			return;
		}
		expsum += FormulaUtil.eatFireExp(obj);
		 
		//更新异火的属性
		
		//等级,经验计算
		Map<String, Integer> retMap = FormulaUtil.calcFireLevel(instPlayerFire.getLevel(), instPlayerFire.getExp() + expsum);
		int level = retMap.get("level");
		int exp = retMap.get("exp");
		
		//组织被动技能
		String newBySkillIds = instPlayerFire.getBySkillIds();
		String bySkillIds = obj.getBySkillIds();
		String newSkills[] = newBySkillIds.split(";");
		String skills[] = bySkillIds.split(";");
		int num = newSkills.length;
		if(skills.length < newSkills.length){
			num = skills.length;
		}
		for(int i = 0; i < num; i++){
			if(ConvertUtil.toInt(skills[i].split("_")[1]) != 0){
				newSkills[i] = skills[i];
			}
		}
		newBySkillIds = "";
		for(String str : newSkills){
			newBySkillIds += str + ";";
		}
		instPlayerFire.setBySkillIds(newBySkillIds.substring(0, newBySkillIds.length() - 1));
		
		instPlayerFire.setLevel(level);
		instPlayerFire.setExp(exp);
		getInstPlayerFireDAL().update(instPlayerFire, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFire, instPlayerFire.getId(), instPlayerFire.getResult(), syncMsgData);
		
		//新增一个初始异火
		InstPlayerFire newInstPlayerFire = FireUtil.addInstPlayerFire(instPlayerId, obj.getFireId());
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, newInstPlayerFire, newInstPlayerFire.getId(), newInstPlayerFire.getResult(), syncMsgData);
		
		//删除旧异火
		getInstPlayerFireDAL().deleteById(obj.getId(), instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
		
		//更新铜钱消耗
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - ConvertUtil.toInt(DictMap.dictSysConfigMap.get(StaticSysConfig.fireInherit+ "").getValue())) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 异火上阵
	 * @author hzw
	 * @date 2014-7-28下午5:20:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fireUse(HashMap<String, Object> msgMap, String channelId)throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerFireId = (Integer)msgMap.get("instPlayerFireId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, 0);
		InstPlayerFire instPlayerFire = getInstPlayerFireDAL().getModel(instPlayerFireId, instPlayerId);
		if(instPlayerId != instPlayerFire.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		DictFire dictFire = DictMap.dictFireMap.get(instPlayerFire.getFireId() + "");
		if(dictFire.getType() != 1){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFireUse);
			return;
		}
		 
		//更新异火上阵状态
		instPlayerFire.setIsUse(1);
		getInstPlayerFireDAL().update(instPlayerFire, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFire, instPlayerFire.getId(), instPlayerFire.getResult(), syncMsgData);
		
		//更新异火上阵状态
		if(instPlayer.getInstPlayerFireId() != 0){
			InstPlayerFire oldInstPlayerFire = getInstPlayerFireDAL().getModel(instPlayer.getInstPlayerFireId(), instPlayerId);
			oldInstPlayerFire.setIsUse(0);
			getInstPlayerFireDAL().update(oldInstPlayerFire, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, oldInstPlayerFire, oldInstPlayerFire.getId(), oldInstPlayerFire.getResult(), syncMsgData);
		}
				
		
		//更新玩家异火字段
		instPlayer.setInstPlayerFireId(instPlayerFireId);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 出售
	 * @author hzw
	 * @date 2014-7-28下午5:24:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fireSell(HashMap<String, Object> msgMap, String channelId)throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, 0);
		String instPlayerFireIdList = (String)msgMap.get("instPlayerFireIdList");
		String[] paramList = instPlayerFireIdList.split(";");
		int copperSum = 0;
		
		for(String str : paramList){
			InstPlayerFire obj = getInstPlayerFireDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			DictFire dictFire = DictMap.dictFireMap.get(obj.getFireId() + "");
			if(instPlayerId == obj.getInstPlayerId() && dictFire.getType() == 2){
				copperSum += dictFire.getSellCopper();
				getInstPlayerFireDAL().deleteById(obj.getId(), instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
			}
		}
		
		//更新玩家铜钱
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + copperSum) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
}
