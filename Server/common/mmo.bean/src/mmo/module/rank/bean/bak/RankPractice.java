package mmo.module.rank.bean.bak;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.config.MoneyConfig;
import mmo.module.cache.role.CacheRole;
import mmo.module.rank.bean.RankData;

public class RankPractice extends RankData {
	protected RankPractice() {
	}

	public RankPractice(CacheRole role) {
		super(role);
		this.rankValue = role.getXiuWei();
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getMoney(MoneyConfig.XIU_WEI_1012);
	}

	public void reset() {
		super.reset();
		Role role = RoleManager.getOLUserRole(roleId);
		if (role != null) {
			this.rankValue = role.getMoney(MoneyConfig.XIU_WEI_1012);
		}
	}
}
