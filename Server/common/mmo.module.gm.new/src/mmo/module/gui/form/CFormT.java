package mmo.module.gui.form;

// import hy.editorclient.gui.tab.MyTabItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class CFormT extends SashForm {
	protected Display display = null;

	public CFormT(Composite parent, int style) {
		super(parent, SWT.BORDER);
		display = parent.getDisplay();
		setLayout(new FillLayout());
//		createTabFolder();
		Composite left = new Composite(this, SWT.NONE);
		left.setLayout(new FillLayout());

		SashForm right = new SashForm(this, SWT.BORDER);
		right.setOrientation(SWT.VERTICAL);
		right.setLayout(new FillLayout());

		setWeights(new int[] { 15, 85 });

		Composite up = new Composite(right, SWT.NONE);
		up.setLayout(new FillLayout());

		Composite down = new Composite(right, SWT.NONE);
		down.setLayout(new FillLayout());

		right.setWeights(new int[] { 15, 85 });
	}

	/**
	 * 创建选项卡窗体
	 */
	public void createTabFolder() {
		Composite left = new Composite(this, SWT.NONE);
		left.setLayout(new FillLayout());

		SashForm right = new SashForm(this, SWT.BORDER);
		right.setOrientation(SWT.VERTICAL);
		right.setLayout(new FillLayout());

		setWeights(new int[] { 15, 85 });

		Composite up = new Composite(right, SWT.NONE);
		up.setLayout(new FillLayout());

		Composite down = new Composite(right, SWT.NONE);
		down.setLayout(new FillLayout());

		right.setWeights(new int[] { 15, 85 });
	}

}
