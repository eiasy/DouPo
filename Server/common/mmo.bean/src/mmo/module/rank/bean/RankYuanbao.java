package mmo.module.rank.bean;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.config.MoneyConfig;
import mmo.module.cache.role.CacheRole;

public class RankYuanbao extends RankData {
	public RankYuanbao() {
	}

	public RankYuanbao(CacheRole role) {
		super(role);
		this.rankValue = role.getRmbMoney();
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getMoney(MoneyConfig.YUAN_BAO_1001);
	}

	public void reset() {
		super.reset();
		Role role = RoleManager.getOLUserRole(roleId);
		if (role != null) {
			this.rankValue = role.getMoney(MoneyConfig.YUAN_BAO_1001);
		}
	}

	public String getRankContent() {
		return "";
	}
}
