package mmo.common.bean.extension;

import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.role.Role;

public class NeedGoods {
	private List<GoodsItem> items = new ArrayList<GoodsItem>();

	public void addNeedGoods(int goodsId, int count) {
		GoodsItem item = new GoodsItem();
		item.setGoodsId(goodsId);
		item.setNeedCount(count);

		items.add(item);
	}

	/**
	 * 获取需要的材料[0:ID][1:COUNT]
	 * 
	 * @return
	 */
	public int[][] getNeedGoods() {
		GoodsItem item = null;
		int[][] values = new int[items.size()][2];
		for (int i = 0, size = items.size(); i < size; i++) {
			item = items.get(i);
			if (item != null) {
				values[i][0] = item.getGoodsId();
				values[i][1] = item.getNeedCount();
			}
		}
		return values;
	}

	public boolean hasEnoughGoods(Role userRole) {
		GoodsItem item = null;
		for (int i = 0, size = items.size(); i < size; i++) {
			item = items.get(i);
			if (item != null) {
				if (userRole.getGoodsCountByGoodsId(item.getGoodsId()) < item.getNeedCount()) {
					return false;
				}
			}
		}
		return true;
	}

	class GoodsItem {
		private int goodsId   = 0;
		private int needCount = 0;

		public int getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(int goodsId) {
			this.goodsId = goodsId;
		}

		public int getNeedCount() {
			return needCount;
		}

		public void setNeedCount(int needCount) {
			this.needCount = needCount;
		}
	}

}
