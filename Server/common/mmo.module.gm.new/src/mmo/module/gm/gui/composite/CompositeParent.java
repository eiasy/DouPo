package mmo.module.gm.gui.composite;

import mmo.module.gm.gui.GMWindow;
import mmo.module.gm.gui.form.GMTabManager;
import mmo.module.gui.composite.CComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class CompositeParent extends CComposite {
	public static final int COMPOSITE_1_LOGIN = 1;
	public static final int COMPOSITE_2_GM    = 2;
	private CompositeLogin  login             = null;
	private GMTabManager       gmManager         = null;
	private StackLayout     stackLayout       = new StackLayout();
	private Composite       compositeStack;

	public CompositeParent(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		compositeStack = new Composite(this, SWT.NONE);

		login = new CompositeLogin(compositeStack, SWT.SMOOTH);
		gmManager = new GMTabManager(compositeStack, SWT.SMOOTH);
		stackLayout.topControl = login;
		compositeStack.setLayout(stackLayout);
		compositeStack.layout();
		GMWindow.getInstance().updateStatusLineMessage("Î´µÇÂ¼");
	}

	public void updatePower() {
		gmManager.updatePower();
	}

	public void switchComposite(int compositeId) {
		switch (compositeId) {
			case COMPOSITE_1_LOGIN: {
				if (login == null) {
					login = new CompositeLogin(this, SWT.SMOOTH);
				}
				stackLayout.topControl = login;
				break;
			}
			case COMPOSITE_2_GM: {
				gmManager = new GMTabManager(compositeStack, SWT.SMOOTH);
				stackLayout.topControl = gmManager;
				break;
			}
		}
		compositeStack.layout();
		this.layout();
	}

	public void gmSwitchTabItem(int itemId, Object data) {
		if (gmManager != null) {
			gmManager.switchTabItem(itemId, data);
		}
	}

}
