package com.huayi.doupo.logic.handler.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.huayi.doupo.base.model.*;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.memcalc.CardCalcObj;
import com.huayi.doupo.base.model.memcalc.CardPropObj;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticEquipQuality;
import com.huayi.doupo.base.model.statics.StaticFightProp;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticPillType;
import com.huayi.doupo.base.model.statics.StaticQuality;
import com.huayi.doupo.base.model.statics.StaticRecruitType;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 公式工具类
 * @author hzw
 * @date 2014-6-19下午3:59:44
 */
public class FormulaUtil extends DALFactory{

	/**
	 * 卡牌等级属性值 + 卡边属性值 + 称号属性值
	 * @author hzw
	 * @date 2014-7-11上午10:51:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int CardChangValue(InstPlayerCard instPlayerCard, int type) throws Exception{
		int result = 0;
		int diffLevel = instPlayerCard.getLevel() - 1;
		int initValue = 0;
		int valueGrow = 0;
		int qualityId = instPlayerCard.getQualityId();
		DictCard card = DictMap.dictCardMap.get(instPlayerCard.getCardId()+"");
		List<DictAdvance> dictAdvanceList = (List<DictAdvance>)DictMapList.dictAdvanceMap.get(instPlayerCard.getCardId());
		if(type == StaticFightProp.blood){
			initValue = card.getBlood(); 
			valueGrow = card.getBloodAdd();
			if(dictAdvanceList != null){
				for(int i = 0; i < dictAdvanceList.size();  i++){
					DictAdvance dictAdvance = dictAdvanceList.get(i);
					if(qualityId == dictAdvance.getQualityId() && instPlayerCard.getStarLevelId() == dictAdvance.getStarLevelId()){
						valueGrow += dictAdvance.getBloodAdd();
					}
				}
			}
		}else if (type == StaticFightProp.wAttack){
			initValue = card.getWuAttack();
			valueGrow = card.getWuAttackAdd();
			if(dictAdvanceList != null){
				for(int i = 0; i < dictAdvanceList.size();  i++){
					DictAdvance dictAdvance = dictAdvanceList.get(i);
					if(qualityId == dictAdvance.getQualityId() && instPlayerCard.getStarLevelId() == dictAdvance.getStarLevelId()){
						valueGrow += dictAdvance.getWuAttackAdd();
					}
				}
			}
		}else if (type == StaticFightProp.fAttack){
			initValue = card.getFaAttack();
			valueGrow = card.getFaAttackAdd();
			if(dictAdvanceList != null){
				for(int i = 0; i < dictAdvanceList.size();  i++){
					DictAdvance dictAdvance = dictAdvanceList.get(i);
					if(qualityId == dictAdvance.getQualityId() && instPlayerCard.getStarLevelId() == dictAdvance.getStarLevelId()){
						valueGrow += dictAdvance.getFaAttackAdd();
					}
				}
			}
		}else if (type == StaticFightProp.wDefense){
			initValue = card.getWuDefense();
			valueGrow = card.getWuDefenseAdd();
			if(dictAdvanceList != null){
				for(int i = 0; i < dictAdvanceList.size();  i++){
					DictAdvance dictAdvance = dictAdvanceList.get(i);
					if(qualityId == dictAdvance.getQualityId() && instPlayerCard.getStarLevelId() == dictAdvance.getStarLevelId()){
						valueGrow += dictAdvance.getWuDefenseAdd();
					}
				}
			}
		}else if (type == StaticFightProp.fDefense){
			initValue = card.getFaDefense();
			valueGrow = card.getFaDefenseAdd();
			if(dictAdvanceList != null){
				for(int i = 0; i < dictAdvanceList.size();  i++){
					DictAdvance dictAdvance = dictAdvanceList.get(i);
					if(qualityId == dictAdvance.getQualityId() && instPlayerCard.getStarLevelId() == dictAdvance.getStarLevelId()){
						valueGrow += dictAdvance.getFaDefenseAdd();
					}
				}
			}
		}else if (type == StaticFightProp.dodge){
			initValue = card.getDodge();
			valueGrow = card.getDodgeAdd();
		}else if (type == StaticFightProp.crit){
			initValue = card.getCrit();
			valueGrow = card.getCritAdd();
		}else if (type == StaticFightProp.hit){
			initValue = card.getHit();
			valueGrow = card.getHitAdd();
		}else if (type == StaticFightProp.flex){
			initValue = card.getFlex();
			valueGrow = card.getFlexAdd();
		}
		result = initValue + diffLevel * valueGrow;
		
		//加上称号对属性的附加值
		List<DictTitleDetail> dictTitleDetails = (List<DictTitleDetail>)DictList.dictTitleDetailList;
		for(DictTitleDetail dictTitleDetail : dictTitleDetails){
			if(dictTitleDetail.getId() == instPlayerCard.getTitleDetailId()){
				String effects[] = dictTitleDetail.getEffects().split(";");
				for(String str : effects){
					if(type == ConvertUtil.toInt(str.split("_")[0])){
						result += ConvertUtil.toInt(str.split("_")[1]);
					}
				}
			}
		}
		
		
		return result;
	}
	
	/**
	 * 计算阵型位置
	 * 规则：按从小到大，找空缺的位置
	 * @author mp
	 * @date 2014-6-24 下午3:19:48
	 * @param formationList
	 * @return
	 * @Description
	 */
	public static int calcFormationPos(List<InstPlayerFormation> formationList){
		int pos = 0;
		if (formationList.size() == 0) {
			pos = 1;
		} else {
			//按位置从小到大排序
			Collections.sort(formationList, new Comparator<Object>() {
				public int compare(Object a, Object b) {
					int one = ((InstPlayerFormation)a).getPosition();
					int two = ((InstPlayerFormation)b).getPosition(); 
					return (int)(one - two); 
				}
			});
			//如果位置是按顺序排的,那么加一个人后的位置应该是最后一个人的位置+1
			pos = formationList.get(formationList.size() - 1).getPosition() + 1;
			//从小到大跟阵型位置比较
			for (int i = 0; i < formationList.size(); i++) {
				InstPlayerFormation instPlayerFormation = formationList.get(i);
				int orderPos = i + 1;
				if (orderPos != instPlayerFormation.getPosition()) {
					pos = orderPos;
					break;
				}
			}
		}
		return pos;
	}
	
