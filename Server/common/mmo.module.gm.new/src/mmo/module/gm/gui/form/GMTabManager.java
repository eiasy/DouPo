package mmo.module.gm.gui.form;

import java.util.HashMap;
import java.util.Map;

import mmo.module.gm.config.TabItemConfig;
import mmo.module.gm.gui.composite.CompositeAdminCreateOrder;
import mmo.module.gm.gui.composite.CompositeAdminFinishCharge;
import mmo.module.gm.gui.composite.CompositeAdminGetGold;
import mmo.module.gm.gui.composite.CompositeAdminGoods;
import mmo.module.gm.gui.composite.CompositeAdminRole;
import mmo.module.gm.gui.composite.CompositeAppConfig;
import mmo.module.gm.gui.composite.CompositeManagerTree;
import mmo.module.gui.DataKey;
import mmo.module.gui.form.CFormII;
import mmo.module.gui.tab.MyTabItem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class GMTabManager extends CFormII {
	private Map<Object, MyTabItem> tabItems = new HashMap<Object, MyTabItem>();
	private CompositeManagerTree composite;

	public GMTabManager(Composite parent, int style) {
		super(parent, style);
		init();

	}

	private void init() {
		CTabItem tbtmGm = new CTabItem(getCenterTabFolder(), SWT.NONE);
		tbtmGm.setText("GM\u7BA1\u7406\u5668");
		getCenterTabFolder().setSelection(tbtmGm);

		composite = new CompositeManagerTree(getCenterTabFolder(), SWT.NONE);
		tbtmGm.setControl(composite);
	}

	public void updatePower() {
		composite.updatePower();
	}

	private void addTabItem(int itemId, MyTabItem tabItem) {
		tabItem.setData(DataKey.dataKey, itemId);
		tabItem.addDisposeListener(new DisposeAction());
		tabItems.put(itemId, tabItem);
	}

	public void switchTabItem(int itemId, Object data) {
		if (itemId < 1) {
			return;
		}
		MyTabItem tabItem = tabItems.get(itemId);
		if (tabItem == null) {
			tabItem = new MyTabItem(itemId, getRightTabFolder(), SWT.NONE);

			tabItem.getParent().setLayout(new FillLayout());
			ScrolledComposite scrolledComposite = new ScrolledComposite(tabItem.getParent(), SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
			scrolledComposite.setExpandVertical(true);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setAlwaysShowScrollBars(true);
			// scrolledComposite.setMinSize(composite.computeSize(1200, 1200));
			tabItem.setControl(scrolledComposite);

			switch (itemId) {
				case TabItemConfig.ITEM_1_ROLE_INFO: {
					scrolledComposite.setContent(new CompositeAdminRole(scrolledComposite, SWT.NONE));
					break;
				}
				case TabItemConfig.ITEM_2_ITEM_INFO: {
					scrolledComposite.setContent(new CompositeAdminGoods(scrolledComposite, SWT.NONE));
					break;
				}
				case TabItemConfig.ITEM_3_CHARGE_CREATE_ORDER: {
					scrolledComposite.setContent(new CompositeAdminCreateOrder(scrolledComposite, SWT.NONE));
					break;
				}
				case TabItemConfig.ITEM_5_CHARGE_FINISH: {
					scrolledComposite.setContent(new CompositeAdminFinishCharge(scrolledComposite, SWT.NONE));
					break;
				}
				case TabItemConfig.ITEM_6_GET_GOLD: {
					scrolledComposite.setContent(new CompositeAdminGetGold(scrolledComposite, SWT.NONE));
					break;
				}
				case TabItemConfig.ITEM_7_APP_CONFIG: {
					scrolledComposite.setContent(new CompositeAppConfig(scrolledComposite, SWT.NONE));
					break;
				}
				default: {
					return;
				}
			}
		}
		if (tabItem != null) {
			tabItem.setText(TabItemConfig.getTabItemTitle(itemId));
			tabItem.setShowClose(true);
			addTabItem(itemId, tabItem);
			tabItem.setUIData(data);
			getRightTabFolder().setSelection(tabItem);
		}
	}

	class DisposeAction implements DisposeListener {
		public void widgetDisposed(DisposeEvent e) {
			MyTabItem item = (MyTabItem) e.getSource();
			tabItems.remove(item.getData(DataKey.dataKey));
		}
	}
}
