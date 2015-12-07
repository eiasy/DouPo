package mmo.module.gm.gui.composite;

import java.util.List;

import mmo.common.protocol.command.ProGmClient_17000;
import mmo.common.protocol.command.sub.SubPro_17084_serverCommds;
import mmo.module.cache.queue.ServiceServer;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.provider.table.mq.TableMQContentProvider;
import mmo.module.gm.gui.provider.table.mq.TableMQLableProvider;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;

import org.apache.mina.core.buffer.IoBuffer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
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

public class CompositeLoginServer extends GmComposite {
	private static final String[] stateNet = new String[] { "开启", "关闭" };
	private Table                 table;
	private Text                  textMQ;

	private TableViewer           tableViewer;

	public CompositeLoginServer(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite group = new Composite(this, SWT.NONE);
		group.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(group, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		group_1.setText("\u767B\u5F55\u670D\u52A1\u5668");

		Button button_6 = new Button(group_1, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.LOGIN_LOAD_CONFIG);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_LOGIN_CONFIG, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		button_6.setText("\u52A0\u8F7D\u914D\u7F6E\u9879");

		Button button_7 = new Button(group_1, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否已经更新classes目录，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新classes目录，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.LOGIN_LOAD_CLASS);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_LOGIN_CLASS, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		button_7.setText("\u52A0\u8F7D\u7C7B\u5E93");

		Button btnNewButton = new Button(group_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
				// if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
				// IoBuffer buf = IoBuffer.getPacketBuffer();
				// buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
				// buf.putInt(SubPro_17084_serverCommds.LOGIN_LOAD_CONFIG);
				// sendData(buf, true);
				//
				// LoggerDevelop.gm(GmOperate.CLIENT_LOGIN_CONFIG, GMNetManager.getGmUserId(), "");
				// }
				// }

				if (MyDialog.openQuestion("是否已经更新机型配置文件，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新机型配置文件，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.LOGIN_LOAD_CHANNEL);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_CLIENT_CHANNEL, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		btnNewButton.setText("\u52A0\u8F7D\u6E20\u9053\u914D\u7F6E");

		Group group_2 = new Group(composite, SWT.NONE);
		group_2.setLayout(new RowLayout(SWT.HORIZONTAL));
		group_2.setText("\u8D26\u53F7\u670D\u52A1\u5668");

		Button btnNewButton_1 = new Button(group_2, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.ACCOUNT_LOAD_CONFIG);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_ACCOUNT_CONFIG, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		btnNewButton_1.setText("\u52A0\u8F7D\u914D\u7F6E\u9879");

		Button button_5 = new Button(group_2, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否已经更新classes目录，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新classes目录，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.ACCOUNT_LOAD_CLASS);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_ACCOUNT_CLASS, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		button_5.setText("\u52A0\u8F7D\u7C7B\u5E93");

		Button button_8 = new Button(group_2, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否重新加载账号充值记录?")) {
					if (MyDialog.openQuestion("是否重新加载账号充值记录?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.ACCOUNT_LOAD_CHARGE);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_ACCOUNT_CHARGE, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		button_8.setText("\u52A0\u8F7D\u8D26\u53F7\u5145\u503C");

		Group group_3 = new Group(composite, SWT.NONE);
		group_3.setLayout(new RowLayout(SWT.HORIZONTAL));
		group_3.setText("\u5145\u503C\u670D\u52A1\u5668");

		Button btnNewButton_2 = new Button(group_3, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.CHARGE_LOAD_CONFIG);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_CHARGE_CONFIG, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		btnNewButton_2.setText("\u52A0\u8F7D\u914D\u7F6E\u9879");

		Button button_3 = new Button(group_3, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否已经更新classes目录，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新classes目录，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.CHARGE_LOAD_CLASS);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_CHARGE_CLASS, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		button_3.setText("\u52A0\u8F7D\u7C7B\u5E93");

		Group group_4 = new Group(composite, SWT.NONE);
		group_4.setLayout(new RowLayout(SWT.HORIZONTAL));
		group_4.setText("\u7BA1\u7406\u670D\u52A1\u5668");

		Button button = new Button(group_4, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新configs.xml文件，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.MANAGER_LOAD_CONFIG);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_MANAGER_CONFIG, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		button.setText("\u52A0\u8F7D\u914D\u7F6E\u9879");

		Button button_4 = new Button(group_4, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MyDialog.openQuestion("是否已经更新classes目录，并进行重新加载?")) {
					if (MyDialog.openQuestion("是否已经更新classes目录，并进行重新加载?")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17084_SERVER_CMDS);
						buf.putInt(SubPro_17084_serverCommds.MANAGER_LOAD_CLASS);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_MANAGER_CLASS, GMNetManager.getGmUserId(), "");
					}
				}
			}
		});
		button_4.setText("\u52A0\u8F7D\u7C7B\u5E93");

		Composite composite_4 = new Composite(group, SWT.BORDER);
		composite_4.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayout(null);

		Label lblNewLabel = new Label(composite_5, SWT.NONE);
		lblNewLabel.setBounds(3, 3, 84, 17);
		lblNewLabel.setText("\u670D\u52A1\u76D1\u542C\u961F\u5217\uFF1A");

		textMQ = new Text(composite_5, SWT.BORDER);
		textMQ.setBounds(90, 3, 150, 23);

		Button button_1 = new Button(composite_4, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String mq = textMQ.getText().trim();
				if (mq.trim().length() < 5) {
					MyDialog.openError("请输入正确的消息队列");
					return;
				}
				if (MyDialog.openQuestion("是否要维护消息队列:" + mq)) {
					if (MyDialog.openQuestion("是否要维护消息队列:" + mq)) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17062_MQ_REPAIR);
						buf.putString(mq);
						sendData(buf, true);

						LoggerDevelop.gm(GmOperate.CLIENT_REPAIR_MSG_QUEUE, GMNetManager.getGmUserId(), mq);
					}
				}
			}
		});
		button_1.setText("\u7EF4\u62A4");

		Button button_2 = new Button(composite_4, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String mq = textMQ.getText();
				if (mq.trim().length() < 5) {
					MyDialog.openError("请输入正确的消息队列");
					return;
				}
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17063_MQ_DINGAOS);
				buf.putString(mq.trim());
				sendData(buf, true);

				LoggerDevelop.gm(GmOperate.CLIENT_CHECK_MSG_QUEUE, GMNetManager.getGmUserId(), mq.trim());
			}
		});
		button_2.setText("\u8BCA\u65AD");

		Composite composite_1 = new Composite(group, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		TableColumnLayout tcl_composite_2 = new TableColumnLayout();
		composite_2.setLayout(tcl_composite_2);

		tableViewer = new TableViewer(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tcl_composite_2.setColumnData(tableColumn, new ColumnPixelData(60, true, true));
		tableColumn.setText("\u4EA7\u54C1\u7F16\u53F7");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_1.getColumn();
		tcl_composite_2.setColumnData(tblclmnNewColumn, new ColumnPixelData(74, true, true));
		tblclmnNewColumn.setText("\u4EA7\u54C1\u540D\u79F0");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_1 = tableViewerColumn_2.getColumn();
		tcl_composite_2.setColumnData(tableColumn_1, new ColumnPixelData(66, true, true));
		tableColumn_1.setText("\u5E94\u7528\u7F16\u53F7");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_2 = tableViewerColumn_3.getColumn();
		tcl_composite_2.setColumnData(tableColumn_2, new ColumnPixelData(80, true, true));
		tableColumn_2.setText("\u5E94\u7528\u540D\u79F0");

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_3 = tableViewerColumn_4.getColumn();
		tcl_composite_2.setColumnData(tableColumn_3, new ColumnPixelData(72, true, true));
		tableColumn_3.setText("\u5E94\u7528\u7C7B\u578B");

		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_4 = tableViewerColumn_5.getColumn();
		tcl_composite_2.setColumnData(tableColumn_4, new ColumnPixelData(105, true, true));
		tableColumn_4.setText("\u76D1\u542C\u961F\u5217");

		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_5 = tableViewerColumn_6.getColumn();
		tcl_composite_2.setColumnData(tableColumn_5, new ColumnPixelData(150, true, true));
		tableColumn_5.setText("\u6CE8\u518C\u65F6\u95F4");

		tableViewer.setContentProvider(new TableMQContentProvider());
		tableViewer.setLabelProvider(new TableMQLableProvider());
		createContextMenu();
	}

	public void createContextMenu() {
		MenuManager menuBar = new MenuManager();
		menuBar.add(new ActionRefresh());
		menuBar.add(new ActionRepair());
		menuBar.add(new ActionDiagnos());
		menuBar.add(new ActionClassloader());
		Menu menu = menuBar.createContextMenu(getShell());
		tableViewer.getTable().setMenu(menu);
	}

	public void setUIData(Object data) {
		setData(dataKey, data);
		if (data != null) {
			if (data instanceof List) {
				tableViewer.setInput(data);
			}
		}
	}

	class ActionRefresh extends Action {
		public ActionRefresh() {
			setText("刷新队列服务");
		}

		@Override
		public void run() {
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17061_REQUEST_MQ_LIST);
			sendData(buf, true);

			LoggerDevelop.gm(GmOperate.CLIENT_REFRESH_MSG_QUEUE_LIST, GMNetManager.getGmUserId(), "");
		}
	}

	class ActionRepair extends Action {
		public ActionRepair() {
			setText("维护队列服务");
		}

		@Override
		public void run() {
			StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
			if (selection.isEmpty()) {
				return;
			}

			ServiceServer ss = (ServiceServer) selection.getFirstElement();
			if (MyDialog.openQuestion("是否要维护" + ss.serverInfo())) {
				if (MyDialog.openQuestion("是否要维护" + ss.serverInfo())) {
					IoBuffer buf = IoBuffer.getPacketBuffer();
					buf.setProtocol(ProGmClient_17000.P_17062_MQ_REPAIR);
					buf.putString(ss.getMqName());
					sendData(buf, true);

					LoggerDevelop.gm(GmOperate.CLIENT_REPAIR_MSG_QUEUE, GMNetManager.getGmUserId(), ss.getMqName());
				}
			}
		}
	}

	class ActionDiagnos extends Action {
		public ActionDiagnos() {
			setText("诊断队列服务");
		}

		@Override
		public void run() {
			StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
			if (selection.isEmpty()) {
				return;
			}
			ServiceServer ss = (ServiceServer) selection.getFirstElement();
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17063_MQ_DINGAOS);
			buf.putString(ss.getMqName());
			sendData(buf, true);

			LoggerDevelop.gm(GmOperate.CLIENT_CHECK_MSG_QUEUE, GMNetManager.getGmUserId(), ss.getMqName());
		}
	}

	class ActionClassloader extends Action {
		public ActionClassloader() {
			setText("加载类库");
		}

		@Override
		public void run() {
			StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
			if (selection.isEmpty()) {
				return;
			}
			ServiceServer ss = (ServiceServer) selection.getFirstElement();
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17065_CLASSLOADER);
			buf.putString(ss.getMqName());
			sendData(buf, true);

			LoggerDevelop.gm(GmOperate.CLIENT_CLASSLOADER, GMNetManager.getGmUserId(), ss.getMqName());
		}
	}
}
