package com.huayi.doupo.base.model.socket;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.huayi.doupo.base.model.memcalc.MemCalcObj;

/**
 * 用于绑定的socket中的Player
 * @author mp
 * @date 2013-10-11 下午1:57:33
 */
public class Player implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6974830267746751593L;
	
	/**
	 * 状态： 0-离线, 1-在线  2-冻结 3-禁言
	 */
	private int onlineState;
	
	/**
	 * 渠道 qq/wx 
	 */
	private String channel;
	
	/**
	 * 腾讯SDK-openId
	 */
	private String openId;
	
	/**
	 * 腾讯SDK-appid
	 */
	private String appid;
	
	/**
	 * 腾讯SDK-appKey
	 */
	private String appKey;
	
	/**
	 * 腾讯SDK-openkey
	 */
	private String openkey;
	
	/**
	 * 腾讯SDK-userip
	 */
	private String userip;
	
	/**
	 * 腾讯SDK-pay_token
	 */
	private String pay_token;
	
	/**
	 * 腾讯SDK-pf
	 */
	private String pf;
	
	/**
	 * 腾讯SDK-pfkey
	 */
	private String pfkey;
	
	/**
	 * 腾讯SDK-zoneid
	 */
	private String zoneid;
	
	/**
	 * 腾讯SDK-session_id
	 */
	private String session_id;
	
	/**
	 * 腾讯SDK-session_type
	 */
	private String session_type;
	
	/**
	 * 玩家Id
	 */
	private int playerId; 
	
	/**
	 * 玩家昵称
	 */
	private String playerName;
	
	/**
	 * Socket通道Id
	 */
	private String channelId;
	
	/**
	 * 用于计算战斗属性的内存基础类
	 */
	private MemCalcObj memCalcObj;
	
	/**
	 * 用于战斗时与客户端之间的授权码验证
	 */
	private String authCode;
	
	/**
	 * 用于玩家上次请求时间
	 */
	private String operTime;
	
	/**
	 * 协议号
	 */
	private int protoHeader;
	
	/**
	 * 玩家相应协议时间-毫秒数
	 */
	private long operTimeMill;
	
	/**
	 * 重复协议计数器
	 */
	private int repProtoCount;
	
	/**
	 * 玩家等级
	 */
	private int level;
	
	/**
	 * 登录验证时,登录服返回的账户Id
	 */
	private String accountId;
	
	/**
	 * 发起世界聊天的时间-毫秒
	 */
	private long worldChatTime;
	
	/**
	 * 发起联盟聊天的时间-毫秒
	 */
	private long unionChatTime;
	
	/**
	 * 聊天窗口状态 0-关闭 1-打开
	 */
	private int chatWindowState;
	
	/**
	 * 客户端版本号
	 */
	private String clientVersion;
	
	/**
	 * 设备OS[ios/android]
	 */
	private String devOs;
	
	/**
	 * 设备IMEI
	 */
	private String imei;
	
	/**
	 * 设备IDFA
	 */
	private String idfa;
	
	/**
	 * 登录服标识
	 */
	private String loginServer;
	
	/**
	 * IP
	 */
	private String ip;
	
	/**
	 * Vip等级
	 */
	private int vipLevel;
	
	
	/**
	 * 独服Id
	 */
	private String aloneServerId;
	
	/**
	 * 子渠道表示
	 */
	private String channelSub;
	
	

	public long getUnionChatTime() {
		return unionChatTime;
	}

	public void setUnionChatTime(long unionChatTime) {
		this.unionChatTime = unionChatTime;
	}

	public int getProtoHeader() {
		return protoHeader;
	}

	public void setProtoHeader(int protoHeader) {
		this.protoHeader = protoHeader;
	}

	public long getOperTimeMill() {
		return operTimeMill;
	}

	public void setOperTimeMill(long operTimeMill) {
		this.operTimeMill = operTimeMill;
	}

	public int getRepProtoCount() {
		return repProtoCount;
	}

	public void setRepProtoCount(int repProtoCount) {
		this.repProtoCount = repProtoCount;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public String getAloneServerId() {
		return aloneServerId;
	}

	public void setAloneServerId(String aloneServerId) {
		this.aloneServerId = aloneServerId;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginServer() {
		return loginServer;
	}

	public void setLoginServer(String loginServer) {
		this.loginServer = loginServer;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getDevOs() {
		return devOs;
	}

	public void setDevOs(String devOs) {
		this.devOs = devOs;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public int getChatWindowState() {
		return chatWindowState;
	}

	public void setChatWindowState(int chatWindowState) {
		this.chatWindowState = chatWindowState;
	}

	public long getWorldChatTime() {
		return worldChatTime;
	}

	public void setWorldChatTime(long worldChatTime) {
		this.worldChatTime = worldChatTime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(int onlineState) {
		this.onlineState = onlineState;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public MemCalcObj getMemCalcObj() {
		return memCalcObj;
	}

	public void setMemCalcObj(MemCalcObj memCalcObj) {
		this.memCalcObj = memCalcObj;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getOpenkey() {
		return openkey;
	}

	public void setOpenkey(String openkey) {
		this.openkey = openkey;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public String getPay_token() {
		return pay_token;
	}

	public void setPay_token(String pay_token) {
		this.pay_token = pay_token;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getPfkey() {
		return pfkey;
	}

	public void setPfkey(String pfkey) {
		this.pfkey = pfkey;
	}

	public String getZoneid() {
		return zoneid;
	}

	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_type() {
		return session_type;
	}

	public void setSession_type(String session_type) {
		this.session_type = session_type;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	
	public String getLogHeader () {
		return ("" + "|" + loginServer + "|" + zoneid + "|" + "1" + "|" + clientVersion + "|" + ip + "|" + openId + "|" + channel + "|" + devOs + "|" + imei + "|" + idfa + "|" + accountId + "|" + playerId + "|" + level + "|" + vipLevel + "|" + aloneServerId + "|" + channelSub).replace("null", "");
	}
	
	/**
	 * 获取向天喜客服工具推送的公共的参数
	 * @author mp
	 * @date 2015-8-24 下午9:16:46
	 * @return
	 * @Description
	 */
	public Map<String, String> getCommonPushMap () {
		Map<String, String> map = new HashMap<String, String>();
		map.put("eventSource", "doupo");
		map.put("platform", aloneServerId);
		map.put("serverTag", zoneid);
		map.put("channelTag", channel);
		map.put("channelSub", channelSub);
		map.put("accountId", accountId);
		map.put("userId", openId);
		map.put("roleId", playerId + "");
		map.put("roleName", playerName);
		map.put("roleLevel", level + "");
		map.put("vipLevel", vipLevel + "");
		return map;
	}

}
