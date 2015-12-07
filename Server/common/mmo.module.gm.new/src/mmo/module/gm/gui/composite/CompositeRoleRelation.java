package mmo.module.gm.gui.composite;

import mmo.module.gm.bean.AccountRole;
import mmo.module.gm.gui.provider.table.role.relation.TableRoleRelationContentProvider;
import mmo.module.gm.gui.provider.table.role.relation.TableRoleRelationLableProvider;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.swtdesigner.SWTResourceManager;

public class CompositeRoleRelation extends GmComposite {
	private Group       groupServerInfo;
	private Label       labelGameName;
	private Table       table;
	private TableViewer checkboxTableViewer;
	private Group       groupNotice;
	private Table       table_1;
	private Table       table_2;
	private Table       table_3;
	private TableViewer tableViewer_1;
	private TableViewer tableViewer;
	private TableViewer tableViewer_2;
	private Label       labelRole;

	public CompositeRoleRelation(Composite parent, int style) {
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
		labelSymbol_3.setText(">>\u89D2\u8272\u793E\u4EA4\u4FE1\u606F#");

		labelRole = new Label(groupServerInfo, SWT.NONE);

		Composite compositeEmailServer = new Composite(composite, SWT.NONE);
		compositeEmailServer.setLayout(new FillLayout(SWT.HORIZONTAL));
		compositeEmailServer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		SashForm form = new SashForm(compositeEmailServer, SWT.SMOOTH);
		form.setLayout(new FillLayout());

		Group group_1 = new Group(form, SWT.NONE);
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		group_1.setText("\u597D\u53CB\u5217\u8868");

		Composite composite_5 = new Composite(group_1, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));

		checkboxTableViewer = new TableViewer(composite_5, SWT.BORDER | SWT.FULL_SELECTION);
		table = checkboxTableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tblclmnId = tableViewerColumn.getColumn();
		tblclmnId.setWidth(43);
		tblclmnId.setText("ID");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn_1.getColumn();
		tableColumn.setWidth(46);
		tableColumn.setText("\u6635\u79F0");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumn_1 = tableViewerColumn_2.getColumn();
		tableColumn_1.setWidth(45);
		tableColumn_1.setText("\u804C\u4E1A");

		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumn_5 = tableViewerColumn_6.getColumn();
		tableColumn_5.setWidth(41);
		tableColumn_5.setText("\u6027\u522B");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumn_2 = tableViewerColumn_3.getColumn();
		tableColumn_2.setWidth(37);
		tableColumn_2.setText("\u7B49\u7EA7");

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tableColumn_3 = tableViewerColumn_4.getColumn();
		tableColumn_3.setWidth(37);
		tableColumn_3.setText("\u72B6\u6001");

		Group group_2 = new Group(form, SWT.NONE);
		group_2.setText("\u88AB\u5173\u6CE8");
		group_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_1 = new Composite(group_2, SWT.NONE);
		TableColumnLayout tcl_composite_1 = new TableColumnLayout();
		composite_1.setLayout(tcl_composite_1);

		tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer.getTable();
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnId_1 = tableViewerColumn_5.getColumn();
		tcl_composite_1.setColumnData(tblclmnId_1, new ColumnPixelData(43, true, true));
		tblclmnId_1.setText("ID");

		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_4 = tableViewerColumn_7.getColumn();
		tcl_composite_1.setColumnData(tableColumn_4, new ColumnPixelData(46, true, true));
		tableColumn_4.setText("\u6635\u79F0");

		TableViewerColumn tableViewerColumn_8 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_6 = tableViewerColumn_8.getColumn();
		tcl_composite_1.setColumnData(tableColumn_6, new ColumnPixelData(36, true, true));
		tableColumn_6.setText("\u804C\u4E1A");

		TableViewerColumn tableViewerColumn_9 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_7 = tableViewerColumn_9.getColumn();
		tcl_composite_1.setColumnData(tableColumn_7, new ColumnPixelData(36, true, true));
		tableColumn_7.setText("\u6027\u522B");

		TableViewerColumn tableViewerColumn_10 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_8 = tableViewerColumn_10.getColumn();
		tcl_composite_1.setColumnData(tableColumn_8, new ColumnPixelData(42, true, true));
		tableColumn_8.setText("\u7B49\u7EA7");

		TableViewerColumn tableViewerColumn_11 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_9 = tableViewerColumn_11.getColumn();
		tcl_composite_1.setColumnData(tableColumn_9, new ColumnPixelData(37, true, true));
		tableColumn_9.setText("\u72B6\u6001");

		Group group = new Group(form, SWT.NONE);
		group.setText("\u53D1\u51FA\u8BF7\u6C42");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_2 = new Composite(group, SWT.NONE);
		TableColumnLayout tcl_composite_2 = new TableColumnLayout();
		composite_2.setLayout(tcl_composite_2);

		tableViewer_1 = new TableViewer(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_2 = tableViewer_1.getTable();
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);

		TableViewerColumn tableViewerColumn_12 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tableColumn_10 = tableViewerColumn_12.getColumn();
		tableColumn_10.setText("ID");
		tcl_composite_2.setColumnData(tableColumn_10, new ColumnPixelData(33, true, true));