	/**
	 * 计算卡牌等级造成的影响
	 * 规则：属性基础值  + 和1级等级差 * 属性加成
	 * @author mp
	 * @date 2014-6-26 下午2:05:36
	 * @Description
	 */
	public static void calcPropByCardLevel(CardCalcObj cardCalcObj, CardPropObj cardPropObj) {
		int cardId = cardCalcObj.getCardId(); 
		int level = cardCalcObj.getLevel();//当前等级
		int upgradeLevel = level - 1;//和1级比提升了几级
		DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
		//计算属性-总共15项目
		cardPropObj.setBlood(dictCard.getBlood() + upgradeLevel * dictCard.getBloodAdd());//血量
		cardPropObj.setwAttack(dictCard.getWuAttack() + upgradeLevel * dictCard.getWuAttackAdd());//物攻
		cardPropObj.setfAttack(dictCard.getFaAttack() + upgradeLevel * dictCard.getFaAttackAdd());//法功
		cardPropObj.setwDefense(dictCard.getWuDefense() + upgradeLevel * dictCard.getWuDefenseAdd());//物防
		cardPropObj.setfDefense(dictCard.getFaDefense() + upgradeLevel * dictCard.getFaDefenseAdd());//法防
		cardPropObj.setDodge(dictCard.getDodge() + upgradeLevel * dictCard.getDodgeAdd());//闪避
		cardPropObj.setCrit(dictCard.getCrit() + upgradeLevel * dictCard.getCritAdd());//暴击
		cardPropObj.setHit(dictCard.getHit() + upgradeLevel * dictCard.getHitAdd());//命中
		cardPropObj.setFlex(dictCard.getFlex() + upgradeLevel * dictCard.getFlexAdd());//韧性
	}
	
	/**
	 * 取得卡牌被吃掉时增长的经验值
	 * @author hzw
	 * @date 2014-6-26下午2:44:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int eatCardExp(int level) throws Exception{
		int exp = 0;
		List<DictCardExpAdd> dictCardExpAddDALList = DictList.dictCardExpAddList;
		for(DictCardExpAdd dictCardExpAdd : dictCardExpAddDALList){
			if(dictCardExpAdd.getLevelStart() <= level){
				exp += dictCardExpAdd.getExp();
			}
		}
		return exp;
	}

	/**
	 * 返回此经验下卡牌能达到多少级和剩余经验
	 * @author hzw
	 * @date 2014-6-26下午3:01:29
	 * @param level  卡牌当前等级
	 * @param exp	 卡牌当前经验值
	 * @param pcLevel 玩家当前等级
	 * @throws Exception
	 * @Description
	 */
	public static Map<String, Integer> calcLevel(int level, int exp, int pcLevel) throws Exception{
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		
		List<DictCardExp> dictCardExpDALList = DictList.dictCardExpList;
		int potential = 0;
		for(DictCardExp dictCardExp : dictCardExpDALList){
			if(dictCardExp.getExp() == 0 && dictCardExp.getId() == level ){
				exp = 0;
				break;
			}
			else if(dictCardExp.getExp() <= exp && dictCardExp.getId() == level){
				level = dictCardExp.getId() + 1;
				exp = exp - dictCardExp.getExp();
//				potential += dictCardExp.getPotential();
			}else if(dictCardExp.getExp() > exp && dictCardExp.getId() == level){
				break;
			}
		}
		
		if(level >= pcLevel)
		{
			level = pcLevel;
			exp = 0;
		}
		
		retMap.put("level", level);
		retMap.put("exp", exp);
		retMap.put("potential", potential);
		return retMap;
	}
	
	/**
	 * 处理VIP升级
	 * @author mp
	 * @date 2015-1-28 下午2:32:15
	 * @param instPlayer
	 * @param level
	 * @Description
	 */
	public static void vipUpgrade (InstPlayer instPlayer, int level, MessageData syncMsgData) throws Exception{
		int vipUpgradeUpLimit = 0;
		List<DictVIP> vipList = DictList.dictVIPList;
		for (DictVIP obj : vipList) {
			if (obj.getOpenLevel() != 0) {
				vipUpgradeUpLimit = obj.getLevel();
			}
		}
		if (instPlayer.getVipLevel() < vipUpgradeUpLimit) {
			int nextVipLevel = instPlayer.getVipLevel() + 1;
			DictVIP vip = null;
			for (DictVIP obj : vipList) {
				if (obj.getLevel() == nextVipLevel) {
					vip = obj;
					break;
				}
			}
			if (vip != null && level >= vip.getOpenLevel()) {
				instPlayer.setVipLevel(vip.getLevel());
				
				//更新玩家成就计数实例数据
				AchievementUtil.updateAchievement (instPlayer.getId(), StaticAchievementType.vip, vip.getLevel(), syncMsgData, null);
			}
		}
	}
	
	/**
	 * 返回此经验下战队能达到多少级和剩余经验
	 * @author hzw
	 * @date 2014-8-22上午10:54:59
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void calcPlayerLevelExp(InstPlayer instPlayer, int exp, MessageData syncMsgData, Map<String, Object> msgMap) throws Exception{
		int level = instPlayer.getLevel();
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(level + "");
		if(level != DictMapUtil.getSysConfigIntValue(StaticSysConfig.playerMaxLevel)){
			if(exp >= dictLevelProp.getFleetExp()){
				exp = exp - dictLevelProp.getFleetExp();
				level = level + 1;
				int count = 0;
				while (true) {
					count ++;
					dictLevelProp = DictMap.dictLevelPropMap.get(level + "");
					if(exp >= dictLevelProp.getFleetExp()){
						exp = exp - dictLevelProp.getFleetExp();
						level = level + 1;
					}else{
						break;
					}
					if(level == DictMapUtil.getSysConfigIntValue(StaticSysConfig.playerMaxLevel)){
						exp = 0;
						break;
					}
					if (count > 100) {
						break;
					}
				}
			}
		}else{
			exp = 0;
		}
		
		//处理战队升级
		if(level > instPlayer.getLevel()){
			
			//同步到玩家对象中
			Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayer.getId());
			player.setLevel(level);
			
			int gold = 0;
			int copper = 0;
			int energy = 0;
			int vigor = 0;
			for(int i = instPlayer.getLevel() + 1; i <= level; i++){
				dictLevelProp = DictMap.dictLevelPropMap.get(i + "");
				gold += dictLevelProp.getGold();
				copper += dictLevelProp.getCopper();
				energy += dictLevelProp.getEnergy();
				vigor += dictLevelProp.getVigor();
			}
			instPlayer.setLevel(level);
			
			//加上元宝
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, gold, msgMap);
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + copper) + "");
			instPlayer.setEnergy(instPlayer.getEnergy() + energy);
			instPlayer.setVigor(instPlayer.getVigor() + vigor);
			
			//处理vip升级
			vipUpgrade(instPlayer, level, syncMsgData);
		}
		instPlayer.setExp(exp);
		
		//根据等级处理是否开启黑角域卖场
		if(level >= DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.hJYStoreLevel + "").getLevel()){
			ActivityUtil.addInstActivity(instPlayer.getId(), StaticActivity.hJYStore, 1, syncMsgData);
		}
		
		//根据等级处理是否添加丹药药方
		if(level >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.pillRecipeLevel)){
			PillUtil.addInstPlayerPillRecipe(instPlayer.getId(), syncMsgData);
		}
		
		//处理等级成就
		AchievementUtil.updateAchievement(instPlayer.getId(), StaticAchievementType.pcLevel, level, syncMsgData, null);
	}
	
	/**
	 * 根据等级计算强化总费用
	 * 规则：查DictEquipStrengthen表
	 * @author mp
	 * @date 2014-7-28 下午3:36:11
	 * @param level
	 * @param equipId
	 * @return
	 * @Description
	 */
	public static int calcStrengFeeByLevel (int level, int equipId) {
		int fee = 0;
		DictEquipment dictEquipment = DictMap.dictEquipmentMap.get(equipId + "");
		List<DictEquipStrengthen> equipStrengthenList = DictList.dictEquipStrengthenList;
		for (DictEquipStrengthen obj : equipStrengthenList) {
			if (obj.getId() > level) {
				break;
			}
			if (dictEquipment.getEquipQualityId() == StaticEquipQuality.white) {
				fee += obj.getWhiteCopper();
			} else if (dictEquipment.getEquipQualityId() == StaticEquipQuality.green) {
				fee += obj.getGreenCopper();
			} else if (dictEquipment.getEquipQualityId() == StaticEquipQuality.blue) {
				fee += obj.getBlueCopper();
			} else if (dictEquipment.getEquipQualityId() == StaticEquipQuality.purple) {
				fee += obj.getPurpleCopper();
			} else if (dictEquipment.getEquipQualityId() == StaticEquipQuality.golden) {
				fee += obj.getGoldenCopper();
			}
		}
		return fee;
	}
	
