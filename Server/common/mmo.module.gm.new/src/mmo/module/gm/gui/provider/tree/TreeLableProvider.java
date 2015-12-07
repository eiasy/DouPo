package mmo.module.gm.gui.provider.tree;

import mmo.module.gm.bean.TreeNode;

import org.eclipse.jface.viewers.LabelProvider;

public class TreeLableProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof TreeNode)
			return ((TreeNode) element).getNodeName();
		else
			return super.getText(element);
	}
}
