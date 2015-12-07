package mmo.common.module.account.doupo.cache.account.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mmo.tools.util.MD5;

public class ChannelSubAccount {
	private final static String      ACCOUNT_NOT_EXIST = "账号不存在#3";
	private final static String      PWD_ERROR         = "账号密码错误#4";
	private String                   channelSub;
	private Map<String, UserAccount> accounts          = new HashMap<String, UserAccount>();

	public ChannelSubAccount(String channelSub) {
		super();
		this.channelSub = channelSub;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public UserAccount getUserAccount(String userid) {
		return accounts.get(userid);
	}

	public void initAccount(UserAccount account) {
		accounts.put(account.getUserid(), account);
	}

	public void print() {
		Collection<UserAccount> values = accounts.values();
		for (UserAccount ua : values) {
			System.out.println("        " + ua);
		}
	}

	public Object validateAccount(String userid, String password) {
		UserAccount ua = accounts.get(userid);
		if (ua == null) {
			return ACCOUNT_NOT_EXIST;
		}
		if (MD5.getHashString(password).equals(ua.getPassword())) {
			return ua;
		}
		return PWD_ERROR;
	}
	
	public UserAccount validateChannelAccount(String userid) {
		return accounts.get(userid);
	}
}
