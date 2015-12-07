package mmo.module.rank.bean;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.config.MoneyConfig;
import mmo.module.cache.role.CacheRole;

public class RankLingshi extends RankData {
	public RankLingshi() {
	}

	public RankLingshi(CacheRole role) {
		super(role);
		this.rankValue = role.getGameMoney();
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getMoney(MoneyConfig.LING_SHI_1000);
	}

	public void reset() {
		super.reset();
		Role role = RoleManager.getOLUserRole(roleId);
		if (role != null) {
			this.rankValue = role.getMoney(MoneyConfig.LING_SHI_1000);
		}
	}

	public String getRankContent() {
		return "";
	}
}
