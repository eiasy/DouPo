package com.huayi.doupo.base.model.memcalc;

import java.util.Map;

/**
 * 用于计算战斗属性的内存基础类
 * @author mp
 * @date 2014-6-26 上午9:42:08
 */
public class MemCalcObj {
	
	/**
	 * 卡牌对象列表
	 * 里面包含影响单张卡牌属性的因素
	 */
	private Map<Integer, CardCalcObj> cardCalcMap = null;
	
	/**
	 * 修行造成的影响
	 * 影响所有上阵卡牌属性
	 */
	private PracticeCalcObj practiceCalcObj = null;
	
	/**
	 * 公会造成的影响
	 * 影响所有上阵卡牌属性
	 */
	private UnionCalcObj unionCalcObj = null;
	
	

	public Map<Integer, CardCalcObj> getCardCalcMap() {
		return cardCalcMap;
	}

	public void setCardCalcMap(Map<Integer, CardCalcObj> cardCalcMap) {
		this.cardCalcMap = cardCalcMap;
	}

	public UnionCalcObj getUnionCalcObj() {
		return unionCalcObj;
	}

	public void setUnionCalcObj(UnionCalcObj unionCalcObj) {
		this.unionCalcObj = unionCalcObj;
	}

	public PracticeCalcObj getPracticeCalcObj() {
		return practiceCalcObj;
	}

	public void setPracticeCalcObj(PracticeCalcObj practiceCalcObj) {
		this.practiceCalcObj = practiceCalcObj;
	}
	
}
