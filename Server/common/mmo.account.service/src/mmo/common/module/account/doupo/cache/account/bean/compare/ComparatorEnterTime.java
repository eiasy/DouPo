package mmo.common.module.account.doupo.cache.account.bean.compare;

import java.util.Comparator;

import mmo.common.module.account.doupo.cache.account.bean.ServerEnterTime;

public class ComparatorEnterTime implements Comparator<ServerEnterTime> {
	public int compare(ServerEnterTime data0, ServerEnterTime data1) {
		if (data0.getTime() < data1.getTime()) {
			return 1;
		} else {
			return -1;
		}
	}
}