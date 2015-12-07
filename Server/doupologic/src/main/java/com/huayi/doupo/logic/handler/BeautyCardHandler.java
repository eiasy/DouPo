package com.huayi.doupo.logic.handler;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictBeautyCard;
import com.huayi.doupo.base.model.DictBeautyCardExp;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBarrier;
import com.huayi.doupo.base.model.InstPlayerBeautyCard;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class BeautyCardHandler extends BaseHandler{
	
	/**
	 * 示爱(赠送亲密度)
	 * @author hzw
	 * @date 2015-3-17上午11:04:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void courtship(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int beautyCardId = (Integer)msgMap.get("beautyCardId");//美人Id
		int instPlayerBeautyCardId = (Integer)msgMap.get("instPlayerBeautyCardId");//美人实例Id
		int instPlayerThingId = (Integer)msgMap.get("instPlayerThingId");//物品实例Id
		int type = (Integer)msgMap.get("type");//1-一键赠送  2-单个赠送
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerBeautyCard instPlayerBeautyCard = null;
		if(instPlayerBeautyCardId != 0){
			instPlayerBeautyCard = getInstPlayerBeautyCardDAL().getModel(instPlayerBeautyCardId, instPlayerId);
			//验证玩家是否一致
			if (instPlayerBeautyCard.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
		}
		if(instPlayerBeautyCard == null){
			DictBeautyCard dictBeautyCard = DictMap.dictBeautyCardMap.get(beautyCardId + "");
			int barrierId = dictBeautyCard.getUnblock();
			List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + barrierId, instPlayerId);
			if(instPlayerBarrierList.size() <= 0 || instPlayerBarrierList.get(0).getBarrierLevel() == 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unBlock);
				return;
			}
			String conditions = dictBeautyCard.getConditions();
			String str[] = StringUtil.split(conditions, "_");
			if(ConvertUtil.toInt(str[0]) == StaticTableType.DictPlayerBaseProp){
				if(ConvertUtil.toInt(str[1]) == StaticPlayerBaseProp.level){
					if(instPlayer.getLevel() < ConvertUtil.toInt(str[2])){
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unConquer);
						return;
					}
				}
			}
			if(ConvertUtil.toInt(str[0]) == StaticTableType.DictBarrier){
				List<InstPlayerBarrier> instPlayerBarrierList2 = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + ConvertUtil.toInt(str[1]), instPlayerId);
				if(instPlayerBarrierList2.size() <= 0 || instPlayerBarrierList2.get(0).getBarrierLevel() < ConvertUtil.toInt(str[2])){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unBlock);
					return;
				}
			}
		}
		
		int num = 1;
		InstPlayerThing instPlayerThing = getInstPlayerThingDAL().getModel(instPlayerThingId, instPlayerId);
		if (instPlayerThing == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "无此物品,登录重试");
			return;
		}
		//验证玩家是否一致
		if (instPlayerThing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(type == 1){
			num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.courtshipNum);
		}
		if(instPlayerThing.getNum() < num){
			num = instPlayerThing.getNum();
		}
		DictThing dictThing = DictMap.dictThingMap.get(instPlayerThing.getThingId() + "");
		int beautyCardExpId = 1;
		int exp = 0;
		if(instPlayerBeautyCard != null){
			beautyCardExpId = instPlayerBeautyCard.getBeautyCardExpId();
			exp += instPlayerBeautyCard.getExp();
		}
		
		float pr = 0.0f; //用来记录临时概率
		for(int i = 1; i <= num; i++){
			DictBeautyCardExp dictBeautyCardExp = DictMap.dictBeautyCardExpMap.get(beautyCardExpId + "");
			exp += dictThing.getValue();
			if(dictBeautyCardExp.getVeryLow() <= exp && exp < dictBeautyCardExp.getLow()){
				pr = dictBeautyCardExp.getVeryLowPr() + (exp % DictMapUtil.getSysConfigIntValue(StaticSysConfig.beautyPrAdd) - 1) * dictBeautyCardExp.getVeryLowPrAdd();
			}else if(dictBeautyCardExp.getLow() <= exp && exp < dictBeautyCardExp.getCentre()){
				pr = dictBeautyCardExp.getLowPr() + (exp % DictMapUtil.getSysConfigIntValue(StaticSysConfig.beautyPrAdd) - 1) * dictBeautyCardExp.getLowPrAdd();
			}else if(dictBeautyCardExp.getCentre() <= exp && exp < dictBeautyCardExp.getTall()){
				pr = dictBeautyCardExp.getCentrePr() + (exp % DictMapUtil.getSysConfigIntValue(StaticSysConfig.beautyPrAdd) - 1) * dictBeautyCardExp.getCentrePrAdd();
			}else if(dictBeautyCardExp.getTall() <= exp){
				pr = dictBeautyCardExp.getTallPr() + (exp % DictMapUtil.getSysConfigIntValue(StaticSysConfig.beautyPrAdd) - 1) * dictBeautyCardExp.getTallPrAdd();
			}
			if(RandomUtil.getRandomFloat() <= pr){
				if(dictBeautyCardExp.getExp() == 0){
					break;
				}
				beautyCardExpId ++;
				if(exp >= dictBeautyCardExp.getExp()){
					exp = exp - dictBeautyCardExp.getExp();
				}else{
					exp = 0;
				}
			}
		}
		
		//等级,经验计算
		Map<String, Integer> retMap = FormulaUtil.calcBeautyCard(beautyCardExpId, exp);
		beautyCardExpId = retMap.get("beautyCardExpId");
		exp = retMap.get("exp");
		
		if(instPlayerBeautyCard == null){
			instPlayerBeautyCard = new InstPlayerBeautyCard();
			instPlayerBeautyCard.setInstPlayerId(instPlayerId);
			instPlayerBeautyCard.setBeautyCardId(beautyCardId);
			instPlayerBeautyCard.setBeautyCardExpId(beautyCardExpId);
			instPlayerBeautyCard.setExp(exp);
			instPlayerBeautyCard.setPr(0);
			getInstPlayerBeautyCardDAL().add(instPlayerBeautyCard, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerBeautyCard, instPlayerBeautyCard.getId(), instPlayerBeautyCard.getResult(), syncMsgData);
		}else{
			instPlayerBeautyCard.setBeautyCardExpId(beautyCardExpId);
			instPlayerBeautyCard.setExp(exp);
			instPlayerBeautyCard.setPr(0);
			getInstPlayerBeautyCardDAL().update(instPlayerBeautyCard, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerBeautyCard, instPlayerBeautyCard.getId(), instPlayerBeautyCard.getResult(), syncMsgData);
		}
		
		//删除使用的物品
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, num, syncMsgData, msgMap);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 缠绵
	 * @author hzw
	 * @date 2015-3-17下午3:48:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void linger(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int beautyCardId = (Integer)msgMap.get("beautyCardId");//美人Id
		int instPlayerBeautyCardId = (Integer)msgMap.get("instPlayerBeautyCardId");//美人实例Id
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerBeautyCard instPlayerBeautyCard = null;
		if(instPlayerBeautyCardId != 0){
			instPlayerBeautyCard = getInstPlayerBeautyCardDAL().getModel(instPlayerBeautyCardId, instPlayerId);
			//验证玩家是否一致
			if (instPlayerBeautyCard.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
		}
		String beautyCardTime = instPlayer.getBeautyCardTime();
		if(beautyCardTime != null && !beautyCardTime.equals("") && DateUtil.isSameDay(beautyCardTime, DateUtil.getCurrTime(), DateFormat.YMDHMS)){
			//验证当天是否已经缠绵过
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLinger);
			return;
		}
		if(instPlayerBeautyCard == null){
			DictBeautyCard dictBeautyCard = DictMap.dictBeautyCardMap.get(beautyCardId + "");
			int barrierId = dictBeautyCard.getUnblock();
			List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + barrierId, instPlayerId);
			if(instPlayerBarrierList.size() <= 0 || instPlayerBarrierList.get(0).getBarrierLevel() == 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unBlock);
				return;
			}
			String conditions = dictBeautyCard.getConditions();
			String str[] = StringUtil.split(conditions, "_");
			if(ConvertUtil.toInt(str[0]) == StaticTableType.DictPlayerBaseProp){
				if(ConvertUtil.toInt(str[1]) == StaticPlayerBaseProp.level){
					if(instPlayer.getLevel() < ConvertUtil.toInt(str[2])){
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unConquer);
						return;
					}
				}
			}
			if(ConvertUtil.toInt(str[0]) == StaticTableType.DictBarrier){
				List<InstPlayerBarrier> instPlayerBarrierList2 = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + ConvertUtil.toInt(str[1]), instPlayerId);
				if(instPlayerBarrierList2.size() <= 0 || instPlayerBarrierList2.get(0).getBarrierLevel() < ConvertUtil.toInt(str[2])){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unBlock);
					return;
				}
			}
		}
		
		int beautyCardExpId = 1;
		int exp = DictMapUtil.getSysConfigIntValue(StaticSysConfig.linger);
		if(instPlayerBeautyCard != null){
			beautyCardExpId = instPlayerBeautyCard.getBeautyCardExpId();
			exp += instPlayerBeautyCard.getExp();
		}
		
		float pr = 0.0f; //用来记录临时概率
		DictBeautyCardExp dictBeautyCardExp = DictMap.dictBeautyCardExpMap.get(beautyCardExpId + "");
		if(dictBeautyCardExp.getExp() > 0){
			if(dictBeautyCardExp.getVeryLow() <= exp && exp < dictBeautyCardExp.getLow()){
				pr = dictBeautyCardExp.getVeryLowPr() + (exp % DictMapUtil.getSysConfigIntValue(StaticSysConfig.beautyPrAdd) - 1) * dictBeautyCardExp.getVeryLowPrAdd();
			}else if(dictBeautyCardExp.getLow() <= exp && exp < dictBeautyCardExp.getCentre()){
				pr = dictBeautyCardExp.getLowPr() + (exp % DictMapUtil.getSysConfigIntValue(StaticSysConfig.beautyPrAdd) - 1) * dictBeautyCardExp.getLowPrAdd();
			}else if(dictBeautyCardExp.getCentre() <= exp && exp < dictBeautyCardExp.getTall()){
				pr = dictBeautyCardExp.getCentrePr() + (exp % DictMapUtil.getSysConfigIntValue(StaticSysConfig.beautyPrAdd) - 1) * dictBeautyCardExp.getCentrePrAdd();
			}else if(dictBeautyCardExp.getTall() <= exp){
				pr = dictBeautyCardExp.getTallPr() + (exp % DictMapUtil.getSysConfigIntValue(StaticSysConfig.beautyPrAdd) - 1) * dictBeautyCardExp.getTallPrAdd();
			}
			if(RandomUtil.getRandomFloat() <= pr){
				beautyCardExpId ++;
				if(exp >= dictBeautyCardExp.getExp()){
					exp = exp - dictBeautyCardExp.getExp();
				}else{
					exp = 0;
				}
			}
		}
		
		
		//等级,经验计算
		Map<String, Integer> retMap = FormulaUtil.calcBeautyCard(beautyCardExpId, exp);
		beautyCardExpId = retMap.get("beautyCardExpId");
		exp = retMap.get("exp");
		
		if(instPlayerBeautyCard == null){
			instPlayerBeautyCard = new InstPlayerBeautyCard();
			instPlayerBeautyCard.setInstPlayerId(instPlayerId);
			instPlayerBeautyCard.setBeautyCardId(beautyCardId);
			instPlayerBeautyCard.setBeautyCardExpId(beautyCardExpId);
			instPlayerBeautyCard.setExp(exp);
			instPlayerBeautyCard.setPr(0);
			getInstPlayerBeautyCardDAL().add(instPlayerBeautyCard, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerBeautyCard, instPlayerBeautyCard.getId(), instPlayerBeautyCard.getResult(), syncMsgData);
		}else{
			instPlayerBeautyCard.setBeautyCardExpId(beautyCardExpId);
			instPlayerBeautyCard.setExp(exp);
			instPlayerBeautyCard.setPr(0);
			getInstPlayerBeautyCardDAL().update(instPlayerBeautyCard, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerBeautyCard, instPlayerBeautyCard.getId(), instPlayerBeautyCard.getResult(), syncMsgData);
		}
		
		instPlayer.setBeautyCardTime(DateUtil.getCurrTime());
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
	
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 征服
	 * @author hzw
	 * @date 2015-3-17下午3:50:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void conquer(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int beautyCardId = (Integer)msgMap.get("beautyCardId");//美人Id
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		DictBeautyCard dictBeautyCard = DictMap.dictBeautyCardMap.get(beautyCardId + "");
		String conditions = dictBeautyCard.getConditions();
		String str[] = StringUtil.split(conditions, "_");
		if(ConvertUtil.toInt(str[0]) == StaticTableType.DictPlayerBaseProp){
			if(ConvertUtil.toInt(str[1]) == StaticPlayerBaseProp.copper){
				if(ConvertUtil.toInt(instPlayer.getCopper()) < ConvertUtil.toInt(str[2])){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
					return;
				}
			}
		}
		
		//处理金币
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - ConvertUtil.toInt(str[2])) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//添加美人实例数据
		InstPlayerBeautyCard instPlayerBeautyCard = new InstPlayerBeautyCard();
		instPlayerBeautyCard.setInstPlayerId(instPlayerId);
		instPlayerBeautyCard.setBeautyCardId(beautyCardId);
		instPlayerBeautyCard.setBeautyCardExpId(1);
		instPlayerBeautyCard.setExp(0);
		instPlayerBeautyCard.setPr(0);
		getInstPlayerBeautyCardDAL().add(instPlayerBeautyCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerBeautyCard, instPlayerBeautyCard.getId(), instPlayerBeautyCard.getResult(), syncMsgData);
	
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

}
