package mmo.common.config.goods;

/**
 * 物品绑定技能的冷却时间
 * 
 * @author 李天喜
 * 
 */
public class GoodsSkillCool {
	/** 技能编号 */
	private int  skillId;
	/** 持续时间 */
	private int  continueTime;
	/** 开始时间 */
	private long startTime;
	/** 结束时间 */
	private long endTime;

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getContinueTime() {
		return continueTime;
	}

	public void setContinueTime(int continueTime) {
		this.continueTime = continueTime;
	}

	/**
	 * 开始冷却
	 */
	public void startCool() {
		startTime = System.currentTimeMillis();
		endTime = startTime + continueTime;
	}

	/**
	 * 冷却是否结束
	 * 
	 * @return true结束，false未结束
	 */
	public boolean isEndCool() {
		return System.currentTimeMillis() > endTime;
	}

	/**
	 * 获取剩余的冷却时间
	 * 
	 * @return 剩余的冷却时间
	 */
	public int getRemainTime() {
		return (int) (endTime - System.currentTimeMillis());
	}
}
