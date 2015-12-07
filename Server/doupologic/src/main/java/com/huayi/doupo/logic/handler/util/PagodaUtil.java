package com.huayi.doupo.logic.handler.util;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayerPagoda;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;

public class PagodaUtil extends DALFactory{
	
	
	public static void initInstPlayerPagoda(int instPlayerId) throws Exception{
		InstPlayerPagoda instPlayerPagoda = new InstPlayerPagoda();
		instPlayerPagoda.setInstPlayerId(instPlayerId);
		instPlayerPagoda.setNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.instPlayerPagodaNum));
		instPlayerPagoda.setPagodaStoreyId(DictMapUtil.getSysConfigIntValue(StaticSysConfig.pagodaStoreyId));
		instPlayerPagoda.setResetNum(0);
		instPlayerPagoda.setSearchNum(0);
		instPlayerPagoda.setMaxPagodaStoreyId(0);
		instPlayerPagoda.setOperTime(DateUtil.getCurrTime());
		instPlayerPagoda = getInstPlayerPagodaDAL().add(instPlayerPagoda, instPlayerId);
	}

}
