package com.huayi.doupo.logic.handler.util;

import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictFightSoul;
import com.huayi.doupo.base.model.DictMagic;
import com.huayi.doupo.base.model.DictMagicLevel;
import com.huayi.doupo.base.model.DictTryToPractice;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerAchievementValue;
import com.huayi.doupo.base.model.InstPlayerArena;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerCardSoul;
import com.huayi.doupo.base.model.InstPlayerChapter;
import com.huayi.doupo.base.model.InstPlayerChip;
import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.InstPlayerFightSoul;
import com.huayi.doupo.base.model.InstPlayerKungFu;
import com.huayi.doupo.base.model.InstPlayerMagic;
import com.huayi.doupo.base.model.InstPlayerManualSkill;
import com.huayi.doupo.base.model.InstPlayerPagoda;
import com.huayi.doupo.base.model.InstPlayerPill;
import com.huayi.doupo.base.model.InstPlayerRecharge;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.InstPlayerTrain;
import com.huayi.doupo.base.model.InstPlayerTryToPractice;
import com.huayi.doupo.base.model.InstPlayerWing;
import com.huayi.doupo.base.model.InstPlayerYFire;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticBagType;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.StaticTryToPracticeType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 条件验证工具类
 * @author mp
 * @date 2013-10-16 下午3:46:07
 */
public class VerifyUtil extends DALFactory{
	
