package mmo.common.bean.role;

import mmo.extension.application.ApplicationConfig;
import mmo.tools.util.MD5;

public class RoleStoreReceipt {
	public static int TIME_WAIT = 1000 * 60 * 2;
	/** 新票据，未在AppStore上验证过或验证失败 */
	public final static byte STAUS_0_NEW = 0;
	/** 已经在AppStore上验证过但未收到充值服务器的确认 */
	public final static byte STAUS_1_UNCHARGE = 1;
	/** 已经收到充值服务器的确认 */
	public final static byte STAUS_2_CHARGED = 2;

	private int id;
	private String receipt;
	private int status;
	private int gameId;
	private int serverId;
	private String channelId;
	private String channelSub;
	private int accountId;
	private int roleId;
	private short level;
	private String roleName;
	private int goodsId;
	private String goodsName = "";
	private int price;
	private int count;
	private String deviceOS;
	private String userId;
	private long timeCreate;
	private String proid = "";
	private String orderform = "";
	private String deviceImei = "";
	private String idfa;
	private String deviceSerial;
	private String deviceMac;
	private long nextTime;
	private String checkResult;
	private String receiptMd5;
	private String chargeType;

	public RoleStoreReceipt() {
	}

	public RoleStoreReceipt(String receipt, int gameId, int serverId, String channelId, String channelSub, int accountId, int roleId, short level, String roleName, String deviceOS, String userId, long timeCreate, String deviceImei, String deviceSerial, String deviceMac, String idfa) {
		super();
		this.receipt = receipt;
		this.gameId = gameId;
		this.serverId = serverId;
		this.channelId = channelId;
		this.channelSub = channelSub;
		this.accountId = accountId;
		this.roleId = roleId;
		this.level = level;
		this.roleName = roleName;
		this.deviceOS = deviceOS;
		this.userId = userId;
		this.timeCreate = timeCreate;
		this.deviceImei = deviceImei;
		this.deviceSerial = deviceSerial;
		this.deviceMac = deviceMac;
		this.idfa = idfa;
	}

	public String getCheckResult() {
		if (checkResult == null) {
			return "";
		}
		return checkResult;
	}

	public void setCheckResult(String validateResult) {
		this.checkResult = validateResult;
	}

	public String getReceiptMd5() {
		return receiptMd5;
	}

	public void setReceiptMd5(String receiptMd5) {
		this.receiptMd5 = receiptMd5;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getRoleId() {
		return roleId;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public long getNextTime() {
		return nextTime;
	}

	public void setNextTime(long nextTime) {
		this.nextTime = nextTime;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public String getDeviceImei() {
		return deviceImei;
	}

	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDeviceOS() {
		return deviceOS;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(long timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getProid() {
		if (proid == null) {
			return "";
		}
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getOrderform() {
		return orderform;
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

	public void setOrderform(String orderform) {
		this.orderform = orderform;
	}

	public String toOrderform() {
		return MD5.getHashString(receipt) + "_" + gameId + "_" + serverId + ApplicationConfig.getInstance().getAppId();
	}

	public String getSign() {
		StringBuilder sbOrder = new StringBuilder();
		sbOrder.append(gameId);
		sbOrder.append("-").append(serverId);
		sbOrder.append("+").append(channelId);
		sbOrder.append("-").append(channelSub);
		sbOrder.append("+").append(accountId);
		sbOrder.append("-").append(roleId);
		sbOrder.append("+").append(level);
		sbOrder.append("-").append(roleName);
		sbOrder.append("+").append(goodsId);
		sbOrder.append("-").append(goodsName);
		sbOrder.append("+").append(price);
		sbOrder.append("-").append(count);
		sbOrder.append("+").append(deviceOS);
		sbOrder.append("-").append(userId);
		sbOrder.append("+").append(timeCreate);
		sbOrder.append("-").append(deviceImei);
		return MD5.getHashString(sbOrder.toString());
	}

	public boolean isValidate() {
		if (System.currentTimeMillis() > nextTime) {
			nextTime = System.currentTimeMillis() + TIME_WAIT;
			return true;
		}
		return false;
	}
}
