package mmo.module.gm.gui.composite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.account.HttpCData;
import mmo.common.protocol.command.ProGmClient_17000;
import mmo.module.gm.bean.BeanPushMessage;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.provider.table.push.TablePushMessageContentProvider;
import mmo.module.gm.gui.provider.table.push.TablePushMessageLableProvider;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerFilter;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.buffer.IoBuffer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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

import com.swtdesigner.SWTResourceManager;

public class CompositePushMessage extends GmComposite {
	private Group               groupServerInfo;
	private Label               labelGameName;
	private Table               table;
	private CheckboxTableViewer checkboxTableViewer;
	private BeanPushMessage     pm = null;
	private Button              buttonOperate;
	private Text                textOffset;
	private Text                textTitle;
	private Combo               comboTarget;
	private Combo               comboChannel;
	private Button              buttonPushNow;
	private Combo               comboHour;
	private Combo               comboMinute;
	private StyledText          textContent;
	private Label               labelState;

	public CompositePushMessage(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		groupServerInfo = new Group(composite, SWT.NONE);
		groupServerInfo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		groupServerInfo.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label labelSymbol_1 = new Label(groupServerInfo, SWT.NONE);
		labelSymbol_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		labelSymbol_1.setText("\u6E38\u620F\uFF1A");

		labelGameName = new Label(groupServerInfo, SWT.NONE);
		labelGameName.setText("\u540D\u79F0");

		Label labelSymbol_3 = new Label(groupServerInfo, SWT.NONE);
		labelSymbol_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		labelSymbol_3.setText(">>\u7F16\u8F91\u6D3B\u52A8#");

		Composite compositeEmailServer = new Composite(composite, SWT.NONE);
		compositeEmailServer.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_compositeEmailServer = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_compositeEmailServer.heightHint = 238;
		compositeEmailServer.setLayoutData(gd_compositeEmailServer);

		SashForm form = new SashForm(compositeEmailServer, SWT.SMOOTH);
		form.setLayout(new FillLayout());

		Group group_1 = new Group(form, SWT.NONE);
		group_1.setText("\u6D3B\u52A8\u5217\u8868");
		group_1.setLayout(new GridLayout(1, false));

		Composite composite_13 = new Composite(group_1, SWT.NONE);
		composite_13.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button button = new Button(composite_13, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IoBuffer buf_load = IoBuffer.getPacketBuffer();
				buf_load.setProtocol(ProGmClient_17000.P_17085_LOAD_PUSH_MESSAGE);
				sendData(buf_load, true);
			}
		});
		button.setText("\u5237\u65B0\u5217\u8868");

		Composite composite_5 = new Composite(group_1, SWT.NONE);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));

		checkboxTableViewer = CheckboxTableViewer.newCheckList(composite_5, SWT.BORDER | SWT.FULL_SELECTION);
		table = checkboxTableViewer.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				IStructuredSelection selection = (IStructuredSelection) checkboxTableViewer.getSelection();

				if (selection.isEmpty()) {
					return;
				}
				clearData();
				pm = (BeanPushMessage) selection.getFirstElement();
				String[] array = StringUtil.splitString(pm.getPushTime(), ':');
				if (array.length == 2) {
					comboHour.setText(array[0]);
					comboMinute.setText(array[1]);
				}
				labelState.setText(pm.getStatus() == 0 ? "开启" : "关闭");
				textOffset.setText(pm.getPushOffset() + "");
				comboChannel.setText(pm.getChannel());
				comboTarget.setText(pm.getTarget());
				textTitle.setText(pm.getTitle());
				textContent.setText(pm.getContent());
				buttonPushNow.setSelection(false);
				buttonPushNow.setEnabled(false);
				buttonOperate.setText("  更新  ");
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tblclmnId = tableViewerColumn.getColumn();
		tblclmnId.setWidth(50);
		tblclmnId.setText("ID");

		TableViewerColumn tableViewerColumnStatus = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumnStatus = tableViewerColumnStatus.getColumn();
		tableColumnStatus.setWidth(55);
		tableColumnStatus.setText("\u6807\u9898");

		TableViewerColumn tableViewerColumnStart = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumnStart = tableViewerColumnStart.getColumn();
		tableColumnStart.setWidth(131);
		tableColumnStart.setText("\u5185\u5BB9");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn_1.getColumn();
		tableColumn.setWidth(66);
		tableColumn.setText("\u63A8\u9001\u65F6\u95F4");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumn_1 = tableViewerColumn_2.getColumn();
		tableColumn_1.setWidth(60);
		tableColumn_1.setText("\u63A8\u9001\u95F4\u9694");

		TableViewerColumn tableViewerColumnCate = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumnCate = tableViewerColumnCate.getColumn();
		tableColumnCate.setWidth(55);
		tableColumnCate.setText("\u6E20\u9053");

		TableViewerColumn tableViewerColumnServer = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumnServer = tableViewerColumnServer.getColumn();
		tableColumnServer.setWidth(55);
		tableColumnServer.setText("\u76EE\u6807");
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumn_2 = tableViewerColumn_3.getColumn();
		tableColumn_2.setText("\u72B6\u6001");
		tableColumn_2.setWidth(55);
		checkboxTableViewer.setContentProvider(new TablePushMessageContentProvider());
		checkboxTableViewer.setLabelProvider(new TablePushMessageLableProvider());

		Group group = new Group(form, SWT.NONE);
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		group.setText("\u901A\u77E5");

		Composite compositeEmail = new Composite(group, SWT.NONE);
		compositeEmail.setLayout(new GridLayout(1, false));

		Composite composite_16 = new Composite(compositeEmail, SWT.NONE);
		composite_16.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		buttonOperate = new Button(composite_16, SWT.NONE);
		buttonOperate.addSelectionListener(new ListenerCommit());
		buttonOperate.setText("  \u6DFB\u52A0  ");

		Button button_1 = new Button(composite_16, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearData();
			}
		});
		button_1.setText("  \u91CD\u7F6E  ");

		Composite composite_8 = new Composite(compositeEmail, SWT.BORDER);
		composite_8.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_9 = new Composite(composite_8, SWT.NONE);
		composite_9.setLayout(null);

		Label label_6 = new Label(composite_9, SWT.NONE);
		label_6.setBounds(5, 9, 60, 17);
		label_6.setText("\u63A8\u9001\u6E20\u9053\uFF1A");

		comboChannel = new Combo(composite_9, SWT.READ_ONLY);
		comboChannel.setBounds(70, 5, 88, 25);
		String[] channels = StringUtil.splitString(ProjectCofigs.getParameter("push_channel"), ',');
		if (channels != null && channels.length > 0) {
			comboChannel.setItems(channels);
			comboChannel.select(0);
		}

		Composite composite_10 = new Composite(composite_8, SWT.NONE);
		composite_10.setLayout(null);

		Label label_7 = new Label(composite_10, SWT.NONE);
		label_7.setBounds(3, 3, 60, 17);
		label_7.setText("\u63A8\u9001\u76EE\u6807\uFF1A");

		comboTarget = new Combo(composite_10, SWT.READ_ONLY);
		comboTarget.setBounds(66, 3, 88, 25);
		String[] targets = StringUtil.splitString(ProjectCofigs.getParameter("push_target"), ',');
		if (targets != null && targets.length > 0) {
			comboTarget.setItems(targets);
			comboTarget.select(0);
		}

		Composite composite_7 = new Composite(composite_8, SWT.NONE);
		composite_7.setLayout(null);

		buttonPushNow = new Button(composite_7, SWT.CHECK);
		buttonPushNow.setBounds(3, 3, 69, 17);
		buttonPushNow.setText("\u5373\u65F6\u63A8\u9001");

		labelState = new Label(composite_8, SWT.NONE);
		labelState.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		labelState.setText("\u5F00\u542F");

		Composite composite_1 = new Composite(compositeEmail, SWT.BORDER);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(null);

		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(3, 3, 60, 17);
		label.setText("\u63A8\u9001\u65F6\u95F4\uFF1A");

		comboHour = new Combo(composite_2, SWT.READ_ONLY);
		comboHour.setBounds(66, 3, 43, 25);
		comboHour.setItems(HOURS);
		comboHour.select(0);
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setBounds(112, 3, 12, 17);
		label_1.setText("\u70B9");

		comboMinute = new Combo(composite_2, SWT.READ_ONLY);
		comboMinute.setBounds(127, 3, 43, 25);
		comboMinute.setItems(MINUTES);
		comboMinute.select(0);

		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setBounds(173, 3, 12, 17);
		label_2.setText("\u5206");

		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayout(null);

		Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setBounds(3, 3, 60, 17);
		label_3.setText("\u63A8\u9001\u95F4\u9694\uFF1A");

		textOffset = new Text(composite_3, SWT.BORDER);
		textOffset.setBounds(66, 3, 73, 23);

		Label label_8 = new Label(composite_3, SWT.NONE);
		label_8.setBounds(142, 3, 24, 17);
		label_8.setText("\u5206\u949F");

		Composite composite_4 = new Composite(compositeEmail, SWT.BORDER);
		composite_4.setLayout(new GridLayout(2, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(composite_4, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u6807\u9898\uFF1A");

		textTitle = new Text(composite_4, SWT.BORDER);
		textTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_6 = new Composite(compositeEmail, SWT.BORDER);
		composite_6.setLayout(new GridLayout(2, false));
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(composite_6, SWT.NONE);
		label_5.setText("\u5185\u5BB9\uFF1A");

		textContent = new StyledText(composite_6, SWT.BORDER | SWT.FULL_SELECTION | SWT.WRAP);
		GridData gd_textContent = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textContent.heightHint = 107;
		textContent.setLayoutData(gd_textContent);

		MenuManager menuBar = new MenuManager();
		menuBar.add(new ActionClose());
		menuBar.add(new ActionOpen());
		menuBar.add(new ActionRefresh());
		Menu menu = menuBar.createContextMenu(getShell());
		checkboxTableViewer.getTable().setMenu(menu);

		form.setWeights(new int[] { 524, 515 });
	}

	public void setUIData(Object data) {
		setData(dataKey, data);
		this.pm = null;
		if (data != null) {
			if (data instanceof List) {
				checkboxTableViewer.setInput(data);
			}
			groupServerInfo.layout();
		}
		clearData();
	}

	class ListenerCommit extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			if (pm != null) {
				updateActivity();
			} else {
				addActivity();
			}
		}
	}

	private void clearData() {
		pm = null;
		textOffset.setText("0");
		comboHour.select(0);
		comboMinute.select(0);
		comboChannel.select(0);
		comboTarget.select(0);
		textTitle.setText("");
		textContent.setText("");
		buttonPushNow.setSelection(false);
		buttonPushNow.setEnabled(true);
		labelState.setText("开启");
		buttonOperate.setText("  添加  ");
	}

	private void addActivity() {
		String channel = comboChannel.getText();
		if (channel == null || channel.trim().length() < 1) {
			MyDialog.openInformation("请选择推送渠道");
			return;
		}
		String target = comboTarget.getText();
		if (target == null || target.trim().length() < 1) {
			MyDialog.openInformation("请选择推送目标");
			return;
		}

		String title = textTitle.getText().trim();
		String content = textContent.getText().trim();
		if (content.length() < 1) {
			MyDialog.openInformation("请输入通知内容");
			return;
		}

		String pushTime = comboHour.getText() + ":" + comboMinute.getText();
		int offset = 5;
		if (!buttonPushNow.getSelection()) {
			try {
				offset = Integer.parseInt(textOffset.getText().trim());
			} catch (Exception ex) {
				MyDialog.openInformation("请输入推送间隔");
				return;
			}
			if (offset < 5) {
				MyDialog.openInformation("推送间隔不能小于5分钟");
				return;
			}
		}

		Map<String, Object> dataSource = new HashMap<String, Object>();
		dataSource.put(HttpCData.PushMessage.channel, channel);
		dataSource.put(HttpCData.PushMessage.target, target);
		dataSource.put(HttpCData.PushMessage.title, title);
		dataSource.put(HttpCData.PushMessage.content, content);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(HttpCData.PushMessage.cdata, dataSource);

		String cdata = jsonObject.toString();

		StringBuilder sb = new StringBuilder();
		sb.append("是否要添加通知:\r\n");
		sb.append("标题：").append(title).append("\r\n");
		sb.append("内容：").append(content).append("\r\n");
		sb.append("即时推送：").append(buttonPushNow.getSelection() ? "是" : "否").append("\r\n");
		sb.append("推送时间：").append(pushTime).append("\r\n");
		sb.append("推送间隔：").append(offset + "分钟").append("\r\n");

		if (MyDialog.openQuestion(sb.toString())) {
			if (MyDialog.openQuestion(sb.toString())) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17086_ADD_PUSH_MESSAGE);
				buf.putBoolean(buttonPushNow.getSelection());
				buf.putString(pushTime);
				buf.putInt(offset);
				buf.putString(cdata);
				sendData(buf, true);

				IoBuffer buf_load = IoBuffer.getPacketBuffer();
				buf_load.setProtocol(ProGmClient_17000.P_17085_LOAD_PUSH_MESSAGE);
				sendData(buf_load, false);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(pushTime);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(offset);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(cdata);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(cdata);
				LoggerDevelop.gm(GmOperate.CLIENT_ADD_PUSH_MESSAGE, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	private void updateActivity() {
		if (pm == null) {
			MyDialog.openInformation("请双击选择要要更新的通知");
			return;
		}
		String channel = comboChannel.getText();
		if (channel == null || channel.trim().length() < 1) {
			MyDialog.openInformation("请选择推送渠道");
			return;
		}
		String target = comboTarget.getText();
		if (target == null || target.trim().length() < 1) {
			MyDialog.openInformation("请选择推送目标");
			return;
		}

		String title = textTitle.getText().trim();
		String content = textContent.getText().trim();
		if (content.length() < 1) {
			MyDialog.openInformation("请输入通知内容");
			return;
		}

		String pushTime = comboHour.getText() + ":" + comboMinute.getText();
		int offset = 5;
		if (!buttonPushNow.getSelection()) {
			try {
				offset = Integer.parseInt(textOffset.getText().trim());
			} catch (Exception ex) {
				MyDialog.openInformation("请输入推送间隔");
				return;
			}
			if (offset < 5) {
				MyDialog.openInformation("推送间隔不能小于5分钟");
				return;
			}
		}

		Map<String, Object> dataSource = new HashMap<String, Object>();
		dataSource.put(HttpCData.PushMessage.channel, channel);
		dataSource.put(HttpCData.PushMessage.target, target);
		dataSource.put(HttpCData.PushMessage.title, title);
		dataSource.put(HttpCData.PushMessage.content, content);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(HttpCData.PushMessage.cdata, dataSource);

		String cdata = jsonObject.toString();

		StringBuilder sb = new StringBuilder();
		sb.append("是否要更新通知:\r\n");
		sb.append("标题：").append(title).append("\r\n");
		sb.append("内容：").append(content).append("\r\n");
		sb.append("即时推送：").append(buttonPushNow.getSelection() ? "是" : "否").append("\r\n");
		sb.append("推送时间：").append(pushTime).append("\r\n");
		sb.append("推送间隔：").append(offset + "分钟").append("\r\n");

		if (MyDialog.openQuestion(sb.toString())) {
			if (MyDialog.openQuestion(sb.toString())) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17087_UPDATE_PUSH_MESSAGE);
				buf.putInt(pm.getId());
				buf.putString(pushTime);
				buf.putInt(offset);
				buf.putString(cdata);
				sendData(buf, true);

				IoBuffer buf_load = IoBuffer.getPacketBuffer();
				buf_load.setProtocol(ProGmClient_17000.P_17085_LOAD_PUSH_MESSAGE);
				sendData(buf_load, false);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(pushTime);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(offset);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(cdata);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(cdata);
				LoggerDevelop.gm(GmOperate.CLIENT_UPDATE_PUSH_MESSAGE, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	class ActionClose extends Action {
		public ActionClose() {
			setText("关闭通知");
		}

		@Override
		public void run() {
			Object[] checked = checkboxTableViewer.getCheckedElements();
			if (checked.length == 0) {
				MyDialog.openInformation("请选中你要关闭的活动");
				return;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("是否要关闭下列通知:\r\n");
			BeanPushMessage pm = null;
			for (Object obj : checked) {
				pm = (BeanPushMessage) obj;
				sb.append("ID：").append(pm.getId()).append(",  内容：").append(pm.getContent()).append("\r\n");
			}
			if (MyDialog.openQuestion(sb.toString())) {
				if (MyDialog.openQuestion(sb.toString())) {
					StringBuilder sb_log = new StringBuilder();
					IoBuffer buf = IoBuffer.getPacketBuffer();
					buf.setProtocol(ProGmClient_17000.P_17088_UPDATE_PUSH_MESSAGE_STATUS);
					buf.put((byte) 1);
					for (Object obj : checked) {
						pm = (BeanPushMessage) obj;
						buf.putInt(pm.getId());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pm.getId());
					}
					buf.putInt(-1);
					sendData(buf, true);
					IoBuffer buf_load = IoBuffer.getPacketBuffer();
					buf_load.setProtocol(ProGmClient_17000.P_17085_LOAD_PUSH_MESSAGE);
					sendData(buf_load, false);
					LoggerDevelop.gm(GmOperate.CLIENT_DEL_PUSH_MESSAGE, GMNetManager.getGmUserId(), sb_log.toString());
				}
			}
		}
	}

	class ActionOpen extends Action {
		public ActionOpen() {
			setText("开启通知");
		}

		@Override
		public void run() {
			Object[] checked = checkboxTableViewer.getCheckedElements();
			if (checked.length == 0) {
				MyDialog.openInformation("请选中你要开启的活动");
				return;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("是否要开启下列通知:\r\n");
			BeanPushMessage pm = null;
			for (Object obj : checked) {
				pm = (BeanPushMessage) obj;
				sb.append("ID：").append(pm.getId()).append(",  内容：").append(pm.getContent()).append("\r\n");
			}
			if (MyDialog.openQuestion(sb.toString())) {
				if (MyDialog.openQuestion(sb.toString())) {
					StringBuilder sb_log = new StringBuilder();
					IoBuffer buf = IoBuffer.getPacketBuffer();
					buf.setProtocol(ProGmClient_17000.P_17088_UPDATE_PUSH_MESSAGE_STATUS);
					buf.put((byte) 0);
					for (Object obj : checked) {
						pm = (BeanPushMessage) obj;
						buf.putInt(pm.getId());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pm.getId());
					}
					buf.putInt(-1);
					sendData(buf, true);
					IoBuffer buf_load = IoBuffer.getPacketBuffer();
					buf_load.setProtocol(ProGmClient_17000.P_17085_LOAD_PUSH_MESSAGE);
					sendData(buf_load, false);
					LoggerDevelop.gm(GmOperate.CLIENT_DEL_PUSH_MESSAGE, GMNetManager.getGmUserId(), sb_log.toString());
				}
			}
		}
	}

	class ActionRefresh extends Action {
		public ActionRefresh() {
			setText("刷新列表");
		}

		@Override
		public void run() {
			IoBuffer buf_load = IoBuffer.getPacketBuffer();
			buf_load.setProtocol(ProGmClient_17000.P_17085_LOAD_PUSH_MESSAGE);
			sendData(buf_load, true);
		}
	}
}
