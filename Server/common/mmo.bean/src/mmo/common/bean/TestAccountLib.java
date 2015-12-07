package mmo.common.bean;

import java.util.HashMap;
import java.util.Map;

public class TestAccountLib {
	private final static Object               TMP_OBJ  = new Object();
	private final static Map<Integer, Object> accounts = new HashMap<Integer, Object>();

	public final static boolean isTestAccount(int accountId) {
		return accounts.containsKey(accountId);
	}

	public final static void addTestAccount(int accountId) {
		if (accountId < 1) {
			return;
		}
		accounts.put(accountId, TMP_OBJ);
	}

	public final static void clearTestAccount() {
		accounts.clear();
	}
}
