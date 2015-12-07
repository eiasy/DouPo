package mmo.module.gm.gui.provider.table.paomadeng;

import java.util.Date;

import mmo.module.gm.bean.BeanPaoMaDeng;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TablePaoMaDengLableProvider implements ITableLabelProvider {
	public static final int ID          = 0;
	public static final int TIME_START  = 1;
	public static final int TIME_OFFSET = 2;
	public static final int COUNT       = 3;
	public static final int TIME_UPDATE = 4;
	public static final int CONTENT     = 5;

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BeanPaoMaDeng paoMaDeng = (BeanPaoMaDeng) element;
		switch (columnIndex) {
			case 0: {
				return paoMaDeng.getId() + "";
			}

			case 1: {
				return DateUtil.formatDate(new Date(paoMaDeng.getTimeStart()));
			}
			case 2: {
				return DateUtil.formatDate(new Date(paoMaDeng.getTimeStop()));
			}
			case 3: {
				return paoMaDeng.getHourStart();
			}
			case 4: {
				return paoMaDeng.getHourStop();
			}
			case 5: {
				return paoMaDeng.getOffsetSecond() + "";
			}
			case 6: {
				return paoMaDeng.getMessage();
			}
			case 7: {
				return DateUtil.formatDate(new Date(paoMaDeng.getTimeNext()));
			}
			case 8: {
				return DateUtil.formatDate(new Date(paoMaDeng.getTimeEffectStart()));
			}
			case 9: {
				return DateUtil.formatDate(new Date(paoMaDeng.getTimeEffectStop()));
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