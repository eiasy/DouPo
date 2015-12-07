package mmo.module.gm.gui.provider.table.shop;

import mmo.module.gm.bean.BeanShopGoods;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableShopGoodsLableProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BeanShopGoods card = (BeanShopGoods) element;
		switch (columnIndex) {
			case 0: {
				return card.getGoodsId() + "";
			}
			case 1: {
				return card.getGoodsName();
			}
			case 2: {
				return card.getDayLimit() + "";
			}
			case 3: {
				return card.getTotalLimit() + "";
			}
			case 4: {
				return card.getPriceGoods() + "";
			}
			case 5: {
				return card.getPriceName() + "";
			}
			case 6: {
				return card.getPrice() + "";
			}
			case 7: {
				return card.getPriceNow() + "";
			}
			case 8: {
				return card.getOnceBuy() + "";
			}
			case 9: {
				return card.getTitle();
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