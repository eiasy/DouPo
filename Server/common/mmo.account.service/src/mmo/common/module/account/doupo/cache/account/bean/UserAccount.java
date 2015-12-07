package mmo.common.module.account.doupo.cache.account.bean;

/**
 * 用户账号信息
 * 
 * @author fanren
 * 
 */
public class UserAccount {
	private int    accountId;
	private String userid;
	private String reuserid;
	private String username;
	/** MD5加密过的密码 */
	private String password;
	private byte   sex;
	private String phone;
	private String email;
	private String channelId;
	private int    belongto;
	private String permit;
	private long   timeRegister;
	private byte   state;
	private int    money;
	private long   timeFreeze;
	private String deviceImei;
	private byte   pwdState;
	private long   freezeDay;
	private int    loginCount;
	private String channelSub;
	private String customData;
	private String activeCode;

	public String getActiveCode() {
		if (activeCode == null) {
			return "";
		}
		return activeCode;
	}

	public void setActiveCode(String accountCode) {
		this.activeCode = accountCode;
	}

	public boolean isActive() {
		return activeCode != null && activeCode.length() > 1;
	}

	public String getCustomData() {
		return customData;
	}

	public void setCustomData(String customData) {
		this.customData = customData;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getReuserid() {
		return reuserid;
	}

	public void setReuserid(String reuserid) {
		this.reuserid = reuserid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public int getBelongto() {
		return belongto;
	}

	public void setBelongto(int belongto) {
		this.belongto = belongto;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public long getTimeRegister() {
		return timeRegister;
	}

	public void setTimeRegister(long timeRegister) {
		this.timeRegister = timeRegister;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public long getTimeFreeze() {
		return timeFreeze;
	}

	public void setTimeFreeze(long timeFreeze) {
		this.timeFreeze = timeFreeze;
	}

	public String getDeviceImei() {
		return deviceImei;
	}

	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}

	public byte getPwdState() {
		return pwdState;
	}

	public void setPwdState(byte pwdState) {
		this.pwdState = pwdState;
	}

	public long getFreezeDay() {
		return freezeDay;
	}

	public void setFreezeDay(long freezeDay) {
		this.freezeDay = freezeDay;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	@Override
	public String toString() {
		return "UserAccount [accountId=" + accountId + ", userid=" + userid + ", reuserid=" + reuserid + ", username=" + username + ", password="
		        + password + ", sex=" + sex + ", phone=" + phone + ", email=" + email + ", channelId=" + channelId + ", belongto=" + belongto
		        + ", permit=" + permit + ", timeRegister=" + timeRegister + ", state=" + state + ", money=" + money + ", timeFreeze=" + timeFreeze
		        + ", deviceImei=" + deviceImei + ", pwdState=" + pwdState + ", freezeDay=" + freezeDay + ", loginCount=" + loginCount
		        + ", channelSub=" + channelSub + "]";
	}
}
