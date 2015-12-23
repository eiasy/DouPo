package com.huayi.doupo.logic.handler.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictFightSoul;
import com.huayi.doupo.base.model.DictFightSoulHuntRule;
import com.huayi.doupo.base.model.DictFightSoulUpgradeExp;
import com.huayi.doupo.base.model.DictFightSoulUpgradeProp;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerFightSoul;
import com.huayi.doupo.base.model.InstPlayerFightSoulHuntRule;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticFightProp;
import com.huayi.doupo.base.model.statics.StaticFightSoulQuality;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.logic.util.MessageData;

/**
 * 斗魂工具类
 * @author mp
 * @date 2015-10-17 下午4:05:58
 */
public class FightSoulUtil extends DALFactory{
	
	/**
	 * /处理点击后的移动位置
	 * @author mp
	 * type 1-猎一次  2-猎十次
	 * @date 2015-10-19 下午3:26:46
	 * @Description
	 */
	public static int huntMovePosition (int fightSouleHuntRuleId, DictFightSoulHuntRule fightSoulHuntRule, int instPlayerId, MessageData syncMsgData, int type, int guideStep) throws Exception{
		int nextHuntRuleId = 0;
		int activeHuntRuleIdPer = fightSoulHuntRule.getActiveHuntRuleIdPer();//前进1隔概率
		int random = RandomUtil.getRangeInt(1, 100);
		if (random <= activeHuntRuleIdPer) {
			nextHuntRuleId = fightSoulHuntRule.getActiveHuntRuleId();
		} else {
			nextHuntRuleId = 1;
		}
		if (guideStep == 1) {
			nextHuntRuleId = 2;
		}
		if (nextHuntRuleId != 1) {
			//点亮下一个
			if (getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId + " and fightSouleHuntRuleId = " + nextHuntRuleId, instPlayerId).size() <= 0) {
				if (type == 2) {
					FightSoulUtil.addInstPlayerFightSoulHuntRule(instPlayerId, nextHuntRuleId, new MessageData());
				} else if (type == 1) {
					FightSoulUtil.addInstPlayerFightSoulHuntRule(instPlayerId, nextHuntRuleId, syncMsgData);
				}
			}
		}
		//熄灭当前的
		if (fightSouleHuntRuleId != 1) {
			InstPlayerFightSoulHuntRule instPlayerFightSoulHuntRule = getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId + " and fightSouleHuntRuleId = " + fightSouleHuntRuleId, instPlayerId).get(0);
			getInstPlayerFightSoulHuntRuleDAL().deleteById(instPlayerFightSoulHuntRule.getId(), instPlayerId);
			if (type == 2) {
//				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, new InstPlayerFightSoulHuntRule(), instPlayerFightSoulHuntRule.getId(), "", syncMsgData);
			} else if (type == 1) {
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, new InstPlayerFightSoulHuntRule(), instPlayerFightSoulHuntRule.getId(), "", syncMsgData);
			}
		}
		return nextHuntRuleId;
	}
	
	/**
	 * 得到最靠后的一个点亮的猎魂位置
	 * @author mp
	 * @date 2015-10-19 下午3:03:12
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static int getTheLastHuntRuleId (int instPlayerId) {
		int fightSouleHuntRuleId = 1;
		List<InstPlayerFightSoulHuntRule> instPlayerFightSoulHuntRuleList = getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if (instPlayerFightSoulHuntRuleList.size() > 0) {
			Collections.sort(instPlayerFightSoulHuntRuleList, new Comparator<Object>() {
				public int compare(Object a, Object b) {
					int one = ((InstPlayerFightSoulHuntRule)a).getFightSouleHuntRuleId();
					int two = ((InstPlayerFightSoulHuntRule)b).getFightSouleHuntRuleId(); 
					return (int)(two - one); 
				}
			});
			fightSouleHuntRuleId = instPlayerFightSoulHuntRuleList.get(instPlayerFightSoulHuntRuleList.size() - 1).getFightSouleHuntRuleId();
		}
		return fightSouleHuntRuleId;
	}
	
	/**
	 * 此位置上能得到哪个斗魂
	 * @author mp
	 * @date 2015-10-19 上午11:55:29
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static DictFightSoul huntFightSoul (DictFightSoulHuntRule fightSoulHuntRule, InstPlayer instPlayer, MessageData syncMsgData, int guideStep) throws Exception{
		int sumPerValue = 0;
		int instPlayerId = instPlayer.getId();
		Map<Integer, Integer> perMap = new HashMap<Integer, Integer>();
		for (String fightSoulQuality : fightSoulHuntRule.getFightSoulQualityPers().split(";")) {
			int fightSoulQualityId = ConvertUtil.toInt(fightSoulQuality.split("_")[0]);
			int perValue = ConvertUtil.toInt(fightSoulQuality.split("_")[1]);
			perMap.put(fightSoulQualityId, perValue);
			sumPerValue += perValue;
		}
		
		int savePerValue = 0;
		int huntFightSoulQualityId = 0;
		int perRandom = RandomUtil.getRangeInt(1, sumPerValue);
		for (Entry<Integer, Integer> entry : perMap.entrySet()) {
			int fightSoulQualityId = entry.getKey();
			int perValue = entry.getValue();
			savePerValue += perValue;
			if (perRandom <= savePerValue) {
				huntFightSoulQualityId = fightSoulQualityId;
				break;
			}
		}
		
		int sumWeight = 0;
		List<DictFightSoul> fightAllQualitySoulList = (List<DictFightSoul>)DictMapList.dictFightSoulMap.get(huntFightSoulQualityId);
		List<DictFightSoul> fightSoulList = new ArrayList<>();
		for (DictFightSoul fightSoulObj : fightAllQualitySoulList) {
			if (fightSoulObj.getLevel() <= instPlayer.getLevel()) {
				fightSoulList.add(fightSoulObj);
				sumWeight += fightSoulObj.getWeight();
			}
		}
		
		int randomSumWeight = 0;
		DictFightSoul fightSoul = null;
		int fightSoulRandom = RandomUtil.getRangeInt(1, sumWeight);
		for (DictFightSoul fightSoulObj : fightSoulList) {
			randomSumWeight += fightSoulObj.getWeight();
			if (fightSoulRandom <= randomSumWeight) {
				fightSoul = fightSoulObj;
				break;
			}
		}
		
		if (guideStep == 1) {//第一次引导必给黄阶物防斗魂
			List<DictFightSoul> fightSoulCanList = new ArrayList<>();
			for (DictFightSoul fightSoulObj : DictList.dictFightSoulList) {
				if (fightSoulObj.getLevel() == 1 && fightSoulObj.getFightSoulQualityId() == StaticFightSoulQuality.huang) {
					if (isFixFightProp(fightSoulObj.getId(), StaticFightProp.wDefense)) {
						fightSoulCanList.add(fightSoulObj);
					}
				}
			}
			if (fightSoulCanList.size() > 0) {
				int random = RandomUtil.getRangeInt(0, fightSoulCanList.size() - 1);
				fightSoul = fightSoulCanList.get(random);
			}
		} else if (guideStep == 2) {//第二次引导必给黄阶法防斗魂
			List<DictFightSoul> fightSoulCanList = new ArrayList<>();
			for (DictFightSoul fightSoulObj : DictList.dictFightSoulList) {
				if (fightSoulObj.getLevel() == 1 && fightSoulObj.getFightSoulQualityId() == StaticFightSoulQuality.huang) {
					if (isFixFightProp(fightSoulObj.getId(), StaticFightProp.fDefense)) {
						fightSoulCanList.add(fightSoulObj);
					}
				}
			}
			if (fightSoulCanList.size() > 0) {
				int random = RandomUtil.getRangeInt(0, fightSoulCanList.size() - 1);
				fightSoul = fightSoulCanList.get(random);
			}
		}
		
		InstPlayerFightSoul instPlayerFightSoul = new InstPlayerFightSoul();
		instPlayerFightSoul.setInstPlayerId(instPlayerId);
		instPlayerFightSoul.setFightSoulId(fightSoul.getId());
		instPlayerFightSoul.setFightSoulQualityId(fightSoul.getFightSoulQualityId());
		instPlayerFightSoul.setLevel(1);
		instPlayerFightSoul.setLockState(0);//锁定状态 0-未锁定 1-锁定
		instPlayerFightSoul.setInstCardId(0);
		instPlayerFightSoul.setPosition(0);
		instPlayerFightSoul.setExp(0);
		getInstPlayerFightSoulDAL().add(instPlayerFightSoul, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
		
		return fightSoul;
	}
	
	/**
	 * 添加斗魂
	 * @author mp
	 * @date 2015-11-23 下午4:15:09
	 * @param instPlayerId
	 * @param fightSoulId
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void addFightSoul (int instPlayerId, int fightSoulId, MessageData syncMsgData) throws Exception{ 
		DictFightSoul fightSoul = DictMap.dictFightSoulMap.get(fightSoulId + "");
		InstPlayerFightSoul instPlayerFightSoul = new InstPlayerFightSoul();
		instPlayerFightSoul.setInstPlayerId(instPlayerId);
		instPlayerFightSoul.setFightSoulId(fightSoulId);
		instPlayerFightSoul.setFightSoulQualityId(fightSoul.getFightSoulQualityId());
		instPlayerFightSoul.setLevel(1);
		instPlayerFightSoul.setLockState(0);//锁定状态 0-未锁定 1-锁定
		instPlayerFightSoul.setInstCardId(0);
		instPlayerFightSoul.setPosition(0);
		instPlayerFightSoul.setExp(0);
		getInstPlayerFightSoulDAL().add(instPlayerFightSoul, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
	}
	
	/**
	 * 是否为固定属性的斗魂
	 * @author mp
	 * @date 2015-10-30 下午2:38:06
	 * @param fightSoulId
	 * @param fightPropId
	 * @return
	 * @Description
	 */
	public static boolean isFixFightProp (int fightSoulId, int fightPropId) {
		boolean flag = false;
		List<DictFightSoulUpgradeProp> fightSoulUpgradePropList = DictList.dictFightSoulUpgradePropList;
		for (DictFightSoulUpgradeProp obj : fightSoulUpgradePropList) {
			if (obj.getFightSoulId() == fightSoulId && obj.getFightPropId() == fightPropId) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 点亮猎魂的某个位置
	 * @author mp
	 * @date 2015-10-17 下午4:10:55
	 * @param instPlayerId
	 * @param fightSouleHuntRuleId
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerFightSoulHuntRule (int instPlayerId, int fightSouleHuntRuleId, MessageData syncMsgData) throws Exception{
		InstPlayerFightSoulHuntRule instPlayerFightSoulHuntRule = new InstPlayerFightSoulHuntRule();
		instPlayerFightSoulHuntRule.setInstPlayerId(instPlayerId);
		instPlayerFightSoulHuntRule.setFightSouleHuntRuleId(fightSouleHuntRuleId);
		getInstPlayerFightSoulHuntRuleDAL().add(instPlayerFightSoulHuntRule, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerFightSoulHuntRule, instPlayerFightSoulHuntRule.getId(), instPlayerFightSoulHuntRule.getResult(), syncMsgData);
	}
	
	/**
	 * 根据斗魂等级获取银币
	 * @author mp
	 * @date 2015-10-17 下午4:07:46
	 * @param fightSoulId
	 * @param level
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int getSilverByLevel (int fightSoulId, int level) {
		int silver = 0;
		List<DictFightSoulUpgradeProp> fightSoulUpgradePropList = (List<DictFightSoulUpgradeProp>)DictMapList.dictFightSoulUpgradePropMap.get(fightSoulId);
		for (DictFightSoulUpgradeProp obj : fightSoulUpgradePropList) {
			if (obj.getLevel() == level) {
				silver = obj.getSellSilver();
				break;
			}
		}
		return silver;
	}

	/**
	 * 根据经验计算出等级和剩余经验
	 * @author mp
	 * @date 2015-10-20 上午11:50:46
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int [] getLevelAndRemainExpByExp (int saveExp, int fightSoulQualityId) {
		int level = 0;
		int remainExp = 0;
		int calcExp = 0;
		List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(fightSoulQualityId);
		for (DictFightSoulUpgradeExp obj : fightSoulUpgradeExpList) {
			calcExp += obj.getExp();
			if (calcExp > saveExp) {
				level = obj.getLevel();
				remainExp = (obj.getExp() - (calcExp - saveExp));
				break;
			} else if (calcExp == saveExp) {
				level = obj.getLevel() + 1;
				remainExp = 0;
				break;
			}
		}
		return new int[] {level, remainExp};
	}
	
	/**
	 * 获得满级经验
	 * @author mp
	 * @date 2015-10-20 下午7:11:15
	 * @param fightSoulQulaityId
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int upLevelExp (int fightSoulQulaityId) {
		int upLevelExp = 0;
		List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(fightSoulQulaityId);
		for (DictFightSoulUpgradeExp obj : fightSoulUpgradeExpList) {
			upLevelExp += obj.getExp();
		}
		return upLevelExp;
	}
	
	/**
	 * 分解，轮回，吃卡是如果此卡有斗魂，卸下
	 * @author mp
	 * @date 2015-10-20 下午4:28:06
	 * @Description
	 */
	public static String dropFightSoulIfCardExsits (int instPlayerId, int instCardId, MessageData syncMsgData) throws Exception {
		String fightSoulList = "";//卡牌实例Id_斗魂实例Id_斗魂字典Id_斗魂名字_斗魂等级;
		List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instCardId, instPlayerId);
		if (instPlayerFightSoulList.size() > 0) {
			for (InstPlayerFightSoul instPlayerFightSoul : instPlayerFightSoulList) {
				instPlayerFightSoul.setInstCardId(0);
				instPlayerFightSoul.setPosition(0);
				getInstPlayerFightSoulDAL().update(instPlayerFightSoul, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFightSoul, instPlayerFightSoul.getId(), instPlayerFightSoul.getResult(), syncMsgData);
				fightSoulList = instPlayerFightSoul.getInstCardId() + "_" + instPlayerFightSoul.getId() + "_" + instPlayerFightSoul.getFightSoulId() + "_"  + DictMap.dictFightSoulMap.get(instPlayerFightSoul.getFightSoulId() + "").getName() + "_" + instPlayerFightSoul.getLevel() + ";";
			}
		}
		return fightSoulList;
	}
	
	/**
	 * 分解，轮回，吃卡是如果此卡有斗魂，返回
	 * @author mp
	 * @date 2015-10-29 上午10:57:16
	 * @param instPlayerId
	 * @param instCardId
	 * @param syncMsgData
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static String getFightSoulIfCardExsits (int instPlayerId, int instCardId) throws Exception {
		String fightSoulList = "";//斗魂实例Id;
		List<InstPlayerFightSoul> instPlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instCardId, instPlayerId);
		if (instPlayerFightSoulList.size() > 0) {
			for (InstPlayerFightSoul instPlayerFightSoul : instPlayerFightSoulList) {
				fightSoulList += instPlayerFightSoul.getId() + ";";
			}
		}
		return fightSoulList;
	}

	/**
	 * 当前等级能获得的累计经验
	 * @author mp
	 * @date 2015-10-21 下午8:42:38
	 * @param fightSoulQualityId
	 * @param level
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int getCurrLevelExp (int fightSoulQualityId, int level) {
		int sumExp = 0;
		List<DictFightSoulUpgradeExp> fightSoulUpgradeExpList = (List<DictFightSoulUpgradeExp>)DictMapList.dictFightSoulUpgradeExpMap.get(fightSoulQualityId);
		for (DictFightSoulUpgradeExp obj : fightSoulUpgradeExpList) {
			if (obj.getLevel() < level) {
				sumExp += obj.getExp();
			}
		}
		return sumExp;
	}
	
	/**
	 * 根据斗魂id找到此斗魂加的战斗属性类型和值类型
	 * @author mp
	 * @date 2015-10-26 下午2:00:27
	 * @param fightSoulId
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static String getFightTypeByFightSoulId (int fightSoulId) {
		List<DictFightSoulUpgradeProp> fightSoulUpgradePropList = (List<DictFightSoulUpgradeProp>)DictMapList.dictFightSoulUpgradePropMap.get(fightSoulId);
		DictFightSoulUpgradeProp fightSoulUpgradeProp = fightSoulUpgradePropList.get(0);
		return fightSoulUpgradeProp.getFightPropId() + ";" + fightSoulUpgradeProp.getFightPropValueType();//战斗属性id_属性值类型
	}
	
}
