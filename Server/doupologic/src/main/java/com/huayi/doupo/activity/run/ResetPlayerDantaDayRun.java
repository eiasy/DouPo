package com.huayi.doupo.activity.run;

import com.huayi.doupo.activity.danta.DantaManager;

public class ResetPlayerDantaDayRun implements IActRunnable {
	private int instPlayer;

	public ResetPlayerDantaDayRun(int instPlayer) {
		super();
		this.instPlayer = instPlayer;
	}

	@Override
	public void run() {
		DantaManager.getInstance().checkResetRankDantaDay(instPlayer);
	}

}
