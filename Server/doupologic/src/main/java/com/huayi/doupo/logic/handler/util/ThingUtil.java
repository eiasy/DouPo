package com.huayi.doupo.logic.handler.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.DictCardSoul;
import com.huayi.doupo.base.model.DictChip;
import com.huayi.doupo.base.model.DictConstell;
import com.huayi.doupo.base.model.DictEquipment;
import com.huayi.doupo.base.model.DictGenerBoxThing;
import com.huayi.doupo.base.model.DictMagic;
import com.huayi.doupo.base.model.DictMagicLevel;
import com.huayi.doupo.base.model.DictManualSkill;
import com.huayi.doupo.base.model.DictPill;
import com.huayi.doupo.base.model.DictPillThing;
import com.huayi.doupo.base.model.DictSpecialBoxThing;
import com.huayi.doupo.base.model.DictSpecialRule;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.DictThingExtend;
import com.huayi.doupo.base.model.DictVIP;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerArena;
import com.huayi.doupo.base.model.InstPlayerBagExpand;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerCardSoul;
import com.huayi.doupo.base.model.InstPlayerConstell;
import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.InstPlayerMagic;
import com.huayi.doupo.base.model.InstPlayerManualSkill;
import com.huayi.doupo.base.model.InstPlayerStore;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticBagType;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.StaticThingType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.LogicLogUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 物品帮助类
 * 
 * @author mp
 * @date 2013-10-11 下午1:29:17
 */
public class ThingUtil extends DALFactory {

	/**
	 * 根据开箱子值获取specialBoxRuleId
	 * 
	 * @author mp
	 * @date 2015-1-24 下午5:21:34
	 * @param openValue
	 * @return
	 * @Description
	 */
	public static int specialBoxRuleId(int openValue) {
		int specialBoxRuleId = 0;
		List<DictSpecialRule> specialRuleList = DictList.dictSpecialRuleList;
		for (DictSpecialRule obj : specialRuleList) {
			if (obj.getMinNum() <= openValue && openValue <= obj.getMaxNum()) {
				specialBoxRuleId = obj.getId();
				break;
			}
		}
		return specialBoxRuleId;
	}

	/**
	 * 获取金宝箱单次开箱子值
	 * 
	 * @author mp
	 * @date 2015-1-24 下午5:49:35
	 * @param instPlayerThing
	 * @return
	 * @Description
	 */
	public static float openBoxValue(InstPlayerThing instPlayerThing) {
		if (instPlayerThing.getLevel() > 0) {
			// instPlayerThing.setLevel(instPlayerThing.getLevel() - 1);
			return DictMapUtil.getSysConfigFloatValue(StaticSysConfig.specialGoldBoxOpenValue);
		} else {
			// instPlayerThing.setNum(instPlayerThing.getNum() - 1);
			return DictMapUtil.getSysConfigFloatValue(StaticSysConfig.generGoldBoxOpenValue);
		}
	}

	/**
	 * 根据类型获取箱子在特殊库中开出的物品
	 * 
	 * @author mp
	 * @date 2015-1-24 下午5:22:17
	 * @param type
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static String openSpecialThingId(int tableTypeId) {

		String openedThingId = "";
		List<DictSpecialBoxThing> specialBoxThingList = (List<DictSpecialBoxThing>) DictMapList.dictSpecialBoxThingMap.get(tableTypeId);

		// 计算随机数的基数
		int randomBaseNum = 0;
		for (DictSpecialBoxThing obj : specialBoxThingList) {
			randomBaseNum += obj.getChance();
		}

		// 获取随机数
		int randomNum = RandomUtil.getRangeInt(1, randomBaseNum);

		// 计算得到的卡牌Id,并返回
		int weightSum = 0;
		for (DictSpecialBoxThing obj : specialBoxThingList) {
			weightSum += obj.getChance();
			if (randomNum <= weightSum) {
				openedThingId = obj.getId() + "";
				break;
			}
		}

		// 1表示Dict_Gener_BoxThing中的id, 2表示Dict_Special_BoxThing中的id
		return "2_" + openedThingId;
	}

	/**
	 * 根据类型获取箱子在普通库中开出的物品
	 * 
	 * @author mp
	 * @date 2015-1-24 下午4:05:26
	 * @param type
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static String openGenerThingId(int type) {
		List<DictGenerBoxThing> generBoxThingList = (List<DictGenerBoxThing>) DictMapList.dictGenerBoxThingMap.get(type);
		// 已发现的负值
		int negitiveValue = 0;
		// 该负值的个数
		int negitiveCount = 0;
		// 计算随机数的基数
		int randomBaseNum = 0;
		StringBuilder sb = new StringBuilder();
		for (DictGenerBoxThing obj : generBoxThingList) {
			int chance = obj.getChance();
			if (chance < 0) {
				if (negitiveValue == 0) {
					negitiveValue = chance;
					++negitiveCount;
					sb.append("1_" + obj.getId() + ";");
				} else if (negitiveValue == chance && negitiveCount + negitiveValue < 0) {
					++negitiveCount;
					sb.append("1_" + obj.getId() + ";");
				}
			} else { // chance 可以为0
				randomBaseNum += chance;
			}
		}
		if (randomBaseNum > 0) {
			// 获取随机数
			int randomNum = RandomUtil.getRangeInt(1, randomBaseNum);
			// 计算得到的卡牌Id,并返回
			for (DictGenerBoxThing obj : generBoxThingList) {
				int chance = obj.getChance();
				if (chance > 0) {
					if ((randomNum -= chance) <= 0) {
						sb.append("1_" + obj.getId() + ";");
						break;
					}
				}
			}
		}
		// 1表示Dict_Gener_BoxThing中的id, 2表示Dict_Special_BoxThing中的id
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 添加玩家物品
	 * 
	 * @author mp
	 * @date 2014-7-2 下午3:54:24
	 * @param instPlayerId
	 * @param thingId
	 * @param num
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerThing(int instPlayerId, int thingId, int num, MessageData syncMsgData, Map<String, Object> msgMap) throws Exception {
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + thingId, instPlayerId);
		InstPlayerThing instPlayerThing = null;

		int source = 0;// 来源-协议号
		String desc = "";// 来源描述
		if (msgMap != null) {
			source = (int) msgMap.get("header");
			desc = DictMap.sysMsgRuleMap.get(source + "").getName();
		}

		if (instPlayerThingList.size() == 0) {
			instPlayerThing = new InstPlayerThing();
			DictThing thing = DictMap.dictThingMap.get(thingId + "");
			instPlayerThing.setInstPlayerId(instPlayerId);
			instPlayerThing.setLevel(thing.getLevel());
			instPlayerThing.setNum(num);
			instPlayerThing.setThingId(thingId);
			instPlayerThing.setThingTypeId(thing.getThingTypeId());
			instPlayerThing.setBagTypeId(thing.getBagTypeId());
			instPlayerThing.setIndexOrder(thing.getIndexOrder());
			getInstPlayerThingDAL().add(instPlayerThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
			// 记录道具-物品日志
			try {
				String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|1|" + "" + "|" + "" + "|" + thingId + "|" + thing.getName() + "|" + "0" + "|" + num + "|" + num + "|" + source + "|" + desc;
				LogicLogUtil.info("things", logContent.replace("null", ""));
			} catch (Exception e) {
				LogUtil.error("道具/物品日志错误", e);
			}
		}
		if (instPlayerThingList.size() > 0) {
			instPlayerThing = instPlayerThingList.get(0);
			int operBeforeNum = instPlayerThing.getNum();
			instPlayerThing.setNum((instPlayerThing.getNum() + num) <= 0 ? 0 : (instPlayerThing.getNum() + num));
			getInstPlayerThingDAL().update(instPlayerThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
			// 记录道具-物品日志
			try {
				String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|1|" + "" + "|" + "" + "|" + thingId + "|" + DictMap.dictThingMap.get(thingId + "").getName() + "|" + operBeforeNum + "|" + instPlayerThing.getNum() + "|" + num + "|" + source + "|" + desc;
				LogicLogUtil.info("things", logContent.replace("null", ""));
			} catch (Exception e) {
				LogUtil.error("道具/物品日志错误", e);
			}
		}
	}

	/**
	 * 添加物品[特殊宝箱]
	 * 
	 * @author mp
	 * @date 2015-1-26 上午10:46:01
	 * @param instPlayerId
	 * @param thingId
	 * @param num
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void addSpecialBoxThing(int instPlayerId, int num, MessageData syncMsgData) throws Exception {
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.goldBox, instPlayerId);
		InstPlayerThing instPlayerThing = null;
		if (instPlayerThingList.size() == 0) {
			instPlayerThing = new InstPlayerThing();
			DictThing thing = DictMap.dictThingMap.get(StaticThing.goldBox + "");
			instPlayerThing.setInstPlayerId(instPlayerId);
			instPlayerThing.setLevel(num);
			instPlayerThing.setNum(0);
			instPlayerThing.setThingId(StaticThing.goldBox);
			instPlayerThing.setThingTypeId(thing.getThingTypeId());
			instPlayerThing.setBagTypeId(thing.getBagTypeId());
			instPlayerThing.setIndexOrder(thing.getIndexOrder());
			getInstPlayerThingDAL().add(instPlayerThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
		}
		if (instPlayerThingList.size() > 0) {
			instPlayerThing = instPlayerThingList.get(0);
			instPlayerThing.setLevel(instPlayerThing.getLevel() + num);
			getInstPlayerThingDAL().update(instPlayerThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
		}
	}

	/**
	 * 更新删除玩家物品
	 * 
	 * @author hzw
	 * @date 2014-7-4上午11:28:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void updateInstPlayerThing(int instPlayerId, InstPlayerThing instPlayerThing, int num, MessageData syncMsgData, Map<String, Object> msgMap) throws Exception {
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
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|2|" + "" + "|" + "" + "|" + instPlayerThing.getThingId() + "|" + dictThing.getName() + "|" + instPlayerThing.getNum() + "|" + (instPlayerThing.getNum() - num) + "|" + num + "|" + source + "|" + desc;
			LogicLogUtil.info("things", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("道具/物品日志错误", e);
		}
		if (instPlayerThing.getNum() - num <= 0) {
			getInstPlayerThingDAL().deleteById(instPlayerThing.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerThing, instPlayerThing.getId(), "", syncMsgData);
		} else {
			instPlayerThing.setNum(instPlayerThing.getNum() - num);
			getInstPlayerThingDAL().update(instPlayerThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, instPlayerThing.getId(), instPlayerThing.getResult(), syncMsgData);
		}
	}

	/**
	 * 验证某物品列表中是否含有新物品
	 * 
	 * @author mp
	 * @date 2014-7-31 下午2:23:19
	 * @param thingList
	 *            中间用分号隔开
	 * @param instPlayerThingList
	 * @return
	 * @Description
	 */
	public static boolean haveNewThing(String thingList, int bagTypeId, List<InstPlayerThing> instPlayerThingList) {
		int index = 0;
		List<Integer> idList = Lists.newArrayList();

		// 将符合条件的背包类型物品放入列表中
		for (String thing : thingList.split(";")) {
			int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
			DictThing dictThing = DictMap.dictThingMap.get(tableFieldId + "");
			if (dictThing.getBagTypeId() == bagTypeId) {
				idList.add(tableFieldId);
			}
		}

		// 循环物品列表并比较是否在符合条件的物品列表中
		for (InstPlayerThing instPlayerThing : instPlayerThingList) {
			int thingId = instPlayerThing.getThingId();
			if (idList.contains(thingId)) {
				index++;
			}
		}
		if (index < idList.size()) {
			return true;
		}
		return false;
	}

