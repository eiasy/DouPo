package mmo.module.gm.gui.comparator;

import java.util.Comparator;

import mmo.module.gm.bean.BeanMoreMoneyData;

public class ComparatorRoleMoreMoney implements Comparator<BeanMoreMoneyData> {
	public int compare(BeanMoreMoneyData data0, BeanMoreMoneyData data1) {
		if (data0.getCostMoney() < data1.getCostMoney()) {
			return 1;
		} else {
			return -1;
		}
	}
}