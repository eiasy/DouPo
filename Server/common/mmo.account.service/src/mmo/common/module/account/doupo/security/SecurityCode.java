package mmo.common.module.account.doupo.security;

import java.util.Date;

import mmo.tools.util.DateUtil;

public class SecurityCode {
	/** 30分钟后过期 */
	private final static int OVERTIME_OFFSET = 1000 * 60 * 60 * 2;

	/********************************************************** 过期时间 ****/
	/** 过期时间 */
	private long             overtime;
	/** 时间被重置过，需要重新排队 */
	private boolean          reorder;
	/********************************************************** 用户数据 ****/
	private int              accountId;
	private String           userId;
	private String           securityCode;
	/** 渠道编号 */
	protected String         channelId;
	/** 客户端版本号 */
	protected int            clientVersion;
	/** 游戏编号 */
	protected int            gameId;
	protected long           registerTime;
	/** 子渠道 */
	protected String         channelSub;
	/** 归属的渠道号 */
	protected int            belongto;
	protected String         loginServer;

	public String getLoginServer() {
		return loginServer;
	}

	public void setLoginServer(String loginServer) {
		this.loginServer = loginServer;
	}

	SecurityCode(String securityCode) {
		this.securityCode = securityCode;
		this.overtime = System.currentTimeMillis() + OVERTIME_OFFSET;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public int getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(int clientVersion) {
		this.clientVersion = clientVersion;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public boolean isOvertime() {
		return System.currentTimeMillis() > overtime;
	}

	public boolean isOvertime(long currTime) {
		return currTime > overtime;
	}

	public void resetTime() {
		this.overtime = System.currentTimeMillis() + OVERTIME_OFFSET;
		this.reorder = true;
	}

	public void resetTime(long currTime) {
		this.overtime = currTime + OVERTIME_OFFSET;
		this.reorder = true;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}

	public boolean isReorder() {
		return reorder;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public int getBelongto() {
		return belongto;
	}

	public void setBelongto(int belongto) {
		this.belongto = belongto;
	}

	@Override
	public String toString() {
		return "SecurityCode [overtime=" + DateUtil.formatDatetime(new Date(overtime)) + ", reorder=" + reorder + ", accountId=" + accountId
		        + ", userId=" + userId + ", securityCode=" + securityCode + "]";
	}

	public void setReorder(boolean reorder) {
		this.reorder = reorder;
	}
}
