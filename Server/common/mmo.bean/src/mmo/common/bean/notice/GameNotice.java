package mmo.common.bean.notice;

import mmo.common.bean.role.Role;
import mmo.tools.util.string.StringSplit;

public class GameNotice {
	private int    id;
	private String title;
	private String titleStyle;
	private String content;
	private String contentStyle;
	private short  levelSub;
	private short  levelSup;
	private long   timeEffect;
	private long   timeOvertime;
	private int    orderValue;
	/** 状态：0正常，1删除 */
	private byte   status;
	private byte   type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.titleStyle = StringSplit.transformString(title);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.contentStyle = StringSplit.transformString(content);
	}

	public short getLevelSub() {
		return levelSub;
	}

	public void setLevelSub(short levelSub) {
		this.levelSub = levelSub;
	}

	public short getLevelSup() {
		return levelSup;
	}

	public void setLevelSup(short levelSup) {
		this.levelSup = levelSup;
	}

	public long getTimeEffect() {
		return timeEffect;
	}

	public void setTimeEffect(long timeEffect) {
		this.timeEffect = timeEffect;
	}

	public long getTimeOvertime() {
		return timeOvertime;
	}

	public void setTimeOvertime(long timeOvertime) {
		this.timeOvertime = timeOvertime;
	}

	public int getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTitleStyle() {
		return titleStyle;
	}

	public String getContentStyle() {
		return contentStyle;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public boolean isEffect(Role role) {
		return timeEffect < System.currentTimeMillis() && (levelSub < 1 || role.getLevel() >= levelSub)
		        && (levelSup < 1 || role.getLevel() <= levelSup);
	}

	public boolean isEffect() {
		return timeEffect < System.currentTimeMillis();
	}

	public boolean isInvalid() {
		return status != 0 || timeOvertime < System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return "GameNotice [id=" + id + ", title=" + title + ", titleStyle=" + titleStyle + ", content=" + content + ", contentStyle=" + contentStyle
		        + ", levelSub=" + levelSub + ", levelSup=" + levelSup + ", timeEffect=" + timeEffect + ", timeOvertime=" + timeOvertime
		        + ", orderValue=" + orderValue + ", status=" + status + ", type=" + type + "]";
	}
}
