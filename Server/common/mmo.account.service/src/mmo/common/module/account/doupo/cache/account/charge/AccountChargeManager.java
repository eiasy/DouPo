package mmo.common.module.account.doupo.cache.account.charge;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.module.account.doupo.cache.account.service.ServiceAccountCharge;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.UpdateAccountChargeRun;

public class AccountChargeManager {
	private final static AccountChargeManager instance = new AccountChargeManager();

	public final static AccountChargeManager getInstance() {
		return instance;
	}

	/** 账号充值《账号编号，《游戏编号，《服务器编号，充值数额》》》 */
	private Map<Integer, Map<Integer, Map<Integer, AccountCharge>>> accountGameCharges = new HashMap<Integer, Map<Integer, Map<Integer, AccountCharge>>>();

	private AccountChargeManager() {

	}

	public void loadAccountCharge() {
		List<AccountCharge> list = ServiceAccountCharge.getInstance().loadAccountCharges((byte) 0);
		AccountCharge ac = null;
		for (int ci = 0; ci < list.size(); ci++) {
			ac = list.get(ci);
			Map<Integer, Map<Integer, AccountCharge>> acs = accountGameCharges.get(ac.getAccountId());
			if (acs == null) {
				acs = new HashMap<Integer, Map<Integer, AccountCharge>>();
				accountGameCharges.put(ac.getAccountId(), acs);
			}
			Map<Integer, AccountCharge> as = acs.get(ac.getGameId());
			if (as == null) {
				as = new HashMap<Integer, AccountCharge>();
				acs.put(ac.getGameId(), as);
			}
			as.put(ac.getServerId(), ac);
		}
	}

	public boolean hasAccountCharge(int accountId, int gameId) {
		Map<Integer, Map<Integer, AccountCharge>> acs = accountGameCharges.get(accountId);
		if (acs == null) {
			return false;
		}
		return acs.get(gameId) != null;
	}

	public long[] getAndRemoveAccountCharge(int accountId, int gameId, int roleId, int roleServerId) {
		long[] array = new long[2];
		Map<Integer, Map<Integer, AccountCharge>> acs = accountGameCharges.get(accountId);
		if (acs == null) {
			return array;
		} else {
			Map<Integer, AccountCharge> as = acs.remove(gameId);
			if (as != null) {
				int count = 0;
				Collection<AccountCharge> values = as.values();
				for (AccountCharge ac : values) {
					ac.setRoleId(roleId);
					ac.setRoleServerId(roleServerId);
					ac.setTimeAward(System.currentTimeMillis());
					ac.setStatus((byte) 1);
					count += ac.getRmb();
					if (array[1] < 1) {
						array[1] = ac.getTimeCharge();
					}
					ThreadManager.accessDatabase(new UpdateAccountChargeRun(ac));
				}
				array[0] = count;
				return array;
			}
		}
		return array;
	}
}
