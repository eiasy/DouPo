package mmo.module.gui.composite;

import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public class BaseWindow extends Window {

	/**
	 * @wbp.parser.constructor
	 */
	public BaseWindow(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	public BaseWindow(IShellProvider shellProvider) {
		super(shellProvider);
	}
}
