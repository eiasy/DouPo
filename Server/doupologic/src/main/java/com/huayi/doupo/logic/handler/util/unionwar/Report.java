package com.huayi.doupo.logic.handler.util.unionwar;

public class Report {

	/**
	 * 事件
	 */
	private int event;

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	/**
	 * 战场索引（非Id）
	 */
	private int battlefieldIdx;

	public int getBattlefieldIdx() {
		return battlefieldIdx;
	}

	public void setBattlefieldIdx(int battlefieldIdx) {
		this.battlefieldIdx = battlefieldIdx;
	}

	/**
	 * 玩家A ID
	 */
	private int instPlayerA;

	public int getInstPlayerA() {
		return instPlayerA;
	}

	public void setInstPlayerA(int instPlayerA) {
		this.instPlayerA = instPlayerA;
	}

	/**
	 * 玩家B ID
	 */
	private int instPlayerB;

	public int getInstPlayerB() {
		return instPlayerB;
	}

	public void setInstPlayerB(int instPlayerB) {
		this.instPlayerB = instPlayerB;
	}

	/**
	 * 是否玩家A胜利
	 * 对阵时：1=A胜利，0=B胜利
	 * 战场时：1=A胜利，0=B胜利，-1=平局
	 * 打架时：1=A胜利，0=B胜利
	 */
	private int isAWin;

	public int getIsAWin() {
		return isAWin;
	}

	public void setIsAWin(int isAWin) {
		this.isAWin = isAWin;
	}

	/**
	 * 战斗索引
	 */
	private int fightIndex;

	public int getFightIndex() {
		return fightIndex;
	}

	public void setFightIndex(int fightIndex) {
		this.fightIndex = fightIndex;
	}

	/**
	 * 录像Id
	 */
	private int videoId;

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	/**
	 * 录像lua数据
	 */
	private String videoLua;

	public String getVideoLua() {
		return videoLua;
	}

	public void setVideoLua(String videoLua) {
		this.videoLua = videoLua;
	}

	/**
	 * 战场胜利积分A
	 */
	private int battlefieldWinScoreA;

	public int getBattlefieldWinScoreA() {
		return battlefieldWinScoreA;
	}

	public void setBattlefieldWinScoreA(int battlefieldWinScoreA) {
		this.battlefieldWinScoreA = battlefieldWinScoreA;
	}

	/**
	 * 战场胜利积分B
	 */
	private int battlefieldWinScoreB;

	public int getBattlefieldWinScoreB() {
		return battlefieldWinScoreB;
	}

	public void setBattlefieldWinScoreB(int battlefieldWinScoreB) {
		this.battlefieldWinScoreB = battlefieldWinScoreB;
	}

	/**
	 * 战场击杀积分A
	 */
	private int battlefieldKillScoreA;

	public int getBattlefieldKillScoreA() {
		return battlefieldKillScoreA;
	}

	public void setBattlefieldKillScoreA(int battlefieldKillScoreA) {
		this.battlefieldKillScoreA = battlefieldKillScoreA;
	}

	/**
	 * 战场击杀积分B
	 */
	private int battlefieldKillScoreB;

	public int getBattlefieldKillScoreB() {
		return battlefieldKillScoreB;
	}

	public void setBattlefieldKillScoreB(int battlefieldKillScoreB) {
		this.battlefieldKillScoreB = battlefieldKillScoreB;
	}

	/**
	 * 战场存活积分A
	 */
	private int battlefieldAliveScoreA;

	public int getBattlefieldAliveScoreA() {
		return battlefieldAliveScoreA;
	}

	public void setBattlefieldAliveScoreA(int battlefieldAliveScoreA) {
		this.battlefieldAliveScoreA = battlefieldAliveScoreA;
	}

	/**
	 * 战场存活积分B
	 */
	private int battlefieldAliveScoreB;

	public int getBattlefieldAliveScoreB() {
		return battlefieldAliveScoreB;
	}

	public void setBattlefieldAliveScoreB(int battlefieldAliveScoreB) {
		this.battlefieldAliveScoreB = battlefieldAliveScoreB;
	}

	/**
	 * 总积分A
	 */
	private int totalScoreA;

	public int getTotalScoreA() {
		return totalScoreA;
	}

	public void setTotalScoreA(int totalScoreA) {
		this.totalScoreA = totalScoreA;
	}

	/**
	 * 总积分B
	 */
	private int totalScoreB;

	public int getTotalScoreB() {
		return totalScoreB;
	}

	public void setTotalScoreB(int totalScoreB) {
		this.totalScoreB = totalScoreB;
	}

}
