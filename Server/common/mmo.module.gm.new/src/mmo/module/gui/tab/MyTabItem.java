package mmo.module.gui.tab;

import mmo.module.gui.DataKey;
import mmo.module.gui.composite.CComposite;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class MyTabItem extends CTabItem implements DataKey {
	protected int        id;
	protected Object     key = null;
	/** Ö÷ÈÝÆ÷ */
	protected CComposite mainComposite;

	public MyTabItem(int id, CTabFolder parent, int style) {
		super(parent, style);
		this.id = id;
		parent.setSelection(this);
	}

	public MyTabItem(int id, CTabFolder parent, int style, int index) {
		super(parent, style, index);
		this.id = id;
		parent.setSelection(this);
	}

	public int getId() {
		return id;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Object getKey() {
		return key;
	}

	public void updateItem() {

	}

	public void updateItem(boolean isUpdate) {

	}

	public void setUIData(Object data) {
		Control control = getControl();
		if (control instanceof CComposite) {
			((CComposite) control).setUIData(data);
		} else if (control instanceof ScrolledComposite) {
			ScrolledComposite sc = (ScrolledComposite) control;
			if (sc.getContent() instanceof CComposite) {
				((CComposite) sc.getContent()).setUIData(data);
			}
		}
	}

	public Shell getTopShell() {
		Shell topShell = null;
		if (mainComposite != null && !mainComposite.isDisposed()) {
			topShell = mainComposite.getTopShell();
		}
		return topShell;
	}
}
