package mmo.module.gm.gui;

import java.awt.Toolkit;

import mmo.module.gm.config.TabItemConfig;
import mmo.module.gm.gui.composite.CompositeParent;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.net.GMNetManager;
import mmo.module.gui.form.CForm;
import mmo.module.gui.resource.UIResourceManager;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class GMWindow extends ApplicationWindow {
	/** 唯一实例 */
	private static GMWindow instance = null;
	/** 主窗口 */
	private CForm           form     = null;
	/*********************************************/
	private CompositeParent compositeParent;

	private GMWindow() {
		super(null);
		this.addMenuBar();
		this.addStatusLine();
	}

	/**
	 * 获取窗口管理器的唯一实例
	 * 
	 * @return
	 */
	public synchronized static GMWindow getInstance() {
		if (instance == null) {
			instance = new GMWindow();
		}
		return instance;
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("GM管理器");
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int width = screenWidth * 3 / 4;
		int height = screenHeight * 4 / 5;
		int x = (screenWidth - width) / 2;
		int y = (screenHeight - height) / 2;
		shell.setBounds(x, y, width, height);
		UIResourceManager.initResource(shell.getDisplay());
	}

	protected Control createContents(Composite parent) {
		compositeParent = new CompositeParent(parent, SWT.NONE);
		return parent;
	}

	public Shell getTopShell() {
		Shell topShell = null;
		if (form != null) {
			topShell = form.getTopShell();
		}
		if (topShell == null) {
			topShell = getShell();
		}
		return topShell;
	}

	public void switchComposite(final int compositeId) {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (compositeParent != null) {
					compositeParent.switchComposite(compositeId);
				}
			}
		});
	}

	public void updatePower() {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (compositeParent != null) {
					compositeParent.updatePower();
				}
			}
		});
	}

	public void netClosed() {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				MyDialog.openInformation("网络连接断开");
				GMWindow.getInstance().setTitle("GM管理器");
				switchComposite(CompositeParent.COMPOSITE_1_LOGIN);
				GMWindow.getInstance().getStatusLineManager().setMessage("网络连接断开");
			}
		});
	}

	protected MenuManager createMenuManager() {
		MenuManager menuBar = new MenuManager();

		MenuManager editorMenu = new MenuManager("文件(&F)");
		editorMenu.add(new ExitAction());
		editorMenu.add(new ExitLoginAction());
		editorMenu.add(new ResetPasswrodAction());

		menuBar.add(editorMenu);

		return menuBar;
	}

	class ExitAction extends Action {
		public ExitAction() {
			setText("退出");
		}

		@Override
		public void run() {
			System.exit(0);
		}
	}

	class ExitLoginAction extends Action {
		public ExitLoginAction() {
			setText("退出登录");
		}

		@Override
		public void run() {
			GMWindow.getInstance().setTitle("GM管理器");
			GMWindow.getInstance().getStatusLineManager().setMessage("未登陆");
			switchComposite(CompositeParent.COMPOSITE_1_LOGIN);
		}
	}

	class ResetPasswrodAction extends Action {
		public ResetPasswrodAction() {
			setText("修改密码");
		}

		@Override
		public void run() {
			gmSwitchTabItem(TabItemConfig.ITEM_4_RESET_PWD, GMNetManager.getNetSession());
		}
	}

	/**
	 * 刷新左侧区域
	 */
	public void updateLeftTabFolder() {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				form.updateLeftTabFolder();
			}
		});
	}

	/**
	 * 刷新右侧区域
	 */
	public void updateRightTabFolder() {
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				form.updateRightTabFolder();
			}
		});
	}

	public void gmSwitchTabItem(final int itemId) {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (compositeParent != null) {
					compositeParent.gmSwitchTabItem(itemId, null);
				}
			}
		});
	}

	public void setTitle(final String title) {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				getShell().setText(title);
			}
		});
	}
	
	public void updateStatusLineMessage(final String message) {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				GMWindow.getInstance().getStatusLineManager().setMessage(message);
			}
		});
	}

	public void gmSwitchTabItem(final int itemId, final Object data) {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (compositeParent != null) {
					compositeParent.gmSwitchTabItem(itemId, data);
				}
			}
		});
	}

	@Override
	protected StatusLineManager getStatusLineManager() {
		return super.getStatusLineManager();
	}

	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}
}
