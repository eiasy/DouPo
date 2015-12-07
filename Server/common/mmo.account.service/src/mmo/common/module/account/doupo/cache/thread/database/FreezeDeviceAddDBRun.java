package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.bean.DeviceFreeze;
import mmo.common.module.account.doupo.cache.account.service.ServiceDeviceFreeze;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class FreezeDeviceAddDBRun implements IDatabaseRunnable {
	private DeviceFreeze df;
	private boolean      add;

	public FreezeDeviceAddDBRun(DeviceFreeze df, boolean add) {
		super();
		this.df = df;
		this.add = add;
	}

	@Override
	public void run() {
		if (df != null) {
			if (add) {
				ServiceDeviceFreeze.getInstance().freezeDevice(df);
			} else {
				ServiceDeviceFreeze.getInstance().updateFreezeDevice(df);
			}
		}
		df = null;
	}

}
