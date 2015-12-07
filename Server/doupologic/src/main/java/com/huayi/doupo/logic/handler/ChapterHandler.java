package com.huayi.doupo.logic.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictActivitySoulChapterDrop;
import com.huayi.doupo.base.model.DictBarrier;
import com.huayi.doupo.base.model.DictBarrierDrop;
import com.huayi.doupo.base.model.DictBarrierDropType;
import com.huayi.doupo.base.model.DictBarrierLevel;
import com.huayi.doupo.base.model.DictChapter;
import com.huayi.doupo.base.model.DictLevelProp;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.DictVIP;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBarrier;
import com.huayi.doupo.base.model.InstPlayerChapter;
import com.huayi.doupo.base.model.InstPlayerChapterType;
import com.huayi.doupo.base.model.InstPlayerStore;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticActivityHoliday;
import com.huayi.doupo.base.model.statics.StaticBagType;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticDailyTask;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.DateUtil.DateType;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.ChapterUtil;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.VerifyUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class ChapterHandler extends BaseHandler{
		
	/**
	 * 副本——普通战斗
	 * @author hzw
	 * @date 2014-8-20上午10:42:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 *//*
	@SuppressWarnings({ "unchecked" })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void commonWar(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		int barrierLevelId = (Integer)msgMap.get("barrierLevelId");
		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
		DictBarrier dictBarrier = DictMap.dictBarrierMap.get(dictBarrierLevel.getBarrierId() + "");
		DictChapter dictChapter = DictMap.dictChapterMap.get(dictBarrier.getChapterId() + "");
		
		//判断是否有资格
		String currTime = DateUtil.getCurrTime();
		if(dictBarrierLevel.getBarrierId() -1 != 0){
			int barrierId = dictBarrierLevel.getBarrierId();
			if(dictBarrierLevel.getLevel() == 1){
				barrierId = DictList.dictBarrierList.get(DictList.dictBarrierList.indexOf(dictBarrier) - 1).getId();
			}
			List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList(" instPlayerId = " + instPlayerId + " and barrierId = " + barrierId, instPlayerId); 
			if(instPlayerBarrierList.size() <= 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
				return;
			}else{
				if(dictBarrierLevel.getLevel() - instPlayerBarrierList.get(0).getBarrierLevel() > 1){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
					return;
				}
				instPlayerBarrierList = getInstPlayerBarrierDAL().getList(" instPlayerId = " + instPlayerId + " and barrierId = " + dictBarrierLevel.getBarrierId(), instPlayerId); 
				if(instPlayerBarrierList.size() > 0){
					if(instPlayerBarrierList.get(0).getFightNum() + 1 > dictBarrier.getFightNum()  && DateUtil.isSameDay(instPlayerBarrierList.get(0).getOperTime(), currTime, DateFormat.YMDHMS)){
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFightNum);
						return;
					}
				}
			}
		}
		
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(instPlayer.getLevel() < dictChapter.getOpenLeve()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPlayerLevel);
			return;
		}
		if(instPlayer.getEnergy() < dictBarrier.getEnergy()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notEnergy);
			return;
		}
		
		//用于验证玩家是否利用烧饼等修改器
		if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
			return;
		}
		
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
		
		int beforeLevel = instPlayer.getLevel();
		
		//处理战队能达到多少级和剩余经验
		FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + dictLevelProp.getOneWarExp(), syncMsgData, msgMap);
		
		int afterLevel = instPlayer.getLevel();
		
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictBarrierLevel.getCopper()) + "");
		instPlayer.setCulture(instPlayer.getCulture() + dictBarrierLevel.getCulture());
		if(instPlayer.getEnergy() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy) &&  (instPlayer.getEnergy() - dictBarrier.getEnergy()) < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy)){
			instPlayer.setLastEnergyRecoverTime(currTime);
		}
		instPlayer.setEnergy(instPlayer.getEnergy() - dictBarrier.getEnergy());
		ChapterUtil.auctionShopIsOpen(instPlayer, 1, syncMsgData);  		//记录玩家副本战斗胜利次数判断拍卖行是否开启
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		String things = "";
		List<DictBarrierDrop> objList = (List<DictBarrierDrop>)DictMapList.dictBarrierDropMap.get(dictBarrierLevel.getBarrierId());
		Set<Integer> hashSet = new HashSet<Integer>(); //用于存储掉落物品类型，保证不重复
		for(DictBarrierDrop obj : objList){
			if(obj.getType() == 0 && obj.getChance() != 0){
				hashSet.add(obj.getType2());
			}
		}
		Map<String, String> thingMap = new HashMap<String, String>(); //掉落的物品map
		Iterator<Integer> iterator = hashSet.iterator();
		while(iterator.hasNext()){
			int type2 = iterator.next();
			List<DictBarrierDropType> objTypeList = (List<DictBarrierDropType>)DictMapList.dictBarrierDropTypeMap.get(type2);
			
			//得到此类型物品的掉落个数
			float random = RandomUtil.getRandomFloat();
			int thingNum = 0;
			for(DictBarrierDropType obj : objTypeList){
				if(obj.getType() == type2){
					if(random <= obj.getPr()){
						thingNum = obj.getValue();
						break;
					}
				}
			}
			
			//得到此类物品的总点数
			int num = 0;
			for(DictBarrierDrop obj : objList){
				if(obj.getType() == 0 && obj.getType2() == type2){
					num += obj.getChance();
				}
			}
			
			//根据物品个数算出掉落的物品
			for(int i = 1; i <= thingNum; i++){
				random = RandomUtil.getRandomFloat();
				Float sum = 0.00f;
				for(DictBarrierDrop obj : objList){
					if(obj.getType() == 0 && obj.getType2() == type2){
						sum += (float)obj.getChance() / (float)num;
						if(random <= sum){
							things += obj.getId() + ";";
							int tableTypeId = obj.getTableTypeId();
							int tableFieldId = obj.getTableFieldId();
							int value = obj.getValue();
							ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);
							break;
						}
					}
				}
			}
		}
		
		int thing = 0;
		float random = RandomUtil.getRandomFloat();
		if(random < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.chapterWarDropChance)){
			Float num = 0.00f;
			for(DictBarrierDrop obj : objList){
				if(obj.getType() == 1){
					num += obj.getChance();
				}
			}
			random = RandomUtil.getRandomFloat();
			Float sum = 0.00f;
			for(DictBarrierDrop obj : objList){
				if(obj.getType() == 1){
					sum += (float)obj.getChance() / (float)num;
					if(random <= sum){
						thing = obj.getId();
						int tableTypeId = obj.getTableTypeId();
						int tableFieldId = obj.getTableFieldId();
						int value = obj.getValue();
						ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);
						break;
					}
				}
			}
		}
		
		ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);
		
		//3-1[意外获得], 翻天印碎片一295
		int barrierId = dictBarrier.getId();
		List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + barrierId, instPlayerId);
		if (instPlayerBarrierList.size() <= 0) {
			if (barrierId == 16) {
				int tableTypeId = StaticTableType.DictChip;
				int tableFieldId = 295;
				int value = 1;
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
				thing = 100001;
			}
		} else {
			//一般的时候，输了后来又赢了应该不会再输了，对于那种赢了又输了的情况暂不处理了[这会导致可能会多发翻天印碎片]
			if (instPlayerBarrierList.get(0).getBarrierLevel() == 0 && barrierId == 16) {
				int tableTypeId = StaticTableType.DictChip;
				int tableFieldId = 295;
				int value = 1;
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
				thing = 100001;
			}
		}
		
		//3-2[意外获得], 翻天印碎片二296
		if (instPlayerBarrierList.size() <= 0) {
			if (barrierId == 17) {
				int tableTypeId = StaticTableType.DictChip;
				int tableFieldId = 296;
				int value = 1;
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
				thing = 100002;
			}
		} else {
			//一般的时候，输了后来又赢了应该不会再输了，对于那种赢了又输了的情况不处理了[这会导致可能会多发翻天印碎片]
			if (instPlayerBarrierList.get(0).getBarrierLevel() == 0 && barrierId == 17) {
				int tableTypeId = StaticTableType.DictChip;
				int tableFieldId = 296;
				int value = 1;
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
				thing = 100002;
			}
		}
		
		//添加玩家副本关卡等级实例表
		ChapterUtil.initInstPlayerBarrierLevel(instPlayerId, barrierLevelId, 1, currTime, syncMsgData);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.generBarrier, 1, syncMsgData);
		
		//引导给东西[意外获得]  1-指定关卡的某一步给韩雪魂魄31  2-升级到7级后给韩雪魂魄31  后修改成给萧玉魂魄38 2015-04-07 修改人：王力,孙毅
		if (beforeLevel != afterLevel && afterLevel == 7) {
			int tableTypeId = StaticTableType.DictCardSoul;
			int tableFieldId = 38;
			int value = 5;
			ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
			thing = 100000;
		}
		
		if (instPlayer.getGuipedBarrier().length() > 2) {
			if (instPlayer.getGuipedBarrier().split("&")[0].equals("6B10") || instPlayer.getGuipedBarrier().split("&")[0].equals("7B7")) {
				
				//加验证,给过了就不给了
				String givedLevelGuidStep = instPlayer.getGuipedBarrier().split("&")[1];
				if (!StringUtil.contains(givedLevelGuidStep, instPlayer.getGuipedBarrier().split("&")[0], ";")) {
					int tableTypeId = StaticTableType.DictCardSoul;
					int tableFieldId = 38;
					int value = 5;
					ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
					thing = 100000;
					instPlayer.setGuipedBarrier(instPlayer.getGuipedBarrier() + instPlayer.getGuipedBarrier().split("&")[0] + ";");
					getInstPlayerDAL().update(instPlayer, 0);//服务器用,不需要给客户端同步
				}
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageData retMsgData = new MessageData();
		if(things.length() > 0){
			retMsgData.putStringItem("1", things.substring(0,things.length() - 1));
		}
		if(thing != 0){
			retMsgData.putIntItem("2", thing);
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	*/
	/** 副本——普通战斗
	 * @author hzw
	 * @date 2014-8-20上午10:42:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked" })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void commonWar(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int barrierLevelId = (Integer)msgMap.get("barrierLevelId");
//		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
		DictBarrier dictBarrier = DictMap.dictBarrierMap.get(dictBarrierLevel.getBarrierId() + "");
		DictChapter dictChapter = DictMap.dictChapterMap.get(dictBarrier.getChapterId() + "");
		
		//判断是否有资格
		String currTime = DateUtil.getCurrTime();
		if(dictBarrierLevel.getBarrierId() -1 != 0){
			int barrierId = dictBarrierLevel.getBarrierId();
//			if(dictBarrierLevel.getLevel() == 1){
				barrierId = DictList.dictBarrierList.get(DictList.dictBarrierList.indexOf(dictBarrier) - 1).getId();
//			}
			List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList(" instPlayerId = " + instPlayerId + " and barrierId = " + barrierId, instPlayerId); 
			if(instPlayerBarrierList.size() <= 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
				return;
			}else{
//				if(dictBarrierLevel.getLevel() - instPlayerBarrierList.get(0).getBarrierLevel() > 1){
//					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
//					return;
//				}
				instPlayerBarrierList = getInstPlayerBarrierDAL().getList(" instPlayerId = " + instPlayerId + " and barrierId = " + dictBarrierLevel.getBarrierId(), instPlayerId); 
				if(instPlayerBarrierList.size() > 0){
					if(instPlayerBarrierList.get(0).getFightNum() + 1 > dictBarrier.getFightNum()  && DateUtil.isSameDay(instPlayerBarrierList.get(0).getOperTime(), currTime, DateFormat.YMDHMS)){
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFightNum);
						return;
					}
				}
			}
		}
		
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(instPlayer.getLevel() < dictChapter.getOpenLeve()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPlayerLevel);
			return;
		}
		if(instPlayer.getEnergy() < dictBarrier.getEnergy()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notEnergy);
			return;
		}
		
		//用于验证玩家是否利用烧饼等修改器
//		if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
//			return;
//		}
		
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
		
		//在节假日活动期内,经验n倍
		float multipleExp = 1f;
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.barrierExp)) {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.barrierExp + "");
			multipleExp = activityHoliday.getMultiple();
		}
		
		int resultExp = (int)(dictLevelProp.getOneWarExp() * multipleExp);
		
		//处理战队能达到多少级和剩余经验
		FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + (int)(dictLevelProp.getOneWarExp() * multipleExp), syncMsgData, msgMap);
		
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictBarrierLevel.getCopper()) + "");
		instPlayer.setCulture(instPlayer.getCulture() + dictBarrierLevel.getCulture());
		if(instPlayer.getEnergy() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy) &&  (instPlayer.getEnergy() - dictBarrier.getEnergy()) < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy)){
			instPlayer.setLastEnergyRecoverTime(currTime);
		}
		instPlayer.setEnergy(instPlayer.getEnergy() - dictBarrier.getEnergy());
		ChapterUtil.auctionShopIsOpen(instPlayer, 1, syncMsgData);  		//记录玩家副本战斗胜利次数判断拍卖行是否开启
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		String things = "";
		List<DictBarrierDrop> objList = (List<DictBarrierDrop>)DictMapList.dictBarrierDropMap.get(dictBarrierLevel.getBarrierId());
		Set<Integer> hashSet = new HashSet<Integer>(); //用于存储掉落物品类型，保证不重复
		for(DictBarrierDrop obj : objList){
			if(obj.getType() == 0 && obj.getChance() != 0){
				hashSet.add(obj.getType2());
			}
		}
		
		Map<String, String> thingMap = new HashMap<String, String>(); //掉落的物品map
		Iterator<Integer> iterator = hashSet.iterator();
		while(iterator.hasNext()){
			int type2 = iterator.next();
			List<DictBarrierDropType> objTypeList = (List<DictBarrierDropType>)DictMapList.dictBarrierDropTypeMap.get(type2);
			
			//得到此类型物品的掉落个数
			float random = RandomUtil.getRandomFloat();
			int thingNum = 0;
			Float randomSum = 0.00f;
			for(DictBarrierDropType obj : objTypeList){
				if(obj.getType() == type2){
					randomSum += obj.getPr();
					if(random <= randomSum){
						thingNum = obj.getValue();
						break;
					}
				}
			}
			
			//得到此类物品的总点数
			int num = 0;
			for(DictBarrierDrop obj : objList){
				if(obj.getType() == 0 && obj.getType2() == type2){
					num += obj.getChance();
				}
			}
			
			//根据物品个数算出掉落的物品
			for(int i = 1; i <= thingNum; i++){
				random = RandomUtil.getRandomFloat();
				Float sum = 0.00f;
				for(DictBarrierDrop obj : objList){
					if(obj.getType() == 0 && obj.getType2() == type2){
						sum += (float)obj.getChance() / (float)num;
						if(random <= sum){
							int tableTypeId = obj.getTableTypeId();
							int tableFieldId = obj.getTableFieldId();
							int value = obj.getValue();
							if(value != 0){
//								things += obj.getId() + ";";
								//在节假日活动期内,掉落个数n倍
								float multipleDrop = 1f;
								if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.barrierDrop)) {
									DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.barrierDrop + "");
									multipleDrop = activityHoliday.getMultiple();
								}
								if (tableTypeId == StaticTableType.DictCard) {
									multipleDrop = 1f;//当掉落的是卡牌的时候,不参与双倍；其他物品都参与
								} 
//								System.out.println("tableTypeId=" + tableTypeId + "  tableFieldId=" + tableFieldId + "  value = " + value + " multipleDrop=" + multipleDrop);
								things += tableTypeId + "_" + tableFieldId + "_" + (int)(value * multipleDrop) + ";";
								ThingUtil.groupThingMapBarrier(thingMap, tableTypeId, tableFieldId, (int)(value * multipleDrop));
							}
							break;
						}
					}
				}
			}
		}
		
		int thing = 0;
		float random = RandomUtil.getRandomFloat();
		if(random < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.chapterWarDropChance)){
			objList = (List<DictBarrierDrop>)DictMapList.dictBarrierDropMap.get(5000);
			Float num = 0.00f;
			for(DictBarrierDrop obj : objList){
				if(obj.getType() == 1){
					num += obj.getChance();
				}
			}
			random = RandomUtil.getRandomFloat();
			Float sum = 0.00f;
			for(DictBarrierDrop obj : objList){
				if(obj.getType() == 1){
					sum += (float)obj.getChance() / (float)num;
					if(random <= sum){
						int tableTypeId = obj.getTableTypeId();
						int tableFieldId = obj.getTableFieldId();
						int value = obj.getValue();
						if(value != 0){
							thing = obj.getId();
							ThingUtil.groupThingMapBarrier(thingMap, tableTypeId, tableFieldId, value);
						}
						break;
					}
				}
			}
		}
		ThingUtil.groupThingMapBarrier(instPlayer, thingMap, syncMsgData, msgMap);
		
		//3-1[意外获得], 翻天印碎片一295
		int barrierId = dictBarrier.getId();
		List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + barrierId, instPlayerId);
		if (instPlayerBarrierList.size() <= 0) {
			if (barrierId == 19) {
				int tableTypeId = StaticTableType.DictChip;
				int tableFieldId = 94;
				int value = 1;
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
				thing = 100001;
			}
		} else {
			//一般的时候，输了后来又赢了应该不会再输了，对于那种赢了又输了的情况暂不处理了[这会导致可能会多发翻天印碎片]
			if (instPlayerBarrierList.get(0).getBarrierLevel() == 0 && barrierId == 19) {
				int tableTypeId = StaticTableType.DictChip;
				int tableFieldId = 94;
				int value = 1;
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
				thing = 100001;
			}
		}
		
		//3-2[意外获得], 翻天印碎片二296
		if (instPlayerBarrierList.size() <= 0) {
			if (barrierId == 20) {
				int tableTypeId = StaticTableType.DictChip;
				int tableFieldId = 95;
				int value = 1;
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
				thing = 100002;
			}
		} else {
			//一般的时候，输了后来又赢了应该不会再输了，对于那种赢了又输了的情况不处理了[这会导致可能会多发翻天印碎片]
			if (instPlayerBarrierList.get(0).getBarrierLevel() == 0 && barrierId == 20) {
				int tableTypeId = StaticTableType.DictChip;
				int tableFieldId = 95;
				int value = 1;
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
				thing = 100002;
			}
		}
		
		//添加玩家副本关卡等级实例表
		ChapterUtil.initInstPlayerBarrierLevel(instPlayerId, barrierLevelId, 1, currTime, syncMsgData);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.generBarrier, 1, syncMsgData);
		
		//引导给东西[意外获得]  1-指定关卡的某一步给韩雪魂魄31  2-升级到7级后给韩雪魂魄31  后修改成给萧玉魂魄38 2015-04-07 修改人：王力,孙毅
//		if (beforeLevel != afterLevel && afterLevel == 7) {
//			int tableTypeId = StaticTableType.DictCardSoul;
//			int tableFieldId = 38;
//			int value = 5;
//			ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
//			thing = 100000;
//		}
		
		if (instPlayer.getGuipedBarrier().length() > 2) {
			if (instPlayer.getGuipedBarrier().split("&")[0].equals("6B11") || instPlayer.getGuipedBarrier().split("&")[0].equals("7B8")) {
				
				//加验证,给过了就不给了
				String givedLevelGuidStep = "";
				int length = instPlayer.getGuipedBarrier().split("&").length;
				if (length > 1) {
					givedLevelGuidStep = instPlayer.getGuipedBarrier().split("&")[1];
				}
				if (givedLevelGuidStep.equals("") || !StringUtil.contains(givedLevelGuidStep, instPlayer.getGuipedBarrier().split("&")[0], ";")) {
					int tableTypeId = StaticTableType.DictCardSoul;
					int tableFieldId = 38;
					int value = 5;
					ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
					thing = 100000;
					instPlayer.setGuipedBarrier(instPlayer.getGuipedBarrier() + instPlayer.getGuipedBarrier().split("&")[0] + ";");
					getInstPlayerDAL().update(instPlayer, 0);//服务器用,不需要给客户端同步
				}
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageData retMsgData = new MessageData();
		if(things.length() > 0){
			retMsgData.putStringItem("1", things.substring(0,things.length() - 1));
		}
		if(thing != 0){
			retMsgData.putIntItem("2", thing);
		}
		retMsgData.putIntItem("3", resultExp);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 一键战斗
	 * @author hzw
	 * @date 2014-8-21上午11:33:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked" })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aKeyCommonWar(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerBarrierId = (Integer)msgMap.get("instPlayerBarrierId");
		int barrierLevelId = (Integer)msgMap.get("barrierLevelId");
		int fightNum = (Integer)msgMap.get("fightNum");
		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
		DictBarrier dictBarrier = DictMap.dictBarrierMap.get(dictBarrierLevel.getBarrierId() + "");
		DictChapter dictChapter = DictMap.dictChapterMap.get(dictBarrier.getChapterId() + "");
		
		//判断是否有资格
		String currTime = DateUtil.getCurrTime();
		/*List<InstPlayerChapterType> instPlayerChapterTypeList = getInstPlayerChapterTypeDAL().getList(" instPlayerId = " + instPlayerId + " and chapterType = " + 1, instPlayerId); 
		if(instPlayerChapterTypeList.size() > 0){
			InstPlayerChapterType instPlayerChapterType = instPlayerChapterTypeList.get(0);
			if(instPlayerChapterType.getAKeyTime() != null && !instPlayerChapterType.getAKeyTime().equals("") && instPlayerChapterType.getAKeyTime().equals(0)){
				long freeTime = DateUtil.getMillSecond(instPlayerChapterType.getAKeyTime());
				long gap = (DateUtil.getCurrMill() - freeTime) < 0 ? 0 : (DateUtil.getCurrMill() - freeTime);
				if (gap != 0) {
					gap = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterAKeyTime) * 60 * 60 * 1000 - gap;
					if(gap > 0){
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotTime);
						return;
					}
				}
			}
		}*/
		
		//客户端传来的参数 不能小于等于0
		if (fightNum <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		InstPlayerBarrier instPlayerBarrier = getInstPlayerBarrierDAL().getModel(instPlayerBarrierId, instPlayerId);
		if (instPlayerBarrier.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerBarrier == null || dictBarrierLevel.getLevel() - instPlayerBarrier.getBarrierLevel() > 1){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
			return;
		}
		if(instPlayerBarrier.getFightNum() + fightNum > dictBarrier.getFightNum()  && DateUtil.isSameDay(instPlayerBarrier.getOperTime(), currTime, DateFormat.YMDHMS)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFightNum);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(instPlayer.getLevel() < dictChapter.getOpenLeve()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPlayerLevel);
			return;
		}
		if(instPlayer.getEnergy() < dictBarrier.getEnergy() * fightNum){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notEnergy);
			return;
		}
		//用于验证玩家是否利用烧饼等修改器
		if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
			return;
		}
		
		//加vip和开启等级验证 2015-09-25 去掉vip限制 -修改人：王力
