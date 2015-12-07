package mmo.module.gm.gui.provider.table.event.order;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mmo.common.module.datacenter.bean.ChargeOrderform;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableCreateOrderLableProvider implements ITableLabelProvider {

	private Map<Integer, String> gameMap = new HashMap<Integer, String>();

	public TableCreateOrderLableProvider() {
		gameMap.put(2, "安卓混服");
		gameMap.put(3, "IOS");
		gameMap.put(4, "掌阅");
	}

	private String getGame(int gameId) {
		String game = gameMap.get(gameId);
		if (game == null) {
			return "未知：" + gameId;
		}
		return game;
	}

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ChargeOrderform game = (ChargeOrderform) element;
		switch (columnIndex) {
			case 0: {
				return game.getId() + "";
			}
			case 1: {
				return getGame(game.getGameId());
			}
			case 2: {
				return game.getServerId() + "";
			}
			case 3: {
				return game.getOrderform();
			}
			case 4: {
				String channel = ProjectCofigs.getParameter("anysdk_" + game.getChannelId());
				if (channel != null) {
					return channel;
				}
				return game.getChannelId();
			}
			case 5: {
				return game.getChannelSub();
			}
			case 6: {
				return game.getAccountId() + "";
			}
			case 7: {
				return game.getUserId() + "";
			}
			case 8: {
				return game.getRoleId() + "";
			}
			case 9: {
				return game.getRoleName();
			}
			case 10: {
				return game.getRoleLevel() + "";
			}
			case 11: {
				return game.getItemId() + "";
			}
			case 12: {
				return game.getItemName();
			}
			case 13: {
				return (game.getItemPrice() / 100) + "";
			}
			case 14: {
				return game.getItemCount() + "";
			}
			case 15: {
				return game.getStatus() == 0 ? "新单" : "已确认";
			}
			case 16: {
				return game.getDeviceOS();
			}
			case 17: {
				return game.getDeviceImei();
			}
			case 18: {
				return game.getDeviceSerial();
			}
			case 19: {
				return game.getDeviceMac();
			}
			case 20: {
				return game.getIdfa();
			}
			case 21: {
				return DateUtil.formatDate(new Date(game.getTimeCreate()));
			}
			case 22: {
				return DateUtil.formatDate(new Date(game.getTimeHandle()));
			}
			case 23: {
				return game.getHandleZoneId() + "";
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