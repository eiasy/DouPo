package mmo.module.gm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminManager {
	private static String userId;
	private static String sessionId;
	private static String powers;
	private static Map<Integer, List<Integer>> menus = new HashMap<Integer, List<Integer>>();

	public static Map<Integer, List<Integer>> getMenus() {
		return menus;
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		AdminManager.userId = userId;
	}

	public static String getSessionId() {
		return sessionId;
	}

	public static void setSessionId(String sessionId) {
		AdminManager.sessionId = sessionId;
	}

	public static String getPowers() {
		return powers;
	}

	public static void setPowers(String powers) {
		AdminManager.powers = powers;
		Map<Integer, List<Integer>> menus = new HashMap<Integer, List<Integer>>();
		JSONObject json = JSONObject.fromObject(powers);
		Set<String> keys = (Set<String>) json.keySet();
		for (String k : keys) {
			JSONArray ja = json.getJSONArray(k);
			List<Integer> items = new ArrayList<Integer>();
			for (int ji = 0; ji < ja.size(); ji++) {
				items.add(ja.getInt(ji));
			}
			menus.put(Integer.parseInt(k), items);
		}
		AdminManager.menus=menus;
	}
}
