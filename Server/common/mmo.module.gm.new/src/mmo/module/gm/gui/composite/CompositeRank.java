package mmo.module.gm.gui.composite;

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

public class CompositeRank extends GmComposite {
	private Table table;

	public CompositeRank(Composite parent, int style) {
	    super(parent, style);
	    setLayout(new GridLayout(1, false));
	    
	    Group group = new Group(this, SWT.NONE);
	    group.setLayout(new RowLayout(SWT.HORIZONTAL));
	    group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    
	    Label label = new Label(group, SWT.NONE);
	    label.setText("\u6E38\u620F");
	    
	    Label label_1 = new Label(group, SWT.NONE);
	    label_1.setText(">>");
	    
	    Label label_2 = new Label(group, SWT.NONE);
	    label_2.setText("\u670D\u52A1\u5668");
	    
	    Label label_3 = new Label(group, SWT.NONE);
	    label_3.setText(">>\u6392\u884C\u699C#");
	    
	    Group group_1 = new Group(this, SWT.NONE);
	    group_1.setLayout(new RowLayout(SWT.HORIZONTAL));
	    group_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    
	    Button button = new Button(group_1, SWT.NONE);
	    button.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    	}
	    });
	    button.setText("\u7B49\u7EA7");
	    
	    Button button_1 = new Button(group_1, SWT.NONE);
	    button_1.setText("\u5883\u754C");
	    
	    Button button_2 = new Button(group_1, SWT.NONE);
	    button_2.setText("\u5145\u503C");
	    
	    Button button_3 = new Button(group_1, SWT.NONE);
	    button_3.setText("\u6218\u6597\u529B");
	    
	    Group group_2 = new Group(this, SWT.NONE);
	    group_2.setText("\u6392\u884C\u699C");
	    group_2.setLayout(new FillLayout(SWT.HORIZONTAL));
	    group_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	    
	    Composite composite = new Composite(group_2, SWT.NONE);
	    TableColumnLayout tcl_composite = new TableColumnLayout();
	    composite.setLayout(tcl_composite);
	    
	    TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
	    table = tableViewer.getTable();
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);
	    
	    TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
	    tcl_composite.setColumnData(tableColumn_5, new ColumnPixelData(73, true, true));
	    tableColumn_5.setText("\u540D\u6B21");
	    
	    TableColumn tableColumn = new TableColumn(table, SWT.NONE);
	    tcl_composite.setColumnData(tableColumn, new ColumnPixelData(110, true, true));
	    tableColumn.setText("\u8D26\u53F7");
	    
	    TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
	    tcl_composite.setColumnData(tableColumn_1, new ColumnPixelData(111, true, true));
	    tableColumn_1.setText("\u89D2\u8272\u7F16\u53F7");
	    
	    TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
	    tcl_composite.setColumnData(tableColumn_2, new ColumnPixelData(120, true, true));
	    tableColumn_2.setText("\u89D2\u8272\u540D\u79F0");
	    
	    TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
	    tcl_composite.setColumnData(tableColumn_3, new ColumnPixelData(82, true, true));
	    tableColumn_3.setText("\u7B49\u7EA7");
	    
	    TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
	    tcl_composite.setColumnData(tableColumn_4, new ColumnPixelData(150, true, true));
	    tableColumn_4.setText("\u53C2\u8003\u503C");
	    // TODO Auto-generated constructor stub
    }

}
