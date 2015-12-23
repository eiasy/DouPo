package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.model.DictMagicrefining;
import com.huayi.doupo.base.model.dict.DictMapList;
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
	 * @update	by cui 2015/12/09
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

		int magicLevel = DictMap.dictMagicLevelMap.get(instPlayerMagic.getMagicLevelId() + "").getLevel();
		if(magicLevel >= instPlayer.getLevel()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_magicstreng_level);
			return;
		}

		int magicLevelMax = 40;	//默认上限
		if(instPlayerMagic.getAdvanceId() > 0){
			DictMagicrefining dictMagicrefining = DictMap.dictMagicrefiningMap.get(instPlayerMagic.getAdvanceId());
			magicLevelMax = dictMagicrefining.getMaxStrengthen();
		}
		if(magicLevel >= magicLevelMax){
			MessageUtil.sendFailMsg(channelId, msgMap, "等级已达到上限");
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

	/**
	 * 功法法宝精炼
	 * @author	cui
	 * @date	2015/12/09
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void magicAdvance(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}

		int instPlayerMagicId = (Integer)msgMap.get("instPlayerMagicId");
		InstPlayerMagic instPlayerMagic = getInstPlayerMagicDAL().getModel(instPlayerMagicId, instPlayerId);

		if(instPlayerMagic == null){
			MessageUtil.sendFailMsg(channelId, msgMap, "物品不存在");
			return;
		}

		//验证参数
		if (instPlayerMagic.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		//是否可精炼
		int quality = instPlayerMagic.getQuality();
		if(quality != 1 && quality != 2){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		int advanceId = instPlayerMagic.getAdvanceId();

		int starLevel = 0;
		if(advanceId > 0){
			DictMagicrefining dictMagicrefining = DictMap.dictMagicrefiningMap.get(advanceId + "");
			starLevel = dictMagicrefining.getStarLevel();
		}

		DictMagicrefining newMagicrefining = null;

		List<DictMagicrefining> dictMagicrefiningList = (List<DictMagicrefining>) DictMapList.dictMagicrefiningMap.get(instPlayerMagic.getMagicId());
		for (DictMagicrefining magicrefining : dictMagicrefiningList){
			if(magicrefining.getStarLevel() == starLevel + 1){
				newMagicrefining = magicrefining;
				break;
			}
		}

		if(newMagicrefining == null){
			MessageUtil.sendFailMsg(channelId, msgMap, "已经达到最高");
			return;
		}
		String options = newMagicrefining.getContions();
		if(options == null || options.equals("")){
			MessageUtil.sendFailMsg(channelId, msgMap, "已经达到最高");
			return;
		}
		ArrayList<HashMap<String,Object>> opts = new ArrayList();
		String[] goodsObj = options.split(";");
		for (String line : goodsObj){
			String[] array = line.split("_");
			int type = Integer.valueOf(array[0]);
			int id = Integer.valueOf(array[1]);
			int count = Integer.valueOf(array[2]);
			if(id == 0 || count <= 0){
				MessageUtil.sendFailMsg(channelId, msgMap, "非法数据");
				return;
			}
			if(type == 13){//只判断 消耗品是 功法法宝时
				//判断是否满足条件
				//没有被装备的功法法宝
				List<InstPlayerMagic> instPlayerMagicList = getInstPlayerMagicDAL().getList(" instPlayerId = " + instPlayerId + " and magicId = " + id + " and instCardId = 0",instPlayerId);
				if(instPlayerMagicList == null || instPlayerMagicList.size() < count){
					MessageUtil.sendFailMsg(channelId, msgMap, "精炼需要的材料数量不足");
					return;
				}
				HashMap<String,Object> lineHash = new HashMap();
				lineHash.put("magic",instPlayerMagicList);
				lineHash.put("count",count);
				opts.add(lineHash);
			}
		}

		if(opts.size() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, "精炼需要的材料数量不足");
			return;
		}
		MessageData syncMsgData = new MessageData();
		//消耗道具
		for (HashMap<String,Object> element : opts){
			List<InstPlayerMagic> playerMagics = (List<InstPlayerMagic>) element.get("magic");
			int count = (int) element.get("count");
			playerMagics = playerMagics.subList(0,count);
			for (InstPlayerMagic magic : playerMagics){
				getInstPlayerMagicDAL().deleteById(magic.getId(),instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, magic, magic.getId(), "", syncMsgData);
			}
		}

		//精炼功法法宝
		instPlayerMagic.setAdvanceId(newMagicrefining.getId());
		getInstPlayerMagicDAL().update(instPlayerMagic,instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerMagic, instPlayerMagic.getId(), instPlayerMagic.getResult(), syncMsgData);

//		//单个同步
//		MessageData msgData = OrgFrontMsgUtil.orgInstPlayerMagicDAL(instPlayerMagic);
//		syncMsgData.putMessageItem(instPlayerMagic.getId()+"", msgData.getMsgData());


		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
}
