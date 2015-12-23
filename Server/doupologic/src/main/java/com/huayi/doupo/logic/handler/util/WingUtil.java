package com.huayi.doupo.logic.handler.util;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayerWing;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.logic.util.MessageData;

/**
 * 翅膀处理类
 * @author mp
 * @date 2015-11-12 上午11:23:02
 */
public class WingUtil extends DALFactory{
	
	/**
	 * 添加翅膀
	 * @author mp
	 * @date 2015-11-12 上午11:27:19
	 * @param tableFiledId
	 * @param instPlayerId
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void addWing (int tableFiledId, int instPlayerId, MessageData syncMsgData) throws Exception{ 
		InstPlayerWing instPlayerWing = new InstPlayerWing();
		instPlayerWing.setInstPlayerId(instPlayerId);
		instPlayerWing.setWingId(tableFiledId);
		instPlayerWing.setLevel(0);
		instPlayerWing.setStarNum(1);
		instPlayerWing.setInstCardId(0);
		getInstPlayerWingDAL().add(instPlayerWing, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerWing, instPlayerWing.getId(), instPlayerWing.getResult(), syncMsgData);
	}
	
	/**
	 * 添加特殊翅膀
	 * @author mp
	 * @date 2015-12-18 下午3:01:55
	 * @param tableFiledId
	 * @param instPlayerId
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void addSpecialWing (int tableFiledId, int instPlayerId, MessageData syncMsgData) throws Exception{ 
		InstPlayerWing instPlayerWing = new InstPlayerWing();
		instPlayerWing.setInstPlayerId(instPlayerId);
		instPlayerWing.setWingId(tableFiledId);
		instPlayerWing.setLevel(0);
		instPlayerWing.setStarNum(3);
		instPlayerWing.setInstCardId(0);
		getInstPlayerWingDAL().add(instPlayerWing, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerWing, instPlayerWing.getId(), instPlayerWing.getResult(), syncMsgData);
	}
}
