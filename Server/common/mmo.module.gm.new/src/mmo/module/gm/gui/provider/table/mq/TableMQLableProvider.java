package mmo.module.gm.gui.provider.table.mq;

import java.util.Date;

import mmo.common.ServerType;
import mmo.module.cache.queue.ServiceServer;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableMQLableProvider implements ITableLabelProvider {
	public static final int ACCOUNT_ID      = 0;
	public static final int USER_ID         = 1;
	public static final int USER_ID_NEW     = 2;
	public static final int USER_NAME       = 3;
	public static final int PHONE           = 4;
	public static final int EMAIL           = 5;
	public static final int STATE_DB        = 6;
	public static final int TIME_FREEZE_END = 7;
	public static final int FREEZE_DAY      = 8;
	public static final int GAME            = 9;
	public static final int CHANNEL         = 10;
	public static final int TIME_REGISTER   = 11;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ServiceServer ss = (ServiceServer) element;
		switch (columnIndex) {
			case ACCOUNT_ID: {
				return ss.getProductId() + "";
			}
			case USER_ID: {
				return ss.getProductName();
			}
			case USER_ID_NEW: {
				return ss.getAppId() + "";
			}
			case USER_NAME: {
				return ss.getAppName();
			}
			case PHONE: {
				return ServerType.getTypeName(ss.getServerType());
			}
			case EMAIL: {
				return ss.getMqName();
			}
			case STATE_DB: {
				return DateUtil.formatDate(new Date(ss.getTimeRegister()));
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