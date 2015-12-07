package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.advertise.IdfaActive;
import mmo.tools.thread.runnable.IHttpRequest;

public abstract class ANotifyIdfaActiveRune implements IHttpRequest {
	private IdfaActive idfa;

	public void setIdfa(IdfaActive idfa) {
		this.idfa = idfa;
	}

}
