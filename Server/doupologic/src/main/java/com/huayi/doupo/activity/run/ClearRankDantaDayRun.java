package com.huayi.doupo.activity.run;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class ClearRankDantaDayRun implements IDatabaseRunnable {

	@Override
	public void run() {
		try {
			 DALFactory.getInstRankDantaDayDAL().deleteByWhere("");
		} catch (Exception e) {
			LogUtil.error("清理丹塔日排行数据", e);
		}
	}

}
