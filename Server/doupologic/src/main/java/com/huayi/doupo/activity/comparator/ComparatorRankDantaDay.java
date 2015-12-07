package com.huayi.doupo.activity.comparator;

import java.util.Comparator;

import com.huayi.doupo.base.model.InstRankDantaDay;

public class ComparatorRankDantaDay implements Comparator<InstRankDantaDay> {
	public int compare(InstRankDantaDay data0, InstRankDantaDay data1) {
		if (data0.getMaxLayer() < data1.getMaxLayer()) {
			return 1;
		} else if (data0.getMaxLayer() > data1.getMaxLayer()) {
			return -1;
		} else {
			if (data0.getMedalCount() > data1.getMedalCount()) {
				return 1;
			} else if (data0.getMedalCount() == data1.getMedalCount()) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}