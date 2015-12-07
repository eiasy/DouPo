package mmo.common.config.update;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ChannelClient {
	private String accountType;
	private String feeCallback;
	private int    belongto;
	private String channelSub;

	public int getBelongto() {
		return belongto;
	}

	public void setBelongto(int belongto) {
		this.belongto = belongto;
	}

	private Map<Integer, PhoneType> phoneTypes = new HashMap<Integer, PhoneType>();

	public PhoneType getPhoneType(int phoneType) {
		return phoneTypes.get(phoneType);
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void addPhoneType(int type, PhoneType phoneType) {
		phoneTypes.put(type, phoneType);
	}

	public String getFeeCallback() {
		return feeCallback;
	}

	public void setFeeCallback(String feeCallback) {
		if (feeCallback == null) {
			feeCallback = "";
		} else {
			this.feeCallback = feeCallback.trim();
		}
	}

	public String getChannelSub() {
		if (channelSub == null) {
			channelSub = belongto + "";
		}
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	@Override
	public String toString() {
		return "ChannelClient [phoneTypes=" + phoneTypes + "]";
	}

	public void release() {
		accountType = null;
		feeCallback = null;
		if (phoneTypes != null) {
			Collection<PhoneType> values = phoneTypes.values();
			for (PhoneType pt : values) {
				pt.release();
			}
			phoneTypes.clear();
		}
		phoneTypes = null;
	}
}
