package mmo.module.gm.gui.composite;

import mmo.common.protocol.command.ProGmClient_17000;
import mmo.common.protocol.command.sub.SubPro_15053_gmCmds;
import mmo.module.gm.bean.BeanRankList;
import mmo.module.gm.gui.provider.table.rank.TableRankContentProvider;
import mmo.module.gm.gui.provider.table.rank.TableRankLableProvider;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;

import org.apache.mina.core.buffer.IoBuffer;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.swtdesigner.SWTResourceManager;

public class CompositeRankList extends GmComposite {
	private Table       table;
	private Label       labelGame;
	private Label       labelServer;
	private Composite   compositeInfo;
	private TableViewer tableViewer;
	private Group       groupRoleList;

	public CompositeRankList(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		compositeInfo = new Group(composite, SWT.NONE);
		compositeInfo.setLayout(new RowLayout(SWT.HORIZONTAL));
		compositeInfo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label = new Label(compositeInfo, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label.setText("\u6E38\u620F\uFF1A");

		labelGame = new Label(compositeInfo, SWT.NONE);
		labelGame.setText("\u6E38\u620F");

		Label label_2 = new Label(compositeInfo, SWT.NONE);
		label_2.setText(">>");

		Label label_3 = new Label(compositeInfo, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_3.setText("\u670D\u52A1\u5668\uFF1A");

		labelServer = new Label(compositeInfo, SWT.NONE);
		labelServer.setText("\u670D\u52A1\u5668");

		Label label_4 = new Label(compositeInfo, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_4.setText(">>\u6392\u884C\u699C#");

		Button button = new Button(compositeInfo, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object data = getData(dataKey);
				if (data != null) {
					if (data instanceof BeanRankList) {
						BeanRankList account = (BeanRankList) data;
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17057_gmCmds);
						buf.putInt(account.getGameId());
						buf.putInt(account.getServerId());
						buf.putInt(SubPro_15053_gmCmds.P_42_REFRESH_RANK);
						buf.put(account.getRankType());
						sendData(buf, true);

						IoBuffer buf_0 = IoBuffer.getPacketBuffer();
						buf_0.setProtocol(ProGmClient_17000.P_17046_RANK_LIST);
						buf_0.putInt(account.getGameId());
						buf_0.putInt(account.getServerId());
						buf_0.put(account.getRankType());
						sendData(buf_0, false);
						LoggerDevelop.gm(GmOperate.CLIENT_GAME_REFRESH_RANK, GMNetManager.getGmUserId(), account.getGameId() + LoggerFilter.logDivide
						        + account.getServerId() + LoggerFilter.logDivide + account.getRankType());
					}
				}
			}
		});
		button.setText("\u5237\u65B0\u6392\u540D");

		Button btnBuf = new Button(compositeInfo, SWT.NONE);
		btnBuf.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Object data = getData(dataKey);
				if (data != null) {
					if (data instanceof BeanRankList) {
						BeanRankList account = (BeanRankList) data;
						IoBuffer buf_0 = IoBuffer.getPacketBuffer();
						buf_0.setProtocol(ProGmClient_17000.P_17046_RANK_LIST);
						buf_0.putInt(account.getGameId());
						buf_0.putInt(account.getServerId());
						buf_0.put(account.getRankType());
						sendData(buf_0, true);
						LoggerDevelop.gm(GmOperate.CLIENT_GAME_RANK_DATA, GMNetManager.getGmUserId(), account.getGameId() + LoggerFilter.logDivide
						        + account.getServerId() + LoggerFilter.logDivide + account.getRankType());
					}
				}
			}
		});
		btnBuf.setText("\u91CD\u65B0\u52A0\u8F7D");

		groupRoleList = new Group(composite, SWT.NONE);
		groupRoleList.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		groupRoleList.setText("\u6392\u884C\u699C");
		groupRoleList.setLayout(new GridLayout(1, false));
		groupRoleList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_4 = new Composite(groupRoleList, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		TableColumnLayout tcl_composite_4 = new TableColumnLayout();
		composite_4.setLayout(tcl_composite_4);

		tableViewer = new TableViewer(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn_12 = new TableColumn(table, SWT.NONE);
		tcl_composite_4.setColumnData(tableColumn_12, new ColumnPixelData(98, true, true));
		tableColumn_12.setText("\u8D26\u53F7");

		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tcl_composite_4.setColumnData(tblclmnid, new ColumnPixelData(90, true, true));
		tblclmnid.setText("\u89D2\u8272ID");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite_4.setColumnData(tableColumn_1, new ColumnPixelData(120, true, true));
		tableColumn_1.setText("\u89D2\u8272\u540D\u79F0");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite_4.setColumnData(tableColumn_3, new ColumnPixelData(61, true, true));
		tableColumn_3.setText("\u804C\u4E1A");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tcl_composite_4.setColumnData(tableColumn_2, new ColumnPixelData(53, true, true));
		tableColumn_2.setText("\u7B49\u7EA7");

		TableColumn tableColumn_13 = new TableColumn(table, SWT.NONE);
		tcl_composite_4.setColumnData(tableColumn_13, new ColumnPixelData(150, true, true));
		tableColumn_13.setText("\u6392\u884C\u503C");

		tableViewer.setContentProvider(new TableRankContentProvider());
		tableViewer.setLabelProvider(new TableRankLableProvider());

	}

	public void setUIData(Object data) {
		setData(dataKey, data);
		if (data != null) {
			if (data instanceof BeanRankList) {
				BeanRankList account = (BeanRankList) data;
				labelGame.setText(account.getGameName());
				labelServer.setText(account.getServerName());
				compositeInfo.layout();
				tableViewer.setInput(account.getRankList());
			} else {
				tableViewer.setInput(null);
			}
		} else {
			tableViewer.setInput(null);
		}
	}
}
