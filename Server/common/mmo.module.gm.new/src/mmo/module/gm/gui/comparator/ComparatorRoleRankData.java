package mmo.module.gm.gui.comparator;

import java.util.Comparator;

import mmo.module.gm.bean.BeanActivityRankData;

public class ComparatorRoleRankData implements Comparator<BeanActivityRankData> {
	public int compare(BeanActivityRankData data0, BeanActivityRankData data1) {
		if (data0.getPosition() > data1.getPosition()) {
			return 1;
		} else {
			return -1;
		}
	}
}