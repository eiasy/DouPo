package mmo.module.gm.gui.provider.table.event;

import java.util.Date;

import mmo.common.bean.bi.EventDefault;
import mmo.module.gm.bean.ExchangeTag;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableEventGoodsLableProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		EventDefault game = (EventDefault) element;
		switch (columnIndex) {
			case 0: {
				return game.getId() + "";
			}
			case 1: {
				return game.getEventSource();
			}
			case 2: {
				return game.getEventTag();
			}
			case 3: {
				return game.getPlatform();
			}
			case 4: {
				return game.getServerTag();
			}
			case 5: {
				return game.getChannelTag();
			}
			case 6: {
				return game.getChannelSub();
			}
			case 7: {
				return game.getAccountId() + "";
			}
			case 8: {
				return game.getUserId();
			}
			case 9: {
				return game.getRoleId() + "";
			}
			case 10: {
				return game.getRoleName();
			}
			case 11: {
				return game.getRoleLevel() + "";
			}
			case 12: {
				return game.getVipLevel() + "";
			}
			case 13: {
				return game.getValue1string();
			}
			case 14: {
				return game.getValue2string();
			}
			case 15: {
				return DateUtil.formatDate(new Date(game.getTimeAdd()));
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