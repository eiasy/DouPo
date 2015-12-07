package mmo.module.gm.gui.composite;

import java.util.List;

import mmo.common.protocol.command.ProGmClient_17000;
import mmo.module.gm.bean.BeanAccount;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.provider.table.account.user.TableAccountUserContentProvider;
import mmo.module.gm.gui.provider.table.account.user.TableAccountUserLableProvider;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;

import org.apache.mina.core.buffer.IoBuffer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class CompositeAccount extends GmComposite {
	private static final String[] loginTypes   = { "无限制", "特权账号" };
	private static final String[] operateTypes = { "添加", "移除" };
	private Text                  textAccountSelect;
	private Table                 table;
	private Text                  textPwdAccount;
	private Text                  textPwd_1;
	private Text                  textPwd_2;
	private Text                  textFreezeAccount;
	private Text                  textFreezeDay;
	private TableViewer           tableViewer;
	private Combo                 comboLimit;
	private Text                  text;
	private Combo                 comboOperate;
	private StyledText            textMessage;

	public CompositeAccount(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		Group group_5 = new Group(this, SWT.NONE);
		group_5.setLayout(new GridLayout(1, false));
		group_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group_5.setText("\u7279\u6743\u8D26\u53F7");

		Group group_6 = new Group(group_5, SWT.NONE);
		group_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group_6.setText("\u767B\u5F55\u9650\u5236");
		group_6.setLayout(new GridLayout(1, false));

		Composite composite_10 = new Composite(group_6, SWT.NONE);
		composite_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_10.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label label_1 = new Label(composite_10, SWT.NONE);
		label_1.setText("\u9650\u5236\u7C7B\u578B\uFF1A");

		comboLimit = new Combo(composite_10, SWT.READ_ONLY);
		comboLimit.setItems(loginTypes);
		comboLimit.select(0);

		Button button_6 = new Button(composite_10, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = textMessage.getText().trim();
				if (text.length() < 1) {
					MyDialog.openInformation("请输入提示信息");
					return;
				}
				if (MyDialog.openQuestion("是否把登录限制修改【" + comboLimit.getText() + "】?")) {
					if (MyDialog.openQuestion("是否把登录限制修改【" + comboLimit.getText() + "】?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17066_LOGIN_LIMIT);
						buf.putBoolean(comboLimit.getSelectionIndex() == 1);
						buf.putString(text);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_LOGIN_LIMIT, GMNetManager.getGmUserId(), comboLimit.getText());
					}
				}
			}
		});
		button_6.setText(" \u66F4 \u65B0 ");

		Button button_8 = new Button(composite_10, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17072_LOAD_ACCOUNT_UNLIMIT);
				sendData(buf, true);
				LoggerDevelop.gm(GmOperate.CLIENT_ACCONT_LIMIT, GMNetManager.getGmUserId(), "");
			}
		});
		button_8.setText("\u52A0\u8F7D\u7279\u6743\u8D26\u53F7");

		Button button_9 = new Button(composite_10, SWT.NONE);
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17077_LOAD_ACCOUNT_TEST);
				sendData(buf, true);
				LoggerDevelop.gm(GmOperate.CLIENT_ACCONT_TEST, GMNetManager.getGmUserId(), "");
			}
		});
		button_9.setText("\u52A0\u8F7D\u6D4B\u8BD5\u8D26\u53F7");

		Composite composite_12 = new Composite(group_6, SWT.NONE);
		composite_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_12.setLayout(new GridLayout(2, false));

		Label label_5 = new Label(composite_12, SWT.NONE);
		label_5.setText("\u63D0\u793A\u4FE1\u606F\uFF1A");

		textMessage = new StyledText(composite_12, SWT.BORDER | SWT.FULL_SELECTION | SWT.WRAP);
		GridData gd_textMessage = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textMessage.heightHint = 66;
		textMessage.setLayoutData(gd_textMessage);

		Composite composite_2 = new Composite(group_5, SWT.NONE);
		composite_2.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_6 = new Composite(group_5, SWT.NONE);
		composite_6.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_11 = new Composite(composite_6, SWT.NONE);
		composite_11.setLayout(null);

		Label label_2 = new Label(composite_11, SWT.NONE);
		label_2.setBounds(3, 3, 60, 17);
		label_2.setText("\u7279\u6743\u8D26\u53F7\uFF1A");

		comboOperate = new Combo(composite_11, SWT.READ_ONLY);
		comboOperate.setBounds(66, 3, 80, 25);
		comboOperate.setItems(operateTypes);
		comboOperate.select(0);

		text = new Text(composite_11, SWT.BORDER);
		text.setBounds(150, 3, 150, 23);

		Button button_7 = new Button(composite_6, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String userid = text.getText().trim();
				if (userid.length() < 1) {
					MyDialog.openInformation("请输入特权账号");
					return;
				}
				if (MyDialog.openQuestion("是否 <" + comboOperate.getText() + "> 特权账号【" + userid + "】?")) {
					if (MyDialog.openQuestion("是否 <" + comboOperate.getText() + "> 特权账号【" + userid + "】?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17067_SPECIAL_ACCOUNT);
						buf.putBoolean(comboOperate.getSelectionIndex() == 0);
						buf.putString(userid);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_SPECIAL_ACCOUNT, GMNetManager.getGmUserId(), userid);
					}
				}
			}
		});
		button_7.setText(" \u63D0 \u4EA4 ");

		Group group_2 = new Group(this, SWT.NONE);
		group_2.setLayout(new RowLayout(SWT.HORIZONTAL));
		group_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group_2.setText("\u91CD\u7F6E\u5BC6\u7801");

		Composite composite_3 = new Composite(group_2, SWT.NONE);
		composite_3.setLayout(null);

		Label lblid_1 = new Label(composite_3, SWT.NONE);
		lblid_1.setBounds(3, 3, 49, 17);
		lblid_1.setText("\u8D26\u53F7ID\uFF1A");

		textPwdAccount = new Text(composite_3, SWT.BORDER);
		textPwdAccount.setBounds(55, 3, 120, 23);

		Composite composite_4 = new Composite(group_2, SWT.NONE);
		composite_4.setLayout(null);

		Label label_3 = new Label(composite_4, SWT.NONE);
		label_3.setBounds(3, 3, 48, 17);
		label_3.setText("\u65B0\u5BC6\u7801\uFF1A");

		textPwd_1 = new Text(composite_4, SWT.BORDER);
		textPwd_1.setBounds(54, 3, 120, 23);

		Composite composite_5 = new Composite(group_2, SWT.NONE);
		composite_5.setLayout(null);

		Label label_4 = new Label(composite_5, SWT.NONE);
		label_4.setBounds(3, 3, 60, 17);
		label_4.setText("\u5BC6\u7801\u786E\u8BA4\uFF1A");

		textPwd_2 = new Text(composite_5, SWT.BORDER);
		textPwd_2.setBounds(66, 3, 120, 23);

		Button button_1 = new Button(group_2, SWT.NONE);
		button_1.addSelectionListener(new ListenerPwd());
		button_1.setText("\u63D0\u4EA4");

		Button button_4 = new Button(group_2, SWT.NONE);
		button_4.addSelectionListener(new ListenerPwdReset());
		button_4.setText("\u91CD\u7F6E");

		Group group_3 = new Group(this, SWT.NONE);
		group_3.setLayout(new RowLayout(SWT.HORIZONTAL));
		group_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group_3.setText("\u51BB\u7ED3\u8D26\u53F7");

		Composite composite_7 = new Composite(group_3, SWT.NONE);
		composite_7.setLayout(null);

		Label lblid = new Label(composite_7, SWT.NONE);
		lblid.setBounds(3, 3, 49, 17);
		lblid.setText("\u8D26\u53F7ID\uFF1A");

		textFreezeAccount = new Text(composite_7, SWT.BORDER);
		textFreezeAccount.setBounds(55, 3, 120, 23);

		Composite composite_8 = new Composite(group_3, SWT.NONE);
		composite_8.setLayout(null);

		Label label_7 = new Label(composite_8, SWT.NONE);
		label_7.setBounds(3, 3, 36, 17);
		label_7.setText("\u65F6\u957F\uFF1A");

		textFreezeDay = new Text(composite_8, SWT.BORDER);
		textFreezeDay.setBounds(42, 3, 73, 23);

		Label label_8 = new Label(composite_8, SWT.NONE);
		label_8.setBounds(118, 3, 12, 17);
		label_8.setText("\u5929");

		Button button_2 = new Button(group_3, SWT.NONE);
		button_2.addSelectionListener(new ListenerFreeze());
		button_2.setText("\u63D0\u4EA4");

		Button button_3 = new Button(group_3, SWT.NONE);
		button_3.addSelectionListener(new ListenerFreezeReset());
		button_3.setText("\u91CD\u7F6E");

		Group group_4 = new Group(this, SWT.NONE);
		group_4.setText("\u8D26\u53F7\u5217\u8868");
		group_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group_4.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(group_4, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group group = new Group(composite, SWT.NONE);
		group.setLayoutData(new RowData(SWT.DEFAULT, 30));
		group.setText("\u666E\u901A\u67E5\u8BE2");
		group.setLayout(null);

		Label label = new Label(group, SWT.NONE);
		label.setBounds(6, 20, 36, 17);
		label.setText("\u8D26\u53F7\uFF1A");

		textAccountSelect = new Text(group, SWT.BORDER);
		textAccountSelect.setBounds(45, 20, 125, 23);

		Button button = new Button(group, SWT.NONE);
		button.setBounds(173, 20, 36, 27);
		button.addSelectionListener(new ListenerAccountSelect());
		button.setText("\u67E5\u8BE2");

		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setLayoutData(new RowData(SWT.DEFAULT, 30));
		group_1.setText("\u51BB\u7ED3\u8D26\u53F7");
		group_1.setLayout(null);

		Button button_5 = new Button(group_1, SWT.NONE);
		button_5.addSelectionListener(new ListenerLoadFreeze());
		button_5.setBounds(6, 20, 84, 27);
		button_5.setText("\u52A0\u8F7D\u51BB\u7ED3\u8D26\u53F7");

		Composite composite_9 = new Composite(group_4, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_9.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_1 = new Composite(composite_9, SWT.NONE);
		TableColumnLayout tcl_composite_1 = new TableColumnLayout();
		composite_1.setLayout(tcl_composite_1);

		tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tblclmnid, new ColumnPixelData(76, true, true));
		tblclmnid.setText("\u8D26\u53F7ID");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tblclmnNewColumn, new ColumnPixelData(75, true, true));
		tblclmnNewColumn.setText("\u8D26\u53F7");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_1, new ColumnPixelData(72, true, true));
		tableColumn_1.setText("\u4FEE\u6539\u540E\u8D26\u53F7");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_2, new ColumnPixelData(60, true, true));
		tableColumn_2.setText("\u7528\u6237\u540D");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_3, new ColumnPixelData(85, true, true));
		tableColumn_3.setText("\u624B\u673A\u53F7");

		TableColumn tblclmnImei = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tblclmnImei, new ColumnPixelData(53, true, true));
		tblclmnImei.setText("IMEI");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_5, new ColumnPixelData(73, true, true));
		tableColumn_5.setText("\u72B6\u6001");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_6, new ColumnPixelData(97, true, true));
		tableColumn_6.setText("\u89E3\u51BB\u65F6\u95F4");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_7, new ColumnPixelData(84, true, true));
		tableColumn_7.setText("\u51BB\u7ED3\u5F00\u59CB\u65F6\u95F4");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_8, new ColumnPixelData(81, true, true));
		tableColumn_8.setText("\u6CE8\u518C\u6E38\u620F");

		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_9, new ColumnPixelData(88, true, true));
		tableColumn_9.setText("\u6CE8\u518C\u6E20\u9053");

		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_10, new ColumnPixelData(86, true, true));
		tableColumn_10.setText("\u6CE8\u518C\u65F6\u95F4");
		tableViewer.setContentProvider(new TableAccountUserContentProvider());
		tableViewer.setLabelProvider(new TableAccountUserLableProvider());

		createContextMenu();
	}

	public void createContextMenu() {
		MenuManager menuBar = new MenuManager();
		menuBar.add(new ActionFreeze());
		menuBar.add(new ActionPassword());
		Menu menu = menuBar.createContextMenu(getShell());
		tableViewer.getTable().setMenu(menu);
	}

	class ActionFreeze extends Action {
		public ActionFreeze() {
			setText("冻结账号");
		}

		@Override
		public void run() {
			StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
			if (selection.isEmpty()) {
				return;
			}
			BeanAccount account = (BeanAccount) selection.getFirstElement();
			textFreezeAccount.setText(account.getAccountId() + "");
			textFreezeAccount.setEditable(false);
		}
	}

	class ListenerFreezeReset extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			textFreezeAccount.setText("");
			textFreezeAccount.setEditable(true);
			textFreezeDay.setText("");
		}
	}

	class ListenerPwdReset extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			textPwdAccount.setText("");
			textPwdAccount.setEditable(true);
			textPwd_1.setText("");
			textPwd_2.setText("");
		}
	}

	class ActionPassword extends Action {
		public ActionPassword() {
			setText("重置密码");
		}

		@Override
		public void run() {
			StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
			if (selection.isEmpty()) {
				return;
			}
			BeanAccount account = (BeanAccount) selection.getFirstElement();
			textPwdAccount.setText(account.getUserid());
			textPwdAccount.setEditable(false);
			textPwd_1.setText("");
			textPwd_2.setText("");
		}
	}

	public void setUIData(Object data) {
		setData(dataKey, data);
		if (data != null) {
			if (data instanceof List) {
				tableViewer.setInput(data);
			}
		}
	}

	class ListenerAccountSelect extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String account = textAccountSelect.getText().trim();
			if (account.length() < 1) {
				MyDialog.openQuestion("请输入要查询的账号");
				return;
			}
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17019_ACCOUNT_INFO);
			buf.putString(account);
			sendData(buf, true);
			LoggerDevelop.gm(GmOperate.CLIENT_REQUEST_ACCOUNT_INFO, GMNetManager.getGmUserId(), account);
		}
	}

	class ListenerFreeze extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String account = textFreezeAccount.getText().trim();
			if (account.length() < 1) {
				MyDialog.openQuestion("请输入要冻结的账号ID");
				return;
			}
			int accountId = 0;
			try {
				accountId = Integer.parseInt(account);
			} catch (Exception err) {
				MyDialog.openQuestion("冻结账号ID为数字");
				return;
			}
			if (accountId < 1) {
				MyDialog.openQuestion("请输入要冻结的账号ID");
				return;
			}
			int freezeDay = 0;
			try {
				freezeDay = Integer.parseInt(textFreezeDay.getText());
			} catch (Exception err) {
				MyDialog.openQuestion("请输入要冻结账号的天数");
				return;
			}

			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17012_FREEZE_ACCOUNT);
			buf.putInt(accountId);
			buf.putInt(freezeDay);
			sendData(buf, true);
			LoggerDevelop.gm(GmOperate.CLIENT_FREEZE_GAME_ACCOUNT, GMNetManager.getGmUserId(), accountId + LoggerFilter.logDivide + freezeDay);
		}
	}

	class ListenerPwd extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String account = textPwdAccount.getText().trim();
			if (account.length() < 1) {
				MyDialog.openQuestion("请输入要修改密码的账号");
				return;
			}
			int accountId = 0;
			try {
				accountId = Integer.parseInt(account);
			} catch (Exception err) {
				MyDialog.openQuestion("冻结账号ID为数字");
				return;
			}
			if (accountId < 1) {
				MyDialog.openQuestion("请输入要修改密码的账号ID");
				return;
			}
			String pwd_1 = textPwd_1.getText().trim();
			if (pwd_1.length() < 6) {
				MyDialog.openQuestion("密码长度必须大于或等于6");
				return;
			}
			String pwd_2 = textPwd_2.getText().trim();
			if (pwd_2.length() < 1) {
				MyDialog.openQuestion("请输入确认密码");
				return;
			}
			if (!pwd_1.equals(pwd_2)) {
				MyDialog.openQuestion("输入密码不一致");
				return;
			}
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17015_PWD_RESET);
			buf.putInt(accountId);
			buf.putString(pwd_2);
			sendData(buf, true);
			LoggerDevelop.gm(GmOperate.CLIENT_RESET_ACCOUNT_PWD, GMNetManager.getGmUserId(), accountId);
		}
	}

	class ListenerLoadFreeze extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17044_LOAD_FREEZE);
			sendData(buf, true);
			LoggerDevelop.gm(GmOperate.CLIENT_FREEZE_ACCOUNT_LIST, GMNetManager.getGmUserId(), "");
		}
	}
}
