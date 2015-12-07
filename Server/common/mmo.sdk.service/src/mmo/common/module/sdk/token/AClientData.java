package mmo.common.module.sdk.token;

abstract public class AClientData {
	public final static int    RT_1_OK             = 1;
	public final static int    RT_2_OVERDATE       = 2;
	public final static int    RT_3_CHANNEL_NO     = 3;
	public final static int    RT_4_VALIDATE_FAIL  = 4;

	public final static String MSG_1_OK            = "ok";
	public final static String MSG_2_OVERDATE      = "验证码过期";
	public final static String MSG_3_CHANNEL_NO    = "未知的渠道号";
	public final static String MSG_4_VALIDATE_FAIL = "登录失败";

	protected String           responseQueue;
	protected String           sessionId;
	protected int              channelId;
	protected int              belongto;
	protected String           channelSub;
	protected int              clientVersion;
	protected int              productId;
	protected String           deviceOS;
	protected String           osVersion;
	protected String           deviceUdid;
	protected String           deviceMac;
	protected String           deviceUA;
	protected String           phone;
	protected String           deviceSerial;
	protected String           phoneType;
	protected int              screenHeight;
	protected int              screenWidth;
	protected String           codeVersion;
	protected String           permit;
	protected String           ip;
	protected byte             registerFrom;
	protected String           userid;
	protected String           username;
	protected String           token;
	protected int              connectId;
	protected String           customData;
	protected String           featureString;
	protected String           loginServer;
	protected String           serverCode;

	protected String           securityCode;

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public AClientData() {

	}

	public AClientData(String responseQueue, String sessionId, int channelId, int belongto, String channelSub, int clientVersion, int productId,
	        String deviceOS, String osVersion, String deviceUdid, String deviceMac, String deviceUA, String phone, String deviceSerial,
	        String phoneType, int screenHeight, int screenWidth, String codeVersion, String permit, String ip, byte registerFrom, String data01,
	        String data02, int connectId, String customData, String featureString, String loginServer, String serverCode) {
		super();
		this.responseQueue = responseQueue;
		this.sessionId = sessionId;
		this.channelId = channelId;
		this.belongto = belongto;
		this.channelSub = channelSub;
		this.clientVersion = clientVersion;
		this.productId = productId;
		this.deviceOS = deviceOS;
		this.osVersion = osVersion;
		this.deviceUdid = deviceUdid;
		this.deviceMac = deviceMac;
		this.deviceUA = deviceUA;
		this.phone = phone;
		this.deviceSerial = deviceSerial;
		this.phoneType = phoneType;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.codeVersion = codeVersion;
		this.permit = permit;
		this.ip = ip;
		this.registerFrom = registerFrom;
		this.userid = data01;
		this.token = data02;
		this.connectId = connectId;
		this.customData = customData;
		this.featureString = featureString;
		this.loginServer = loginServer;
		this.serverCode = serverCode;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCustomData() {
		return customData;
	}

	public String getIp() {
		return ip;
	}

	public String getToken() {
		return token;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getChannelId() {
		return channelId;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	abstract public void validateToken();

	abstract public void validateFail(int code, String msg);

	abstract public void userChannelLogin();
}
