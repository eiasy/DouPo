package com.huayi.doupo.activity.comparator;

import java.util.Comparator;

import com.huayi.doupo.base.model.InstRankDanta;

public class ComparatorRankDanta implements Comparator<InstRankDanta> {
	public int compare(InstRankDanta data0, InstRankDanta data1) {
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