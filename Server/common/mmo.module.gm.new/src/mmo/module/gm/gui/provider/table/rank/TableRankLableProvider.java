package mmo.module.gm.gui.provider.table.rank;

import mmo.module.gm.bean.AccountRole;
import mmo.module.gm.bean.BeanRankData;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableRankLableProvider implements ITableLabelProvider {
	public static final int ACCOUNT_ID = 0;
	public static final int ROLE_ID    = 1;
	public static final int ROLE_NAME  = 2;
	public static final int PROFESSION = 3;
	public static final int LEVEL      = 4;
	public static final int RANK_VALUE = 5;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BeanRankData role = (BeanRankData) element;
		switch (columnIndex) {
			case ACCOUNT_ID: {
				return role.getAccountId() + "";
			}
			case ROLE_ID: {
				return role.getRoleId() + "";
			}
			case ROLE_NAME: {
				return role.getRoleName();
			}
			case PROFESSION: {
				return AccountRole.Profession.getProfessionName(role.getProfession()) + "";
			}
			case LEVEL: {
				return role.getLevel() + "";
			}
			case RANK_VALUE: {
				return role.getRankValue() + "";
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