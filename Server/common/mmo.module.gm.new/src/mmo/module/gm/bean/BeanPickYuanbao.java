package mmo.module.gm.bean;

public class BeanPickYuanbao extends TreeNode {

	private int amount;

	public BeanPickYuanbao(int amount, TreeNode parent) {
		super("µµÎ»£º" + amount, parent);
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
