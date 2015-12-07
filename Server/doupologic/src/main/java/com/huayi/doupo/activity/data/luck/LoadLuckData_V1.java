package com.huayi.doupo.activity.data.luck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;

import com.huayi.doupo.activity.luck.ActivityLuckManager;
import com.huayi.doupo.activity.luck.LuckItem;
import com.huayi.doupo.activity.luck.RankLuck;
import com.huayi.doupo.base.util.base.StringUtil;

public class LoadLuckData_V1 {
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
			StringBuilder sb = new StringBuilder();
			rl = new RankLuck();
			sb.append(id).append("|").append(buf.getString());
			sb.append("|").append(buf.getString());
			sb.append("|").append(buf.getInt());
			sb.append("|").append(buf.getInt());
			sb.append("|").append(buf.getInt());
			sb.append("|").append(buf.getInt());
			sb.append("|").append(buf.getLong());
			sb.append("|").append(buf.getString());
			// rl.setPlayerId(id); 
			// rl.setName(buf.getString());
			// rl.setValue(buf.getInt());
			// rl.setOrder(buf.getInt());
			// rl.setRefreshRemain(buf.getInt());
			// rl.setStartRemain(buf.getInt());
			// rl.setDate(buf.getLong());
			List<LuckItem> list = new ArrayList<LuckItem>();
			String[] array = StringUtil.splitString(buf.getString(), ';');
			for (String a : array) {
				list.add(new LuckItem(a, false));

			}
			int itemId = buf.getInt();
			LuckItem item = new LuckItem(buf.getString(), true);
			item.setId(itemId);
			list.add(item);
			rl.setItems(list);
			sb.append(rl.toForServer());
			System.out.println(sb.toString());
			// rankList.add(rl);
			// ranks.put(id, rl);
		}
	}
}
