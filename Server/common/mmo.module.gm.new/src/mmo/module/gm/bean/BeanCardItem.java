package mmo.module.gm.bean;

public class BeanCardItem {
	private int    goodsId;
	private String goodsName;
	private int    weight;
	private int    countSub;
	private int    countSup;

	public BeanCardItem() {
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getCountSub() {
		return countSub;
	}

	public void setCountSub(int countSub) {
		this.countSub = countSub;
	}

	public int getCountSup() {
		return countSup;
	}

	public void setCountSup(int countSup) {
		this.countSup = countSup;
	}

}
