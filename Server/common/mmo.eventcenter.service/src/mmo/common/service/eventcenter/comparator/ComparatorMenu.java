package mmo.common.service.eventcenter.comparator;

import java.util.Comparator;

import mmo.common.service.eventcenter.module.AdminMenu;

public class ComparatorMenu implements Comparator<AdminMenu> {
	public int compare(AdminMenu data0, AdminMenu data1) {
		if (data0.getOrderFactor() < data1.getOrderFactor()) {
			return 1;
		} else {
			return -1;
		}
	}
}