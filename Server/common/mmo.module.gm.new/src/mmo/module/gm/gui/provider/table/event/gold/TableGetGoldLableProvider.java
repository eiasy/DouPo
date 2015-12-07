package mmo.module.gm.gui.provider.table.event.gold;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mmo.common.config.charge.ChargeType;
import mmo.common.module.datacenter.bean.InstPlayerRecharge;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableGetGoldLableProvider implements ITableLabelProvider {

	private Map<Integer, String> gameMap = new HashMap<Integer, String>();

	public TableGetGoldLableProvider() {
		gameMap.put(2, "°²×¿»ì·þ");
		gameMap.put(3, "IOS");
		gameMap.put(4, "ÕÆÔÄ");
	}

	private String getGame(int gameId) {
		String game = gameMap.get(gameId);
		if (game == null) {
			return "Î´Öª£º" + gameId;
		}
		return game;
	}

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		InstPlayerRecharge game = (InstPlayerRecharge) element;
		switch (columnIndex) {
			case 0: {
				return game.getId() + "";
			}
			case 1: {
				return game.getInstPlayerId()+"";
			}
			case 2: {
				return game.getOpenId();
			}
			case 3: {
				return game.getPlayerName();
			}
			case 4: {
				String channel = ProjectCofigs.getParameter("anysdk_" + game.getChannel());
				if (channel != null) {
					return channel;
				}
				return game.getChannel();
			}
			case 5: {
				return game.getThisrmb()+"";
			}
			case 6: {
				return game.getThisamt()+ "";
			}
			case 7: {
				return game.getSaveamt() + "";
			}
			case 8: {
				return game.getPayChannel()+ "";
			}
			case 9: {
				return game.getSaveNum()+"";
			}
			case 10: {
				return game.getSource();
			}
			case 11: {
				return game.getOrderId();
			}
			case 12: {
				return game.getServerId()+"";
			}
			case 13: {
				return game.getOrderform();
			}
			case 14: {
				return game.getRoleLevel()+"";
			}
			case 15: {
				return game.getGoodsId()+"";
			}
			case 16: {
				 return ChargeType.getNote((byte)game.getCtype());
			}
			case 17: {
				return game.getMoney()+"";
			}
			case 18: {
				return game.getRechargeRecordId();
			}
			case 19: {
				return game.getOperateTime();
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