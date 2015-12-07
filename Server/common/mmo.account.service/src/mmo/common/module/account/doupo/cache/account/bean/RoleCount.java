package mmo.common.module.account.doupo.cache.account.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.tools.util.StringUtil;

public class RoleCount {
	private Map<Integer, Integer> roleCountMap    = new HashMap<Integer, Integer>();
	private String                roleCountString = "";

	public RoleCount(String roleCount) {
		if (roleCount != null) {
			this.roleCountString = roleCount;
			String[] array_2 = StringUtil.splitString(roleCount, ServerRoleCount.SPLIT_SYBOL_2);
			for (int si = 0; si < array_2.length; si++) {
				String[] array_3 = StringUtil.splitString(array_2[si], ServerRoleCount.SPLIT_SYBOL_3);
				if (array_3.length == 2) {
					roleCountMap.put(Integer.parseInt(array_3[0]), Integer.parseInt(array_3[1]));
				}
			}
		}
	}

	protected RoleCount() {

	}

	public void updateServerRoleCount(int serverId, int roleCount) {
		roleCountMap.put(serverId, roleCount);
		this.roleCountString = packetString();
	}

	public String getRoleCount() {
		return roleCountString;
	}

	public Map<Integer, Integer> getRoleCountMap() {
		return roleCountMap;
	}

	protected String packetString() {
		boolean first = true;
		Set<Integer> keys = roleCountMap.keySet();
		StringBuilder sb = new StringBuilder();
		for (int k : keys) {
			if (!first) {
				sb.append(ServerRoleCount.SPLIT_SYBOL_2);
			} else {
				first = false;
			}
			sb.append(k).append(ServerRoleCount.SPLIT_SYBOL_3).append(roleCountMap.get(k));
		}
		return sb.toString();
	}
}
