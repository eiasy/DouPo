package mmo.common.module.clazz.event.run;

import mmo.common.service.eventcenter.module.Admin;
import mmo.common.service.eventcenter.service.Service;
import mmo.common.service.eventcenter.thread.IChargeDatabase;

public class UpdateAdminDBRun implements IChargeDatabase {
	private Admin admin;

	public UpdateAdminDBRun(Admin admin) {
		this.admin = admin;
	}

	@Override
	public void run() {
		Service.getInstance().getAdminService().updateAdminSession(admin);
	}

}