	/**
	 * 验证消耗物品是否足够
	 * @author mp
	 * @date 2015-12-18 上午11:10:20
	 * @param instPlayer
	 * @param things
	 * @param thingsMap
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static String  vfConsumThings (InstPlayer instPlayer, String things, Map<String, Object> thingsMap) {
		String retMsg = "";
		int instPlayerId = instPlayer.getId();
		for (String thing : things.split(";")) {
			int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
			int value = ConvertUtil.toInt(thing.split("_")[2]);
			
			//丹药
			if (tableTypeId == StaticTableType.DictPill) {
				List<InstPlayerPill> instPlayerPillList = getInstPlayerPillDAL().getList("instPlayerId = " + instPlayerId + " and pillId = " + tableFieldId, instPlayerId);
				if (instPlayerPillList.size() <= 0) {
					retMsg = StaticCnServer.fail_costThing_1;
					break;
				} else {
					InstPlayerPill instPlayerPill = instPlayerPillList.get(0);
					if (instPlayerPill.getNum() < value) {
						retMsg = StaticCnServer.fail_costThing_1;
						break;
					}
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerPillList.get(0));
				}
			}
			//物品
			else if (tableTypeId == StaticTableType.DictThing) {
				List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + tableFieldId, instPlayerId);
				if (instPlayerThingList.size() <= 0) {
					retMsg = StaticCnServer.fail_costThing_2;
					break;
				} else {
					InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
					int ownNum = instPlayerThing.getNum();
					if (tableFieldId == StaticThing.goldBox) {//金宝箱时个数特殊处理 num+level
						ownNum = ownNum + instPlayerThing.getLevel();
					}
					if (ownNum < value) {
						retMsg = StaticCnServer.fail_costThing_2;
						break;
					}
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerThingList.get(0));
				}
			}
			//玩家属性
			else if (tableTypeId == StaticTableType.DictPlayerBaseProp) {
				if (tableFieldId == StaticPlayerBaseProp.copper) {//银币
					long ownCopper = ConvertUtil.toLong(instPlayer.getCopper());
					if (ownCopper < value) {
						retMsg = StaticCnServer.fail_costThing_3_2;
						break;
					}
				} else if (tableFieldId == StaticPlayerBaseProp.gold) {//金币
					if (instPlayer.getGold() < value) {
						retMsg = StaticCnServer.fail_costThing_3_1;
						break;
					}
				} else if (tableFieldId == StaticPlayerBaseProp.culture) {//火能石
					if (instPlayer.getCulture() < value) {
						retMsg = StaticCnServer.fail_costThing_3_4;
						break;
					}
				} else if (tableFieldId == StaticPlayerBaseProp.offer) {//联盟贡献
					List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
					if (instUnionMemberList.size() <= 0) {
						retMsg = StaticCnServer.fail_costThing_3_7;
						break;
					} else {
						InstUnionMember instUnionMember = instUnionMemberList.get(0);
						if (instUnionMember.getSumOffer() < value) {
							retMsg = StaticCnServer.fail_costThing_3_7;
							break;
						}
					}
					if (retMsg.length() <= 0) {
						thingsMap.put(thing, instUnionMemberList.get(0));
					}
				} else if (tableFieldId == StaticPlayerBaseProp.prestige) {//威望
					List<InstPlayerArena> instPlayerArenaList = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, 0);
					if (instPlayerArenaList.size() <= 0) {
						retMsg = StaticCnServer.fail_costThing_3_5;
						break;
					} else {
						InstPlayerArena instPlayerArena = instPlayerArenaList.get(0);
						if (instPlayerArena.getPrestige() < value) {
							retMsg = StaticCnServer.fail_costThing_3_5;
							break;
						}
					}
					if (retMsg.length() <= 0) {
						thingsMap.put(thing, instPlayerArenaList.get(0));
					}
				} else if (tableFieldId == StaticPlayerBaseProp.bossIntegral) {//屠魔积分
					List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.bossIntegral+"'", 0);
					if (instPlayerBigTableList.size() <= 0) {
						retMsg = StaticCnServer.fail_costThing_3_8;
						break;
					} else {
						InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
						if (ConvertUtil.toInt(instPlayerBigTable.getValue1()) < value) {
							retMsg = StaticCnServer.fail_costThing_3_8;
							break;
						}
					}
					if (retMsg.length() <= 0) {
						thingsMap.put(thing, instPlayerBigTableList.get(0));
					}
				}
				if (tableFieldId == StaticPlayerBaseProp.copper || tableFieldId == StaticPlayerBaseProp.gold || tableFieldId == StaticPlayerBaseProp.culture) {
					if (retMsg.length() <= 0) {
						thingsMap.put(thing, instPlayer);
					}
				}
			}
			//装备(初始装备：未装备在卡牌上，0级的，进阶是0的，未镶嵌的，这样的装备)
			else if (tableTypeId == StaticTableType.DictEquipment) {
				List<InstPlayerEquip> instPlayerEquipList = getInstPlayerEquipDAL().getList("instPlayerId = " + instPlayerId + " and equipId = " + tableFieldId + " and level = 0 and isInlay = 0 and equipAdvanceId = 0 and instCardId = 0" , instPlayerId);
				if (instPlayerEquipList.size() < value) {
					retMsg = StaticCnServer.fail_costThing_6;
					break;
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerEquipList);
				}
			}
			//卡牌(初始卡牌：未上阵的，等级是1的，经验是0的，这样的卡牌)
			else if (tableTypeId == StaticTableType.DictCard) {
				List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId + " and cardId = " + tableFieldId + " and inTeam = " + StaticCustomDict.unTeam + " and level = 1 and exp = 0", instPlayerId);
				if (instPlayerCardList.size() < value) {
					retMsg = StaticCnServer.fail_costThing_7;
					break;
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerCardList);
				}
			}
			//卡牌魂魄
			else if (tableTypeId == StaticTableType.DictCardSoul) {
				List<InstPlayerCardSoul> instPlayerCardSoulList = getInstPlayerCardSoulDAL().getList("instPlayerId = " + instPlayerId + " and cardSoulId = " + tableFieldId, instPlayerId);
				if (instPlayerCardSoulList.size() <= 0) {
					retMsg = StaticCnServer.fail_costThing_9;
					break;
				} else {
					InstPlayerCardSoul instPlayerCardSoul = instPlayerCardSoulList.get(0);
					if (instPlayerCardSoul.getNum() < value) {
						retMsg = StaticCnServer.fail_costThing_9;
						break;
					}
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerCardSoulList.get(0));
				}
			}
			//功法法宝碎片
			else if (tableTypeId == StaticTableType.DictChip) {
				List<InstPlayerChip> instPlayerChipList = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + tableFieldId, instPlayerId);
				if (instPlayerChipList.size() <= 0) {
					retMsg = StaticCnServer.fail_costThing_10;
					break;
				} else {
					InstPlayerChip instPlayerChip = instPlayerChipList.get(0);
					if (instPlayerChip.getNum() < value) {
						retMsg = StaticCnServer.fail_costThing_10;
						break;
					}
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerChipList.get(0));
				}
			}
			//功法法宝(初始功法法宝：未在卡牌身上, 初始等级,经验是0的功法法宝)
			else if (tableTypeId == StaticTableType.DictMagic) {
				int magicLevelId = 0;
				DictMagic obj = DictMap.dictMagicMap.get(tableFieldId + "");
				if (obj.getType() == 1) {
					magicLevelId = ((List<DictMagicLevel>) DictMapList.dictMagicLevelMap.get(1)).get(0).getId();
				} else if (obj.getType() == 2) {
					magicLevelId = ((List<DictMagicLevel>) DictMapList.dictMagicLevelMap.get(2)).get(0).getId();
				}
				List<InstPlayerMagic> instPlayerMagicList = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId + " and magicId = " + tableFieldId + " and instCardId = 0 and exp = 0 and magicLevelId = " + magicLevelId, instPlayerId);
				if (instPlayerMagicList.size() < value) {
					retMsg = StaticCnServer.fail_costThing_13;
					break;
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerMagicList);
				}
			}
			//异火碎片
			else if (tableTypeId == StaticTableType.DictYFireChip) {
				List<InstPlayerYFire> instPlayerYFireList = getInstPlayerYFireDAL().getList("instPlayerId = " + instPlayerId + " and fireId = " + tableFieldId, instPlayerId);
				if (instPlayerYFireList.size() <= 0) {
					retMsg = StaticCnServer.fail_costThing_16;
					break;
				} else {
					InstPlayerYFire instPlayerYFire = instPlayerYFireList.get(0);
					if (instPlayerYFire.getChipCount() < value) {
						retMsg = StaticCnServer.fail_costThing_16;
						break;
					}
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerYFireList.get(0));
				}
			}
			//翅膀(初始翅膀：等级是0,星数是1,未配在卡牌身上的)
			else if (tableTypeId == StaticTableType.DictWing) {
				List<InstPlayerWing> instPlayerWingList = getInstPlayerWingDAL().getList("instPlayerId = " + instPlayerId + " and wingId = " + tableFieldId + " and level = 0 and starNum = 1 and instCardId = 0", instPlayerId);
				if (instPlayerWingList.size() < value) {
						retMsg = StaticCnServer.fail_costThing_17;
						break;
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerWingList);
				}
			}
			//斗魂(初始斗魂：未附着在卡牌身上，等级为1，经验为0的斗魂)
			else if (tableTypeId == StaticTableType.DictFightSoul) {
				DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(tableFieldId + "");
				List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and fightSoulId = " + tableFieldId + " and fightSoulQualityId = " + fightSoul.getFightSoulQualityId() + " and level = 1 and lockState = 0 and instCardId = 0 and position = 0 and exp = 0", instPlayerId);
				if (instPlayerFightSoulList.size() < value) {
					retMsg = StaticCnServer.fail_costThing_18;
					break;
				}
				if (retMsg.length() <= 0) {
					thingsMap.put(thing, instPlayerFightSoulList);
				}
			}
		}
		return retMsg;
	}
	
	/**
	 * 验证背包容量
	 * @author hzw
	 * @date 2014-9-10下午2:39:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static boolean vfbagEnough(String channelId, Map<String, Object> msgMap, int instPlayerId, int ...bagTypes){
		
		boolean flag = false;
		for(int bagTypeId : bagTypes){
			int extendBagContain = ThingUtil.getExtandBagNum(instPlayerId, bagTypeId);
			//背包上限 = 背包初始容量 + 背包扩充的容量
			int bagUpLimit = DictMap.dictBagTypeMap.get(bagTypeId + "").getBagUpLimit() + extendBagContain;
			int size = 0;
			if(bagTypeId == StaticBagType.item || bagTypeId == StaticBagType.core || bagTypeId == StaticBagType.equipChip){
				List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and bagTypeId = " + bagTypeId, instPlayerId);
				size = instPlayerThingList.size();
			}else if(bagTypeId == StaticBagType.equip){
				List<InstPlayerEquip> instPlayerEquipList = getInstPlayerEquipDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
				size = instPlayerEquipList.size();
			}else if(bagTypeId == StaticBagType.card){
				List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
				size = instPlayerCardList.size();
			}else if(bagTypeId == StaticBagType.kungFu){
				List<InstPlayerKungFu> instPlayerKungFuList = getInstPlayerKungFuDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
				size = instPlayerKungFuList.size();
			}else if(bagTypeId == StaticBagType.manualSkill){
				List<InstPlayerManualSkill> instPlayerManualSkillList = getInstPlayerManualSkillDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
				size = instPlayerManualSkillList.size();
			}else if(bagTypeId == StaticBagType.magic){
				List<InstPlayerMagic> instPlayerMagicList = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
				size = instPlayerMagicList.size();
			}
			//验证背包容量,不够返回   
			if (size >= bagUpLimit) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_bagNotEnough);
				return flag;
			}
		}
		return flag;
	}
	
	/**
	 * 用于验证玩家是否利用烧饼等修改器
	 * @author hzw
	 * @date 2015-3-11上午11:35:45
	 * @param channelId
	 * @param msgMap
	 * @param coredata		//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
	 * @throws Exception
	 * @Description
	 */
	public static boolean vfpullBlack(String channelId, Map<String, Object> msgMap, InstPlayer instPlayer, String coredata){
		boolean flag = false;
		Boolean pullBlack = false; //用于是否验证玩家的各种等级关键数据
		int instPlayerId = instPlayer.getId();
		if(instPlayer.getPullBlack() == 1){
			pullBlack = true;
		}else if(RandomUtil.getRandomFloat() <= DictMapUtil.getSysConfigFloatValue(StaticSysConfig.pullBlack)){
			pullBlack = true;
		}
		if(pullBlack){
			String core[] = coredata.split(";");
			for(String str : core){
				String type = str.split(":")[0];
				String dates[] =  str.split(":")[1].split("\\|");
				if(type.equals("1")){
					long num = 0;
					List<InstPlayerCard> instPlayerCards = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId + " and inTeam = 1", instPlayerId);
					for (InstPlayerCard instPlayerCard : instPlayerCards) {
						num += instPlayerCard.getId() + instPlayerCard.getLevel() +instPlayerCard.getCardId(); 
					}
//					System.out.println("卡牌：" + num);
					String numStr = num + "";
					if (!numStr.equals(dates[0])) {
						flag = true;
					}
					
					
					/*
					List<InstPlayerCard> instPlayerCards = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId + " and inTeam = 1", instPlayerId);
					for (InstPlayerCard obj : instPlayerCards) {
						instCardMap.put(obj.getId(), obj);
					}
					for(String date : dates){
						String dt[] = date.split("_");
						if(instCardMap.get(ConvertUtil.toInt(dt[0])).getLevel() != ConvertUtil.toInt(dt[1])){
							flag = true;
							break;
						}
						if(instCardMap.get(ConvertUtil.toInt(dt[0])).getCardId() != ConvertUtil.toInt(dt[2])){
							flag = true;
							break;
						}
					}
				*/}else if(type.equals("2")){
					long num = 0;
					List<InstPlayerEquip> instPlayerEquips = getInstPlayerEquipDAL().getList("instPlayerId = " + instPlayerId + " and instCardId != 0", instPlayerId);
					for (InstPlayerEquip instPlayerEquip : instPlayerEquips) {
						num += instPlayerEquip.getId() + instPlayerEquip.getLevel() + instPlayerEquip.getEquipAdvanceId() + instPlayerEquip.getEquipId();
					}
//					System.out.println("装备：" + num);
					String numStr = num + "";
					if (!numStr.equals(dates[0])) {
						flag = true;
					}
					
					/*
					List<InstPlayerEquip> instPlayerEquips = getInstPlayerEquipDAL().getList("instPlayerId = " + instPlayerId + " and instCardId != 0", instPlayerId);
					for (InstPlayerEquip obj : instPlayerEquips) {
						instEquipMap.put(obj.getId(), obj);
					}
					for(String date : dates){
						String dt[] = date.split("_");
						if(instEquipMap.get(ConvertUtil.toInt(dt[0])).getLevel() != ConvertUtil.toInt(dt[1])){
							flag = true;
							break;
						}
						if(instEquipMap.get(ConvertUtil.toInt(dt[0])).getEquipAdvanceId() != ConvertUtil.toInt(dt[2])){
							flag = true;
							break;
						}
						if(instEquipMap.get(ConvertUtil.toInt(dt[0])).getEquipId() != ConvertUtil.toInt(dt[3])){
							flag = true;
							break;
						}
					}
					
				*/}else if(type.equals("3")){
					long num = 0;
					List<InstPlayerMagic> instPlayerMagics = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId + " and instCardId != 0", instPlayerId);
					for (InstPlayerMagic instPlayerMagic : instPlayerMagics) {
						num += instPlayerMagic.getId() + instPlayerMagic.getMagicLevelId();
					}
//					System.out.println("功法法宝：" + num);
					String numStr = num + "";
					if (!numStr.equals(dates[0])) {
						flag = true;
					}
					/*
					List<InstPlayerMagic> instPlayerMagics = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId + " and instCardId != 0", instPlayerId);
					for (InstPlayerMagic obj : instPlayerMagics) {
						instMagicMap.put(obj.getId(), obj);
					}
					for(String date : dates){
						String dt[] = date.split("_");
						if(instMagicMap.get(ConvertUtil.toInt(dt[0])).getMagicLevelId() != ConvertUtil.toInt(dt[1])){
							flag = true;
							break;
						}
					}
				*/}else if(type.equals("4")){
					
					long num = 0;
					
					List<InstPlayerTrain> instPlayerTrains = getInstPlayerTrainDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
					for (InstPlayerTrain obj : instPlayerTrains) {
						num += obj.getId() + obj.getFightPropValue();
					}
//					System.out.println("修炼：" + num);
					String numStr = num + "";
					if (!numStr.equals(dates[0])) {
						flag = true;
					}
					
//					List<InstPlayerTrain> instPlayerTrains = getInstPlayerTrainDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
//					for (InstPlayerTrain obj : instPlayerTrains) {
//						instTrainMap.put(obj.getId(), obj);
//					}
//					for(String date : dates){
//						String dt[] = date.split("_");
//						if(instTrainMap.get(ConvertUtil.toInt(dt[0])).getFightPropValue() != ConvertUtil.toInt(dt[1])){
//							flag = true;
//							break;
//						}
//					}
				}else if(type.equals("5")){
					
					long num = 0;
					
					List<InstPlayerFightSoul> instPlayerFightSouls = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId != 0", instPlayerId);
					for (InstPlayerFightSoul obj : instPlayerFightSouls) {
						num += obj.getId() + obj.getLevel();
					}
//					System.out.println("斗魂：" + num);
					String numStr = num + "";
					if (!numStr.equals(dates[0])) {
						flag = true;
					}
					
//					for(String date : dates){
//						String dt[] = date.split("_");
//						if(instFightSoulMap.get(ConvertUtil.toInt(dt[0])).getLevel() != ConvertUtil.toInt(dt[1])){
//							flag = true;
//							break;
//						}
//					}
				}else if(flag){
					break;
				}
			}
		}
		
		if(flag){
			try {
				instPlayer.setPullBlack(1);
				getInstPlayerDAL().update(instPlayer, instPlayerId);
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noPullBlack);
				return flag;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 验证试练日
	 * @author hzw
	 * @date 2015-6-25下午3:47:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static boolean vfTryToPractice(String channelId, Map<String, Object> msgMap, int instPlayerId, int tryToPracticeTypeId, DictTryToPractice tryToPractice) throws Exception{
		
		boolean flag = false;
		//各种验证
		if(tryToPracticeTypeId == StaticTryToPracticeType.chapter){
			String condition = tryToPractice.getConditions();//chapterId
			List<InstPlayerChapter> instPlayerChapterList = getInstPlayerChapterDAL().getList("instPlayerId = " + instPlayerId + " and isPass = 1 and chapterId = " + condition, instPlayerId);
			if(instPlayerChapterList.size() <= 0){
				flag = true;
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.equip){
			int condition = ConvertUtil.toInt(tryToPractice.getConditions());//穿齐的装备的等级
			//穿齐等级装备的卡牌个数
			int progress = 0;
			List<Long> listLong = getInstPlayerEquipDAL().getCounts(" where instPlayerId =  " + instPlayerId + " and level >= " + condition + " and instCardId != 0  GROUP BY instCardId");
			for(Long i : listLong){
				if(i == 4){
					progress ++;
				}
			}
			if(progress < tryToPractice.getProgress()){
				flag = true;
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.advance){
			String condition = tryToPractice.getConditions(); //cardId_qualityId_starLevelId
			String[] cond = condition.split("_");
			int progress = 0;
			try {
				progress = getInstPlayerCardDAL().getCount(" instPlayerId = " + instPlayerId + " and cardId = "+ cond[0] +" and qualityId >= "+ cond[1] +" and starLevelId >= "+ cond[2] + " and inTeam = 1"+ " and inTeam = 1");
				if(progress < tryToPractice.getProgress()){
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.magic){
//			int condition = ConvertUtil.toInt(tryToPractice.getConditions()); //穿齐功法法宝的等级默认是0不做等级判断
			//穿齐等级功法法宝的卡牌个数
			int progress = 0;
			List<Long> listLong = getInstPlayerMagicDAL().getCounts(" where instPlayerId =  " + instPlayerId + " and instCardId != 0  GROUP BY instCardId");
			for(Long i : listLong){
				if(i == 2){
					progress ++;
				}
			}
					
			if(progress < tryToPractice.getProgress()){
				flag = true;
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.arena){
			int condition = ConvertUtil.toInt(tryToPractice.getConditions()); //竞技场名次
			List<InstPlayerArena> instPlayerArenaList = getInstPlayerArenaDAL().getList(" instPlayerId =  " + instPlayerId, instPlayerId);
			if(instPlayerArenaList.size() <= 0 || (instPlayerArenaList.size() > 0 && instPlayerArenaList.get(0).getUpRank() > condition)){
				flag = true;
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.title){
			String condition = tryToPractice.getConditions(); //cardId_titleDetailId
			String[] cond = condition.split("_");
			String whereStr = "";
			if(ConvertUtil.toInt(cond[0]) != 0){
				whereStr = " and cardId = "+ cond[0];
			}
			List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList(" instPlayerId = " + instPlayerId + whereStr + " and titleDetailId >= "+ cond[1] + " and inTeam = 1", instPlayerId);
			if(instPlayerCardList.size() < tryToPractice.getProgress()){
				flag = true;
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.constell){
			int condition = ConvertUtil.toInt(tryToPractice.getConditions()); //第几个命宫
			String where = "substring_index(instPlayerConstells, ';', 1)";
			if(condition > 1){
				where = "substring_index(substring_index(instPlayerConstells, ';', "+ condition +"),';',-1)";
			}
			int progress = 0;
			List<Long> listLong = getInstPlayerConstellDAL().getCounts("a ,(SELECT "+ where +" as id from Inst_Player_Card where instPlayerId = " + instPlayerId + " and inTeam = 1) b where a.id = b.id and !a.pills like '%0%'");
			for(Long i : listLong){
				progress += i;
			}
			if(progress < tryToPractice.getProgress()){
				flag = true;
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.fight1 || tryToPracticeTypeId == StaticTryToPracticeType.fight2 || tryToPracticeTypeId == StaticTryToPracticeType.fight3){
			List<InstPlayerTryToPractice> tryToPracticesList = getInstPlayerTryToPracticeDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if(tryToPracticesList.size() <= 0){
				flag = true;
			}else{
				if(!StringUtil.contains(tryToPracticesList.get(0).getCanTryToPracticeIds(), tryToPractice.getId() + "", ";")){
					flag = true;
				}
			}
			
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.inlay){/*
			String condition = tryToPractice.getConditions(); //type_num  type:1-打孔 num-空数 2-镶嵌 num-魔核级别
			String[] cond = condition.split("_");
			String whereStr = "";
			if(ConvertUtil.toInt(cond[0]) == 2){
				whereStr = " and b.thingId > 0 and (select LEVEL from Dict_Thing c where c.id = b.thingId) >= " + cond[1];//字典表访问使用了数据库-屏蔽此验证
			}
			int progress = 0;
			List<Long> listLong = getInstPlayerEquipDAL().getCounts(" a,Inst_Equip_Gem b where a.instPlayerId = " + instPlayerId + " and a.instCardId != 0 and a.id = b.instPlayerEquipId "+ whereStr +" GROUP BY instPlayerEquipId");
			for(Long i : listLong){
				progress += i;
			}
			if(progress < tryToPractice.getProgress()){
				flag = true;
			}
		*/}else if(tryToPracticeTypeId == StaticTryToPracticeType.pagoda){
			int condition = ConvertUtil.toInt(tryToPractice.getConditions()); //type  type:1-层数 2-重置次数
			List<InstPlayerPagoda> instPlayerPagodaList = (List<InstPlayerPagoda>)getInstPlayerPagodaDAL().getList(" instPlayerId =  " + instPlayerId, instPlayerId);
			int progress = instPlayerPagodaList.size();
			if(progress > 0){
				InstPlayerPagoda instPlayerPagoda = instPlayerPagodaList.get(0);
				if(condition == 1){
					progress = instPlayerPagoda.getMaxPagodaStoreyId();
				}else{
					progress = instPlayerPagoda.getResetNum();
				}
			}
			if(progress < tryToPractice.getProgress()){
				flag = true;
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.hJYStore){
			List<InstPlayerAchievementValue> instPlayerAchievementValueList = (List<InstPlayerAchievementValue>)getInstPlayerAchievementValueDAL().getList(" achievementTypeId = " + StaticAchievementType.hJYStore + " and instPlayerId = " + instPlayerId, 0);
			if(instPlayerAchievementValueList.size() <= 0){
				flag = true;
			}else{
				if(instPlayerAchievementValueList.get(0).getValue() < tryToPractice.getProgress()){
					flag = true;
				}
			}
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.luck){
			//由于缘分对于服务端来说要验证的东西太多了所以经过商量相信客户端
		}else if(tryToPracticeTypeId == StaticTryToPracticeType.equipAdvance){/*  数据库操作字典表
			int condition = ConvertUtil.toInt(tryToPractice.getConditions()); //装备进阶到星级
			int progress = 0;
			List<Long> listLong = getInstPlayerEquipDAL().getCounts("a ,Dict_Equip_Advance b where a.instPlayerId = " + instPlayerId + " and a.equipAdvanceId = b.id and b.starLevel >= " + condition);
			for(Long i : listLong){
				progress += i;
			}
			if(progress < tryToPractice.getProgress()){
				flag = true;
			}
		*/}else if(tryToPracticeTypeId == StaticTryToPracticeType.hotShopping1 || tryToPracticeTypeId == StaticTryToPracticeType.hotShopping2 || tryToPracticeTypeId == StaticTryToPracticeType.hotShopping3 || 
				tryToPracticeTypeId == StaticTryToPracticeType.hotShopping4 || tryToPracticeTypeId == StaticTryToPracticeType.hotShopping5){
			String condition = tryToPractice.getConditions(); //type_num  type:1-免费领取 2-充值领取  3-购买
			String[] cond = condition.split("_");
			if(ConvertUtil.toInt(cond[0]) == 2){
				int sumrmb = 0;
				List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
				for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
					sumrmb += instPlayerRecharge.getThisrmb();
				}
				int progress = sumrmb;
				if(progress < tryToPractice.getProgress()){
					flag = true;
				}
			}
		}
				
		if (flag) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
			return flag;
		}
		return flag;
	}
	
}
