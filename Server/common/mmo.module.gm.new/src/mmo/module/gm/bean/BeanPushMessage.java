package mmo.module.gm.bean;

import mmo.common.account.HttpCData;
import mmo.common.activity.SystemActivityType;
import mmo.common.activity.bean.SystemActivityCData;
import net.sf.json.JSONObject;

public class BeanPushMessage {
	/** 消息ID */
	private int    id;
	/** 推送时间hh:mm */
	private String pushTime;
	/** 推送间隔（分） */
	private int    pushOffset;
	private String cdata;
	private byte   status;

	/*******************************************************/
	private String title;
	private String content;
	private String channel;
	private String target;

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public int getPushOffset() {
		return pushOffset;
	}

	public void setPushOffset(int pushOffset) {
		this.pushOffset = pushOffset;
	}

	public String getCdata() {
		return cdata;
	}

	public void setCdata(String cdata) {
		this.cdata = cdata;
		initData();
	}

	public void initData() {
		JSONObject jsonObj = JSONObject.fromObject(cdata);
		JSONObject data = jsonObj.getJSONObject(HttpCData.PushMessage.cdata);
		this.title = data.getString(HttpCData.PushMessage.title);
		this.content = data.getString(HttpCData.PushMessage.content);
		this.channel = data.getString(HttpCData.PushMessage.channel);
		this.target = data.getString(HttpCData.PushMessage.target);
	}
}
