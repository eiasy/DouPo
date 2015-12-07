package mmo.module.gui.tab;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;

public class TabFolderFactory {
	/**
	 * 
	 * @param parent
	 * @return
	 */
	public static final CTabFolder getTabFolder(final Composite parent) {
		// 创建自定义选项卡对象
		final CTabFolder folder = new CTabFolder(parent, SWT.BORDER);

		// 设置选项卡的布局，通过布局的设置呈现出最大化和最小化的外观
		// folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		// 设置复杂的选项卡，也就是带有圆角的选项卡标签
		folder.setSimple(false);
		// 设置未选中标签，图标和关闭按钮的状态
		folder.setUnselectedImageVisible(true);
		folder.setUnselectedCloseVisible(true);
		// 设置前景色和背景色
		folder.setSelectionForeground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		folder.setSelectionBackground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
		// // 显示最大化和最小化按钮
		// folder.setMinimizeVisible(true);
		// folder.setMaximizeVisible(true);
		// 注册选项卡事件
		// folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
		// // 当单击最小化按钮时触发的事件
		// public void minimize(CTabFolderEvent event) {
		// // 设置选项卡的状态为最小化，选项卡的状态决定显示在右上角的窗口按钮
		// folder.setMinimized(true);
		// // 改变选项卡的布局，呈现最小化状态
		// folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		// // 刷新布局，否则重新设置的布局将不起作用
		// parent.layout(true);
		// }
		//
		// // 当单击最大化按钮时触发的事件
		// public void maximize(CTabFolderEvent event) {
		// folder.setMaximized(true);
		// folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		// parent.layout(true);
		// }
		//
		// // 当单击还原按钮时触发的事件
		// public void restore(CTabFolderEvent event) {
		// folder.setMinimized(false);
		// folder.setMaximized(false);
		// folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		// parent.layout(true);
		// }
		// });
		return folder;
	}

}
