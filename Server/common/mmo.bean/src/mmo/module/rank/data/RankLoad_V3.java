package mmo.module.rank.data;

import mmo.module.rank.RankList;
import mmo.module.rank.bean.RankData;

import org.apache.mina.core.buffer.IoBuffer;

public class RankLoad_V3 {
	final static void rankData(RankList rankList, IoBuffer buf) {
		rankList.setRecentIndex(buf.getInt());
		if (buf.getBoolean()) {
			String skeleton = buf.getString();
		}
		int roleId = -1;
		while ((roleId = buf.getInt()) != -1) {
			RankData rd = rankList.getRankData();
			rd.setRoleId(roleId);
			rd.setRoleName(buf.getString());
			rd.setAccountId(buf.getInt());
			rd.setSectId(buf.getLong());
			rd.setLevel(buf.getShort());
			rd.setLevelupFristTime(buf.getLong());
			rd.setRealm(buf.get());
			rd.setProfession(buf.get());
			rd.setRankValue(buf.getInt());
			rd.setPreRankPosition(buf.getInt());
			rd.setCurrRankPosition(buf.getInt());
			rankList.dbInitRank(rd);
		}
	}
}
