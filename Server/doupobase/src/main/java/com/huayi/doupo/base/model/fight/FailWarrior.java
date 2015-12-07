package com.huayi.doupo.base.model.fight;

/**
 * 失败武士对象
 * @author mp
 * @date 2013-11-27 下午2:22:07
 */
public class FailWarrior {
	
	/**
	 * 武士字典Id
	 */
	private int warriorId;
	
	/**
	 * 第几回合
	 * 1:首轮落败 2：第二轮落败 3：意志比拼失败
	 */
	private int round;
	

	public int getWarriorId() {
		return warriorId;
	}

	public void setWarriorId(int warriorId) {
		this.warriorId = warriorId;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	
	
}
