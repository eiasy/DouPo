package mmo.common.bean.bi;

import java.util.Date;

import mmo.tools.util.DateUtil;

public class EventDefault {
	public final static String SOURCE_ACCOUNT = "account";
	public final static String SOURCE_CHARGE = "charge";
	private long id;
	private String eventSource;
	private String eventTag;
	private String appTag;
	private String platform;
	private String serverTag;
	private String channelTag;
	private String channelSub;
	private int accountId;
	private String userId;
	private int roleId;
	private String roleName;
	private int roleLevel;
	private int vipLevel;
	private String value1string;
	private String value2string;
	private String value3string;
	private String value4string;
	private String value5string;
	private String value6string;
	private String value7string;
	private String value8string;
	private String key1int;
	private int value1int;
	private String key2int;
	private int value2int;
	private String key3int;
	private int value3int;
	private String key1long;
	private long value1long;
	private String key1double;
	private double value1double;
	private long timeAdd;

	public long getTimeAdd() {
		return timeAdd;
	}

	public void setTimeAdd(long timeAdd) {
		this.timeAdd = timeAdd;
	}

	public EventDefault() {
	}

	public long getId() {
		return id;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public String getEventTag() {
		return eventTag;
	}

	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
	}

	public String getAppTag() {
		return appTag;
	}

	public void setAppTag(String appTag) {
		this.appTag = appTag;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getServerTag() {
		return serverTag;
	}

	public void setServerTag(String serverTag) {
		this.serverTag = serverTag;
	}

	public String getChannelTag() {
		return channelTag;
	}

	public void setChannelTag(String channelTag) {
		this.channelTag = channelTag;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public String getValue4string() {
		return value4string;
	}

	public void setValue4string(String value4string) {
		this.value4string = value4string;
	}

	public String getValue1string() {
		return value1string;
	}

	public void setValue1string(String value1string) {
		this.value1string = value1string;
	}

	public String getValue5string() {
		return value5string;
	}

	public void setValue5string(String value5string) {
		this.value5string = value5string;
	}

	public String getValue2string() {
		return value2string;
	}

	public void setValue2string(String value2string) {
		this.value2string = value2string;
	}

	public String getValue6string() {
		return value6string;
	}

	public void setValue6string(String value6string) {
		this.value6string = value6string;
	}

	public String getValue3string() {
		return value3string;
	}

	public void setValue3string(String value3string) {
		this.value3string = value3string;
	}

	public String getKey1int() {
		return key1int;
	}

	public void setKey1int(String key1int) {
		this.key1int = key1int;
	}

	public int getValue1int() {
		return value1int;
	}

	public void setValue1int(int value1int) {
		this.value1int = value1int;
	}

	public String getKey2int() {
		return key2int;
	}

	public void setKey2int(String key2int) {
		this.key2int = key2int;
	}

	public int getValue2int() {
		return value2int;
	}

	public void setValue2int(int value2int) {
		this.value2int = value2int;
	}

	public String getKey3int() {
		return key3int;
	}

	public void setKey3int(String key3int) {
		this.key3int = key3int;
	}

	public int getValue3int() {
		return value3int;
	}

	public void setValue3int(int value3int) {
		this.value3int = value3int;
	}

	public String getKey1long() {
		return key1long;
	}

	public void setKey1long(String key1long) {
		this.key1long = key1long;
	}

	public long getValue1long() {
		return value1long;
	}

	public void setValue1long(long value1long) {
		this.value1long = value1long;
	}

	public String getKey1double() {
		return key1double;
	}

	public void setKey1double(String key1double) {
		this.key1double = key1double;
	}

	public double getValue1double() {
		return value1double;
	}

	public void setValue1double(double value1double) {
		this.value1double = value1double;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getValue7string() {
		return value7string;
	}

	public void setValue7string(String value7string) {
		this.value7string = value7string;
	}

	public String getValue8string() {
		return value8string;
	}

	public void setValue8string(String value8string) {
		this.value8string = value8string;
	}

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}

	@Override
	public String toString() {
		return "EventDefault [id=" + id + ", eventSource=" + eventSource + ", eventTag=" + eventTag + ", appTag=" + appTag + ", platform=" + platform + ", serverTag=" + serverTag + ", channelTag=" + channelTag + ", channelSub=" + channelSub + ", accountId=" + accountId + ", userId=" + userId + ", roleId=" + roleId + ", roleName=" + roleName + ", roleLevel=" + roleLevel + ", vipLevel=" + vipLevel + ", value1string=" + value1string + ", value2string=" + value2string + ", value3string=" + value3string + ", value4string=" + value4string + ", value5string=" + value5string + ", value6string=" + value6string + ", value7string=" + value7string + ", value8string=" + value8string + ", key1int=" + key1int + ", value1int=" + value1int + ", key2int=" + key2int + ", value2int=" + value2int + ", key3int=" + key3int + ", value3int=" + value3int + ", key1long=" + key1long + ", value1long=" + value1long + ", key1double=" + key1double + ", value1double=" + value1double + "]";
	}

	public String getData() {
		StringBuilder sb = new StringBuilder();
		sb.append("('").append(eventSource).append("', '").append(eventTag).append("', '").append(appTag).append("', '").append(platform).append("', '").append(serverTag).append("', '").append(channelTag).append("', '").append(channelSub).append("', '").append(accountId).append("', '").append(userId).append("', '").append(roleId).append("', '").append(roleName).append("', '").append(roleLevel).append("', '").append(vipLevel).append("', '").append(value1string).append("', '").append(value2string).append("', '").append(value3string).append("', '").append(value4string).append("', '").append(value5string).append("', '").append(value6string).append("', '").append(value7string).append("', '").append(value8string).append("', '").append(key1int).append("', '").append(value1int).append("', '").append(key2int).append("', '").append(value2int).append("', '").append(key3int).append("', '").append(value3int).append("', '").append(key1long).append("', '").append(value1long).append("', '").append(key1double).append("', '")
				.append(value1double).append("','").append(DateUtil.formatDate(new Date())).append("')");
		return sb.toString();
	}
}
