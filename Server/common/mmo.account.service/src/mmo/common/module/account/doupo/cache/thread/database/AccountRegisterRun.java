package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.common.module.account.doupo.cache.thread.IAccountDatabase;

public class AccountRegisterRun implements IAccountDatabase {
	private UserAccount ua;
	private String      deviceOS;
	private String      osVersion;
	private String      deviceUdid;
	private String      deviceMac;
	private String      deviceUA;
	private String      phoneType;
	private int         screenHeight;
	private int         screenWidth;
	private String      codeVersion;
	private int         productId;
	private String      ip;
	private byte        registFrom;

	public AccountRegisterRun(UserAccount ua, String deviceOS, String osVersion, String deviceUdid, String deviceMac, String deviceUA,
	        String phoneType, int screenHeight, int screenWidth, String codeVersion, int productId, String ip, byte registerFrom) {
		super();
		this.ua = ua;
		this.deviceOS = deviceOS;
		this.osVersion = osVersion;
		this.deviceUdid = deviceUdid;
		this.deviceMac = deviceMac;
		this.deviceUA = deviceUA;
		this.phoneType = phoneType;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.codeVersion = codeVersion;
		this.productId = productId;
		this.ip = ip;
		this.registFrom = registerFrom;
	}

	@Override
	public void run() {
		ServiceAccount.getInstance().register(ua, deviceOS, osVersion, deviceUdid, deviceMac, deviceUA, phoneType, screenHeight, screenWidth,
		        codeVersion, productId, ip, registFrom);
		ua = null;
	}

}
