package mmo.common.module.sdk.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.tools.config.ProjectCofigs;

public class ServerManager {
	private final static ServerManager instance = new ServerManager();
	private final static ServerList NULL_LIST = new ServerList("{\"servers_all\":[]}", "{\"servers_suggest\":[]}", "{\"servers_test\":[]}", Integer.MAX_VALUE, null);

	public final static ServerManager getInstance() {
		return instance;
	}

	public final void init(int platform, String serverAll, String serverSuggest, String serverTest) {
		int subStart = Integer.MAX_VALUE;
		String subPre = ProjectCofigs.getParameter("sub_pre_" + platform);
		if (subPre != null) {
			try {
				subStart = Integer.parseInt(ProjectCofigs.getParameter("sub_" + platform));
			} catch (Exception e) {
				subStart = Integer.MAX_VALUE;
			}
		}
		ServerList serverList = new ServerList(serverAll, serverSuggest, serverTest, subStart, subPre);
		platformServer.put(platform, serverList);
	}

	private Map<Integer, ServerList> platformServer = new HashMap<Integer, ServerList>();

	// private ServerList serverList = null;

	public ServerList getServerList(int platform) {
		ServerList serverList = platformServer.get(platform);
		if (serverList == null) {
			return NULL_LIST;
		}
		return serverList;
	}

	public void setServerList(int platform, ServerList serverList) {
		if (serverList.isFinishInit()) {
			platformServer.put(platform, serverList);
		}
	}

	private ServerManager() {

	}

	public List<BeanServer> getServerAll(int platform, boolean isTest, boolean isSub) {
		ServerList serverList = getServerList(platform);
		if (serverList != null) {
			return serverList.getAllList(isTest, isSub);
		}
		return new ArrayList<BeanServer>();
	}

	public List<BeanServer> getServerSuggest(int platform, boolean isTest, boolean isSub) {
		ServerList serverList = getServerList(platform);
		if (serverList != null) {
			return serverList.getSuggestList(isTest, isSub);
		}
		return new ArrayList<BeanServer>();
	}

	public List<BeanServer> getServerHistory(int platform, int[] array, boolean isSub) {
		ServerList serverList = getServerList(platform);
		if (serverList != null) {
			return serverList.getHistoryList(array, isSub);
		}
		return new ArrayList<BeanServer>();
	}
}
