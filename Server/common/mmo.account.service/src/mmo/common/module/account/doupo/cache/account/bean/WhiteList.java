package mmo.common.module.account.doupo.cache.account.bean;

import java.util.HashMap;
import java.util.Map;

import mmo.tools.util.StringUtil;

public class WhiteList {
	private final static Object              DEFAULT   = new Object();
	private Map<String, Map<String, Object>> whiteList = new HashMap<String, Map<String, Object>>();
	private Map<String, Object>              whiteIps  = new HashMap<String, Object>();

	public Map<String, Map<String, Object>> getWhiteList() {
		return whiteList;
	}

	public void addChannelId(String channel, String id) {
		if (channel == null || channel.trim().length() < 1) {
			return;
		}
		if (id == null || id.trim().length() < 1) {
			return;
		}
		channel = channel.trim();
		id = id.trim();
		Map<String, Object> ids = whiteList.get(channel);
		if (ids == null) {
			ids = new HashMap<String, Object>();
			whiteList.put(channel, ids);
		}
		ids.put(id, DEFAULT);
	}

	public void addWhiteIp(String whiteIp) {
		whiteIps.clear();
		String[] array = StringUtil.splitString(whiteIp, ',');
		if (array != null) {
			String ip = null;
			for (int ai = 0; ai < array.length; ai++) {
				ip = array[ai].trim();
				if (ip.length() > 6) {
					whiteIps.put(ip, DEFAULT);
				}
			}
		}
	}

	public boolean filter(String channelId, String userid, String ip) {
		if (ip != null && whiteIps.get(ip) != null) {
			return true;
		}
		Map<String, Object> ids = whiteList.get(channelId);
		if (ids == null) {
			return false;
		}
		return ids.get(userid) != null;
	}
}
