package mmo.module.gm.gui.provider.table.role.chat;

import mmo.module.cache.role.RoleForbidChat;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableRoleChatLableProvider implements ITableLabelProvider {
	public static final int ID   = 0;
	public static final int TIME = 1;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		RoleForbidChat role = (RoleForbidChat) element;
		switch (columnIndex) {
			case ID: {
				return role.getRoleId() + "";
			}
			case TIME: {
				return role.getTime() + "";
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