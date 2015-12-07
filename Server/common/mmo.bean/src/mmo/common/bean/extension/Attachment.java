package mmo.common.bean.extension;

public class Attachment {
	/** 物品编号 */
	private int goodsId;
	/** 物品数量 */
	private int count;

	public Attachment() {

	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
