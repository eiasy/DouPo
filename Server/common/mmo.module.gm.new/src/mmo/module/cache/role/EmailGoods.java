package mmo.module.cache.role;

import mmo.common.config.goods.GoodsConfig;

public class EmailGoods {
	private int    id;
	private int    goodsId;
	private String name;
	private int    count;
	private byte   quality;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getInfo() {
		return name + ", " + count;
	}

	public byte getQuality() {
		return quality;
	}

	public void setQuality(byte quality) {
		this.quality = quality;
	}

	@Override
	public String toString() {
		return "[名称：" + name + ", 数量：" + count + ", 品阶：" + GoodsConfig.Quality.getQualityName(quality) + "]";
	}
}
