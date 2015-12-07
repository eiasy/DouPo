package mmo.tools.net.extension.session;

import java.sql.Timestamp;

import mmo.tools.net.NetSession;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class NetRole {
	private static final String REMOTE_ADDRESS  = "0.0.0.0";
	private static final String PHONE_CODE      = "phone";
	private final static String NULL            = "NULL";
	/** 角色世界唯一 **/
	protected int               id;
	/** 客户端的版本号，用来区分引用的资源版本 */
	protected int               clientVersion;
	/** 帐号ID */
	protected int               accountId;
	/** 产品编号 */
	protected int               productId;
	/** 产品内部应用编号 */
	protected int               applicationId;
	/** 产品名称 */
	protected String            productName     = NULL;
	/** 产品内部应用名称 */
	protected String            applicationName = NULL;
	/** 帐号 */
	protected String            userid;
	/** 用户第二帐号 */
	protected String            reuserid;
	/** 登录服的编号 */
	protected int               loginServerId;
	/** 登录服名称 */
	protected String            loginServerName;
	/** 名称 */
	protected String            username;
	/** 密码 */
	protected String            password;
	/** 密码状态 */
	protected byte              pwdState;
	/** 会话ID */
	protected String            sessionId       = NULL;
	/** 渠道回话ID */
	protected String            channelSessionId;
	/** 资源版本号 */
	protected String            resVersion;
	/** 职业 */
	protected byte              profession;
	/** 是否在线：1在线2离线 */
	protected byte              online          = 2;
	/** 操作码 */
	protected byte              operateCode;
	/** 验证码 */
	protected String            securityCode;
	/** 渠道编号 */
	protected int               channelId;
	/** 账号来源-子渠道 */
	protected int               belongto;
	/** 子渠道 */
	protected String            channelSub;
	/** 1为可正常游戏 2为本游戏封号 */
	protected byte              active          = 1;
	/** 注册IP */
	protected String            registerIp;
	/** 性别 */
	protected byte              sex;
	/** 电话 */
	protected String            phone;
	/** 邮箱 */
	protected String            email;
	/******************************************************************* 时间相关 ********/
	/** 注册时间/首次登陆时间 */
	protected Timestamp         timeAccountRegister;
	/** 冻结账号截止时间 */
	protected long              timeFreeze;
	/** 成功登陆 */
	protected Timestamp         timeLastLogin   = null;
	/** 角色首次进入游戏时，即角色创建时间 */
	protected Timestamp         timeCreateRole;
	/** 最后一次离线时间 */
	protected Timestamp         timeLastOffline;
	/******************************************************************* 时间相关 ********/
	/** 注册来源 */
	protected byte              registerFrom;
	/** 机型 */
	protected String            phoneType;
	/** 远程地址 */
	protected String            remoteAddress;

	/** VIP等级 */
	protected short             vipLevel;
	/** 积分 */
	protected int               integral;
	/** 客户端代码版本号 */
	protected String            codeVersion;
	/** 屏幕分辨率-宽 */
	protected short             screenWidth;
	/** 屏幕分辨率-高 */
	protected short             screenHeight;
	/** 特征串 */
	protected String            character;
	/** 设备系统 */
	protected String            deviceOS;
	/** 客户端系统版本号 */
	protected String            osVersion;
	/** 设备序列号 */
	protected String            deviceSerial;
	/** 设备UDID */
	protected String            deviceUdid;
	/** 设备物理地址 */
	protected String            deviceMac;
	/** 设备UA */
	protected String            deviceUA;
	/** 用户通行证 */
	protected String            permit;

	/** 累计登录次数 */
	protected int               loginCount;
	/** 是否连接上网络 */
	protected boolean           connected;
	/** true：通过重连进入游戏, false：正常登陆进入游戏 */
	private boolean             isReconect;
	/** 是否为高版本客户端 */
	private boolean             hightVersion;
	/** 归属哪个服务器 */
	protected int               belongServer;

	public void setCodeVersion(String codeVersion) {
		this.codeVersion = codeVersion;
	}

	public String getCodeVersion() {
		return codeVersion;
	}

	public boolean checkHeartbeatPacket() {
		return false;
	}

	public int getBelongto() {
		return belongto;
	}

	public void setBelongto(int belongto) {
		this.belongto = belongto;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public byte getPwdState() {
		return pwdState;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getLoginServerId() {
		return loginServerId;
	}

	public void setLoginServerId(int loginServerId) {
		this.loginServerId = loginServerId;
	}

	public String getLoginServerName() {
		return loginServerName;
	}

	public void setLoginServerName(String loginServerName) {
		this.loginServerName = loginServerName;
	}

	public void setPwdState(byte pwdState) {
		this.pwdState = pwdState;
	}

	public String getDeviceOS() {
		if (deviceOS == null || deviceOS.length() < 1) {
			return NULL;
		}
		return deviceOS;
	}

	public long getTimeFreeze() {
		return timeFreeze;
	}

	public void setTimeFreeze(long freeze) {
		this.timeFreeze = freeze;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getOSVersion() {
		if (osVersion == null || osVersion.length() < 1) {
			return NULL;
		}
		return osVersion;
	}

	public void setOSVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getDeviceSerial() {
		if (deviceSerial == null || deviceSerial.length() < 1) {
			return NULL;
		}
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getDeviceUdid() {
		if (deviceUdid == null || deviceUdid.length() < 1) {
			return NULL;
		}
		return deviceUdid;
	}

	public void setDeviceUdid(String deviceUdid) {
		this.deviceUdid = deviceUdid;
	}

	public String getDeviceMac() {
		if (deviceMac == null || deviceMac.length() < 1) {
			return NULL;
		}
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceUA() {
		if (deviceUA == null || deviceUA.length() < 1) {
			return NULL;
		}
		return deviceUA;
	}

	public void setDeviceUA(String deviceUA) {
		this.deviceUA = deviceUA;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public void setReuserid(String reuserid) {
		this.reuserid = reuserid;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public int getAccountId() {
		return accountId;
	}

	public short getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(short vipLevel) {
		this.vipLevel = vipLevel;
	}

	public short getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(short screenWidth) {
		this.screenWidth = screenWidth;
	}

	public short getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(short screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getIntegral() {
		return integral;
	}

	public String getChannelSessionId() {
		return channelSessionId;
	}

	public void setChannelSessionId(String channelSessionId) {
		this.channelSessionId = channelSessionId;
	}

	/**
	 * 修改VIP等级
	 * 
	 * @param newLevel
	 *            新等级
	 * @param reason
	 *            原因
	 */
	public void changeVipLevelTo(short newLevel, String reason) {
		this.vipLevel = newLevel;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(int clientVersion) {
		this.clientVersion = clientVersion;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		if (remoteAddress != null) {
			int index = remoteAddress.indexOf(':');
			if (index > -1) {
				this.remoteAddress = remoteAddress.substring(1, remoteAddress.indexOf(':'));
			} else {
				this.remoteAddress = REMOTE_ADDRESS;
			}
		} else {
			this.remoteAddress = REMOTE_ADDRESS;
		}
	}

	protected NetSession netSession;

	public NetRole() {
	}

	public final void close(boolean immediately) {
		if (netSession != null) {
			netSession.close(immediately);
		}
	}

	public void outline() {
		if (connected) {
			kickedout();
		}
		connected = false;
	}

	public void kickedout() {
		saveOnCloseNet();
		releaseNet();
	}

	public void releaseNet() {
		if (netSession != null) {
			netSession.releaseSession(sessionId);
			netSession.releaseNet();
			netSession = null;
		}
		// releaseResource();
	}

	public void releaseResource() {
		timeLastLogin = null;
		timeLastOffline = null;
	}

	public byte getActive() {
		return active;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public byte getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(byte operateCode) {
		this.operateCode = operateCode;
	}

	public int getChannelId() {
		return channelId;
	}

	public String getEmail() {
		if (email == null || email.length() < 1) {
			return NULL;
		}
		return email;
	}

	public void responseConnect(int connectId) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(8004);
		buf.setNetConfirm(connectId);
		sendData(buf);
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public NetSession getNetSession() {
		return netSession;
	}

	public byte getOnline() {
		return online;
	}

	public String getPassword() {
		if (password == null) {
			return "";
		}
		return password;
	}

	public String getPhone() {
		if (phone == null || phone.length() < 1) {
			return NULL;
		}
		return phone;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	/**
	 * 获取角色职业
	 * 
	 * @return short 职业
	 */
	public byte getProfession() {
		return profession;
	}

	public byte getRegisterFrom() {
		return registerFrom;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public Timestamp getTimeAccountRegister() {
		return timeAccountRegister;
	}

	public String getResVersion() {
		return resVersion;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public String getSessionId() {
		return sessionId;
	}

	public byte getSex() {
		return sex;
	}

	public String getUserid() {
		return userid;
	}

	public String getReuserid() {
		return reuserid;
	}

	public void sendData(IoBuffer packet) {
		if (netSession != null) {
			netSession.sendData(sessionId, packet);
		} else {
			PacketBufferPool.freePacketBuffer(packet);
		}
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public void setNetSession(NetSession netSession) {
		this.netSession = netSession;
		if (netSession != null) {
			this.netSession.changeRole(this);
			this.connected = true;
		}
	}

	public void setOnline(byte online) {
		this.online = online;
	}

	public void setPassword(String password) {
		if (password != null) {
			this.password = filter(password.trim());
		}
	}

	/**
	 * 转换双引号字符
	 * 
	 * @param source
	 * @return
	 */
	private static String filter(String source) {
		if (source != null) {
			char results[] = source.toCharArray();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < results.length; i++) {
				if (results[i] == '"') {
					sb.append('\\');
					sb.append('"');
				} else if (results[i] == '\'') {
					sb.append('\\');
					sb.append('\'');
				} else {
					sb.append(results[i]);
				}
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public void setPhone(String phone) {
		if (phone != null && phone.startsWith(PHONE_CODE)) {
			return;
		}
		this.phone = phone;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void message(String msg) {

	}

	public void message(String msg, int connectId) {

	}

	public void chargeMessage(String message) {

	}

	public void setRegisterFrom(byte registerFrom) {
		this.registerFrom = registerFrom;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public void setTimeAccountRegister(Timestamp registerTime) {
		this.timeAccountRegister = registerTime;
	}

	public void setResVersion(String resVersion) {
		this.resVersion = resVersion;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public void setUserid(String userId) {
		this.userid = userId;
	}

	public void setReuserId(String reuserid) {
		this.reuserid = reuserid;
	}

	public boolean validate(String securityCode) {
		if (securityCode == null) {
			return false;
		}
		return securityCode.equals(this.securityCode);
	}

	public void pushData() {
		if (netSession != null) {
			netSession.pushData();
		}
	}

	/**
	 * 验证兑换码
	 * 
	 * @param eventId
	 *            事件编号
	 * @param exchangeCode
	 *            兑换码
	 * @type 类别
	 */
	public void validateExchangeRemote(int eventId, String exchangeCode) {
		if (netSession != null) {
			netSession.validateExchange(this, eventId, exchangeCode);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param eventId
	 *            事件编号
	 * @param oldPwd
	 *            旧密码
	 * @param newPwd
	 *            新密码
	 */
	public void alterPasswordRemote(int eventId, String oldPwd, String newPwd) {
		if (netSession != null) {
			netSession.alterPassword(this, eventId, oldPwd, newPwd);
		}
	}

	public void validatePhoneRemote(int eventId, String phone, boolean binded) {
		if (netSession != null) {
			netSession.validatePhone(this, eventId, phone, binded);
		}
	}

	public void validateSecurityCoide(int eventId, String securityCode) {
		if (netSession != null) {
			netSession.validateSecurityCode(eventId, securityCode);
		}
	}

	/**
	 * 反向注册验证码
	 */
	public void reverseSecurityCode() {
		if (netSession != null) {
			netSession.reverseSecurityCode();
		}
	}

	public void bindPhoneRemote(int eventId, String phone, String pwd, String vcode) {
		if (netSession != null) {
			netSession.bindPhone(this, eventId, phone, pwd, vcode);
		}
	}

	public void sendOpcode(byte tipsType, byte messageCate, int opcode, String message, int connectId) {

	}

	public String getFeatureString() {
		return null;
	}

	public short getLevel() {
		return 0;
	}

	public boolean isLogLogin() {
		return false;
	}

	public void setLogLogin(boolean value) {

	}

	public void kickedout(String msg) {
		// TODO Auto-generated method stub

	}

	private int connectId = -1;

	public void setConnectId(int connectId) {
		this.connectId = connectId;

	}

	public int getConnectId() {
		int connectId = this.connectId;
		this.connectId = -1;
		return connectId;
	}

	public void saveOnCloseNet() {

	}

	/**
	 * 是否已经连接网络
	 * 
	 * @return true已经建立连接
	 */
	public boolean isConnected() {
		return connected;
	}

	public boolean isNetConnected() {
		return netSession != null && netSession.isConnected();
	}

	public Timestamp getTimeCreateRole() {
		return this.timeCreateRole;
	}

	public void setTimeCreateRole(Timestamp firsttime) {
		this.timeCreateRole = firsttime;
	}

	public Timestamp getTimeLastLogin() {
		return timeLastLogin;
	}

	public void setTimeLastLogin(Timestamp timeLastLogin) {
		this.timeLastLogin = timeLastLogin;
	}

	public Timestamp getTimeLastOffline() {
		if (timeLastOffline == null) {
			this.timeLastOffline = new Timestamp(System.currentTimeMillis());
		}
		return timeLastOffline;
	}

	public void setTimeLastOffline(Timestamp lastOffline) {
		this.timeLastOffline = lastOffline;
		if (lastOffline == null) {
			this.timeLastOffline = new Timestamp(System.currentTimeMillis());
		}
	}

	public boolean isReconect() {
		return isReconect;
	}

	public void setReconect(boolean isReconect) {
		this.isReconect = isReconect;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public boolean isHightVersion() {
		return hightVersion;
	}

	public void setHightVersion(boolean hightVersion) {
		this.hightVersion = hightVersion;
	}

	public int getBelongServer() {
		return belongServer;
	}

	public void setBelongServer(int belongServer) {
		this.belongServer = belongServer;
	}
}
