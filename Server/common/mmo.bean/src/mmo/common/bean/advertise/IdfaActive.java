package mmo.common.bean.advertise;

import mmo.common.http.parameter.HttpParameter;

public class IdfaActive {
	private long   id;
	private String appId;
	private String channelTag;
	private String idfa;
	private String deviceMac;
	private String deviceImei;
	private String ip;
	private long   addTime;
	private int    appStart;
	private long   appStartTime;
	private int    roleCreate;
	private long   roleCreateTime;
	private String roleCreateChannel;
	private String roleUserId;
	private String desc;
	private byte   status;
	private String deviceUdid;
	private String deviceSerial;
	private String deviceUA;
	private String deviceOS;
	private String deviceOsVersion;
	private String channelSub;
	private String media;
	private String callback;

	public IdfaActive(IdfaEvent event) {
		this.appId = event.getAppId();
		if (IdfaEvent.TYPE_GAME_EVENT.equalsIgnoreCase(event.getEventType())) {
			this.channelTag = "nature";
			if (IdfaEvent.EVENT_APP_START.equalsIgnoreCase(event.getEventTag())) {
				this.appStart = 1;
				this.appStartTime = System.currentTimeMillis();
			} else if (IdfaEvent.EVENT_CREATE_ROLE.equalsIgnoreCase(event.getEventTag())) {
				this.roleCreate = 1;
				this.roleCreateChannel = event.getChannelTag();
				this.roleCreateTime = System.currentTimeMillis();
				this.roleUserId = event.getValue(HttpParameter.GameEvent.user_id);
			}
		} else {
			this.channelTag = event.getChannelTag();
		}
		this.idfa = event.getIdfa();
		this.deviceMac = event.getDeviceMac();
		this.deviceImei = event.getDeviceImei();
		this.ip = event.getIp();
		this.addTime = System.currentTimeMillis();
		this.desc = event.getDesc();
		this.deviceUdid = event.getDeviceUdid();
		this.deviceSerial = event.getDeviceSerial();
		this.deviceUA = event.getDeviceUA();
		this.deviceOS = event.getDeviceOS();
		this.deviceOsVersion = event.getDeviceOsVersion();
		this.channelSub = event.getChannelSub();
		this.media = event.getMedia();
		this.callback = event.getCallback();
	}

	public IdfaActive() {
	}

	public long getId() {
		return id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChannelSub() {
		if (channelSub == null) {
			return "";
		}
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public String getMedia() {
		if (media == null) {
			return "";
		}
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getChannelTag() {
		if (channelTag == null) {
			return "";
		}
		return channelTag;
	}

	public void setChannelTag(String channelTag) {
		this.channelTag = channelTag;
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

	public String getDeviceMac() {
		if (deviceMac == null) {
			return "";
		}
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceImei() {
		if (deviceImei == null) {
			return "";
		}
		return deviceImei;
	}

	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}

	public String getIp() {
		if (ip == null) {
			return "";
		}
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	public int getAppStart() {
		return appStart;
	}

	public void setAppStart(int appStart) {
		this.appStart = appStart;
	}

	public long getAppStartTime() {
		return appStartTime;
	}

	public void setAppStartTime(long appStartTime) {
		this.appStartTime = appStartTime;
	}

	public int getRoleCreate() {
		return roleCreate;
	}

	public void setRoleCreate(int roleCreate) {
		this.roleCreate = roleCreate;
	}

	public long getRoleCreateTime() {
		return roleCreateTime;
	}

	public void setRoleCreateTime(long roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}

	public String getRoleCreateChannel() {
		if (roleCreateChannel == null) {
			return "";
		}
		return roleCreateChannel;
	}

	public void setRoleCreateChannel(String roleCreateChannel) {
		this.roleCreateChannel = roleCreateChannel;
	}

	public String getRoleUserId() {
		if (roleUserId == null) {
			return "";
		}
		return roleUserId;
	}

	public void setRoleUserId(String roleUserId) {
		this.roleUserId = roleUserId;
	}

	public String getDesc() {
		if (desc == null) {
			return "";
		}
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getDeviceUdid() {
		if (deviceUdid == null) {
			return "";
		}
		return deviceUdid;
	}

	public void setDeviceUdid(String deviceUdid) {
		this.deviceUdid = deviceUdid;
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

	public String getDeviceUA() {
		if (deviceUA == null) {
			return "";
		}
		return deviceUA;
	}

	public void setDeviceUA(String deviceUA) {
		this.deviceUA = deviceUA;
	}

	public String getDeviceOS() {
		if (deviceOS == null) {
			return "";
		}
		return deviceOS;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getDeviceOsVersion() {
		if (deviceOsVersion == null) {
			return "";
		}
		return deviceOsVersion;
	}

	public void setDeviceOsVersion(String deviceOsVersion) {
		this.deviceOsVersion = deviceOsVersion;
	}

}
