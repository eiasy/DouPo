package mmo.module.gm.gui.provider.table.event.appconfig;

import java.util.List;

import mmo.common.module.datacenter.bean.AppConfigs;
import mmo.common.module.datacenter.bean.InstPlayerRecharge;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TableAppConfigContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return ((List<AppConfigs>) inputElement).toArray();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {

	}

}
