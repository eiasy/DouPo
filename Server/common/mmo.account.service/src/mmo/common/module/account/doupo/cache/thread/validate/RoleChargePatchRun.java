package mmo.common.module.account.doupo.cache.thread.validate;

import java.util.HashMap;
import java.util.Map;

import mmo.common.account.HttpCData;
import mmo.common.charge.ChargeCData;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.IAccountValidate;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.http.ChargePatchRun;
import mmo.tools.util.MD5;

public class RoleChargePatchRun implements IAccountValidate {
	private int    gameid;
	private int    serverid;
	private int    roleid;
	private String roleName;
	private int    amount;
	private String reason;
	private byte   ctype;
	private byte   state;
	private int    accountId;
	private String userid;
	private int    goodsId;
	private String goodsName;
	private short  goodsCount;
	private String managerKey;

	public RoleChargePatchRun(int gameid, int serverid, int roleid, String roleName, int amount, String reason, byte ctype, byte state,
	        int accountId, String userid, int goodsId, String goodsName, short goodsCount, String managerKey) {
		super();
		this.gameid = gameid;
		this.serverid = serverid;
		this.roleid = roleid;
		this.roleName = roleName;
		this.amount = amount;
		this.reason = reason;
		this.ctype = ctype;
		this.state = state;
		this.accountId = accountId;
		this.userid = userid;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsCount = goodsCount;
		this.managerKey = managerKey;
	}

	@Override
	public void run() {
		UserAccount ua = AccountCache.getInstance().getUserAccount(accountId);
		String channelId = "";
		String channelSub = null;
		if (ua != null) {
			this.userid = ua.getUserid();
			channelId = ua.getChannelId();
			channelSub = ua.getChannelSub();
		}

		StringBuilder sb = new StringBuilder();
		sb.append(gameid).append('^');
		sb.append(serverid).append('^');
		sb.append(roleid).append('^');
		sb.append(roleName).append('^');
		sb.append(amount).append('^');
		sb.append(ctype).append('^');
		sb.append(accountId).append('^');
		sb.append(userid).append('^');
		sb.append(channelId).append('^');
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ChargeCData.Patch.gameid, gameid + "");
		parameters.put(ChargeCData.Patch.serverid, serverid + "");
		parameters.put(ChargeCData.Patch.roleid, roleid + "");
		parameters.put(ChargeCData.Patch.roleName, roleName);
		parameters.put(ChargeCData.Patch.amount, amount + "");
		parameters.put(ChargeCData.Patch.reason, reason);
		parameters.put(ChargeCData.Patch.ctype, ctype + "");
		parameters.put(ChargeCData.Patch.state, state + "");
		parameters.put(ChargeCData.Patch.accountId, accountId + "");
		parameters.put(ChargeCData.Patch.userid, userid);
		parameters.put(ChargeCData.Patch.channelId, channelId + "");
		parameters.put(ChargeCData.Patch.channelSub, channelSub);
		parameters.put(ChargeCData.Patch.goodsId, goodsId + "");
		parameters.put(ChargeCData.Patch.goodsName, goodsName);
		parameters.put(ChargeCData.Patch.goodsCount, goodsCount + "");
		parameters.put(HttpCData.MANAGER_KEY, managerKey);
		parameters.put(ChargeCData.Patch.sign, MD5.getHashString(sb.toString()));

		ThreadManager.requestHttp(new ChargePatchRun(parameters));
	}
}
