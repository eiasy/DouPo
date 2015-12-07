package mmo.module.gm.gui.provider.tree;

import mmo.module.gm.bean.TreeNode;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TreeContentProvider implements ITreeContentProvider {
	public Object[] getElements(Object inputElement) {
		Object[] elements = null;
		if (inputElement instanceof TreeNode) {
			TreeNode c = (TreeNode) inputElement;
			elements = c.chidren.toArray();
		} else
			elements = new Object[0];

		return elements;
	}

	public boolean hasChildren(Object element) {
		boolean hasChildren = false;
		if (element instanceof TreeNode) {
			TreeNode c = (TreeNode) element;
			hasChildren = c.chidren.size() > 0;
		}

		return hasChildren;
	}

	public Object[] getChildren(Object parentElement) {
		Object[] children = null;

		if (parentElement instanceof TreeNode) {
			TreeNode c = (TreeNode) parentElement;
			children = c.chidren.toArray();
		} else
			children = new Object[0];

		return children;
	}

	public Object getParent(Object element) {
		return null;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}