package mmo.common.config.realmpoint;

public class Realm_1 {
	int startLevel;
	int endLevel;
	/**
	 * 玩家最低等级限制
	 */
	int limitLevel;
	int realmPoint;
	/**
	 * 每日领取的最大次数
	 */
	int count;
	public int getRealmPoint() {
		return realmPoint;
	}
	public void setRealmPoint(int realmPoint) {
		this.realmPoint = realmPoint;
	}
	public int getStartLevel() {
		return startLevel;
	}
	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}
	public int getEndLevel() {
		return endLevel;
	}
	public void setEndLevel(int endLevel) {
		this.endLevel = endLevel;
	}
	public int getLimitLevel() {
		return limitLevel;
	}
	public void setLimitLevel(int limitLevel) {
		this.limitLevel = limitLevel;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
