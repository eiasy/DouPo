package mmo.module.gm.gui.composite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mmo.common.bean.bi.EventDefault;
import mmo.common.system.GameSystem;
import mmo.module.gm.gui.provider.table.event.TableEventChargeLableProvider;
import mmo.module.gm.gui.provider.table.event.TableEventContentProvider;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.net.HttpsUtil;
import mmo.tools.util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
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

public class CompositeAdminCharge extends GmComposite {

	private static String[] events = new String[] { "", "创建订单", "完成支付", "游戏发货" };
	private static Map<String, String> eventTag = new HashMap<String, String>();
	static {
		eventTag.put("", "");
		eventTag.put("创建订单", "create_order");
		eventTag.put("完成支付", "channel_push");
		eventTag.put("游戏发货", "get_gold");
	}

	public static String getEventName(String et) {
		Set<String> keys = eventTag.keySet();
		for (String k : keys) {
			if (eventTag.get(k).equalsIgnoreCase(et)) {
				return k;
			}
		}
		return et;
	}

	private Table table;
	private Text textRoleName;
	private Text textChannelOrderId;
	private Text textUserId;
	private Text textRoleLevel;
	private Text textChannelSub;
	private Text textServerTag;
	private Text textIdfa;
	private Text textImei;
	private Text textOrderId;
	private Text textChannelId;
	private Combo comboPlatform;
	private Combo comboEvent;
	private int totalPage;
	private int page;
	private TableViewer tableViewer;
	private Text textStart;
	private Text textStop;
	private Composite composite_15;
	private Group group_2;