	/**
	 * 根据等级计算升级费用
	 * 规则： 1点经验=1个铜币.经验之和为分解得到的总铜币
	 * @author mp
	 * @date 2014-10-2 下午3:36:14
	 * @param level
	 * @return
	 * @Description
	 */
	public static int calcUpgradeFeeByLevel (int level) {
		int fee = 0;
		List<DictCardExp> cardExpList = DictList.dictCardExpList;
		for (DictCardExp obj : cardExpList) {
			if (obj.getId() > level) {
				break;
			}
			fee += obj.getExp();
		}
		return fee;
	}
	
	/**
	 * 计算公会对上阵卡牌造成的影响
	 * @author mp
	 * @date 2014-6-26 下午2:55:43
	 * @Description
	 */
	public static void calcPropByUnion () {
		
	}
	
	public static void main(String[] args) {
		int total=10;
		int min_num=-4;
		int max_num=4;
		int times=3;
		int num[] = Train(total, min_num, max_num, times);
		for(int i : num){
			System.out.println(i);
		}
//		num = CollectionUtil.getSequence(num);
//		for(int i : num){
//			System.out.println(i);
//		}
	}
	
	/**
	 * 修炼属性值的产生
	 * @author hzw
	 * @date 2014-7-8上午10:30:37
	 * @param total 属性总和
	 * @param min_num  单个属性浮动下限
	 * @param max_num  单个属性浮动上限
	 * @param times  属性个数
	 * @throws Exception
	 * @Description
	 */
	public static int[] Train (int total, int min_num, int max_num, int times) {
		int average= total/ times;
		int now=0;
		int num[] = new int[times];
		int count = 0;
		for(int i=0;i<times;i++)
		{
			count ++;
			int off=0;
			int tmp=0;
			if(i==times-1)
			{
				tmp=total-now;
				if(tmp>max_num||tmp<0)
				{
					off=now=0;
					i=0;
					// echo "tmp|";// 不符合要求的数据
					tmp=RandomUtil.getRangeInt(min_num,max_num);
					off=tmp-average;
					now=now+tmp;
					num[i]=tmp;
					continue;
				}
				else
				{
					num[i]=tmp;
					break;
				}
			}

			if(off==0)
			{
				tmp=RandomUtil.getRangeInt(min_num,max_num);
			}
			else
			{
				tmp=RandomUtil.getRangeInt(min_num-off,max_num-off);
				tmp=(tmp+off);
			}
			off=tmp-average;
			now=now+tmp;
			num[i]=tmp;
		
			if (count > 100) {
				num = null;
				break;
			}
		}
		return num;
	}
	