	/**
	 * 是否为新物品
	 * 
	 * @author mp
	 * @date 2014-8-7 上午10:18:31
	 * @param thingId
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static boolean haveNewThing(int thingId, int instPlayerId) {
		List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId =  " + instPlayerId, instPlayerId);
		int index = 0;
		for (InstPlayerThing obj : instPlayerThingList) {
			if (obj.getThingId() == thingId) {
				index++;
			}
		}
		// 等于零说明背包中没有此物品,为新物种
		if (index == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取某背包类型的扩容数量
	 * 
	 * @author mp
	 * @date 2014-7-31 下午3:42:47
	 * @param instPlayerId
	 * @param bagTypeId
	 * @return
	 * @Description
	 */
	public static int getExtandBagNum(int instPlayerId, int bagTypeId) {
		List<InstPlayerBagExpand> instPlayerBagExpandList = getInstPlayerBagExpandDAL().getList("instPlayerId = " + instPlayerId + " and bagTypeId = " + bagTypeId, instPlayerId);
		if (instPlayerBagExpandList.size() > 0) {
			return instPlayerBagExpandList.get(0).getExpandSumNum();
		}
		return 0;
	}

	/**
	 * 获取某背包类型的当前背包数
	 * 
	 * @author mp
	 * @date 2014-8-6 上午11:19:17
	 * @param instPlayerId
	 * @param bagTypeId
	 * @return
	 * @Description
	 */
	public static int getBagNum(int instPlayerId, int bagTypeId) {
		int expandNum = 0;
		List<InstPlayerBagExpand> instPlayerBagExpandList = getInstPlayerBagExpandDAL().getList("instPlayerId = " + instPlayerId + " and bagTypeId = " + bagTypeId, instPlayerId);
		if (instPlayerBagExpandList.size() > 0) {
			expandNum = instPlayerBagExpandList.get(0).getExpandSumNum();
		}
		// 背包数 = 初始上限 + 已扩充的
		int bagNum = DictMap.dictBagTypeMap.get(bagTypeId + "").getBagUpLimit() + expandNum;
		return bagNum;
	}

