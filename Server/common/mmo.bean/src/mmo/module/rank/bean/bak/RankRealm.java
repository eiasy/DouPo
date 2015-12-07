package mmo.module.rank.bean.bak;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.module.cache.role.CacheRole;
import mmo.module.rank.bean.RankData;

public class RankRealm extends RankData {
	protected RankRealm() {
	}

	public RankRealm(CacheRole role) {
		super(role);
		this.rankValue = role.getRealm();
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getRealm();
	}

	public void reset() {
		super.reset();
		Role role = RoleManager.getOLUserRole(roleId);
		if (role != null) {
			this.rankValue = role.getRealm();
		}
	}
}
