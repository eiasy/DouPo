package mmo.common.module.account.doupo.cache.account.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChannelAccount {
	private final static String            ACCOUNT_NOT_EXIST = "账号不存在#2";
	private String                            channel;
	/** <子渠道，子渠道账号集> */
	private Map<String, ChannelSubAccount> subAccounts       = new HashMap<String, ChannelSubAccount>();

	public ChannelAccount(String channel) {
		super();
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}

	public ChannelSubAccount getChannelSubAccount(String channelSub) {
		ChannelSubAccount subAccount = subAccounts.get(channelSub);
		if (subAccount == null) {
			subAccount = new ChannelSubAccount(channelSub);
		}
		return subAccount;
	}

	public void initAccount(UserAccount account) {
		ChannelSubAccount sa = subAccounts.get(account.getChannelSub());
		if (sa == null) {
			sa = new ChannelSubAccount(account.getChannelSub());
			subAccounts.put(sa.getChannelSub(), sa);
		}
		sa.initAccount(account);
	}

	public void print() {
		Set<String> keys = subAccounts.keySet();
		for (String c : keys) {
			System.out.println("    sub=" + c);
			subAccounts.get(c).print();
		}
	}

	public Object validateAccount(String channelSub, String userid, String password) {
		ChannelSubAccount sa = subAccounts.get(channelSub);
		if (sa == null) {
			return ACCOUNT_NOT_EXIST;
		}
		return sa.validateAccount(userid, password);
	}
	
	public UserAccount validateChannelAccount(String channelSub, String userid) {
		ChannelSubAccount sa = subAccounts.get(channelSub);
		if (sa == null) {
			return null;
		}
		return sa.validateChannelAccount(userid);
	}
}
