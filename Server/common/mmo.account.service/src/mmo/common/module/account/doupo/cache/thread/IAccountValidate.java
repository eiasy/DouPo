package mmo.common.module.account.doupo.cache.thread;

import mmo.tools.thread.runnable.CRunnable;

public interface IAccountValidate extends CRunnable {
	/** 新账号登录(注册并登录) */
	int LOGIN_1_NEW_ACCOUNT = 1;
	/** 老账号登录 */
	int LOGIN_2_OLD_ACCOUNT = 2;

}
