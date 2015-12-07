package mmo.module.gui.composite;

import mmo.module.gui.DataKey;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class CComposite extends Composite implements DataKey {

	protected CComposite child;

	public CComposite(Composite parent, int style) {
		super(parent, style);
	}

	public void updateUI() {
	}

	public void updateUI(boolean isUpdate) {
	}

	public Shell getTopShell() {
		Shell topShell = null;
		if (child != null && !child.isDisposed()) {
			topShell = child.getTopShell();
		} else {
			topShell = getShell();
		}
		return topShell;
	}

	public void setUIData(Object data) {

	}

	public final static CComposite getScrolledComposite(Composite parent, int style) {
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		CComposite composite = new CComposite(scrolledComposite, style);
		scrolledComposite.setContent(composite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(750);
		scrolledComposite.setMinHeight(600);
		return composite;
	}

	public final static ScrolledComposite getScrolledComposite(Composite parent) {
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(600);
		scrolledComposite.setMinHeight(600);
		return scrolledComposite;
	}
}