	/**
	 * 返回此能达到那个称号和剩余潜力值
	 * @author hzw
	 * @date 2014-7-11上午11:36:55
	 * @param msgMap
	 * @param  type (1-修炼在同阶9星之内可以增长)
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Integer> calcTitle(int titleDetailId, int useTalentValue, int type) throws Exception{
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		
		DictTitleDetail dictTitleDetail = DictMap.dictTitleDetailMap.get(titleDetailId + "");
		int value = dictTitleDetail.getValue();
		if(value != 9){
			List<DictTitleDetail> dictTitleDetailList = (List<DictTitleDetail>)DictMapList.dictTitleDetailMap.get(dictTitleDetail.getTitleId());
			for(DictTitleDetail obj : dictTitleDetailList){
				if(obj.getUseTalentValue() <= useTalentValue && obj.getValue() == value){
					titleDetailId = obj.getId();
					if(value == 9 && type == 1){
						break;
					}
					value = value + 1;
					useTalentValue = useTalentValue - obj.getUseTalentValue();
				}else if(obj.getUseTalentValue() > useTalentValue && obj.getValue() == value){
					titleDetailId = obj.getId();
					if(value == 9 && type == 1){
						break;
					}
					break;
				}
			}
		}
		retMap.put("titleDetailId", titleDetailId);
		retMap.put("useTalentValue", useTalentValue);
		return retMap;
	}
	
	/**
	 * 计算达到的重数与经脉节点数
	 * @author hzw
	 * @date 2014-7-17上午10:44:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> calcAcupoint(InstPlayerKungFu instPlayerKungFu, int culture) throws Exception{
		Map<String, String> retMap = new HashMap<String, String>();
		int acupointNodeId = instPlayerKungFu.getAcupointNodeId();
		int kungFuTierAddId = instPlayerKungFu.getKungFuTierAddId();
		int kungFuId = instPlayerKungFu.getKungFuId();
		DictKungFu dictKungFu = DictMap.dictKungFuMap.get(kungFuId + "");
		DictAcupointNode dictAcupointNode = DictMap.dictAcupointNodeMap.get(acupointNodeId + "");
		DictKungFuTierAdd dictKungFuTierAdd = DictMap.dictKungFuTierAddMap.get(kungFuTierAddId + "");
		int tier = dictAcupointNode.getTier();
		int node = dictAcupointNode.getNode();
		int cultureTemp = culture;
		String acupointNodes = "";
		List<DictAcupointNode> dictAcupointNodeList = (List<DictAcupointNode>)DictMapList.dictAcupointNodeMap.get(dictAcupointNode.getAcupointId());
		for(DictAcupointNode obj : dictAcupointNodeList){
			if(obj.getTier() == tier && obj.getNode() == node && obj.getCulture() <= cultureTemp){
				cultureTemp = cultureTemp - obj.getCulture();
				String fightPropOne = instPlayerKungFu.getFightPropOne();
				String fightPropTwo = instPlayerKungFu.getFightPropTwo();
				String fightPropThree = instPlayerKungFu.getFightPropThree();
				String fightPropFour = instPlayerKungFu.getFightPropFour();
				String fightPropFive = instPlayerKungFu.getFightPropFive();
				String fightPropSix = instPlayerKungFu.getFightPropSix();
				int fightProp = obj.getFightPropId();
				int value = obj.getValue();
				if(fightPropOne == null || fightPropOne.equals("")){
					instPlayerKungFu.setFightPropOne(fightProp + "_" + value);
				}else if(ConvertUtil.toInt(fightPropOne.split("_")[0]) == fightProp){
					instPlayerKungFu.setFightPropOne(fightProp + "_" + (ConvertUtil.toInt(fightPropOne.split("_")[1]) + value));
				}else if(fightPropTwo == null || fightPropTwo.equals("")){
					instPlayerKungFu.setFightPropTwo(fightProp + "_" + value);
				}else if(ConvertUtil.toInt(fightPropTwo.split("_")[0]) == fightProp){
					instPlayerKungFu.setFightPropTwo(fightProp + "_" + (ConvertUtil.toInt(fightPropTwo.split("_")[1]) + value));
				}else if(fightPropThree == null || fightPropThree.equals("")){
					instPlayerKungFu.setFightPropThree(fightProp + "_" + value);
				}else if(ConvertUtil.toInt(fightPropThree.split("_")[0]) == fightProp){
					instPlayerKungFu.setFightPropThree(fightProp + "_" + (ConvertUtil.toInt(fightPropThree.split("_")[1]) + value));
				}else if(fightPropFour == null || fightPropFour.equals("")){
					instPlayerKungFu.setFightPropFour(fightProp + "_" + value);
				}else if(ConvertUtil.toInt(fightPropFour.split("_")[0]) == fightProp){
					instPlayerKungFu.setFightPropFour(fightProp + "_" + (ConvertUtil.toInt(fightPropFour.split("_")[1]) + value));
				}else if(fightPropFive == null || fightPropFive.equals("")){
					instPlayerKungFu.setFightPropFive(fightProp + "_" + value);
				}else if(ConvertUtil.toInt(fightPropFive.split("_")[0]) == fightProp){
					instPlayerKungFu.setFightPropFive(fightProp + "_" + (ConvertUtil.toInt(fightPropFive.split("_")[1]) + value));
				}else if(fightPropSix == null || fightPropSix.equals("")){
					instPlayerKungFu.setFightPropSix(fightProp + "_" + value);
				}else if(ConvertUtil.toInt(fightPropSix.split("_")[0]) == fightProp){
					instPlayerKungFu.setFightPropSix(fightProp + "_" + (ConvertUtil.toInt(fightPropSix.split("_")[1]) + value));
				}
				
				if(node == dictKungFu.getMaxNode()){
					if(tier == dictKungFu.getTier()){
						acupointNodeId = 0;
						kungFuTierAddId = 0;
						break;
					}else{
						tier++;
						node = 1;
					}
				}else{
					node ++;
				}
			}else if(obj.getTier() == tier && obj.getNode() == node && obj.getCulture() > cultureTemp){
				acupointNodeId = obj.getId();
				break;
			}
		}
		List<DictKungFuTierAdd> dictKungFuTierAddList = (List<DictKungFuTierAdd>)DictMapList.dictKungFuTierAddMap.get(kungFuId);
		for(DictKungFuTierAdd obj : dictKungFuTierAddList){
			if(acupointNodeId == 0){
				if(obj.getTier() >= dictKungFuTierAdd.getTier() && obj.getTier() <= tier){
					String fightPropOne = instPlayerKungFu.getFightPropOne();
					String fightPropTwo = instPlayerKungFu.getFightPropTwo();
					String fightPropThree = instPlayerKungFu.getFightPropThree();
					String fightPropFour = instPlayerKungFu.getFightPropFour();
					String fightPropFive = instPlayerKungFu.getFightPropFive();
					String fightPropSix = instPlayerKungFu.getFightPropSix();
					int fightProp = obj.getFightPropId();
					int value = obj.getValue();
					if(ConvertUtil.toInt(fightPropOne.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropOne(fightProp + "_" + (ConvertUtil.toInt(fightPropOne.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropTwo.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropTwo(fightProp + "_" + (ConvertUtil.toInt(fightPropTwo.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropThree.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropThree(fightProp + "_" + (ConvertUtil.toInt(fightPropThree.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropFour.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropFour(fightProp + "_" + (ConvertUtil.toInt(fightPropFour.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropFive.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropFive(fightProp + "_" + (ConvertUtil.toInt(fightPropFive.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropSix.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropSix(fightProp + "_" + (ConvertUtil.toInt(fightPropSix.split("_")[1]) + value));
					}
				}
			}else{
				if(obj.getTier() >= dictKungFuTierAdd.getTier() && obj.getTier() < tier){
					String fightPropOne = instPlayerKungFu.getFightPropOne();
					String fightPropTwo = instPlayerKungFu.getFightPropTwo();
					String fightPropThree = instPlayerKungFu.getFightPropThree();
					String fightPropFour = instPlayerKungFu.getFightPropFour();
					String fightPropFive = instPlayerKungFu.getFightPropFive();
					String fightPropSix = instPlayerKungFu.getFightPropSix();
					int fightProp = obj.getFightPropId();
					int value = obj.getValue();
					if(ConvertUtil.toInt(fightPropOne.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropOne(fightProp + "_" + (ConvertUtil.toInt(fightPropOne.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropTwo.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropTwo(fightProp + "_" + (ConvertUtil.toInt(fightPropTwo.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropThree.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropThree(fightProp + "_" + (ConvertUtil.toInt(fightPropThree.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropFour.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropFour(fightProp + "_" + (ConvertUtil.toInt(fightPropFour.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropFive.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropFive(fightProp + "_" + (ConvertUtil.toInt(fightPropFive.split("_")[1]) + value));
					}else if(ConvertUtil.toInt(fightPropSix.split("_")[0]) == fightProp){
						instPlayerKungFu.setFightPropSix(fightProp + "_" + (ConvertUtil.toInt(fightPropSix.split("_")[1]) + value));
					}
				}
			}
			if(tier == obj.getTier() && acupointNodeId != 0){
				kungFuTierAddId = obj.getId();
			}
		}
		
		List<DictAcupointNode> dictAcupointNodes = (List<DictAcupointNode>)DictMapList.dictAcupointNodeMap.get(dictAcupointNode.getAcupointId());
		for(DictAcupointNode obj : dictAcupointNodes){
			if(obj.getTier() == tier){
				acupointNodes = acupointNodes + obj.getId() + ";";
			}
		}
		
		

		retMap.put("acupointNodeId", acupointNodeId+"");
		retMap.put("kungFuTierAddId", kungFuTierAddId+"");
		retMap.put("culture", culture - cultureTemp+"");
		retMap.put("acupointNodes", acupointNodes);
		return retMap;
	}
	
	/**
	 * 计算当前功法消耗的元气和
	 * @author hzw
	 * @date 2014-7-18下午2:55:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int calcCulture(InstPlayerKungFu instPlayerKungFu) throws Exception{
		int acupointNodeId = instPlayerKungFu.getAcupointNodeId();
		int kungFuId = instPlayerKungFu.getKungFuId();
		DictKungFu dictKungFu = DictMap.dictKungFuMap.get(kungFuId + "");
		int tier = 0;
		int node = 0;
		int culture = 0;
		if(acupointNodeId == 0){
			tier = dictKungFu.getTier() + 1;
			node = 0;
		}else{
			DictAcupointNode dictAcupointNode = DictMap.dictAcupointNodeMap.get(acupointNodeId + "");
			tier = dictAcupointNode.getTier();
			node = dictAcupointNode.getNode();
		}
		if(tier != 1 && node != 1){
			List<DictAcupointNode> dictAcupointNodeList = (List<DictAcupointNode>)DictMapList.dictAcupointNodeMap.get(dictKungFu.getAcupoint());
			for(DictAcupointNode obj : dictAcupointNodeList){
				if(obj.getTier() < tier){
					culture += obj.getCulture();
				}else if(obj.getTier() == tier && node < obj.getNode()){
					culture += obj.getCulture();
				}
					
			}
		}
		return culture;
	}
	
	/**
	 * 计算当前异火可返还的经验值
	 * @author hzw
	 * @date 2014-7-28上午11:10:46
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int eatFireExp(InstPlayerFire instPlayerFire) throws Exception{
		int exp = 0;
		exp += instPlayerFire.getExp();
		DictFire dictFire = DictMap.dictFireMap.get(instPlayerFire.getFireId() + "");
		exp += dictFire.getExp();
		List<DictFireExp> dictFireExpList = DictList.dictFireExpList;
		for(DictFireExp obj : dictFireExpList){
			if(obj.getId() < instPlayerFire.getLevel()){
				exp += obj.getExp();
			}
		}
		
		return exp;
	}
	
	/**
	 * 计算异火可达到的等级以及剩余经验
	 * @author hzw
	 * @date 2014-7-28上午11:17:29
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static Map<String, Integer> calcFireLevel(int level, int exp) throws Exception{
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		
		List<DictFireExp> dictFireExpList = DictList.dictFireExpList;
		for(DictFireExp dictFireExp : dictFireExpList){
			if(dictFireExp.getExp() == 0 && dictFireExp.getId() == level ){
				exp = 0;
				break;
			}
			else if(dictFireExp.getExp() <= exp && dictFireExp.getId() == level){
				level++;
				exp = exp - dictFireExp.getExp();
			}else if(dictFireExp.getExp() > exp && dictFireExp.getId() == level){
				break;
			}
		}
		
		retMap.put("level", level);
		retMap.put("exp", exp);
		return retMap;
	}
	
	/**
	 * 根据等级计算升到此等级所需总经验[不含当前等级已有的经验,这些经验在外边计算]
	 * @author mp
	 * @date 2014-7-29 下午3:50:31
	 * @param level
	 * @return
	 * @Description
	 */
	public static int calcSumExpByLevel (int level) {
		int sumExp = 0;
		for (int i = 1; i < level; i++) {
			sumExp += DictMap.dictCardExpMap.get(i + "").getExp();
		}
		return sumExp;
	}
	
