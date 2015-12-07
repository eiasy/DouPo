package mmo.module.gui.tree;

import mmo.module.gui.action.TreeAction;
import mmo.module.gui.resource.ResourcePath;
import mmo.module.gui.resource.UIResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeUtil {

	/**
	 * 创建一个树
	 * 
	 * @param parent
	 * @return
	 */
	public static Tree getTree(Composite parent) {
		final Tree tree = new Tree(parent, SWT.BORDER | SWT.SINGLE);
		// 为树注册树监听事件
		tree.addTreeListener(new TreeAction());
		return tree;
	}

	/**
	 * 设置树图标的方法
	 * 
	 * @param tree
	 */
	public static void convertImage(Tree tree) {
		// 这里假设只有一个根节点
		TreeItem[] items = tree.getItems();
		int length = items.length;
		for (int i = 0; i < length; i++) {
			UIResourceManager.bindID2Widget(items[i]);
			// 首先根据根节点的状态设置图标
			if (items[i].getExpanded()) {// 如果该节点为展开状态
				items[i].setImage(UIResourceManager.getImage(tree.getDisplay(), ResourcePath.ICON_EDITOR + UIResourceManager.TOC_OPEN));
			} else { // 否则，如果为折叠状态
				items[i].setImage(UIResourceManager.getImage(tree.getDisplay(), ResourcePath.ICON_EDITOR + UIResourceManager.TOC_CLOSED));
			}
			// 设置该根节点的图标
			setChildImage(items[i]);
		}
	}

	/**
	 * 设置一个节点的方法，该方法非常重要，要理解该方法的递归用法
	 * 
	 * @param item可以把单独看作是树中的某一个TreeItem
	 */
	private static void setChildImage(TreeItem item) {
		TreeItem[] items = item.getItems();// 首先获得该TreeItem的所有子TreeItem
		for (int i = 0; i < items.length; i++) {// 循环每一个TreeItem
			UIResourceManager.bindID2Widget(items[i]);
			if (items[i].getItems().length == 0) {// 如果这个TreeItem下没有子孙
				items[i].setImage(UIResourceManager.getImage(item.getDisplay(), ResourcePath.ICON_EDITOR + UIResourceManager.TOPIC));
			} else {// 如果这个TreeItem有多个子孙
				if (items[i].getExpanded()) {// 如果这个TreeItem是展开状态，则设置展开的图片
					items[i].setImage(UIResourceManager.getImage(item.getDisplay(), ResourcePath.ICON_EDITOR + UIResourceManager.TOC_OPEN));
				} else {// 否则，则设置折叠的图片
					items[i].setImage(UIResourceManager.getImage(item.getDisplay(), ResourcePath.ICON_EDITOR + UIResourceManager.TOC_CLOSED));
				}
				setChildImage(items[i]);// 要为该TreeItem得子孙设置图标，递归调用setChildImage方法
			}
		}
	}

	public static Tree initSceneTree(Composite parent) {
		return null;
	}

	// 设置子树的子孙数据，递归调用该方法
	// private static void setDirectory(File file, TreeItem parent) {
	// File[] files = file.listFiles();
	// for (int i = 0; i < files.length; i++) {
	// TreeItem item = new TreeItem(parent, SWT.NONE);
	// item.setData(Constants.TREE_CATE, Constants.TREE_RESOURCE);
	// item.setText(files[i].getName());
	// if (files[i].isDirectory()) {
	// setDirectory(files[i], item);
	// }
	// }
	// }
}
