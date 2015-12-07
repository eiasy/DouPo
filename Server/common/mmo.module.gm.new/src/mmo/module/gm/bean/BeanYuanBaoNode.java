package mmo.module.gm.bean;

public class BeanYuanBaoNode extends TreeNode {
	private int amount;
	private int award;
	private int weight;

	public BeanYuanBaoNode(int amount, int count, int weight, TreeNode parent) {
		super("½±Àø£º" + count + ", È¨ÖØ£º" + weight, parent);
		this.amount = amount;
		this.award = count;
		this.weight = weight;
	}

	public int getAward() {
		return award;
	}

	public void setCount(int count) {
		this.award = count;
	}

	public int getWeight() {
		return weight;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
