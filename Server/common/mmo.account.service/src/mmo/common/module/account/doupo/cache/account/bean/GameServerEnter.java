package mmo.common.module.account.doupo.cache.account.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.module.account.doupo.cache.account.bean.compare.ComparatorEnterTime;

public class GameServerEnter {
	private int                           gameId;
	private Map<Integer, ServerEnterTime> servers     = new HashMap<Integer, ServerEnterTime>();
	private Map<String, Long>             serversTime = new HashMap<String, Long>();

	private String                        serverLast  = "";

	public String getServerLast() {
		if (serverLast == null) {
			return "";
		}
		return serverLast;
	}

	public Map<String, Long> getServersTime() {
		return serversTime;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public void enterServer(int serverId, long time) {
		ServerEnterTime server = servers.get(serverId);
		if (server == null) {
			server = new ServerEnterTime();
			server.setServerId(serverId);
			servers.put(serverId, server);
		}
		server.setTime(time);
		serversTime.put(serverId + "", time);
		resetServerLast();
	}

	public String resetServerLast() {
		List<ServerEnterTime> list = new ArrayList<ServerEnterTime>(servers.values());
		Collections.sort(list, new ComparatorEnterTime());
		int size = list.size();
		if (size > 4) {
			size = 4;
		}
		StringBuilder sb = new StringBuilder();
		for (int li = 0; li < size; li++) {
			if (li > 0) {
				sb.append(':');
			}
			sb.append(list.get(li).getServerId());
		}
		serverLast = sb.toString();
		return sb.toString();
	}

	public Map<Integer, ServerEnterTime> getServers() {
		return servers;
	}

}
