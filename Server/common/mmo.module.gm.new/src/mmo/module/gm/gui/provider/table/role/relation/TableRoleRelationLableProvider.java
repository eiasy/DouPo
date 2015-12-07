package mmo.module.gm.gui.provider.table.role.relation;

import java.util.Date;

import mmo.common.config.role.RoleSex;
import mmo.module.cache.role.RoleRelation;
import mmo.module.gm.bean.AccountRole;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableRoleRelationLableProvider implements ITableLabelProvider {
	public static final int ID           = 0;
	public static final int NAME         = 1;
	public static final int LEVEL        = 2;
	public static final int STATE_ONLINE = 3;
	public static final int PROFESSION   = 4;
	public static final int SEX          = 5;
	public static final int TIME         = 6;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		RoleRelation role = (RoleRelation) element;
		switch (columnIndex) {
			case ID: {
				return role.getRoleId() + "";
			}
			case NAME: {
				return role.getRoleName();
			}
			case STATE_ONLINE: {
				return AccountRole.StateOnline.getStateOnline(role.getState());
			}
			case PROFESSION: {
				return AccountRole.Profession.getProfessionName(role.getProfession()) + "";
			}
			case LEVEL: {
				return role.getLevel() + "";
			}
			case SEX: {
				return RoleSex.getSexName(role.getSex());
			}
			case TIME: {
				return DateUtil.formatDate(new Date(role.getTime()));
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