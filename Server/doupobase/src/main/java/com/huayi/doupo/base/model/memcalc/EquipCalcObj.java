package com.huayi.doupo.base.model.memcalc;

import java.util.Map;

/**
 * 装备计算类
 * @author mp
 * @date 2014-6-26 上午10:26:37
 */
public class EquipCalcObj {
	
	/**
	 * 装备字典Id
	 */
	private int equipId;
	
	/**
	 * 装备等级相关
	 */
	private int level;
	
	/**
	 * 宝石相关
	 */
	Map<Integer, GemCalcObj> gemCalcMap = null;

	
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Map<Integer, GemCalcObj> getGemCalcMap() {
		return gemCalcMap;
	}

	public void setGemCalcMap(Map<Integer, GemCalcObj> gemCalcMap) {
		this.gemCalcMap = gemCalcMap;
	}

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}
	
}
