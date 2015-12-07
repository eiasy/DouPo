package mmo.common.module.account.doupo.cache.account.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.ServerLastDBRun;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONObject;

public class ServerLastEnter {
	private final static int              VER_1_BASE      = 1;
	private final static char             SPLIT_SYBOL_1   = ';';
	private final static char             SPLIT_SYBOL_2   = ',';
	private final static char             SPLIT_SYBOL_3   = ':';
	/** <游戏编号,服务器编号> */
	private Map<Integer, GameServerEnter> serverLastEnter = new HashMap<Integer, GameServerEnter>();
	private int                           accountId;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setServers(String servers) {
		try {
			JSONObject json = JSONObject.fromObject(servers);
			Set<String> keys = json.keySet();
			for (String k : keys) {
				if (k.startsWith("g_")) {
					int gameId = Integer.parseInt(k.substring(k.indexOf('_') + 1));
					GameServerEnter gse = serverLastEnter.get(gameId);
					if (gse == null) {
						gse = new GameServerEnter();
						gse.setGameId(gameId);
						serverLastEnter.put(gameId, gse);
					}
					JSONObject jsonServers = json.getJSONObject(k);
					Set<String> serverKeys = jsonServers.keySet();
					for (String sk : serverKeys) {
						gse.enterServer(Integer.parseInt(sk), jsonServers.getLong(sk));
					}
				}
			}
		} catch (Exception e) {
			String[] array_1 = StringUtil.splitString(servers, SPLIT_SYBOL_1);
			for (int a1 = 0; a1 < array_1.length; a1++) {
				String[] array_2 = StringUtil.splitString(array_1[a1], SPLIT_SYBOL_2);
				if (array_2.length == 2) {
					int gameId = Integer.parseInt(array_2[0]);
					GameServerEnter gse = serverLastEnter.get(array_2[0]);
					if (gse == null) {
						gse = new GameServerEnter();
						gse.setGameId(gameId);
						serverLastEnter.put(gameId, gse);
					}
					int[] ss = StringUtil.string2IntArray(array_2[1], SPLIT_SYBOL_3);
					for (int s : ss) {
						gse.enterServer(s, System.currentTimeMillis());
					}
				}
			}
		}
	}

	public String packServers() {
		JSONObject json = new JSONObject();
		json.put("version", VER_1_BASE);
		Set<Integer> keys = serverLastEnter.keySet();
		for (int k : keys) {
			json.put("g_" + k, serverLastEnter.get(k).getServersTime());
		}
		return json.toString();
	}

	public void print() {
		System.out.println("accountid=" + accountId);
		Set<Integer> games = serverLastEnter.keySet();
		for (int game : games) {
			System.out.println("    " + game + ", " + serverLastEnter.get(game));
		}
	}

	public String getLastEnter(int gameId) {
		GameServerEnter gse = serverLastEnter.get(gameId);
		if (gse == null) {
			return "";
		}
		return gse.getServerLast();
	}

	public void updateServerLast(int gameId, int serverId) {
		GameServerEnter gse = serverLastEnter.get(gameId);
		if (gse == null) {
			gse = new GameServerEnter();
			gse.setGameId(gameId);
			serverLastEnter.put(gameId, gse);
		}
		gse.enterServer(serverId, System.currentTimeMillis());
		ThreadManager.accessDatabase(new ServerLastDBRun(accountId, packServers()));
	}
}
