package mmo.module.gm.gui.provider.table.card;

import mmo.module.gm.bean.BeanCardItem;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TablePickCardLableProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BeanCardItem card = (BeanCardItem) element;
		switch (columnIndex) {
			case 0: {
				return card.getGoodsId() + "";
			}
			case 1: {
				return card.getGoodsName();
			}
			case 2: {
				return card.getWeight() + "";
			}
			case 3: {
				return card.getCountSub() + " ~ " + card.getCountSup();
			}
			default: {
				return "";
			}
		}
	}

	@Override
	public void addListener(ILabelProviderListener arg0) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {

	}

}