	public CompositeAdminCharge(Composite parent, int style) {
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

		Composite composite_8 = new Composite(group_1, SWT.NONE);
		composite_8.setLayout(null);

		Label label_1 = new Label(composite_8, SWT.NONE);
		label_1.setBounds(3, 3, 60, 17);
		label_1.setText("\u5206\u533A\u7F16\u53F7\uFF1A");

		textServerTag = new Text(composite_8, SWT.BORDER);
		textServerTag.setBounds(66, 3, 120, 23);

		Composite composite_9 = new Composite(group_1, SWT.NONE);
		composite_9.setLayout(null);

		Label label_2 = new Label(composite_9, SWT.NONE);
		label_2.setBounds(3, 3, 60, 17);
		label_2.setText("\u4E8B\u4EF6\u7C7B\u578B\uFF1A");

		comboEvent = new Combo(composite_9, SWT.NONE);
		comboEvent.setBounds(66, 3, 120, 25);
		comboEvent.setItems(events);

		Composite composite_10 = new Composite(group_1, SWT.NONE);
		composite_10.setLayout(null);

		Label lblIdfa = new Label(composite_10, SWT.NONE);
		lblIdfa.setBounds(3, 3, 39, 17);
		lblIdfa.setText("IDFA\uFF1A");

		textIdfa = new Text(composite_10, SWT.BORDER);
		textIdfa.setBounds(45, 3, 120, 23);

		Composite composite_11 = new Composite(group_1, SWT.NONE);
		composite_11.setLayout(null);

		Label lblImei = new Label(composite_11, SWT.NONE);
		lblImei.setBounds(3, 3, 39, 17);
		lblImei.setText("IMEI\uFF1A");

		textImei = new Text(composite_11, SWT.BORDER);
		textImei.setBounds(45, 3, 120, 23);

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

		Composite composite_3 = new Composite(composite_13, SWT.NONE);
		composite_3.setLayout(null);

		Label lblNewLabel_1 = new Label(composite_3, SWT.NONE);
		lblNewLabel_1.setBounds(3, 3, 60, 17);
		lblNewLabel_1.setText("\u6E20\u9053\u8BA2\u5355\uFF1A");

		textChannelOrderId = new Text(composite_3, SWT.BORDER);
		textChannelOrderId.setBounds(66, 3, 120, 23);

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

		Composite composite_5 = new Composite(composite_12, SWT.NONE);
		composite_5.setLayout(null);

		Label lblNewLabel_3 = new Label(composite_5, SWT.NONE);
		lblNewLabel_3.setBounds(3, 3, 60, 17);
		lblNewLabel_3.setText("\u89D2\u8272\u7B49\u7EA7\uFF1A");

		textRoleLevel = new Text(composite_5, SWT.BORDER);
		textRoleLevel.setBounds(66, 3, 120, 23);

		Composite composite_16 = new Composite(composite_12, SWT.NONE);
		composite_16.setLayout(null);

		Label label_4 = new Label(composite_16, SWT.NONE);
		label_4.setBounds(3, 3, 60, 17);
		label_4.setText("\u5F00\u59CB\u65F6\u95F4\uFF1A");

		textStart = new Text(composite_16, SWT.BORDER);
		textStart.setBounds(66, 3, 150, 23);
		textStart.setText(DateUtil.formatDate(new Date(System.currentTimeMillis() - GameSystem.ONE_HOUR)));

		Composite composite_17 = new Composite(composite_12, SWT.NONE);
		composite_17.setLayout(null);

		Label label_5 = new Label(composite_17, SWT.NONE);
		label_5.setBounds(3, 3, 60, 17);
		label_5.setText("\u622A\u81F3\u65F6\u95F4\uFF1A");

		textStop = new Text(composite_17, SWT.BORDER);
		textStop.setBounds(66, 3, 150, 23);
		textStop.setText(DateUtil.formatDate(new Date(System.currentTimeMillis())));

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

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnId, new ColumnPixelData(36, true, true));
		tblclmnId.setText("ID");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn, new ColumnPixelData(49, true, true));
		tableColumn.setText("\u4E8B\u4EF6\u6E90");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_1, new ColumnPixelData(36, true, true));
		tableColumn_1.setText("\u4E8B\u4EF6");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_2, new ColumnPixelData(60, true, true));
		tableColumn_2.setText("\u5E94\u7528\u6807\u7B7E");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_3, new ColumnPixelData(38, true, true));
		tableColumn_3.setText("\u5E73\u53F0");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_4, new ColumnPixelData(46, true, true));
		tableColumn_4.setText("\u5206\u533A");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setText("\u6E20\u9053");
		tcl_composite.setColumnData(tableColumn_5, new ColumnPixelData(41, true, true));

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setText("\u5305\u540D");
		tcl_composite.setColumnData(tableColumn_6, new ColumnPixelData(42, true, true));

		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setText("\u8D26\u53F7\u7F16\u53F7");
		tcl_composite.setColumnData(tableColumn_7, new ColumnPixelData(57, true, true));

		TableColumn tblclmnOpenid = new TableColumn(table, SWT.NONE);
		tblclmnOpenid.setText("OPEN_ID");
		tcl_composite.setColumnData(tblclmnOpenid, new ColumnPixelData(63, true, true));

		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setText("\u89D2\u8272\u7F16\u53F7");
		tcl_composite.setColumnData(tableColumn_8, new ColumnPixelData(61, true, true));

		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setText("\u89D2\u8272\u540D\u79F0");
		tcl_composite.setColumnData(tableColumn_9, new ColumnPixelData(59, true, true));

		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setText("\u89D2\u8272\u7B49\u7EA7");
		tcl_composite.setColumnData(tableColumn_10, new ColumnPixelData(56, true, true));

		TableColumn tblclmnVip = new TableColumn(table, SWT.NONE);
		tblclmnVip.setText("VIP");
		tcl_composite.setColumnData(tblclmnVip, new ColumnPixelData(31, true, true));

		TableColumn tblclmnImei = new TableColumn(table, SWT.NONE);
		tblclmnImei.setText("IMEI");
		tcl_composite.setColumnData(tblclmnImei, new ColumnPixelData(38, true, true));

		TableColumn tblclmnOs = new TableColumn(table, SWT.NONE);
		tblclmnOs.setText("OS");
		tcl_composite.setColumnData(tblclmnOs, new ColumnPixelData(30, true, true));

		TableColumn tblclmnProxy = new TableColumn(table, SWT.NONE);
		tblclmnProxy.setText("PROXY");
		tcl_composite.setColumnData(tblclmnProxy, new ColumnPixelData(50, true, true));

		TableColumn tblclmnIdfa_1 = new TableColumn(table, SWT.NONE);
		tblclmnIdfa_1.setText("IDFA");
		tcl_composite.setColumnData(tblclmnIdfa_1, new ColumnPixelData(35, true, true));

		TableColumn tblclmnIdfa = new TableColumn(table, SWT.NONE);
		tblclmnIdfa.setText("\u9053\u5177\u540D\u79F0");
		tcl_composite.setColumnData(tblclmnIdfa, new ColumnPixelData(42, true, true));

		TableColumn tableColumn_12 = new TableColumn(table, SWT.NONE);
		tableColumn_12.setText("\u63CF\u8FF0");
		tcl_composite.setColumnData(tableColumn_12, new ColumnPixelData(40, true, true));

		TableColumn tableColumn_13 = new TableColumn(table, SWT.NONE);
		tableColumn_13.setText("\u81EA\u6709\u8BA2\u5355");
		tcl_composite.setColumnData(tableColumn_13, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_14 = new TableColumn(table, SWT.NONE);
		tableColumn_14.setText("\u6E20\u9053\u8BA2\u5355");
		tcl_composite.setColumnData(tableColumn_14, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_15 = new TableColumn(table, SWT.NONE);
		tableColumn_15.setText("\u4ED8\u8D39\u91D1\u989D(\u5143)");
		tcl_composite.setColumnData(tableColumn_15, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_16 = new TableColumn(table, SWT.NONE);
		tableColumn_16.setText("\u5355\u4EF7(\u5143)");
		tcl_composite.setColumnData(tableColumn_16, new ColumnPixelData(39, true, true));

		TableColumn tableColumn_17 = new TableColumn(table, SWT.NONE);
		tableColumn_17.setText("\u65F6\u95F4");
		tcl_composite.setColumnData(tableColumn_17, new ColumnPixelData(150, true, true));

		tableViewer.setContentProvider(new TableEventContentProvider());
		tableViewer.setLabelProvider(new TableEventChargeLableProvider());
	}

	protected void selectCharge(int page) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", page + "");
		map.put("platform", platformValue.get(comboPlatform.getText()));
		map.put("serverTag", textServerTag.getText());
		map.put("eventTag", getEventTag());
		map.put("value1string", textImei.getText());
		map.put("value4string", textIdfa.getText());
		map.put("value7string", textOrderId.getText());
		map.put("value8string", textChannelOrderId.getText());
		map.put("channelTag", textChannelId.getText());
		map.put("channelSub", textChannelSub.getText());
		map.put("userId", textUserId.getText());
		map.put("roleName", textRoleName.getText());
		map.put("roleLevel", textRoleLevel.getText());
		map.put("start", textStart.getText());
		map.put("stop", textStop.getText());

		String result = HttpsUtil.request(ProjectCofigs.getParameter("datacenter_url") + "/selectCharge", HttpsUtil.httpBuildQuery(map));
		JSONObject json = JSONObject.fromObject(result);
		this.page = json.getInt("page");
		this.totalPage = json.getInt("totalPage");
		group_2.setText("第" + page + "页 共" + totalPage + "页");
		JSONArray array = json.getJSONArray("events");
		List<EventDefault> list = new ArrayList<EventDefault>();
		for (int ai = 0; ai < array.size(); ai++) {
			list.add((EventDefault) JSONObject.toBean(array.getJSONObject(ai), EventDefault.class));
		}
		tableViewer.setInput(list);
	}

	private String getEventTag() {
		String tag = eventTag.get(comboEvent.getText());
		if (tag == null) {
			return comboEvent.getText();
		}
		return tag;
	}
}
