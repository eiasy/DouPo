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

public class CForm extends SashForm {
	protected Display    display        = null;
	protected CTabFolder leftTabFolder  = null;
	protected CTabFolder rightTabFolder = null;

	public CForm(Composite parent, int style) {
		super(parent, SWT.NONE);
		display = parent.getDisplay();
		setLayout(new FillLayout());
		createTabFolder();
	}

	/**
	 * 创建选项卡窗体
	 */
	private void createTabFolder() {
		Composite left = new Composite(this, SWT.NONE);
		left.setLayout(new FillLayout());
		leftTabFolder = TabFolderFactory.getTabFolder(left);
		leftTabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));

		Composite right = new Composite(this, SWT.NONE);
		right.setLayout(new FillLayout());
		rightTabFolder = TabFolderFactory.getTabFolder(right);
		rightTabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		setWeights(new int[] {60, 387});
	}

	public CTabFolder getLeftTabFolder() {
		return leftTabFolder;
	}

	public CTabFolder getRightTabFolder() {
		return rightTabFolder;
	}

	public void updateLeftTabFolder() {
		MyTabItem item = (MyTabItem) leftTabFolder.getSelection();
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
		MyTabItem item = (MyTabItem) leftTabFolder.getSelection();
		if (item != null) {
			topShell = item.getTopShell();
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
