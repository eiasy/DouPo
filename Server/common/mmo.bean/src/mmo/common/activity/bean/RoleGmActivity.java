package mmo.common.activity.bean;

public class RoleGmActivity {
	/** 活动编号 */
	private int  gmaId;
	/** 参与次数 */
	private int  count;
	/** 最后一次参与时间 */
	private long lastTime;

	public int getGmaId() {
		return gmaId;
	}

	public void setGmaId(int gmaId) {
		this.gmaId = gmaId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public void countIncrease() {
		count++;
	}
}
