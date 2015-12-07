package mmo.common.bean.sect;

import mmo.common.config.MoneyConfig;

public class SectLevel {
	/** 宗门等级 */
	private short level;
	/** 消耗绑灵 */
	private int   gold;
	/** 宗门建设度 */
	private int   perfect;

	public SectLevel() {

	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public boolean validate(IGameSect sect) {
		return !(sect.getMoney(MoneyConfig.LING_SHI_1000) < gold);
	}

	public int getPerfect() {
		return perfect;
	}

	public void setPerfect(int perfect) {
		this.perfect = perfect;
	}

	public boolean checkoff(IGameSect sect) {
		if (validate(sect)) {
			sect.changeMoney(MoneyConfig.LING_SHI_1000, -gold);
			return true;
		}
		return false;
	}
}
