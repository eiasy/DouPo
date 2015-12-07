package mmo.module.gm.gui.provider.table.role.charge;

import mmo.module.cache.role.ChargeLog;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableChargeLogLableProvider implements ITableLabelProvider {
	public static final int ID           = 0;
	public static final int NAME         = 1;
	public static final int STATE_ONLINE = 2;
	public static final int STATE_DB     = 3;
	public static final int SCENE        = 4;
	public static final int PROFESSION   = 5;
	public static final int LEVEL        = 6;
	public static final int EXPERIENC    = 7;
	public static final int REALM        = 8;
	public static final int LING_SHI     = 9;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ChargeLog role = (ChargeLog) element;
		return role.getField(columnIndex);
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