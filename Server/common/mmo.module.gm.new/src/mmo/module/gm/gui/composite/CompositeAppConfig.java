package mmo.module.gm.gui.composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mmo.common.module.datacenter.bean.AppConfigs;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.provider.table.event.appconfig.TableAppConfigContentProvider;
import mmo.module.gm.gui.provider.table.event.appconfig.TableAppConfigLableProvider;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class CompositeAppConfig extends GmComposite {
	static {
	}

	private Table table;
	private Combo comboPlatform;
	private int totalPage;
	private int page;
	private TableViewer tableViewer;
	private Composite composite_15;
	private Group group_2;
	private Combo comboGameServer;
	private Text textDbName;
	private Text textDbIp;
	private Text textDbPort;
	private Text textDbUser;
	private Text textDbPassword;
	private Combo comboType;
	private Combo comboAppName;
	private AppConfigs appConfig;
	private Button buttonOperate;
	private Group groupOpreate;

	public CompositeAppConfig(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		Composite group_1 = new Composite(this, SWT.BORDER);
		group_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_7 = new Composite(group_1, SWT.NONE);
		composite_7.setLayout(null);

		Label lblNewLabel_5 = new Label(composite_7, SWT.NONE);
		lblNewLabel_5.setBounds(3, 3, 60, 17);
		lblNewLabel_5.setText("\u5E73\u53F0\u7C7B\u578B\uFF1A");

		comboPlatform = new Combo(composite_7, SWT.READ_ONLY);
		comboPlatform.setBounds(66, 3, 120, 25);
		comboPlatform.setItems(platforms);
		comboPlatform.select(0);

		Composite composite_8 = new Composite(group_1, SWT.NONE);
		composite_8.setLayout(null);

		Label label_1 = new Label(composite_8, SWT.NONE);
		label_1.setBounds(3, 3, 60, 17);
		label_1.setText("\u6E38\u620F\u5206\u533A\uFF1A");

		comboGameServer = new Combo(composite_8, SWT.READ_ONLY);
		comboGameServer.setBounds(66, 3, 120, 25);
		String[] gsArray = StringUtil.splitString(ProjectCofigs.getParameter("gameServers"), ',');
		String[] array = new String[gsArray.length + 1];
		array[0] = "";
		for (int ai = 0; ai < gsArray.length; ai++) {
			array[ai + 1] = (ai + 1) + ":" + gsArray[ai];
		}
		comboGameServer.setItems(array);
		if (array.length > 0) {
			comboGameServer.select(0);
		}

		composite_15 = new Composite(this, SWT.NONE);
		composite_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_15.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button button_1 = new Button(composite_15, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				page = 0;
				selectCharge(page);
			}
		});
		button_1.setText("\u9996\u9875");

		Button button_2 = new Button(composite_15, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				page -= 1;
				selectCharge(page);
			}
		});
		button_2.setText("\u4E0A\u4E00\u9875");

		Button button_3 = new Button(composite_15, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				page += 1;
				selectCharge(page);
			}
		});
		button_3.setText("\u4E0B\u4E00\u9875");

		Button button_4 = new Button(composite_15, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				page = totalPage;
				selectCharge(page);
			}
		});
		button_4.setText("\u5C3E\u9875");

		Button button = new Button(composite_15, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectCharge(page);
			}
		});
		button.setText("    \u67E5  \u8BE2    ");

		group_2 = new Group(this, SWT.NONE);
		group_2.setText("第" + page + "页 共" + totalPage + "页");

		group_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		group_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite = new Composite(group_2, SWT.NONE);
		TableColumnLayout tcl_composite = new TableColumnLayout();
		composite.setLayout(tcl_composite);

		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();

				if (selection.isEmpty()) {
					return;
				}
				appConfig = (AppConfigs) selection.getFirstElement();
				updateUI();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();

				if (selection.isEmpty()) {
					return;
				}
				appConfig = (AppConfigs) selection.getFirstElement();
				updateUI();
			}
		});

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnId, new ColumnPixelData(36, true, true));
		tblclmnId.setText("ID");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn, new ColumnPixelData(49, true, true));
		tableColumn.setText("\u5E73\u53F0\u7C7B\u578B");

		TableColumn tblclmnOpenid_1 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnOpenid_1, new ColumnPixelData(61, true, true));
		tblclmnOpenid_1.setText("\u5206\u533A\u7F16\u53F7");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_2, new ColumnPixelData(60, true, true));
		tableColumn_2.setText("\u5206\u533A\u540D\u79F0");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_3, new ColumnPixelData(66, true, true));
		tableColumn_3.setText("\u6570\u636E\u5E93\u540D");

		TableColumn tblclmnip = new TableColumn(table, SWT.NONE);
		tblclmnip.setText("\u6570\u636E\u5E93IP");
		tcl_composite.setColumnData(tblclmnip, new ColumnPixelData(62, true, true));

		TableColumn tblclmnRmb = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnRmb, new ColumnPixelData(78, true, true));
		tblclmnRmb.setText("\u6570\u636E\u5E93\u7AEF\u53E3");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setText("\u7528\u6237\u540D");
		tcl_composite.setColumnData(tableColumn_6, new ColumnPixelData(104, true, true));

		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setText("\u5BC6\u7801");
		tcl_composite.setColumnData(tableColumn_7, new ColumnPixelData(86, true, true));

		groupOpreate = new Group(this, SWT.NONE);
		groupOpreate.setText("\u6DFB\u52A0\u65B0\u914D\u7F6E");
		groupOpreate.setLayout(new GridLayout(1, false));
		groupOpreate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_1 = new Composite(groupOpreate, SWT.BORDER);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(null);

		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(3, 3, 60, 17);
		label.setText("\u5E73\u53F0\u7C7B\u578B\uFF1A");

		comboType = new Combo(composite_2, SWT.READ_ONLY);
		comboType.setBounds(66, 3, 100, 25);
		String[] projects = new String[0];
		try {
			String source = ProjectCofigs.getParameter("platform").replace(";", "_");
			array = StringUtil.splitString(source, '_');
			projects = new String[array.length / 2];
			for (int ai = 0; ai < array.length / 2; ai++) {
				projects[ai] = array[ai * 2];

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		comboType.setItems(projects);
		if (projects.length > 0) {
			comboType.select(0);
		}

		Composite composite_16 = new Composite(composite_1, SWT.NONE);
		composite_16.setLayout(null);

		Label label_6 = new Label(composite_16, SWT.NONE);
		label_6.setBounds(3, 3, 64, 17);
		label_6.setText(" \u6E38\u620F\u5206\u533A\uFF1A");

		comboAppName = new Combo(composite_16, SWT.READ_ONLY);
		comboAppName.setBounds(70, 3, 100, 25);
		gsArray = StringUtil.splitString(ProjectCofigs.getParameter("gameServers"), ',');
		array = new String[gsArray.length];
		for (int ai = 0; ai < gsArray.length; ai++) {
			array[ai] = (ai + 1) + ":" + gsArray[ai];
		}
		comboAppName.setItems(array);
		if (array.length > 0) {
			comboAppName.select(0);
		}

		Composite composite_6 = new Composite(groupOpreate, SWT.BORDER);
		composite_6.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_9 = new Composite(composite_6, SWT.NONE);
		composite_9.setLayout(null);

		Label label_4 = new Label(composite_9, SWT.NONE);
		label_4.setBounds(3, 3, 60, 17);
		label_4.setText("\u6570\u636E\u5E93\u540D\uFF1A");

		textDbName = new Text(composite_9, SWT.BORDER);
		textDbName.setBounds(66, 3, 100, 23);

		Composite composite_10 = new Composite(composite_6, SWT.NONE);
		composite_10.setLayout(null);

		Label lblIp = new Label(composite_10, SWT.NONE);
		lblIp.setBounds(3, 3, 60, 17);
		lblIp.setText("IP\u5730\u5740\uFF1A");

		textDbIp = new Text(composite_10, SWT.BORDER);
		textDbIp.setBounds(66, 3, 100, 23);

		Composite composite_11 = new Composite(composite_6, SWT.NONE);
		composite_11.setLayout(null);

		Label label_5 = new Label(composite_11, SWT.NONE);
		label_5.setBounds(3, 3, 60, 17);
		label_5.setText("\u7AEF\u53E3\u53F7\uFF1A");

		textDbPort = new Text(composite_11, SWT.BORDER);
		textDbPort.setBounds(66, 3, 100, 23);

		Composite composite_12 = new Composite(composite_6, SWT.NONE);
		composite_12.setLayout(null);

		Label lblNewLabel = new Label(composite_12, SWT.NONE);
		lblNewLabel.setBounds(3, 3, 60, 17);
		lblNewLabel.setText("\u7528\u6237\u540D\uFF1A");

		textDbUser = new Text(composite_12, SWT.BORDER);
		textDbUser.setBounds(66, 3, 100, 23);

		Composite composite_13 = new Composite(composite_6, SWT.NONE);
		composite_13.setLayout(null);

		Label lblNewLabel_1 = new Label(composite_13, SWT.NONE);
		lblNewLabel_1.setBounds(3, 3, 60, 17);
		lblNewLabel_1.setText("\u5BC6\u7801\uFF1A");

		textDbPassword = new Text(composite_13, SWT.BORDER);
		textDbPassword.setBounds(66, 3, 100, 23);

		Composite composite_14 = new Composite(groupOpreate, SWT.NONE);
		composite_14.setLayout(null);

		Button button_5 = new Button(composite_14, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				appConfig=null;
				updateUI();
			}
		});
		button_5.setBounds(20, 3, 60, 27);
		button_5.setText(" \u91CD\u7F6E");

		buttonOperate = new Button(composite_14, SWT.NONE);
		buttonOperate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addOrUpdate();
			}
		});
		buttonOperate.setBounds(120, 3, 50, 27);
		buttonOperate.setText("\u6DFB\u52A0");

		tableViewer.setContentProvider(new TableAppConfigContentProvider());
		tableViewer.setLabelProvider(new TableAppConfigLableProvider());
	}

	private void addOrUpdate() {
		String dbName = textDbName.getText().trim();
		String dbIp = textDbIp.getText().trim();
		int dbPort = 0;
		try {
			dbPort = Integer.parseInt(textDbPort.getText().trim());
		} catch (Exception e) {
			MyDialog.openError("数据库端口必须为数字");
			return;
		}
		String dbUser = textDbUser.getText().trim();
		String dbPassword = textDbPassword.getText().trim();
		if (dbName.length() < 1) {
			MyDialog.openError("请输入数据库名称");
			return;
		}
		if (dbIp.length() < 1) {
			MyDialog.openError("请输入数据库IP");
			return;
		}
		if (dbPort < 1) {
			MyDialog.openError("请输入数据库端口");
			return;
		}
		if (dbUser.length() < 1) {
			MyDialog.openError("请输入数据库用户名");
			return;
		}
		if (dbPassword.length() < 1) {
			MyDialog.openError("请输入数据库密码");
			return;
		}
		int projectId = 0;
		try {
			projectId = Integer.parseInt(platformValue.get(comboType.getText()));
		} catch (Exception e) {
			MyDialog.openError("平台类型出错");
			return;
		}

		String[] arrayStr = StringUtil.splitString(comboAppName.getText(), ':');
		if (arrayStr == null || arrayStr.length != 2) {
			MyDialog.openError("游戏分区格式错误");
			return;
		}
		int appId = 0;
		try {
			appId = projectId * 10000 + Integer.parseInt(arrayStr[0]);
		} catch (Exception e) {
			MyDialog.openError("游戏分区编号异常");
			return;
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("platform", projectId + "");
		map.put("appId", appId + "");
		map.put("appName", arrayStr[1]);
		map.put("dbName", dbName);
		map.put("dbIp", dbIp);
		map.put("dbPort", dbPort + "");
		map.put("dbUser", dbUser);
		map.put("dbPassword", dbPassword);

		String url = ProjectCofigs.getParameter("datacenter_url") + "/operateAppConfig";
		if (appConfig == null) {//
			map.put("operate", "add");
			if (!MyDialog.openConfirm("是否要添加新配置?")) {
				return;
			}
			if (!MyDialog.openConfirm("是否要添加新配置?")) {
				return;
			}
		} else {
			map.put("operate", "update");
			map.put("id", appConfig.getId() + "");
			if (!MyDialog.openConfirm("是否要更新配置?")) {
				return;
			}
			if (!MyDialog.openConfirm("是否要更新配置?")) {
				return;
			}
		}
		try {
			String result = HttpsUtil.request(url, HttpsUtil.httpBuildQuery(map));
			System.out.println("result=" + result);
			JSONObject json = JSONObject.fromObject(result);
			if ("ok".equalsIgnoreCase(json.getString("code"))) {
				this.appConfig = null;
				if (MyDialog.openConfirm("操作成功，是否要刷新当前页")) {
					selectCharge(page);
				}
			} else {
				MyDialog.openInformation(json.getString("message"));
			}
		} catch (Exception e) {
			LoggerError.error("操作数据异常#", e);
			MyDialog.openInformation("操作数据异常#" + e.getMessage());
		}
		updateUI();
	}

	public void updateUI() {
		if (this.appConfig == null) {
			this.comboType.select(0);
			this.comboAppName.select(0);
			this.textDbIp.setText("");
			this.textDbName.setText("");
			this.textDbPassword.setText("");
			this.textDbPort.setText("");
			this.textDbUser.setText("");
			this.groupOpreate.setText("添加新配置");
			this.buttonOperate.setText("添加");
		} else {
			String platform = "";
			Set<String> keys = platformValue.keySet();
			for (String key : keys) {
				if ((appConfig.getProjectId() + "").equals(platformValue.get(key))) {
					platform = key;
					break;
				}
			}
			this.comboType.setText(platform);
			this.comboAppName.setText((appConfig.getAppId() % 10000) + ":" + appConfig.getAppName());
			this.textDbIp.setText(appConfig.getDbIp());
			this.textDbName.setText(appConfig.getDbName());
			this.textDbPassword.setText(appConfig.getDbPass());
			this.textDbPort.setText(appConfig.getDbPort() + "");
			this.textDbUser.setText(appConfig.getDbUser());
			this.groupOpreate.setText("更新配置【ID:" + appConfig.getId() + "】");
			this.buttonOperate.setText("更新");
		}
		groupOpreate.layout();
	}

	protected void selectCharge(int page) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", page + "");
		map.put("platform", platformValue.get(comboPlatform.getText()));
		String[] arrayStr = StringUtil.splitString(comboGameServer.getText(), ':');
		if (arrayStr.length == 1) {
			map.put("serverTag", arrayStr[0]);
		} else if (arrayStr.length > 1) {
			map.put("serverTag", arrayStr[1]);
		} else {
			map.put("serverTag", comboGameServer.getText());
		}

		try {
			String result = HttpsUtil.request(ProjectCofigs.getParameter("datacenter_url") + "/selectAppConfig", HttpsUtil.httpBuildQuery(map));
			JSONObject json = JSONObject.fromObject(result);
			if ("ok".equalsIgnoreCase(json.getString("code"))) {
				this.page = json.getInt("page");
				this.totalPage = json.getInt("totalPage");
				group_2.setText("第" + page + "页 共" + totalPage + "页");
				JSONArray array = json.getJSONArray("datas");
				List<AppConfigs> list = new ArrayList<AppConfigs>();
				for (int ai = 0; ai < array.size(); ai++) {
					list.add((AppConfigs) JSONObject.toBean(array.getJSONObject(ai), AppConfigs.class));
				}
				tableViewer.setInput(list);
				MyDialog.openInformation("查询完成");
			} else {
				MyDialog.openInformation(json.getString("message"));
			}
		} catch (Exception e) {
			LoggerError.error("查询数据异常#", e);
			MyDialog.openInformation("查询数据异常#" + e.getMessage());
		}
	}
}
