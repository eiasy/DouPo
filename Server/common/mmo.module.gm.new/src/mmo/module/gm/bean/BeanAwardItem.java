package mmo.module.gm.bean;

public class BeanAwardItem {
	private int    goodsId;
	private String goodsName;
	private int    count;
	private byte   profession;
	private int    price;
	private int    priceNow;

	public BeanAwardItem() {
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public byte getProfession() {
		return profession;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPriceNow() {
		return priceNow;
	}

	public void setPriceNow(int priceNow) {
		this.priceNow = priceNow;
	}

}
