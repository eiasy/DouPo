package com.huayi.doupo.logic.handler;

import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictAcupointNode;
import com.huayi.doupo.base.model.DictKungFu;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerKungFu;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.KungFuUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 功法处理类
 * @author hzw
 * @date 2014-7-16下午2:51:37
 */
public class KungFuHandler extends BaseHandler{

	/**
	 * 添加功法
	 * @author hzw
	 * @date 2014-7-16下午3:54:20
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addKungFu(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		int instPlayerKungFuId = (Integer)msgMap.get("instPlayerKungFuId");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(instPlayerKungFuId, instPlayerId);
		if(instPlayerId != instPlayerKungFu.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		instPlayerCard.setInstPlayerKungFuId(instPlayerKungFuId);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		instPlayerKungFu.setInstPlayerCardId(instPlayerCardId);
		getInstPlayerKungFuDAL().update(instPlayerKungFu, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerKungFu, instPlayerKungFu.getId(), instPlayerKungFu.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 运功
	 * @author hzw
	 * @date 2014-7-16下午4:15:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addNode(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerKungFuId = (Integer)msgMap.get("instPlayerKungFuId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(instPlayerKungFuId, instPlayerId);
		if(instPlayerId != instPlayerKungFu.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerKungFu.getKungFuTierAddId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_maxKungFu);
			return;
		}
		DictAcupointNode dictAcupointNode = DictMap.dictAcupointNodeMap.get(instPlayerKungFu.getAcupointNodeId() + "");
		int culture = dictAcupointNode.getCulture();
		if(instPlayer.getCulture() < culture){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCultureNum);
			return;
		}
		
		//更新玩家功法实例数据
		Map<String, String> retMap = FormulaUtil.calcAcupoint(instPlayerKungFu, culture);
		int acupointNodeId = ConvertUtil.toInt(retMap.get("acupointNodeId"));
		int kungFuTierAddId = ConvertUtil.toInt(retMap.get("kungFuTierAddId"));
		culture = ConvertUtil.toInt(retMap.get("culture"));
		String acupointNodes = retMap.get("acupointNodes");
		instPlayerKungFu.setAcupointNodeId(acupointNodeId);
		if(kungFuTierAddId != instPlayerKungFu.getKungFuTierAddId()){
			instPlayerKungFu.setKungFuTierAddId(kungFuTierAddId);
			if(acupointNodes != null && !acupointNodes.equals("")){
				instPlayerKungFu.setAcupointNodes(acupointNodes.substring(0, acupointNodes.length() - 1));
			}
		}
		getInstPlayerKungFuDAL().update(instPlayerKungFu, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerKungFu, instPlayerKungFu.getId(), instPlayerKungFu.getResult(), syncMsgData);
		
		//处理消耗的修为
		instPlayer.setCulture(instPlayer.getCulture() - culture);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 一键升满
	 * @author hzw
	 * @date 2014-7-18上午10:46:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addNodes(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerKungFuId = (Integer)msgMap.get("instPlayerKungFuId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(instPlayerKungFuId, instPlayerId);
		if(instPlayerId != instPlayerKungFu.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerKungFu.getKungFuTierAddId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_maxKungFu);
			return;
		}
		
		DictAcupointNode dictAcupointNode = DictMap.dictAcupointNodeMap.get(instPlayerKungFu.getAcupointNodeId() + "");
		int culture = dictAcupointNode.getCulture();
		if(instPlayer.getCulture() < culture){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCultureNum);
			return;
		}
		
		//更新玩家功法实例数据
		Map<String, String> retMap = FormulaUtil.calcAcupoint(instPlayerKungFu, instPlayer.getCulture());
		int acupointNodeId = ConvertUtil.toInt(retMap.get("acupointNodeId"));
		int kungFuTierAddId = ConvertUtil.toInt(retMap.get("kungFuTierAddId"));
		culture = ConvertUtil.toInt(retMap.get("culture"));
		String acupointNodes = retMap.get("acupointNodes");
		if(!acupointNodes.equals("")){
			acupointNodes = acupointNodes.substring(0, acupointNodes.length() - 1);
		}
		instPlayerKungFu.setAcupointNodeId(acupointNodeId);
		if(kungFuTierAddId != instPlayerKungFu.getKungFuTierAddId()){
			instPlayerKungFu.setKungFuTierAddId(kungFuTierAddId);
			instPlayerKungFu.setAcupointNodes(acupointNodes);
		}
		getInstPlayerKungFuDAL().update(instPlayerKungFu, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerKungFu, instPlayerKungFu.getId(), instPlayerKungFu.getResult(), syncMsgData);
		
		//处理消耗的修为
		instPlayer.setCulture(instPlayer.getCulture() - culture);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 更换功法
	 * @author hzw
	 * @date 2014-7-18上午11:42:55
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void convertKungFu(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		int instPlayerKungFuId = (Integer)msgMap.get("instPlayerKungFuId");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(instPlayerKungFuId, instPlayerId);
		if(instPlayerId != instPlayerKungFu.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerKungFu.getInstPlayerCardId() != 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_useKungFu);
			return;
		}
		
		//更新旧功法状态
		InstPlayerKungFu oldInstPlayerKungFu = getInstPlayerKungFuDAL().getModel(instPlayerCard.getInstPlayerKungFuId(), instPlayerId);
		oldInstPlayerKungFu.setInstPlayerCardId(0);
		getInstPlayerKungFuDAL().update(oldInstPlayerKungFu, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, oldInstPlayerKungFu, oldInstPlayerKungFu.getId(), oldInstPlayerKungFu.getResult(), syncMsgData);
		
		instPlayerCard.setInstPlayerKungFuId(instPlayerKungFuId);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		//更新新功法状态
		instPlayerKungFu.setInstPlayerCardId(instPlayerCardId);
		getInstPlayerKungFuDAL().update(instPlayerKungFu, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerKungFu, instPlayerKungFu.getId(), instPlayerKungFu.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 吞噬升级功法
	 * @author hzw
	 * @date 2014-7-21下午4:34:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteKungFu(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		int instPlayerKungFuId = 0;
		InstPlayerCard instPlayerCard = null;
		if(instPlayerId != 0){
			instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
			if(instPlayerId != instPlayerCard.getInstPlayerId()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			instPlayerKungFuId = instPlayerCard.getInstPlayerKungFuId();
		}else{
			instPlayerKungFuId = (Integer)msgMap.get("instPlayerKungFuId");
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(instPlayerKungFuId, instPlayerId);
		if(instPlayerId != instPlayerKungFu.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		String instPlayerKungFuIds = (String)msgMap.get("instPlayerKungFuIds");
		int exp = 0;
		int culture = 0;
		String[] instPlayerKungFuIdList = instPlayerKungFuIds.split(";");
		for(String str : instPlayerKungFuIdList){
			InstPlayerKungFu obj = getInstPlayerKungFuDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			DictKungFu dictKungFu = DictMap.dictKungFuMap.get(obj.getKungFuId() + "");
			if(obj.getInstPlayerId() == instPlayerId && obj.getInstPlayerCardId() == 0){
				exp += obj.getExp() + dictKungFu.getExp();
				culture += FormulaUtil.calcCulture(obj);
				getInstPlayerKungFuDAL().deleteById(obj.getId(), instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
			}
		}
		
		DictKungFu dictKungFu = DictMap.dictKungFuMap.get(instPlayerKungFu.getKungFuId() + "");
		if((instPlayerKungFu.getExp() + exp) >= dictKungFu.getExpSum()){
			culture += FormulaUtil.calcCulture(instPlayerKungFu);
			
			//删除旧功法
			getInstPlayerKungFuDAL().deleteById(instPlayerKungFu.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerKungFu, instPlayerKungFu.getId(), "", syncMsgData);
			
			//添加新功法
			InstPlayerKungFu newInstPlayerKungFu = KungFuUtil.addInstPlayerKungFu(instPlayerId, dictKungFu.getNextKungFu(), instPlayerCardId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, newInstPlayerKungFu, newInstPlayerKungFu.getId(), newInstPlayerKungFu.getResult(), syncMsgData);
			
			//更新卡牌的功法
			if(instPlayerId != 0){
				instPlayerCard.setInstPlayerKungFuId(newInstPlayerKungFu.getId());
				getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
			}
		}else{
			//更新新功法经验值
			instPlayerKungFu.setExp(instPlayerKungFu.getExp() + exp);
			getInstPlayerKungFuDAL().update(instPlayerKungFu, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerKungFu, instPlayerKungFu.getId(), instPlayerKungFu.getResult(), syncMsgData);
		}
		
		if(culture > 0){
			//处理玩家修为
			instPlayer.setCulture(instPlayer.getCulture() + culture);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 出售功法
	 * @author hzw
	 * @date 2014-9-2下午4:30:05
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sellKungFus(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		String instPlayerKungFus = (String)msgMap.get("instPlayerKungFus");
		String[] paramList = instPlayerKungFus.split(";");
		int sellCopper = 0;
		
		for(String str : paramList){
			InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			if(instPlayerId != instPlayerKungFu.getInstPlayerId()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if(instPlayerKungFu.getInstPlayerCardId() != 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_useKungFu);
				return;
			}
			sellCopper += FormulaUtil.sellKungFuCopper(instPlayerKungFu);
		}
		 
		//更新玩家银币
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + sellCopper) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//删除被出售卡牌
		for(String str : paramList){
			InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			getInstPlayerKungFuDAL().deleteById(instPlayerKungFu.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerKungFu, instPlayerKungFu.getId(), "", syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	

}
