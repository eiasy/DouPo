package com.huayi.doupo.activity.luck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.huayi.doupo.activity.RankData;
import com.huayi.doupo.base.model.InstLuckRank;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;

public class RankLuck extends RankData implements Serializable {

	private static final long serialVersionUID = -4738059893064963483L;
	private List<LuckItem> items = new ArrayList<LuckItem>();
	private int[] commonIndex = new int[0];
	private String infoForClient = "";
	private int limitIndex = -1;

	public List<LuckItem> getItems() {
		return items;
	}

	public void setItems(List<LuckItem> items) {
		this.items = items;
		resetRate();
	}

	private void resetRate() {
		int sub = 0;
		int sup = 0;
		limitIndex = -1;
		StringBuilder sb = new StringBuilder();
		if (items != null) {
			LuckItem item = null;
			for (int ii = 0; ii < items.size(); ii++) {
				item = items.get(ii);
				if (ii > 0) {
					sb.append(';');
				}
				if (item.isLimit()) {
					sb.append(item.getType()).append('_').append(item.getItemId()).append('_').append(item.getCount()).append("_1");
					this.limitIndex = ii;
				} else {
					sb.append(item.getType()).append('_').append(item.getItemId()).append('_').append(item.getCount()).append("_0");
					sup += items.get(ii).getRate2();
				}
			}
			infoForClient = sb.toString();
			int[] array = new int[sup];
			sup = 0;
			for (int ii = 0; ii < items.size(); ii++) {
				if (limitIndex == ii) {
					continue;
				}
				sup += items.get(ii).getRate2();
				for (; sub < sup; sub++) {
					array[sub] = ii;
				}
			}
			commonIndex = array;
		}
	}

	public String getInfoForClient() {
		return infoForClient;
	}

	public String toForServer() {
		StringBuilder sb = new StringBuilder();
		LuckItem item = null;
		for (int ii = 0; ii < items.size(); ii++) {
			item = items.get(ii);
			if (ii > 0) {
				sb.append(';');
			}
			if (item.isLimit()) {
				sb.append(item.getId()).append("_").append(item.getType()).append('_').append(item.getItemId()).append('_').append(item.getCount()).append('_').append(item.getRate1()).append('_').append(item.getRate2()).append("_1");
			} else {
				sb.append(item.getId()).append("_").append(item.getType()).append('_').append(item.getItemId()).append('_').append(item.getCount()).append('_').append(item.getRate1()).append('_').append(item.getRate2()).append("_0");
			}
		}
		return sb.toString();
	}

	public Object[] getAwardLuckItem(boolean free, InstLuckRank rl, int count) {
		Object[] data = new Object[count * 2 + 1];
		boolean checkLimit = limitIndex > -1;
		for (int ci = 0; ci < count; ci++) {
			if (checkLimit) {
				float rate = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.selectedChance);
				if (free) {
					rate = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.FreeselectedChance);
				} else {
					int subValue = DictMapUtil.getSysConfigIntValue(StaticSysConfig.luckyStarNum);
					if (rl.getRankValue() - rl.getLastLimitValue() > subValue) {
						float denominator = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.luckyCoefficient);
						float maxRate = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.luckyMaxNum);
						if (denominator < 1) {
							denominator = 1;
						}
						rate += (rl.getRankValue() - rl.getLastLimitValue() - subValue) / denominator;
						if (rate > maxRate) {
							rate = maxRate;
						}
					}
				}
				if (ActivityLuckManager.getInstance().getLimitRemain(items.get(limitIndex).getId()) > 0 && StringUtil.getRandom(0, 10000) < rate * 10000) {
					ActivityLuckManager.getInstance().reduceLimitRemain(items.get(limitIndex).getId());
					data[0] = limitIndex;
					data[ci*2+1] = items.get(limitIndex);
					data[ci*2+2] = 1;
					checkLimit=false;
				} else {
					int index = commonIndex[StringUtil.getRandom(0, commonIndex.length - 1)];
					data[0] = index;
					data[ci*2+1] = items.get(index);
					data[ci*2+2] = 0;
				}
			} else {
				int index = commonIndex[StringUtil.getRandom(0, commonIndex.length - 1)];
				data[0] = index;
				data[ci*2+1] = items.get(index);
				data[ci*2+2] = 0;
			}
		}
		return data;
	}
}
