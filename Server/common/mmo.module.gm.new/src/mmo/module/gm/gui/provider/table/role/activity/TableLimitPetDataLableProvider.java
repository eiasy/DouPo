package mmo.module.gm.gui.provider.table.role.activity;

import java.util.Date;

import mmo.common.config.role.RoleProfession;
import mmo.module.gm.bean.BeanLimitPetData;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableLimitPetDataLableProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BeanLimitPetData data = (BeanLimitPetData) element;
		switch (columnIndex) {
			case 0: {
				return data.getRoleId() + "";
			}
			case 1: {
				return data.getRoleName();
			}
			case 2: {
				return RoleProfession.getProfessionName(data.getProfession());
			}
			case 3: {
				return data.getRank() + "";
			}
			case 4: {
				return data.getPosition() + "";
			}
			case 5: {
				return data.getFreeCount() + "";
			}
			case 6: {
				return DateUtil.formatDate(new Date(data.getFreeTime()));
			}
			case 7: {
				return data.getExtraCount() + "";
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