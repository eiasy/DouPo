package com.huayi.doupo.base.model.player;

/**
 * 充值延迟玩家
 * @author mp
 * @date 2015-3-27 下午4:09:50
 */
public class DelayRechargePlayer {
	
	/**
	 * 当前时间毫秒数-用于判断超过2分钟后,从队列中消除
	 */
	private long currMill;
	
	/**
	 * 玩家Id
	 */
	private int instPlayerId;
	
	/**
	 * openId
	 */
	private String openId;
	
	/**
	 * 玩家名字
	 */
	private String playerName;
	
	/**
	 * 充值渠道
	 */
	private int payChannel;

	/**
	 * 渠道
	 */
	private String channel;
	
	/**
	 * 下单成功时购买的数量(游戏币)
	 */
	private int saveNum;
	
	
	
	public int getSaveNum() {
		return saveNum;
	}

	public void setSaveNum(int saveNum) {
		this.saveNum = saveNum;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public long getCurrMill() {
		return currMill;
	}

	public void setCurrMill(long currMill) {
		this.currMill = currMill;
	}

	public int getInstPlayerId() {
		return instPlayerId;
	}

	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(int payChannel) {
		this.payChannel = payChannel;
	}
	
}
