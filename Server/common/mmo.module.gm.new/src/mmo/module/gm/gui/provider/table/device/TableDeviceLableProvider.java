package mmo.module.gm.gui.provider.table.device;

import java.util.Date;

import mmo.module.cache.device.DeviceFreeze;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableDeviceLableProvider implements ITableLabelProvider {
	public static final int ACCOUNT_ID  = 0;
	public static final int USER_ID     = 1;
	public static final int USER_ID_NEW = 2;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		DeviceFreeze df = (DeviceFreeze) element;
		switch (columnIndex) {
			case ACCOUNT_ID: {
				return df.getDeviceImei() + "";
			}
			case USER_ID: {
				return DateUtil.formatDate(new Date(df.getTimeFreeze()));
			}
			case USER_ID_NEW: {
				return DateUtil.formatDate(new Date(df.getTimeOperate()));
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