package mmo.module.gm.gui.dialog;

import mmo.module.gm.gui.GMWindow;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class MyDialog {
	public static void openError(final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.openError(GMWindow.getInstance().getTopShell(), "错误信息", message);
			}
		});
	}

	public static boolean openConfirm(final String message) {
		return MessageDialog.openConfirm(GMWindow.getInstance().getTopShell(), "确认信息", message);
	}

	public static void openInformation(final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.openInformation(GMWindow.getInstance().getTopShell(), "提示信息", message);
			}
		});
	}

	public static boolean openQuestion(final String message) {
		return MessageDialog.openQuestion(GMWindow.getInstance().getTopShell(), "询问操作", message);
	}

	public static void openWarning(final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.openWarning(GMWindow.getInstance().getTopShell(), "警告信息", message);
			}
		});
	}
}
