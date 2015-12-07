package com.huayi.doupo.activity.data.luck;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.buffer.IoBuffer;

import com.huayi.doupo.activity.luck.ActivityLuckManager;
import com.huayi.doupo.activity.luck.LuckItem;
import com.huayi.doupo.activity.luck.RankLuck;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class ActivityLuckDataManager {
	/** 基础版 */
	public final static int BASE_0 = 0;
	/** 基础版 */
	public final static int BASE_2 = 2;

	public static final IoBuffer dataPacket() {
		IoBuffer buf = IoBuffer.getPacketBuffer();
//		try {
//			ActivityLuckManager acm = ActivityLuckManager.getInstance();
//			buf.putInt(BASE_2);
//			// 存时间
//			buf.putLong(0);
//			buf.putLong(0);
//			buf.putBoolean(acm.isDispatchedAward());
//
//			// 存限定物品
//			Map<Integer, Integer> remain = acm.getLimitRemain();
//			Set<Integer> keys = remain.keySet();
//			for (int k : keys) {
//				buf.putInt(k);
//				buf.putInt(remain.get(k));
//			}
//			buf.putInt(-1);
//			// 存排名
//			List<RankLuck> ranks = acm.getRankList();
//			RankLuck v = null;
//			for (int ri = 0; ri < ranks.size(); ri++) {
//				v = ranks.get(ri);
//				buf.putInt(v.getPlayerId());
//				buf.putString(v.getName());
//				buf.putInt(v.getValue());
//				buf.putInt(v.getOrder());
//				buf.putInt(v.getRefreshRemain());
//				buf.putInt(v.getStartRemain());
//				buf.putLong(v.getDate());
//				List<LuckItem> list = v.getItems();
//				LuckItem item = null;
//				for (int li = 0; li < list.size(); li++) {
//					item = list.get(li);
//					buf.putInt(item.getId());
//					buf.putInt(item.getType());
//					buf.putInt(item.getItemId());
//					buf.putInt(item.getCount());
//					buf.putInt(item.getRate1());
//					buf.putInt(item.getRate2());
//					buf.putBoolean(item.isLimit());
//				}
//				buf.putInt(-1);
//			}
//			buf.putInt(-1);
//		} catch (Exception e) {
//			LogUtil.error("幸运转轮数据异常", e);
//		} finally {
//			buf.flip();
//		}
		return buf;

	}

	public final static void setRankDatas(InputStream is) {
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			bis = new BufferedInputStream(is);
			dis = new DataInputStream(bis);
			byte[] data = new byte[dis.available()];
			dis.read(data);
			IoBuffer buffer = IoBuffer.getPacketBuffer();
			buffer.put(data);
			buffer.flip();
			int dataVersion = buffer.getInt();
			System.out.println("dataVersion="+dataVersion);
			switch (dataVersion) {
				case BASE_0: {
					LoadLuckData_V1.load(buffer);
					break;
				}
				case BASE_2: {
					LoadLuckData_V2.load(buffer);
					break;
				}
			}

		} catch (Exception e) {
			LogUtil.error("加载幸运转轮数据异常", e);
		}
	}

}
