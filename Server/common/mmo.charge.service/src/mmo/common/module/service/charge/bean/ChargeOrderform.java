package mmo.common.module.service.charge.bean;

import mmo.extension.application.ApplicationConfig;
import mmo.tools.log.LoggerError;
import mmo.tools.util.StringUtil;

public class ChargeOrderform {
	public final static byte STATUS_0_UNHNADLE = 0;
	public final static byte STATUS_1_HANDLED = 1;
	private long id;
	private String orderform;
	private int gameId;
	private int serverId;
	private String channelId;
	private String channelSub;
	private int accountId;
	private String userId;
	private int roleId;
	private short roleLevel;
	private String roleName;
	private int itemId;
	private String itemName;
	private int itemPrice;
	private int itemCount;
	private String deviceOS;
	private String deviceImei = "";

	private byte status;
	private long timeCreate;
	private long overtime;
	private String deviceSerial;
	private String deviceMac;
	private String idfa;
	private int zoneId;
	private int appId;

	public ChargeOrderform() {

	}

	public ChargeOrderform(int gameid, int serverid, String channelid, String channelsub, int accountid, String userId, int roleid, short rolelevel, String rolename, int itemid, String itemname, int itemprice, int itemcount, String deviceos, String deviceImei) {
		super();
		this.gameId = gameid;
		this.serverId = serverid;
		this.channelId = channelid;
		this.channelSub = channelsub;
		this.accountId = accountid;
		this.userId = userId;
		this.roleId = roleid;
		this.roleLevel = rolelevel;
		this.roleName = rolename;
		this.itemId = itemid;
		this.itemName = itemname;
		this.itemPrice = itemprice;
		this.itemCount = itemcount;
		this.deviceOS = deviceos;
		this.deviceImei = deviceImei;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getDeviceSerial() {
		if (deviceSerial == null) {
			return "";
		}
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getDeviceMac() {
		if (deviceMac == null) {
			return "";
		}
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getIdfa() {
		if (idfa == null) {
			return "";
		}
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getOrderform() {
		return orderform;
	}

	public void setOrderform(String orderform) {
		this.zoneId = getZoneId(orderform);
		this.orderform = orderform;
	}

	public boolean isHadled() {
		return this.status == STATUS_1_HANDLED;
	}

	public long getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(long timeCreate) {
		this.timeCreate = timeCreate;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public long getOvertime() {
		return overtime;
	}

	public void setOvertime(long overtime) {
		this.overtime = overtime;
	}

	public boolean isOvertime() {
		return System.currentTimeMillis() > overtime;
	}

	public int getGameId() {
		return gameId;
	}

	public int getServerId() {
		return serverId;
	}

	public String getChannelId() {
		return channelId;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public int getAccountId() {
		return accountId;
	}

	public int getRoleId() {
		return roleId;
	}

	public short getRoleLevel() {
		return roleLevel;
	}

	public String getRoleName() {
		return roleName;
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public int getItemCount() {
		return itemCount;
	}

	public String getDeviceOS() {
		return deviceOS;
	}

	public byte getStatus() {
		return status;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setRoleLevel(short roleLevel) {
		this.roleLevel = roleLevel;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getDeviceImei() {
		return deviceImei;
	}

	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getZoneId() {
		return zoneId;
	}

	public static int getZoneId(String orderId) {
		String[] array = StringUtil.splitString(orderId, '_');
		if (array.length == 4) {
			try {
				return Integer.parseInt(array[3]);
			} catch (Exception e) {
				LoggerError.error("获取订单归属区编号异常#" + orderId, e);
			}
		} else if (array.length == 3) {
			return 0;
		}
		return ApplicationConfig.getInstance().getAppId();
	}

	public static String getNoticeKey(String orderId) {
		String[] array = StringUtil.splitString(orderId, '_');
		if (array.length > 2) {
			return array[1] + "_" + array[2];
		}
		return "";
	}
}
