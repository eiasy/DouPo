package mmo.module.gm.gui.provider.table.event.player;

import java.util.HashMap;
import java.util.Map;

import mmo.common.config.charge.ChargeType;
import mmo.common.module.datacenter.bean.InstPlayer;
import mmo.tools.config.ProjectCofigs;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TablePlayerLableProvider implements ITableLabelProvider {

	private Map<Integer, String> gameMap = new HashMap<Integer, String>();

	public TablePlayerLableProvider() {
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
		InstPlayer game = (InstPlayer) element;
		switch (columnIndex) {
			case 0: {
				return game.getServerId() + "";
			}
			case 1: {
				String channel = ProjectCofigs.getParameter("anysdk_" + game.getChannel());
				if (channel != null) {
					return channel;
				}
				return game.getChannel();
			}
			case 2: {
				return game.getOpenId();
			}
			case 3: {
				return game.getId() + "";
			}
			case 4: {
				return game.getName();
			}
			case 5: {
				return game.getLevel() + "";
			}
			case 6: {
				return game.getGold() + "";
			}
			case 7: {
				return game.getCopper();
			}
			case 8: {
				return game.getExp() + "";
			}
			case 9: {
				return game.getEnergy() + "";
			}
			case 10: {
				return game.getMaxEnergy() + "";
			}
			case 11: {
				return game.getVigor() + "";
			}
			case 12: {
				return game.getMaxVigor() + "";
			}
			case 13: {
				return game.getCardNum() + "";
			}
			case 14: {
				return game.getMaxCardNum() + "";
			}
			case 15: {
				return game.getGuidStep();
			}
			case 16: {
				return game.getChapterId() + "";
			}
			case 17: {
				return game.getBarrierId() + "";
			}
			case 18: {
				return game.getFireGainRuleId() + "";
			}
			case 19: {
				return game.getInstPlayerFireId() == 0 ? "未装备" : "装备";
			}
			case 20: {
				return game.getVipLevel() + "";
			}
			case 21: {
				return game.getVigour() + "";
			}
			case 22: {
				return game.getCulture() + "";
			}
			case 23: {
				return game.getBarrierNum() + "";
			}
			case 24: {
				return game.getBuyEnergyNum() + "";
			}
			case 25: {
				return game.getBuyVigorNum() + "";
			}
			case 26: {
				return game.getLastBuyEnergyTime() + "";
			}
			case 27: {
				return game.getLastBuyVigorTime() + "";
			}
			case 28: {
				return game.getGuipedBarrier() + "";
			}
			case 29: {
				return game.getWashTime();
			}
			case 30: {
				return game.getDailyTashTime();
			}
			case 31: {
				return game.getHeaderCardId() + "";
			}
			case 32: {
				return game.getVipIds() + "";
			}
			case 33: {
				return game.getPullBlack() == 1 ? "拉黑" : "";
			}
			case 34: {
				return game.getIsGetFirstRechargeGift() == 1 ? "未领" : "已领";
			}
			case 35: {
				return game.getBeautyCardTime() + "";
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