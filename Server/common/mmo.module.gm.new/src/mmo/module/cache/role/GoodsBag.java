package mmo.module.cache.role;

import mmo.module.gm.bean.TreeNode;

public class GoodsBag extends TreeNode {
	private short  bagId;
	private String bagName;

	public GoodsBag(String name, TreeNode parent) {
		super(name, parent);
	}

	public short getBagId() {
		return bagId;
	}

	public void setBagId(short bagId) {
		this.bagId = bagId;
	}

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public String getNodeName() {
		return bagName + "(" + bagId + ")";
	}
}
