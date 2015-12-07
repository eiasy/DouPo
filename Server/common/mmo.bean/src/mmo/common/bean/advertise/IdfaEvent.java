package mmo.common.bean.advertise;

import java.util.TreeMap;

public class IdfaEvent {
	public final static String      TYPE_GAME_EVENT   = "GAME_EVENT";
	public final static String      TYPE_ADVERTISE    = "ADVERTISE";
	public final static String      EVENT_APP_START   = "APP_START";
	public final static String      EVENT_CLICK       = "CLICK";
	public final static String      EVENT_CREATE_ROLE = "CREATE_ROLE";
	public final static String      CHANNEL_NATURE    = "nature";
	private long                    id;
	private String                  eventType;
	private String                  eventTag;
	private String                  appId;
	private String                  channelTag;
	private String                  idfa;
	private String                  deviceMac;
	private String                  deviceImei;
	private String                  ip;
	private long                    timeAdd;
	private String                  deviceUdid;
	private String                  deviceSerial;
	private String                  deviceUA;
	private String                  deviceOS;
	private String                  deviceOsVersion;
	private String                  phoneCode;
	private String                  desc;
	private String                  channelSub;
	private String                  media;
	private String                  callback;
	private TreeMap<String, String> customs           = new TreeMap<String, String>();

	public IdfaEvent(String eventType, String eventTag, String appId, String channelTag, String idfa, String deviceMac, String deviceImei, String ip,
	        long timeAdd, String deviceUdid, String deviceSerial, String deviceUA, String deviceOS, String deviceOsVersion, String phoneCode,
	        String desc) {
		super();
		this.eventType = eventType;
		this.eventTag = eventTag;
		this.appId = appId;
		this.channelTag = channelTag;
		this.idfa = idfa;
		this.deviceMac = deviceMac;
		this.deviceImei = deviceImei;
		this.ip = ip;
		this.timeAdd = timeAdd;
		this.deviceUdid = deviceUdid;
		this.deviceSerial = deviceSerial;
		this.deviceUA = deviceUA;
		this.deviceOS = deviceOS;
		this.deviceOsVersion = deviceOsVersion;
		this.phoneCode = phoneCode;
		this.desc = desc;
	}

	public IdfaEvent() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTag() {
		return eventTag;
	}

	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getDeviceImei() {
		return deviceImei;
	}

	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getTimeAdd() {
		return timeAdd;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelsub) {
		this.channelSub = channelsub;
	}

	public String getMedia() {
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

	public void setTimeAdd(long timeAdd) {
		this.timeAdd = timeAdd;
	}

	public void setCustoms(TreeMap<String, String> customs) {
		this.customs = customs;
	}

	public String getChannelTag() {
		return channelTag;
	}

	public void setChannelTag(String channelTag) {
		this.channelTag = channelTag;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceUdid() {
		return deviceUdid;
	}

	public void setDeviceUdid(String deviceUdid) {
		this.deviceUdid = deviceUdid;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getDeviceUA() {
		return deviceUA;
	}

	public void setDeviceUA(String deviceUA) {
		this.deviceUA = deviceUA;
	}

	public String getDeviceOS() {
		return deviceOS;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getDeviceOsVersion() {
		return deviceOsVersion;
	}

	public void setDeviceOsVersion(String deviceOsVersion) {
		this.deviceOsVersion = deviceOsVersion;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void addCustomValue(String key, String value) {
		if (key != null && key.length() > 0 && value != null) {
			this.customs.put(key, value);
		}
	}

	public String getValue(String key) {
		return customs.get(key);
	}

	public TreeMap<String, String> getCustoms() {
		return customs;
	}

}