		TableViewerColumn tableViewerColumn_13 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tableColumn_11 = tableViewerColumn_13.getColumn();
		tableColumn_11.setText("\u6635\u79F0");
		tcl_composite_2.setColumnData(tableColumn_11, new ColumnPixelData(48, true, true));

		TableViewerColumn tableViewerColumn_17 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tableColumn_15 = tableViewerColumn_17.getColumn();
		tableColumn_15.setText("\u804C\u4E1A");
		tcl_composite_2.setColumnData(tableColumn_15, new ColumnPixelData(34, true, true));

		TableViewerColumn tableViewerColumn_14 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tableColumn_12 = tableViewerColumn_14.getColumn();
		tableColumn_12.setText("\u6027\u522B");
		tcl_composite_2.setColumnData(tableColumn_12, new ColumnPixelData(41, true, true));

		TableViewerColumn tableViewerColumn_15 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tableColumn_13 = tableViewerColumn_15.getColumn();
		tableColumn_13.setText("\u7B49\u7EA7");
		tcl_composite_2.setColumnData(tableColumn_13, new ColumnPixelData(41, true, true));

		TableViewerColumn tableViewerColumn_16 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tableColumn_14 = tableViewerColumn_16.getColumn();
		tableColumn_14.setText("\u72B6\u6001");
		tcl_composite_2.setColumnData(tableColumn_14, new ColumnPixelData(38, true, true));

		groupNotice = new Group(form, SWT.NONE);
		groupNotice.setText("\u88AB\u9080\u8BF7");
		groupNotice.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_3 = new Composite(groupNotice, SWT.NONE);
		TableColumnLayout tcl_composite_3 = new TableColumnLayout();
		composite_3.setLayout(tcl_composite_3);

		tableViewer_2 = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table_3 = tableViewer_2.getTable();
		table_3.setHeaderVisible(true);
		table_3.setLinesVisible(true);

		TableViewerColumn tableViewerColumn_18 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tableColumn_16 = tableViewerColumn_18.getColumn();
		tableColumn_16.setText("ID");
		tcl_composite_3.setColumnData(tableColumn_16, new ColumnPixelData(36, true, true));

		TableViewerColumn tableViewerColumn_19 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tableColumn_17 = tableViewerColumn_19.getColumn();
		tableColumn_17.setText("\u6635\u79F0");
		tcl_composite_3.setColumnData(tableColumn_17, new ColumnPixelData(54, true, true));

		TableViewerColumn tableViewerColumn_23 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tableColumn_21 = tableViewerColumn_23.getColumn();
		tableColumn_21.setText("\u804C\u4E1A");
		tcl_composite_3.setColumnData(tableColumn_21, new ColumnPixelData(36, true, true));

		TableViewerColumn tableViewerColumn_20 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tableColumn_18 = tableViewerColumn_20.getColumn();
		tableColumn_18.setText("\u6027\u522B");
		tcl_composite_3.setColumnData(tableColumn_18, new ColumnPixelData(38, true, true));

		TableViewerColumn tableViewerColumn_21 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tableColumn_19 = tableViewerColumn_21.getColumn();
		tableColumn_19.setText("\u7B49\u7EA7");
		tcl_composite_3.setColumnData(tableColumn_19, new ColumnPixelData(41, true, true));

		TableViewerColumn tableViewerColumn_22 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tableColumn_20 = tableViewerColumn_22.getColumn();
		tableColumn_20.setText("\u72B6\u6001");
		tcl_composite_3.setColumnData(tableColumn_20, new ColumnPixelData(40, true, true));

		checkboxTableViewer.setContentProvider(new TableRoleRelationContentProvider());
		checkboxTableViewer.setLabelProvider(new TableRoleRelationLableProvider());
		tableViewer.setContentProvider(new TableRoleRelationContentProvider());
		tableViewer.setLabelProvider(new TableRoleRelationLableProvider());

		tableViewer_1.setContentProvider(new TableRoleRelationContentProvider());
		tableViewer_1.setLabelProvider(new TableRoleRelationLableProvider());

		tableViewer_2.setContentProvider(new TableRoleRelationContentProvider());
		tableViewer_2.setLabelProvider(new TableRoleRelationLableProvider());

		form.setWeights(new int[] { 257, 257, 257, 257 });
	}

	public void setUIData(Object data) {
		setData(dataKey, data);
		labelRole.setText("");
		if (data != null) {
			if (data instanceof AccountRole) {
				AccountRole ar = (AccountRole) data;
				labelRole.setText("½ÇÉ«¡¾ID£º" + ar.getRoleId() + ", êÇ³Æ£º" + ar.getRoleName() + "¡¿");
				labelGameName.setText(ar.getGameName());

				checkboxTableViewer.setInput(ar.getMyFriend());
				tableViewer.setInput(ar.getJoinMe());
				tableViewer_1.setInput(ar.getRequestMe());
				tableViewer_2.setInput(ar.getMyRequest());
			}
			groupServerInfo.layout();
		}
	}

}
