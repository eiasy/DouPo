package mmo.module.gm.gui.provider.table.account.user;

import java.util.Date;

import mmo.module.gm.bean.BeanAccount;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableAccountUserLableProvider implements ITableLabelProvider {
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
		BeanAccount account = (BeanAccount) element;
		switch (columnIndex) {
			case ACCOUNT_ID: {
				return account.getAccountId() + "";
			}
			case USER_ID: {
				return account.getUserid();
			}
			case USER_ID_NEW: {
				return account.getReuserid();
			}
			case USER_NAME: {
				return account.getUsername();
			}
			case PHONE: {
				return account.getPhone();
			}
			case EMAIL: {
				return account.getDeviceImei();
			}
			case STATE_DB: {
				return BeanAccount.State.getState(account.getStateDb());
			}
			case TIME_FREEZE_END: {
				return DateUtil.formatDate(new Date(account.getTimeFreeze()));
			}
			case FREEZE_DAY: {
				return DateUtil.formatDate(new Date(account.getFreezeDay()));
			}
			case GAME: {
				return account.getProductId() + "";
			}
			case CHANNEL: {
				return account.getChannelId() + "";
			}
			case TIME_REGISTER: {
				return DateUtil.formatDate(new Date(account.getTimeRegister()));
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