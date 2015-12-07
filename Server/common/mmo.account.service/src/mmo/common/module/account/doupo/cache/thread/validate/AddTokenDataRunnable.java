package mmo.common.module.account.doupo.cache.thread.validate;

import mmo.common.module.account.doupo.cache.thread.IAccountValidate;
import mmo.common.module.account.doupo.security.SecurityCodeManager;
import mmo.common.module.account.doupo.security.TokenData;

public class AddTokenDataRunnable implements IAccountValidate {
	private TokenData tokenData;

	public AddTokenDataRunnable(TokenData tokenData) {
		this.tokenData = tokenData;
	}

	@Override
	public void run() {
		if (tokenData != null) {
			SecurityCodeManager.addTokenData(tokenData);
			tokenData = null;
		}
	}

}
