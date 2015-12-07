package mmo.module.gm.gui.composite;

import java.util.List;

import mmo.common.protocol.command.ProGmClient_17000;
import mmo.module.cache.device.DeviceFreeze;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.provider.table.device.TableDeviceContentProvider;
import mmo.module.gm.gui.provider.table.device.TableDeviceLableProvider;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class CompositeDevice extends GmComposite {
	private Text        textAccountSelect;
	private Table       table;
	private Text        textFreezeAccount;
	private Text        textFreezeDay;
	private TableViewer tableViewer;

	public CompositeDevice(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		Group group_3 = new Group(this, SWT.NONE);
		group_3.setLayout(new RowLayout(SWT.HORIZONTAL));
		group_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group_3.setText("\u51BB\u7ED3\u8BBE\u5907");

		Composite composite_7 = new Composite(group_3, SWT.NONE);
		composite_7.setLayout(null);

		Label lblid = new Label(composite_7, SWT.NONE);
		lblid.setBounds(3, 3, 63, 17);
		lblid.setText("\u8BBE\u5907IMEI\uFF1A");

		textFreezeAccount = new Text(composite_7, SWT.BORDER);
		textFreezeAccount.setBounds(69, 3, 150, 23);

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
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group group = new Group(composite, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setLayout(new RowLayout(SWT.HORIZONTAL));
		group.setText("\u51BB\u7ED3\u8BBE\u5907");

		Composite composite_2 = new Composite(group, SWT.NONE);
		composite_2.setLayout(null);

		Label lblimei = new Label(composite_2, SWT.NONE);
		lblimei.setBounds(3, 3, 63, 17);
		lblimei.setText("\u8BBE\u5907IMEI\uFF1A");

		textAccountSelect = new Text(composite_2, SWT.BORDER);
		textAccountSelect.setBounds(69, 3, 150, 23);

		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new ListenerAccountSelect());
		button.setText("\u67E5\u8BE2");

		Button button_5 = new Button(group, SWT.NONE);
		button_5.addSelectionListener(new ListenerLoadFreeze());
		button_5.setText("\u52A0\u8F7D\u5DF2\u51BB\u7ED3\u8BBE\u5907");

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
		tcl_composite_1.setColumnData(tblclmnid, new ColumnPixelData(170, true, true));
		tblclmnid.setText("\u8BBE\u5907IMEI");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_6, new ColumnPixelData(132, true, true));
		tableColumn_6.setText("\u89E3\u51BB\u65F6\u95F4");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_7, new ColumnPixelData(146, true, true));
		tableColumn_7.setText("\u51BB\u7ED3\u5F00\u59CB\u65F6\u95F4");
		tableViewer.setContentProvider(new TableDeviceContentProvider());
		tableViewer.setLabelProvider(new TableDeviceLableProvider());

		createContextMenu();
	}

	public void createContextMenu() {
		MenuManager menuBar = new MenuManager();
		menuBar.add(new ActionFreeze());
		Menu menu = menuBar.createContextMenu(getShell());
		tableViewer.getTable().setMenu(menu);
	}

	class ActionFreeze extends Action {
		public ActionFreeze() {
			setText("冻结设备");
		}

		@Override
		public void run() {
			StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
			if (selection.isEmpty()) {
				return;
			}
			DeviceFreeze df = (DeviceFreeze) selection.getFirstElement();
			textFreezeAccount.setText(df.getDeviceImei());
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
			String device = textAccountSelect.getText().trim();
			if (device.length() < 1) {
				MyDialog.openQuestion("请输入要查询的设备IMEI");
				return;
			}
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17059_CHECK_FREEZE_DEVICE);
			buf.putString(device);
			sendData(buf, true);
			LoggerDevelop.gm(GmOperate.CLIENT_CHECK_DEVICE_FREEZE, GMNetManager.getGmUserId(), device);
		}
	}

	class ListenerFreeze extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String imei = textFreezeAccount.getText().trim();
			if (imei.length() < 1) {
				MyDialog.openQuestion("请输入要冻结设备的IMEI");
				return;
			}
			int freezeDay = 0;
			try {
				freezeDay = Integer.parseInt(textFreezeDay.getText());
			} catch (Exception err) {
				MyDialog.openQuestion("请输入要冻设备的天数");
				return;
			}

			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17058_FREEZE_DEVICE);
			buf.putString(imei);
			buf.putInt(freezeDay);
			sendData(buf, true);
			LoggerDevelop.gm(GmOperate.CLIENT_DEVICE_FREEZE, GMNetManager.getGmUserId(), imei + LoggerFilter.logDivide + freezeDay);
		}
	}

	class ListenerLoadFreeze extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17060_FREEZE_DEVICE_LIST);
			sendData(buf, true);
			LoggerDevelop.gm(GmOperate.CLIENT_DEVICE_FREEZE_LIST, GMNetManager.getGmUserId(), "");
		}
	}
}
