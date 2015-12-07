package com.huayi.doupo.activity.run;

import com.huayi.doupo.activity.danta.DantaManager;

public class ResetDantaRun implements IActRunnable {

	public ResetDantaRun() {
		super();
	}

	@Override
	public void run() {
		DantaManager.getInstance().checkReset();
	}

}
