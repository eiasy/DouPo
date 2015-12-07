package mmo.module.gui.form;

import mmo.module.gui.tab.MyTabItem;
import mmo.module.gui.tab.TabFolderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.swtdesigner.SWTResourceManager;

public class CFormII extends SashForm {
	protected Display    display         = null;
	protected CTabFolder centerTabFolder = null;
	protected CTabFolder rightTabFolder  = null;

	public CFormII(Composite parent, int style) {
		super(parent, SWT.NONE);
		display = parent.getDisplay();
		setLayout(new FillLayout());
		createTabFolder();
	}

	/**
	 * 创建选项卡窗体
	 */
	private void createTabFolder() {

		Composite center = new Composite(this, SWT.NONE);
		center.setLayout(new FillLayout());
		centerTabFolder = TabFolderFactory.getTabFolder(center);

		centerTabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));

		Composite right = new Composite(this, SWT.NONE);
		right.setLayout(new FillLayout());
		rightTabFolder = TabFolderFactory.getTabFolder(right);
		rightTabFolder.setSelectionForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		rightTabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		setWeights(new int[] { 60, 390 });
	}

	public CTabFolder getCenterTabFolder() {
		return centerTabFolder;
	}

	public CTabFolder getRightTabFolder() {
		return rightTabFolder;
	}

	public void updateCenterTabFolder() {
		MyTabItem item = (MyTabItem) centerTabFolder.getSelection();
		if (item != null) {
			item.updateItem();
		}
	}

	public void updateRightTabFolder() {
		MyTabItem item = (MyTabItem) rightTabFolder.getSelection();
		if (item != null) {
			item.updateItem();
		}
	}

	public Shell getTopShell() {
		Shell topShell = null;
		MyTabItem item = null;

		if (topShell == null) {
			item = (MyTabItem) centerTabFolder.getSelection();
			if (item != null) {
				topShell = item.getTopShell();
			}
		}

		if (topShell == null) {
			item = (MyTabItem) rightTabFolder.getSelection();
			if (item != null) {
				topShell = item.getTopShell();
			}
		}

		if (topShell == null) {
			topShell = getShell();
		}
		return topShell;
	}
}
