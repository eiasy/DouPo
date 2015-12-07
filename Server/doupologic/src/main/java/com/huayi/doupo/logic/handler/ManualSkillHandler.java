package com.huayi.doupo.logic.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictManualSkill;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerManualSkill;
import com.huayi.doupo.base.model.InstPlayerManualSkillLine;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.ManualSkillUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class ManualSkillHandler extends BaseHandler{
	
	/**
	 * 装备手动技能
	 * @author hzw
	 * @date 2014-9-11上午11:23:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void use(Map<String, Object> msgMap,String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		//参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerManualSkillId = (Integer)msgMap.get("instPlayerManualSkillId");
		int position = (Integer)msgMap.get("position");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId , instPlayerId);
		//验证参数
		InstPlayerManualSkill instPlayerManualSkill = getInstPlayerManualSkillDAL().getModel(instPlayerManualSkillId , instPlayerId);
		if(instPlayerManualSkill.getInstPlayerId() != instPlayerId){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		List<InstPlayerManualSkill> instPlayerManualSkillList = getInstPlayerManualSkillDAL().getList("instPlayerId = " + instPlayerId + " and manualSkillId = " + instPlayerManualSkill.getManualSkillId() + " and isUse > 0 ", instPlayerId);
		instPlayerManualSkill.setIsUse(position);
		//是否已在阵上
		if(instPlayerManualSkillList.size() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notManualSkillRepeat);
			return;
		}
		
		if(instPlayer.getLevel() < FormulaUtil.manualSkillPosition(position)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		ManualSkillUtil.addInstPlayerManualSkillLine(instPlayerId, instPlayerManualSkillId, position, syncMsgData);
		
		getInstPlayerManualSkillDAL().update(instPlayerManualSkill, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerManualSkill, instPlayerManualSkill.getId(), instPlayerManualSkill.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 更换/卸下/排布
	 * @author hzw
	 * @date 2014-9-11上午11:31:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void convertManualSkill(Map<String, Object> msgMap,String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		//参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerManualSkillLineId = (Integer)msgMap.get("instPlayerManualSkillLineId");
		int instPlayerManualSkillId = (Integer)msgMap.get("instPlayerManualSkillId");  //卸下的时候为0
		int position = (Integer)msgMap.get("position");
		//验证参数
		InstPlayerManualSkillLine instPlayerManualSkillLine = getInstPlayerManualSkillLineDAL().getModel(instPlayerManualSkillLineId , instPlayerId);
		if(instPlayerManualSkillLine.getInstPlayerId() != instPlayerId){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		InstPlayerManualSkill instPlayerManualSkill = null;
		if(instPlayerManualSkillId != 0){
			instPlayerManualSkill = getInstPlayerManualSkillDAL().getModel(instPlayerManualSkillId , instPlayerId);
			if(instPlayerManualSkill.getInstPlayerId() != instPlayerId){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			List<InstPlayerManualSkill> instPlayerManualSkillList = null;
				instPlayerManualSkillList = getInstPlayerManualSkillDAL().getList("instPlayerId = " + instPlayerId + " and manualSkillId = " + instPlayerManualSkill.getManualSkillId() + " and isUse > 0 ", instPlayerId);
				
			//验证
			if(instPlayerManualSkillList.size() > 0 && instPlayerManualSkill.getIsUse() == 0 && instPlayerManualSkillList.get(0).getIsUse() != position){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notManualSkillRepeat);
				return;
			}
			
		}
		
		int oldInstPlayerManualSkillId = 0;
		switch(position){
			case 1: oldInstPlayerManualSkillId = instPlayerManualSkillLine.getPosition1(); instPlayerManualSkillLine.setPosition1(instPlayerManualSkillId); break;
			case 2: oldInstPlayerManualSkillId = instPlayerManualSkillLine.getPosition2(); instPlayerManualSkillLine.setPosition2(instPlayerManualSkillId); break;
			case 3: oldInstPlayerManualSkillId = instPlayerManualSkillLine.getPosition3(); instPlayerManualSkillLine.setPosition3(instPlayerManualSkillId); break;
			case 4: oldInstPlayerManualSkillId = instPlayerManualSkillLine.getPosition4(); instPlayerManualSkillLine.setPosition4(instPlayerManualSkillId); break;
		}
		
		
		InstPlayerManualSkill oldInstPlayerManualSkill = getInstPlayerManualSkillDAL().getModel(oldInstPlayerManualSkillId , instPlayerId);
			oldInstPlayerManualSkill.setIsUse(0);
		
		if(instPlayerManualSkill != null){
			int oldPosition = 0;
			if(instPlayerManualSkill.getIsUse() > 0){
				oldInstPlayerManualSkill.setIsUse(instPlayerManualSkill.getIsUse());
				oldPosition = instPlayerManualSkill.getIsUse();
			}
			instPlayerManualSkill.setIsUse(position);
			
			getInstPlayerManualSkillDAL().update(instPlayerManualSkill, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerManualSkill, instPlayerManualSkill.getId(), instPlayerManualSkill.getResult(), syncMsgData);
			
			switch(oldPosition){
			case 1: instPlayerManualSkillLine.setPosition1(oldInstPlayerManualSkillId); break;
			case 2: instPlayerManualSkillLine.setPosition2(oldInstPlayerManualSkillId); break;
			case 3: instPlayerManualSkillLine.setPosition3(oldInstPlayerManualSkillId); break;
			case 4: instPlayerManualSkillLine.setPosition4(oldInstPlayerManualSkillId); break;
			}
		}
		
		getInstPlayerManualSkillDAL().update(oldInstPlayerManualSkill, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, oldInstPlayerManualSkill, oldInstPlayerManualSkill.getId(), oldInstPlayerManualSkill.getResult(), syncMsgData);
		
		getInstPlayerManualSkillLineDAL().update(instPlayerManualSkillLine, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerManualSkillLine, instPlayerManualSkillLine.getId(), instPlayerManualSkillLine.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	
	/**
	 * 吃技能/升级
	 * @author hzw
	 * @date 2014-9-11下午2:26:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eatManualSkill(Map<String, Object> msgMap,String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerManualSkillId = (Integer)msgMap.get("instPlayerManualSkillId");
		String instPlayerManualSkillIdList = (String)msgMap.get("instPlayerManualSkillIdList");
		String[] paramList = instPlayerManualSkillIdList.split(";");
		int expsum = 0;
		
		InstPlayerManualSkill instPlayerManualSkill = getInstPlayerManualSkillDAL().getModel(instPlayerManualSkillId, instPlayerId);
		if(instPlayerId != instPlayerManualSkill.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
	
		for(String str : paramList){
			InstPlayerManualSkill obj = getInstPlayerManualSkillDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			if(instPlayerId != obj.getInstPlayerId()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if(obj.getIsUse() > 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_manualSkillIsUse);
				return;
			}
			DictManualSkill dictManualSkill = DictMap.dictManualSkillMap.get(obj.getManualSkillId() + "");
			expsum += FormulaUtil.eatManualSkillExp(obj.getLevel()) + dictManualSkill.getExp() + obj.getExp();
		}
		 
		
		//等级,经验计算
		Map<String, Integer> retMap = FormulaUtil.calcManualSkillLevel(instPlayerManualSkill.getLevel(), instPlayerManualSkill.getExp() + expsum);
		int level = retMap.get("level");
		int exp = retMap.get("exp");
		
		instPlayerManualSkill.setLevel(level);
		instPlayerManualSkill.setExp(exp);
		getInstPlayerManualSkillDAL().update(instPlayerManualSkill, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerManualSkill, instPlayerManualSkill.getId(), instPlayerManualSkill.getResult(), syncMsgData);
		
		//删除被吃技能
		for(String str : paramList){
			InstPlayerManualSkill obj = getInstPlayerManualSkillDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			getInstPlayerManualSkillDAL().deleteById(obj.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	
	/**
	 * 出售
	 * @author hzw
	 * @date 2014-9-15上午10:14:02
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void manualSkillSell(HashMap<String, Object> msgMap, String channelId)throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, 0);
		String instPlayerManualSkillIdList = (String)msgMap.get("instPlayerManualSkillIdList");
		String[] paramList = instPlayerManualSkillIdList.split(";");
		int copperSum = 0;
		
		for(String str : paramList){
			InstPlayerManualSkill obj = getInstPlayerManualSkillDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			DictManualSkill dictManualSkill = DictMap.dictManualSkillMap.get(obj.getManualSkillId() + "");
			if(instPlayerId == obj.getInstPlayerId()){
				copperSum += dictManualSkill.getSellCopper();
				getInstPlayerManualSkillDAL().deleteById(obj.getId(), instPlayerId);
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
