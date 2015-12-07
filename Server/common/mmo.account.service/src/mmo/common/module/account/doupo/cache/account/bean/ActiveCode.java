package mmo.common.module.account.doupo.cache.account.bean;

public class ActiveCode {
	private int    id;
	private String code;
	private long   timeEnd;

	public ActiveCode(int id, String code, long timeEnd) {
		super();
		this.id = id;
		this.code = code;
		this.timeEnd = timeEnd;
	}

	public String getCode() {
		return code;
	}

	public int getId() {
		return id;
	}

	public long getTimeEnd() {
		return timeEnd;
	}
}
