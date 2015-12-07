package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.advertise.IdfaActive;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.thread.IChargeDatabase;

public class AddIdfaActiveDBRun implements IChargeDatabase {
	private IdfaActive idfa;

	public AddIdfaActiveDBRun(IdfaActive idfa) {
		this.idfa = idfa;
	}

	@Override
	public void run() {
		Service.getInstance().addIdfaActive(idfa);
	}

}
