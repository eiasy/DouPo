package mmo.module.rank.bean.bak;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.charge.ChargeCountManage;
import mmo.module.cache.role.CacheRole;
import mmo.module.rank.bean.RankData;

public class RankCharge extends RankData {
	protected RankCharge() {
	}

	public RankCharge(CacheRole role) {
		super(role);
		this.rankValue = ChargeCountManage.getChargeTotal(role);
	}

	public int getCompareResult(Role role) {
		return this.rankValue - ChargeCountManage.getChargeTotal(role);
	}

	public void reset() {
		super.reset();
		this.rankValue = ChargeCountManage.getChargeTotal(roleId);
	}
}
