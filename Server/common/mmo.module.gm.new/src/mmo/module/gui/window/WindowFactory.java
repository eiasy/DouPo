package mmo.module.gui.window;

import mmo.module.gui.composite.CComposite;
import mmo.module.gui.resource.ResourcePath;
import mmo.module.gui.resource.UIResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class WindowFactory {
	public static int        screenWidth       = 1024;
	public static int        screenHeight      = 768;
	public static int        offsetNumerator   = 1;
	public static int        offsetDenominator = 18;
	public static int        shellNumerator    = 2;
	public static int        shellDenominator  = 3;
	public static int        offsetX           = 80;
	public static int        offset_Y          = 80;
	public static int        shellWidth        = 800;
	public static int        shellHeight       = 600;
	private static Rectangle screenArea        = null;

	private final static void init(Display display) {
		screenArea = display.getClientArea();
		screenHeight = screenArea.height;
		screenWidth = screenArea.width;
		shellWidth = screenWidth * shellNumerator / shellDenominator;
		shellHeight = screenHeight * shellNumerator / shellDenominator;
		offset_Y = offsetX = (screenHeight > screenWidth ? screenHeight : screenWidth) * offsetNumerator / offsetDenominator;
	}

	public final static Shell getShell(Display display, String title) {
		if (screenArea == null) {
			init(display);
		}
		int width = screenWidth * 2 / 3;
		int height = screenHeight * 2 / 3;
		int x = (screenWidth - width) / 2;
		int y = (screenHeight - height) / 2;

		Shell shell = new Shell(Display.getDefault());
		shell.setText(title);
		shell.setImage(UIResourceManager.getImage(display, ResourcePath.ICON_EDITOR + UIResourceManager.EDITOR));
		shell.setBounds(x, y, width, height);
		return shell;
	}

	public final static ScrolledComposite getScrolledComposite(Composite parent) {
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		CComposite composite_17 = new CComposite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite_17);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(600);
		scrolledComposite.setMinHeight(600);

		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 2;
		gridLayout.marginHeight = 2;
		gridLayout.verticalSpacing = 2;
		gridLayout.makeColumnsEqualWidth = true;
		composite_17.setLayout(gridLayout);
		return scrolledComposite;
	}

}
