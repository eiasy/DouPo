package com.huayi.doupo.base.model.socket;

import java.io.Serializable;


/**
 * 世界Boss的抽象Player对象
 * @author hzw
 * @date 2015-1-4上午11:33:46
 */
public class WorldBossPlayer implements Serializable{
	
	private static final long serialVersionUID = 1067026970318129628L;

	/**
	 * 玩家Id
	 */
	private int playerId; 
	
	/**
	 * 玩家昵称
	 */
	private String playerName;
	
	/**
	 * 玩家等级
	 */
	private int level;
	
	/**
	 * 打boss的次数
	 */
	private int bossFightTimes;
	
	/**
	 * 单次伤害
	 */
	private int bossOnceHurt;
	
	/**
	 * 总伤害
	 */
	private int bossSumHurt;
	
	/**
	 * 银币鼓舞次数
	 */
	private int copperInspireNum;
	
	/**
	 * 元宝鼓舞次数
	 */
	private int goldInspireNum;
	
	/**
	 * 元宝重生次数
	 */
	private int goldRebirthNum;
	
	/**
	 * 死亡时间（用于继续挑战倒计时）
	 */
	private String overTime;
	
	/**
	 * 状态 0-未参战， 1-参战
	 */
	private int exitState;
	
	/**
	 * 开大宝箱状态 0-未开大宝箱 1-已开大宝箱
	 */
	private int openBigBoxState;
	
	
	
	public int getOpenBigBoxState() {
		return openBigBoxState;
	}

	public void setOpenBigBoxState(int openBigBoxState) {
		this.openBigBoxState = openBigBoxState;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBossFightTimes() {
		return bossFightTimes;
	}

	public void setBossFightTimes(int bossFightTimes) {
		this.bossFightTimes = bossFightTimes;
	}

	public int getBossOnceHurt() {
		return bossOnceHurt;
	}

	public void setBossOnceHurt(int bossOnceHurt) {
		this.bossOnceHurt = bossOnceHurt;
	}

	public int getBossSumHurt() {
		return bossSumHurt;
	}

	public void setBossSumHurt(int bossSumHurt) {
		this.bossSumHurt = bossSumHurt;
	}

	public int getCopperInspireNum() {
		return copperInspireNum;
	}

	public void setCopperInspireNum(int copperInspireNum) {
		this.copperInspireNum = copperInspireNum;
	}

	public int getGoldInspireNum() {
		return goldInspireNum;
	}

	public void setGoldInspireNum(int goldInspireNum) {
		this.goldInspireNum = goldInspireNum;
	}

	public int getGoldRebirthNum() {
		return goldRebirthNum;
	}

	public void setGoldRebirthNum(int goldRebirthNum) {
		this.goldRebirthNum = goldRebirthNum;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public int getExitState() {
		return exitState;
	}

	public void setExitState(int exitState) {
		this.exitState = exitState;
	}


	
}