	/**
	 * 分解时,根据总经验计算能得到多少经验丹
	 * @author mp
	 * @date 2014-7-29 下午4:10:04
	 * @param sumExp
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static String calcExpPillBySumExp(int sumExp) {
		
		String result = "";
		
		//将查出的经验丹,按照数量倒叙排列
		List<DictPill> pillList = (List<DictPill>)DictMapList.dictPillMap.get(StaticPillType.exp);
		Collections.sort(pillList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((DictPill)a).getValue();
				int two = ((DictPill)b).getValue(); 
				return (int)(two - one); 
			}
		});
		
		//计算得到各种类型的经验丹的个数,并组织返回结果
		for (DictPill pill : pillList) {
			int value = pill.getValue();
			int num = sumExp / value;
			if (num >= 1) {
				result += StaticTableType.DictPill + "_" + pill.getId() + "_" + num + ";";
			}
			sumExp = sumExp % value;
		}
		return result;
	}
	
	/**
	 * 组织vip影响的招募品质权重列表
	 * @author mp
	 * @date 2014-8-14 下午4:19:07
	 * @param vipLevel
	 * @param recruitTypeId
	 * @return
	 * @Description
	 */
	private static List<Integer> orgVipRecruitList (int vipLevel, int recruitTypeId) {
		
		//定义参数
		List<Integer> vipRecruitWeightAddList = Lists.newArrayList();
		
		//根据vip等级获取vip对象
		DictVIP vip = CardUtil.getVipByLevel(vipLevel);
		
		//确定影响的招募类型
		String vipRecruitWeightAdd = "";
		if (recruitTypeId == StaticRecruitType.silverRecruit) {
			vipRecruitWeightAdd = vip.getSilverRecruitAdd();
		} else if (recruitTypeId == StaticRecruitType.goldRecruit) {
			vipRecruitWeightAdd = vip.getGoldRecruitAdd();
		} else if (recruitTypeId == StaticRecruitType.diamondRecruit) {
			vipRecruitWeightAdd = vip.getDiamondRecruitAdd();
		}
		
		//组织返回的List,里面按 白绿蓝紫金的顺序存放比重数据
		if (vipRecruitWeightAdd != null && !vipRecruitWeightAdd.equals("")) {
			//先将数据放入map
			Map<Integer, Integer> vipRecruitMap = Maps.newHashMap();
			for (String vipRecruitAdd :vipRecruitWeightAdd.split(";")) {
				vipRecruitMap.put(ConvertUtil.toInt(vipRecruitAdd.split("_")[0]), ConvertUtil.toInt(vipRecruitAdd.split("_")[1]));
			}
			//按顺序组织List
			for (DictQuality obj : DictList.dictQualityList) {
				if (vipRecruitMap.containsKey(obj.getId())) {
					vipRecruitWeightAddList.add(vipRecruitMap.get(obj.getId()));
				} else {
					//如果map中不存在此品质的权重加成, 赋值为0
					vipRecruitWeightAddList.add(0);
				}
			}
		}
		return vipRecruitWeightAddList;
	}
	
