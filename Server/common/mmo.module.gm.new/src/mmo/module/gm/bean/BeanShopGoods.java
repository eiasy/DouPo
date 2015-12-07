package mmo.module.gm.bean;

public class BeanShopGoods {
	private int    goodsId;
	private String goodsName;
	private int    priceGoods;
	private String priceName;
	private int    price;
	private int    priceNow;
	private int    dayLimit;
	private int    totalLimit;
	private int    onceBuy;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BeanShopGoods() {
		// TODO Auto-generated constructor stub
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

	public int getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(int dayLimit) {
		this.dayLimit = dayLimit;
	}

	public int getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(int totalLimit) {
		this.totalLimit = totalLimit;
	}

	public int getPriceGoods() {
		return priceGoods;
	}

	public void setPriceGoods(int priceGoods) {
		this.priceGoods = priceGoods;
	}

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	public int getOnceBuy() {
		return onceBuy;
	}

	public void setOnceBuy(int onceBuy) {
		this.onceBuy = onceBuy;
	}

}
