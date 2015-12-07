package mmo.module.gm.gui.provider.table.event.appconfig;

import java.util.HashMap;
import java.util.Map;

import mmo.common.config.charge.ChargeType;
import mmo.common.module.datacenter.bean.AppConfigs;
import mmo.common.module.datacenter.bean.InstPlayerRecharge;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.util.StringUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableAppConfigLableProvider implements ITableLabelProvider {

	private Map<Integer, String> gameMap = new HashMap<Integer, String>();

	public TableAppConfigLableProvider() {
		try {
			String source = ProjectCofigs.getParameter("platform").replace(";", "_");
			String[] array = StringUtil.splitString(source, '_');
			for (int ai = 0; ai < array.length / 2; ai++) {
				gameMap.put(Integer.parseInt(array[ai * 2 + 1]), array[ai * 2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		AppConfigs game = (AppConfigs) element;
		switch (columnIndex) {
			case 0: {
				return game.getId() + "";
			}
			case 1: {
				return getGame(game.getProjectId());
			}
			case 2: {
				return game.getAppId()+"";
			}
			case 3: {
				return game.getAppName();
			}
			case 4: {
				return game.getDbName();
			}
			case 5: {
				return game.getDbIp();
			}
			case 6: {
				return game.getDbPort()+ "";
			}
			case 7: {
				return game.getDbUser();
			}
			case 8: {
				return game.getDbPass();
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