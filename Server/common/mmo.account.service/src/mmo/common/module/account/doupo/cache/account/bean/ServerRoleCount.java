package mmo.common.module.account.doupo.cache.account.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.ServerRoleCountDBRun;
import mmo.tools.util.StringUtil;

/***
 * 在服务器上创建角色的数量
 * 
 * @author fanren
 * 
 */
public class ServerRoleCount {
	final static char               SPLIT_SYBOL_GAME = '|';
	final static char               SPLIT_SYBOL_1    = ':';
	final static char               SPLIT_SYBOL_2    = ';';
	final static char               SPLIT_SYBOL_3    = ',';
	/** <游戏编号，<游戏服务器编号，角色数量>> */
	private Map<Integer, RoleCount> serverRoleCount  = new HashMap<Integer, RoleCount>();
	private int                     accountId;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setRoleCount(String roleCount) {
		String[] array_game = StringUtil.splitString(roleCount, SPLIT_SYBOL_GAME);
		for (int gi = 0; gi < array_game.length; gi++) {
			String[] array_1 = StringUtil.splitString(array_game[gi], SPLIT_SYBOL_1);
			if (array_1.length == 2) {
				serverRoleCount.put(Integer.parseInt(array_1[0]), new RoleCount(array_1[1]));
			}
		}
	}

	public String packRoleCount() {
		StringBuilder sb = new StringBuilder();
		Set<Integer> games = serverRoleCount.keySet();
		for (int game : games) {
			sb.append(game).append(SPLIT_SYBOL_1);
			RoleCount rc = serverRoleCount.get(game);
			if (rc != null) {
				sb.append(rc.getRoleCount());
			}
			sb.append(SPLIT_SYBOL_GAME);
		}
		return sb.toString();
	}

	public void print() {
		System.out.println("accountid=" + accountId);
		Set<Integer> games = serverRoleCount.keySet();
		for (int game : games) {
			System.out.println("    game=" + game);
			Map<Integer, Integer> rc = serverRoleCount.get(game).getRoleCountMap();
			Set<Integer> servers = rc.keySet();
			for (int server : servers) {
				System.out.println("        " + server + ", " + rc.get(server));
			}
		}
	}

	public String getRoleCount(int gameId) {
		RoleCount rc = serverRoleCount.get(gameId);
		if (rc != null) {
			rc.getRoleCount();
		}
		return "";
	}

	public void updateServerRoleCount(int gameId, int serverId, int roleCount) {
		RoleCount rc = serverRoleCount.get(gameId);
		if (rc == null) {
			rc = new RoleCount();
			serverRoleCount.put(gameId, rc);
		}
		rc.updateServerRoleCount(serverId, roleCount);
		ThreadManager.accessDatabase(new ServerRoleCountDBRun(accountId, packRoleCount()));
	}
}
