package mmo.module.gm.gui.progress;

import java.util.HashMap;
import java.util.Map;

import mmo.common.protocol.manager.ManagerProtocol;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;

public class ProgressFrame {
	private static Map<Integer, ProgressFrame> progresses = new HashMap<Integer, ProgressFrame>();
	private ProgressBar                        progressBar;
	private Composite                          compositeDetail;
	private Label                              infoLabel;
	private Text                               messageText;
	private int                                width      = 480;
	private int                                height0    = 120;
	private int                                height1    = 250;
	private int                                height     = 0;
	private Shell                              shell;
	private Button                             confirmBtn;
	private Button                             showMessage;

	private ProgressFrame(String msg) {
		shell = new Shell(Display.getDefault(), SWT.MIN | SWT.MAX | SWT.RESIZE | SWT.TITLE | SWT.APPLICATION_MODAL);
		shell.setLayout(new FillLayout());
		Composite composite = new Composite(shell, SWT.NULL);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 2;
		gridLayout.marginHeight = 2;
		gridLayout.verticalSpacing = 2;
		gridLayout.makeColumnsEqualWidth = true;
		composite.setLayout(gridLayout);

		Composite compositeInfo = new Composite(composite, SWT.NONE);
		compositeInfo.setLayout(new RowLayout(SWT.HORIZONTAL));
		compositeInfo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		infoLabel = new Label(compositeInfo, SWT.WRAP);
		infoLabel.setLayoutData(new RowData(381, SWT.DEFAULT));
		infoLabel.setText(msg);
		progressBar = new ProgressBar(composite, SWT.SMOOTH | SWT.INDETERMINATE);
		progressBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);

		compositeDetail = new Composite(composite, SWT.NONE);
		compositeDetail.setLayout(new FillLayout(SWT.HORIZONTAL));
		compositeDetail.setLayoutData(new GridData(GridData.FILL_BOTH));
		compositeDetail.setVisible(false);
		messageText = new Text(compositeDetail, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		messageText.setEditable(false);
		Composite compositeOperate = new Composite(composite, SWT.NONE);
		RowLayout rl_compositeOperate = new RowLayout(SWT.HORIZONTAL);
		rl_compositeOperate.justify = true;
		compositeOperate.setLayout(rl_compositeOperate);
		compositeOperate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		showMessage = new Button(compositeOperate, SWT.NONE);
		showMessage.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (compositeDetail.isVisible()) {
					showMessage(false);
				} else {
					showMessage(true);
				}
			}
		});
		showMessage.setText("详情 >>");

		confirmBtn = new Button(compositeOperate, SWT.NONE);
		confirmBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		confirmBtn.setEnabled(false);
		confirmBtn.setText(" 确  定 ");
		height = height0;
		Rectangle rec = Display.getDefault().getClientArea();
		shell.setBounds(rec.x + (rec.width - width) / 2, rec.y + (rec.height - height) / 2, width, height);
		shell.setText("进度条");
		shell.open();
	}

	private ProgressFrame() {
		this("请稍等……");
	}

	private void showMessage(boolean show) {
		if (show) {
			showMessage.setText("详情 <<");
			height = height1;
			shell.setSize(width, height);
			compositeDetail.setVisible(show);
		} else {
			showMessage.setText("详情 >>");
			height = height0;
			shell.setSize(width, height);
			compositeDetail.setVisible(show);
		}
	}

	public void updateInfo(String info) {
		infoLabel.setText(info);
	}

	public void updateMessage(String message) {
		messageText.setText(message);
	}

	private void dispose(boolean dispose, String message) {
		if (dispose) {
			shell.dispose();
		} else {
			shell.setText("操作结果");
			infoLabel.setText("系统提示：");
			progressBar.dispose();
			messageText.setText(message);
			showMessage(true);
			confirmBtn.setEnabled(true);
		}
	}

	private void dispose(boolean dispose, byte msgLevel, String message) {
		if (dispose) {
			shell.dispose();
		} else {
			shell.setText("操作结果");
			progressBar.dispose();
			messageText.setText(message);
			switch (msgLevel) {
				case ManagerProtocol.MsgLevel.ERROR: {
					messageText.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
					infoLabel.setText("错误信息：");
					break;
				}
				case ManagerProtocol.MsgLevel.WARN: {
					messageText.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
					infoLabel.setText("警告信息：");
					break;
				}
				case ManagerProtocol.MsgLevel.INFO: {
					infoLabel.setText("系统提示：");
					break;
				}
			}
			showMessage(true);
			confirmBtn.setEnabled(true);
		}
	}

	public static final void show(final int validateCode) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				progresses.put(validateCode, new ProgressFrame());
			}
		});
	}

	public static final void show(final int validateCode, final String msg) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				progresses.put(validateCode, new ProgressFrame(msg));
			}
		});
	}

	public static final void validateCode(final int validateCode, final boolean dispose, final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ProgressFrame progress = progresses.get(validateCode);
				if (progress != null) {
					progress.dispose(dispose, message);
				}
			}
		});
	}

	public static final void validateCode(final int validateCode, final boolean dispose, final byte msgLevel, final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ProgressFrame progress = progresses.get(validateCode);
				if (progress != null) {
					progress.dispose(dispose, msgLevel, message);
				}
			}
		});
	}

	public static final void validateCode(final int validateCode) {
		validateCode(validateCode, true, null);
	}
}
