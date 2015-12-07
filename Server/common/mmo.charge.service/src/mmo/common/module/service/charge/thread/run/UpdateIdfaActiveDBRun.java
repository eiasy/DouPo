package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.advertise.IdfaActive;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.thread.IChargeDatabase;

public class UpdateIdfaActiveDBRun implements IChargeDatabase {
	private IdfaActive idfa;

	public UpdateIdfaActiveDBRun(IdfaActive idfa) {
		this.idfa = idfa;
	}

	@Override
	public void run() {
		Service.getInstance().updateIdfaActive(idfa);
	}

}
