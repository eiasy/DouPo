package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.logic.handler.util.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.huayi.doupo.base.model.DictActivityGroupGiveZiRule;
import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictGenerBoxThing;
import com.huayi.doupo.base.model.DictSpecialBoxThing;
import com.huayi.doupo.base.model.DictSpecialRule;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.InstEquipGem;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBagExpand;
import com.huayi.doupo.base.model.InstPlayerBoxCount;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.InstPlayerFire;
import com.huayi.doupo.base.model.InstPlayerGroup;
import com.huayi.doupo.base.model.InstPlayerMagic;
import com.huayi.doupo.base.model.InstPlayerResolveTemp;
import com.huayi.doupo.base.model.InstPlayerStore;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.InstPlayerTrain;
import com.huayi.doupo.base.model.InstPlayerTrainTemp;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticActivityHoliday;
import com.huayi.doupo.base.model.statics.StaticBagType;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.StaticThingType;
import com.huayi.doupo.base.model.statics.custom.CustomMarqueeType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.LogicLogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 物品处理类
 * 
 * @author mp
 * @date 2014-8-4 上午9:52:10
 */
public class ThingHandler extends BaseHandler {

	/**
	 * 分解-预览分解结果
	 * 
	 * @author mp
	 * @date 2014-7-28 上午11:28:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void preViewResolve(Map<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int resolveType = (Integer) msgMap.get("resolveType");// 分解类型: 装备-1, 卡牌-2, 异火-3 功法法宝-4
		String resolveList = (String) msgMap.get("resolveList");// 分解物品的Id列表,中间用;分开。例如：1;2;3;[最后的分号可以带]

		if (StringUtil.isEmpty(resolveList)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}

		// 分解装备
		if (resolveType == 1) {
			PlayerUtil.resolve(resolveType, resolveList, instPlayerId, "EQUIP", retMsgData);
			// 分解卡牌
		} else if (resolveType == 2) {
			PlayerUtil.resolve(resolveType, resolveList, instPlayerId, "CARD", retMsgData);
			// 分解异火
		} else if (resolveType == 3) {
			PlayerUtil.resolve(resolveType, resolveList, instPlayerId, "FIRE", retMsgData);
		}  else if (resolveType == 4) {
			// 分解功法法宝
			PlayerUtil.resolve(resolveType, resolveList, instPlayerId, "MAGIC", retMsgData);
		}

		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 确认分解
	 * @author mp
	 * @date 2014-7-28 上午11:29:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void makeSureResolve(Map<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instResolveId = (Integer) msgMap.get("instResolveId");// 分解预览的Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerResolveTemp instPlayerResolveTemp = getInstPlayerResolveTempDAL().getModel(instResolveId, instPlayerId);

		// 处理引导逻辑
		String step = (String) msgMap.get("step");// 等级用'_'区分; 关卡用'b'区分
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}

		// 验证参数是否有效
		if (instPlayerResolveTemp == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}

		// 验证玩家是否一致
		if (instPlayerResolveTemp.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		// 分解产生的洗练石,经验丹,魂源,火晶都属于道具背包,获取道具背包上限
		int extendBagContain = ThingUtil.getExtandBagNum(instPlayerId, StaticBagType.item);
		// 背包上限 = 背包初始容量 + 背包扩充的容量
		int thingBagUpLimit = DictMap.dictBagTypeMap.get(StaticBagType.item + "").getBagUpLimit() + extendBagContain;
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and bagTypeId = " + StaticBagType.item, instPlayerId);

		// 验证背包容量,不够返回
		if (instPlayerThingList.size() >= thingBagUpLimit && ThingUtil.haveNewThing(instPlayerResolveTemp.getResolveResult(), StaticBagType.item, instPlayerThingList)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_bagNotEnough);
			return;
		}

		// 验证铜币是否充足
		if (ConvertUtil.toInt(instPlayer.getCopper()) < instPlayerResolveTemp.getConsumCopper()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}

		// 验证分解结果是否有效[分解预览协议中的参数经过过滤后是否为空]
		if (StringUtil.isEmpty(instPlayerResolveTemp.getResolveList())) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noResolveThing);
			return;
		}

		// 将分解得到的物品放入背包中
		String thingList = instPlayerResolveTemp.getResolveResult();
		int getCopper = 0;
		int culture = 0;
		for (String thing : thingList.split(";")) {
			int tableType = ConvertUtil.toInt(thing.split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
			int tableValue = ConvertUtil.toInt(thing.split("_")[2]);

			// 计算铜币
			if (tableType == StaticTableType.DictPlayerBaseProp) {
				if (tableFieldId == StaticPlayerBaseProp.copper) {
					getCopper += tableValue;
				}
				if (tableFieldId == StaticPlayerBaseProp.culture) {
					culture += tableValue;
				}
				// 处理洗练石等物品
			} else if (tableType == StaticTableType.DictThing) {
				ThingUtil.addInstPlayerThing(instPlayerId, tableFieldId, tableValue, syncMsgData, msgMap);
			} else {
				ThingUtil.groupThing(instPlayer, tableType, tableFieldId, tableValue, syncMsgData, msgMap);
			}
		}

		// 最终得到的铜币 = 分解获得的铜币 - 分解消耗的铜币
		int finalCopper = getCopper - instPlayerResolveTemp.getConsumCopper();
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + finalCopper) + "");
		if (culture > 0) {
			instPlayer.setCulture(instPlayer.getCulture() + culture);
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 删除需分解的物品
		String fightSoulList = "";
		int resolveType = instPlayerResolveTemp.getResolveType();
		String resolveList = instPlayerResolveTemp.getResolveList();
		if (resolveType == 1) {
			for (String equipId : resolveList.split(";")) {
				int instPlayerEquipId = ConvertUtil.toInt(equipId);
//				getInstPlayerEquipDAL().deleteById(instPlayerEquipId, instPlayerId);
				//Update by cui @date 2015/12/09,删除装备时需要多部操作、所以封装到一个方法上
				EquipUtil.deletePlayerEquip(instPlayerEquipId, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, new InstPlayerEquip(), instPlayerEquipId, "", syncMsgData);
			}
		} else if (resolveType == 2) {
			for (String cardId : resolveList.split(";")) {
				int instPlayerCardId = ConvertUtil.toInt(cardId);
				InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
				//斗魂处理
				int instCardId = instPlayerCard.getId();
				fightSoulList += FightSoulUtil.dropFightSoulIfCardExsits(instPlayerId, instCardId, syncMsgData);
				CardUtil.deletePlayerCard(instPlayerId, instPlayerCard, syncMsgData);
				
				//修炼
				List<InstPlayerTrainTemp>  instPlayerTrainTempList = getInstPlayerTrainTempDAL().getList("instPlayerCardId = " + instPlayerCardId, instPlayerId);
				for(InstPlayerTrainTemp instPlayerTrainTemp : instPlayerTrainTempList){
					getInstPlayerTrainTempDAL().deleteById(instPlayerTrainTemp.getId(), 0);
				}
				List<InstPlayerTrain>  instPlayerTrainList = getInstPlayerTrainDAL().getList("instPlayerCardId = " + instPlayerCardId, instPlayerId);
				for(InstPlayerTrain instPlayerTrain : instPlayerTrainList){
					getInstPlayerTrainDAL().deleteById(instPlayerTrain.getId(), 0);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerTrain, instPlayerTrain.getId(), "", syncMsgData);
				}
				
			}
		} else if (resolveType == 3) {
			for (String fireId : resolveList.split(";")) {
				int instPlayerFireId = ConvertUtil.toInt(fireId);
				getInstPlayerFireDAL().deleteById(instPlayerFireId, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, new InstPlayerFire(), instPlayerFireId, "", syncMsgData);
			}
		} else if (resolveType == 4) {
			for (String magicId : resolveList.split(";")) {
				int instPlayerMagicId = ConvertUtil.toInt(magicId);
				//分解功法法宝
				InstPlayerMagic instPlayerMagic = getInstPlayerMagicDAL().getModel(instPlayerMagicId, instPlayerId);
				getInstPlayerMagicDAL().deleteById(instPlayerMagicId, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerMagic, instPlayerMagicId, "", syncMsgData);
			}
		}

		// 放入背包后,删除临时表数据
		List<InstPlayerResolveTemp> instPlayerResolveTempList = getInstPlayerResolveTempDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerResolveTemp obj : instPlayerResolveTempList) {
			getInstPlayerResolveTempDAL().deleteById(obj.getId(), instPlayerId);
		}

		String resolveSureLog  = "分解-分解确认：instPlayerId=" + instPlayerId + " 分解类型(1-装备2-卡牌)=" + resolveType + " 分解的实例id=" + resolveList + " 分解的到的物品=" + thingList + "斗魂相关=" + fightSoulList;
		LogUtil.info(resolveSureLog);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 物品出售
	 * 
	 * @author mp
	 * @date 2014-8-4 上午10:05:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sell(Map<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int buyNum = (Integer) msgMap.get("buyNum");// 出售个数
		int type = (Integer) msgMap.get("type");// 1-物品 2-装备 3-装备碎片4-出售功法法宝
		String sellIds = (String) msgMap.get("sellIds");// 出售Id列表, 用分号隔开
		int sumPrice = 0;
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		//防止传来的参数为负数或0
		if (buyNum <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		// 出售物品
		if (type == 1 || type == 3) {

			// 验证玩家是否一致
			InstPlayerThing instPlayerThing = getInstPlayerThingDAL().getModel(ConvertUtil.toInt(sellIds), instPlayerId);

			if (instPlayerThing == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noThing);
				return;
			}

			if (instPlayerThing.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}

			// 验证购买数量[购买数量不可以大于已有数量]
			int num = instPlayerThing.getNum();
			if (instPlayerThing.getThingId() == StaticThing.goldBox) {
				num += instPlayerThing.getLevel();
			}
			if (buyNum > num) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
				return;
			}

			// 计算可获得的总铜币
			sumPrice = DictMap.dictThingMap.get(instPlayerThing.getThingId() + "").getSellCopper() * buyNum;

			// 处理物品[含碎片]
			if (instPlayerThing.getThingId() == StaticThing.goldBox) {
				if (instPlayerThing.getLevel() > buyNum) {
					instPlayerThing.setLevel(instPlayerThing.getLevel() - buyNum);
					getInstPlayerThingDAL().update(instPlayerThing, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
				} else {
					int mod = buyNum - instPlayerThing.getLevel();
					instPlayerThing.setLevel(0);
					ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, mod, syncMsgData, msgMap);
				}
			} else {
				ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, buyNum, syncMsgData, msgMap);
			}

			// 出售装备
		} else if (type == 2) {
			List<InstPlayerEquip> sellList = new ArrayList<InstPlayerEquip>();
			for (String equipId : sellIds.split(";")) {

				int sellId = ConvertUtil.toInt(equipId);
				// 验证玩家是否一致
				InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(sellId, instPlayerId);
				sellList.add(instPlayerEquip);
				if (instPlayerEquip == null) {
					sellList.clear();
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noThing);
					return;
				}

				if (instPlayerEquip.getInstPlayerId() != instPlayerId) {
					continue;
				}

				// 验证是否镶嵌有魔核
				List<InstEquipGem> instEquipGemList = getInstEquipGemDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerEquipId = " + instPlayerEquip.getId(), instPlayerId);
				if (instEquipGemList.size() > 0) {
					sellList.clear();
					MessageUtil.sendFailMsg(channelId, msgMap, DictMap.dictEquipmentMap.get(instPlayerEquip.getEquipId() + "").getName() + StaticCnServer.fail_equipHaveCore);
					return;
				}

				// 计算价格
				sumPrice += (DictMap.dictEquipmentMap.get(instPlayerEquip.getEquipId() + "").getSellPrice() + FormulaUtil.calcStrengFeeByLevel(instPlayerEquip.getLevel(), instPlayerEquip.getEquipId())) * buyNum;

			}
			for(InstPlayerEquip instPlayerEquip:sellList){
				// 处理装备
//				getInstPlayerEquipDAL().deleteById(instPlayerEquip.getId(), instPlayerId);
				//Update by cui @date 2015/12/09,删除装备时需要多部操作、所以封装到一个方法上
				EquipUtil.deletePlayerEquip(instPlayerEquip.getId(), instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerEquip, instPlayerEquip.getId(), "", syncMsgData);

				// 删除装备镶嵌、洗练相关数据
				getInstPlayerWashDAL().deleteByWhere("instPlayerId = " + instPlayerId + " and instPlayerEquipId = " + instPlayerEquip.getId());

				// getInstEquipGemDAL().deleteByWhere("instPlayerId = "+instPlayerId+" and instPlayerEquipId = "+ instPlayerEquip.getId());

				// 验证洗练成就
				AchievementUtil.wash(instPlayerId, syncMsgData);

				// 验证镶嵌魔核成就
				// AchievementUtil.inlay(instPlayerId, syncMsgData);
			}
			// 出售功法法宝
		} else if (type == 4) {
			for (String cell : sellIds.split(";")) {
				int instPlayerMagicId = ConvertUtil.toInt(cell);
				InstPlayerMagic instPlayerMagic = getInstPlayerMagicDAL().getModel(instPlayerMagicId, instPlayerId);
				if (instPlayerMagic == null) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noThing);
					return;
				}
				if (instPlayerMagic.getInstPlayerId() != instPlayerId) {
					continue;
				}
				if (instPlayerMagic.getInstCardId() != 0) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noCanSell);
					return;
				}

				// 计算价格
				sumPrice += DictMap.dictMagicMap.get(instPlayerMagic.getMagicId() + "").getSellCopper() * buyNum;

				// 处理功法法宝
				getInstPlayerMagicDAL().deleteById(instPlayerMagicId, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerMagic, instPlayerMagic.getId(), "", syncMsgData);
			}
		}

		// 处理卖掉的铜币
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + sumPrice) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 获取商城列表
	 * 
	 * @author mp
	 * @date 2014-8-5 上午11:09:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getStoreData(Map<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		MessageData retMsgData = new MessageData();
		int type = (Integer) msgMap.get("type");// 商店类型
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		// 组织道具
		if (type == 1) {

			// 获取商城道具列表
			List<DictThing> itemList = ThingUtil.getStoreItems();

			// 根据玩家vip等级获取商店购买的道具列表
			Map<Integer, Integer> vipItemMap = ThingUtil.getItemsByVipLevel(instPlayer.getVipLevel());

			// 获取玩家当日的商店购买记录
			Map<Integer, String> todayBuyItemMap = ThingUtil.getTodayBuyItemMap(instPlayerId);

			ThingUtil.orgStoreItems(itemList, vipItemMap, todayBuyItemMap, retMsgData);

			// 组织魔核
		} else if (type == 2) {
			ThingUtil.orgStoreCores(retMsgData);

			// 组织vip礼包
		} else if (type == 3) {
			ThingUtil.orgVipGifts(instPlayerId, retMsgData);
		}
		
		float multipleExp = 1f;
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.storeDiscont)) {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.storeDiscont + "");
			multipleExp = activityHoliday.getMultiple();
		}
		retMsgData.putIntItem("multipleExp", (int)(multipleExp * 100));

		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 商城购买
	 * 
	 * @author mp
	 * @date 2014-8-4 上午10:06:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buy(Map<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int thingId = (Integer) msgMap.get("thingId");// 物品Id
		int type = (Integer) msgMap.get("type");// 类型 1-道具 2-魔核 3-vip礼包
		int num = (Integer) msgMap.get("num");// 购买数量 vip礼包传1
		DictThing thing = DictMap.dictThingMap.get(thingId + "");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int bagNum = 0;
		int usedBagNum = 0;
		int price = thing.getBuyGold() * num;

		//判断购买物品是否为可供购买的物品
		if (thing.getIsCanBuy() != 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_thingNoBuy);
			return;
		}
		
		// 验证上限-防止整数溢出
		int buyUpLimit = DictMapUtil.getSysConfigIntValue(StaticSysConfig.buyUpLimit);
		if (num > buyUpLimit) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyUpLimit + " " + buyUpLimit);
			return;
		}

		// 验证参数
		if (type != 1 && type != 2) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//防止玩家传入的购买个数为负数
		if (num <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}

		// 获取某背包类型的容量 [道具,礼包同属于道具 , 魔核属于魔核]
		bagNum = ThingUtil.getBagNum(instPlayerId, StaticBagType.item);

		// 当前已占用数量
		usedBagNum = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and bagTypeId = " + StaticBagType.item, instPlayerId).size();

		// 购买道具
		if (type == 1) {
			
			//验证玩家购买的物品是否属于1-道具
			if (thing.getBagTypeId() != StaticBagType.item) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
				return;
			}

			// 获取玩家当日的商店购买记录
			Map<Integer, String> todayBuyItemMap = ThingUtil.getTodayBuyItemMap(instPlayerId);

			// 根据玩家vip等级获取商店购买的道具列表
			Map<Integer, Integer> vipItemMap = ThingUtil.getItemsByVipLevel(instPlayer.getVipLevel());

			// 验证当日购买个数
			int canBuyNum = -1;
			if (vipItemMap.containsKey(thingId)) {

				// 对于vip限制每天购买个数的物品，每次购买数量只能是1
				// num = 1;

				int buyLimitNum = vipItemMap.get(thingId);
				int todayBuyNum = 0;
				if (todayBuyItemMap.containsKey(thingId)) {
					todayBuyNum = ConvertUtil.toInt(todayBuyItemMap.get(thingId).split("_")[0]);
				}

				canBuyNum = (buyLimitNum - todayBuyNum) < 0 ? 0 : (buyLimitNum - todayBuyNum);

				if ((canBuyNum - num) < 0) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_timesNull);
					return;
				}
			}

			// 计算价格
			if (thingId == StaticThing.vigorPill || thingId == StaticThing.energyPill) {
//				int tempNum = num;
				int buyNum = 0;
				price = 0;
				if (todayBuyItemMap.containsKey(thingId)) {
					buyNum = ConvertUtil.toInt(todayBuyItemMap.get(thingId).split("_")[0]);
				}
				
				int lootInitNum = buyNum + 1;
				int lootMaxNum = buyNum + num;
				for (int i = lootInitNum; i <= lootMaxNum; i++) {
					price += ThingUtil.getStoreBuyPrice(thingId, i);
				}
				
//				DictThingExtend dictThingExtend = DictMap.dictThingExtendMap.get(thingId + "");
//				String extend[] = dictThingExtend.getExtend().split(";");
//				for (String str : extend) {
//					int down = ConvertUtil.toInt(str.split("_")[0]);
//					int up = ConvertUtil.toInt(str.split("_")[1]);
//					int gold = ConvertUtil.toInt(str.split("_")[2]);
//					if (down <= (buyNum + tempNum) && (buyNum + tempNum) <= up) {
//						price += tempNum * gold;
//						buyNum += tempNum;
//						tempNum -= tempNum;
//					} else if ((buyNum + tempNum) >= up) {
//						price += (up - buyNum) * gold;
//						tempNum = buyNum + tempNum - up;
//						buyNum = up;
//					}
//					if (tempNum == 0) {
//						break;
//					}
//				}
			} else {
				price = DictMap.dictThingMap.get(thingId + "").getBuyGold() * num;
				if (todayBuyItemMap.containsKey(thingId)) {
					price = (price + (ConvertUtil.toInt(todayBuyItemMap.get(thingId).split("_")[1]) * thing.getStoreBuyGoldGrow()) * num);
				}
			}
		} else if (type == 2) {
			
			//验证是否为2-魔核
			if (thing.getBagTypeId() != StaticBagType.core) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
				return;
			}
			
			// 获取某背包类型的容量 [道具,礼包同属于道具 , 魔核属于魔核]
			bagNum = ThingUtil.getBagNum(instPlayerId, StaticBagType.core);

			// 当前已占用数量
			usedBagNum = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and bagTypeId = " + StaticBagType.core, instPlayerId).size();
		} else if (type == 3) {

			// 验证是否已经购买
			List<InstPlayerStore> instPlayerStoreList = getInstPlayerStoreDAL().getList("instPlayerId = " + instPlayerId + " and bagType = 0", instPlayerId);
			List<Integer> buyVipGiftIdList = Lists.newArrayList();
			for (InstPlayerStore instPlayerStore : instPlayerStoreList) {
				buyVipGiftIdList.add(instPlayerStore.getThingId());
			}
			if (buyVipGiftIdList.contains(thingId)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyRepeat);
				return;
			}

			// 验证vip等级
			if (instPlayer.getVipLevel() < thing.getLevel()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noUpVip);
				return;
			}

			// vip礼包购买个数只能是1,如果个数大于1，说明玩家作弊，金币照扣，以示惩罚
			num = 1;
		}

		// 验证格子数量
		if (usedBagNum >= bagNum && ThingUtil.haveNewThing(thingId, instPlayerId)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_bagNotEnough);
			return;
		}

		float multipleExp = 1f;
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.storeDiscont)) {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.storeDiscont + "");
			multipleExp = activityHoliday.getMultiple();
		}
		if (multipleExp != 1f) {
			price = ConvertUtil.toInt(price * multipleExp);
		}
		
		// 验证元宝是否足够
		if (price > instPlayer.getGold()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}

		// 处理价格-消耗元宝
		String logParams = thing.getName() + ";" + thing.getId() + ";" + num;
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, price, msgMap, logParams);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 处理物品
		ThingUtil.groupThing(instPlayer, StaticTableType.DictThing, thingId, num, syncMsgData, msgMap);
		// ThingUtil.addInstPlayerThing(instPlayerId, thingId, num, syncMsgData);

		// 处理商店购买记录,插入前对于道具, 魔核只保留今日和昨日数据,以便查询, vip礼包永久
		String beforeYesterday = DateUtil.getNumDayDate(-2);
		List<InstPlayerStore> todayBuyItemList = getInstPlayerStoreDAL().getList("instPlayerId = " + instPlayerId + " and bagType = " + StaticBagType.item + " and buyTime < '" + beforeYesterday + "'", 0);
		for (InstPlayerStore instPlayerStore : todayBuyItemList) {
			getInstPlayerStoreDAL().deleteById(instPlayerStore.getId(), instPlayerId);
		}

		// 添加记录
		ThingUtil.addPlayerStore(type, thing, instPlayerId, num, thingId);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 背包扩容
	 * 
	 * @author mp
	 * @date 2014-8-6 下午2:18:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bagExpand(Map<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int bagTypeId = (Integer) msgMap.get("bagTypeId");// 类型 1-道具 2-魔核
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		// 获取扩容次数
		int expandTimes = 0;
		List<InstPlayerBagExpand> instPlayerBagExpandList = getInstPlayerBagExpandDAL().getList("instPlayerId = " + instPlayerId + " and bagTypeId = " + bagTypeId, instPlayerId);
		if (instPlayerBagExpandList.size() > 0) {
			expandTimes = instPlayerBagExpandList.get(0).getExpandSumTimes();
		}

		// 计算扩容费用 初始费用 + 扩容次数 * 扩容额外增加的费用
		int expandFee = DictMapUtil.getSysConfigIntValue(StaticSysConfig.expandInitGold) + expandTimes * DictMapUtil.getSysConfigIntValue(StaticSysConfig.bagExpandGoldGrow);

		// 验证元宝是否充足
		if (instPlayer.getGold() < expandFee) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}

		// 扣除费用
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, expandFee, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 扩容背包
		ThingUtil.expandBag(instPlayerBagExpandList, bagTypeId, instPlayerId, expandFee, syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 装备-背包中宝石升级
	 * 
	 * @author mp
	 * @date 2014-7-2 上午10:52:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void packGemUpgrade(HashMap<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int nextLevelGemNum = (Integer) msgMap.get("nextLevelGemNum");// 想生成几个升级后的宝石
		int instPlayerThingId = (Integer) msgMap.get("instPlayerThingId");// 要升级的宝石实例Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerThing instPlayerThing = getInstPlayerThingDAL().getModel(instPlayerThingId, instPlayerId);

		//判断客户端传来的个数是否为负数或0
		if (nextLevelGemNum <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		// 验证玩家是否一致
		if (instPlayerId != instPlayerThing.getInstPlayerId()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		// 验证宝石数量是否充足
		int needNum = ConvertUtil.toInt(nextLevelGemNum * DictMapUtil.getSysConfigIntValue(StaticSysConfig.gemUpgradeNeedNum));
		if (instPlayerThing.getNum() < needNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_gemNum_notEnough);
			return;
		}

		// 验证银币是否充足
		int initUpgradeCopper = DictMapUtil.getSysConfigIntValue(StaticSysConfig.coreUpgradeCopper);
		int needCooper = initUpgradeCopper * nextLevelGemNum;
		if (ConvertUtil.toInt(instPlayer.getCopper()) < needCooper) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}

		// 验证是否已经达到最高等级
		if (instPlayerThing.getLevel() == 8) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_upLevel);
			return;
		}

		// 处理银币
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - needCooper) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 减去升级所需宝石数
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, needNum, syncMsgData, msgMap);

		// 验证是否有下一级宝石，如果有增加数量，如果没有插入
		DictThing thing = DictUtil.getNextLevelGemObj(instPlayerThing);// 获取下一级宝石对象
		ThingUtil.addInstPlayerThing(instPlayerId, thing.getId(), nextLevelGemNum, syncMsgData, msgMap);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 装备-装备身上宝石升级
	 * 
	 * @author mp
	 * @date 2014-7-2 下午5:07:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void equipGemUpgrade(HashMap<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instEquipGemId = (Integer) msgMap.get("instEquipGemId");// 装备镶嵌宝石Id
		InstEquipGem instEquipGem = getInstEquipGemDAL().getModel(instEquipGemId, instPlayerId);

		// 验证玩家是否一致
		if (instPlayerId != instEquipGem.getInstPlayerId()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		// 验证所需宝石是否充足
		int needNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.gemUpgradeNeedNum) - 1;// 自己算1个
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + instEquipGem.getThingId(), instPlayerId);
		if (instPlayerThingList.size() == 0 || instPlayerThingList.get(0).getNum() < needNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_gemNum_notEnough);
			return;
		}

		// 验证是否已经达到最高等级
		InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
		if (instPlayerThing.getLevel() == 6) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_upLevel);
			return;
		}
		
		//防止数量为负数
		if (instPlayerThing != null) {
			if (instPlayerThing.getNum() <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
				return ;
			}
		}

		// 记录日志
		try {
			int source = 0;// 来源-协议号
			String desc = "";// 来源描述
			if (msgMap != null) {
				source = (int) msgMap.get("header");
				desc = DictMap.sysMsgRuleMap.get(source + "").getName();
			}
			int thingId = instPlayerThing.getThingId();
			DictThing dictThing = DictMap.dictThingMap.get(thingId + "");
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|2|" + "" + "|" + "" + "|" + instPlayerThing.getThingId() + "|" + dictThing.getName() + "|" + instPlayerThing.getNum() + "|" + (instPlayerThing.getNum() - needNum) + "|" + needNum + "|" + source + "|" + desc;
			LogicLogUtil.info("things", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("道具/物品日志错误", e);
		}

		// 减去升级宝石消耗的宝石
		if (instPlayerThing.getNum() - needNum <= 0) {
			getInstPlayerThingDAL().deleteById(instPlayerThing.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerThing, instPlayerThing.getId(), "", syncMsgData);
		} else {
			instPlayerThing.setNum(instPlayerThing.getNum() - needNum);
			getInstPlayerThingDAL().update(instPlayerThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
		}

		// 将升级后的宝石放到装备上
		DictThing thing = DictUtil.getNextLevelGemObj(instPlayerThing);// 获取下一级宝石对象
		instEquipGem.setThingId(thing.getId());
		getInstEquipGemDAL().update(instEquipGem, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instEquipGem, instEquipGem.getId(), instEquipGem.getResult(), syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 魔核转换
	 * 
	 * @author mp
	 * @date 2014-8-6 下午5:00:38
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void coreConvert(HashMap<String, Object> msgMap, String channelId) throws Exception {

		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerThingId = (Integer) msgMap.get("instPlayerThingId");// 物品实例Id
		int fightPropId = (Integer) msgMap.get("fightPropId");// 转换成哪一种类型的魔核
		int convNum = (Integer) msgMap.get("convNum");// 转换数量
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerThing instPlayerThing = getInstPlayerThingDAL().getModel(instPlayerThingId, instPlayerId);

		if (instPlayerThing == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_magicCoreNumNotEnough);
			return;
		}
		
		//判断客户端传来的个数是否为负数或0
		if (convNum <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}

		// 验证玩家是否一致
		if (instPlayerThing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		// 计算转换所需价格
		DictThing thing = DictMap.dictThingMap.get(instPlayerThing.getThingId() + "");
		int convCopper = thing.getCoreConvCopper();
		int haveNum = instPlayerThing.getNum();
		int needCopper = convCopper * convNum;

		// 验证价格
		if (ConvertUtil.toInt(instPlayer.getCopper()) < needCopper) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}

		// 验证数量
		if (haveNum < convNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
			return;
		}

		// 处理金币
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - needCopper) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 处理转换的魔核
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, convNum, syncMsgData, msgMap);

		// 处理转换后的魔核
		int newThingId = 0;
		for (DictThing obj : DictList.dictThingList) {
			if (obj.getThingTypeId() == StaticThingType.core && obj.getLevel() == instPlayerThing.getLevel() && obj.getFightPropId() == fightPropId) {
				newThingId = obj.getId();
				break;
			}
		}
		ThingUtil.addInstPlayerThing(instPlayerId, newThingId, convNum, syncMsgData, msgMap);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 物品使用
	 * 
	 * @author mp
	 * @date 2014-8-7 上午9:44:07
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void thingUse(HashMap<String, Object> msgMap, String channelId) throws Exception {

		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerThingId = (Integer) msgMap.get("instPlayerThingId");// 物品实例Id
		int num = (Integer) msgMap.get("num");// 数量
		
		//传入数量不可为负数和零
		if (num <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
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
		
		if (instPlayerThing.getNum() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		int thingId = instPlayerThing.getThingId();
		DictThing thing = DictMap.dictThingMap.get(thingId + "");
		if (instPlayerThing.getNum() < num) {
			num = instPlayerThing.getNum();
		}
		// //目前只做vip礼包使用 - 因为礼包中物品也暂未定,所以只做已定的 获取元宝/金币 , 物品
		// if (thing.getThingTypeId() == StaticThingType.vipGift) {
		// //验证vip
		// if (instPlayer.getVipLevel() < instPlayerThing.getLevel()) {
		// MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noUpVip);
		// return;
		// }
		//
		// String thingList = thing.getChildThings();
		// for (String childThing : thingList.split(";")) {
		// int tableTypeId = ConvertUtil.toInt(childThing.split("_")[0]);
		// int tableFieldId = ConvertUtil.toInt(childThing.split("_")[1]);
		// int value = ConvertUtil.toInt(childThing.split("_")[2]);
		// ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData);
		// }
		// }
		if (thing.getId() == StaticThing.energyPill) {
			instPlayer.setEnergy(instPlayer.getEnergy() + (DictMapUtil.getSysConfigIntValue(StaticSysConfig.energyPillEnergy) * num));
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		if (thing.getId() == StaticThing.vigorPill) {
			instPlayer.setVigor(instPlayer.getVigor() + (DictMapUtil.getSysConfigIntValue(StaticSysConfig.vigorPillVigor) * num));
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		if (thing.getId() == StaticThing.silverNote10000 || thing.getId() == StaticThing.silverNote5000) {
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + (DictMap.dictThingMap.get(thing.getId() + "").getSellCopper()) * num) + "");
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}

		// 删除使用的物品
		ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, num, syncMsgData, msgMap);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 开宝箱
	 * 
	 * @author mp
	 * @date 2015-1-24 下午2:34:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void openBox(HashMap<String, Object> msgMap, String channelId) throws Exception {

		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerThingId = (Integer) msgMap.get("instPlayerThingId");// 物品[宝箱]实例Id
		int num = (Integer) msgMap.get("num");// 开启宝箱个数
		
		//传入数量不可为负数和零
		if (num <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerThing instPlayerThing = getInstPlayerThingDAL().getModel(instPlayerThingId, instPlayerId);

		int thingId = instPlayerThing.getThingId();

		if (thingId == StaticThing.groupBox) {
			if (num > 10) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_openBox_ten);
				return;
			}
		}
		
		DictThing thingObj = DictMap.dictThingMap.get(thingId + "");
		if (thingId == StaticThing.copperKey) {
			thingId = StaticThing.copperBox;
		} else if (thingId == StaticThing.silverKey) {
			thingId = StaticThing.silverBox;
		} else if (thingId == StaticThing.goldKey) {
			thingId = StaticThing.goldBox;
		}
		List<InstPlayerThing> instPlayerThings = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + thingId, instPlayerId);
		if (instPlayerThings.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		instPlayerThing = instPlayerThings.get(0);

		// 验证玩家Id
		if (instPlayerThing == null || instPlayerThing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		// 验证数量
		if (num <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}

		int boxNum = instPlayerThing.getNum();
		int keyId = 0;
		int type = 0;
		int keyNum = 0;
		boolean needKey = true;

		// 获取宝箱对应的钥匙字典Id
		thingId = instPlayerThing.getThingId();
		if (thingId == StaticThing.copperBox) {
			keyId = StaticThing.copperKey;
			type = 1;
		} else if (thingId == StaticThing.silverBox) {
			keyId = StaticThing.silverKey;
			type = 2;
		} else if (thingId == StaticThing.goldBox) {
			keyId = StaticThing.goldKey;
			boxNum += instPlayerThing.getLevel();
			type = 3;
		} else {
			needKey = false; // 非金银铜宝箱无需钥匙
			type = thingId;
		}

		// 单次最大开箱子数为100, 大于100时,分多次开
		if (num > DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxOpenBoxNum)) {
			if (needKey)
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_maxOpenBoxNum);
			else
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_maxOpenBoxNum2);
			return;
		}

		// 获取钥匙数量
		List<InstPlayerThing> instPlayerThingList = null;
		if (needKey) {
			instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + keyId, instPlayerId);
			if (instPlayerThingList.size() > 0) {
				keyNum = instPlayerThingList.get(0).getNum();
			}
			// 验证宝箱个数和宝箱钥匙够不够
			if (num > boxNum || num > keyNum) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_boxOrKeyNumNotEnough);
				return;
			}
		} else {
			// 验证礼包个数够不够
			if (num > boxNum) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_boxOrKeyNumNotEnough2);
				return;
			}
		}

		// 开宝箱,获取物品
		String openedThingIdList = "";
		InstPlayerBoxCount instPlayerBoxCount = null;
		List<InstPlayerBoxCount> instPlayerBoxCountList = null;

		// 当是金箱子时,需要获取一些对象
		if (type == 3) {
			instPlayerBoxCountList = getInstPlayerBoxCountDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			if (instPlayerBoxCountList.size() > 0) {
				instPlayerBoxCount = instPlayerBoxCountList.get(0);
			} else {
				instPlayerBoxCount = new InstPlayerBoxCount();
				instPlayerBoxCount.setInstPlayerId(instPlayerId);
				instPlayerBoxCount.setOpenValue(0);
				instPlayerBoxCount.setSpecialBoxRuleId(0);
				instPlayerBoxCount.setIsHit(0);
			}
		}

		// 循环开箱子
		for (int i = 0; i < num; i++) {

			String openedThingId = "";
			// 非金箱子,走普通库
			openedThingId = ThingUtil.openGenerThingId(type);

			// 当是金箱子时,需要特殊处理一下
			if (type == 3) {
				int openValue = (int) instPlayerBoxCount.getOpenValue();// 向下取整,强转能省略小数点

				// 处理超过特殊宝箱最大数的时候, 每隔xx[配置表]次给某个东西[先随机type,在随机物品]
				List<DictSpecialRule> specialRuleList = DictList.dictSpecialRuleList;
				int maxNum = specialRuleList.get(specialRuleList.size() - 1).getMaxNum();
				int mod = 0;
				if (openValue > maxNum) {
					mod = openValue - maxNum;
					if (mod % DictMapUtil.getSysConfigIntValue(StaticSysConfig.openBoxRepeatNum) == 0) {
						int randomRuleId = RandomUtil.getRangeInt(0, specialRuleList.size() - 1);
						int tableTypeId = specialRuleList.get(randomRuleId).getTableTypeId();
						openedThingId = ThingUtil.openSpecialThingId(tableTypeId);
					}
				} else {
					int specialBoxRuleId = ThingUtil.specialBoxRuleId(openValue);
					if (specialBoxRuleId != 0) {
						// 如果根据开箱子值计算出来的specialBoxRuleId和现存的specialBoxRuleId不相同
						if (specialBoxRuleId != instPlayerBoxCount.getSpecialBoxRuleId()) {
							// 更新specialBoxRuleId和isHit
							instPlayerBoxCount.setSpecialBoxRuleId(specialBoxRuleId);
							instPlayerBoxCount.setIsHit(0);
						}

						// 如果当前命中状态为未命中
						if (instPlayerBoxCount.getIsHit() == 0) {
							DictSpecialRule specialRule = DictMap.dictSpecialRuleMap.get(specialBoxRuleId + "");
							if (openValue == specialRule.getMaxNum()) {
								// 如果达到区间最大边界,必命中
								instPlayerBoxCount.setIsHit(1);
								// 去特殊开箱子库中,给东西
								int tableTypeId = specialRule.getTableTypeId();
								openedThingId = ThingUtil.openSpecialThingId(tableTypeId);
							} else {
								// 没有达到区间最大边界,随机处理
								int random = RandomUtil.getRangeInt(specialRule.getMinNum(), specialRule.getMaxNum());
								if (random == openValue) {
									// 命中
									instPlayerBoxCount.setIsHit(1);
									// 去特殊开箱子库中,给东西
									int tableTypeId = specialRule.getTableTypeId();
									openedThingId = ThingUtil.openSpecialThingId(tableTypeId);
								}
							}
						}
					}
				}

				// 处理开金箱子得到的开箱子值
				float currOpenBoxValue = ThingUtil.openBoxValue(instPlayerThing);
				instPlayerBoxCount.setOpenValue(instPlayerBoxCount.getOpenValue() + currOpenBoxValue);
			}
			openedThingIdList += openedThingId + ";";
		}

		// 更新开箱子计数实例表, 服务器用, 不需给客户端同步
		if (instPlayerBoxCountList != null) {
			if (instPlayerBoxCountList.size() <= 0) {
				getInstPlayerBoxCountDAL().add(instPlayerBoxCount, instPlayerId);
			} else {
				getInstPlayerBoxCountDAL().update(instPlayerBoxCount, instPlayerId);
			}
		}

		MessageData syncMsgData = new MessageData();

		// 减去对应的钥匙
		if (needKey) {
			InstPlayerThing instPlayerKey = instPlayerThingList.get(0);
			ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerKey, num, syncMsgData, msgMap);
		}

		// 减去对应的箱子
		if (type == 3) {
			// 开金宝箱的时候,先开特殊金宝箱,特殊的开完了,在开普通的金宝箱
			if (instPlayerThing.getLevel() > num) {
				instPlayerThing.setLevel(instPlayerThing.getLevel() - num);
				getInstPlayerThingDAL().update(instPlayerThing, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
			} else {
				int mod = num - instPlayerThing.getLevel();
				instPlayerThing.setLevel(0);
				ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, mod, syncMsgData, msgMap);
			}
		} else {
			ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, num, syncMsgData, msgMap);
		}

		Map<String, String> thingMap = new HashMap<String, String>();
		
		if (thingId == StaticThing.groupBox) {
			List<InstPlayerGroup> instPlayerGroupList = getInstPlayerGroupDAL().getList("instPlayerId = " + instPlayerId, 0);
			if (instPlayerGroupList.size() > 0) {
				InstPlayerGroup instPlayerGroup = instPlayerGroupList.get(0);
				int calcBoxNum = instPlayerGroup.getOpenGroupBoxNum() + num;//按加上本次的箱子数量来计算
				instPlayerGroup.setOpenGroupBoxNum(calcBoxNum);
				
				//将此箱子数量能必得的id获取出来
				List<Integer> calcList = new ArrayList<>();
				for (DictActivityGroupGiveZiRule obj : DictList.dictActivityGroupGiveZiRuleList) {
					if (calcBoxNum >= obj.getStartBuyNum()) {
						calcList.add(obj.getId());
					}
				}
				
				//和本地保存的做比较,没有的话给固定的,并记录
				List<String> guThingList = new ArrayList<>();
				int ownNum = 0;
				for (int ziRuleId : calcList) {
					if (!StringUtil.contains(instPlayerGroup.getGiveZiList(), ziRuleId + "", ";")) {
						//如果不包含,给固定的物品
						ownNum ++;
						int generBoxId = 0;
						int randomBase = 0;
						DictActivityGroupGiveZiRule dictActivityGroupGiveZiRule = DictMap.dictActivityGroupGiveZiRuleMap.get(ziRuleId + "");
						List<DictGenerBoxThing> hsGenerBoxThingList = new ArrayList<>();
						for (DictGenerBoxThing generBoxThing : DictList.dictGenerBoxThingList) {
							if (generBoxThing.getType() == dictActivityGroupGiveZiRule.getEndBuyNum()) {
								hsGenerBoxThingList.add(generBoxThing);
								randomBase += generBoxThing.getChance();
							}
						}
						int randomNum = RandomUtil.getRangeInt(1, randomBase);
						int randomSum = 0;
						for (DictGenerBoxThing generBoxThing : hsGenerBoxThingList) {
							randomSum += generBoxThing.getChance();
							if (randomNum <= randomSum) {
								generBoxId = generBoxThing.getId();
								break;
							}
						}
						guThingList.add("1_" + generBoxId);
						if (instPlayerGroup.getGiveZiList().equals("")) {
							instPlayerGroup.setGiveZiList(ziRuleId + "");
						} else {
							instPlayerGroup.setGiveZiList(instPlayerGroup.getGiveZiList() + ";" + ziRuleId);
						}
//						break;//按一个处理
					}
				}
				
				if (ownNum != 0) {
					String[] openThingArray = openedThingIdList.split(";");
					for (String guThingObj : guThingList) {
						int randomIndex = RandomUtil.getRandomInt(openThingArray.length);
						openThingArray [randomIndex] = guThingObj;//有可能会出现重复，就算了吧（因为单次开箱子最大只能开10个,放出的箱子数又有限）
					}
					openedThingIdList = "";
					for (String thingString : openThingArray) {
						openedThingIdList += thingString + ";";
					}
				}
				getInstPlayerGroupDAL().update(instPlayerGroup, 0);
			}
		}
		
		// 添加箱子开出的物品
		for (String thing : openedThingIdList.split(";")) {
			int tableTypeId = 0;
			int tableFieldId = 0;
			int value = 0;
			String tableType = thing.split("_")[0];
			int id = ConvertUtil.toInt(thing.split("_")[1]);
			if (tableType.equals("1")) {
				DictGenerBoxThing generBoxThing = DictMap.dictGenerBoxThingMap.get(id + "");
				tableTypeId = generBoxThing.getTableTypeId();
				tableFieldId = generBoxThing.getTableFieldId();
				value = generBoxThing.getValue();
			} else if (tableType.equals("2")) {
				DictSpecialBoxThing specialBoxThing = DictMap.dictSpecialBoxThingMap.get(id + "");
				tableTypeId = specialBoxThing.getTableTypeId();
				tableFieldId = specialBoxThing.getTableFieldId();
				value = specialBoxThing.getValue();
			}

			// 当产出多个物品时,将最终操作结果,放入一个map
			ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);

			// ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData);
		}
		
		//日志处理
		String log = "开箱子:instPlayerId=" + instPlayerId + " 箱子名称=" + thingObj.getName() + " 开箱子个数=" + num + " 开出的物品列表=" + thingMap;
		LogUtil.info(log);
		
		ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);

		// 将宝箱开出的id发给客户端,以供展示
		// [格式为1_1;2_2; 下划线前面的数为1或2两个值, 等于1 表示去Dict_Gener_BoxThing表查, 等于2 表示去Dict_Special_BoxThing表查； 下划线后边的数为表的id；字符串最后不包含分号]
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", StringUtil.noContainLastString(openedThingIdList));
		
		// 跑马灯相关
		MarqueeUtil.putMsgToMarquee(channelId, StringUtil.noContainLastString(openedThingIdList), CustomMarqueeType.MARQUEE_TYPE_EQUIP, CustomMarqueeType.MARQUEE_SOURCE_OPENBOX);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
}