	/**
	 * 计算招募品质
	 * @author mp
	 * @date 2014-8-14 下午4:14:48
	 * @param vipLevel
	 * @param recruitTypeId
	 * @return
	 * @Description
	 */
	public static int calcRecruitQuality (int vipLevel, int recruitTypeId) {
//		a
		//获取招募类型对象
		DictRecruitType recruitType = DictMap.dictRecruitTypeMap.get(recruitTypeId + "");
		
		//随机数的基数
		int randomBaseNum = 0;
		
		//初始招募品质权重数组
		int [] qualityWeightArray = {recruitType.getWhiteWeight(), recruitType.getGreenWeight(), recruitType.getBlueWeight(), recruitType.getPurpleWeight()};
//		int [] qualityWeightArray = {recruitType.getWhiteWeight(), recruitType.getGreenWeight(), recruitType.getBlueWeight(), recruitType.getPurpleWeight(), recruitType.getGoldWeight()};
		//后来修改为没有金  2014-10-27日  若改回去，除了修改此处外，还需修改此方法中行号为904的代码[已有标注]，当然Dict_Quality中需加上金的一条数据
		
		//获取受vip影响的品质权重加成
		List<Integer> vipRecruitWeightAddList = orgVipRecruitList(vipLevel, recruitTypeId);
	
		//计算随机数的基数和组织最终的品质权重数组
		for (int i = 0; i < qualityWeightArray.length; i++) {
			if (vipRecruitWeightAddList.size() > 0) {
				int finalWeight = qualityWeightArray [i] + vipRecruitWeightAddList.get(i);
				qualityWeightArray [i] = finalWeight;
				randomBaseNum += finalWeight;
			} else {
				randomBaseNum += qualityWeightArray [i];
			}
		}
		
		//将最终的招募品质权重数组组织成由品质Id为Key的map
		Map<Integer, Integer> qualityWeightMap = ImmutableMap.of(
				 StaticQuality.white, qualityWeightArray[0],
				 StaticQuality.green, qualityWeightArray[1],
				 StaticQuality.blue, qualityWeightArray[2],
				 StaticQuality.purple, qualityWeightArray[3]
//				 StaticQuality.gold, qualityWeightArray[4])//后来修改为没有金  2014-10-27日
				 );
		
		//计算招募品质并返回
		int weightSum = 0;
		int qualityId = 0;
		int qualityRandomNum = RandomUtil.getRangeInt(1, randomBaseNum);
		for (Entry<Integer, Integer> entry : qualityWeightMap.entrySet()) {
			int weight = entry.getValue();
			weightSum += weight;
			if (qualityRandomNum <= weightSum) {
				qualityId = entry.getKey();
				break;
			}
		}
		return qualityId;
	}
	
	/**
	 * 计算招募得到的卡牌Id
	 * @author mp
	 * @date 2014-8-14 下午5:08:43
	 * @param qualityId
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int calcRecruitCard (int qualityId, int recuitTypeId) {
		
		//定义返回值
		int cardId = 0;
		
		//获取此品质下的卡牌列表
		List<DictRecruitCard> recruitTypeCardList = (List<DictRecruitCard>)DictMapList.dictRecruitCardMap.get(recuitTypeId);
		List<DictRecruitCard> recruitCardList = Lists.newArrayList();
		for (DictRecruitCard obj : recruitTypeCardList) {
			if (obj.getQualityId() == qualityId) {
				recruitCardList.add(obj);
			}
		}
		
		//计算随机数的基数
		int randomBaseNum = 0;
		for (DictRecruitCard obj : recruitCardList) {
			randomBaseNum += obj.getWeight();
		}
		
		//获取随机数
		int randomNum = RandomUtil.getRangeInt(1, randomBaseNum);
		
		//洗牌卡牌列表
//		CollectionUtil.shuffle(recruitCardList);
		
		//计算得到的卡牌Id,并返回
		int weightSum = 0;
		for (DictRecruitCard obj : recruitCardList) {
			weightSum += obj.getWeight();
			if (randomNum <= weightSum) {
				cardId = obj.getCardId();
				break;
			}
		}
		return cardId;
	}
	
	/**
	 * 计算特殊招募得到的卡牌Id[到达指定次数和十连抽]
	 * @author mp
	 * @date 2015-4-6 下午4:28:20
	 * @param qualityId
	 * @param areaNo
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int calcSpecialRecruitCard (int areaNo) {
		
		//定义返回值
		int cardId = 0;
		
		//获取此卡牌库下的卡牌列表
		List<DictRecruitSpecialCard> recruitTypeSpecialCardList = (List<DictRecruitSpecialCard>)DictMapList.dictRecruitSpecialCardMap.get(areaNo);
		
		//计算随机数的基数
		int randomBaseNum = 0;
		for (DictRecruitSpecialCard obj : recruitTypeSpecialCardList) {
			randomBaseNum += obj.getWeight();
		}
		
		//获取随机数
		int randomNum = RandomUtil.getRangeInt(1, randomBaseNum);
		
		//洗牌卡牌列表
//		CollectionUtil.shuffle(recruitCardList);
		
		//计算得到的卡牌Id,并返回
		int weightSum = 0;
		for (DictRecruitSpecialCard obj : recruitTypeSpecialCardList) {
			weightSum += obj.getWeight();
			if (randomNum <= weightSum) {
				cardId = obj.getCardId();
				break;
			}
		}
		return cardId;
	}
	
	/**
	 * 获取当前vip精英副本每天可购买的攻击次数
	 * @author hzw
	 * @date 2014-8-26上午10:31:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int chapterEliteBuyVip(int vip) {
		
		int num = 0;
		switch(vip){
			case 1: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip1); break;
			case 2: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip2); break;
			case 3: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip3); break;
			case 4: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip4); break;
			case 5: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip5); break;
			case 6: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip6); break;
			case 7: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip7); break;
			case 8: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip8); break;
			case 9: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip9); break;
			case 10: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip10); break;
			case 11: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip11); break;
			case 12: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip12); break;
			case 13: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip13); break;
			case 14: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip14); break;
			case 15: num = DictMapUtil.getSysConfigIntValue(StaticSysConfig.chapterEliteBuyVip15); break;
		}
		
		return num;
	}
	
	/**
	 * 获取当前vip精英副本每天可购买的攻击次数
	 * @author mp
	 * @date 2015-1-28 下午4:15:24
	 * @param vipLevel
	 * @return
	 * @Description
	 */
	public static int chapterEliteBuyVipNew(int vipLevel, int chapterType) {
		
		//根据vip等级获取vip对象
		DictVIP vip = null;
		List<DictVIP> vipList = DictList.dictVIPList;
		for (DictVIP obj : vipList) {
			if (obj.getLevel() == vipLevel) {
				vip = obj;
				break;
			}
		}
		
		if(chapterType == 4) {//魔王副本
			return vip.getFiendChapterNum();
		} else {
			return vip.getEliteChapterBuyTimes();
		}
		
	}
	
