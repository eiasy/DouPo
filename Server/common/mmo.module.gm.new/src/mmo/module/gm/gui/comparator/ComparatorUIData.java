package mmo.module.gm.gui.comparator;

import java.util.Comparator;

import mmo.module.gm.bean.UIData;

public class ComparatorUIData implements Comparator<UIData> {
	public int compare(UIData arg0, UIData arg1) {
		UIData data0 = (UIData) arg0;
		UIData data1 = (UIData) arg1;
		if (data0.getCompareValue() > data1.getCompareValue()) {
			return 1;
		} else {
			return -1;
		}
	}
}