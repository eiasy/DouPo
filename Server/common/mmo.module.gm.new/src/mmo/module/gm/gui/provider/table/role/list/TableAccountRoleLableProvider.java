package mmo.module.gm.gui.provider.table.role.list;

import java.util.Date;

import mmo.module.gm.bean.AccountRole;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableAccountRoleLableProvider implements ITableLabelProvider {
	public static final int ID            = 0;
	public static final int NAME          = 1;
	public static final int STATE_ONLINE  = 2;
	public static final int STATE_DB      = 3;
	public static final int SCENE         = 4;
	public static final int PROFESSION    = 5;
	public static final int LEVEL_MANSION = 6;
	public static final int LEVEL         = 7;
	public static final int EXPERIENC     = 8;
	public static final int REALM         = 9;
	public static final int LING_SHI      = 10;
	public static final int YUAN_BAO      = 11;
	public static final int LEVEL_VIP     = 12;
	public static final int TIME_CREATE   = 13;
	public static final int TIME_OFFLINE  = 14;
	public static final int TIME_FREEZE   = 15;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		AccountRole role = (AccountRole) element;
		switch (columnIndex) {
			case ID: {
				return role.getRoleId() + "";
			}
			case NAME: {
				return role.getRoleName();
			}
			case STATE_ONLINE: {
				return AccountRole.StateOnline.getStateOnline(role.getOnlineState());
			}
			case STATE_DB: {
				return AccountRole.StateDb.getStateOnline(role.getDbState());
			}
			case SCENE: {
				return role.getScene();
			}
			case PROFESSION: {
				return AccountRole.Profession.getProfessionName(role.getProfession()) + "";
			}
			case LEVEL_MANSION: {
				return role.getMansionLevel() + "";
			}
			case LEVEL: {
				return role.getLevel() + "";
			}
			case EXPERIENC: {
				return role.getExperience() + "";
			}
			case REALM: {
				return AccountRole.Realm.getName(role.getRealm());
			}
			case LING_SHI: {
				return role.getLingShi() + "";
			}
			case YUAN_BAO: {
				return role.getYuanBao() + "";
			}
			case LEVEL_VIP: {
				return role.getVipLevel() + "";
			}
			case TIME_CREATE: {
				return role.getTimeCreate();
			}
			case TIME_OFFLINE: {
				return role.getTimeOffline();
			}
			case TIME_FREEZE: {
				return DateUtil.formatDate(new Date(role.getFreeze()));
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