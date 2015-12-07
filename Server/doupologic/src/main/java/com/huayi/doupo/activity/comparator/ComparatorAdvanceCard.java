package com.huayi.doupo.activity.comparator;

import java.util.Comparator;

import com.huayi.doupo.base.model.DictAdvance;

public class ComparatorAdvanceCard implements Comparator<DictAdvance> {
	public int compare(DictAdvance data0, DictAdvance data1) {
		if (data0.getQualityId() < data1.getQualityId()) {
			return -1;
		} else if (data0.getQualityId() > data1.getQualityId()) {
			return 1;
		} else {
			if (data0.getStarLevelId() > data1.getStarLevelId()) {
				return 1;
			} else if (data0.getStarLevelId() == data1.getStarLevelId()) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}