	/**
	 * 获取当前vip活动副本每天可购买的攻击次数
	 * @author hzw
	 * @date 2014-8-28下午1:50:43
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int chapterActivityBuyVip(DictChapterActivityVip dictChapterActivityVip, int vip) {
		
		int num = 0;
		switch(vip){
			case 1: num = dictChapterActivityVip.getVip1(); break;
			case 2: num = dictChapterActivityVip.getVip2(); break;
			case 3: num = dictChapterActivityVip.getVip3(); break;
			case 4: num = dictChapterActivityVip.getVip4(); break;
			case 5: num = dictChapterActivityVip.getVip5(); break;
			case 6: num = dictChapterActivityVip.getVip6(); break;
			case 7: num = dictChapterActivityVip.getVip7(); break;
			case 8: num = dictChapterActivityVip.getVip8(); break;
			case 9: num = dictChapterActivityVip.getVip9(); break;
			case 10: num = dictChapterActivityVip.getVip10(); break;
			case 11: num = dictChapterActivityVip.getVip11(); break;
			case 12: num = dictChapterActivityVip.getVip12(); break;
			case 13: num = dictChapterActivityVip.getVip13(); break;
			case 14: num = dictChapterActivityVip.getVip14(); break;
			case 15: num = dictChapterActivityVip.getVip15(); break;
		}
		
		return num;
	}
	
	/**
	 * 获取当前vip活动副本每天可购买的攻击次数
	 * @author mp
	 * @date 2015-1-28 下午4:02:10
	 * @param vipLevel
	 * @param chapterId
	 * @return
	 * @Description
	 */
	public static int chapterActivityBuyVip(int vipLevel, int chapterId) {
		int num = 0;

		//根据vip等级获取vip对象
		DictVIP vip = null;
		List<DictVIP> vipList = DictList.dictVIPList;
		for (DictVIP obj : vipList) {
			if (obj.getLevel() == vipLevel) {
				vip = obj;
				break;
			}
		}
		
		//根据vip和活动副本Id获取可购买数量
		if (chapterId == DictMapUtil.getSysConfigIntValue(StaticSysConfig.slbz)) {//神龙宝藏-银币材料
			num = vip.getSilverActivityChapterBuyTimes();
		} else if (chapterId == DictMapUtil.getSysConfigIntValue(StaticSysConfig.tsxc)) {//天山血池-潜力材料
			num = vip.getTalentActivityChapterBuyTimes();
		} else if (chapterId == DictMapUtil.getSysConfigIntValue(StaticSysConfig.yhgt)) {//阳火古坛-经验材料
			num = vip.getExpActivityChapterBuyTimes();
		} else if (chapterId == DictMapUtil.getSysConfigIntValue(StaticSysConfig.wysm)) {//天魂墓地
			num = vip.getSoulActivityChapterBuyTimes();
		} else if (chapterId == DictMapUtil.getSysConfigIntValue(StaticSysConfig.shcx)) {//神羽强化副本
			num = vip.getWingChapterNum();
		}
		
		return num;
	}
	
	/**
	 * 获取当前卡牌出出售得到的银币
	 * @author hzw
	 * @date 2014-9-2上午10:39:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int sellCardCopper(InstPlayerCard instPlayerCard) throws Exception{
		int sellCopper = 0;
		
//		DictQuality dictQuality = getDictQualityDAL().getModel(instPlayerCard.getQualityId(), 0);
		
		DictQuality dictQuality = DictMap.dictQualityMap.get(instPlayerCard.getQualityId() + "");
		
		sellCopper = dictQuality.getSellCopper() + dictQuality.getSellCopperAdd() * (instPlayerCard.getLevel() - 1);
		return sellCopper;
	}
	
	/**
	 * 获取当前功法出售得到的银币
	 * @author hzw
	 * @date 2014-9-2下午4:36:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int sellKungFuCopper(InstPlayerKungFu instPlayerKungFu) throws Exception{
		int sellCopper = 0;
//		DictKungFu dictKungFu = getDictKungFuDAL().getModel(instPlayerKungFu.getKungFuId(), 0);
		DictKungFu dictKungFu = DictMap.dictKungFuMap.get(instPlayerKungFu.getKungFuId() + "");
//		DictKungFuQuality dictKungFuQuality = getDictKungFuQualityDAL().getModel(dictKungFu.getKungFuQualityId(), 0);
		DictKungFuQuality dictKungFuQuality = DictMap.dictKungFuQualityMap.get(dictKungFu.getKungFuQualityId() + "");
		sellCopper = dictKungFuQuality.getSellCopper();
		return sellCopper;
	}
	
	/**
	 * 手动技能位置开启等级限制
	 * @author hzw
	 * @date 2014-9-11上午10:21:20
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int manualSkillPosition(int position) {
		int level = 0;
		switch(position){
			case 1: level = DictMapUtil.getSysConfigIntValue(StaticSysConfig.manualSkillPosition1); break;
			case 2: level = DictMapUtil.getSysConfigIntValue(StaticSysConfig.manualSkillPosition2); break;
			case 3: level = DictMapUtil.getSysConfigIntValue(StaticSysConfig.manualSkillPosition3); break;
			case 4: level = DictMapUtil.getSysConfigIntValue(StaticSysConfig.manualSkillPosition4); break;
			case 5: level = DictMapUtil.getSysConfigIntValue(StaticSysConfig.manualSkillPosition5); break;
			case 6: level = DictMapUtil.getSysConfigIntValue(StaticSysConfig.manualSkillPosition6); break;
			case 7: level = DictMapUtil.getSysConfigIntValue(StaticSysConfig.manualSkillPosition7); break;
			case 8: level = DictMapUtil.getSysConfigIntValue(StaticSysConfig.manualSkillPosition8); break;
		}
		return level;
	}
	
	/**
	 * 获取该等级下手动技能的经验
	 * @author hzw
	 * @date 2014-9-11下午2:40:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int eatManualSkillExp(int level) throws Exception{
		int exp = 0;
		
		List<DictManualSkillExp> dictManualSkillExpDALList = DictList.dictManualSkillExpList;
		for(DictManualSkillExp dictManualSkillExp : dictManualSkillExpDALList){
			if(dictManualSkillExp.getId() < level){
				exp = dictManualSkillExp.getExp();
			}
		}
		
		return exp;
	}
	
	/**
	 * 计算次经验下手动技能能够达到的等级跟剩余经验
	 * @author hzw
	 * @date 2014-9-11下午2:48:43
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static Map<String, Integer> calcManualSkillLevel(int level, int exp) throws Exception{
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		List<DictManualSkillExp> dictManualSkillExpDALList = DictList.dictManualSkillExpList;
		for(DictManualSkillExp dictManualSkillExp : dictManualSkillExpDALList){
			if(dictManualSkillExp.getExp() == 0 && dictManualSkillExp.getId() == level ){
				exp = 0;
				break;
			}else if(dictManualSkillExp.getExp() <= exp && dictManualSkillExp.getId() == level){
				level = dictManualSkillExp.getId() + 1;
				exp = exp - dictManualSkillExp.getExp();
			}else if(dictManualSkillExp.getExp() > exp && dictManualSkillExp.getId() == level){
				break;
			}
		}
		
		retMap.put("level", level);
		retMap.put("exp", exp);
		return retMap;
	}
	
	/**
	 * 返回此该等级下可以返还的经验值
	 * @author hzw
	 * @date 2014-9-20上午11:50:20
	 * @param level			卡牌等级
	 * @param exp  			卡牌当前拥有的经验值
	 * @throws Exception
	 * @Description
	 */
	public static int restoreCardExp(int level, int exp) throws Exception{
		if(level > 1){
			List<DictCardExp> dictCardExpDALList = DictList.dictCardExpList;
			for(DictCardExp dictCardExp : dictCardExpDALList){
				if(dictCardExp.getId() < level){
					exp += dictCardExp.getExp();
				}
			}
		}
		return exp;
	}
	
