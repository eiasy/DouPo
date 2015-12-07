package mmo.common.module.service.charge.appstore;

public class AppStoreGoods {
	private int    goodsId;
	private String goodsName;
	private String proId;
	private String IAPID;
	private int    price;
	private String desc;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getIAPID() {
		return IAPID;
	}

	public void setIAPID(String iAPID) {
		IAPID = iAPID;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
