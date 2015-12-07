package com.huayi.doupo.activity.data.luck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;

import com.huayi.doupo.activity.luck.ActivityLuckManager;
import com.huayi.doupo.activity.luck.LuckItem;
import com.huayi.doupo.activity.luck.RankLuck;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstLuckRank;
import com.huayi.doupo.base.util.base.DateUtil;

public class LoadLuckData_V2 {
	public final static void load(IoBuffer buf) {
		ActivityLuckManager acm = ActivityLuckManager.getInstance();
		long start = buf.getLong();
		long stop = buf.getLong();
		boolean ispatchedAward = buf.getBoolean();

		int id = 0;
		Map<Integer, Integer> remain = acm.getLimitRemain();
		while ((id = buf.getInt()) > -1) {
			remain.put(id, buf.getInt());
		}
		System.out.println("remain=" + remain);

		// Map<Integer, RankLuck> ranks = acm.getRankMap();
		// List<RankLuck> rankList = acm.getRankList();
		RankLuck rl = null;
		while ((id = buf.getInt()) > -1) {
			InstLuckRank lr = new InstLuckRank();
			lr.setPlayerId(id);
			String name = buf.getString();
			int rankValue = buf.getInt();
			int order = buf.getInt();
			int refreshRemain = buf.getInt();
			int startRemain = buf.getInt();
			long date = buf.getLong();
			lr.setName(name);
			lr.setRankValue(rankValue);
			lr.setOrderIndex(order);
			lr.setRefreshRemain(refreshRemain);
			lr.setStartRemain(startRemain);
			lr.setCountReset(DateUtil.getTimeByMill(date));

			StringBuilder sb = new StringBuilder();
			rl = new RankLuck();
			sb.append(id).append("|").append(name);
			sb.append("|").append(rankValue);
			sb.append("|").append(order);
			sb.append("|").append(refreshRemain);
			sb.append("|").append(startRemain);
			sb.append("|").append(date);
			// rl.setPlayerId(id);
			// rl.setName(buf.getString());
			// rl.setValue(buf.getInt());
			// rl.setOrder(buf.getInt());
			// rl.setRefreshRemain(buf.getInt());
			// rl.setStartRemain(buf.getInt());
			// rl.setDate(buf.getLong());
			int itemId = 0;
			List<LuckItem> list = new ArrayList<LuckItem>();
			LuckItem item = null;
			while ((itemId = buf.getInt()) > -1) {
				item = new LuckItem();
				item.setId(itemId);
				item.setType(buf.getInt());
				item.setItemId(buf.getInt());
				item.setCount(buf.getInt());
				item.setRate1(buf.getInt());
				item.setRate2(buf.getInt());
				item.setLimit(buf.getBoolean());
				list.add(item);
			}
			rl.setItems(list);
			lr.setItems(rl.toForServer());
			sb.append(rl.toForServer());
			try {
				DALFactory.getInstLuckRankDAL().add(lr, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(sb.toString());
		}

	}
}
