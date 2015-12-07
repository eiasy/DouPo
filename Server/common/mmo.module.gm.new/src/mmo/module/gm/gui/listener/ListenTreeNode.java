package mmo.module.gm.gui.listener;

import mmo.common.protocol.command.GmPower;
import mmo.module.gm.gui.GMWindow;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class ListenTreeNode extends SelectionAdapter {
	@Override
	public void widgetSelected(SelectionEvent e) {
		Object source = e.getSource();
		if (source != null && source instanceof Tree) {
			Tree tree = (Tree) source;
			TreeItem[] selected = tree.getSelection();
			for (TreeItem ti : selected) {
				GMWindow.getInstance().gmSwitchTabItem(GmPower.getMenuItemId(ti.getText()));
				// switch (GmPower.getMenuItemId(ti.getText())) {
				// case TabItemConfig.ITEM_1_ROLE_INFO: {
				// GMWindow.getInstance().gmSwitchTabItem(TabItemConfig.ITEM_1_ROLE_INFO);
				// break;
				// }
				// case TabItemConfig.ITEM_2_ITEM_INFO: {
				// GMWindow.getInstance().gmSwitchTabItem(TabItemConfig.ITEM_2_ITEM_INFO);
				// break;
				// }
				// case TabItemConfig.ITEM_3_CHARGE_CREATE_ORDER: {
				// GMWindow.getInstance().gmSwitchTabItem(TabItemConfig.ITEM_3_CHARGE_CREATE_ORDER);
				// break;
				// }
				// }
			}
		}
	}
}
