package mmo.common.module.account.doupo.security;

public class TokenData {
	/** 30分钟后过期 */
	private final static int OVERTIME_OFFSET = 1000 * 60 * 60 * 2;

	/********************************************************** 过期时间 ****/
	/** 过期时间 */
	private long overtime;
	/** 时间被重置过，需要重新排队 */
	private boolean reorder;
	/****************************************************************/
	private String key;
	private String data;
	private String type;

	public TokenData() {
		this.overtime = System.currentTimeMillis() + OVERTIME_OFFSET;
	}

	public long getOvertime() {
		return overtime;
	}

	public void setOvertime(long overtime) {
		this.overtime = overtime;
	}

	public boolean isReorder() {
		return reorder;
	}

	public void setReorder(boolean reorder) {
		this.reorder = reorder;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOvertime(long currTime) {
		return currTime > overtime;
	}

	public void resetTime() {
		this.overtime = System.currentTimeMillis() + OVERTIME_OFFSET;
		this.reorder = true;
	}
}
