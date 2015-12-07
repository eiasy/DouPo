package com.huayi.doupo.activity.run;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class ClearLuckDataRun implements IDatabaseRunnable {

	@Override
	public void run() {
		try {
			 DALFactory.getInstLuckRankDAL().deleteByWhere("");
		} catch (Exception e) {
			LogUtil.error("清理排行数据", e);
		}
	}

}
