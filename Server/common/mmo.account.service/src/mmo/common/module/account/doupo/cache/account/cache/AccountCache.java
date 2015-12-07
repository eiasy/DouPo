package mmo.common.module.account.doupo.cache.account.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.common.module.account.doupo.cache.account.ActiveCodeManager;
import mmo.common.module.account.doupo.cache.account.bean.ChannelAccount;
import mmo.common.module.account.doupo.cache.account.bean.DeviceFreeze;
import mmo.common.module.account.doupo.cache.account.bean.ServerLastEnter;
import mmo.common.module.account.doupo.cache.account.bean.ServerRoleCount;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.bean.WhiteList;
import mmo.tools.util.MathUtil;
import mmo.tools.util.StringUtil;

public class AccountCache {

	private final static String       ACCOUNT_NOT_EXIST = "账号不存在#1";
	private final static AccountCache instance          = new AccountCache();

	public final static AccountCache getInstance() {
		return instance;
	}

	/** <账号ID，渠道账号合集> */
	private Map<String, ChannelAccount>   channelAccounts    = new HashMap<String, ChannelAccount>();
	/** <用户账号ID，服务器角色数量合集> */
	private Map<Integer, ServerRoleCount> serverRoleCount    = new HashMap<Integer, ServerRoleCount>();
	/** <用户账号ID,最后一次进入游戏服务器编号> */
	private Map<Integer, ServerLastEnter> serverLastEnter    = new HashMap<Integer, ServerLastEnter>();
	/** 所有的用户账号<账号ID，账号信息> */
	private Map<Integer, UserAccount>     allAccounts        = new HashMap<Integer, UserAccount>();
	/** 绑定手机的账号<手机号，账号ID> */
	private Map<String, Integer>          phoneBind          = new HashMap<String, Integer>();
	/** 被冻结的设备<设备IMEI,冻结信息> */
	private Map<String, DeviceFreeze>     devicesFreeze      = new HashMap<String, DeviceFreeze>();
	/** 账号生成器 */
	private AtomicInteger                 accountIdGenerator = new AtomicInteger(1000000);
	/** 白名单 */
	private WhiteList                     whiteList          = new WhiteList();
	/** 允许通行的渠道 */
	private Map<String, Object>           permitChannels     = new HashMap<String, Object>();

	public void resetPermitChannels(String channels) {
		permitChannels.clear();
		String[] array = StringUtil.splitString(channels, ',');
		if (array != null) {
			for (int ai = 0; ai < array.length; ai++) {
				permitChannels.put(array[ai], array[ai]);
			}
		}
	}

	public WhiteList getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(WhiteList whiteList) {
		this.whiteList = whiteList;
	}

	private AccountCache() {

	}

	public final void initAccount(UserAccount account) {
		ChannelAccount ca = channelAccounts.get(account.getChannelId());
		if (ca == null) {
			ca = new ChannelAccount(account.getChannelId());
			channelAccounts.put(ca.getChannel(), ca);
		}
		ca.initAccount(account);
		allAccounts.put(account.getAccountId(), account);
		if (account.getPhone() != null) {
			phoneBind.put(account.getPhone(), account.getAccountId());
		}
		if (account.getAccountId() > accountIdGenerator.get()) {
			accountIdGenerator.set(account.getAccountId());
		}
		if (account.getActiveCode() != null && account.getActiveCode().length() > 1) {
			ActiveCodeManager.getInstance().activeAccount(account);
		}
	}

	public final boolean isPhoneBinded(String phone) {
		return phoneBind.get(phone) != null;
	}

	public final void printAccount() {
		System.out.println("-------------------Start print Account----------------------");
		Set<String> keys = channelAccounts.keySet();
		for (String c : keys) {
			System.out.println("channel=" + c);
			channelAccounts.get(c).print();
		}
		System.out.println("-------------------End print Account----------------------");
	}

	public final void printServerLastEnter() {
		System.out.println("-------------------Start print ServerLastEnter----------------------");
		Collection<ServerLastEnter> values = serverLastEnter.values();
		for (ServerLastEnter v : values) {
			v.print();
		}
		System.out.println("-------------------End print ServerLastEnter----------------------");
	}

	public final void printServerRoleCount() {
		System.out.println("-------------------Start print ServerRoleCount----------------------");
		Collection<ServerRoleCount> values = serverRoleCount.values();
		for (ServerRoleCount v : values) {
			v.print();
		}
		System.out.println("-------------------End print ServerRoleCount----------------------");
	}

	public void initServerLastEnter(ServerLastEnter lastEnter) {
		serverLastEnter.put(lastEnter.getAccountId(), lastEnter);
	}

	public void initServerRoleCount(ServerRoleCount roleCount) {
		serverRoleCount.put(roleCount.getAccountId(), roleCount);
	}

	public final UserAccount getUserAccount(int accountId) {
		return allAccounts.get(accountId);
	}

