package mmo.module.gm.gui.composite;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mmo.common.protocol.command.GmPower;
import mmo.module.gm.AdminManager;
import mmo.module.gm.bean.TreeNode;
import mmo.module.gm.gui.listener.ListenTreeNode;
import mmo.module.gm.gui.provider.tree.TreeContentProvider;
import mmo.module.gm.gui.provider.tree.TreeLableProvider;
import mmo.module.gui.composite.CComposite;

import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class CompositeManagerTree extends CComposite {

	private TreeNode root = new TreeNode("root");

	private TreeViewer treeViewer;

	public CompositeManagerTree(Composite parent, int style) {
		super(parent, style);
		updateUI();
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new TreeColumnLayout());

		treeViewer = new TreeViewer(composite, SWT.BORDER);
		treeViewer.setLabelProvider(new TreeLableProvider());
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setInput(root);

		final Tree tree = treeViewer.getTree();
		tree.addSelectionListener(new ListenTreeNode());
		tree.setLinesVisible(true);
	}

	public void updatePower() {
		root.getChidren().clear();
		Map<Integer, List<Integer>> map = AdminManager.getMenus();
		Set<Integer> keys = map.keySet();
		for (int k : keys) {
			TreeNode game = new TreeNode(GmPower.getMenu(k), root);
			for (int ii : map.get(k)) {
				game.addChild(GmPower.getMenuItem(ii));
			}
		}

		treeViewer.setInput(root);
	}
}
