package com.huayi.doupo.base.model.memcalc;

import java.util.Map;

/**
 * 卡牌计算基础类
 * @author mp
 * @date 2014-6-26 上午9:45:15
 */
public class CardCalcObj {
	
	/**
	 * 卡牌字典Id
	 */
	private int cardId;
	
	/**
	 * 是否在队伍中
	 */
	private int inTeam;
	
	/**
	 * 卡牌等级相关
	 */
	private int level;
	
	/**
	 * 卡牌进阶等级相关
	 */
	private int advanceLevel;
	
	/**
	 * 装备相关
	 */
	private Map<Integer, EquipCalcObj> equipCalcMap = null;
	
	/**
	 * 培养相关
	 */
	private Map<Integer, Integer> trainCalcMap = null;

	
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAdvanceLevel() {
		return advanceLevel;
	}

	public void setAdvanceLevel(int advanceLevel) {
		this.advanceLevel = advanceLevel;
	}

	public Map<Integer, EquipCalcObj> getEquipCalcMap() {
		return equipCalcMap;
	}

	public void setEquipCalcMap(Map<Integer, EquipCalcObj> equipCalcMap) {
		this.equipCalcMap = equipCalcMap;
	}

	public Map<Integer, Integer> getTrainCalcMap() {
		return trainCalcMap;
	}

	public void setTrainCalcMap(Map<Integer, Integer> trainCalcMap) {
		this.trainCalcMap = trainCalcMap;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getInTeam() {
		return inTeam;
	}

	public void setInTeam(int inTeam) {
		this.inTeam = inTeam;
	}
	
}
