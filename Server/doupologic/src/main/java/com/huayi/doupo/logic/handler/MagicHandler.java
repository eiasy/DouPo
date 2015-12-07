package com.huayi.doupo.logic.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerMagic;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class MagicHandler extends BaseHandler{
	
	/**
	 * 功法/法宝  上阵/更换
	 * @author mp
	 * @date 2014-12-8 上午10:15:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void putOn(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerMagicId = (Integer)msgMap.get("instPlayerMagicId");//法宝/功法实例Id
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");//卡牌实例Id
		int type = (Integer)msgMap.get("type");//类型 1-法宝 2-功法
		
		MessageData syncMsgData = new MessageData();
		InstPlayerMagic instPlayerMagic = getInstPlayerMagicDAL().getModel(instPlayerMagicId, instPlayerId);
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);

		//验证参数是否有效
		if (instPlayerMagic == null || instPlayerCard == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家Id
		if (instPlayerMagic.getInstPlayerId() != instPlayerId || instPlayerCard.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证此功法/法宝是否已是上阵状态
		if (instPlayerMagic.getInstCardId() != 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_onFormat);
			return;
		}
		
		//查找是否已有法宝/功法在阵上,没有添加,有更换[将原有法宝/功法的上阵状态修改为下阵]
		List<InstPlayerMagic> onFormatMagicList = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId + " and magicType = " + type + " and instCardId = " + instPlayerCardId, instPlayerId);
		if (onFormatMagicList.size() > 0) {
			InstPlayerMagic onFormatMagic = onFormatMagicList.get(0);
			onFormatMagic.setInstCardId(0);
			getInstPlayerMagicDAL().update(onFormatMagic, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, onFormatMagic, onFormatMagic.getId(), onFormatMagic.getResult(), syncMsgData);
		}
		
		//上阵
		instPlayerMagic.setInstCardId(instPlayerCardId);
		getInstPlayerMagicDAL().update(instPlayerMagic, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerMagic, instPlayerMagic.getId(), instPlayerMagic.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 功法/法宝下阵
	 * @author mp
	 * @date 2014-12-8 上午10:30:59
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void putOff(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerMagicId = (Integer)msgMap.get("instPlayerMagicId");//法宝/功法实例Id
		
		MessageData syncMsgData = new MessageData();
		InstPlayerMagic instPlayerMagic = getInstPlayerMagicDAL().getModel(instPlayerMagicId, instPlayerId);

		//验证参数是否有效
		if (instPlayerMagic == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家Id
		if (instPlayerMagic.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证此法宝/功法是否在阵上,只要在阵上的,才谈得上卸下
		if (instPlayerMagic.getInstCardId() == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noFormat);
			return;
		}
		
		//下阵
		instPlayerMagic.setInstCardId(0);
		getInstPlayerMagicDAL().update(instPlayerMagic, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerMagic, instPlayerMagic.getId(), instPlayerMagic.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	
	/**
	 * 强化法宝与功法
	 * @author hzw
	 * @date 2014-12-8上午10:48:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void strengthenMagic(HashMap<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerMagicId = (Integer)msgMap.get("instPlayerMagicId");
		InstPlayerMagic instPlayerMagic = getInstPlayerMagicDAL().getModel(instPlayerMagicId, instPlayerId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		String instPlayerMagicIdList = (String)msgMap.get("instPlayerMagicIdList");
		String[] paramList = instPlayerMagicIdList.split(";");
		int expsum = 0;
		//验证参数
		if (instPlayerMagic.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(DictMap.dictMagicLevelMap.get(instPlayerMagic.getMagicLevelId() + "").getLevel() >= instPlayer.getLevel()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_magicstreng_level);
			return;
		}
		
		for(String str : paramList){
			InstPlayerMagic obj = getInstPlayerMagicDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			if(instPlayerId != obj.getInstPlayerId()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if(instPlayerMagicId == ConvertUtil.toInt(str)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardOneself);
				return;
			}
			if(obj.getInstCardId() != 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_magicOneself);
				return;
			}
			expsum += FormulaUtil.eatMagicExp(obj) + obj.getExp() + DictMap.dictMagicMap.get(obj.getMagicId() + "").getExp();
		}
		
		//等级,经验计算
		Map<String, Integer> retMap = FormulaUtil.calcMagicLevel(instPlayerMagic, instPlayerMagic.getExp() + expsum, instPlayer.getLevel());
		int magicLevelId = retMap.get("magicLevelId");
		int exp = retMap.get("exp");
		
		instPlayerMagic.setMagicLevelId(magicLevelId);
		instPlayerMagic.setExp(exp);
		getInstPlayerMagicDAL().update(instPlayerMagic, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerMagic, instPlayerMagic.getId(), instPlayerMagic.getResult(), syncMsgData);
		
		//删除被吃法宝与功法
		for(String str : paramList){
			InstPlayerMagic obj = getInstPlayerMagicDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			getInstPlayerMagicDAL().deleteById(obj.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
		}
		
		//验证法宝功法成就
		if (instPlayerMagic.getMagicType() == 1) {
			AchievementUtil.magic1(instPlayerId, syncMsgData);
		} else if (instPlayerMagic.getMagicType() == 2) {
			AchievementUtil.magic2(instPlayerId, syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);		
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	
	
}