	public final UserAccount getUserAccountByPhone(String phone) {
		Integer accountId = phoneBind.get(phone);
		if (accountId == null) {
			return null;
		}
		return allAccounts.get(accountId);
	}

	public final Object validateAccount(String channelId, String channelSub, String userid, String password) {
		ChannelAccount ca = channelAccounts.get(channelId);
		if (ca == null) {
			return ACCOUNT_NOT_EXIST;
		}
		return ca.validateAccount(channelSub, userid, password);
	}

	public final UserAccount validateChannelAccount(String channelId, String channelSub, String userid) {
		ChannelAccount ca = channelAccounts.get(channelId);
		if (ca == null) {
			return null;
		}
		return ca.validateChannelAccount(channelSub, userid);
	}

	public String getServerRoleCount(int accoutnId, int gameId) {
		ServerRoleCount rc = serverRoleCount.get(accoutnId);
		if (rc != null) {
			return rc.getRoleCount(gameId);
		}
		return "";
	}

	public String getServerLastEnter(int accountId, int gameId) {
		ServerLastEnter le = serverLastEnter.get(accountId);
		if (le != null) {
			return le.getLastEnter(gameId);
		}
		return "";
	}

	public void updateServerLastEnter(int gameId, int serverId, int accountId) {
		ServerLastEnter le = serverLastEnter.get(accountId);
		if (le == null) {
			le = new ServerLastEnter();
			le.setAccountId(accountId);
			serverLastEnter.put(accountId, le);
		}
		le.updateServerLast(gameId, serverId);
	}

	public void updateServerRoleCount(int gameId, int serverId, int accountId, int roleCount) {
		ServerRoleCount src = serverRoleCount.get(accountId);
		if (src == null) {
			src = new ServerRoleCount();
			src.setAccountId(accountId);
			serverRoleCount.put(accountId, src);
		}
		src.updateServerRoleCount(gameId, serverId, roleCount);
	}

	public long getDeviceFreeze(String imei) {
		DeviceFreeze df = devicesFreeze.get(imei);
		if (df != null) {
			if (df.getTimeFreeze() < System.currentTimeMillis()) {
				devicesFreeze.remove(imei);
			}
			return df.getTimeFreeze();
		}
		return 0;
	}

	public DeviceFreeze getFreezeDevice(String imei) {
		return devicesFreeze.get(imei);
	}

	public void initDeviceFreeze(DeviceFreeze df) {
		if (df.getTimeFreeze() > System.currentTimeMillis()) {
			devicesFreeze.put(df.getDeviceImei(), df);
		}
	}

	public void addDeviceFreeze(DeviceFreeze df) {
		if (df.getTimeFreeze() > System.currentTimeMillis()) {
			devicesFreeze.put(df.getDeviceImei(), df);
		}
	}

	public Map<String, DeviceFreeze> getDevicesFreeze() {
		return devicesFreeze;
	}

	public final int nextAccountId() {
		int account = 0;
		for (int c = 0; c < 50; c++) {
			account = accountIdGenerator.getAndIncrement();
			if (allAccounts.containsKey(account)) {
				continue;
			}
			return account;
		}
		return -1;
	}

	public final static String generatePassword() {
		long _long = System.currentTimeMillis();
		int _int = MathUtil.getRandom(100, 999);
		StringBuilder sb = new StringBuilder();
		sb.append(_long).append(_int);
		return MathUtil.getRandom(1, 9) + sb.substring(sb.toString().length() - 5);
	}

	public void newAccount(UserAccount account) {
		ChannelAccount ca = channelAccounts.get(account.getChannelId());
		if (ca == null) {
			ca = new ChannelAccount(account.getChannelId());
			channelAccounts.put(ca.getChannel(), ca);
		}
		ca.initAccount(account);
		allAccounts.put(account.getAccountId(), account);
		if (account.getPhone() != null && account.getPhone().trim().length() > 0) {
			phoneBind.put(account.getPhone(), account.getAccountId());
		}
	}

	public UserAccount bindPhone(UserAccount currUA, String phone) {
		Integer accountId = phoneBind.get(phone);
		if (accountId != null) {
			if (currUA.getAccountId() == accountId) {
				return currUA;
			} else {
				UserAccount oldUA = allAccounts.get(accountId);
				oldUA.setPhone("");
				currUA.setPhone(phone);
				phoneBind.put(phone, currUA.getAccountId());
				return oldUA;
			}
		} else {
			currUA.setPhone(phone);
			phoneBind.put(phone, currUA.getAccountId());
			return null;
		}
	}

	public boolean filterWhiteList(String channelId, String userid, String ip) {
		if (permitChannels.get(channelId) != null) {
			return true;
		}
		WhiteList wl = whiteList;
		if (wl == null) {
			return false;
		}
		return wl.filter(channelId, userid, ip);
	}
}
