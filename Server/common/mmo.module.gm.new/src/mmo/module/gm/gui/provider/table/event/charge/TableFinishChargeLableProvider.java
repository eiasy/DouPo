package mmo.module.gm.gui.provider.table.event.charge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mmo.common.config.charge.ChargeType;
import mmo.common.module.datacenter.bean.ChargeRecord;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.util.DateUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableFinishChargeLableProvider implements ITableLabelProvider {

	private Map<Integer, String> gameMap = new HashMap<Integer, String>();

	public TableFinishChargeLableProvider() {
		gameMap.put(2, "安卓混服");
		gameMap.put(3, "IOS");
		gameMap.put(4, "掌阅");
		gameMap.put(5, "qq");
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
		ChargeRecord game = (ChargeRecord) element;
		switch (columnIndex) {
			case 0: {
				return game.getId() + "";
			}
			case 1: {
				return game.getOrderId();
			}
			case 2: {
				return getGame(game.getGameId());
			}
			case 3: {
				return game.getServerId() + "";
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
				return game.getUserid() + "";
			}
			case 8: {
				return game.getRoleId() + "";
			}
			case 9: {
				return game.getRolename();
			}
			case 10: {
				return game.getRoleLevel() + "";
			}
			case 11: {
				return (game.getMoney() / 100) + "";
			}
			case 12: {
				return ChargeType.getNote(game.getCtype());
			}
			case 13: {
				switch (game.getState()) {
					case 1: {
						return "未确认";
					}
					case 2: {
						return "已确认";
					}
					default: {
						return "未知：" + game.getState();
					}
				}
			}
			case 14: {
				return DateUtil.formatDate(new Date(game.getAtime()));
			}
			case 15: {
				return DateUtil.formatDate(new Date(game.getDtime()));
			}
			case 16: {
				return game.getNote();
			}
			case 17: {
				return game.getOrderform();
			}
			case 18: {
				return game.getProxy();
			}
			case 19: {
				return DateUtil.formatDate(new Date(game.getProxyTime()));
			}
			case 20: {
				return game.getGoodsId() + "";
			}
			case 21: {
				return game.getGoodsName();
			}
			case 22: {
				return (game.getPrice() / 100) + "";
			}
			case 23: {
				return game.getGoodsCount() + "";
			}
			case 24: {
				return game.getDeviceOS();
			}
			case 25: {
				return game.getDeviceImei();
			}
			case 26: {
				return game.getDeviceSerial();
			}
			case 27: {
				return game.getDeviceMac();
			}
			case 28: {
				return game.getIdfa();
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