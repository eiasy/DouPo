package mmo.common.module.account.doupo.cache.account.charge;

public class AccountCharge {
	/** 数据库中的记录编号 */
	private int  id;
	/** 充值账号ID */
	private int  accountId;
	/** 充值人民币数量（分） */
	private int  rmb;
	/** 充值的次数 */
	private int  counter;
	/** 在该游戏充值 */
	private int  gameId;
	/** 在该分区充值 */
	private int  serverId;
	/** 状态：0未领取，1，已领取 */
	private byte status;
	/** 领取奖励的角色编号 */
	private int  roleId;
	/** 领取奖励的角色所在服务器的编号 */
	private int  roleServerId;
	/** 领取奖励的时间 */
	private long timeAward;
	/** 第一次充值时间 */
	private long timeCharge;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getRmb() {
		return rmb;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleServerId() {
		return roleServerId;
	}

	public void setRoleServerId(int roleServerId) {
		this.roleServerId = roleServerId;
	}

	public long getTimeAward() {
		return timeAward;
	}

	public void setTimeAward(long timeAward) {
		this.timeAward = timeAward;
	}

	public void counterAdd() {
		this.counter++;
	}

	public void rmbAdd(int money) {
		this.rmb += money;
	}

	public long getTimeCharge() {
		return timeCharge;
	}

	public void setTimeCharge(long timeCharge) {
		this.timeCharge = timeCharge;
	}
}