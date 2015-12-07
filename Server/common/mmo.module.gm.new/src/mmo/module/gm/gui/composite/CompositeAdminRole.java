package mmo.module.gm.gui.composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.module.datacenter.bean.InstPlayer;
import mmo.module.gm.bean.BeanPushMessage;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.provider.table.event.player.TablePlayerContentProvider;
import mmo.module.gm.gui.provider.table.event.player.TablePlayerLableProvider;
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

public class CompositeAdminRole extends GmComposite {
//	String[] platforms = new String[] { "安卓混服", "IOS", "掌阅专服" };
	static {
	}

	private Table table;
	private Text textRoleName;
	private Text textUserId;
	private Combo comboPlatform;
	private int totalPage;
	private int page;
	private TableViewer tableViewer;
	private Composite composite_15;
	private Group group_2;
	private Combo comboGameServer;
	private StyledText descText;

	public CompositeAdminRole(Composite parent, int style) {
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
		for (int ai = 0; ai < gsArray.length; ai++) {
			gsArray[ai] = (ai + 1) + ":" + gsArray[ai];
		}
		comboGameServer.setItems(gsArray);
		if (gsArray.length > 0) {
			comboGameServer.select(0);
		}

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
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn_24 = new TableColumn(table, SWT.NONE);
		tableColumn_24.setText("\u5206\u533A");
		tcl_composite.setColumnData(tableColumn_24, new ColumnPixelData(40, true, true));

		TableColumn tableColumn_25 = new TableColumn(table, SWT.NONE);
		tableColumn_25.setText("\u6E20\u9053");
		tcl_composite.setColumnData(tableColumn_25, new ColumnPixelData(56, true, true));

		TableColumn tblclmnOpenid_1 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnOpenid_1, new ColumnPixelData(36, true, true));
		tblclmnOpenid_1.setText("OPEN_ID");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn, new ColumnPixelData(49, true, true));
		tableColumn.setText("\u89D2\u8272\u7F16\u53F7");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_2, new ColumnPixelData(60, true, true));
		tableColumn_2.setText("\u89D2\u8272\u540D\u79F0");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_3, new ColumnPixelData(38, true, true));
		tableColumn_3.setText("\u7B49\u7EA7");

		TableColumn tblclmnRmb = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnRmb, new ColumnPixelData(46, true, true));
		tblclmnRmb.setText("\u5143\u5B9D");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setText("\u94DC\u94B1");
		tcl_composite.setColumnData(tableColumn_5, new ColumnPixelData(41, true, true));

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setText("\u7ECF\u9A8C");
		tcl_composite.setColumnData(tableColumn_6, new ColumnPixelData(50, true, true));

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setText("\u4F53\u529B");
		tcl_composite.setColumnData(tableColumn_1, new ColumnPixelData(63, true, true));

		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setText("\u6700\u5927\u4F53\u529B");
		tcl_composite.setColumnData(tableColumn_7, new ColumnPixelData(57, true, true));

		TableColumn tblclmnOpenid = new TableColumn(table, SWT.NONE);
		tblclmnOpenid.setText("\u8010\u529B");
		tcl_composite.setColumnData(tblclmnOpenid, new ColumnPixelData(63, true, true));

		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setText("\u6700\u5927\u8010\u529B");
		tcl_composite.setColumnData(tableColumn_8, new ColumnPixelData(61, true, true));

		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setText("\u53EF\u4E0A\u9635\u5361\u724C\u6570");
		tcl_composite.setColumnData(tableColumn_9, new ColumnPixelData(59, true, true));

		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setText("\u6700\u5927\u4E0A\u9635\u5361\u724C\u6570");
		tcl_composite.setColumnData(tableColumn_10, new ColumnPixelData(56, true, true));

		TableColumn tblclmnVip = new TableColumn(table, SWT.NONE);
		tblclmnVip.setText("\u5F15\u5BFC\u6B65\u9AA4");
		tcl_composite.setColumnData(tblclmnVip, new ColumnPixelData(59, true, true));

		TableColumn tblclmnImei = new TableColumn(table, SWT.NONE);
		tblclmnImei.setText("\u7AE0\u8282ID");
		tcl_composite.setColumnData(tblclmnImei, new ColumnPixelData(38, true, true));

		TableColumn tblclmnOs = new TableColumn(table, SWT.NONE);
		tblclmnOs.setText("\u5173\u5361ID");
		tcl_composite.setColumnData(tblclmnOs, new ColumnPixelData(30, true, true));

		TableColumn tblclmnProxy = new TableColumn(table, SWT.NONE);
		tblclmnProxy.setText("\u5F02\u706B\u6293\u53D6\u89C4\u5219ID");
		tcl_composite.setColumnData(tblclmnProxy, new ColumnPixelData(50, true, true));

		TableColumn tblclmnIdfa_1 = new TableColumn(table, SWT.NONE);
		tblclmnIdfa_1.setText("\u5F02\u706B\u5B9E\u4F8B");
		tcl_composite.setColumnData(tblclmnIdfa_1, new ColumnPixelData(87, true, true));

		TableColumn tblclmnIdfa = new TableColumn(table, SWT.NONE);
		tblclmnIdfa.setText("VIP");
		tcl_composite.setColumnData(tblclmnIdfa, new ColumnPixelData(42, true, true));

		TableColumn tableColumn_13 = new TableColumn(table, SWT.NONE);
		tableColumn_13.setText("\u5143\u6C14");
		tcl_composite.setColumnData(tableColumn_13, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_14 = new TableColumn(table, SWT.NONE);
		tableColumn_14.setText("\u706B\u80FD");
		tcl_composite.setColumnData(tableColumn_14, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_15 = new TableColumn(table, SWT.NONE);
		tableColumn_15.setText("\u526F\u672C\u80DC\u5229\u6B21\u6570");
		tcl_composite.setColumnData(tableColumn_15, new ColumnPixelData(56, true, true));

		TableColumn tableColumn_16 = new TableColumn(table, SWT.NONE);
		tableColumn_16.setText("\u5F53\u65E5\u8D2D\u4E70\u4F53\u529B\u6B21\u6570");
		tcl_composite.setColumnData(tableColumn_16, new ColumnPixelData(39, true, true));

		TableColumn tableColumn_17 = new TableColumn(table, SWT.NONE);
		tableColumn_17.setText("\u5F53\u65E5\u8D2D\u4E70\u8010\u529B\u6B21\u6570");
		tcl_composite.setColumnData(tableColumn_17, new ColumnPixelData(49, true, true));

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setText("\u6700\u540E\u8D2D\u4E70\u4F53\u529B\u65F6\u95F4");
		tcl_composite.setColumnData(tableColumn_4, new ColumnPixelData(75, true, true));

		TableColumn tableColumn_11 = new TableColumn(table, SWT.NONE);
		tableColumn_11.setText("\u6700\u540E\u8D2D\u4E70\u8010\u529B\u65F6\u95F4");
		tcl_composite.setColumnData(tableColumn_11, new ColumnPixelData(67, true, true));

		TableColumn tableColumn_12 = new TableColumn(table, SWT.NONE);
		tableColumn_12.setText("\u5173\u5361\u5F15\u5BFC\u6B65\u9AA4");
		tcl_composite.setColumnData(tableColumn_12, new ColumnPixelData(71, true, true));

		TableColumn tableColumn_18 = new TableColumn(table, SWT.NONE);
		tableColumn_18.setText("\u6D17\u6FA1\u65F6\u95F4");
		tcl_composite.setColumnData(tableColumn_18, new ColumnPixelData(63, true, true));

		TableColumn tableColumn_19 = new TableColumn(table, SWT.NONE);
		tableColumn_19.setText("\u6BCF\u65E5\u4EFB\u52A1\u65F6\u95F4");
		tcl_composite.setColumnData(tableColumn_19, new ColumnPixelData(87, true, true));

		TableColumn tableColumn_20 = new TableColumn(table, SWT.NONE);
		tableColumn_20.setText("\u5934\u50CF\u5361\u724C");
		tcl_composite.setColumnData(tableColumn_20, new ColumnPixelData(56, true, true));

		TableColumn tblclmnVip_1 = new TableColumn(table, SWT.NONE);
		tblclmnVip_1.setText("VIP\u793C\u5305");
		tcl_composite.setColumnData(tblclmnVip_1, new ColumnPixelData(55, true, true));

		TableColumn tableColumn_21 = new TableColumn(table, SWT.NONE);
		tableColumn_21.setText("\u62C9\u9ED1");
		tcl_composite.setColumnData(tableColumn_21, new ColumnPixelData(38, true, true));

		TableColumn tableColumn_22 = new TableColumn(table, SWT.NONE);
		tableColumn_22.setText("\u9996\u5145\u793C\u5305");
		tcl_composite.setColumnData(tableColumn_22, new ColumnPixelData(57, true, true));

		TableColumn tableColumn_23 = new TableColumn(table, SWT.NONE);
		tableColumn_23.setText("\u7F8E\u4EBA\u7F20\u7EF5\u65F6\u95F4");
		tcl_composite.setColumnData(tableColumn_23, new ColumnPixelData(150, true, true));

		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_2.heightHint = 81;
		composite_2.setLayoutData(gd_composite_2);

		descText = new StyledText(composite_2, SWT.BORDER | SWT.FULL_SELECTION | SWT.WRAP);
		descText.setEditable(false);

		tableViewer.setContentProvider(new TablePlayerContentProvider());
		tableViewer.setLabelProvider(new TablePlayerLableProvider());
	}

	protected void selectCharge(int page) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", page + "");
		map.put("platform", platformValue.get(comboPlatform.getText()));
		// map.put("serverTag", comboGameServer.getText());
		String[] arrayStr = StringUtil.splitString(comboGameServer.getText(), ':');
		if (arrayStr.length == 1) {
			map.put("serverTag", arrayStr[0]);
		} else if (arrayStr.length > 1) {
			map.put("serverTag", arrayStr[1]);
		} else {
			map.put("serverTag", comboGameServer.getText());
		}
		map.put("userId", textUserId.getText());
		map.put("roleName", textRoleName.getText());
		try {
			String result = HttpsUtil.request(ProjectCofigs.getParameter("datacenter_url") + "/selectPlayer", HttpsUtil.httpBuildQuery(map));
			System.out.println("result="+result);
			JSONObject json = JSONObject.fromObject(result);
			if ("ok".equalsIgnoreCase(json.getString("code"))) {
				this.page = json.getInt("page");
				this.totalPage = json.getInt("totalPage");
				group_2.setText("第" + page + "页 共" + totalPage + "页");
				JSONArray array = json.getJSONArray("datas");
				List<InstPlayer> list = new ArrayList<InstPlayer>();
				for (int ai = 0; ai < array.size(); ai++) {
					list.add((InstPlayer) JSONObject.toBean(array.getJSONObject(ai), InstPlayer.class));
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
