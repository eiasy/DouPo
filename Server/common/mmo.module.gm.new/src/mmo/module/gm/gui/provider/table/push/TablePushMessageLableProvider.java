package mmo.module.gm.gui.provider.table.push;

import mmo.module.gm.bean.BeanPushMessage;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TablePushMessageLableProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BeanPushMessage pm = (BeanPushMessage) element;
		switch (columnIndex) {
			case 0: {
				return pm.getId() + "";
			}
			case 1: {
				return pm.getTitle();
			}
			case 2: {
				return pm.getContent();
			}
			case 3: {
				return pm.getPushTime();
			}
			case 4: {
				return pm.getPushOffset() + "";
			}
			case 5: {
				return pm.getChannel();
			}
			case 6: {
				return pm.getTarget();
			}
			case 7: {
				return pm.getStatus() == 0 ? "¿ªÆô" : "¹Ø±Õ";
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