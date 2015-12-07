package mmo.module.rank.data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import mmo.module.rank.RankList;
import mmo.module.rank.bean.RankData;
import mmo.tools.log.LoggerError;

import org.apache.mina.core.buffer.IoBuffer;

public class DataRank {
	/** 基础版 */
	public final static int BASE_0 = 0;

	/** 去掉位置 */
	public final static int V_1    = 1;
	/** 对斗法排名进行修复 */
	public final static int V_2    = 2;
	/** 对斗法排名进行修复 */
	public final static int V_3    = 3;
	public final static int V_4    = 4;
	public final static int V_5    = 5;

	public static final IoBuffer dataPacket(RankList rankList) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		try {
			buf.putInt(V_5);
			buf.putInt(rankList.getRecentIndex());
			RankData rd = null;
			List<RankData> list = rankList.getRankList();
			int rsize = list.size();
			for (int ri = 0; ri < rsize; ri++) {
				rd = list.get(ri);
				if (rd != null) {
					buf.putInt(rd.getRoleId());
					buf.putString(rd.getRoleName());
					buf.putInt(rd.getAccountId());
					buf.putLong(rd.getSectId());
					buf.putShort(rd.getLevel());
					buf.putLong(rd.getLevelupFristTime());
					buf.put(rd.getRealm());
					buf.put(rd.getProfession());
					buf.putInt(rd.getRankValue());
					buf.putInt(rd.getPreRankPosition());
					buf.putInt(rd.getCurrRankPosition());
					buf.put(rd.getSex());
				}
			}
			buf.putInt(-1);
		} catch (Exception e) {
			LoggerError.error("封装排行报错：" + rankList.getRankType(), e);
		} finally {
			buf.flip();
		}
		return buf;

	}

	public final static void setRankDatas(RankList rankList, Blob roleDatas) {
		if (roleDatas == null) {
			return;
		}
		InputStream is = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			is = roleDatas.getBinaryStream();
			bis = new BufferedInputStream(is);
			dis = new DataInputStream(bis);
			byte[] data = new byte[dis.available()];
			dis.read(data);
			IoBuffer buffer = IoBuffer.getPacketBuffer();
			buffer.put(data);
			buffer.flip();
			int dataVersion = buffer.getInt();
			switch (dataVersion) {
				case BASE_0: {
					RankLoad_V0.rankData(rankList, buffer);
					break;
				}
				case V_1: {
					RankLoad_V1.rankData(rankList, buffer);
					break;
				}
				case V_2: {
					RankLoad_V2.rankData(rankList, buffer);
					break;
				}
				case V_3: {
					RankLoad_V3.rankData(rankList, buffer);
					break;
				}
				case V_4: {
					RankLoad_V4.rankData(rankList, buffer);
					break;
				}
				case V_5: {
					RankLoad_V5.rankData(rankList, buffer);
					break;
				}
			}

		} catch (Exception e) {
			LoggerError.error("加载排行报错：" + rankList.getRankType(), e);
		}
	}

}
