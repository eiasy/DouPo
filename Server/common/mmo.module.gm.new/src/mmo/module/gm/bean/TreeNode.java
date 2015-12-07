package mmo.module.gm.bean;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	public TreeNode       parent;

	public String         nodeName;
	public List<TreeNode> chidren = new ArrayList<TreeNode>();

	public TreeNode(String name) {
		this.nodeName = name;
	}

	public TreeNode(String name, TreeNode parent) {
		this.nodeName = name;
		this.parent = parent;

		if (parent != null)
			parent.chidren.add(this);
	}

	public TreeNode(String name, TreeNode parent, List<TreeNode> children) {
		this.nodeName = name;
		this.parent = parent;
		this.chidren = children;

		if (parent != null)
			parent.chidren.add(this);
	}

	public void addChild(String childName) {
		new TreeNode(childName, this);
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public void setChidren(List<TreeNode> chidren) {
		this.chidren = chidren;
	}

	public TreeNode getChildNode(String nodeName) {
		return getChild(this, nodeName);
	}

	public List<TreeNode> getChidren() {
		return chidren;
	}

	public TreeNode getChild(String nodeName) {
		for (TreeNode child : chidren) {
			if (child.nodeName.equals(nodeName)) {
				return child;
			}
		}

		return null;
	}

	public TreeNode getChild(TreeNode parent, String nodeName) {
		for (TreeNode child : parent.chidren) {
			if (child.nodeName.equals(nodeName)) {
				return child;
			}
		}

		return null;
	}

	public boolean hasChild() {
		return this.chidren.size() > 0;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setName(String name) {
		this.nodeName = name;
	}

	public String toString() {
		return nodeName == null ? "" : nodeName;
	}

	public int hashCode() {
		return (nodeName == null) ? 0 : nodeName.hashCode();
	}
}