package mmo.common.module.account.doupo.cache.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.common.module.account.doupo.cache.account.bean.ActiveCode;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.AccountDatabaseHeartbeat;
import mmo.common.module.account.doupo.cache.thread.database.UpdateActiveCodeRun;

public class ActiveCodeManager {
	private final static ActiveCodeManager instance = new ActiveCodeManager();

	public final static ActiveCodeManager getInstance() {
		return instance;
	}

	private Map<String, ActiveCode> allCode      = new HashMap<String, ActiveCode>();
	private Map<String, Integer>    code2account = new HashMap<String, Integer>();

	private ActiveCodeManager() {

	}

	public void activeAccount(UserAccount account) {
		if (account == null || account.getActiveCode() != null) {
			return;
		}
		code2account.put(account.getActiveCode().toUpperCase(), account.getAccountId());
	}

	public void resetActiveCode() {
		Set<String> keys = code2account.keySet();
		for (String k : keys) {
			UserAccount ua = AccountCache.getInstance().getUserAccount(code2account.get(k));
			if (ua != null) {
				ua.setActiveCode("");
				AccountDatabaseHeartbeat.getInstance().execute(new UpdateActiveCodeRun(ua));
			}
		}
		code2account.clear();
	}

	public void initActiveCode(ActiveCode code) {
		if (code == null || code.getCode() == null) {
			return;
		}
		allCode.put(code.getCode().toUpperCase(), code);
	}

	public boolean validate(UserAccount ua, String ac) {
		if (ac == null) {
			return false;
		}
		String activeCode = ac.toUpperCase();
		ActiveCode code = allCode.get(activeCode);
		if (code == null) {
			return false;
		}
		if (code.getTimeEnd() < System.currentTimeMillis()) {
			return false;
		}
		Integer account = code2account.get(activeCode);
		if (account == null || account < 1) {
			ua.setActiveCode(activeCode);
			code2account.put(activeCode, ua.getAccountId());
			return true;
		}
		return false;
	}

}
