package com.huayi.doupo.logic.other;

import java.util.List;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerConstell;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;

public class ConstellMain extends DALFactory{
	
	public static void main(String[] args) {
		try {
			SpringUtil.getSpringContext();
			DictData.loadDictData();
			int constellNum = 8;
			
			long start = DateUtil.getCurrMill();
			
			for (DictCard cardObj : DictList.dictCardList) {
				if (cardObj.getId() < 500) {
					int cardId = cardObj.getId();
					String constell = cardObj.getConstells();
					int [] newAddConstell = new int [2];
					if (constell.split(";").length == constellNum) {
						newAddConstell[0] =  ConvertUtil.toInt(constell.split(";")[constell.split(";").length-2]);
						newAddConstell[1] =  ConvertUtil.toInt(constell.split(";")[constell.split(";").length-1]);
						
						//查找玩家拥有的所有这张卡
						List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("cardId = " + cardId, 0);
						System.out.println("cardId= " + cardId + " 的卡牌实例共数= " + instPlayerCardList.size());
						for (InstPlayerCard instPlayerCard : instPlayerCardList) {
							int instCardId = instPlayerCard.getId();
							int instPlayerId = instPlayerCard.getInstPlayerId();
							//向命宫实例表中插入新加的那两个命宫
							String newAddInstConstell = ";";
							for (int i = 0; i < newAddConstell.length; i++) {
								int constellId = newAddConstell[i];
								InstPlayerConstell instPlayerConstell = new InstPlayerConstell();
								instPlayerConstell.setInstPlayerId(instPlayerId);
								instPlayerConstell.setInstCardId(instCardId);
								instPlayerConstell.setConstellId(constellId);
								instPlayerConstell.setPills("0;0;0;0;0;0");
//								instPlayerConstell.setOperTime(DateUtil.getCurrTime());
								getInstPlayerConstellDAL().add(instPlayerConstell, 0);
								newAddInstConstell += instPlayerConstell.getId() + ";";
							}
							instPlayerCard.setInstPlayerConstells(instPlayerCard.getInstPlayerConstells() + StringUtil.noContainLastString(newAddInstConstell));
							getInstPlayerCardDAL().update(instPlayerCard, 0);
						}
					}
				}
			}
			
			long end = DateUtil.getCurrMill();
			System.out.println("用时 " + ((end - start) / 1000) + "s");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
