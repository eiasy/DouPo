package com.huayi.doupo.activity.run;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class UpdateLimitRemainRun implements IDatabaseRunnable {
	private String remain;
	private boolean isDispatch;

	public UpdateLimitRemainRun(String remain, boolean isDispatch) {
		super();
		this.remain = remain;
		this.isDispatch = isDispatch;
	}

	@Override
	public void run() {
		try {
			int result = DALFactory.getInstPlayerBigTableDAL().update("update Inst_Player_BigTable set value1='" + remain + "', value2='" + isDispatch + "' where properties='" + StaticBigTable.luckRemain + "'");
			if (result < 1) {
				InstPlayerBigTable bt = new InstPlayerBigTable();
				bt.setProperties(StaticBigTable.luckRemain);
				bt.setValue1(remain);
				bt.setValue2(isDispatch + "");
				DALFactory.getInstPlayerBigTableDAL().add(bt, 0);
			}
		} catch (Exception e) {
			LogUtil.error("更新幸运转轮限定剩余数量异常", e);
		}
	}

}
