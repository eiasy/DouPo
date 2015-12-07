package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.advertise.IdfaEvent;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.thread.IChargeDatabase;

public class AddIdfaEventDBRun implements IChargeDatabase {
	private IdfaEvent idfa;

	public AddIdfaEventDBRun(IdfaEvent idfa) {
		this.idfa = idfa;
	}

	@Override
	public void run() {
		Service.getInstance().addIdfaActive(idfa);
	}

}
