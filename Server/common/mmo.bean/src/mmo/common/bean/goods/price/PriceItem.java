package mmo.common.bean.goods.price;

import mmo.common.bean.role.Role;

/**
 * 物品价格项
 * 
 * @author fanren
 * 
 */
public class PriceItem implements Cloneable {
	/** 原货币编号 */
	private int goodsId;
	/** 价格 */
	private int count;
	/** 原价量 */
	private int normCount;
	/** 特价 */
	private int nowCount;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getNormCount() {
		return normCount;
	}

	public void setNormCount(int normCount) {
		this.normCount = normCount;
	}

	public int getNowCount() {
		return nowCount;
	}
	
	public int getNowPrice() {
		if (nowCount < 1) {
			return normCount;
		}
		return nowCount;
	}

	public void setNowCount(int nowCount) {
		this.nowCount = nowCount;
	}

	@Override
	public String toString() {
		return "PriceItem [normId=" + goodsId + ", normCount=" + normCount + ", nowCount=" + nowCount + "]";
	}

	public boolean validate(Role role, int count) {
		return role.getMoney(goodsId) >= nowCount * count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public PriceItem clone() {
		PriceItem o = null;
		try {
			o = (PriceItem) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
