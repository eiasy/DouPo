package mmo.module.gui.action;


import mmo.module.gui.resource.ResourcePath;
import mmo.module.gui.resource.UIResourceManager;

import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.widgets.TreeItem;

public class TreeAction implements TreeListener {
	/**
	 * 当折叠树节点时
	 */
	public void treeCollapsed(TreeEvent e) {
		// 首先获得触发事件的TreeItem
		TreeItem item = (TreeItem) e.item;
		// 将该节点的图标设置为关闭状态
		item.setImage(UIResourceManager.getImage(e.display, ResourcePath.ICON_EDITOR + UIResourceManager.TOC_CLOSED));
	}

	/**
	 * 当展开树节点时
	 */
	public void treeExpanded(TreeEvent e) {
		TreeItem item = (TreeItem) e.item;
		item.setImage(UIResourceManager.getImage(e.display, ResourcePath.ICON_EDITOR + UIResourceManager.TOC_OPEN));
	}

}
