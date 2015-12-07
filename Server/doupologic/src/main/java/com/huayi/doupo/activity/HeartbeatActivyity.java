package com.huayi.doupo.activity;

import com.huayi.doupo.activity.danta.DantaManager;
import com.huayi.doupo.activity.luck.ActivityLuckManager;
import com.huayi.doupo.activity.run.IActRunnable;
import com.huayi.doupo.activity.thread.AHeartbeat;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.util.base.DateUtil;

public class HeartbeatActivyity extends AHeartbeat {
	public final static HeartbeatActivyity instance = new HeartbeatActivyity();

	public final static HeartbeatActivyity getInstance() {
		return instance;
	}

	private HeartbeatActivyity() {
		execute(new IActRunnable() {

			@Override
			public void run() {
				ActivityLuckManager.getInstance().init();
				DantaManager.getInstance().init();
			}
		});
	}


	protected final void execute(IActRunnable run) {
		super.addEvent(run);
	}

	public void callback() {
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.lucky + "");
		if (dictActivity == null) {
			return;
		}
		if (dictActivity.getEndTime() == null) {
			return;
		}
		if (DateUtil.string2MillSecond(dictActivity.getEndTime()) < System.currentTimeMillis()) {
			if (ActivityLuckManager.getInstance().getRankCount() > 0) {
				ActivityLuckManager.getInstance().dispatchAward();
			}
		}
	}
}
