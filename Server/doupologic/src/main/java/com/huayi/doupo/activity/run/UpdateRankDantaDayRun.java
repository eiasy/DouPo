package com.huayi.doupo.activity.run;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstRankDantaDay;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class UpdateRankDantaDayRun implements IDatabaseRunnable {
	private InstRankDantaDay data;

	public UpdateRankDantaDayRun(InstRankDantaDay data) {
		super();
		this.data = data;
	}

	@Override
	public void run() {
		try {
			if (DALFactory.getInstRankDantaDayDAL().isExist(data.getId(), null)) {
				DALFactory.getInstRankDantaDayDAL().update(data, 0);
			}
		} catch (Exception e) {
			LogUtil.error("更新当日丹塔排行数据异常", e);
		}
	}

}
