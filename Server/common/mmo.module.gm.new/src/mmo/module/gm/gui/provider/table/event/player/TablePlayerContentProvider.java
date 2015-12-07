package mmo.module.gm.gui.provider.table.event.player;

import java.util.List;

import mmo.common.module.datacenter.bean.InstPlayer;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TablePlayerContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return ((List<InstPlayer>) inputElement).toArray();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {

	}

}
