package mmo.module.gm.gui.provider.table.intaward;

import mmo.common.config.role.RoleProfession;
import mmo.module.gm.bean.BeanAwardItem;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableIntAwardLableProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BeanAwardItem card = (BeanAwardItem) element;
		switch (columnIndex) {
			case 0: {
				return card.getGoodsName();
			}
			case 1: {
				return card.getCount() + "";
			}
			case 2: {
				return card.getProfession() == 0 ? "ȫְҵ" : RoleProfession.getProfessionName(card.getProfession());
			}
			case 3: {
				return card.getPrice() + "";
			}
			case 4: {
				return card.getPriceNow() + "";
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