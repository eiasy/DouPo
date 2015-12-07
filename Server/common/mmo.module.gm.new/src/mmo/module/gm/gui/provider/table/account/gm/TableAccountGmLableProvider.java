package mmo.module.gm.gui.provider.table.account.gm;

import mmo.module.gm.bean.BeanGmAccount;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableAccountGmLableProvider implements ITableLabelProvider {
	public static final int ACCOUNT_ID = 0;
	public static final int USER_ID    = 1;
	public static final int TIME_ADD   = 2;
	public static final int STATE      = 3;
	public static final int NOTE       = 4;
	public static final int POWERS     = 5;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BeanGmAccount account = (BeanGmAccount) element;
		switch (columnIndex) {
			case ACCOUNT_ID: {
				return account.getId() + "";
			}
			case USER_ID: {
				return account.getUserId();
			}
			case TIME_ADD: {
				return account.getTimeAddd();
			}
			case STATE: {
				return BeanGmAccount.State.getState(account.getState());
			}
			case NOTE: {
				return account.getNote();
			}
			case POWERS: {
				return account.getPowerNote();
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