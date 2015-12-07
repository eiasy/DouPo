package com.huayi.doupo.logic.handler.util;

import java.util.List;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictAcupointNode;
import com.huayi.doupo.base.model.DictKungFu;
import com.huayi.doupo.base.model.DictKungFuTierAdd;
import com.huayi.doupo.base.model.InstPlayerKungFu;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;

public class KungFuUtil  extends DALFactory{

	/**
	 * 添加新功法
	 * @author hzw
	 * @date 2014-7-22上午11:10:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static InstPlayerKungFu addInstPlayerKungFu(int instPlayerId, int kungFuId, int instPlayerCardId) throws Exception{
		InstPlayerKungFu instPlayerKungFu = new InstPlayerKungFu();
		DictKungFu dictKungFu = DictMap.dictKungFuMap.get(kungFuId + "");
		String acupointNodes = "";
		List<DictKungFuTierAdd> dictKungFuTierAddList = (List<DictKungFuTierAdd>)DictMapList.dictKungFuTierAddMap.get(kungFuId);
		for(DictKungFuTierAdd obj : dictKungFuTierAddList){
			if(obj.getTier() == 1){
				instPlayerKungFu.setKungFuTierAddId(obj.getId());
				break;
			}
		}
		List<DictAcupointNode> dictAcupointNodeList = (List<DictAcupointNode>)DictMapList.dictAcupointNodeMap.get(dictKungFu.getAcupoint());
		for(DictAcupointNode obj : dictAcupointNodeList){
			if(obj.getTier() == 1 && obj.getNode() ==1){
				instPlayerKungFu.setAcupointNodeId(obj.getId());
			}
			if(obj.getTier() == 1){
				acupointNodes = acupointNodes + obj.getId() + ";";
			}
			if(obj.getTier() > 1){
				break;
			}
		}
		instPlayerKungFu.setInstPlayerId(instPlayerId);
		instPlayerKungFu.setKungFuId(kungFuId);
		instPlayerKungFu.setExp(0);
		instPlayerKungFu.setAcupointNodes(acupointNodes.substring(0, acupointNodes.length() -1));
		instPlayerKungFu.setInstPlayerCardId(instPlayerCardId);
		
		instPlayerKungFu = getInstPlayerKungFuDAL().add(instPlayerKungFu, instPlayerId);
		return instPlayerKungFu;
	}
}
