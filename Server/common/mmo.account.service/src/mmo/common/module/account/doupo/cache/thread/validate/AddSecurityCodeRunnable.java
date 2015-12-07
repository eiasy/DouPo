package mmo.common.module.account.doupo.cache.thread.validate;

import mmo.common.module.account.doupo.cache.thread.IAccountValidate;
import mmo.common.module.account.doupo.security.SecurityCode;
import mmo.common.module.account.doupo.security.SecurityCodeManager;

/**
 * 把验证码加入验证码管理器
 * 
 * @author 李天喜
 * 
 */
public class AddSecurityCodeRunnable implements IAccountValidate {
	private SecurityCode securityCode;

	public AddSecurityCodeRunnable(SecurityCode securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public void run() {
		if (securityCode != null) {
			SecurityCodeManager.addSecurityCode(securityCode);
			securityCode = null;
		}
	}

}
