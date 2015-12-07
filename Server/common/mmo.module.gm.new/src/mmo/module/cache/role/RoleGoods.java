package mmo.module.cache.role;

import java.util.Date;

import mmo.common.config.goods.GoodsCate;
import mmo.common.config.goods.GoodsConfig;
import mmo.common.config.role.RoleRealmConfig;
import mmo.module.gm.bean.TreeNode;
import mmo.tools.util.DateUtil;

public class RoleGoods extends TreeNode {

	private int    id;
	private int    goodsId;
	private String name;
	private short  category;
	private String categoryName;
	private short  level;
	private byte   realm;
	private int    count;
	private long   timeGet;
	private long   timeEffect;
	private byte   pinZhi;
	private byte   quality;
	private byte   star;
	private short  strength;
	private int    holeCount;
	private short  bag;

	public RoleGoods(String name, TreeNode parent) {
		super(name, parent);
	}

	public short getBag() {
		return bag;
	}

	public void setBag(short bag) {
		this.bag = bag;
	}

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

	public short getCategory() {
		return category;
	}

	public void setCategory(short category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public byte getRealm() {
		return realm;
	}

	public void setRealm(byte realm) {
		this.realm = realm;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getTimeGet() {
		return timeGet;
	}

	public void setTimeGet(long timeGet) {
		this.timeGet = timeGet;
	}

	public long getTimeEffect() {
		return timeEffect;
	}

	public void setTimeEffect(long timeEffect) {
		this.timeEffect = timeEffect;
	}

	public byte getPinZhi() {
		return pinZhi;
	}

	public void setPinZhi(byte pinZhi) {
		this.pinZhi = pinZhi;
	}

	public byte getQuality() {
		return quality;
	}

	public void setQuality(byte quality) {
		this.quality = quality;
	}

	public byte getStar() {
		return star;
	}

	public void setStar(byte star) {
		this.star = star;
	}

	public short getStrength() {
		return strength;
	}

	public void setStrength(short strength) {
		this.strength = strength;
	}

	public int getHoleCount() {
		return holeCount;
	}

	public void setHoleCount(int holeCount) {
		this.holeCount = holeCount;
	}

	@Override
	public String getNodeName() {
		switch (category) {
			case GoodsCate.MONEY: {
				return "名称：" + name + ", 类型：" + categoryName + ", 数量：" + count;
			}
			default: {
				return "名称：" + name + ", 类型：" + categoryName + ", 级别：" + level + ", 境界：" + RoleRealmConfig.getName(realm) + ", 数量：" + count
				        + ", 获得时间：" + DateUtil.formatDate(new Date(timeGet)) + ", 有效期："
				        + (timeEffect < 0 ? "永久" : DateUtil.formatDate(new Date(timeEffect))) + ", 品质：" + pinZhi + ", 品阶："
				        + GoodsConfig.Quality.getQualityName(quality) + ", 星级：" + star + ", 强化：" + strength + ", 开孔数：" + holeCount;
			}
		}
	}
}
