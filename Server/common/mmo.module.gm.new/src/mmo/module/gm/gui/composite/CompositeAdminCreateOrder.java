package mmo.module.gm.gui.composite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.module.datacenter.bean.ChargeOrderform;
import mmo.common.system.GameSystem;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.provider.table.event.order.TableCreateOrderContentProvider;
import mmo.module.gm.gui.provider.table.event.order.TableCreateOrderLableProvider;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import mmo.tools.util.DateUtil;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class CompositeAdminCreateOrder extends GmComposite {

	static {
	}

	private Table table;
	private Text textRoleName;
	private Text textUserId;
	private Text textChannelSub;
	private Text textServerTag;
	private Text textIdfa;
	private Text textImei;
	private Text textOrderId;
	private Text textChannelId;
	private Combo comboPlatform;
	private int totalPage;
	private int page;
	private TableViewer tableViewer;
	private Text textStart;
	private Text textStop;
	private Composite composite_15;
	private Group group_2;
	private Combo comboChargeSserver;
	private StyledText descText;

	public CompositeAdminCreateOrder(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_9 = new Composite(composite_3, SWT.NONE);
		composite_9.setLayout(null);

		Label label_2 = new Label(composite_9, SWT.NONE);
		label_2.setBounds(3, 3, 60, 17);
		label_2.setText("\u5145\u503C\u5206\u533A\uFF1A");

		comboChargeSserver = new Combo(composite_9, SWT.READ_ONLY);
		comboChargeSserver.setBounds(66, 3, 120, 25);
		String[] serverItems = StringUtil.splitString(ProjectCofigs.getParameter("chargeServer"), ',');
		comboChargeSserver.setItems(serverItems);
		if (serverItems.length > 0) {
			comboChargeSserver.select(0);
		}

		Composite composite_16 = new Composite(composite_3, SWT.NONE);
		composite_16.setLayout(null);

		Label label_4 = new Label(composite_16, SWT.NONE);
		label_4.setBounds(3, 3, 60, 17);
		label_4.setText("\u5F00\u59CB\u65F6\u95F4\uFF1A");

		textStart = new Text(composite_16, SWT.BORDER);
		textStart.setBounds(66, 3, 125, 23);
		textStart.setText(DateUtil.formatDate(new Date(System.currentTimeMillis() - GameSystem.ONE_HOUR)));

		Composite composite_17 = new Composite(composite_3, SWT.NONE);
		composite_17.setLayout(null);

		Label label_5 = new Label(composite_17, SWT.NONE);
		label_5.setBounds(3, 3, 60, 17);
		label_5.setText("\u622A\u81F3\u65F6\u95F4\uFF1A");

		textStop = new Text(composite_17, SWT.BORDER);
		textStop.setBounds(66, 3, 125, 23);
		textStop.setText(DateUtil.formatDate(new Date(System.currentTimeMillis())));

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

		Composite composite_8 = new Composite(group_1, SWT.NONE);
		composite_8.setLayout(null);

		Label label_1 = new Label(composite_8, SWT.NONE);
		label_1.setBounds(3, 3, 60, 17);
		label_1.setText("\u5206\u533A\u7F16\u53F7\uFF1A");

		textServerTag = new Text(composite_8, SWT.BORDER);
		textServerTag.setBounds(66, 3, 120, 23);

		Composite composite_10 = new Composite(group_1, SWT.NONE);
		composite_10.setLayout(null);

		Label lblIdfa = new Label(composite_10, SWT.NONE);
		lblIdfa.setBounds(3, 3, 39, 17);
		lblIdfa.setText("IDFA\uFF1A");

		textIdfa = new Text(composite_10, SWT.BORDER);
		textIdfa.setBounds(66, 3, 120, 23);

		Composite composite_11 = new Composite(group_1, SWT.NONE);
		composite_11.setLayout(null);

		Label lblImei = new Label(composite_11, SWT.NONE);
		lblImei.setBounds(3, 3, 39, 17);
		lblImei.setText("IMEI\uFF1A");

		textImei = new Text(composite_11, SWT.BORDER);
		textImei.setBounds(66, 3, 120, 23);

		Composite composite_13 = new Composite(this, SWT.BORDER);
		composite_13.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_13.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_2 = new Composite(composite_13, SWT.NONE);
		composite_2.setLayout(null);

		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(3, 3, 60, 17);
		label.setText("\u81EA\u6709\u8BA2\u5355\uFF1A");

		textOrderId = new Text(composite_2, SWT.BORDER);
		textOrderId.setBounds(66, 3, 120, 23);

		Composite composite_14 = new Composite(composite_13, SWT.NONE);
		composite_14.setLayout(null);

		Label label_3 = new Label(composite_14, SWT.NONE);
		label_3.setBounds(3, 3, 60, 17);
		label_3.setText("\u6E20\u9053\u7F16\u53F7\uFF1A");

		textChannelId = new Text(composite_14, SWT.BORDER);
		textChannelId.setBounds(66, 3, 120, 23);

		Composite composite_6 = new Composite(composite_13, SWT.NONE);
		composite_6.setLayout(null);

		Label lblNewLabel_4 = new Label(composite_6, SWT.NONE);
		lblNewLabel_4.setBounds(3, 3, 60, 17);
		lblNewLabel_4.setText("\u5B50\u6E20\u9053\u540D\uFF1A");

		textChannelSub = new Text(composite_6, SWT.BORDER);
		textChannelSub.setBounds(66, 3, 120, 23);

		Composite composite_12 = new Composite(this, SWT.BORDER);
		composite_12.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_4 = new Composite(composite_12, SWT.NONE);
		composite_4.setLayout(null);

		Label lblNewLabel_2 = new Label(composite_4, SWT.NONE);
		lblNewLabel_2.setBounds(3, 3, 60, 17);
		lblNewLabel_2.setText("\u7528\u6237\u8D26\u53F7\uFF1A");

		textUserId = new Text(composite_4, SWT.BORDER);
		textUserId.setBounds(66, 3, 120, 23);

		Composite composite_1 = new Composite(composite_12, SWT.NONE);
		composite_1.setLayout(null);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(3, 3, 64, 17);
		lblNewLabel.setText("\u89D2\u8272\u540D\u79F0\uFF1A");

		textRoleName = new Text(composite_1, SWT.BORDER);
		textRoleName.setBounds(70, 3, 120, 23);

		composite_15 = new Composite(this, SWT.NONE);
		composite_15.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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
				descText.setText(selection.getFirstElement().toString());
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();

				if (selection.isEmpty()) {
					return;
				}
				descText.setText(selection.getFirstElement().toString());
			}
		});

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnId, new ColumnPixelData(36, true, true));
		tblclmnId.setText("ID");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn, new ColumnPixelData(49, true, true));
		tableColumn.setText("\u6E38\u620F");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_1, new ColumnPixelData(36, true, true));
		tableColumn_1.setText("\u6E38\u620F\u5206\u533A");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_2, new ColumnPixelData(60, true, true));
		tableColumn_2.setText("\u81EA\u6709\u8BA2\u5355");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_3, new ColumnPixelData(38, true, true));
		tableColumn_3.setText("\u6E20\u9053");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_4, new ColumnPixelData(46, true, true));
		tableColumn_4.setText("\u5305\u540D");

		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tblclmnid.setText("\u8D26\u53F7ID");
		tcl_composite.setColumnData(tblclmnid, new ColumnPixelData(41, true, true));

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setText("\u6E20\u9053\u8D26\u53F7");
		tcl_composite.setColumnData(tableColumn_6, new ColumnPixelData(42, true, true));

		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setText("\u89D2\u8272\u7F16\u53F7");
		tcl_composite.setColumnData(tableColumn_7, new ColumnPixelData(57, true, true));

		TableColumn tblclmnOpenid = new TableColumn(table, SWT.NONE);
		tblclmnOpenid.setText("\u89D2\u8272\u540D");
		tcl_composite.setColumnData(tblclmnOpenid, new ColumnPixelData(63, true, true));

		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setText("\u89D2\u8272\u7B49\u7EA7");
		tcl_composite.setColumnData(tableColumn_8, new ColumnPixelData(61, true, true));

		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setText("\u9053\u5177\u7F16\u53F7");
		tcl_composite.setColumnData(tableColumn_9, new ColumnPixelData(59, true, true));

		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setText("\u9053\u5177\u540D");
		tcl_composite.setColumnData(tableColumn_10, new ColumnPixelData(56, true, true));

		TableColumn tblclmnVip = new TableColumn(table, SWT.NONE);
		tblclmnVip.setText("\u5355\u4EF7");
		tcl_composite.setColumnData(tblclmnVip, new ColumnPixelData(31, true, true));

		TableColumn tblclmnImei = new TableColumn(table, SWT.NONE);
		tblclmnImei.setText("\u6570\u91CF");
		tcl_composite.setColumnData(tblclmnImei, new ColumnPixelData(38, true, true));

		TableColumn tblclmnOs = new TableColumn(table, SWT.NONE);
		tblclmnOs.setText("\u72B6\u6001");
		tcl_composite.setColumnData(tblclmnOs, new ColumnPixelData(30, true, true));

		TableColumn tblclmnProxy = new TableColumn(table, SWT.NONE);
		tblclmnProxy.setText("OS");
		tcl_composite.setColumnData(tblclmnProxy, new ColumnPixelData(50, true, true));

		TableColumn tblclmnIdfa_1 = new TableColumn(table, SWT.NONE);
		tblclmnIdfa_1.setText("IMEI");
		tcl_composite.setColumnData(tblclmnIdfa_1, new ColumnPixelData(35, true, true));

		TableColumn tblclmnIdfa = new TableColumn(table, SWT.NONE);
		tblclmnIdfa.setText("\u5E8F\u5217\u53F7");
		tcl_composite.setColumnData(tblclmnIdfa, new ColumnPixelData(42, true, true));

		TableColumn tblclmnMac = new TableColumn(table, SWT.NONE);
		tblclmnMac.setText("MAC");
		tcl_composite.setColumnData(tblclmnMac, new ColumnPixelData(40, true, true));

		TableColumn tblclmnIdfa_2 = new TableColumn(table, SWT.NONE);
		tblclmnIdfa_2.setText("IDFA");
		tcl_composite.setColumnData(tblclmnIdfa_2, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_14 = new TableColumn(table, SWT.NONE);
		tableColumn_14.setText("\u521B\u5EFA\u65F6\u95F4");
		tcl_composite.setColumnData(tableColumn_14, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_15 = new TableColumn(table, SWT.NONE);
		tableColumn_15.setText("\u786E\u8BA4\u65F6\u95F4");
		tcl_composite.setColumnData(tableColumn_15, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_16 = new TableColumn(table, SWT.NONE);
		tableColumn_16.setText("\u786E\u8BA4\u5206\u533A");
		tcl_composite.setColumnData(tableColumn_16, new ColumnPixelData(69, true, true));

		Composite composite_5 = new Composite(this, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_5 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_5.heightHint = 78;
		composite_5.setLayoutData(gd_composite_5);

		descText = new StyledText(composite_5, SWT.BORDER | SWT.FULL_SELECTION | SWT.WRAP);
		descText.setEditable(false);

		tableViewer.setContentProvider(new TableCreateOrderContentProvider());
		tableViewer.setLabelProvider(new TableCreateOrderLableProvider());
	}

	protected void selectCharge(int page) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", page + "");
		map.put("chargeServer", comboChargeSserver.getText());
		map.put("start", textStart.getText());
		map.put("stop", textStop.getText());
		map.put("platform", platformValue.get(comboPlatform.getText()));
		map.put("serverTag", textServerTag.getText());
		map.put("idfa", textImei.getText());
		map.put("imei", textIdfa.getText());
		map.put("orderId", textOrderId.getText());
		map.put("channelTag", textChannelId.getText());
		map.put("channelSub", textChannelSub.getText());
		map.put("userId", textUserId.getText());
		map.put("roleName", textRoleName.getText());
		try {
			String result = HttpsUtil.request(ProjectCofigs.getParameter("datacenter_url") + "/selectCreateOrder", HttpsUtil.httpBuildQuery(map));
			System.out.println("result=" + result);
			JSONObject json = JSONObject.fromObject(result);
			if ("ok".equalsIgnoreCase(json.getString("code"))) {
				this.page = json.getInt("page");
				this.totalPage = json.getInt("totalPage");
				group_2.setText("第" + page + "页 共" + totalPage + "页");
				JSONArray array = json.getJSONArray("orders");
				List<ChargeOrderform> list = new ArrayList<ChargeOrderform>();
				for (int ai = 0; ai < array.size(); ai++) {
					list.add((ChargeOrderform) JSONObject.toBean(array.getJSONObject(ai), ChargeOrderform.class));
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
