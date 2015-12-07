package com.huayi.doupo.activity.run;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstLuckRank;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class UpdateRankDataRun implements IDatabaseRunnable {
	private InstLuckRank luckData;

	public UpdateRankDataRun(InstLuckRank luckData) {
		super();
		this.luckData = luckData;
	}

	@Override
	public void run() {
		try {
			DALFactory.getInstLuckRankDAL().update(luckData, 0);
		} catch (Exception e) {
			LogUtil.error("更新幸运转轮数据异常", e);
		}
	}

}
