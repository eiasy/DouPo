package com.huayi.doupo.activity.luck;

import java.util.Comparator;

import com.huayi.doupo.base.model.InstLuckRank;
import com.huayi.doupo.base.util.base.DateUtil;

public class ComparatorRank implements Comparator<InstLuckRank> {
	public int compare(InstLuckRank data0, InstLuckRank data1) {
		if (data0.getRankValue() < data1.getRankValue()) {
			return 1;
		} else if (data0.getRankValue() > data1.getRankValue()) {
			return -1;
		} else {
			if (DateUtil.string2MillSecond(data0.getCountReset()) > DateUtil.string2MillSecond(data1.getCountReset())) {
				return 1;
			}else if (DateUtil.string2MillSecond(data0.getCountReset()) == DateUtil.string2MillSecond(data1.getCountReset())) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}