//		DictVIP dictVIP = DictMap.dictVIPMap.get((instPlayer.getVipLevel() + 1) + "");
//		if (dictVIP != null) {
		if (instPlayer.getLevel() < DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.continuFight + "").getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
//		}
		
		//在节假日活动期内,经验n倍
		float multipleExp = 1f;
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.barrierExp)) {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.barrierExp + "");
			multipleExp = activityHoliday.getMultiple();
		}
		
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
		
		int resultExp = (int)(dictLevelProp.getOneWarExp() * multipleExp);
		
		//处理战队能达到多少级和剩余经验
		FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + (int)(dictLevelProp.getOneWarExp() * multipleExp) * fightNum, syncMsgData, msgMap);
		
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictBarrierLevel.getCopper() * fightNum) + "");
		if(instPlayer.getEnergy() > DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy) &&  instPlayer.getEnergy() - dictBarrier.getEnergy() * fightNum < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy)){
			instPlayer.setLastEnergyRecoverTime(currTime);
		}
		instPlayer.setCulture(instPlayer.getCulture() + dictBarrierLevel.getCulture() * fightNum);
		if(instPlayer.getEnergy() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy) &&  (instPlayer.getEnergy() - (dictBarrier.getEnergy() * fightNum)) < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxEnergy)){
			instPlayer.setLastEnergyRecoverTime(currTime);
		}
		instPlayer.setEnergy(instPlayer.getEnergy() - dictBarrier.getEnergy() * fightNum);
		ChapterUtil.auctionShopIsOpen(instPlayer, fightNum, syncMsgData);  		//记录玩家副本战斗胜利次数判断拍卖行是否开启
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		String things = "";
		String thing = "";
		List<DictBarrierDrop> objList = (List<DictBarrierDrop>)DictMapList.dictBarrierDropMap.get(dictBarrierLevel.getBarrierId());
		Set<Integer> hashSet = new HashSet<Integer>(); //用于存储掉落物品类型，保证不重复
		for(DictBarrierDrop obj : objList){
			if(obj.getType() == 0 && obj.getChance() != 0){
				hashSet.add(obj.getType2());
			}
		}
		
		Map<String, String> thingMap = new HashMap<String, String>(); //掉落的物品map
		Iterator<Integer> iterator = hashSet.iterator();
		boolean tag = false;
		for(int a = 1; a <= fightNum; a++){
			tag = false;
			things +=  "|" + a + ":";
			iterator = hashSet.iterator();
			while(iterator.hasNext()){
				int type2 = iterator.next();
				List<DictBarrierDropType> objTypeList = (List<DictBarrierDropType>)DictMapList.dictBarrierDropTypeMap.get(type2);
				
				//得到此类型物品的掉落个数
				float random = RandomUtil.getRandomFloat();
				int thingNum = 0;
				Float randomSum = 0.00f;
				for(DictBarrierDropType obj : objTypeList){
					if(obj.getType() == type2){
						randomSum += obj.getPr();
						if(random <= randomSum){
							thingNum = obj.getValue();
							break;
						}
					}
				}
				
				//得到此类物品的总点数
				int num = 0;
				for(DictBarrierDrop obj : objList){
					if(obj.getType() == 0 && obj.getType2() == type2){
						num += obj.getChance();
					}
				}
				
				//根据物品个数算出掉落的物品
				for(int i = 1; i <= thingNum; i++){
					random = RandomUtil.getRandomFloat();
					Float sum = 0.00f;
					for(DictBarrierDrop obj : objList){
						if(obj.getType() == 0 && obj.getType2() == type2){
							sum += (float)obj.getChance() / (float)num;
							if(random <= sum){
								int tableTypeId = obj.getTableTypeId();
								int tableFieldId = obj.getTableFieldId();
								int value = obj.getValue();
								if(value != 0){
//									things += obj.getId() + ";";
									//在节假日活动期内,掉落个数n倍
									float multipleDrop = 1f;
									if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.barrierDrop)) {
										DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.barrierDrop + "");
										multipleDrop = activityHoliday.getMultiple();
									}
									if (tableTypeId == StaticTableType.DictCard) {
										multipleDrop = 1f;//当掉落的是卡牌的时候,不参与双倍；其他物品都参与
									}
									things += tableTypeId + "_" + tableFieldId + "_" + (int)(value * multipleDrop) + ";";
									ThingUtil.groupThingMapBarrier(thingMap, tableTypeId, tableFieldId, (int)(value * multipleDrop));
									tag = true;
								}
								break;
							}
						}
					}
				}
			}
			if(tag){
				things = things.substring(0, things.length() - 1);
			}
			
			//意外掉落
			float random = RandomUtil.getRandomFloat();
			if(random < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.chapterWarDropChance)){
				List<DictBarrierDrop> objList2 = (List<DictBarrierDrop>)DictMapList.dictBarrierDropMap.get(5000);
				Float num = 0.00f;
				for(DictBarrierDrop obj : objList2){
					if(obj.getType() == 1){
						num += obj.getChance();
					}
				}
				random = RandomUtil.getRandomFloat();
				Float sum = 0.00f;
				for(DictBarrierDrop obj : objList2){
					if(obj.getType() == 1){
						sum += (float)obj.getChance() / (float)num;
						if(random <= sum){
							int tableTypeId = obj.getTableTypeId();
							int tableFieldId = obj.getTableFieldId();
							int value = obj.getValue();
							if(value != 0){
								thing += obj.getId() + ";";
								ThingUtil.groupThingMapBarrier(thingMap, tableTypeId, tableFieldId, value);
							}
							break;
						}
					}
				}
			}
		}
		
		ThingUtil.groupThingMapBarrier(instPlayer, thingMap, syncMsgData, msgMap);
		
		//更新玩家副本关卡战斗次数实例表
		ChapterUtil.updateInstPlayerBarrierFightNum(instPlayerBarrier, barrierLevelId, fightNum, currTime, syncMsgData);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.generBarrier, fightNum, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageData retMsgData = new MessageData();
		if(things.length() > 0){
			retMsgData.putStringItem("1", things.substring(1));
		}
		if(!thing.equals("")){
			retMsgData.putStringItem("2", thing.substring(0,thing.length() - 1));
		}
		retMsgData.putIntItem("3", resultExp);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 星数开箱
	 * @author hzw
	 * @date 2014-8-21下午4:22:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void chapterOpenBox(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int instPlayerChapterId = (Integer)msgMap.get("instPlayerChapterId");
		int type = (Integer)msgMap.get("type"); //1-一阶段 2-二阶段 3-三阶段
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		//判断是否有资格
		InstPlayerChapter instPlayerChapter = getInstPlayerChapterDAL().getModel(instPlayerChapterId, instPlayerId);
		if (instPlayerChapter.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerChapter.getGetStarType() != null && instPlayerChapter.getGetStarType().contains(type + "")){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notGetStarType);
			return;
		}
		
		DictChapter dictChapter = DictMap.dictChapterMap.get(instPlayerChapter.getChapterId() + "");
		int starNum = dictChapter.getStarOne();
		String things = dictChapter.getThingsOne();
		if(type == 2){
			starNum = dictChapter.getStarTwo();
			things = dictChapter.getThingsTwo();
		}
		if(type == 3){
			starNum = dictChapter.getStarThree();
			things = dictChapter.getThingsThree();
		}
		
		if(instPlayerChapter.getStarNum() < starNum){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notStarNum);
			return;
		}
		
		if(things != null && !things.equals("")){
			for(String str : things.split(";")){
				ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
			}
		}
			
		if(instPlayerChapter.getGetStarType() == null || instPlayerChapter.getGetStarType().equals("")){
			instPlayerChapter.setGetStarType(type + "");
		}else{
			instPlayerChapter.setGetStarType(instPlayerChapter.getGetStarType() + ";" + type);
		}
		getInstPlayerChapterDAL().update(instPlayerChapter, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChapter, instPlayerChapter.getId(), instPlayerChapter.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", things);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 清除连战冷却时间
	 * @author hzw
	 * @date 2014-8-22下午2:25:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void clearColdTime(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerChapterTypeId = (Integer)msgMap.get("instPlayerChapterTypeId");
		
		//判断是否有资格
		String currTime = DateUtil.getCurrTime();
		InstPlayerChapterType instPlayerChapterType = getInstPlayerChapterTypeDAL().getModel(instPlayerChapterTypeId, instPlayerId);
		if (instPlayerChapterType.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(!DateUtil.isSameDay(instPlayerChapterType.getOperTime(), currTime, DateFormat.YMDHMS)){
			instPlayerChapterType.setBuyNum(0);
		}
		int gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterAKeyGold) + (instPlayerChapterType.getBuyNum()) * DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterAKeyGoldAdd);
		if(gold > DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterBuyMaxGold)){
			gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterBuyMaxGold);
		}
		if(instPlayer.getGold() < gold){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		//消耗元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		instPlayerChapterType.setAKeyTime(0 + "");
		instPlayerChapterType.setBuyNum(instPlayerChapterType.getBuyNum() + 1);
		instPlayerChapterType.setOperTime(currTime);
		getInstPlayerChapterTypeDAL().update(instPlayerChapterType, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChapterType, instPlayerChapterType.getId(), instPlayerChapterType.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 普通战斗重置战斗次数
	 * @author hzw
	 * @date 2014-8-25上午11:19:56
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void resetFightNum(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerBarrierId = (Integer)msgMap.get("instPlayerBarrierId");
		
		//判断是否有资格
		String currTime = DateUtil.getCurrTime();
		InstPlayerBarrier instPlayerBarrier = getInstPlayerBarrierDAL().getModel(instPlayerBarrierId, instPlayerId);
		if (instPlayerBarrier.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(!DateUtil.isSameDay(instPlayerBarrier.getOperTime(), currTime, DateFormat.YMDHMS)){
			instPlayerBarrier.setResetNum(0);
		}
		int gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterAKeyGold) + (instPlayerBarrier.getResetNum()) * DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterAKeyGoldAdd);
		if(instPlayer.getGold() < gold){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		//既要验证元宝又要验证vip等级
		DictVIP dictVIP = DictMap.dictVIPMap.get((instPlayer.getVipLevel() + 1) + "");
		if (dictVIP != null) {
			if (dictVIP.getIsResetGenerBarrier() == 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noUpVip);
				return;
			}
			if(instPlayerBarrier.getResetNum()>dictVIP.getChapterResetCount()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notResetNum);
				return;
			}
		}
		
		//消耗元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		instPlayerBarrier.setFightNum(0);
		instPlayerBarrier.setResetNum(instPlayerBarrier.getResetNum() + 1);
		instPlayerBarrier.setOperTime(currTime);
		getInstPlayerBarrierDAL().update(instPlayerBarrier, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerBarrier, instPlayerBarrier.getId(), instPlayerBarrier.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 精英战斗
	 * @author hzw
	 * @date 2014-8-23上午10:43:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliteWar(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int barrierLevelId = (Integer)msgMap.get("barrierLevelId");
//		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
		DictBarrier dictBarrier = DictMap.dictBarrierMap.get(dictBarrierLevel.getBarrierId() + "");
		DictChapter dictChapter = DictMap.dictChapterMap.get(dictBarrier.getChapterId() + "");
		
		//判断是否有资格
		
		int chapterId = dictChapter.getChapterId();
		String currTime = DateUtil.getCurrTime();
		List<InstPlayerChapter> instPlayerChapterList = getInstPlayerChapterDAL().getList(" instPlayerId = " + instPlayerId + " and chapterId = " + chapterId, instPlayerId); 
		if(instPlayerChapterList.size() <= 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
			return;
		}else{
			if(instPlayerChapterList.get(0).getIsPass() == 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
				return;
			}
		}
		
		if (dictChapter.getType() != 4) {
			DictChapter upDictChapter = DictMap.dictChapterMap.get(dictChapter.getId() - 1 + "");
			if(upDictChapter != null){
				List<InstPlayerChapter> upInstPlayerChapterList = getInstPlayerChapterDAL().getList(" instPlayerId = " + instPlayerId + " and chapterId = " + upDictChapter.getId(), instPlayerId); 
				if(upInstPlayerChapterList.size() <= 0){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
					return;
				}else{
					if(upInstPlayerChapterList.get(0).getIsPass() == 0){
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_commonWarNotOpen);
						return;
					}
				}
			}
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(instPlayer.getLevel() < dictChapter.getOpenLeve()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPlayerLevel);
			return;
		}
		
		List<InstPlayerChapterType> instPlayerChapterTypeList = getInstPlayerChapterTypeDAL().getList(" instPlayerId = " + instPlayerId + " and chapterType = " + dictChapter.getType(), instPlayerId); 
		if(instPlayerChapterTypeList.size() > 0){
			if(DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteNum) - instPlayerChapterTypeList.get(0).getFightNum() - 1 < 0  && DateUtil.isSameDay(instPlayerChapterTypeList.get(0).getOperTime(), currTime, DateFormat.YMDHMS) ){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFightNum);
				return;
			}
		}
		//用于验证玩家是否利用烧饼等修改器
