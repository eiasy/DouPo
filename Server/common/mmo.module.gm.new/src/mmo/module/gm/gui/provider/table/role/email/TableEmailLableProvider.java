package mmo.module.gm.gui.provider.table.role.email;

import java.util.Date;

import mmo.module.cache.role.RoleEmail;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableEmailLableProvider implements ITableLabelProvider {
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
	public static final int YUAN_BAO     = 10;
	public static final int LEVEL_VIP    = 11;
	public static final int TIME_GOODS_1 = 12;
	public static final int TIME_GOODS_2 = 13;
	public static final int TIME_GOODS_3 = 14;
	public static final int TIME_GOODS_4 = 15;
	public static final int TIME_GOODS_5 = 16;
	public static final int TIME_GOODS_6 = 17;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		RoleEmail role = (RoleEmail) element;
		switch (columnIndex) {
			case ID: {
				return role.getId() + "";
			}
			case NAME: {
				return role.getTargetid() + "";
			}
			case STATE_ONLINE: {
				return role.getTargetName();
			}
			case STATE_DB: {
				return role.getFromid() + "";
			}
			case SCENE: {
				return role.getFromName();
			}
			case PROFESSION: {
				return role.getTitle();
			}
			case LEVEL: {
				return role.getContent();
			}
			case EXPERIENC: {
				return role.getStateName();
			}
			case REALM: {
				return role.getPicekupName();
			}
			case LING_SHI: {
				return role.getTypeName();
			}
			case YUAN_BAO: {
				return DateUtil.formatDate(new Date(role.getCtime()));
			}
			case LEVEL_VIP: {
				return DateUtil.formatDate(new Date(role.getPtime()));
			}
			case TIME_GOODS_1: {
				if (role.getGoodsList().size() > 0) {
					return role.getGoodsList().get(0).getInfo();
				}
				return "";
			}
			case TIME_GOODS_2: {
				if (role.getGoodsList().size() > 1) {
					return role.getGoodsList().get(1).getInfo();
				}
				return "";
			}
			case TIME_GOODS_3: {
				if (role.getGoodsList().size() > 2) {
					return role.getGoodsList().get(2).getInfo();
				}
				return "";
			}
			case TIME_GOODS_4: {
				if (role.getGoodsList().size() > 3) {
					return role.getGoodsList().get(3).getInfo();
				}
				return "";
			}
			case TIME_GOODS_5: {
				if (role.getGoodsList().size() > 4) {
					return role.getGoodsList().get(4).getInfo();
				}
				return "";
			}
			case TIME_GOODS_6: {
				if (role.getGoodsList().size() > 5) {
					return role.getGoodsList().get(5).getInfo();
				}
				return "";
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