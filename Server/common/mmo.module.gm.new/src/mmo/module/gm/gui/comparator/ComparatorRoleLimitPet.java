package mmo.module.gm.gui.comparator;

import java.util.Comparator;

import mmo.module.gm.bean.BeanLimitPetData;

public class ComparatorRoleLimitPet implements Comparator<BeanLimitPetData> {
	public int compare(BeanLimitPetData data0, BeanLimitPetData data1) {
		if (data0.getRank() < data1.getRank()) {
			return 1;
		} else {
			return -1;
		}
	}
}