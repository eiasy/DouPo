package mmo.common.bean.role.charge;

import java.util.HashMap;
import java.util.Map;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.bean.role.Role;
import mmo.common.config.charge.ChargeType;
import mmo.module.cache.role.CacheRole;

public class ChargeCountManage {
	/** 角色充值汇总<角色ID，角色充值汇总> */
	private final static Map<Integer, ChargeCount> roleChargeCount = new HashMap<Integer, ChargeCount>();

	public final static void initChargeCount(ChargeCount chargeCount) {
		roleChargeCount.put(chargeCount.getRoleId(), chargeCount);
	}

	public final static int getChargeTotal(int roleId) {
		ChargeCount cc = roleChargeCount.get(roleId);
		if (cc == null) {
			return 0;
		}
		return cc.getChargeTotal();
	}

	public final static int getChargeTotal(Role role) {
		ChargeCount cc = roleChargeCount.get(role.getId());
		if (cc == null) {
			return 0;
		}
		return cc.getChargeTotal();
	}

	public final static int getChargeTotal(CacheRole role) {
		ChargeCount cc = roleChargeCount.get(role.getRoleId());
		if (cc == null) {
			return 0;
		}
		return cc.getChargeTotal();
	}

	public final static ChargeCount newChargeRecord(ChargeRecord cr) {
		ChargeCount cc = roleChargeCount.get(cr.getRoleId());
		if (cc == null) {
			cc = new ChargeCount();
			cc.setAccountId(cr.getAccountId());
			cc.setRoleId(cr.getRoleId());
			roleChargeCount.put(cc.getRoleId(), cc);
		}
		switch (cr.getCtype()) {
			case ChargeType.TYPE_GM_ADD: {
				cc.addChargeTotal(cr.getMoney());
				cc.addPatchCount(cr.getMoney());
				cc.addNumgerPatch();
				break;
			}
			case ChargeType.TYPE_ROLE_CHARGE_FAIL: {
				cc.addNumgerFail();
				break;
			}
			case ChargeType.TYPE_ROLE_CHARGE_SUCC: {
				cc.addChargeTotal(cr.getMoney());
				cc.addNumgerSucc();
				break;
			}
		}
		return cc;
	}
}