	/**
	 * 获取商店道具列表
	 * 
	 * @author mp
	 * @date 2014-8-5 上午11:24:25
	 * @return
	 * @Description
	 */
	public static List<DictThing> getStoreItems() {
		List<DictThing> itemList = Lists.newArrayList();
		List<DictThing> thingsList = DictList.dictThingList;
		for (DictThing thing : thingsList) {
			if (thing.getBagTypeId() == StaticBagType.item && thing.getIsCanBuy() == 1 && thing.getThingTypeId() != StaticThingType.vipGift) {
				itemList.add(thing);
			}
		}
		Collections.sort(itemList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((DictThing) a).getIndexOrder();
				int two = ((DictThing) b).getIndexOrder();
				return (int) (one - two);
			}
		});
		return itemList;
	}

	/**
	 * 根据vip等级获取道具列表
	 * 
	 * @author mp
	 * @date 2014-8-5 下午12:00:47
	 * @param vipLevel
	 * @return
	 * @Description
	 */
	public static Map<Integer, Integer> getItemsByVipLevel(int vipLevel) {
		Map<Integer, Integer> vipItemMap = Maps.newHashMap();
		List<DictVIP> vipList = DictList.dictVIPList;
		DictVIP vip = null;
		for (DictVIP obj : vipList) {
			if (obj.getLevel() == vipLevel) {
				vip = obj;
				break;
			}
		}
		if (vip != null && !vip.getBuyItemsNum().equals("")) {
			for (String itemInfo : vip.getBuyItemsNum().split(";")) {
				vipItemMap.put(ConvertUtil.toInt(itemInfo.split("_")[0]), ConvertUtil.toInt(itemInfo.split("_")[1]));
			}
		}
		return vipItemMap;
	}

	/**
	 * 获取玩家当日的商店购买记录
	 * 
	 * @author mp
	 * @date 2014-8-5 下午2:31:14
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static Map<Integer, String> getTodayBuyItemMap(int instPlayerId) {
		// key value={buyNum_buyTimes}
		Map<Integer, String> todayBuyItemMap = Maps.newHashMap();
		List<InstPlayerStore> todayBuyItemList = getInstPlayerStoreDAL().getList("instPlayerId = " + instPlayerId + " and bagType = " + StaticBagType.item + " and buyTime like '" + DateUtil.getYmdDate() + "%'", instPlayerId);
		for (InstPlayerStore instPlayerStore : todayBuyItemList) {
			int key = instPlayerStore.getThingId();
			int buyNum = instPlayerStore.getNum();
			if (todayBuyItemMap.containsKey(key)) {
				int haveBuyNum = ConvertUtil.toInt(todayBuyItemMap.get(key).split("_")[0]);
				int haveBuyTimes = ConvertUtil.toInt(todayBuyItemMap.get(key).split("_")[1]);
				String value = (haveBuyNum + buyNum) + "_" + (haveBuyTimes + 1);
				todayBuyItemMap.put(key, value);
			} else {
				String value = buyNum + "_" + "1";
				todayBuyItemMap.put(key, value);
			}
		}
		return todayBuyItemMap;
	}

	/**
	 * 向客户端组织商店道具列表
	 * 
	 * @author mp
	 * @date 2014-8-5 下午3:23:06
	 * @param itemList
	 * @param vipItemMap
	 * @param todayBuyItemMap
	 * @param retMsgData
	 * @Description
	 */
	public static void orgStoreItems(List<DictThing> itemList, Map<Integer, Integer> vipItemMap, Map<Integer, String> todayBuyItemMap, MessageData retMsgData) {
		int index = 0;
		for (DictThing thing : itemList) {
			// tableFieldId_canBuyNum_price;
			int thingId = thing.getId();
			int canBuyNum = 0;// vip购买个数上限-已购买的个数
			int price = 0;
			int todayBuyNum = 0;
			// 如果不受vip等级限制,表示此物品可以无限购买,返回-1
			if (vipItemMap.size() == 0 || !vipItemMap.containsKey(thingId)) {
				canBuyNum = -1;
			} else {
				int buyLimitNum = vipItemMap.get(thingId);
				if (todayBuyItemMap.containsKey(thingId)) {
					todayBuyNum = ConvertUtil.toInt(todayBuyItemMap.get(thingId).split("_")[0]);
				}
				canBuyNum = (buyLimitNum - todayBuyNum) < 0 ? 0 : (buyLimitNum - todayBuyNum);
			}
			// 售价 = 初始售价 + 已购买次数 * 价格涨幅
			price = DictMap.dictThingMap.get(thingId + "").getBuyGold();
			if (todayBuyItemMap.containsKey(thingId)) {
				price = price + (ConvertUtil.toInt(todayBuyItemMap.get(thingId).split("_")[1]) * thing.getStoreBuyGoldGrow());
			}

			if (thingId == StaticThing.vigorPill || thingId == StaticThing.energyPill) {
				DictThingExtend dictThingExtend = DictMap.dictThingExtendMap.get(thingId + "");
				String extend[] = dictThingExtend.getExtend().split(";");
				for (String str : extend) {
					int down = ConvertUtil.toInt(str.split("_")[0]);
					int up = ConvertUtil.toInt(str.split("_")[1]);
					int gold = ConvertUtil.toInt(str.split("_")[2]);
					if (down <= todayBuyNum && todayBuyNum <= up) {
						price = gold;
					}
				}
			}
			MessageData itemMsgData = new MessageData();
			itemMsgData.putIntItem("thingId", thingId);
			itemMsgData.putIntItem("canBuyNum", canBuyNum);
			itemMsgData.putIntItem("price", price);
			itemMsgData.putIntItem("indexOrder", thing.getIndexOrder());
			itemMsgData.putIntItem("todayBuyNum", todayBuyNum);
			retMsgData.putMessageItem((++index) + "", itemMsgData.getMsgData());
		}
	}

	/**
	 * 商店魔核列表
	 * 
	 * @author mp
	 * @date 2014-8-6 上午9:50:27
	 * @param retMsgData
	 * @Description
	 */
	public static void orgStoreCores(MessageData retMsgData) {
		int index = 0;
		for (DictThing dictThing : DictList.dictThingList) {
			if (dictThing.getBagTypeId() == StaticBagType.core && dictThing.getIsCanBuy() == 1) {
				MessageData coreMsgData = new MessageData();
				coreMsgData.putIntItem("thingId", dictThing.getId());
				coreMsgData.putIntItem("price", dictThing.getBuyGold());// 价格
				coreMsgData.putIntItem("indexOrder", dictThing.getIndexOrder());
				retMsgData.putMessageItem((++index) + "", coreMsgData.getMsgData());
			}
		}
	}

	/**
	 * 商店vip礼包列表
	 * 
	 * @author mp
	 * @date 2014-8-6 上午9:51:46
	 * @param instPlayerId
	 * @param retMsgData
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void orgVipGifts(int instPlayerId, MessageData retMsgData) {
		List<InstPlayerStore> instPlayerStoreList = getInstPlayerStoreDAL().getList("instPlayerId = " + instPlayerId + " and bagType = 0", instPlayerId);
		List<Integer> buyVipGiftIdList = Lists.newArrayList();
		for (InstPlayerStore instPlayerStore : instPlayerStoreList) {
			buyVipGiftIdList.add(instPlayerStore.getThingId());
		}
		int index = 0;
		List<DictThing> vipGiftList = (List<DictThing>) DictMapList.dictThingMap.get(StaticThingType.vipGift);
		for (DictThing obj : vipGiftList) {
			if (obj.getIsCanBuy() == 1) {
				MessageData coreMsgData = new MessageData();
				coreMsgData.putIntItem("thingId", obj.getId());
				coreMsgData.putIntItem("isBuy", buyVipGiftIdList.contains(obj.getId()) ? 1 : 0);// 0-未购买 1-已购买
				coreMsgData.putIntItem("oldPrice", obj.getOldBuyGold());// 原价
				coreMsgData.putIntItem("nowPrice", obj.getBuyGold());// 现价
				coreMsgData.putIntItem("indexOrder", obj.getIndexOrder());
				retMsgData.putMessageItem((++index) + "", coreMsgData.getMsgData());
			}
		}
	}

	/**
	 * 添加商店购买记录
	 * 
	 * @author mp
	 * @date 2014-8-6 上午11:46:59
	 * @param type
	 * @param thing
	 * @param instPlayerId
	 * @param num
	 * @param thingId
	 * @throws Exception
	 * @Description
	 */
	public static void addPlayerStore(int type, DictThing thing, int instPlayerId, int num, int thingId) throws Exception {
		InstPlayerStore instPlayerStore = new InstPlayerStore();
		instPlayerStore.setBagType(type == 3 ? 0 : thing.getBagTypeId());
		instPlayerStore.setInstPlayerId(instPlayerId);
		instPlayerStore.setThingId(thingId);
		instPlayerStore.setNum(num);
		instPlayerStore.setBuyTime(DateUtil.getCurrTime());
		getInstPlayerStoreDAL().add(instPlayerStore, instPlayerId);
	}

	/**
	 * 扩容背包
	 * 
	 * @author mp
	 * @date 2014-8-6 下午3:01:14
	 * @param instPlayerBagExpandList
	 * @param bagTypeId
	 * @param instPlayerId
	 * @param expandFee
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void expandBag(List<InstPlayerBagExpand> instPlayerBagExpandList, int bagTypeId, int instPlayerId, int expandFee, MessageData syncMsgData) throws Exception {
		int expandBum = ConvertUtil.toInt(DictMap.dictSysConfigMap.get(StaticSysConfig.bagExpandNum + "").getValue());
		if (instPlayerBagExpandList.size() <= 0) {
			InstPlayerBagExpand instPlayerBagExpand = new InstPlayerBagExpand();
			instPlayerBagExpand.setBagTypeId(bagTypeId);
			instPlayerBagExpand.setExpandSumGold(expandFee);
			instPlayerBagExpand.setExpandSumNum(expandBum);
			instPlayerBagExpand.setExpandSumTimes(1);
			instPlayerBagExpand.setInstPlayerId(instPlayerId);
			getInstPlayerBagExpandDAL().add(instPlayerBagExpand, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerBagExpand, instPlayerBagExpand.getId(), instPlayerBagExpand.getResult(), syncMsgData);
		} else {
			InstPlayerBagExpand instPlayerBagExpand = instPlayerBagExpandList.get(0);
			instPlayerBagExpand.setExpandSumGold(instPlayerBagExpand.getExpandSumGold() + expandFee);
			instPlayerBagExpand.setExpandSumNum(instPlayerBagExpand.getExpandSumNum() + expandBum);
			instPlayerBagExpand.setExpandSumTimes(instPlayerBagExpand.getExpandSumTimes() + 1);
			getInstPlayerBagExpandDAL().update(instPlayerBagExpand, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerBagExpand, instPlayerBagExpand.getId(), instPlayerBagExpand.getResult(), syncMsgData);
		}

		// 记录扩容日志-玩家Id[instPlayerId]#背包类型[bagTypeId]#扩充时间[expandTime]#扩充消耗的金币[expandGold]#扩充得到的格子数[expandBagNum]
		// String content = instPlayerId + "#" + bagTypeId + "#" + DateUtil.getCurrTime() + "#" + expandFee + "#" + expandBum;
		// LogicLogUtil.info("bagExpand", content);
	}

	/**
	 * 添加tableTypeId_tableFieldId_value组合形式的物品
	 * @author hzw
	 * @date 2014-8-12下午4:16:46
	 * @update 15/10/27 by cui
	 * @param instPlayer
	 * @param tableTypeId
	 * @param tableFieldId
	 * @param value
	 * @param syncMsgData
	 * @param msgMap
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void groupThing(InstPlayer instPlayer, int tableTypeId, int tableFieldId, int value, MessageData syncMsgData, Map<String, Object> msgMap) throws Exception {
		int instPlayerId = instPlayer.getId();
		if (tableTypeId == StaticTableType.DictPill) {
			PillUtil.addInstPlayerPill(instPlayerId, tableFieldId, value, syncMsgData);
		} else if (tableTypeId == StaticTableType.DictThing) {
			// 当是特殊金宝箱的时候, 需要把数量累加到普通宝箱的level上边
			if (tableFieldId == StaticThing.specialGoldBox) {
				addSpecialBoxThing(instPlayerId, value, syncMsgData);
			} else {
				ThingUtil.addInstPlayerThing(instPlayerId, tableFieldId, value, syncMsgData, msgMap);
			}
		} else if (tableTypeId == StaticTableType.DictPlayerBaseProp) {
			if (tableFieldId == StaticPlayerBaseProp.copper) {
				instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + value) + "");
				// getInstPlayerDAL().update(instPlayer, instPlayerId);
				// OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			if (tableFieldId == StaticPlayerBaseProp.gold) {
				// 加元宝
				PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, value, msgMap);
				// getInstPlayerDAL().update(instPlayer, instPlayerId);
				// OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			if (tableFieldId == StaticPlayerBaseProp.culture) {
				instPlayer.setCulture(instPlayer.getCulture() + value);
				// getInstPlayerDAL().update(instPlayer, instPlayerId);
				// OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			if (tableFieldId == StaticPlayerBaseProp.exp) {
				FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + value, syncMsgData, msgMap);
				// getInstPlayerDAL().update(instPlayer, instPlayerId);
				// OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			if (tableFieldId == StaticPlayerBaseProp.offer) {
				List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
				if (instUnionMemberList.size() > 0) {
					InstUnionMember instUnionMember = instUnionMemberList.get(0);
					instUnionMember.setSumOffer(instUnionMember.getSumOffer() + value);
					getInstUnionMemberDAL().update(instUnionMember, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
				}
			}
			if (tableFieldId == StaticPlayerBaseProp.prestige) {
				List<InstPlayerArena> instPlayerArenaList = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
				if (instPlayerArenaList.size() > 0) {
					InstPlayerArena instPlayerArena = instPlayerArenaList.get(0);
					instPlayerArena.setPrestige(instPlayerArena.getPrestige() + value);
					getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
				}
			}			
			if (tableFieldId == StaticPlayerBaseProp.bossIntegral) {//屠魔积分
				List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.bossIntegral+"'", 0);
				if (instPlayerBigTableList.size() > 0) {
					InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
					instPlayerBigTable.setValue1((ConvertUtil.toInt(instPlayerBigTable.getValue1()) + value) + "");
					getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
				} else {
					InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
					instPlayerBigTable.setInstPlayerId(instPlayerId);
					instPlayerBigTable.setProperties(StaticBigTable.bossIntegral);
					instPlayerBigTable.setValue1(value + "");
					getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
				}
			}

			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		} else if (tableTypeId == StaticTableType.DictEquipment) {
			for (int i = 0; i < value; i++) {
				InstPlayerEquip InstPlayerEquip = EquipmentUtil.addEquipment(instPlayerId, tableFieldId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, InstPlayerEquip, InstPlayerEquip.getId(), InstPlayerEquip.getResult(), syncMsgData);

				// 验证紫色装备成就
				AchievementUtil.purpleEquip(instPlayerId, syncMsgData);
			}
		} else if (tableTypeId == StaticTableType.DictCard) {
			for (int i = 0; i < value; i++) {
				// value-指卡牌等级
				InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, tableFieldId, 1);
				
				// 初始化玩家卡牌命宫实例表
				DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
				if (dictCard.getConstells() != null && !dictCard.getConstells().equals("")) {
					String constells[] = dictCard.getConstells().split(";");
					String instPlayerConstells = "";
					for (String constell : constells) {
						DictConstell dictConstell = DictMap.dictConstellMap.get(ConvertUtil.toInt(constell) + "");
						InstPlayerConstell obj = PillUtil.initPlayerConstell(instPlayerId, instPlayerCard.getId(), ConvertUtil.toInt(constell), dictConstell.getPills().split(";").length);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, obj, obj.getId(), obj.getResult(), syncMsgData);
						instPlayerConstells += obj.getId() + ";";
					}
					instPlayerCard.setInstPlayerConstells(instPlayerConstells.substring(0, instPlayerConstells.length() - 1));
					getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
				}
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
			}

		} else if (tableTypeId == StaticTableType.DictCardSoul) {
			CardUtil.addinstPlayerCardSoul(instPlayerId, tableFieldId, value, syncMsgData);
		} else if (tableTypeId == StaticTableType.DictChip) {
			LootUtil.addInstPlayerChip(instPlayerId, tableFieldId, value, syncMsgData, msgMap);
		} else if (tableTypeId == StaticTableType.DictPillThing) {
			PillUtil.addInstPlayerPillThing(instPlayerId, tableFieldId, value, syncMsgData);
		} else if (tableTypeId == StaticTableType.DictManualSkill) {
			InstPlayerManualSkill instPlayerMannualSkill = ManualSkillUtil.addInstPlayerManualSkill(instPlayerId, tableFieldId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerMannualSkill, instPlayerMannualSkill.getId(), instPlayerMannualSkill.getResult(), syncMsgData);
		} else if (tableTypeId == StaticTableType.DictMagic) {
			for (int i = 0; i < value; i++) {
				DictMagic obj = DictMap.dictMagicMap.get(tableFieldId + "");
				InstPlayerMagic instPlayerMagic = new InstPlayerMagic();
				instPlayerMagic.setExp(0);
				instPlayerMagic.setInstCardId(0);
				instPlayerMagic.setInstPlayerId(instPlayerId);
				instPlayerMagic.setMagicId(obj.getId());
				// 类型 1-法宝 2-功法
				if (obj.getType() == 1) {
					instPlayerMagic.setMagicLevelId(((List<DictMagicLevel>) DictMapList.dictMagicLevelMap.get(1)).get(0).getId());
				} else if (obj.getType() == 2) {
					instPlayerMagic.setMagicLevelId(((List<DictMagicLevel>) DictMapList.dictMagicLevelMap.get(2)).get(0).getId());
				}
				instPlayerMagic.setMagicType(obj.getType());
				instPlayerMagic.setQuality(obj.getMagicQualityId());
				getInstPlayerMagicDAL().add(instPlayerMagic, 0);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerMagic, instPlayerMagic.getId(), instPlayerMagic.getResult(), syncMsgData);

				// 验证法宝功法成就
				if (obj.getType() == 1) {
					AchievementUtil.magic1(instPlayerId, syncMsgData);
				} else if (obj.getType() == 2) {
					AchievementUtil.magic2(instPlayerId, syncMsgData);
				}
			}
		} else if (tableTypeId == StaticTableType.DictYFireChip) {
			//异火碎片获得逻辑
			YFireUtil.addInstPlayerYFire(instPlayerId,tableFieldId, value,syncMsgData);
		} else if (tableTypeId == StaticTableType.DictWing) {//翅膀逻辑
			for (int i = 0; i < value; i++) {
				WingUtil.addWing(tableFieldId, instPlayerId, syncMsgData);
			}
		} else if (tableTypeId == StaticTableType.DictFightSoul) {//斗魂逻辑
			for (int i = 0; i < value; i++) {
				FightSoulUtil.addFightSoul(instPlayerId, tableFieldId, syncMsgData);
			}
		}
		
	}
	
	/**
	 *  添加tableTypeId_tableFieldId_value组合形式的物品
	 * @author mp
	 * @date 2015-8-23 上午11:03:29
	 * @param instPlayer
	 * @param tableTypeId
	 * @param tableFieldId
	 * @param value
	 * @param syncMsgData
	 * @param msgMap
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void groupThingBarrier (InstPlayer instPlayer, int tableTypeId, int tableFieldId, int value, MessageData syncMsgData, Map<String, Object> msgMap) throws Exception {
		int instPlayerId = instPlayer.getId();
		if (tableTypeId == StaticTableType.DictPill) {
			PillUtil.addInstPlayerPill(instPlayerId, tableFieldId, value, syncMsgData);
		} else if (tableTypeId == StaticTableType.DictThing) {
			// 当是特殊金宝箱的时候, 需要把数量累加到普通宝箱的level上边
			if (tableFieldId == StaticThing.specialGoldBox) {
				addSpecialBoxThing(instPlayerId, value, syncMsgData);
			} else {
				ThingUtil.addInstPlayerThing(instPlayerId, tableFieldId, value, syncMsgData, msgMap);
			}
		} else if (tableTypeId == StaticTableType.DictPlayerBaseProp) {
			if (tableFieldId == StaticPlayerBaseProp.copper) {
				instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + value) + "");
				// getInstPlayerDAL().update(instPlayer, instPlayerId);
				// OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			if (tableFieldId == StaticPlayerBaseProp.gold) {
				// 加元宝
				PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, value, msgMap);
				// getInstPlayerDAL().update(instPlayer, instPlayerId);
				// OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			if (tableFieldId == StaticPlayerBaseProp.culture) {
				instPlayer.setCulture(instPlayer.getCulture() + value);
				// getInstPlayerDAL().update(instPlayer, instPlayerId);
				// OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			if (tableFieldId == StaticPlayerBaseProp.exp) {
				FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + value, syncMsgData, msgMap);
				// getInstPlayerDAL().update(instPlayer, instPlayerId);
				// OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			if (tableFieldId == StaticPlayerBaseProp.prestige) {
				List<InstPlayerArena> instPlayerArenaList = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
				if (instPlayerArenaList.size() > 0) {
					InstPlayerArena instPlayerArena = instPlayerArenaList.get(0);
					instPlayerArena.setPrestige(instPlayerArena.getPrestige() + value);
					getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
				}
			}

			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		} else if (tableTypeId == StaticTableType.DictEquipment) {
			for (int i = 0; i < value; i++) {
				InstPlayerEquip InstPlayerEquip = EquipmentUtil.addEquipment(instPlayerId, tableFieldId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, InstPlayerEquip, InstPlayerEquip.getId(), InstPlayerEquip.getResult(), syncMsgData);

				// 验证紫色装备成就
				AchievementUtil.purpleEquip(instPlayerId, syncMsgData);
			}
		} else if (tableTypeId == StaticTableType.DictCard) {
			// value-指卡牌等级
			InstPlayerCard instPlayerCard = CardUtil.addPlayerCardBarrier(instPlayerId, tableFieldId, value);

			// 初始化玩家卡牌命宫实例表
			DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
			if (dictCard.getConstells() != null && !dictCard.getConstells().equals("")) {
				String constells[] = dictCard.getConstells().split(";");
				String instPlayerConstells = "";
				for (String constell : constells) {
					DictConstell dictConstell = DictMap.dictConstellMap.get(ConvertUtil.toInt(constell) + "");
					InstPlayerConstell obj = PillUtil.initPlayerConstell(instPlayerId, instPlayerCard.getId(), ConvertUtil.toInt(constell), dictConstell.getPills().split(";").length);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, obj, obj.getId(), obj.getResult(), syncMsgData);
					instPlayerConstells += obj.getId() + ";";
				}
				instPlayerCard.setInstPlayerConstells(instPlayerConstells.substring(0, instPlayerConstells.length() - 1));
				getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
			}
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);

		} else if (tableTypeId == StaticTableType.DictCardSoul) {
			CardUtil.addinstPlayerCardSoul(instPlayerId, tableFieldId, value, syncMsgData);
		} else if (tableTypeId == StaticTableType.DictChip) {
			LootUtil.addInstPlayerChip(instPlayerId, tableFieldId, value, syncMsgData, msgMap);
		} else if (tableTypeId == StaticTableType.DictPillThing) {
			PillUtil.addInstPlayerPillThing(instPlayerId, tableFieldId, value, syncMsgData);
		} else if (tableTypeId == StaticTableType.DictManualSkill) {
			InstPlayerManualSkill instPlayerMannualSkill = ManualSkillUtil.addInstPlayerManualSkill(instPlayerId, tableFieldId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerMannualSkill, instPlayerMannualSkill.getId(), instPlayerMannualSkill.getResult(), syncMsgData);
		} else if (tableTypeId == StaticTableType.DictMagic) {
			for (int i = 0; i < value; i++) {
				DictMagic obj = DictMap.dictMagicMap.get(tableFieldId + "");
				InstPlayerMagic instPlayerMagic = new InstPlayerMagic();
				instPlayerMagic.setExp(0);
				instPlayerMagic.setInstCardId(0);
				instPlayerMagic.setInstPlayerId(instPlayerId);
				instPlayerMagic.setMagicId(obj.getId());
				// 类型 1-法宝 2-功法
				if (obj.getType() == 1) {
					instPlayerMagic.setMagicLevelId(((List<DictMagicLevel>) DictMapList.dictMagicLevelMap.get(1)).get(0).getId());
				} else if (obj.getType() == 2) {
					instPlayerMagic.setMagicLevelId(((List<DictMagicLevel>) DictMapList.dictMagicLevelMap.get(2)).get(0).getId());
				}
				instPlayerMagic.setMagicType(obj.getType());
				instPlayerMagic.setQuality(obj.getMagicQualityId());
				getInstPlayerMagicDAL().add(instPlayerMagic, 0);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerMagic, instPlayerMagic.getId(), instPlayerMagic.getResult(), syncMsgData);

				// 验证法宝功法成就
				if (obj.getType() == 1) {
					AchievementUtil.magic1(instPlayerId, syncMsgData);
				} else if (obj.getType() == 2) {
					AchievementUtil.magic2(instPlayerId, syncMsgData);
				}
			}
		} else if (tableTypeId == StaticTableType.DictYFireChip) {
			//异火碎片获得逻辑
			YFireUtil.addInstPlayerYFire(instPlayerId,tableFieldId, value,syncMsgData);
		} else if (tableTypeId == StaticTableType.DictWing) {//翅膀逻辑
			for (int i = 0; i < value; i++) {
				WingUtil.addWing(tableFieldId, instPlayerId, syncMsgData);
			}
		} else if (tableTypeId == StaticTableType.DictFightSoul) {//斗魂逻辑
			for (int i = 0; i < value; i++) {
				FightSoulUtil.addFightSoul(instPlayerId, tableFieldId, syncMsgData);
			}
		}
	}

	/**
	 * 处理用map组合起来的tableTypeId_tableFieldId：value形式的物品Map(value是指物品的个数)
	 * 
	 * @author hzw
	 * @date 2014-10-13下午3:27:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void groupThingMap(InstPlayer instPlayer, Map<String, String> thingMap, MessageData syncMsgData, Map<String, Object> msgMap) throws Exception {
		for (Entry<String, String> str : thingMap.entrySet()) {
			int tableTypeId = ConvertUtil.toInt(str.getKey().split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(str.getKey().split("_")[1]);
			int value = ConvertUtil.toInt(str.getValue());
			if (tableTypeId == StaticTableType.DictEquipment) {
				for (int i = 0; i < value; i++) {
					ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, 1, syncMsgData, msgMap);
				}
			} else if (tableTypeId == StaticTableType.DictCard) {
				for (int i = 0; i < value; i++) {
					ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, ConvertUtil.toInt(str.getKey().split("_")[2]), syncMsgData, msgMap);
				}
			} else {
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
			}
		}
	}
	
	/**
	 * 处理用map组合起来的tableTypeId_tableFieldId：value形式的物品Map(value是指物品的个数) 只针对于副本战斗掉落
	 * @author mp
	 * @date 2015-8-23 上午11:01:07
	 * @param instPlayer
	 * @param thingMap
	 * @param syncMsgData
	 * @param msgMap
	 * @throws Exception
	 * @Description
	 */
	public static void groupThingMapBarrier(InstPlayer instPlayer, Map<String, String> thingMap, MessageData syncMsgData, Map<String, Object> msgMap) throws Exception {
		for (Entry<String, String> str : thingMap.entrySet()) {
			int tableTypeId = ConvertUtil.toInt(str.getKey().split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(str.getKey().split("_")[1]);
			int value = ConvertUtil.toInt(str.getValue());
			if (tableTypeId == StaticTableType.DictEquipment) {
				for (int i = 0; i < value; i++) {
					ThingUtil.groupThingBarrier(instPlayer, tableTypeId, tableFieldId, 1, syncMsgData, msgMap);
				}
			} else if (tableTypeId == StaticTableType.DictCard) {
				for (int i = 0; i < value; i++) {
					ThingUtil.groupThingBarrier(instPlayer, tableTypeId, tableFieldId, ConvertUtil.toInt(str.getKey().split("_")[2]), syncMsgData, msgMap);
				}
			} else {
				ThingUtil.groupThingBarrier(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
			}
		}
	}

	/**
	 * 组织tableTypeId_tableFieldId：value形式的物品Map(value是指物品的个数) 只针对于副本战斗掉落
	 * 
	 * @author hzw
	 * @date 2014-10-13下午3:46:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void groupThingMap(Map<String, String> thingMap, int tableTypeId, int tableFieldId, int value) throws Exception {
		if (tableTypeId == StaticTableType.DictCard) {
			// 由于卡牌value代表等级所以对于卡牌特殊处理，按照tableTypeId+"_"+tableFieldId+"_"+value 作为Key，每组织一次就是多增加一张同等级的卡牌
			if (thingMap.containsKey(tableTypeId + "_" + tableFieldId + "_" + value)) {
				int values = ConvertUtil.toInt(thingMap.get(tableTypeId + "_" + tableFieldId + "_" + value)) + 1;
				thingMap.put(tableTypeId + "_" + tableFieldId + "_" + value, values + "");
			} else {
				thingMap.put(tableTypeId + "_" + tableFieldId + "_" + value, 1 + "");
			}
		} else {
			if (thingMap.containsKey(tableTypeId + "_" + tableFieldId)) {
				int values = ConvertUtil.toInt(thingMap.get(tableTypeId + "_" + tableFieldId)) + value;
				thingMap.put(tableTypeId + "_" + tableFieldId, values + "");
			} else {
				thingMap.put(tableTypeId + "_" + tableFieldId, value + "");
			}
		}
	}
	
	/**
	 * 组织tableTypeId_tableFieldId：value形式的物品Map(value是指物品的个数) 
	 * @author mp
	 * @date 2015-8-23 上午11:01:53
	 * @param thingMap
	 * @param tableTypeId
	 * @param tableFieldId
	 * @param value
	 * @throws Exception
	 * @Description
	 */
	public static void groupThingMapBarrier(Map<String, String> thingMap, int tableTypeId, int tableFieldId, int value) throws Exception {
		if (tableTypeId == StaticTableType.DictCard) {
			// 由于卡牌value代表等级所以对于卡牌特殊处理，按照tableTypeId+"_"+tableFieldId+"_"+value 作为Key，每组织一次就是多增加一张同等级的卡牌
			if (thingMap.containsKey(tableTypeId + "_" + tableFieldId + "_" + value)) {
				int values = ConvertUtil.toInt(thingMap.get(tableTypeId + "_" + tableFieldId + "_" + value)) + 1;
				thingMap.put(tableTypeId + "_" + tableFieldId + "_" + value, values + "");
			} else {
				thingMap.put(tableTypeId + "_" + tableFieldId + "_" + value, 1 + "");
			}
		} else {
			if (thingMap.containsKey(tableTypeId + "_" + tableFieldId)) {
				int values = ConvertUtil.toInt(thingMap.get(tableTypeId + "_" + tableFieldId)) + value;
				thingMap.put(tableTypeId + "_" + tableFieldId, values + "");
			} else {
				thingMap.put(tableTypeId + "_" + tableFieldId, value + "");
			}
		}
	}

	@Test
	public void test() {
		try {
			SpringUtil.getSpringContext();
			DictData.loadDictData();
			MessageData syncMsgData = new MessageData();
			int instPlayerId = 31;
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			Map<String, Integer> things = Maps.newHashMap();
			// 卡牌
			for (int i = 1; i <= 153; i++) {
				things.put(StaticTableType.DictCard + "_" + i, 1);
			}
			// 魔盒
			for (int i = 18; i <= 68; i++) {
				things.put(StaticTableType.DictThing + "_" + i, 5);
			}
			// 装备碎片
			for (int i = 200; i <= 343; i++) {
				things.put(StaticTableType.DictThing + "_" + i, 1);
			}
			// 装备
			for (int i = 1; i <= 144; i++) {
				things.put(StaticTableType.DictEquipment + "_" + i, 1);
			}
			// 丹药
			Map<Integer, Integer> dictPills = new HashMap<Integer, Integer>();
			dictPills.put(1, 6);
			dictPills.put(10, 15);
			dictPills.put(19, 24);
			dictPills.put(28, 33);
			dictPills.put(37, 42);
			dictPills.put(46, 51);
			for (Entry<Integer, Integer> entry : dictPills.entrySet()) {
				Integer key = entry.getKey();
				for (int i = key; i <= entry.getValue(); i++) {
					things.put(StaticTableType.DictPill + "_" + i, 50);
				}
			}
			// 其它物品
			Map<Integer, Integer> thingMap = new HashMap<Integer, Integer>();
			thingMap.put(1, 100);
			thingMap.put(2, 100);
			thingMap.put(3, 100);
			thingMap.put(7, 100);
			thingMap.put(8, 100);
			thingMap.put(12, 100000);
			for (Entry<Integer, Integer> entry : thingMap.entrySet()) {
				things.put(StaticTableType.DictThing + "_" + entry.getKey(), entry.getValue());
			}

			// 功法碎片
			for (int i = 1; i <= 78; i++) {
				things.put(StaticTableType.DictChip + "_" + i, 5);
			}

			// 丹药材料
			for (int i = 1; i <= 60; i++) {
				things.put(StaticTableType.DictPillThing + "_" + i, 50);
			}

			// 添加
			for (Entry<String, Integer> entry : things.entrySet()) {
				int tableTypeId = ConvertUtil.toInt(entry.getKey().split("_")[0]);
				int tableFieldId = ConvertUtil.toInt(entry.getKey().split("_")[1]);
				int value = entry.getValue();
				// groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData);
			}

			// 丹方
			for (Entry<Integer, Integer> entry : dictPills.entrySet()) {
				Integer key = entry.getKey();
				for (int i = key; i <= entry.getValue(); i++) {
					PillUtil.addInstPlayerPillRecipe(instPlayerId, syncMsgData);
				}
			}

			// 丹药材料
			for (int i = 1; i <= 60; i++) {
				PillUtil.addInstPlayerPillThing(instPlayerId, i, 50, syncMsgData);
			}

			// 功法
			for (int i = 1; i <= 105; i++) {
				KungFuUtil.addInstPlayerKungFu(instPlayerId, i, 0);
			}

			System.out.println("添加完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 扣除玩家道具或属性
	 * 
	 * @author 李天喜
	 * @date 2015-8-17上午17:22:13
	 * @param msgMap
	 * @param channelId
	 * @param instPlayer
	 * @param array
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public static boolean costThingOrProperty(Map<String, Object> msgMap, String channelId, InstPlayer instPlayer, String[] array) {
		boolean result = false;
		if (array != null) {
			try {
				Map<Integer, List<int[]>> costMap = new HashMap<Integer, List<int[]>>();

				for (int ai = 0; ai < array.length; ai++) {
					int[] array_1 = StringUtil.string2IntArray(array[ai], '_');
					if (array_1 != null && array_1.length == 3) {
						List<int[]> list = costMap.get(array_1[0]);
						if (list == null) {
							list = new ArrayList<int[]>();
							costMap.put(array_1[0], list);
						}
						list.add(array_1);
					}
				}
				List<InstPlayerThing> things = null;
				List<InstPlayerCardSoul> souls = null;
				Map<Integer, InstPlayerThing> thingMap = new HashMap<Integer, InstPlayerThing>();
				Map<Integer, InstPlayerCardSoul> soulMap = new HashMap<Integer, InstPlayerCardSoul>();
				StringBuilder sb = new StringBuilder();
				for (Entry<Integer, List<int[]>> me : costMap.entrySet()) {
					sb.setLength(0);
					int[] data = null;
					List<int[]> dataList = me.getValue();
					switch (me.getKey()) {
						case StaticTableType.DictThing: {
							sb.append(" instPlayerId = ").append(instPlayer.getId()).append(" and thingId in (");
							for (int ii = 0; ii < dataList.size(); ii++) {
								data = dataList.get(ii);
								if (ii > 0) {
									sb.append(",");
								}
								sb.append(data[1]);
							}
							sb.append(")");
							things = getInstPlayerThingDAL().getList(sb.toString(), instPlayer.getId());
							for (InstPlayerThing t : things) {
								thingMap.put(t.getThingId(), t);
							}
							InstPlayerThing thing = null;
							for (int ii = 0; ii < dataList.size(); ii++) {
								data = dataList.get(ii);
								thing = thingMap.get(data[1]);
								if (thing == null || thing.getNum() < data[2]) {
									DictThing dc = DictMap.dictThingMap.get(data[1] + "");
									if (dc != null) {
										MessageUtil.sendFailMsg(channelId, msgMap, dc.getName() + "数量不足");
									} else {
										MessageUtil.sendFailMsg(channelId, msgMap, "道具数量不足");
									}
									return false;
								}
							}
							break;
						}
						case StaticTableType.DictCardSoul: {
							sb.append(" instPlayerId = ").append(instPlayer.getId()).append(" and cardSoulId in (");
							for (int ii = 0; ii < dataList.size(); ii++) {
								data = dataList.get(ii);
								if (ii > 0) {
									sb.append(",");
								}
								sb.append(data[1]);
							}
							sb.append(")");
							souls = getInstPlayerCardSoulDAL().getList(sb.toString(), instPlayer.getId());
							for (InstPlayerCardSoul t : souls) {
								soulMap.put(t.getCardSoulId(), t);
							}
							InstPlayerCardSoul soul = null;
							for (int ii = 0; ii < dataList.size(); ii++) {
								data = dataList.get(ii);
								soul = soulMap.get(data[1]);
								if (soul == null || soul.getNum() < data[2]) {
									DictCardSoul ds = DictMap.dictCardSoulMap.get(data[1] + "");
									if (ds != null) {
										MessageUtil.sendFailMsg(channelId, msgMap, ds.getName() + "数量不足");
									} else {
										MessageUtil.sendFailMsg(channelId, msgMap, "魂魄数量不足");
									}
									return false;
								}
							}
							break;
						}
						case StaticTableType.DictPlayerBaseProp: {
							for (int ii = 0; ii < dataList.size(); ii++) {
								data = dataList.get(ii);
								switch (data[1]) {
									case StaticPlayerBaseProp.copper: {
										if (Integer.parseInt(instPlayer.getCopper()) < data[2]) {
											MessageUtil.sendFailMsg(channelId, msgMap, "铜币数量不足");
											return false;
										}
										break;
									}
									case StaticPlayerBaseProp.gold: {
										if (instPlayer.getGold() < data[2]) {
											MessageUtil.sendFailMsg(channelId, msgMap, "元宝数量不足");
											return false;
										}
										break;
									}
									case StaticPlayerBaseProp.culture: {
										if (instPlayer.getCulture() < data[2]) {
											MessageUtil.sendFailMsg(channelId, msgMap, "火能数量不足");
											return false;
										}
										break;
									}
									case StaticPlayerBaseProp.exp: {
										if (instPlayer.getExp() < data[2]) {
											MessageUtil.sendFailMsg(channelId, msgMap, "经验数量不足");
											return false;
										}
										break;
									}
									default: {
										MessageUtil.sendFailMsg(channelId, msgMap, "道具类型异常");
										return false;
									}

								}
							}
							break;
						}
					}
				}
				MessageData syncMsgData = new MessageData();
				for (Entry<Integer, List<int[]>> me : costMap.entrySet()) {
					sb.setLength(0);
					int[] data = null;
					List<int[]> dataList = me.getValue();
					switch (me.getKey()) {
						case StaticTableType.DictThing: {
							InstPlayerThing t = null;
							for (int ii = 0; ii < dataList.size(); ii++) {
								data = dataList.get(ii);
								t = thingMap.get(data[1]);
								if (t != null) {
									// 记录日志
									try {
										int source = 0;// 来源-协议号
										String desc = "";// 来源描述
										if (msgMap != null) {
											source = (int) msgMap.get("header");
											desc = DictMap.sysMsgRuleMap.get(source + "").getName();
										}
										int thingId = t.getThingId();
										DictThing dictThing = DictMap.dictThingMap.get(thingId + "");
										String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayer.getId()).getLogHeader() + "|2|" + "" + "|" + "" + "|" + t.getThingId() + "|" + dictThing.getName() + "|" + t.getNum() + "|" + (t.getNum() - data[2]) + "|" + data[2] + "|" + source + "|" + desc;
										LogicLogUtil.info("things", logContent.replace("null", ""));
									} catch (Exception e) {
										LogUtil.error("道具/物品日志错误", e);
									}

									if (t.getNum() <= 0) {
										getInstPlayerThingDAL().deleteById(t.getId(), instPlayer.getId());
										OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, t, t.getId(), "", syncMsgData);
									} else {
										t.setNum(t.getNum() - data[2]);
										if (t.getNum() <= 0) {
											getInstPlayerThingDAL().deleteById(t.getId(), instPlayer.getId());
											OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, t, t.getId(), "", syncMsgData);
										} else {
											getInstPlayerThingDAL().update(t, instPlayer.getId());
											OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, t, t.getId(), t.getResult(), syncMsgData);
										}
									}

								}
							}
						}
						case StaticTableType.DictCardSoul: {
							InstPlayerCardSoul s = null;
							for (int ii = 0; ii < dataList.size(); ii++) {
								data = dataList.get(ii);
								s = soulMap.get(data[1]);
								if (s != null) {
									// 记录日志
									try {
										int source = 0;// 来源-协议号
										String desc = "";// 来源描述
										if (msgMap != null) {
											source = (int) msgMap.get("header");
											desc = DictMap.sysMsgRuleMap.get(source + "").getName();
										}
										int soulId = s.getCardSoulId();
										DictCardSoul dictSoul = DictMap.dictCardSoulMap.get(soulId + "");
										String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayer.getId()).getLogHeader() + "|9|" + "" + "|" + "" + "|" + s.getCardSoulId() + "|" + dictSoul.getName() + "|" + s.getNum() + "|" + (s.getNum() - data[2]) + "|" + data[2] + "|" + source + "|" + desc;
										LogicLogUtil.info("cardSoul", logContent.replace("null", ""));
									} catch (Exception e) {
										LogUtil.error("道具/物品日志错误", e);
									}

									if (s.getNum() <= 0) {
										getInstPlayerCardSoulDAL().deleteById(s.getId(), instPlayer.getId());
										OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, s, s.getId(), "", syncMsgData);
									} else {
										s.setNum(s.getNum() - data[2]);
										if (s.getNum() <= 0) {
											getInstPlayerCardSoulDAL().deleteById(s.getId(), instPlayer.getId());
											OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, s, s.getId(), "", syncMsgData);
										} else {
											getInstPlayerCardSoulDAL().update(s, instPlayer.getId());
											OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, s, s.getId(), s.getResult(), syncMsgData);
										}
									}
								}
							}
						}
						case StaticTableType.DictPlayerBaseProp: {
							for (int ii = 0; ii < dataList.size(); ii++) {
								data = dataList.get(ii);
								switch (data[1]) {
									case StaticPlayerBaseProp.copper: {
										instPlayer.setCopper("" + (Integer.parseInt(instPlayer.getCopper()) - data[2]));
										break;
									}
									case StaticPlayerBaseProp.gold: {
										PlayerUtil.goldStatics(instPlayer, 0, data[2], msgMap);
										break;
									}
									case StaticPlayerBaseProp.culture: {
										instPlayer.setCulture(instPlayer.getCulture() - data[2]);
										break;
									}
									case StaticPlayerBaseProp.exp: {
										instPlayer.setExp(instPlayer.getExp() - data[2]);
										break;
									}
								}
							}
							break;
						}
					}
				}
				getInstPlayerDAL().update(instPlayer, instPlayer.getId());
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				MessageUtil.sendSyncMsg(channelId, syncMsgData);
				MessageUtil.sendSuccMsg(channelId, msgMap);
				result = true;
			} catch (Exception e) {
				LogUtil.error("扣除玩家物品异常", e);
			}
		}
		return result;
	}

	public final static String getThingName(int tableTypeId, int tableFieldId) {
		if (tableTypeId == StaticTableType.DictPill) {
			DictPill pill = DictMap.dictPillMap.get(tableFieldId + "");
			if (pill != null) {
				return pill.getName();
			}
		} else if (tableTypeId == StaticTableType.DictThing) {
			DictThing thing = DictMap.dictThingMap.get(tableFieldId + "");
			if (thing != null) {
				return thing.getName();
			}
		} else if (tableTypeId == StaticTableType.DictPlayerBaseProp) {
			if (tableFieldId == StaticPlayerBaseProp.copper) {
				return "银币";
			} else if (tableFieldId == StaticPlayerBaseProp.gold) {
				return "元宝";
			} else if (tableFieldId == StaticPlayerBaseProp.culture) {
				return "火能石";
			} else if (tableFieldId == StaticPlayerBaseProp.exp) {
				return "经验";
			} else if (tableFieldId == StaticPlayerBaseProp.prestige) {
				return "威望";
			}
		} else if (tableTypeId == StaticTableType.DictEquipment) {
			DictEquipment thing = DictMap.dictEquipmentMap.get(tableFieldId + "");
			if (thing != null) {
				return thing.getName();
			}
		} else if (tableTypeId == StaticTableType.DictCard) {
			DictCard thing = DictMap.dictCardMap.get(tableFieldId + "");
			if (thing != null) {
				return thing.getName();
			}
		} else if (tableTypeId == StaticTableType.DictCardSoul) {
			DictCardSoul thing = DictMap.dictCardSoulMap.get(tableFieldId + "");
			if (thing != null) {
				return thing.getName();
			}
		} else if (tableTypeId == StaticTableType.DictChip) {
			DictChip thing = DictMap.dictChipMap.get(tableFieldId + "");
			if (thing != null) {
				return thing.getName();
			}
		} else if (tableTypeId == StaticTableType.DictPillThing) {
			DictPillThing thing = DictMap.dictPillThingMap.get(tableFieldId + "");
			if (thing != null) {
				return thing.getName();
			}
		} else if (tableTypeId == StaticTableType.DictManualSkill) {
			DictManualSkill thing = DictMap.dictManualSkillMap.get(tableFieldId + "");
			if (thing != null) {
				return thing.getName();
			}
		} else if (tableTypeId == StaticTableType.DictMagic) {
			DictMagic thing = DictMap.dictMagicMap.get(tableFieldId + "");
			if (thing != null) {
				return thing.getName();
			}
		}
		return "";
	}
	
	/**
	 * 获取商店买体力丹/耐力丹的时候,应付价格
	 * @author mp
	 * @date 2015-9-1 上午1:54:27
	 * @return
	 * @Description
	 */
	public static int getStoreBuyPrice (int thingId, int buyNum) {
		int price = 0;
		DictThingExtend dictThingExtend = DictMap.dictThingExtendMap.get(thingId + "");
		if (dictThingExtend != null) {
			String extend[] = dictThingExtend.getExtend().split(";");
			for (String str : extend) {
				int down = ConvertUtil.toInt(str.split("_")[0]);
				int up = ConvertUtil.toInt(str.split("_")[1]);
				int gold = ConvertUtil.toInt(str.split("_")[2]);
				if (down <= buyNum && buyNum <= up) {
					price = gold;
					break;
				}
			}
		}
		return price;
	}
	
}