	/**
	 * 返回此称号下可以返还的潜力点
	 * @author hzw
	 * @date 2014-9-20上午11:50:20
	 * @param titleDetailId 卡牌详细称号Id
	 * @param level			卡牌等级
	 * @param potential  	卡牌当前拥有的潜力点（升级送潜力点已经取消）
	 * @throws Exception
	 * @Description
	 */
	public static int restoreCardPotential(int titleDetailId, int level, int potential) throws Exception{
		List<DictTitleDetail> dictTitleDetailList = (List<DictTitleDetail>)DictList.dictTitleDetailList;
		for(DictTitleDetail obj : dictTitleDetailList){
			if(obj.getId() < titleDetailId){
//				potential += obj.getUseTalentValue(); //这里的升级送潜力点已经取消
			}
		}
		if(level > 1){
			List<DictCardExp> dictCardExpDALList = DictList.dictCardExpList;
			for(DictCardExp dictCardExp : dictCardExpDALList){
				if(dictCardExp.getId() < level){
//					potential -= dictCardExp.getPotential(); //这里的升级送潜力点已经取消
				}
			}
		}
		
		return potential;
	}
	
	/**
	 * 此潜力下可以返回的潜力丹
	 * @author hzw
	 * @date 2014-9-22上午11:26:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static String calcPotentialPillBySumPotential(int sumPotential) {
		
		String result = "";
		
		//将查出的经验丹,按照数量倒叙排列
		List<DictPill> pillList = (List<DictPill>)DictMapList.dictPillMap.get(StaticPillType.potential);
		Collections.sort(pillList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((DictPill)a).getValue();
				int two = ((DictPill)b).getValue(); 
				return (int)(two - one); 
			}
		});
		
		//计算得到各种类型的经验丹的个数,并组织返回结果
		for (DictPill pill : pillList) {
			int value = pill.getValue();
			int num = sumPotential / value;
			if (num >= 1) {
				result += StaticTableType.DictPill + "_" + pill.getId() + "_" + num + ";";
			}
			sumPotential = sumPotential % value;
		}
		if(result.equals("")){
			return result;
		}else{
			return result.substring(0, result.length() - 1);
		}
	}
	
	/**
	 * 取得法宝与功法时增长的经验值
	 * @author hzw
	 * @date 2014-12-8上午11:28:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int eatMagicExp(InstPlayerMagic instPlayerMagic) throws Exception{
		int exp = 0;
		List<DictMagicLevel> dictMagicLevelList = (List<DictMagicLevel>)DictMapList.dictMagicLevelMap.get(instPlayerMagic.getMagicType());
		int level = DictMap.dictMagicLevelMap.get(instPlayerMagic.getMagicLevelId() + "").getLevel() - 1;
		for(DictMagicLevel dictMagicLevel : dictMagicLevelList){
			if(dictMagicLevel.getLevel() <= level){
				exp += dictMagicLevel.getExp();
			}
		}
		return exp;
	}
	
	/**
	 * 返回此经验下法宝跟功法能达到多少级和剩余经验
	 * @author hzw
	 * @date 2014-6-26下午3:01:29
	 * @update	by cui 2015/12/09
	 * @param instPlayerMagic  法宝跟功法
	 * @param exp	 增加的经验值
	 * @param pcLevel 玩家当前等级
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Integer> calcMagicLevel(InstPlayerMagic instPlayerMagic, int exp, int pcLevel) throws Exception{
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		int magicLevelId = instPlayerMagic.getMagicLevelId();
		int magicLevelMax = 40;	//默认上限
		if(instPlayerMagic.getAdvanceId() > 0){
			DictMagicrefining dictMagicrefining = DictMap.dictMagicrefiningMap.get(instPlayerMagic.getAdvanceId());
			magicLevelMax = dictMagicrefining.getMaxStrengthen();
		}
		int level = DictMap.dictMagicLevelMap.get(magicLevelId + "").getLevel();
		List<DictMagicLevel> dictMagicLevelList = (List<DictMagicLevel>)DictMapList.dictMagicLevelMap.get(instPlayerMagic.getMagicType());

		for (DictMagicLevel magicLevel : dictMagicLevelList){
			if(magicLevel.getLevel() == level){
				magicLevelId = magicLevel.getId();
				if(level >= magicLevelMax || level >= pcLevel){
					break;
				}
				if(exp < magicLevel.getExp()){
					break;
				}
				exp -= magicLevel.getExp();
				level += 1;
			}
		}

		retMap.put("magicLevelId", magicLevelId);
		retMap.put("exp", exp);
		return retMap;
	}
	
	/**
	 * 返回此经验下美人能达到多少级和剩余经验
	 * @author hzw
	 * @date 2015-3-17下午3:25:54
	 * @param beautyCardExpId 美人经验Id
	 * @param exp 增加的经验值
	 * @throws Exception
	 * @Description
	 */
	public static Map<String, Integer> calcBeautyCard(int beautyCardExpId, int exp) throws Exception{
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		
		List<DictBeautyCardExp> dictBeautyCardExpList = DictList.dictBeautyCardExpList;
		for(DictBeautyCardExp dictBeautyCardExp : dictBeautyCardExpList){
			if(dictBeautyCardExp.getExp() == 0 && dictBeautyCardExp.getId() == beautyCardExpId ){
				exp = 0;
				break;
			}
			else if(dictBeautyCardExp.getExp() <= exp && dictBeautyCardExp.getId() == beautyCardExpId){
				beautyCardExpId = dictBeautyCardExp.getId() + 1;
				exp = exp - dictBeautyCardExp.getExp();
			}else if(dictBeautyCardExp.getExp() > exp && dictBeautyCardExp.getId() == beautyCardExpId){
				break;
			}
		}
		
		retMap.put("beautyCardExpId", beautyCardExpId);
		retMap.put("exp", exp);
		return retMap;
	}
	
	
	@Test
	public void test () {
		try {
			SpringUtil.getSpringContext();
			DictData.dictData(0);
			calcExpPillBySumExp(2370);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
