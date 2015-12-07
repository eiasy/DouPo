package mmo.module.gm.gui.composite;

import java.util.HashMap;
import java.util.Map;

import mmo.module.gm.gui.GMWindow;
import mmo.module.gm.net.GMNetManager;
import mmo.module.gui.composite.CComposite;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.util.StringUtil;

import org.apache.mina.core.buffer.IoBuffer;
import org.eclipse.swt.widgets.Composite;

public class GmComposite extends CComposite {
	public static String[] platforms = new String[] { "", "安卓混服", "IOS", "掌阅专服" };
	public static Map<String, String> platformValue = new HashMap<String, String>();
	public final static String[] STATUS = new String[] { "开启", "关闭" };
	public final static String[] HOURS = new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
	public final static String[] MINUTES = new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };

	static {
		try {
			String source = ProjectCofigs.getParameter("platform").replace(";", "_");
			String[] array = StringUtil.splitString(source, '_');
			platforms = new String[array.length / 2 + 1];
			platforms[0] = "";
			platformValue.put("", "");
			for (int ai = 0; ai < array.length / 2; ai++) {
				platforms[ai + 1] = array[ai * 2];
				platformValue.put(array[ai * 2], array[ai * 2 + 1]);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GmComposite(Composite parent, int style) {
		super(parent, style);
	}

	public void switchTabItem(int itemId, Object data) {
		GMWindow.getInstance().gmSwitchTabItem(itemId, data);
	}

	public void switchTabItem(int itemId) {
		GMWindow.getInstance().gmSwitchTabItem(itemId);
	}

	public void sendData(IoBuffer buf, boolean showProgress) {
		GMNetManager.sendData(buf, showProgress);
	}

}