//		if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
//			return;
//		}
		
		if (dictChapter.getType() != 4) {
			DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
			//处理战队能达到多少级和剩余经验
			FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + dictLevelProp.getOneEliteWarExp(), syncMsgData, msgMap);
		}
		
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictBarrierLevel.getCopper()) + "");
		instPlayer.setCulture(instPlayer.getCulture() + dictBarrierLevel.getCulture());
		ChapterUtil.auctionShopIsOpen(instPlayer, 1, syncMsgData);  		//记录玩家副本战斗胜利次数判断拍卖行是否开启
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		String things = "";
		List<DictBarrierDrop> objList = (List<DictBarrierDrop>)DictMapList.dictBarrierDropMap.get(dictBarrierLevel.getBarrierId());
		Set<Integer> hashSet = new HashSet<Integer>(); //用于存储掉落物品类型，保证不重复
		for(DictBarrierDrop obj : objList){
			if(obj.getType() == 0 && obj.getChance() != 0){
				hashSet.add(obj.getType2());
			}
		}
		Map<String, String> thingMap = new HashMap<String, String>(); //掉落的物品map
		Iterator<Integer> iterator = hashSet.iterator();
		while(iterator.hasNext()){
			int type2 = iterator.next();
			List<DictBarrierDropType> objTypeList = (List<DictBarrierDropType>)DictMapList.dictBarrierDropTypeMap.get(type2);
			
			//得到此类型物品的掉落个数
			float random = RandomUtil.getRandomFloat();
			int thingNum = 0;
			Float randomSum = 0.00f;
			for(DictBarrierDropType obj : objTypeList){
				if(obj.getType() == type2){
					randomSum += obj.getPr();
					if(random <= randomSum){
						thingNum = obj.getValue();
						break;
					}
				}
			}
			
			//得到此类物品的总点数
			int num = 0;
			for(DictBarrierDrop obj : objList){
				if(obj.getType() == 0 && obj.getType2() == type2){
					num += obj.getChance();
				}
			}
			
			//根据物品个数算出掉落的物品
			for(int i = 1; i <= thingNum; i++){
				random = RandomUtil.getRandomFloat();
				Float sum = 0.00f;
				for(DictBarrierDrop obj : objList){
					if(obj.getType() == 0 && obj.getType2() == type2){
						sum += (float)obj.getChance() / (float)num;
						if(random <= sum){
							int tableTypeId = obj.getTableTypeId();
							int tableFieldId = obj.getTableFieldId();
							int value = obj.getValue();
							if(value != 0){
//								things += obj.getId() + ";";
								things += tableTypeId + "_" + tableFieldId + "_" + value  + ";";
								ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);
							}
							break;
						}
					}
				}
			}
		}
		ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);
		
		//添加玩家副本章节类型实例表
		ChapterUtil.initInstPlayerChapterType(instPlayerId, dictChapter.getType(), dictBarrier.getChapterId(), 1, currTime, syncMsgData);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.almaBarrier, 1, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageData retMsgData = new MessageData();
		if(things.length() > 0){
			retMsgData.putStringItem("1", things.substring(0,things.length() - 1));
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 精英战斗购买次数
	 * @author hzw
	 * @date 2014-8-25下午2:48:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buyEliteFightNum(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerChapterTypeId = (Integer)msgMap.get("instPlayerChapterTypeId");
		
		//判断是否有资格
		String currTime = DateUtil.getCurrTime();
		InstPlayerChapterType instPlayerChapterType = getInstPlayerChapterTypeDAL().getModel(instPlayerChapterTypeId, instPlayerId);
		if (instPlayerChapterType.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
//		int num = FormulaUtil.chapterEliteBuyVip(instPlayer.getVipLevel());
		int num = FormulaUtil.chapterEliteBuyVipNew(instPlayer.getVipLevel(), instPlayerChapterType.getChapterType());
		if(!DateUtil.isSameDay(instPlayerChapterType.getOperTime(), currTime, DateFormat.YMDHMS)){
			instPlayerChapterType.setBuyNum(0);
		}
		int gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyGold) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyGoldAdd) * instPlayerChapterType.getBuyNum();
		if(instPlayerChapterType.getBuyNum() + 1 > num){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notBuyEliteFightNum);
			return;
		}
		if(gold > DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterBuyMaxGold)){
			gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterBuyMaxGold);
		}
		if(instPlayer.getGold() < gold){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		//消耗元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		instPlayerChapterType.setFightNum(instPlayerChapterType.getFightNum() - 1);
		instPlayerChapterType.setBuyNum(instPlayerChapterType.getBuyNum() + 1);
		getInstPlayerChapterTypeDAL().update(instPlayerChapterType, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChapterType, instPlayerChapterType.getId(), instPlayerChapterType.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 活动战斗
	 * @author hzw
	 * @date 2014-8-26下午2:34:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void activityWar(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int barrierId = (Integer)msgMap.get("barrierId");
//		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		DictBarrier dictBarrier = DictMap.dictBarrierMap.get(barrierId + "");
		DictChapter dictChapter = DictMap.dictChapterMap.get(dictBarrier.getChapterId() + "");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//判断是否有资格
		String currTime = DateUtil.getCurrTime();
		if(dictBarrier.getOpenLevel() > instPlayer.getLevel()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPlayerLevel);
			return;
		}
		
		int chapterId = dictChapter.getId();
		List<InstPlayerChapter> instPlayerChapterList = getInstPlayerChapterDAL().getList(" instPlayerId = " + instPlayerId + " and chapterId = " + chapterId, instPlayerId); 
		if(instPlayerChapterList.size() > 0){
			
			//服务器记录的是打过的次数
//			if (condition) {
//				
//			}
			
			int vipLevel = instPlayer.getVipLevel();
			int vipId = vipLevel + 1;
			DictVIP dictVIP = DictMap.dictVIPMap.get(vipId + "");
			
			
			//根据vip和chapterId获取次数
			int fightNum = 0;
			if (chapterId == 201) {//神龙宝藏-银币
				fightNum = dictVIP.getSilverActivityChapterBuyTimes();
			} else if (chapterId == 202) {//天山血池-境界丹
				fightNum = dictVIP.getTalentActivityChapterBuyTimes();
			} else if (chapterId == 203) {//阳火古坛-经验丹
				fightNum = dictVIP.getExpActivityChapterBuyTimes();
			} else if (chapterId == 204) {//名字未定-魂魄
				fightNum = dictVIP.getSoulActivityChapterBuyTimes();
			} else if (chapterId == 205) {//名字未定-翅膀强化石
				fightNum = dictVIP.getWingChapterNum();
			}
			
//			System.out.println("fightNum = " + fightNum + "      is true?  " + DateUtil.isSameDay(instPlayerChapterList.get(0).getOperTime(), currTime, DateFormat.YMDHMS));
			
			if(instPlayerChapterList.get(0).getFightNum() + 1 > fightNum && DateUtil.isSameDay(instPlayerChapterList.get(0).getOperTime(), currTime, DateFormat.YMDHMS) ){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFightNum);
				return;
			}
			
//			if(instPlayerChapterList.get(0).getFightNum() + 1 > fightNum) {
//				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notFightNum);
//				return;
//			}
			
		}
		//用于验证玩家是否利用烧饼等修改器
//		if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
//			return;
//		}
		
		List<DictBarrierLevel> dictBarrierLevelList = (List<DictBarrierLevel>)DictMapList.dictBarrierLevelMap.get(barrierId);
		DictBarrierLevel dictBarrierLevel = dictBarrierLevelList.get(0);
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictBarrierLevel.getCopper()) + "");
		instPlayer.setCulture(instPlayer.getCulture() + dictBarrierLevel.getCulture());
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//副本掉落逻辑
		String things = "";
		DictActivitySoulChapterDrop activitySoulChapterDrop = null;
		if (chapterId == 204) {
			int weekDay = DateUtil.getTimeInfo(DateType.DayOfWeek);
			for (DictActivitySoulChapterDrop obj : DictList.dictActivitySoulChapterDropList) {
				if (weekDay == obj.getWeekDay() && obj.getDifficultyBarrierId() == barrierId) {
					activitySoulChapterDrop = obj;
					break;
				}
			}
			if (activitySoulChapterDrop != null) {
				for (String thing : activitySoulChapterDrop.getThings().split(";")) {
					int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
					int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
					int value = ConvertUtil.toInt(thing.split("_")[2]);
					ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
					things += tableTypeId + "_" + tableFieldId + "_" + value  + ";";
				}
			}
		} else {
			List<DictBarrierDrop> objList = (List<DictBarrierDrop>)DictMapList.dictBarrierDropMap.get(dictBarrierLevel.getBarrierId());
			Set<Integer> hashSet = new HashSet<Integer>(); //用于存储掉落物品类型，保证不重复
			for(DictBarrierDrop obj : objList){
				if(obj.getType() == 0 && obj.getChance() != 0){
					hashSet.add(obj.getType2());
				}
			}
			Map<String, String> thingMap = new HashMap<String, String>(); //掉落的物品map
			Iterator<Integer> iterator = hashSet.iterator();
			while(iterator.hasNext()){
				int type2 = iterator.next();
				List<DictBarrierDropType> objTypeList = (List<DictBarrierDropType>)DictMapList.dictBarrierDropTypeMap.get(type2);
				
				//得到此类型物品的掉落个数
				float random = RandomUtil.getRandomFloat();
				int thingNum = 0;
				Float randomSum = 0.00f;
				for(DictBarrierDropType obj : objTypeList){
					if(obj.getType() == type2){
						randomSum += obj.getPr();
						if(random <= randomSum){
							thingNum = obj.getValue();
							break;
						}
					}
				}
				
				//得到此类物品的总点数
				int num = 0;
				for(DictBarrierDrop obj : objList){
					if(obj.getType() == 0 && obj.getType2() == type2){
						num += obj.getChance();
					}
				}
				
				//根据物品个数算出掉落的物品
				for(int i = 1; i <= thingNum; i++){
					random = RandomUtil.getRandomFloat();
					Float sum = 0.00f;
					for(DictBarrierDrop obj : objList){
						if(obj.getType() == 0 && obj.getType2() == type2){
							sum += (float)obj.getChance() / (float)num;
							if(random <= sum){
								int tableTypeId = obj.getTableTypeId();
								int tableFieldId = obj.getTableFieldId();
								int value = obj.getValue();
								if(value != 0){
//								things += obj.getId() + ";";
									things += tableTypeId + "_" + tableFieldId + "_" + value  + ";";
									ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);
								}
								break;
							}
						}
					}
				}
			}
			ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);
		}
		
		//添加玩家副本章节类型实例表
		ChapterUtil.initInstPlayerChapter(instPlayerId, dictBarrier.getChapterId(), currTime, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageData retMsgData = new MessageData();
		if(things.length() > 0){
			retMsgData.putStringItem("1", things.substring(0,things.length() - 1));
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 活动战斗购买次数
	 * @author hzw
	 * @date 2014-8-26下午3:35:18
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buyActivityFightNum(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerChapterId = (Integer)msgMap.get("instPlayerChapterId");
		
		//判断是否有资格
		String currTime = DateUtil.getCurrTime();
		InstPlayerChapter instPlayerChapter = getInstPlayerChapterDAL().getModel(instPlayerChapterId, instPlayerId);
		if (instPlayerChapter.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
//		DictChapterActivityVip dictChapterActivityVip = DictMap.dictChapterActivityVipMap.get(instPlayerChapter.getChapterId() + "");
//		int num = FormulaUtil.chapterActivityBuyVip(dictChapterActivityVip, instPlayer.getVipLevel());
		int num = FormulaUtil.chapterActivityBuyVip(instPlayer.getVipLevel(), instPlayerChapter.getChapterId());
		if(!DateUtil.isSameDay(instPlayerChapter.getOperTime(), currTime, DateFormat.YMDHMS)){
			instPlayerChapter.setBuyNum(0);
		}
//		int gold = dictChapterActivityVip.getBuyGold() + dictChapterActivityVip.getBuyGoldAdd() * instPlayerChapter.getBuyNum();
		int gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.activityChapterInitGold) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.activityChapterInitGoldAdd) * instPlayerChapter.getBuyNum();
		if(instPlayerChapter.getBuyNum() + 1 > num){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notBuyEliteFightNum);
			return;
		}
		if(gold > DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterBuyMaxGold)){
			gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterBuyMaxGold);
		}
		if(instPlayer.getGold() < gold){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		//消耗元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		instPlayerChapter.setFightNum(instPlayerChapter.getFightNum() - 1);
		instPlayerChapter.setBuyNum(instPlayerChapter.getBuyNum() + 1);
		getInstPlayerChapterDAL().update(instPlayerChapter, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChapter, instPlayerChapter.getId(), instPlayerChapter.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 战斗失败-普通/精英/活动
	 * @author hzw
	 * @date 2014-10-2下午3:29:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void warFailed(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int barrierId = (Integer)msgMap.get("barrierId");  //用于活动
		int barrierLevelId = (Integer)msgMap.get("barrierLevelId");	//用于普通 、精英
		int type = (Integer)msgMap.get("type");   //1-普通 2-精英 3-活动
		String currTime = DateUtil.getCurrTime();
		if(type == 1){
			//添加玩家副本关卡等级实例表
			ChapterUtil.initInstPlayerBarrierLevel(instPlayerId, barrierLevelId, 0, currTime, syncMsgData);
		}else if(type == 2){
			DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
			DictBarrier dictBarrier = DictMap.dictBarrierMap.get(dictBarrierLevel.getBarrierId() + "");
			//添加玩家副本章节类型实例表
			ChapterUtil.initInstPlayerChapterType(instPlayerId, 2, dictBarrier.getChapterId(), 0, currTime, syncMsgData);
		}else if(type == 3){
			DictBarrier dictBarrier = DictMap.dictBarrierMap.get(barrierId + "");
			//添加玩家副本章节类型实例表
			ChapterUtil.initInstPlayerChapter(instPlayerId, dictBarrier.getChapterId(), currTime, syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 元宝购买体力/耐力
	 * @author hzw
	 * @date 2014-10-2下午3:35:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void goldEnergyOrVigor(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int type = (Integer)msgMap.get("type");   //1-体力 2-耐力
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int num = 1;//每次只买一个
		int thingId = 0;//79-耐力丹, 80-体力丹
		
		if(type == 1){
			thingId = StaticThing.energyPill;
		}else if(type == 2){
			thingId = StaticThing.vigorPill;
		}
		
		DictThing thing = DictMap.dictThingMap.get(thingId + "");
		
		//获取玩家当日的商店购买记录
		Map<Integer, String> todayBuyItemMap = ThingUtil.getTodayBuyItemMap(instPlayerId);
		
		//根据玩家vip等级获取商店购买的道具列表
		Map<Integer, Integer> vipItemMap = ThingUtil.getItemsByVipLevel(instPlayer.getVipLevel());
		
		//验证当日购买个数
		int canBuyNum = -1;
		if (vipItemMap.containsKey(thingId)) {
			int buyLimitNum = vipItemMap.get(thingId);
			int todayBuyNum = 0;
			if (todayBuyItemMap.containsKey(thingId)) {
				todayBuyNum = ConvertUtil.toInt(todayBuyItemMap.get(thingId).split("_")[0]);
			}
			canBuyNum = (buyLimitNum - todayBuyNum) < 0 ? 0 : (buyLimitNum - todayBuyNum) ;
			if ((canBuyNum - num) < 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_timesNull);
				return;
			}
		}
		
		//验证元宝
//		int tempNum = num;
		int buyNum = 0;
		int price = 0;
		if (todayBuyItemMap.containsKey(thingId)) {
			buyNum = ConvertUtil.toInt(todayBuyItemMap.get(thingId).split("_")[0]);
		}
		
		int lootInitNum = buyNum + 1;
		int lootMaxNum = buyNum + num;
		for (int i = lootInitNum; i <= lootMaxNum; i++) {
			price += ThingUtil.getStoreBuyPrice(thingId, i);
		}
		
		float multipleExp = 1f;
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.storeDiscont)) {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.storeDiscont + "");
			multipleExp = activityHoliday.getMultiple();
		}
		if (multipleExp != 1f) {
			price = ConvertUtil.toInt(price * multipleExp);
		}
		
//		DictThingExtend dictThingExtend = DictMap.dictThingExtendMap.get(thingId + "");
//		String extend[] = dictThingExtend.getExtend().split(";");
//		for(String str : extend){
//			int down = ConvertUtil.toInt(str.split("_")[0]);
//			int up = ConvertUtil.toInt(str.split("_")[1]);
//			int gold = ConvertUtil.toInt(str.split("_")[2]);
//			if(down <= (buyNum + tempNum) && (buyNum + tempNum) <= up){
//				price += tempNum * gold;
//				buyNum += tempNum;
//				tempNum -= tempNum;
//			}
//			else if((buyNum + tempNum) >= up){
//				price += (up - buyNum) * gold;
//				tempNum = buyNum + tempNum - up;
//				buyNum = up;
//			}
//			if(tempNum == 0){
//				break;
//			}
//		}
		
		if (price > instPlayer.getGold()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		
//		//扣除元宝,赠送体力
//		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, price, msgMap);
//		if(type == 1){
//			instPlayer.setEnergy(instPlayer.getEnergy() + DictMapUtil.getSysConfigIntValue(StaticSysConfig.buyEnergyEnergy));
//		}else if(type == 2){
//			instPlayer.setVigor(instPlayer.getVigor() + DictMapUtil.getSysConfigIntValue(StaticSysConfig.buyVigorVigor));
//		}
//		getInstPlayerDAL().update(instPlayer, instPlayerId);
//		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		//扣除元宝,赠送体力丹
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, price, msgMap);
		if(type == 1){
			ThingUtil.groupThing(instPlayer, StaticTableType.DictThing, thingId, num, syncMsgData, msgMap);
		}else if(type == 2){
//			instPlayer.setVigor(instPlayer.getVigor() + DictMapUtil.getSysConfigIntValue(StaticSysConfig.buyVigorVigor));
			ThingUtil.groupThing(instPlayer, StaticTableType.DictThing, thingId, num, syncMsgData, msgMap);
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		//增加次数-处理商店购买记录,插入前对于道具, 魔核只保留今日和昨日数据,以便查询, vip礼包永久
		String beforeYesterday = DateUtil.getNumDayDate(-2);
		List<InstPlayerStore> todayBuyItemList = getInstPlayerStoreDAL().getList("instPlayerId = " + instPlayerId + " and bagType = " + StaticBagType.item + " and buyTime < '" + beforeYesterday + "'", 0);
		for (InstPlayerStore instPlayerStore : todayBuyItemList) {
			getInstPlayerStoreDAL().deleteById(instPlayerStore.getId(), instPlayerId);
		}
		ThingUtil.addPlayerStore(1, thing, instPlayerId, num, thingId);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 普通副本开福利箱
	 * @author hzw
	 * @date 2015-3-13上午11:08:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void openWelfareBox(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int instPlayerBarrierId = (Integer)msgMap.get("instPlayerBarrierId");
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		//判断是否有资格
		InstPlayerBarrier instPlayerBarrier = getInstPlayerBarrierDAL().getModel(instPlayerBarrierId, instPlayerId);
		if (instPlayerBarrier.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerBarrier.getWelfareBox() != 1){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notWelfareBox);
			return;
		}
		
		DictBarrier dictBarrier = DictMap.dictBarrierMap.get(instPlayerBarrier.getBarrierId() + "");
		String things = dictBarrier.getWelfareBox();
		
		for(String str : things.split(";")){
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}
			
		instPlayerBarrier.setWelfareBox(2);
		getInstPlayerBarrierDAL().update(instPlayerBarrier, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerBarrier, instPlayerBarrier.getId(), instPlayerBarrier.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 下发魂魄活动副本魂魄
	 * @author mp
	 * @date 2015-10-14 下午3:04:55
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sendSoulActivityChapterSoul(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData retMsgData = new MessageData();
		int weekDay = DateUtil.getTimeInfo(DateType.DayOfWeek);
		for (DictActivitySoulChapterDrop obj : DictList.dictActivitySoulChapterDropList) {
			if (weekDay == obj.getWeekDay()) {
				if (obj.getDifficultyBarrierId() == 1010) {
					retMsgData.putStringItem("1", obj.getThings());//难度-简单
				} else if (obj.getDifficultyBarrierId() == 1011) {
					retMsgData.putStringItem("2", obj.getThings());//难度-普通
				} else if (obj.getDifficultyBarrierId() == 1012) {
					retMsgData.putStringItem("3", obj.getThings());//难度-困难
				}
			}
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
}
