package mmo.module.gui.form;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class CFormT0 extends SashForm {
	protected Display display = null;

	public CFormT0(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setOrientation(SWT.VERTICAL);
		display = parent.getDisplay();
		setLayout(new FillLayout());
		SashForm top = new SashForm(this, SWT.BORDER);
		top.setOrientation(SWT.HORIZONTAL);
		top.setLayout(new FillLayout());
		Composite bottom = new Composite(this, SWT.NONE);
		bottom.setLayout(new FillLayout());

		Composite left = new Composite(top, SWT.NONE);
		left.setLayout(new FillLayout());

		Composite right = new Composite(top, SWT.NONE);
		right.setLayout(new FillLayout());

		top.setWeights(new int[] { 15, 85 });
		setWeights(new int[] { 141, 148 });
	}

}
