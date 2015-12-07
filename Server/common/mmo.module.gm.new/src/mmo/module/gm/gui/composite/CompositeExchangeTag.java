package mmo.module.gm.gui.composite;

import java.util.List;

import mmo.common.protocol.command.ProGmClient_17000;
import mmo.module.gm.gui.provider.table.tags.TableTagsContentProvider;
import mmo.module.gm.gui.provider.table.tags.TableTagsLableProvider;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;

import org.apache.mina.core.buffer.IoBuffer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.swtdesigner.SWTResourceManager;

public class CompositeExchangeTag extends GmComposite {

	private Table       table;
	private TableViewer tableViewer;
	private Composite   compositeGameInfo;

	public CompositeExchangeTag(Composite parent, int style) {
		super(parent, style);
		createPartControl(this);
	}

	public void setUIData(Object data) {
		setData(dataKey, data);
		if (data != null && data instanceof List) {
			tableViewer.setInput(data);
		}
	}

	public void createPartControl(Composite parent) {
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		compositeGameInfo = new Composite(composite, SWT.NONE);
		compositeGameInfo.setLayout(new RowLayout(SWT.HORIZONTAL));
		compositeGameInfo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(compositeGameInfo, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_1.setText("\u5151\u6362\u7801\u6807\u7B7E#");

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		TableColumnLayout tcl_composite_3 = new TableColumnLayout();
		composite_3.setLayout(tcl_composite_3);

		tableViewer = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tcl_composite_3.setColumnData(tblclmnid, new ColumnPixelData(82, true, true));
		tblclmnid.setText("\u6807\u7B7EID");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tcl_composite_3.setColumnData(tableColumn, new ColumnPixelData(100, true, true));
		tableColumn.setText("\u6807\u7B7E");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite_3.setColumnData(tableColumn_1, new ColumnPixelData(523, true, true));
		tableColumn_1.setText("\u6807\u7B7E\u63CF\u8FF0");

		tableViewer.setContentProvider(new TableTagsContentProvider());
		tableViewer.setLabelProvider(new TableTagsLableProvider());
		createContextMenu();
	}

	public void createContextMenu() {
		MenuManager menuBar = new MenuManager();
		menuBar.add(new EditorAction());
		Menu menu = menuBar.createContextMenu(getShell());
		tableViewer.getTable().setMenu(menu);
	}

	class EditorAction extends Action {
		public EditorAction() {
			setText("Ë¢ÐÂ");
		}

		@Override
		public void run() {
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(ProGmClient_17000.P_17052_EXCHANGE_TAG);
			sendData(buf, true);
			LoggerDevelop.gm(GmOperate.CLIENT_CODE_TAG, GMNetManager.getGmUserId(), "");
		}
	}

}
