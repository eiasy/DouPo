package mmo.common.bean.goods.price;

import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.role.Role;

/**
 * 物品价格组
 * 
 * @author fanren
 * 
 */
public class PriceGroup implements Cloneable {
	/** 该组中需要的货币及数量 */
	private List<PriceItem> priceItems   = new ArrayList<PriceItem>();
	/** 分组编号 */
	private short           id;
	/** 有效期（小时） */
	private int             effectPeriod = Integer.MAX_VALUE;

	public PriceGroup() {

	}

	/**
	 * 获取价格列表
	 * 
	 * @return
	 */
	public final List<PriceItem> getPriceItems() {
		return priceItems;
	}

	/**
	 * 增加一种货币价格
	 * 
	 * @param priceItem
	 */
	public final void addPriceItem(PriceItem priceItem) {
		if (priceItem != null) {
			priceItems.add(priceItem);
		}
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	/**
	 * 获取价格物品的数量
	 * 
	 * @return 数量
	 */
	public int getItemCount() {
		return priceItems.size();
	}

	public boolean validate(Role role, int count) {
		int isize = priceItems.size();
		PriceItem pi = null;
		for (int ii = 0; ii < isize; ii++) {
			pi = priceItems.get(ii);
			if (!pi.validate(role, count)) {
				return false;
			}
		}
		return false;
	}

	public void addPriceItems(List<PriceItem> itemList) {
		if (itemList != null) {
			priceItems.addAll(itemList);
		}
	}

	public PriceGroup clone() {
		PriceGroup o = null;
		try {
			o = (PriceGroup) super.clone();
			for (PriceItem pi : priceItems) {
				o.priceItems.add(pi.clone());
			}
			o.priceItems = new ArrayList<PriceItem>();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public int getEffectPeriod() {
		return effectPeriod;
	}

	public void setEffectPeriod(int effectPeriod) {
		this.effectPeriod = effectPeriod;
	}

	@Override
	public String toString() {
		return "PriceGroup [priceItems=" + priceItems + ", id=" + id + ", effectPeriod=" + effectPeriod + "]";
	}
}
