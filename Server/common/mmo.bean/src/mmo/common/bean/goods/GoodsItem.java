package mmo.common.bean.goods;

import mmo.common.bean.role.Role;

public class GoodsItem {
	private int  bindId;
	private int  goodsId;
	private int  count;
	private byte quality;
	private byte profession;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int itemID) {
		this.goodsId = itemID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int itemNum) {
		this.count = itemNum;
	}

	public byte getQuality() {
		return quality;
	}

	public void setQuality(byte quality) {
		this.quality = quality;
	}

	public byte getProfession() {
		return profession;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public int getBindId() {
		return bindId;
	}

	public void setBindId(int bindId) {
		this.bindId = bindId;
	}

	public boolean isAward(Role role) {
		return profession == 0 || profession == role.getProfession();
	}
}
