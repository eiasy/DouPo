package mmo.module.gm.gui.provider.table.event;

import java.util.Date;

import mmo.common.bean.bi.EventDefault;
import mmo.module.gm.gui.composite.CompositeAdminCharge;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableEventChargeLableProvider implements ITableLabelProvider {

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
				return CompositeAdminCharge.getEventName(game.getEventTag());
			}
			case 3: {
				return game.getAppTag();
			}
			case 4: {
				return game.getPlatform();
			}
			case 5: {
				return game.getServerTag();
			}
			case 6: {
				return game.getChannelTag();
			}
			case 7: {
				return game.getChannelSub();
			}
			case 8: {
				return game.getAccountId() + "";
			}
			case 9: {
				return game.getUserId();
			}
			case 10: {
				return game.getRoleId() + "";
			}
			case 11: {
				return game.getRoleName();
			}
			case 12: {
				return game.getRoleLevel() + "";
			}
			case 13: {
				return game.getVipLevel() + "";
			}
			case 14: {
				return game.getValue1string();
			}
			case 15: {
				return game.getValue2string();
			}
			case 16: {
				return game.getValue3string();
			}
			case 17: {
				return game.getValue4string();
			}
			case 18: {
				return game.getValue5string();
			}
			case 19: {
				return game.getValue6string();
			}
			case 20: {
				return game.getValue7string();
			}
			case 21: {
				return game.getValue8string();
			}
			case 22: {
				return (game.getValue2int()/100) + "";
			}
			case 23: {
				return (game.getValue3int()/100) + "";
			}
			case 24: {
				return DateUtil.formatDate(new Date(game.getTimeAdd())) ;
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