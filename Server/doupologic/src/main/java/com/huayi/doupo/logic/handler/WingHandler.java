package com.huayi.doupo.logic.handler;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictTitleDetail;
import com.huayi.doupo.base.model.DictWingAdvance;
import com.huayi.doupo.base.model.DictWingStrengthen;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.InstPlayerWing;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 翅膀处理类
 * @author mp
 * @date 2015-11-11 下午2:06:51
 */
public class WingHandler extends BaseHandler{
	
	/**
	 * 翅膀激活
	 * @author mp
	 * @date 2015-11-12 上午10:07:21
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({"unchecked" })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wingActivity(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int wingId = (Integer)msgMap.get("wingId");//欲合成的翅膀字典Id
		
		//验证参数有效范围
		if (wingId < 1 || wingId > DictList.dictWingList.size()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证相应碎片是否足够
		List<DictWingAdvance> wingAdvanceList = (List<DictWingAdvance>)DictMapList.dictWingAdvanceMap.get(wingId);
		DictWingAdvance wingAdvanceObj = null;
		for (DictWingAdvance obj : wingAdvanceList) {
			if (obj.getStarNum() == 0) {
				wingAdvanceObj = obj;
				break;
			}
		}
		
		//验证碎片是否充足
		String nextStarNumConds = wingAdvanceObj.getNextStarNumConds();
		int wingChipThingId = ConvertUtil.toInt(nextStarNumConds.split("_")[1]);
		int needNum = ConvertUtil.toInt(nextStarNumConds.split("_")[2]);
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + wingChipThingId, instPlayerId);
		if (instPlayerThingList.size() <= 0 || instPlayerThingList.get(0).getNum() < needNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLootHavaChip);
			return;
		}
		
		//消耗碎片
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThingList.get(0), needNum, syncMsgData, msgMap);
		
		//获得翅膀
		ThingUtil.groupThing(instPlayer, StaticTableType.DictWing, wingId, 1, syncMsgData, msgMap);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 翅膀佩戴
	 * @author mp
	 * @date 2015-11-12 上午10:08:37
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wingPutOnOrExchanger(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerWingId = (Integer)msgMap.get("instPlayerWingId");//翅膀实例Id
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");//卡牌实例Id
		InstPlayerWing instPlayerWing = getInstPlayerWingDAL().getModel(instPlayerWingId, instPlayerId);
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);

		//验证翅膀和卡牌是否存在
		if (instPlayerWing == null || instPlayerCard == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerWing.getInstPlayerId() != instPlayerId || instPlayerCard.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证此翅膀是否已在其他卡牌身上
		if (instPlayerWing.getInstCardId() != 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_otherCard);
			return;
		}
		
		//验证佩戴卡牌是否达到指定称号-暂定大斗师
		DictTitleDetail titleDetail = DictMap.dictTitleDetailMap.get(instPlayerCard.getTitleDetailId() + "");
		int playerTitleId = titleDetail.getTitleId();
		int needTitleId = DictMapUtil.getSysConfigIntValue(StaticSysConfig.wingTitleId);
		if (playerTitleId < needTitleId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_upTitle);
			return;
		}
		
		//验证当前卡牌是否有翅膀,如果有先卸下
		List<InstPlayerWing> instPlayerWingList = getInstPlayerWingDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
		if (instPlayerWingList.size() > 0) {
			InstPlayerWing instPlayerWingObj = instPlayerWingList.get(0);
			instPlayerWingObj.setInstCardId(0);
			getInstPlayerWingDAL().update(instPlayerWingObj, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerWingObj, instPlayerWingObj.getId(), instPlayerWingObj.getResult(),syncMsgData);
		}
		
		//翅膀上阵
		instPlayerWing.setInstCardId(instPlayerCardId);
		getInstPlayerWingDAL().update(instPlayerWing, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerWing, instPlayerWing.getId(), instPlayerWing.getResult(),syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 翅膀卸下
	 * @author mp
	 * @date 2015-11-12 上午10:09:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wingPutOff(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerWingId = (Integer)msgMap.get("instPlayerWingId");//翅膀实例Id
		InstPlayerWing instPlayerWing = getInstPlayerWingDAL().getModel(instPlayerWingId, instPlayerId);
		
		//验证翅膀是否存在
		if (instPlayerWing == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerWing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证翅膀是否在卡牌身上
		if (instPlayerWing.getInstCardId() == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_noInCard);
			return;
		}
		
		//卸下
		instPlayerWing.setInstCardId(0);
		getInstPlayerWingDAL().update(instPlayerWing, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerWing, instPlayerWing.getId(), instPlayerWing.getResult(),syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 翅膀转换
	 * @author mp
	 * @date 2015-11-12 上午10:09:56
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wingConvert(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int wingId = (Integer)msgMap.get("wingId");//欲转换成哪种翅膀Id(翅膀字典Id)
		int instPlayerWingId = (Integer)msgMap.get("instPlayerWingId");//将当前哪个翅膀来转化 (翅膀实例Id)
		InstPlayerWing instPlayerWing = getInstPlayerWingDAL().getModel(instPlayerWingId, instPlayerId);
		
		//验证翅膀合理范围
		if (wingId < 1 || wingId > DictList.dictWingList.size()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证翅膀是否存在
		if (instPlayerWing == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerWing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证两种翅膀属性是否一致
		if (instPlayerWing.getWingId() == wingId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_common);
			return;
		}
		
		//验证元宝数
		int needGold = 0;
		List<DictWingAdvance> wingAdvanceList = (List<DictWingAdvance>)DictMapList.dictWingAdvanceMap.get(wingId);
		for (DictWingAdvance obj : wingAdvanceList) {
			if (obj.getStarNum() == instPlayerWing.getStarNum()) {
				needGold = obj.getConvertGold();
				break;
			}
		}
		if (instPlayer.getGold() < needGold) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		
		//扣除元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, needGold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(),syncMsgData);
		
		//转换翅膀
		instPlayerWing.setWingId(wingId);
		getInstPlayerWingDAL().update(instPlayerWing, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerWing, instPlayerWing.getId(), instPlayerWing.getResult(),syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 翅膀进阶
	 * @author mp
	 * @date 2015-11-12 上午10:10:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wingAdvance(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerWingId = (Integer)msgMap.get("instPlayerWingId");//需要进阶的翅膀 (翅膀实例Id)
		InstPlayerWing instPlayerWing = getInstPlayerWingDAL().getModel(instPlayerWingId, instPlayerId);
		
		//验证翅膀是否存在
		if (instPlayerWing == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerWing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		int wingId = instPlayerWing.getWingId();
		List<DictWingAdvance> wingAdvanceList = (List<DictWingAdvance>)DictMapList.dictWingAdvanceMap.get(wingId);
		
		//验证当前是否已达到最大星数
		int maxStarNum = wingAdvanceList.get(wingAdvanceList.size() - 1).getStarNum();
		if (instPlayerWing.getStarNum() >= maxStarNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_maxStarNum);
			return;
		}
		
		//获取消耗材料
		DictWingAdvance wingAdvanceObj = null;
		for (DictWingAdvance obj : wingAdvanceList) {
			if (obj.getStarNum() == instPlayerWing.getStarNum()) {
				wingAdvanceObj = obj;
				break;
			}
		}
		int wingThingId = ConvertUtil.toInt(wingAdvanceObj.getNextStarNumConds().split("_")[1]);
		int needNum = ConvertUtil.toInt(wingAdvanceObj.getNextStarNumConds().split("_")[2]);
		
		//检查碎片是否足够
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + wingThingId, instPlayerId);
		if (instPlayerThingList.size() <= 0 || instPlayerThingList.get(0).getNum() < needNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLootHavaChip);
			return;
		}
		
		//消耗碎片
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThingList.get(0), needNum, syncMsgData, msgMap);
		
		//处理星数
		instPlayerWing.setStarNum(instPlayerWing.getStarNum() + 1);
		getInstPlayerWingDAL().update(instPlayerWing, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerWing, instPlayerWing.getId(), instPlayerWing.getResult(),syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 翅膀强化
	 * @author mp
	 * @date 2015-11-12 上午10:11:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({"unchecked" })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wingStronger(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerWingId = (Integer)msgMap.get("instPlayerWingId");//需要强化的翅膀 (翅膀实例Id)
		InstPlayerWing instPlayerWing = getInstPlayerWingDAL().getModel(instPlayerWingId, instPlayerId);
		
		//验证翅膀是否存在
		if (instPlayerWing == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerWing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//获取强化材料及其数量
		DictWingStrengthen wingStrengthenObj = null;
		List<DictWingStrengthen> wingStrengthenList = (List<DictWingStrengthen>)DictMapList.dictWingStrengthenMap.get(instPlayerWing.getWingId());
		for (DictWingStrengthen obj : wingStrengthenList) {
			if (obj.getLevel() == instPlayerWing.getLevel()) {
				wingStrengthenObj = obj;
				break;
			}
		}
		int wingStrogerStoneThingId = ConvertUtil.toInt(wingStrengthenObj.getNextLevelConds().split("_")[1]);
		int needNum = ConvertUtil.toInt(wingStrengthenObj.getNextLevelConds().split("_")[2]);
		
		//验证是否已达到最大等级
		if (instPlayerWing.getLevel() >= wingStrengthenList.get(wingStrengthenList.size() - 1).getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_maxStrognerLevel);
			return;
		}
		
		//验证材料是否充足
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + wingStrogerStoneThingId, instPlayerId);
		if (instPlayerThingList.size() <= 0 || instPlayerThingList.get(0).getNum() < needNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_strongBoneNotEnough);
			return;
		}
		
		//消耗碎片
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThingList.get(0), needNum, syncMsgData, msgMap);
		
		//提升等级
		instPlayerWing.setLevel(instPlayerWing.getLevel() + 1);
		getInstPlayerWingDAL().update(instPlayerWing, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerWing, instPlayerWing.getId(), instPlayerWing.getResult(),syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 翅膀出售
	 * @author mp
	 * @date 2015-11-12 上午10:11:28
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wingSell(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		String instPlayerWingIdList = (String)msgMap.get("instPlayerWingIdList");//需要出售的翅膀 (翅膀实例Id),多个用分号分开,单次最多30个
		
		//验证是否超过最大数量
		if (instPlayerWingIdList.split(";").length > 30) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_upSellNum);
			return;
		}
		
		int canSellCopper = 0;
		for (String instPlayerWingIdStr : instPlayerWingIdList.split(";")) {
			
			int instPlayerWingId = ConvertUtil.toInt(instPlayerWingIdStr);
			InstPlayerWing instPlayerWing = getInstPlayerWingDAL().getModel(instPlayerWingId, instPlayerId);
			
			//是否存在
			if (instPlayerWing == null) {
				continue;
			}
			
			//是否一致
			if (instPlayerWing.getInstPlayerId() != instPlayerId) {
				continue;
			}
			
			//是否在卡牌身上
			if (instPlayerWing.getInstCardId() != 0) {
				continue;
			}
			
			//计算银币
			int silver = 0;
			List<DictWingAdvance> wingAdvanceList = (List<DictWingAdvance>)DictMapList.dictWingAdvanceMap.get(instPlayerWing.getWingId());
			for (DictWingAdvance obj : wingAdvanceList) {
				if (obj.getStarNum() == instPlayerWing.getStarNum()) {
					silver = obj.getSellSilver();
					break;
				}
			}
			canSellCopper += silver;
			
			//删除翅膀
			getInstPlayerWingDAL().deleteById(instPlayerWing.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerWing, instPlayerWing.getId(), "", syncMsgData);
		}
		
		//增加银币
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + canSellCopper) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 翅膀一键强化
	 * @author mp
	 * @date 2015-11-17 下午1:58:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void wingOneKeyStronger (Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerWingId = (Integer)msgMap.get("instPlayerWingId");//需要一键强化的翅膀 (翅膀实例Id)
		InstPlayerWing instPlayerWing = getInstPlayerWingDAL().getModel(instPlayerWingId, instPlayerId);
		
		//验证翅膀是否存在
		if (instPlayerWing == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instPlayerWing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//获取强化材料及其数量
		DictWingStrengthen wingStrengthenObj = null;
		List<DictWingStrengthen> wingStrengthenList = (List<DictWingStrengthen>)DictMapList.dictWingStrengthenMap.get(instPlayerWing.getWingId());
		for (DictWingStrengthen obj : wingStrengthenList) {
			if (obj.getLevel() == instPlayerWing.getLevel()) {
				wingStrengthenObj = obj;
				break;
			}
		}
		int wingStrogerStoneThingId = ConvertUtil.toInt(wingStrengthenObj.getNextLevelConds().split("_")[1]);
		int maxStrongerLevel = wingStrengthenList.get(wingStrengthenList.size() - 1).getLevel();
		
		//验证是否已达到最大等级
		if (instPlayerWing.getLevel() >= maxStrongerLevel) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_maxStrognerLevel);
			return;
		}
		
		//计算当前拥有强化石个数
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + wingStrogerStoneThingId, instPlayerId);
		if (instPlayerThingList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_strongBoneNotEnough);
			return;
		}
		int ownStrongerStoneNum = instPlayerThingList.get(0).getNum();
		
		//根据当前等级和拥有的强化石数量算出可升到多少级,和消耗了多少强化石
		int afterLevel = instPlayerWing.getLevel();
		int needNum = 0;
		for (DictWingStrengthen obj : wingStrengthenList) {
			if (obj.getLevel() >= instPlayerWing.getLevel() && afterLevel < maxStrongerLevel) {
				int currLevelNeedNum = ConvertUtil.toInt(obj.getNextLevelConds().split("_")[2]);
				if (ownStrongerStoneNum >= currLevelNeedNum) {
					needNum += currLevelNeedNum;
					ownStrongerStoneNum -= currLevelNeedNum;
					afterLevel = obj.getLevel() + 1;
				} else {
					break;
				}
			}
		}
		
		//验证是否能够升一级
		if (instPlayerWing.getLevel() == afterLevel) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_wing_strongBoneNotEnough);
			return;
		}
		
		//消耗强化石
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThingList.get(0), needNum, syncMsgData, msgMap);
		
		//提升等级
		instPlayerWing.setLevel(afterLevel);
		getInstPlayerWingDAL().update(instPlayerWing, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerWing, instPlayerWing.getId(), instPlayerWing.getResult(),syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
}
