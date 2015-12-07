package mmo.common.bean.notice;

import mmo.tools.util.string.StringSplit;

public class OnlineNotice {
	private short  lvsub = 0;  // 可接收到公告的等级下限
	private short  lvsup = 0;  // 可接公告的等级上限
	private String noticeTitle;
	private String noticeText;

	public OnlineNotice() {

	}

	public short getLvsub() {
		return lvsub;
	}

	public void setLvsub(short lvsub) {
		this.lvsub = lvsub;
	}

	public short getLvsup() {
		return lvsup;
	}

	public void setLvsup(short lvsup) {
		this.lvsup = lvsup;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeText() {
		return noticeText;
	}

	public void setNoticeText(String noticeText) {
		this.noticeText = StringSplit.transformString(noticeText);
	}

	@Override
	public String toString() {
		return this.lvsub + ", " + this.lvsup + ", " + this.noticeTitle + ", " + this.noticeText;
	}
}
