package mmo.common.module.sdk.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.tools.log.LoggerError;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ServerList {
	private String serverAll;
	private String serverSuggest;

	public String getServerTest() {
		return serverTest;
	}

	private String serverTest;
	private List<BeanServer> suggestList = new ArrayList<BeanServer>();
	private List<BeanServer> allList = new ArrayList<BeanServer>();
	private List<BeanServer> testList = new ArrayList<BeanServer>();
	private Map<Integer, BeanServer> serverMap = new HashMap<Integer, BeanServer>();
	/** 子列表，从某个区开始截取 */
	private List<BeanServer> subSuggestList = new ArrayList<BeanServer>();
	private List<BeanServer> subAllList = new ArrayList<BeanServer>();
	private Map<Integer, BeanServer> subServerMap = new HashMap<Integer, BeanServer>();

	private boolean finishInit = false;

	public List<BeanServer> getSuggestList(boolean isTest, boolean isSub) {
		if (isTest) {
			return testList;
		} else {
			if (isSub) {
				return subSuggestList;
			} else {
				return suggestList;
			}
		}
	}

	public List<BeanServer> getAllList(boolean isTest, boolean isSub) {
		if (isTest) {
			return testList;
		} else {
			if (isSub) {
				return subAllList;
			} else {
				return allList;
			}
		}
	}

	public List<BeanServer> getHistoryList(int[] array, boolean isSub) {
		List<BeanServer> list = new ArrayList<BeanServer>();
		if (isSub) {
			BeanServer server = null;
			for (int li = 0; li < array.length; li++) {
				server = subServerMap.get(array[li]);
				if (server != null) {
					list.add(server);
				}
			}
		} else {
			BeanServer server = null;
			for (int li = 0; li < array.length; li++) {
				server = serverMap.get(array[li]);
				if (server != null) {
					list.add(server);
				}
			}
		}
		return list;
	}

	public ServerList(String serverAll, String serverSuggest, String serverTest, int subStart, String subPre) {
		super();
		this.serverAll = serverAll;
		this.serverSuggest = serverSuggest;
		this.serverTest = serverTest;
		try {
			JSONObject jsonObj = JSONObject.fromObject(serverAll);

			JSONArray array = jsonObj.getJSONArray("servers_all");
			StringBuilder sb = new StringBuilder();
			BeanServer server = null;
			for (int a = 0; a < array.size(); a++) {
				server = (BeanServer) JSONObject.toBean(array.getJSONObject(a), BeanServer.class);
				allList.add(server);
				serverMap.put(server.getId(), server);
				if (subPre != null) {// "宝山老人(A42)"
					if (subStart > 1 && server.getId() >= subStart) {
						server = (BeanServer) JSONObject.toBean(array.getJSONObject(a), BeanServer.class);
						String[] array_1 = StringUtil.splitString(server.getName().replace('（', '('), '(');
						sb.setLength(0);
						sb.append(array_1[0]).append('(').append(subPre).append((server.getId() - subStart + 1) % 10000).append(')');
						server.setName(sb.toString());
						subAllList.add(server);
						subServerMap.put(server.getId(), server);
					}
				}
			}

			jsonObj = JSONObject.fromObject(serverSuggest);
			array = jsonObj.getJSONArray("servers_suggest");
			for (int a = 0; a < array.size(); a++) {
				server = (BeanServer) JSONObject.toBean(array.getJSONObject(a), BeanServer.class);
				suggestList.add(server);

				if (subPre != null) {// "宝山老人(A42)"
					if (subStart > 1 && server.getId() >= subStart) {
						server = (BeanServer) JSONObject.toBean(array.getJSONObject(a), BeanServer.class);
						String[] array_1 = StringUtil.splitString(server.getName().replace('（', '('), '(');
						sb.setLength(0);
						sb.append(array_1[0]).append('(').append(subPre).append((server.getId() - subStart + 1) % 10000).append(')');
						server.setName(sb.toString());
						subSuggestList.add(server);
					}
				}
			}

			if (subSuggestList.size() < 1 && subAllList.size() > 0) {
				subSuggestList.add(subAllList.get(subAllList.size() - 1));
			}
			jsonObj = JSONObject.fromObject(serverTest);
			array = jsonObj.getJSONArray("servers_test");
			for (int a = 0; a < array.size(); a++) {
				server = (BeanServer) JSONObject.toBean(array.getJSONObject(a), BeanServer.class);
				testList.add(server);
			}
			finishInit = true;
		} catch (Exception e) {
			LoggerError.error("加载服务器列表报错@serverAll=" + serverAll + "@serverSuggest=" + serverSuggest, e);
		}
	}

	public boolean isFinishInit() {
		return finishInit;
	}

	public String getServerAll() {
		if (serverAll == null) {
			return "";
		}
		return serverAll;
	}

	public String getServerSuggest() {
		if (serverSuggest == null) {
			return "";
		}
		return serverSuggest;
	}

}
