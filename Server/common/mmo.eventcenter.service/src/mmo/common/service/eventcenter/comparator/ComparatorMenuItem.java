package mmo.common.service.eventcenter.comparator;

import java.util.Comparator;

import mmo.common.service.eventcenter.module.AdminMenuItem;

public class ComparatorMenuItem implements Comparator<AdminMenuItem> {
	public int compare(AdminMenuItem data0, AdminMenuItem data1) {
		if (data0.getOrderFactor() < data1.getOrderFactor()) {
			return 1;
		} else {
			return -1;
		}
	}
}