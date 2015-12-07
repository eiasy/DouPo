package mmo.module.rank.bean;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.module.cache.role.CacheRole;

public class RankLevel extends RankData {
	public RankLevel() {
	}

	public RankLevel(CacheRole role) {
		super(role);
		this.rankValue = role.getLevel();
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getLevel();
	}

	public void reset() {
		super.reset();
		Role role = RoleManager.getOLUserRole(roleId);
		if (role != null) {
			this.rankValue = role.getLevel();
		}
	}

	public String getRankContent() {
		return "";
	}
}
