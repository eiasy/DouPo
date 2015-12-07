package com.huayi.doupo.activity.luck;

import java.io.Serializable;

import com.huayi.doupo.base.util.base.StringUtil;

public class LuckItem implements Serializable{
	
	private static final long serialVersionUID = 91741678300786298L;
	private int id;
	private int type;
	private int itemId;
	private int count;
	private int rate1;
	private int rate2;
	private boolean limit;

	public LuckItem(String info, boolean limit) {
		this.limit = limit;
		int[] array = StringUtil.string2IntArray(info, '_');
		if (array != null) {
			switch (array.length) {
				case 3: {
					reset(array[0], array[1], array[2]);
					break;
				}
				case 4: {
					reset(array[0], array[1], array[2], array[3]);
					break;
				}
				case 5: {
					reset(array[0], array[1], array[2], array[3], array[4]);
					break;
				}
			}
		}
	}

	public LuckItem() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void reset(int type, int itemId, int count, int rate1, int rate2) {
		this.type = type;
		this.itemId = itemId;
		this.count = count;
		this.rate1 = rate1;
		this.rate2 = rate2;
	}

	public void reset(int type, int itemId, int count) {
		reset(type, itemId, count, 0, 0);
	}

	public void reset(int type, int itemId, int count, int rate1) {
		reset(type, itemId, count, rate1, 0);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRate1() {
		return rate1;
	}

	public void setRate1(int rate1) {
		this.rate1 = rate1;
	}

	public int getRate2() {
		return rate2;
	}

	public void setRate2(int rate2) {
		this.rate2 = rate2;
	}

	public boolean isLimit() {
		return limit;
	}

	public void setLimit(boolean limit) {
		this.limit = limit;
	}

}
