package mmo.module.gm.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanAccount {
	private int               productId;
	private String            gameName;
	private int               serverId;
	private String            serverName;
	/***************************************/
	private int               accountId;
	private String            phone;
	private String            email;
	private byte              stateDb;
	private int               channelId;

	private String            userid;
	private String            reuserid;
	private String            username;
	/** MD5加密过的密码 */
	private String            password;
	private byte              sex;
	private int               belongto;
	private String            permit;
	private long              timeRegister;
	private byte              state;
	private int               money;
	private long              timeFreeze;
	private String            deviceImei;
	private byte              pwdState;
	private long              freezeDay;
	private int               loginCount;
	private String            channelSub;
	private List<AccountRole> roles = new ArrayList<AccountRole>();

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int gameId) {
		this.productId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public List<AccountRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AccountRole> roles) {
		this.roles = roles;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
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

	public byte getStateDb() {
		return stateDb;
	}

	public void setStateDb(byte stateDb) {
		this.stateDb = stateDb;
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

	public static final class State {
		private static final String[] states = new String[] { "正常", "冻结" };

		public static final String getState(byte state) {
			if (state > -1 && state < states.length) {
				return states[state];
			}
			return "异常#" + state;
		}
	}
}
