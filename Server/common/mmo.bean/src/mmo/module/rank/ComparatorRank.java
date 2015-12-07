package mmo.module.rank;

import java.util.Comparator;

import mmo.module.rank.bean.RankData;

public class ComparatorRank implements Comparator<RankData> {
	public int compare(RankData data0, RankData data1) {
		if (data0.getRankValue() < data1.getRankValue()) {
			return 1;
		} else {
			return -1;
		}
	}
}