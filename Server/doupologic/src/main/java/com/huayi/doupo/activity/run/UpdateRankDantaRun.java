package com.huayi.doupo.activity.run;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstRankDanta;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class UpdateRankDantaRun implements IDatabaseRunnable {
	private InstRankDanta data;

	public UpdateRankDantaRun(InstRankDanta data) {
		super();
		this.data = data;
	}

	@Override
	public void run() {
		try {
			DALFactory.getInstRankDantaDAL().update(data, 0);
		} catch (Exception e) {
			LogUtil.error("更新当日丹塔排行数据异常", e);
		}
	}

}
