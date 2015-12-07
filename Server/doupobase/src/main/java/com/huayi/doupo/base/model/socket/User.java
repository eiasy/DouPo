
package com.huayi.doupo.base.model.socket;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -9204068956967093254L;

	private int userId;// 用户ID
	
	private int channelId;// channel通道ID
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	
}
